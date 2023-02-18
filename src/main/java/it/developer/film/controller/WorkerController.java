package it.developer.film.controller;

import it.developer.film.entity.*;
import it.developer.film.payload.request.WorkerRequest;
import it.developer.film.payload.response.MovieWorkerResponse;
import it.developer.film.payload.response.WorkerDetailsResponse;
import it.developer.film.payload.response.WorkerResponse;
import it.developer.film.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("worker")
public class WorkerController {

    @Autowired
    WorkerService workerService;
    @Autowired
    LocalityService localityService;
    @Autowired
    WorkerImgService workerImgService;
    @Autowired
    FileService fileService;
    @Autowired
    MovieWorkerService movieWorkerService;

    @Value("${worker.size}")
    private long size;

    @Value("${worker.width}")
    private int width;

    @Value("${worker.height}")
    private int height;

    @Value("${worker.extensions}")
    private String[] extensions;

    @GetMapping("/{id}")
    public ResponseEntity<?> workerDetails(@PathVariable long id){

        WorkerDetailsResponse w = workerService.getWorkerDetails(id);

        if (w == null)
            return new ResponseEntity<>("Worker not found", HttpStatus.NOT_FOUND);

        List<MovieWorkerResponse> mw = movieWorkerService.getMovieByWorker(id);

        w.setMovieList(mw);

        return new ResponseEntity<>(w, HttpStatus.OK);
    }

    // metodo per inserire un nuovo professionista
    @PutMapping("/insert")
    public ResponseEntity<?> insertWorker(@RequestBody WorkerRequest worker){

        //Optional<Nationality> nat = nationalityService.existsByNationalityName(worker.getNationalityName());

        if(workerService.getWorkerForCheck(worker.getFirstName(),worker.getLastName(),worker.getBirthday()) > 0){
            return new ResponseEntity<String>("Worker already exists", HttpStatus.BAD_REQUEST);
        }

        Worker wor = new Worker(
                worker.getFirstName(),
                worker.getLastName(),
                worker.getBirthday(),
                new Locality(new LocalityId(new Nationality(worker.getNationalityName()), worker.getCityName()))
        );
        workerService.insertWorker(wor);

        return new ResponseEntity<String>("The operation run!", HttpStatus.CREATED);
    }

    // metodo per stampare la lista di professionisti gia inseriti
    @GetMapping("/findAll")
    public ResponseEntity<?>findAllWorker() {

        List<WorkerResponse> w = workerService.findAllWorker();
        return new ResponseEntity<>(w, HttpStatus.OK);

    }

    @PatchMapping(value="worker-img/{workerId}", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public ResponseEntity<?> updateWorkerImg(@PathVariable long workerId, @RequestParam @NotNull MultipartFile file) throws IOException {

        if(!fileService.checkSize(file, size))
            return new ResponseEntity("File empty or size grater than "+size, HttpStatus.BAD_REQUEST);

        if(!fileService.checkDimensions(fileService.fromMutipartFileToBufferedImage(file), width, height))
            return new ResponseEntity("Wrong width or height image", HttpStatus.BAD_REQUEST);

        if(!fileService.checkExtension(file, extensions))
            return new ResponseEntity("File type not allowed", HttpStatus.BAD_REQUEST);

        Optional<Worker> w = workerService.findById(workerId);
        WorkerImg workerImg = workerImgService.fromMultipartFileToAvatar(file);

        if(w.get().getWorkerImg() != null)
            workerImg.setId(w.get().getWorkerImg().getId());

        workerImgService.save(workerImg);

        w.get().setWorkerImg(workerImg);

        return new ResponseEntity("Your photo has been update",  HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteImg(@PathVariable long id){

        Optional<Worker> w = workerService.findById(id);

        if (w.isEmpty())
            return new ResponseEntity<>("Worker not found", HttpStatus.NOT_FOUND);

        WorkerImg img = w.get().getWorkerImg();

        if (img != null) {
            w.get().setWorkerImg(null);
            workerImgService.delete(img);
        }else{
            return new ResponseEntity<>("Nessuna immagine da eliminare", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Image deleted", HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    @Transactional
    public ResponseEntity<?>updateWorker(@PathVariable long id, @RequestBody WorkerRequest workerRequest){
        Optional<Worker> w = workerService.findById(id);
        if(w.isEmpty()){
            return new ResponseEntity<String>("Worker not found", HttpStatus.NOT_FOUND);
        }
        if(workerService.getWorkerForCheckForUpdate(id, workerRequest.getFirstName(), workerRequest.getLastName(), workerRequest.getBirthday())>0){
            return new ResponseEntity<String>("This Worker already present in another record", HttpStatus.BAD_REQUEST);
        }
        Optional<Locality> l = localityService.findByLocality(workerRequest.getNationalityName(), workerRequest.getCityName());
        if(l.isEmpty()){
            return new ResponseEntity<String>("Locality not found", HttpStatus.NOT_FOUND);
        }

        w.get().setFirstName(workerRequest.getFirstName());
        w.get().setLastName(workerRequest.getLastName());
        w.get().setBirthday(workerRequest.getBirthday());
        w.get().setLocality(new Locality(new LocalityId( new Nationality(workerRequest.getNationalityName()), workerRequest.getCityName())));

        return new ResponseEntity<String>("The operation run", HttpStatus.OK);
    }
}
