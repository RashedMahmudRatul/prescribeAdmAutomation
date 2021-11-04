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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.Common.BaseClass;

public class TC001_GenericManagenent_Advice extends BaseClass{
  
  @Test(priority = -1)

	public void Advice() throws InterruptedException, IOException {
	  readConfig();
		String path = prop.getProperty("Coppied");
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("CreateAdvice");

		XSSFRow row = null;
		XSSFCell cell = null;

		String name = null;
		String result = null;
		String timeStamp = new SimpleDateFormat("HH:mm:ss<--->dd-MM-yyyy").format(new Date());
//		
//		Random r = new Random();
//		char c = (char) (r.nextInt(26) + 'a');
//		System.out.println(sheet.getLastRowNum());

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			if (row != null) {
				cell = row.getCell(1);
				if(cell==null) {
					name="";
					
				}
				else if (cell!=null){
				
					name=cell.getStringCellValue();
				}
			} 
				// go to Advice page
				driver.findElement(By.xpath(prop.getProperty("mcBtn"))).click();
				Thread.sleep(100);
				driver.findElement(By.xpath(prop.getProperty("adviceBtn"))).click();
				// press on create Advice
				driver.findElement(By.xpath(prop.getProperty("createBtn"))).click();
				// send data to modal and press submit

				driver.findElement(By.xpath(prop.getProperty("Name"))).sendKeys(name);
				driver.findElement(
						By.xpath(prop.getProperty("Submit")))
						.click();
				Thread.sleep(1000);

				
				try {
					
					// Sucess Alert
					String sMsg = driver.findElement(By.xpath(prop.getProperty("SuccessAlert"))).getText();
					String dupMsg = driver.findElement(By.xpath(prop.getProperty("SuccessAlert"))).getText();
					
					Boolean sAlert = sMsg.contains("All Records Saved!");
					Boolean dupAlert = dupMsg.contains("The name has already been taken.");
					
					if (sAlert == true) {
						result = "PASS";
						cell = row.createCell(2);
						cell.setCellType(CellType.STRING);
						cell.setCellValue(result);
						
						cell = row.createCell(3);
						cell.setCellType(CellType.STRING);
						cell.setCellValue(timeStamp);
						
						cell = row.createCell(4);
						cell.setCellType(CellType.STRING);
						cell.setCellValue(sMsg);
						
						driver.findElement(By.xpath(prop.getProperty("SuccessAlertOk"))).sendKeys(Keys.ENTER);
						
					}
					
					else if (dupAlert == true) {
						result = "PASS";
						cell = row.createCell(2);
						cell.setCellType(CellType.STRING);
						cell.setCellValue(result);
						
						cell = row.createCell(3);
						cell.setCellType(CellType.STRING);
						cell.setCellValue(timeStamp);
						
						cell = row.createCell(4);
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
					 
					String validationMessage = driver.findElement(By.name("name")).getAttribute("validationMessage");
					String sms=validationMessage;
					boolean isError = sms.equals("Please fill out this field.");
					
					if (isError==true)
					{
					result = "PASS";
					cell = row.createCell(2);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(result);
					
					
					cell = row.createCell(3);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(timeStamp);
				
					
					cell = row.createCell(4);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(validationMessage);
					continue;
					}
					else {
						result = "Failed";
						cell = row.createCell(2);
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
