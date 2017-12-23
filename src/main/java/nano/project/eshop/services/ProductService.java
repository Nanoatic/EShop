package nano.project.eshop.services;


import nano.project.eshop.models.Product;
import nano.project.eshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(@Qualifier("productRepository") ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Product save(Product product){
        return productRepository.save(product);
    }



    public Product findById(Long id) {
        return productRepository.findOne(id);
    }


    public List<Product> findLast10(){
        return productRepository.findTop10ByOrderByIdDesc();
    }




    public List<Product> findByCategorie(String categorie){
        return productRepository.findByCategorie(categorie);
    }


    public long count() {
        return productRepository.count();
    }



    public void delete(long id) {
        productRepository.delete(id);
    }




}
