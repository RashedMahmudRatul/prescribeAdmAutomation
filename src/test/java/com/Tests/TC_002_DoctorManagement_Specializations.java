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

public class TC_002_DoctorManagement_Specializations extends BaseClass{
  
  @Test(priority = -1)

	public void Specializations() throws InterruptedException, IOException {
	  readConfig();
		String path =prop.getProperty("Coppied");
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Speciallizations");

		XSSFRow row = null;
		XSSFCell cell = null;

		String name = null;
		String sName = null;
		
		String result = null;
		String timeStamp = new SimpleDateFormat("HH:mm:ss<--->dd-MM-yyyy").format(new Date());
//		
//		Random r = new Random();
//		char c = (char) (r.nextInt(26) + 'a');
//		System.out.println(sheet.getLastRowNum());

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			System.out.println(sheet.getLastRowNum());
			for(int j=0; j<=row.getLastCellNum(); j++)
			{
				cell=row.getCell(j);
				
				if(j==1)
				{
					name = cell.getStringCellValue();
				}
				if(j==2)
				{
				sName = cell.getStringCellValue();	
				}
			}
			
			

				// go to doctor type page
				driver.findElement(By.xpath(prop.getProperty("dmBtn"))).click();
				Thread.sleep(100);
				driver.findElement(By.xpath(prop.getProperty("specializationsBtn"))).click();
				// press on create doctor type
				driver.findElement(By.xpath(prop.getProperty("cspBtn"))).click();
				// send data to modal and press submit

				driver.findElement(By.xpath(prop.getProperty("cspName"))).sendKeys(name);
				driver.findElement(By.xpath(prop.getProperty("cspSname"))).sendKeys(sName);
				
				driver.findElement(By.xpath(prop.getProperty("cspSubmit"))).click();
				Thread.sleep(1000);

				
				try {
					
					// Sucess Alert
					String sMsg = driver.findElement(By.xpath(prop.getProperty("SuccessAlert"))).getText();
					String dupMsg = driver.findElement(By.xpath(prop.getProperty("SuccessAlert"))).getText();
					
					Boolean sAlert = sMsg.contains("All Records Saved!");
					Boolean dupAlert = dupMsg.contains("The name has already been taken.");
					
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
						cell = row.createCell(3);
						cell.setCellType(CellType.STRING);
						cell.setCellValue(result);
					}
				} 
				
				catch (Exception e) {
					 
					String nvalidationMessage = driver.findElement(By.name("name")).getAttribute("validationMessage");
					String snValidationMessage = driver.findElement(By.name("short_name")).getAttribute("validationMessage");
					String nSms=nvalidationMessage;
					String snSms=snValidationMessage;
					
					boolean isError = nSms.equals("Please fill out this field.");
					boolean isError2 = snSms.equals("Please fill out this field.");
					
					if (isError==true)
					{
					result = "PASS";
					cell = row.createCell(3);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(result);
					
					
					cell = row.createCell(4);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(timeStamp);
				 
					
					cell = row.createCell(5);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(nSms);
					continue;
					}
					
					else if (isError2==true)
					{
					result = "PASS";
					cell = row.createCell(3);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(result);
					
					
					cell = row.createCell(4);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(timeStamp);
				 
					
					cell = row.createCell(5);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(snSms);
					continue;
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
