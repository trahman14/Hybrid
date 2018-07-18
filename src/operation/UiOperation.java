package operation;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class UiOperation {
	
	WebDriver driver; 
	
	public UiOperation(WebDriver driver) {
		this.driver=driver;
	}
	public void KeyWordPerfrom(Properties p, String Keyword, String ObjectName, String ObjectType, String Data ) throws Exception {
		
		switch (Keyword.toUpperCase()) {
		
		case "GOTOURL":
			driver.get(p.getProperty(Data));
			break;
		case "SENDKEYS":
			driver.findElement(this.getObject(p,  ObjectName, ObjectType)).sendKeys(Data);
			break;
			
		case "CLICK" :
			driver.findElement(this.getObject(p, ObjectName, ObjectType)).click();
			break;
		case "SELECT" :
			Select select = new Select(driver.findElement(this.getObject(p, ObjectName, ObjectType))); 
			select.selectByValue(Data);
			break; 
		case "GETTITLE": 
			System.out.println("Title is: " + driver.getTitle());
			break; 
		case "GETTEXT" : 
			String Text = driver.findElement(this.getObject(p, ObjectName, ObjectType)).getText(); 
			System.out.println("Text is:" + Text);
			break; 
			
		case "MOUSEHOVER": 
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(this.getObject(p, ObjectName, ObjectType))).build().perform();
			break;
		case "THREAD":
			Thread.sleep(3000);
			break;
		case "CLOSE":
			driver.close();
		break;
		case "SCREENSHOT":
			File sf =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(sf, new File("/Users/tarek.rahman/Desktop/ScreenShotSelenium/OurWebsite.png"));
			break;
			
		case "HIGHLIGHT":
			WebElement element = driver.findElement(this.getObject(p, ObjectName, ObjectType));
			JavascriptExecutor js = ((JavascriptExecutor)driver);
			js.executeScript("arguments[0].style.border='3px solid red'", element);
			break;
			
		case "GETPOINT":
			Point point=driver.findElement(this.getObject(p, ObjectName, ObjectType)).getLocation();
			int x=point.getX();
			int y=point.getY();
			System.out.println("Value of X ,Y:"+ x+" ,"+y);
			break;
			
		case "DROPDOWNLISTCOUNTING":
			List <WebElement>dropdown = driver.findElement(this.getObject(p, ObjectName, ObjectType)).findElements(By.tagName("option"));
			System.out.println("Total dropDown " + dropdown.size());
			
			for(int i=0; i<dropdown.size(); i++) {
				System.out.println(i+ ". " +dropdown.get(i).getText()); 
				
			}
			break;
			
		case "LINKCOUNTING": 
			List <WebElement> links = driver.findElements(By.tagName("a"));
			System.out.println("Total links are: " + links.size());
			
			for (int i=0; i<links.size(); i++) {
				
				System.out.println(i+ ". " + links.get(i).getText());
			}
			break;
		
		}
			
		
	
	}
	private By getObject(Properties p,  String ObjectName, String ObjectType) throws Exception {
		if(ObjectType.equalsIgnoreCase("ID")) {
			return By.id(p.getProperty(ObjectType));
		}
		else if (ObjectType.equalsIgnoreCase("xpath")) {
			return By.xpath(p.getProperty(ObjectName)); 
		}
		else if (ObjectType.equalsIgnoreCase("NAME")) {
			return By.name(p.getProperty(ObjectName));
	}
		else if (ObjectType.equalsIgnoreCase("LINKTEXT")) {
			return By.linkText(p.getProperty(ObjectName));
		}
		else if (ObjectType.equalsIgnoreCase("PARTIALLINKTEXT")) {
			return By.partialLinkText(p.getProperty(ObjectName));
		}
		else if (ObjectType.equalsIgnoreCase("CLASSNAME")) {
			return By.className(p.getProperty(ObjectName));
		}
		else if (ObjectType.equalsIgnoreCase("TAGNAME")) {
			return By.tagName(p.getProperty(ObjectName));
		}
		else if (ObjectType.equalsIgnoreCase("CSSSELECTOR")) {
			return By.cssSelector(p.getProperty(ObjectName));
		}
		else { 
			throw new Exception ("wrong Object Type");
		}
	}
}
