package nano.project.eshop;

import nano.project.eshop.models.User;
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
	UserService userService ;
	@Test
	public void contextLoads() {
		System.out.println("Hello world");
		List<User> users = userService.findAllUsers();
		for (User user:
			 users) {
			System.out.println(user.getFirstName());

		}
	}

}
