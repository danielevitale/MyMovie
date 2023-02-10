package it.developer.film.controller;

import it.developer.film.entity.Genre;
import it.developer.film.entity.Language;
import it.developer.film.entity.Movie;
import it.developer.film.entity.Nationality;
import it.developer.film.payload.request.MovieRequest;
import it.developer.film.payload.response.MovieDetailsResponse;
import it.developer.film.payload.response.MovieResponse;
import it.developer.film.payload.response.WorkerMovieResponse;
import it.developer.film.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("movie")
public class MovieController {

    @Value("${movie.size}")
    private long size;

    @Value("${movie.width}")
    private int width;

    @Value("${movie.height}")
    private int height;

    @Value("${movie.extensions}")
    private String[] extensions;

    @Autowired
    MovieService movieService;

    @Autowired
    LanguageService languageService;

    @Autowired
    GenreService genreService;

    @Autowired
    MovieWorkerService movieWorkerService;

    @Autowired FileService fileService;
    @Autowired NationalityService nationalityService;

    // metodo per inserire un nuovo film
    @PutMapping("insert")
    public ResponseEntity<?> insertMovie(@RequestBody Movie movie) {

        if (movieService.getMovieForCheck(movie.getTitle(), movie.getProductionYear()) > 0)
            return new ResponseEntity<String>("This movie is already exists!!!", HttpStatus.NOT_ACCEPTABLE);

        Movie mov = new Movie(
                movie.getTitle(),
                movie.getPlot(),
                movie.getProductionYear(),
                movie.getDuration(),
                movie.getPoster(),
                movie.getNationality(),
                movie.getLanguages()
        );
        movieService.insertMovie(mov);

        return new ResponseEntity<String>("The operation run!", HttpStatus.CREATED);
    }

    // metodo per farsi stampare una lista di film superiori a una durata X
    @GetMapping("duration/{duration}")
    public ResponseEntity<?> getTitle(@PathVariable Long duration){
        List<MovieResponse> movieList = movieService.getTitle(duration);
        if (movieList.isEmpty()) {
            return new ResponseEntity<>("List is empty", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    // metodo per modificare le lingue in cui è stato doppiato quel film
    @PatchMapping("languages/{id}")
    @Transactional
    public ResponseEntity<?> updateLanguages(@PathVariable Long id, @RequestParam Set<String> stringLanguages) {

        Optional<Movie> m = movieService.findById(id);

        if (m.isEmpty()){
            return new ResponseEntity<>("Film not found", HttpStatus.NOT_FOUND);
        }

        Set<Language> languages = languageService.findByLanguageNameIn(stringLanguages);

        if(languages.isEmpty()){
            return new ResponseEntity<>("Language set empty", HttpStatus.BAD_REQUEST);
        }
        m.get().setLanguages(languages);

        return new ResponseEntity<String>("Lingue modificate", HttpStatus.OK);
    }

    // metodo per farsi tornare la lista di tutti i film inseriti
    @GetMapping("/findAll")
    public ResponseEntity<?> getAllMovie (){
        List<MovieResponse> movieList = movieService.getAllMovie();
        return new ResponseEntity<>(movieList,HttpStatus.OK);
    }
    // metodo per farsi tornare una lista di film in relazione alla lingua selezionata
    @GetMapping("/findByLanguage/{language}")
    public ResponseEntity<?> getLanguages(@PathVariable String language){

        List<String> m = movieService.findByLanguage(language);
        if(m.isEmpty()){
            return new ResponseEntity<String>("No movie found!", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(m,HttpStatus.OK);

    }

    // metodo per aggiungere l'immagine poster al film selezionato
    @PatchMapping("/add-poster/{movieId}")
    public ResponseEntity <?> addImage(@PathVariable long movieId, @RequestParam MultipartFile file){

        if(!fileService.checkSize(file, size))
            return new ResponseEntity<String>("File empty or size grater than "+size, HttpStatus.BAD_REQUEST);

        if(!fileService.checkDimensions(fileService.fromMutipartFileToBufferedImage(file), width, height))
            return new ResponseEntity<String>("Wrong width or height image", HttpStatus.BAD_REQUEST);

        if(!fileService.checkExtension(file, extensions))
            return new ResponseEntity<String>("File type not allowed", HttpStatus.BAD_REQUEST);

        Optional<Movie> m = movieService.findById(movieId);
        if(m.isEmpty())
            return new ResponseEntity<String>("Movie not found", HttpStatus.NOT_FOUND);

        String imageToUpload = fileService.uploadPostImage(file, movieId, m.get().getPoster());
        if(imageToUpload == null)
            return new ResponseEntity<String>("Something went wrong uploading image", HttpStatus.INTERNAL_SERVER_ERROR);

        m.get().setPoster(imageToUpload);
        movieService.insertMovie(m.get());

        return new ResponseEntity<String>("Image "+imageToUpload+" succesfully uploaded", HttpStatus.OK);

    }

    @GetMapping("/details/{id}")
    public ResponseEntity<?> getMovieDetails(@PathVariable long id){

        MovieDetailsResponse mdr = movieService.getMovieDetails(id);

        if(mdr == null){
            return new ResponseEntity<>("Movie not found", HttpStatus.NOT_FOUND);
        }

        Set<String> languages = languageService.getLanguagesByMovie(id);

        mdr.setLanguages(languages);

        Set<String> genres = genreService.getGenresByMovie(id);

        mdr.setGenres(genres);

        List<WorkerMovieResponse> workers = movieWorkerService.getWorkerByMovie(id);

        mdr.setWorkers(workers);


        return new ResponseEntity<>(mdr,HttpStatus.OK);

    }


    @PatchMapping("/update/{id}")
    @Transactional
    public ResponseEntity<?> updateMovie(@PathVariable long id, @RequestBody MovieRequest movieRequest) {

        Optional<Movie> m = movieService.findById(id);

        if (m.isEmpty()) {
            return new ResponseEntity<String>("Movie not found", HttpStatus.NOT_FOUND);
        }
        if(movieService.getMovieForCheckForUpdate(movieRequest.getTitle(),movieRequest.getProductionYear(), id) > 0){
            return new ResponseEntity<String>("Movie already exists", HttpStatus.BAD_REQUEST);
        }
        Optional<Nationality> na = nationalityService.findById(movieRequest.getNationalityName());
        if (na.isEmpty()) {
            return new ResponseEntity<String>("Nationality not found", HttpStatus.NOT_FOUND);
        }
        for (Genre genre : movieRequest.getGenres()) {
            if (!genreService.existsByGenre(genre.getGenreName()))
                return new ResponseEntity<String>("Genere " + genre.getGenreName() + " not found", HttpStatus.NOT_FOUND);
        }


        m.get().setTitle(movieRequest.getTitle());
        m.get().setPlot(movieRequest.getPlot());
        m.get().setProductionYear(movieRequest.getProductionYear());
        m.get().setDuration(movieRequest.getDuration());
        //m.get().getNationality().setNationalityName(movieRequest.getNationalityName());
        //m.get().setNationality(new Nationality(movieRequest.getNationalityName()));
        m.get().setNationality(na.get());

        m.get().setGenres(movieRequest.getGenres());


        return new ResponseEntity<String>("The operation run",HttpStatus.OK);
    }

}
