package challenge.to_do.perficient_back_api.service.impl;

import challenge.to_do.perficient_back_api.repository.model.Multimedia;
import challenge.to_do.perficient_back_api.repository.model.Task;
import challenge.to_do.perficient_back_api.repository.persistence.IMultimediaRepository;
import challenge.to_do.perficient_back_api.repository.persistence.ITaskRepository;
import challenge.to_do.perficient_back_api.service.IMultimediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MultimediaServiceImpl implements IMultimediaService {

    @Autowired
    private IMultimediaRepository multimediaRepository;

    @Autowired
    private ITaskRepository taskRepository;

    @Override
    public Optional<Multimedia> save(Multimedia multimedia, Long taskId) {
        Task task = taskRepository.findById(taskId).get();

        multimedia.setTasks(task);

        return Optional.of(multimediaRepository.save(multimedia));
    }
}
