package com.Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Random;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import com.Common.BaseClass;

public class TC001_MedicalCenterTypes extends BaseClass{
  
  @Test(priority = -1)

	public void MedicalCentreTypes() throws InterruptedException, IOException {

		String path = "E:\\Automation\\Excel\\READ.xlsx";
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("MedicalCenterType");

		XSSFRow row = null;
		XSSFCell cell = null;
		String cellt = null;

		String name = "";
		String result = null;
		String timeStamp = new SimpleDateFormat("HH:mm:ss:::dd-mm-yyyy").format(new Date(0));
		Random r = new Random();
		char c = (char) (r.nextInt(26) + 'a');
		System.out.println(sheet.getLastRowNum());
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			System.out.println("I am Testing Here");
			System.out.println(row);
			if (row != null) {
				cell = row.getCell(0);
				System.out.println(row.getCell(0));
				if(cell==null) {
					name="";
				}
			} else {
				row = sheet.createRow(i);
				cell = row.getCell(0);
			}
				// go to medical type page
				driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/ul/li[1]/a")).click();
				Thread.sleep(100);
				driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/ul/li[1]/ul/li[1]/a")).click();
				// press on create medical type
				driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/main/div/div/header/a")).click();
				// send data to modal and press submit
				System.out.println("Test name" + name);
				driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys(name);
				driver.findElement(
						By.xpath("//*[@id=\"root\"]/div/div[2]/div/main/div/div/div/div/div/form/footer/button[1]"))
						.click();
				System.out.println("Test name 2");
				Thread.sleep(1000);
				try {

					// Sucess Alert
					System.out.println("Test name 3");
					Boolean alert = driver.findElement(By.xpath("//*[@id=\"swal2-html-container\"]")).isDisplayed();
					if (alert == true) {
						result = "PASS";
						cell = row.createCell(1);
						cell.setCellType(CellType.STRING);
						cell.setCellValue(result);
						cell = row.createCell(2);
						cell.setCellType(CellType.STRING);
						cell.setCellValue(timeStamp);
						driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).sendKeys(Keys.ENTER);

					}
					System.out.println("Test name 4");

				} catch (Exception e) {
					System.out.println("Test");
					System.out.println(e);
				}

				Thread.sleep(1000);
				System.out.println("Test name 5");
				// driver.navigate().refresh();
				FileOutputStream fos = new FileOutputStream(file);
				wb.write(fos);
				System.out.println("Test name 6");
				fos.close();
				System.out.println("Test name 7");
		}
	}
}
