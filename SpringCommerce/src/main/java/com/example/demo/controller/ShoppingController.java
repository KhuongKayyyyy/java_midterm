package com.example.demo.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PreUpdate;
import javax.servlet.http.HttpSession;

import com.example.demo.model.Books;
import com.example.demo.model.Cart;
import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.repository.BooksRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CartService;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class ShoppingController {

	@Autowired
	private BooksRepository booksRepository;
	@Autowired
	private CartService cartService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	HttpSession session;
	@Autowired
	private OrderService orderService;

	public Page<Books> listAll(int pageNum) {
		int pageSize = 12;

		Pageable pageable = PageRequest.of(pageNum - 1, pageSize);

		return booksRepository.findAll(pageable);
	}

	@RequestMapping("/shopping/{pageNum}")
	public String viewPage(Model model,
						   @PathVariable(name = "pageNum") int pageNum) {

		Page<Books> page = listAll(pageNum);

		List<Books> listProducts = page.getContent();

		System.out.print(listProducts);

		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("bookList", listProducts);

		return "users/shopping";
	}

	@RequestMapping("/")
	public String viewHomePage(Model model) {
		return viewPage(model, 1);
	}

	@RequestMapping("/homepage")
	public String homepage() {
		return "users/home";
	}
	@ModelAttribute("books")
	public List<Books> getStudents(){
		return booksRepository.findAll();
	}

	@RequestMapping("/search")
	public String search(Model model, @Param("keyword") String keyword) {
		System.out.println("Keyword: "+keyword);
		List<Books> lstBooks = booksRepository.search(keyword);
		model.addAttribute("bookList", lstBooks);
		return "users/shopping";
	}
	@RequestMapping("/shopping/byCate")
	public String getBookByCategory(@RequestParam(name = "category_id",required = false) Integer categoryID, Model model){
		if (categoryID != null){
			List<Books> booksList = booksRepository.findByCategory_id(categoryID);
			model.addAttribute("bookList",booksList);
		}else{
			List<Books> allBooks = booksRepository.findAll();
			model.addAttribute("bookList",allBooks);
		}
		return "users/shopping";
	}
	@RequestMapping("/shopping/byBrand")
	public String getBookByBrand(@RequestParam(name = "brand_id",required = false) Integer brandId, Model model){
		if (brandId != null){
			List<Books> booksList = booksRepository.findByBrand_id(brandId);
			model.addAttribute("bookList",booksList);
		}else {
			List<Books> allBooks = booksRepository.findAll();
			model.addAttribute("bookList",allBooks);
		}
		return "users/shopping";
	}
	@GetMapping("/productDetail")
	public String detail(@RequestParam("booksId") String booksId, Model model) {

		System.out.println("Book id: " + booksId);

		Books books = booksRepository.getById(Integer.parseInt(booksId));

		model.addAttribute("books", books);

		return "users/productDetail";
	}
	@GetMapping("/cart")
	public String getCart(Model model,Principal principal) {
		User user = userRepository.findUserByUsername(principal.getName());
		List<Books> cart = cartService.getCart(user).getBooks();
		model.addAttribute("bookList",cart);
		return "users/cart";
	}
	@GetMapping("/cart/{bookId}")
	public String addToCart(@PathVariable Integer bookId, Principal principal,Model model){
		User user = userRepository.findUserByUsername(principal.getName());
		cartService.addToCart(user,bookId);
		List<Books> cart = cartService.getCart(user).getBooks();
		model.addAttribute("bookList",cart);
		return "users/cart";
	}
	@PostMapping("/cart/{bookId}")
	public String removeFromCart(@PathVariable Integer bookId, Principal principal){
		User user = userRepository.findUserByUsername(principal.getName());
		cartService.removeFromCart(user,bookId);
		return "redirect:/users/cart";
	}
	@GetMapping("/cart/proceedToOrder")
	public String proceedToOrder(@RequestParam(name = "total", required = false) Long totalValue,Model model, Principal principal) {
		User user = userRepository.findUserByUsername(principal.getName());
		Cart cart = cartService.getCart(user);

		if (cart != null && !cart.getBooks().isEmpty()) {
			Order order = orderService.convertCartToOrder(cart,totalValue);
			model.addAttribute("order", order);
			cartService.clearCart(user);
			return "users/orderSuccess";
		} else {
			model.addAttribute("errorMessage", "Your cart is empty. Add items to your cart before proceeding to order.");
			return "users/cart";
		}
	}
	@GetMapping("/order")
	public String getOrder(Model model, Principal principal){
		User user = userRepository.findUserByUsername(principal.getName());
		List<Order> orders = orderService.getOrderByUser(user);
		model.addAttribute("orders",orders);
		return "users/order";
	}
}
