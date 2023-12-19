package challenge.to_do.perficient_back_api.controller;

import challenge.to_do.perficient_back_api.repository.model.Multimedia;
import challenge.to_do.perficient_back_api.service.IMultimediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/multimedia")
public class MultimediaController {

    @Autowired
    private IMultimediaService multimediaService;

    @PostMapping("/{task_id}")
    public Optional<Multimedia> save(@RequestParam("multipartFile") MultipartFile multipartFile, @PathVariable Long task_id){
        Multimedia media = new Multimedia();
        try {
            media.setContenido(multipartFile.getBytes());
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return multimediaService.save(media,task_id);
    }

}
