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
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.Common.BaseClass;

public class TC_003_DoctorManagement_Doctors extends BaseClass {

	@Test(priority = -1)

	public void Doctors() throws InterruptedException, IOException {
		readConfig();
		String path = prop.getProperty("Coppied");
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Doctors");

		XSSFRow row = null;
		XSSFCell cell = null;

		String fName = null;
		String lName = null;
		String gender = null;
		String dob = null;
		String phone = null;
		String email = null;
		String pass = null;
		String bmdc = null;
		String dType = null;
		String degree = null;
		String education = null;
		String address = null;
		String work = null;

		String result = null;
		String timeStamp = new SimpleDateFormat("HH:mm:ss<--->dd-MM-yyyy").format(new Date());
//		
//		Random r = new Random();
//		char c = (char) (r.nextInt(26) + 'a');
//		System.out.println(sheet.getLastRowNum());

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			// System.out.println(sheet.getLastRowNum());

			for (int j = 1; j <= row.getLastCellNum(); j++) {
				cell = row.getCell(j);

				if (j == 1) {
					fName = cell.getStringCellValue();
				}
				if (j == 2) {
					lName = cell.getStringCellValue();
				}

				if (j == 3) {
					gender = cell.getStringCellValue();
				}
				if (j == 4) {
					dob = cell.getStringCellValue();
				}
				if (j == 5) {
					phone = cell.getStringCellValue();
				}
				if (j == 6) {
					email = cell.getStringCellValue();
				}

				if (j == 7) {
					pass = cell.getStringCellValue();
				}
				if (j == 8) {
					bmdc = cell.getStringCellValue();
				}	

				if (j == 9) {
					dType = cell.getStringCellValue();
				}
				if (j == 10) {
					degree = cell.getStringCellValue();
				}
				if (j == 11) {
					education = cell.getStringCellValue();
				}
				if (j == 12) {
					address = cell.getStringCellValue();
				}

				if (j == 13) {
					work = cell.getStringCellValue();
				}

			}

			// go to doctor type page
			driver.findElement(By.xpath(prop.getProperty("dmBtn"))).click();
			Thread.sleep(100);
			driver.findElement(By.xpath(prop.getProperty("doctorsBtn"))).click();
			// press on create doctor type
			driver.findElement(By.xpath(prop.getProperty("cdocBtn"))).click();
			// send data to modal and press submit

			driver.findElement(By.xpath(prop.getProperty("cdocFname"))).sendKeys(fName);
			driver.findElement(By.xpath(prop.getProperty("cdocLname"))).sendKeys(lName);
		
			//Gender Dropdown
			WebElement genderdd = driver.findElement(By.xpath(prop.getProperty("cdocGender")));  
			Select gDropdown = new Select(genderdd);  
			gDropdown.selectByVisibleText(gender);
			
			//DoB Field
			driver.findElement(By.xpath(prop.getProperty("cdocDob"))).sendKeys(dob);
			
			driver.findElement(By.xpath(prop.getProperty("cdocPhone"))).sendKeys(phone);
			driver.findElement(By.xpath(prop.getProperty("cdocEmail"))).sendKeys(email);
			driver.findElement(By.xpath(prop.getProperty("cdocPass"))).sendKeys(pass);
			driver.findElement(By.xpath(prop.getProperty("cdocBmdc"))).sendKeys(bmdc);
			
			//doc type Dropdown
			WebElement dtypedd = driver.findElement(By.xpath(prop.getProperty("cdocDt")));  
			Select dtDropdown = new Select(dtypedd);  
			dtDropdown.selectByVisibleText(dType );
			
			driver.findElement(By.xpath(prop.getProperty("cdocDegree"))).sendKeys(degree);
			driver.findElement(By.xpath(prop.getProperty("cdocEdui"))).sendKeys(education);
			driver.findElement(By.xpath(prop.getProperty("cdocAddress"))).sendKeys(address);
			driver.findElement(By.xpath(prop.getProperty("cdocIw"))).sendKeys(work);

			driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/main/div/div/div")).click();
			driver.findElement(By.xpath(prop.getProperty("cdocSubmit"))).click();
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

				String snValidationMessage = driver.findElement(By.id("short_name")).getAttribute("validationMessage");
				String nSms = nvalidationMessage;
				String snSms = snValidationMessage;

				boolean isError = nSms.contains("Please fill out this field.");
				boolean isError2 = snSms.contains("Please fill out this field.");

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
					cell.setCellValue(nSms);
					// continue;
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
					cell.setCellValue(snSms);
					// continue;
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
