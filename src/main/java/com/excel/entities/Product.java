package com.excel.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Mohit Sindhpure
 * @useClass {Product.class}
 * @apiNote Excel-To-Database
 * @version springframework.boot [3.0.4]
 * @see PackageName, AllFields
 * @use Annotation,lombok
 * 
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Product {

	@Id
	private Integer productId;
	
	private String productName;
	
	private String productDesc;
	
	private Double productPrice; 
}
