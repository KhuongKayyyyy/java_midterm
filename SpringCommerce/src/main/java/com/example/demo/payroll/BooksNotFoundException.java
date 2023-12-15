package com.example.demo.payroll;

public class BooksNotFoundException extends RuntimeException{
	public BooksNotFoundException(Integer id) {
	    super("Could not find Book " + id);
	  }
}
