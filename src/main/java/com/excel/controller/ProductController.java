package com.excel.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.excel.entities.Product;
import com.excel.helper.Helper;
import com.excel.service.ProductService;
import com.excel.utils.AppConstant;

/**
 * @author Mohit Sindhpure
 * @useClass {ProductController.class}
 * @apiNote Excel-To-Database
 * @version springframework.boot [3.0.4]
 * @see PackageName
 * @use Annotation,Logger,AppConstant
 * @use Methods{upload,getAllProduct}
 * 
 */

@RestController
@CrossOrigin("*")
public class ProductController {

	@Autowired
	private ProductService productService;

	Logger logger=LoggerFactory.getLogger(ProductController.class);
	
	@PostMapping("/product/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {

		logger.info("Initiating a Dao call for Upload file");
		
		if (Helper.checkExcelFormat(file)) {

			this.productService.save(file);

			return ResponseEntity.ok(Map.of(AppConstant.MESSAGE, AppConstant.UPLOAD_SUCCESS));
		}
		logger.info("Completed a Dao call for Upload file");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(AppConstant.UPLOAD_FAILED);
	}
	
	@GetMapping("/product")
	public List<Product> getAllProduct(){
		logger.info("Initiating a Dao call for AllProduct");
		List<Product> products = this.productService.getAllProducts();
		logger.info("Completed a Dao call for AllProduct");
		return products; 
	}
}
