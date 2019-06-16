package com.pkg.runner;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pkg.utility.ExcelReader;
import com.pkg.utility.FileWritter;

public class GetMerit {
	
	WebDriver driver;
	ArrayList<Integer> userid=new ArrayList<Integer>();
	static ArrayList<ArrayList<String>> alldataset=new ArrayList<ArrayList<String>>();
	FileWriter writer;	
	
	
	@BeforeTest
	public void beforeTest() throws IOException{
		ExcelReader ex=new ExcelReader();
		userid=ex.excelread();
		writer =FileWritter.writeTextfile();
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\resources\\BrowserDrivers\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.jacpcldce.ac.in/Search/BE_Query_18.asp");
		
	}
	
	@DataProvider (name="dp")
	public Object[] getUserId(){
		 Object[] id=userid.toArray();
		return id;
	}
	
	@Test (dataProvider="dp")
	public void getSearchResult(int id) throws InterruptedException, IOException{
		ArrayList<String> getresult=new ArrayList<String>(); 
		WebElement userid=driver.findElement(By.xpath("//input[@name='txtGcetNo']"));
		String rollNo=String.valueOf(id);
		userid.sendKeys(rollNo);
		WebElement captcha=driver.findElement(By.id("CaptchaDiv"));
		String readcaptcha=captcha.getText();
		WebElement captchainput=driver.findElement(By.id("CaptchaInput"));
		captchainput.sendKeys(readcaptcha);
		WebElement btn_submit=driver.findElement(By.xpath("//input[@name='submit']"));
		btn_submit.click();
		String title=driver.getTitle();
		Assert.assertEquals(title, "ACPC");
		WebElement table=driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/table[3]"));
		List<WebElement> dataset=driver.findElements(By.xpath("/html/body/table/tbody/tr[2]/td/table[3]/tbody/tr/td/table/tbody/tr/td[2]"));
		getresult.clear();
		for (int i=0;i<dataset.size();i++){
			getresult.add(dataset.get(i).getText());
			writer.append(dataset.get(i).getText());
			
		}	
		writer.append("\r\n");
		
		alldataset.add(getresult);	
		System.out.println(alldataset);	
		
		
		WebElement btn_newsearch=driver.findElement(By.xpath("//input[@type='button']"));
		btn_newsearch.click();
		Thread.sleep(2000);	
		
	}
	
	@AfterTest
	public void afterTest() throws IOException, InvalidFormatException{
		driver.close();
		writer.close();
		FileWritter.filewritter(alldataset);
	}	
	}
