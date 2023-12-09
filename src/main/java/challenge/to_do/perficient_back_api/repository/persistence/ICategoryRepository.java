package challenge.to_do.perficient_back_api.repository.persistence;

import challenge.to_do.perficient_back_api.repository.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface ICategoryRepository extends CrudRepository<Category,Long> {

}
