package com.Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Random;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import com.Common.BaseClass;

public class TC007_GenericManagenent_Investigation_Types extends BaseClass {

	@Test(priority = -1)

	public void Types() throws InterruptedException, IOException {
		readConfig();
		String path = prop.getProperty("Coppied");
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Investigation_Types");

		XSSFRow row = null;
		XSSFCell cell = null;

		String name = null;
		String primarytype = null;
		String result = null;
		String timeStamp = new SimpleDateFormat("HH:mm:ss<--->dd-MM-yyyy").format(new Date());
//		
//		Random r = new Random();
//		char c = (char) (r.nextInt(26) + 'a');
//		System.out.println(sheet.getLastRowNum());

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);

			for (int j = 0; j <= row.getLastCellNum(); j++) {
				cell = row.getCell(j);
				if (j == 1) {
					name = cell.getStringCellValue();
				}
				if (j == 2) {
					primarytype = cell.getStringCellValue();
				}

			}
			
			
			if(i<2) {
				// go to Investigation_Types page
				driver.findElement(By.xpath(prop.getProperty("gmBtn"))).click();

				driver.findElement(By.xpath(prop.getProperty("investigationsBtn"))).click();
				Thread.sleep(1000);
				//scroll menu
				WebElement menu = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/ul/li[3]/ul"));
				JavascriptExecutor js =  (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView(true);", menu);
				Thread.sleep(1000);
				
				driver.findElement(By.xpath(prop.getProperty("typesBtn"))).click();
				Thread.sleep(2000);
				
			}
			else {
				driver.findElement(By.xpath(prop.getProperty("typesBtn"))).click();
				Thread.sleep(2000);
			}
			
		
			// press on create Investigation_Types
			driver.findElement(By.xpath(prop.getProperty("cTypebtn"))).click();
			// send data to modal and press submit

			driver.findElement(By.xpath(prop.getProperty("Name"))).sendKeys(name);
			Thread.sleep(2000);
			// Primary Types Dropdown
			
			
			//Select pType = new Select(driver.findElement(By.xpath(prop.getProperty("dropdown"))));
			//pType.selectByVisibleText(primarytype);
			//System.out.println("Drop");
		
			
			if(primarytype !="") {
			WebElement testDropDown = driver.findElement(By.xpath(prop.getProperty("dropdown")));  
			Select dropdown = new Select(testDropDown);  
			dropdown.selectByVisibleText(primarytype);
			
			
			driver.findElement(By.xpath(prop.getProperty("Submit"))).click();
			Thread.sleep(1000);
			}
			
			else  {
				driver.findElement(By.xpath(prop.getProperty("Submit"))).click();
				Thread.sleep(1000);
			}
 
			try {

				// Sucess Alert
				String sMsg = driver.findElement(By.xpath(prop.getProperty("SuccessAlert"))).getText();
				String dupMsg = driver.findElement(By.xpath(prop.getProperty("SuccessAlert"))).getText();

				Boolean sAlert = sMsg.contains("All Records Saved!");
				Boolean dupAlert = dupMsg.contains("This data already exists");

				if (sAlert == true) {
					result = "PASS";
					cell = row.createCell(3);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(result);

					cell = row.createCell(4);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(timeStamp);

					cell = row.createCell(5);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(sMsg);

					driver.findElement(By.xpath(prop.getProperty("SuccessAlertOk"))).sendKeys(Keys.ENTER);

				}

				else if (dupAlert == true) {
					result = "PASS";
					cell = row.createCell(3);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(result);

					cell = row.createCell(4);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(timeStamp);

					cell = row.createCell(5);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(dupMsg);

					driver.findElement(By.xpath(prop.getProperty("SuccessAlertOk"))).sendKeys(Keys.ENTER);

				}

				else {
					result = "Failed";
					cell = row.createCell(2);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(result);
				}
			}

			catch (Exception e) {

				String smsvalidationMessage = driver.findElement(By.name("name")).getAttribute("validationMessage");
				String sptSmsvalidationMessage = driver.findElement(By.name("primary_types")).getAttribute("validationMessage");
				
				String sms = smsvalidationMessage;
				String sptSms = sptSmsvalidationMessage;
				boolean isError = sms.equals("Please fill in this field.");
				boolean isError2 = sms.equals("Please select an item in the list.");

				if (isError == true) {
					result = "PASS";
					cell = row.createCell(3);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(result);

					cell = row.createCell(4);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(timeStamp);

					cell = row.createCell(5);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(smsvalidationMessage);
				}
				
				else if (isError2 == true) {
					result = "PASS";
					cell = row.createCell(3);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(result);

					cell = row.createCell(4);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(timeStamp);

					cell = row.createCell(5);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(sptSmsvalidationMessage);
				} 

				
				
				else {
					result = "Failed";
					cell = row.createCell(3);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(result);
				}
			}

			FileOutputStream fos = new FileOutputStream(file);
			wb.write(fos);
			fos.close();

		}

	}
}
