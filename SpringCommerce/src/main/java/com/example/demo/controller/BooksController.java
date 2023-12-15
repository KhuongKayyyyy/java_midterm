package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Books;
import com.example.demo.repository.BooksRepository;

@Controller
@RequestMapping(value = "/books")
public class BooksController {
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\";

    @Autowired
    private BooksRepository booksRepository;

    @RequestMapping("books-list")
    public String list() {
        return "books/books-list";
    }

    public Page<Books> listAll(int pageNum) {
        int pageSize = 10;

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);

        return booksRepository.findAll(pageable);
    }

    @RequestMapping("/page/{pageNum}")
    public String viewPage(Model model,
                           @PathVariable(name = "pageNum") int pageNum) {

        Page<Books> page = listAll(pageNum);

        List<Books> listProducts = page.getContent();

        System.out.print(listProducts);

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("books", listProducts);

        return "redirect:/books/books-list";
    }

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        return viewPage(model, 1);
    }


    @ModelAttribute("books")
    public List<Books> getStudents(){
        return booksRepository.findAll();
    }

    @GetMapping("detail")
    public String detail(@RequestParam("booksId") String booksId, Model model) {

        System.out.println("Book id: " + booksId);

        Books books = booksRepository.getById(Integer.parseInt(booksId));

        model.addAttribute("books", books);

        return "books/detail";
    }
    @RequestMapping("/new")
    public String newStudent(Model model) {
        model.addAttribute("books", new Books());
        return "books/new";
    }

    @RequestMapping("/saveOrUpdate")
    public String doSaveCustomer(@ModelAttribute("Books") Books books
            , Model model
            , @RequestParam("fileToUpload") MultipartFile file, RedirectAttributes attributes
    ) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        Path path = Paths.get(UPLOAD_DIRECTORY + fileName);

        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        books.setImage(fileName);

        System.out.print(books);
        booksRepository.save(books);
        return "redirect:/books/";
    }
    @GetMapping("edit")
    public String edit(@RequestParam("booksId") String id, Model model) {

        Books books = booksRepository.getById(Integer.parseInt(id));

        model.addAttribute("books", books);

        return "books/edit";
    }
    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {

        booksRepository.deleteById(id);

        return "redirect:/books/books-list";
    }

    @PostMapping("/update")
    public String update(
            @ModelAttribute("Books") Books books
            ,Model model
    ) {

        Optional<Books> book2 = booksRepository.findById(books.getId());

        books.setImage(book2.get().getImage());

        booksRepository.save(books);

        model.addAttribute("books", books);
        return "redirect:/books/books-list";
    }

    @GetMapping("/home1")
    public String home() {
        return "home1";
    }
}
