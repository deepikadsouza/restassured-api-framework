package GenericUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {


	//String projectPath = System.getProperty("user.dir");
	String filepath="src/test/resources/testData/TestData.xlsx";
	
	
	public String getDataFromExcel(String sheetName, int rowNum, int colNum) throws EncryptedDocumentException, IOException {
		FileInputStream fileInputStream=new FileInputStream(filepath);
		Workbook wb = WorkbookFactory.create(fileInputStream);
		String data = wb.getSheet(sheetName).getRow(rowNum).getCell(colNum).toString();
		wb.close();
		return data;
		
	}
	
	public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException {
        FileInputStream fis = new FileInputStream(filepath);
        Workbook wb = WorkbookFactory.create(fis);
        int rowCount = wb.getSheet(sheetName).getLastRowNum();
        wb.close();
        return rowCount;
    }
	
	public void writeDataIntoExcel(String sheetName, int rowNum, int cellNum, String data) throws Throwable
	{
	    FileInputStream fis = new FileInputStream(filepath);
	    Workbook wb = WorkbookFactory.create(fis);

	    Sheet sh = wb.getSheet(sheetName);

	    Row row = sh.getRow(rowNum);
	    if(row == null)
	    {
	        row = sh.createRow(rowNum);
	    }

	    Cell cell = row.createCell(cellNum);
	    cell.setCellValue(data);

	    FileOutputStream fos = new FileOutputStream(filepath);
	    wb.write(fos);

	    wb.close();
	}
}
