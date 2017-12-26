package nano.project.eshop.repositories;


import nano.project.eshop.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productRepository")
public interface ProductRepository extends CrudRepository<Product, Long> {
    Long countByCategory(String category);

    List<Product> findByCategory(String category);

    Page<Product> findByCategory(String category, Pageable pageable);

    Product findById(long id);

    void removeById(long id);

    Product save(Product product);

    List<Product> findTop10ByOrderByIdDesc();
}
