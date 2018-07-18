


package testCase;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import operation.ReadObject;
import operation.UiOperation;

public class HybridTestCase {
	
	WebDriver driver; 
	
	@Test (dataProvider = "HybridData")
	public void HybridTest(String TestCaseID, String TestCaseName, String KeyWord, String ObjectName, String ObjectType, String Data) throws Exception {
		
		if(TestCaseName !=null && TestCaseName.length() !=0) {
			
			driver = new FirefoxDriver(); 
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS); 
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS) ;
			
		}
		
		ReadObject object= new ReadObject() ;
		Properties allObject = object.getObjectRepository();
		
		UiOperation opn = new UiOperation(driver); 
		opn.KeyWordPerfrom(allObject, KeyWord, ObjectName, ObjectType, Data);
	}
    @DataProvider(name="HybridData")
    public Object[][] getData() throws IOException {

    	Object [][] object=null;
   	//File fl = new File(System.getProperty("user.dir")+"\\"+"frameWork.xlsx"); 
    	File fl = new File("/Users/tarek.rahman/Documents/AutomationTestingTraining/OldFiles/eclipse-workspace/HybridFrameWork/frameWork.xlsx"); 

    	FileInputStream fis = new FileInputStream(fl); 
    	Workbook wb = new XSSFWorkbook(fis);// excel spread sheet format 
    	Sheet ws = wb.getSheet("Data1");
    	int RowCount = ws.getLastRowNum()-ws.getFirstRowNum();
    	int ColumnCount = 6;
    	object = new Object [RowCount][ColumnCount];
    	
    	for(int i =0; i<RowCount; i++) {
    		Row ro = ws.getRow(i+1); 
    		for (int j = 0; j<ro.getLastCellNum();j++) {
    			object[i][j] = ro.getCell(j).toString();
    			
    		}
    		
    		
    	}
    		
    	return object;
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
