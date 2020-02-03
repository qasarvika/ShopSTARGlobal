package TestData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class TestData {

	private static Object[][] testdataProvider() throws FileNotFoundException, IOException, BiffException {
		return TestData();
	}

	public static Object[][] TestData() throws FileNotFoundException, IOException, BiffException {
		FileInputStream filepath = new FileInputStream("src/test/resources/TestData.xls");
		Workbook wb = Workbook.getWorkbook(filepath);
		Sheet sheet = wb.getSheet("testData");
		 
		int row = sheet.getRows();
		System.out.println("number of rows"+row);
		int column = sheet.getColumns();
		System.out.println("number of columns"+column);
		String Testdata[][] = new String[row-1][column];
		int count=0;

		for (int i = 1; i < row; i++)
		 {
		for (int j = 0; j < column; j++)
		 {
		Cell cell = sheet.getCell(j, i);
		Testdata[count][j] = cell.getContents();
		 }
		count++;
		}
		 filepath.close();
		 return Testdata;
	}
}
