package challenge.to_do.perficient_back_api.service;

import challenge.to_do.perficient_back_api.repository.model.Category;

import java.util.Optional;

public interface ICategoryService {
    Optional<Category> save(Category category);
    Optional<Category> findById(Long id);
    void delete(Long id);

   Iterable<Category> findAll();
}
