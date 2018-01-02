package nano.project.eshop.repositories;

import nano.project.eshop.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

public interface SolrProductRepository extends SolrCrudRepository<Product,String>{
    @Query("name:*?0* OR details:*?0*")
    Page<Product> findByNameContainingIgnoreCaseOrDetailsContainingIgnoreCase(String name,String details, Pageable pageable);

    Product findById(Long id);
    List<Product> findAllByIdIn(List<Long> id);
}
