package com.excel.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.excel.entities.Product;
import com.excel.helper.Helper;
import com.excel.repositories.ProductRepo;

/**
 * @author Mohit Sindhpure
 * @useClass {ProductService.class}
 * @apiNote Excel-To-Database
 * @version springframework.boot [3.0.4]
 * @see PackageName
 * @use Annotation,Logger,AppConstant
 * @use Methods{save,getAllProducts}
 * 
 */
@Service
public class ProductService {

	@Autowired
	private ProductRepo productRepo;
	
	Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	public void save(MultipartFile file) {
		
		logger.info("Initiating Service call for save Method");
		try {
			
			List<Product> products = Helper.convertExcelToListofProduct(file.getInputStream());	
			List<Product> list = products.stream()
					.filter(prod->!prod.getProductDesc().isEmpty() &&
					!prod.getProductName().isEmpty() &&
					prod.getProductId() != 0 &&
					prod.getProductPrice() >=0
					).collect(Collectors.toList());
			
				this.productRepo.saveAll(list);
				logger.info("Completed Service call for save Method");	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Product> getAllProducts(){
		logger.info("Initiating AllProducts call for getall Method");
		List<Product> all = this.productRepo.findAll();
		logger.info("Completed AllProducts call for getall Method");
		return all;
		
		
	}
}
