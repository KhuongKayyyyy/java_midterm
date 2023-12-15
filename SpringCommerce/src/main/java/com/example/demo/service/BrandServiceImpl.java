package com.example.demo.service;

import com.example.demo.model.Books;
import com.example.demo.model.Brand;
import com.example.demo.model.ResponseObject;
import com.example.demo.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService{
    @Autowired
    BrandRepository brandRepository;
    @Override
    public Brand createNew(Brand br) {
        return brandRepository.save(br);
    }

    @Override
    public List<Brand> getAllBranch() {
        return brandRepository.findAll();
    }

    @Override
    public ResponseEntity<ResponseObject> addBrand(Brand newBrand) {
        Brand foundBrand = brandRepository.findByName(newBrand.getName().trim());
        return foundBrand != null ?
                ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                        new ResponseObject(
                                "failed!",
                                "Brand name already taken",
                                ""
                        )
                ) :
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(
                                "successful!",
                                "Insert brand successfully",
                                brandRepository.save(newBrand)
                        )
                );
    }

    @Override
    public ResponseEntity<ResponseObject> updateBrand(Brand newBrand, Integer id) {
        Brand updateBrand = brandRepository.findById(id)
                .map(brand -> {
                    brand.setName(newBrand.getName());
                    return brandRepository.save(brand);
                }).orElseGet(() -> {
                    newBrand.setId(id);
                    return brandRepository.save(newBrand);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(
                        "successful",
                        "Update brand successfully",
                        updateBrand
                )
        );
    }

    @Override
    public ResponseEntity<ResponseObject> deleteBrand(Integer id) {
        boolean exists = brandRepository.existsById(id);
        Optional<Brand> delBrand = brandRepository.findById(id);

        if (exists && delBrand.isPresent()) {

            brandRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(
                            "Successful",
                            "Delete brand successfully!",
                            delBrand
                    )
            );
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(
                        "Failed",
                        "Cannot find product to delete!",
                        ""
                )
        );
    }
}
