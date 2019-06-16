package com.pkg.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	 String inputpath=FileWritter.getDesktopPath();
	 String filename="UserID.xlsx";
	 XSSFWorkbook workbook;
	 XSSFSheet sheet;
	 String requestBody;
	public ArrayList<Integer> data = new ArrayList<Integer>();	
	 
	 public static void main (String []args) throws IOException{
		 ExcelReader ex=new ExcelReader();
		 ex.excelread();
	 }
	
	public ArrayList<Integer> excelread() throws IOException{
		File file = new File(inputpath+filename);
		FileInputStream fileinput = new FileInputStream(file);
		workbook = new XSSFWorkbook(fileinput);
		sheet=workbook.getSheetAt(0);
		Iterator<Row> rows = sheet.iterator();
		Row firstrow = rows.next();
		Iterator<Cell> cell = firstrow.cellIterator();
		int k = 0;
		int coloumn = 0;
		while (cell.hasNext()) {
			Cell value = cell.next();			
			if (value.getStringCellValue().equalsIgnoreCase("User ID")) {
				coloumn = k;
			}
			k++;
				
			while (rows.hasNext()) {
				Row r = rows.next();						
				data.add((int) r.getCell(coloumn).getNumericCellValue());				
				
		}
		
	}
		System.out.println(data);
		return data;
}

}