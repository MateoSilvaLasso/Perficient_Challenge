package challenge.to_do.perficient_back_api.service;

import challenge.to_do.perficient_back_api.repository.model.Multimedia;
import challenge.to_do.perficient_back_api.repository.model.Task;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface IMultimediaService {

    Optional<Multimedia> save(Multimedia multimedia, Long taskId);

}
