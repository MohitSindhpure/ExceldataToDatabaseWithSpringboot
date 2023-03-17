package com.excel.helper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.excel.entities.Product;
import com.excel.utils.AppConstant;

/**
 * @author Mohit Sindhpure
 * @useClass {Helper.class}
 * @apiNote Excel-To-Database
 * @version springframework.boot [3.0.4]
 * @see PackageName
 * @use Excel-ContentType,Excel-File
 * @use AppConstant,Methods{checkExcelFormat,convertExcelToListofProduct}
 * @add dependency{poi-ooxml}
 * 
 */
public class Helper {
	
	

	// check that file is of excel type or not
	
	public static boolean checkExcelFormat(MultipartFile file) {
		
		String contentType = file.getContentType();
		if (contentType.equals(AppConstant.EXCELCONTENTTYPE)) {
			
			return true;
		} else {
			return false;
		}
	}

	// convert excel to list of products

	public static List<Product> convertExcelToListofProduct(InputStream is) {
		
		List<Product> list = new ArrayList<>();
		try {

			XSSFWorkbook workbook = new XSSFWorkbook(is);
			
			XSSFSheet sheet = workbook.getSheet(AppConstant.FILENAME);
			
			int rowNumber=0;
			Iterator<Row> iterator = sheet.iterator();
			
			while(iterator.hasNext())
			{
				Row row=iterator.next();
				
				if (rowNumber==0) {
					rowNumber++;
					continue;
				}
				Iterator<Cell> cells = row.iterator();
				
				int cid=0;
				Product p = new Product();
				
				while(cells.hasNext()) {
					Cell cell = cells.next();
					
					switch(cid) {
					case 0:
						p.setProductId((int)cell.getNumericCellValue());
						break;
						
					case 1:
						p.setProductName(cell.getStringCellValue().toUpperCase());
						break;
					
					case 2:
						p.setProductDesc(cell.getStringCellValue());
						break;
					
					case 3:
						p.setProductPrice(cell.getNumericCellValue());
						break;
					
					default:
						break;
					}
					cid++;
				}
				list.add(p);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
