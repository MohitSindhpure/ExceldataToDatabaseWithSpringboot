package com.excel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excel.entities.Product;

/**
 * @author Mohit Sindhpure
 * @useClass {ProductRepo.interface}
 * @apiNote Excel-To-Database
 * @version springframework.boot [3.0.4]
 * @see PackageName
 * 
 */
public interface ProductRepo extends JpaRepository<Product,Integer>{

}
