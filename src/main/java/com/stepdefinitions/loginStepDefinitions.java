package com.stepdefinitions;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class loginStepDefinitions {
	WebDriver driver;
	WebDriverWait wait;
	Actions action;
	
	@Before
	public void user_opens_login_page_of_GeoData()  {
		
		driver=new ChromeDriver();
	    driver.navigate().to("https://899211-Rajinder.Kuma:EDyzuDG3B5GheCUm@core.geodataplus.com");
	    driver.manage().window().maximize();
	     wait=new WebDriverWait(driver,20);
	    String title1=driver.getTitle();
	    System.out.println(title1);
	    boolean verifyTitle1=wait.until(ExpectedConditions.titleContains("Nationwide Property Data, Reports, Sales Comps"));
	    Assert.assertTrue("Title of Url Page cannot be verified",verifyTitle1);
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[@id='loginRegisterError'])[2]"))).click();
	 
	    	}
	

@When("^user enters username and password and submits$")
public void user_enters_username_and_password_and_submits(DataTable credentials) throws Throwable  {
	List<Map<String,String>> dataList = credentials.asMaps();
	for(Map<String,String> row:dataList){
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='LoginModal_Email']"))).sendKeys( row.get("username"));
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='LoginModal_Password']"))).sendKeys( row.get("password"));
	}

     
     wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='LoginModal_Submit']"))).click();
     Thread.sleep(3000);
}


@Then("^user checks for kickout message$")
public void user_checks_for_kickout_message() throws Throwable {
	action=new Actions(driver);
	String actualtitle=driver.getTitle();
	System.out.println(actualtitle);
	String expectedTitle="GeoData Plus - Search Page";
	if(actualtitle.equalsIgnoreCase(expectedTitle)) {
		Select c1=new Select(driver.findElement(By.id("ddlCountiesByState")));
		c1.selectByVisibleText("Nassau ");
		Thread.sleep(3000);
		action.sendKeys(Keys.ENTER).build().perform();
	}
	else {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[id='btnLogin']"))).click();
		wait.until(ExpectedConditions.titleIs("GeoData Plus - Search Page"));
		Select c=new Select(driver.findElement(By.id("ddlCountiesByState")));
		c.selectByVisibleText("Nassau ");
		Thread.sleep(2000);
		action.sendKeys(Keys.ENTER).build().perform();
	}
}


@Then("^user verifies title of main page$")
public void user_verifies_title_of_main_page() {
String actualTitle=driver.getTitle();
String expectedTitle="GeoData Plus - Search Page";
Assert.assertEquals(expectedTitle,actualTitle);
}

@When("^user clicks Distressed Properties search tab$")
public void user_clicks_Distressed_Properties_search_tab()  {
	
	WebElement s=driver.findElement(By.xpath("(//a[@data-toggle='tab'][text()='Distressed Property Search'])[3]"));
	action.moveToElement(s).build().perform();
	s.click();
}
@Then("^user clicks on search button$")
public void user_clicks_on_search_button()  {
WebElement t=	wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[id='btnNassauLPSearch'][value='Search']")));
JavascriptExecutor executor = (JavascriptExecutor)driver;
executor.executeScript("arguments[0].click();", t);


//action.moveToElement(t).click(t).build().perform();
WebElement e=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='ddlNassauDocDtPreset']")));
Select select=new Select(e);
select.selectByIndex(9);

executor.executeScript("arguments[0].click();", t);
}

@When("^user clicks Map search tab$")
public void user_clicks_Map_search_tab() throws Throwable {
	WebElement t1=	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@data-toggle='tab'][text()='Map ']")));
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", t1);
}

@Then("^user clicks on search button at map search$")
public void user_clicks_on_search_button_at_map_search() throws Throwable {
	WebElement t2=	wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='ow_wrap'][id='dvmapsearchzoning']")));
//action.moveToElement(t2);
JavascriptExecutor executor = (JavascriptExecutor)driver;
executor.executeScript("arguments[0].click();", t2);
Thread.sleep(4000);
}

 @After
 public void captureScreenshot() throws Throwable {
	 String screenshotPath=System.getProperty("user.dir")+"/screenshots/screenshot_"+System.currentTimeMillis()+".png";
	 File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	 System.out.println("Failed and Screen capture saved at location"+screenshotPath );
	 try {
		Files.copy(srcFile, new File(screenshotPath));
	 }
	 catch(IOException e) {
		 System.out.println(e.getMessage());
	 }
 }




}



//public void teardown(Scenario scenario) {
//	if(scenario.isFailed()) {
//		final byte[] screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
//		scenario.embed(screenshot, "img/png");
//	}
	//driver.close();



