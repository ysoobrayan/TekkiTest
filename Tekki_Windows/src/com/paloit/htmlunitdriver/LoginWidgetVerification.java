package com.paloit.htmlunitdriver;


import java.util.regex.Pattern;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

import com.paloit.windows.chrome.WindowsChromeRunner;

public class LoginWidgetVerification {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private final static Logger LOGGER = Logger.getLogger(LoginWidgetVerification.class.getName());
  private Properties configFile;
 
  
  public void init() throws FileNotFoundException, IOException
  {
	  configFile=loadProperties("D:\\Properties\\test.prop"); //JAYESH PLEASE CHANGE THIS PATHE TP POINT TO PROP FILE
  }
  
  @Before
  public void setUp() throws Exception {
	System.out.println("Run Started! HtmlUnitDriver");
	init();
	Handler fileHandler  = new FileHandler("./src/com/paloit/htmlunitdriver/tekki_Windows_htmlunitdriver.log");
	LOGGER.addHandler(fileHandler);
	//Setting levels to handlers and LOGGER
	fileHandler.setLevel(Level.ALL);
	LOGGER.setLevel(Level.ALL);
	driver = new HtmlUnitDriver();
    baseUrl = configFile.getProperty("base_url").toString();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testHelloTekki() throws Exception {
	
	driver.get(baseUrl + "/auth/login?next=%2F");
	System.out.println("BASE URL = " + baseUrl);
    driver.findElement(By.id("username")).clear();
    //driver.findElement(By.id("username")).sendKeys("achal");
    driver.findElement(By.id("username")).sendKeys(configFile.getProperty("username"));
    driver.findElement(By.id("password")).clear();
    //driver.findElement(By.id("password")).sendKeys("achal");
    driver.findElement(By.id("password")).sendKeys(configFile.getProperty("password"));
    driver.findElement(By.name("user_login")).click();
    try {
        assertEquals(driver.findElements(By.xpath("//div[@id='container']/div/div[2]/div[2]/div[2]/div/ul/li[1]/div")).size(), 1);
      } catch (Error e) {
        verificationErrors.append(e.toString());
      }
      try {
        assertEquals(driver.findElements(By.xpath("//div[@id='container']/div/div[2]/div[2]/div[2]/div/ul/li[2]/div")).size(), 1);
      } catch (Error e) {
        verificationErrors.append(e.toString());
      }
      try {
        assertEquals(driver.findElements(By.xpath("//div[@id='container']/div/div[2]/div[2]/div[2]/div/ul/li[3]/div")).size(), 1);
      } catch (Error e) {
        verificationErrors.append(e.toString());
      }
      try {
        assertEquals(driver.findElements(By.xpath("//div[@id='container']/div/div[2]/div[2]/div[2]/div/ul/li[4]/div")).size(), 1);
      } catch (Error e) {
        verificationErrors.append(e.toString());
      }
      try {
        assertEquals(driver.findElements(By.xpath("//div[@id='container']/div/div[2]/div[2]/div[2]/div/ul/li[5]/div")).size(), 1);
      } catch (Error e) {
        verificationErrors.append(e.toString());
      }
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
      LOGGER.log(Level.INFO,"Tekki Login Feature Failed!: Test Failed");
    }
    else{
    	LOGGER.log(Level.INFO,"Tekki Login Feature is succesful!: Test Pass");
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
  
  private Properties loadProperties(String propFileName) throws FileNotFoundException, IOException
  {
	  File propFile = new File (propFileName);
	  FileInputStream inputFileStream = new FileInputStream(propFile);
	  Properties propLoad = new Properties();
	  propLoad.load(inputFileStream);
	  //inputFileStream.close();
	  return propLoad;
	  
  }
}
