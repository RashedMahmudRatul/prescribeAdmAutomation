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

public class TC_000_Login extends BaseClass {

	@Test(priority = -1)

	public void Login() throws InterruptedException, IOException {
		readConfig();
		String path = prop.getProperty("Coppied");
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Login");

		XSSFRow row = null;
		XSSFCell cell = null;

		String email = null;
		String pass = null;

		String result = null;
		String timeStamp = new SimpleDateFormat("HH:mm:ss<--->dd-MM-yyyy").format(new Date());
//		
//		Random r = new Random();
//		char c = (char) (r.nextInt(26) + 'a');
//		System.out.println(sheet.getLastRowNum());

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			//System.out.println(sheet.getLastRowNum());
			
			for (int j = 0; j <= row.getLastCellNum(); j++) {
				cell = row.getCell(j);

				if (j == 1) {
					email = cell.getStringCellValue();
				}
				if (j == 2) {
					pass = cell.getStringCellValue();
				}
			}
			if(i<=1) {
			// go to doctor type page
			driver.findElement(By.xpath(prop.getProperty("rLogo"))).click();
			Thread.sleep(100);
			driver.findElement(By.xpath(prop.getProperty("logout"))).click();
			// press on create doctor type
			driver.findElement(By.xpath(prop.getProperty("logEmail"))).sendKeys(email);
			driver.findElement(By.xpath(prop.getProperty("logPass"))).sendKeys(pass);

			driver.findElement(By.xpath(prop.getProperty("logBtn"))).click();
			Thread.sleep(1000);
			}
			
			if(i<=1) {
				// go to doctor type page
				driver.findElement(By.xpath(prop.getProperty("rLogo"))).click();
				Thread.sleep(100);
				driver.findElement(By.xpath(prop.getProperty("logout"))).click();
				// press on create doctor type
				driver.findElement(By.xpath(prop.getProperty("logEmail"))).sendKeys(email);
				driver.findElement(By.xpath(prop.getProperty("logPass"))).sendKeys(pass);

				driver.findElement(By.xpath(prop.getProperty("logBtn"))).click();
				Thread.sleep(1000);
				}
				
			try {

				// Sucess Alert
				boolean sucess = driver.findElement(By.xpath(prop.getProperty("remediLogo"))).isDisplayed();


				if (sucess == true) {
					result = "Login PASS";
					cell = row.createCell(3);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(result);

					cell = row.createCell(4);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(timeStamp);

					cell = row.createCell(5);
					cell.setCellType(CellType.STRING);
					cell.setCellValue("Logo Appears");

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
				String errMsg = driver.findElement(By.xpath(prop.getProperty("logErr"))).getText();
				boolean logErr = driver.findElement(By.xpath(prop.getProperty("loginErr"))).isDisplayed();

				if (logErr == true) {
					result = "PASS";
					cell = row.createCell(3);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(result);

					cell = row.createCell(4);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(timeStamp);

					cell = row.createCell(5);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(errMsg);
					//continue;
				}


				else {
			
					result = "Failed";
					cell = row.createCell(3);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(result);

					cell = row.createCell(4);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(timeStamp);
				}



			}

			FileOutputStream fos = new FileOutputStream(file);
			wb.write(fos);
			fos.close();

		}

	}
}
