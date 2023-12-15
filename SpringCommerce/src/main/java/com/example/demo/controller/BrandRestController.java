package com.example.demo.controller;

import com.example.demo.model.Brand;
import com.example.demo.model.ResponseObject;
import com.example.demo.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/vi/Brands")
public class BrandRestController {
    @Autowired
    private BrandService brandService;
    @GetMapping("")
    List<Brand> getAll(){return brandService.getAllBranch();}
//    @GetMapping("/{id}")
//    ResponseEntity<ResponseObject> findById(@PathVariable Integer id){
//        return brandService
//    }

    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertBrand(@RequestBody Brand newBrand){
        return  brandService.addBrand(newBrand);
    }
    @PutMapping("{id}")
    ResponseEntity<ResponseObject> updateBrand(@RequestBody Brand newBrand, @PathVariable Integer id){
        return brandService.updateBrand(newBrand,id);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteBrand(@PathVariable Integer id){
        return brandService.deleteBrand(id);
    }
}
