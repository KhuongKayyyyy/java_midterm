package com.example.demo;

import com.example.demo.model.Brand;
import com.example.demo.model.Cart;
import com.example.demo.model.Category;
import com.example.demo.model.User;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BrandService;
import com.example.demo.service.CartService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BookStore implements CommandLineRunner {
	@Autowired
	UserRepository userRepository;
	@Autowired
	CategoryService categoryService;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	BrandService brandService;
	@Autowired
	CartService cartService;
	@Autowired
	OrderService orderService;
	public static void main(String[] args) {
		SpringApplication.run(BookStore.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//test add to db
//		Category test = new Category();
//		test.setName("asdas");
//		categoryService.createNew(test);

		//test cart
//		User userTest = userRepository.findById(20).get();
//		Cart cart = cartRepository.findByUser(userTest);
//		if (cart == null){
//			cart = new Cart();
//			cart.setId(userTest.getId());
//			cart.setUser(userTest);
//			cartRepository.save(cart);
//		}
//		System.out.println("Cart Items: " + cart.getBooks());
//		System.out.println(cart.getId());
//		orderService.convertCartToOrder(cart);
//		cartService.removeFromCart(userTest,2);
//		cartService.proceedToOrder(userTest);

	}
}
