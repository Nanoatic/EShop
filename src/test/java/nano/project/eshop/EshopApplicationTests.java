package nano.project.eshop;

import nano.project.eshop.models.Category;
import nano.project.eshop.models.Order;
import nano.project.eshop.models.Product;
import nano.project.eshop.models.User;
import nano.project.eshop.services.OrderService;
import nano.project.eshop.services.ProductService;
import nano.project.eshop.services.SolrProductService;
import nano.project.eshop.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EshopApplicationTests {
	@Autowired
	ProductService productService;
	@Autowired
	SolrProductService solrProductService;
	@Test
	public void contextLoads() {
		for (int i = 0; i < 20 ; i++) {
			Product product = new Product();
			product.setCategory(Category.CONSOLE);
			product.setDetails("You vexed shy mirth now noise. Talked him people valley add use her depend letter. Allowance too applauded now way something recommend. Mrs age men and trees jokes fancy. Gay pretended engrossed eagerness continued ten. Admitting day him contained unfeeling attention mrs out. \n");
			product.setName(Helpers.gencode9());
			product.setPhoto("/img/xbox.jpg");
			product.setPrice(399.0f);
			product.setQuantity(15);
			solrProductService.saveProduct(product);

		}




	}

}
