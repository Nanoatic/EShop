package nano.project.eshop.repositories;


import nano.project.eshop.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productRepository")
public interface ProductRepository extends CrudRepository<Product, Long> {

     List<Product> findByCategory(String category);
     Product findById(long id);
     void removeById(long id);
     Product save(Product product);
     List<Product> findTop10ByOrderByIdDesc();
}
