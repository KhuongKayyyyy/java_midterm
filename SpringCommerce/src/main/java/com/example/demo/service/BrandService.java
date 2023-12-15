package com.example.demo.service;

import com.example.demo.model.Brand;
import com.example.demo.model.ResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface BrandService {
    Brand createNew(Brand br);
    List<Brand> getAllBranch();
//    ResponseEntity<ResponseObject> getById(Integer id);
    ResponseEntity<ResponseObject> addBrand(Brand newBrand);
    ResponseEntity<ResponseObject> updateBrand(Brand newBrand,Integer id);
    ResponseEntity<ResponseObject> deleteBrand(@PathVariable Integer id);
}
