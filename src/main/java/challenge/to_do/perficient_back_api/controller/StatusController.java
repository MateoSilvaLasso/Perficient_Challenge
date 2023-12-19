package challenge.to_do.perficient_back_api.controller;

import challenge.to_do.perficient_back_api.repository.model.Status;
import challenge.to_do.perficient_back_api.repository.persistence.IStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/status")
public class StatusController {

    @Autowired
    private IStatusRepository statusRepository;

    @GetMapping("")
    private Iterable<Status> getStatus(){
        return statusRepository.findAll();
    }

}
