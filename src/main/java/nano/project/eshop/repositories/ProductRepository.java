package nano.project.eshop.repositories;


import nano.project.eshop.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productRepository")
public interface ProductRepository extends CrudRepository<Product, Long> {

    public List<Product> findByCategorie(String Categorie);
    public Product findById(long id);
    public void removeById(long id);
    public Product save(Product product);
    public List<Product> findTop10ByOrderByIdDesc();
}
