package com.r1v2.backend.leaddetailsreport.module;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Dynmaictest {
	public static void main(String[] args){
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver","C:\\Automation\\chromedriver.exe");
		 driver= new ChromeDriver();
		 driver.get("https://qadealeradminv2fr.izmocars.com/login.htm");
		 
		 driver.findElement(By.xpath("//input[@name='loginId']")).sendKeys("deepti");
		 driver.findElement(By.xpath("//input[@type='password']")).sendKeys("namasvi2208");
		 driver.findElement(By.xpath("//input[@name='Submit']")).click();
		 driver.findElement(By.xpath("//a[@id='dealerbutton']")).click();
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 driver.findElement(By.xpath("//input[@name='search2']")).sendKeys("Citroen");
		 int i=175;
		 
		 
		 
		 WebElement wd =driver.findElement(By.xpath("//a[contains(@onclick,'select1("+i+"')]"));
		 wd.click();
	}
	

}


//a[contains(@onclick,'select1')]

//[“+i+”]

//driver.findElement(By.xpath(“//*[starts-with(@onclick,’select1’)]”));

//a[contains(@onclick,'select1(["+i+"]')]