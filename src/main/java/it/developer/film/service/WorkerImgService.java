package it.developer.film.service;

import it.developer.film.entity.WorkerImg;
import it.developer.film.repository.WorkerImgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class WorkerImgService {

    @Autowired
    WorkerImgRepository workerImgRepository;

    public Optional<WorkerImg> findById (long id){
        return  workerImgRepository.findById(id);
    }

    public void save(WorkerImg workerImg){
        workerImgRepository.save(workerImg);
    }

    public void delete(WorkerImg workerImg){
        workerImgRepository.delete(workerImg);
    }

    public WorkerImg fromMultipartFileToAvatar(MultipartFile file) throws IOException {
        WorkerImg workerImg = new WorkerImg(
                file.getOriginalFilename(),
                file.getContentType(),
                file.getBytes()
        );
        return workerImg;
    }

}
