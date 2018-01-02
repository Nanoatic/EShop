package nano.project.eshop.services;

import nano.project.eshop.models.Product;
import nano.project.eshop.repositories.SolrProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("solrProductService")
public class SolrProductService {
    private SolrProductRepository solrProductRepository;

    @Autowired
    public SolrProductService(SolrProductRepository solrProductRepository) {
        this.solrProductRepository = solrProductRepository;
    }

    public Page<Product> searchByName(String name, Pageable pageable) {
        return solrProductRepository.findByNameContainingIgnoreCaseOrDetailsContainingIgnoreCase(name,name, pageable);

    }

    public Product saveProduct(Product product) {
        return solrProductRepository.save(product);
    }

    public void deleteProduct(Product product) {
        solrProductRepository.delete(product);
    }

    public Product findById(Long id){
        return  solrProductRepository.findById(id);
    }
    public List<Product> findAllByIds(List<Long> ids){
        return  solrProductRepository.findAllByIdIn(ids);
    }
    public  void deleteProducts(List<Product> products){
        solrProductRepository.delete(products);
    }
    public List<Product> findAllProducts(){
        return (List<Product>) solrProductRepository.findAll();
    }
}
