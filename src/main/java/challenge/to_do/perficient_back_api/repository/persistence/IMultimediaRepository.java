package challenge.to_do.perficient_back_api.repository.persistence;

import challenge.to_do.perficient_back_api.repository.model.Multimedia;
import challenge.to_do.perficient_back_api.repository.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface IMultimediaRepository extends CrudRepository<Multimedia, Long> {

}
