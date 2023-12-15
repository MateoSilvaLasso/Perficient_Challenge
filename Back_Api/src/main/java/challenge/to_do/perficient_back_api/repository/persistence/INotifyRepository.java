package challenge.to_do.perficient_back_api.repository.persistence;

import challenge.to_do.perficient_back_api.repository.model.Notify;
import org.springframework.data.repository.CrudRepository;

public interface INotifyRepository extends CrudRepository<Notify, Long> {
}
