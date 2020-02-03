package ProdData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ProdData {
	
	public static Object[][] proddataprovider() throws FileNotFoundException, IOException, BiffException {
		return proddataprovider();
	}

	public static Object[][] ProdData() throws FileNotFoundException, IOException, BiffException {
		FileInputStream filepath = new FileInputStream("C:/Users/NTimilsi/git/ShopStarProd/src/test/resources/ProdData.xls");
		Workbook wb = Workbook.getWorkbook(filepath);
		Sheet sheet = wb.getSheet("ProdData");
		 
		int row = sheet.getRows();
		System.out.println("number of rows"+row);
		int column = sheet.getColumns();
		System.out.println("number of columns"+column);
		String ProdData[][] = new String[row-1][column];
		int count=0;

		for (int i = 1; i < row; i++)
		 {
		for (int j = 0; j < column; j++)
		 {
		Cell cell = sheet.getCell(j, i);
		ProdData[count][j] = cell.getContents();
		 }
		count++;
		}
		
		filepath.close();
		return ProdData;
	}
}

