package com.pharmeasy.MercuryUI.Page;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.pharmeasy.MercuryUI.Base.TestBase;

public class LandingPage extends TestBase{

public static final Logger log = Logger.getLogger(LandingPage.class.getSimpleName());
	
	@FindBy(id="customBtn")
	WebElement loginButton ;
	
	@FindBy(id="identifierId")
	WebElement UserName ;
	
	@FindBy(xpath="//*[@class='RveJvd snByac']")
	WebElement UserNameNextButton ;
	
	@FindBy(xpath="//*[@type='password']")
	WebElement Password ;
	
	@FindBy(xpath="//span[contains(text(),'Next')]")
	WebElement  PasswordNextButton;
	
	@FindBy(xpath="//button[@class='buttongreen newentry']")
	WebElement NewEntryButton ;
	
	@FindBy(id="search")
	WebElement VendorSearch ;
	
	@FindBy(xpath="//*[@placeholder='Search By Invoice No']")
	WebElement InvoiceSearch ;
	
	@FindBy(xpath="//*[@placeholder='Search By Ucode']")
	WebElement UcodeSearch ;
	
	@FindBy(xpath="//*[@placeholder='Search By Date']")
	WebElement DateSearch ;
	
	@FindBy(xpath="//*[@placeholder='Search Vendor By Name' and @class='validate filter-input ng-untouched ng-pristine ng-valid']")
	WebElement searchVendor ;
	
	@FindBy(xpath="//button[@class='buttonthemed']")
	WebElement ContinueButton ;
	
	@FindBy(xpath="//*[@id='toasty']")
	WebElement SuccessMSG ;
	
	
	@FindBy(xpath="//a[@class='restpagination-button' and contains(text(),'NEXT')]")
	WebElement PaginationNextButton ;
	
	@FindBy(xpath="//a[@class='restpagination-button' and contains(text(),'PREV')]")
	WebElement PaginationPREVButton ;
	
	/*
	@FindBy()
	WebElement abc ;
	
	@FindBy()
	WebElement abc ;*/
	
	public LandingPage(){
		PageFactory.initElements(driver, this);
	}
	
	
	
	//Function to select Main menu options
	//Added By Chethan Bidare on 21-01-19
	
	public void selectMainMenuOption(String menuName) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@class='mainnav-circularnav-text' and contains(text(),'"+menuName+"')]"))));
			driver.findElement(By.xpath("//*[@class='mainnav-circularnav-text' and contains(text(),'"+menuName+"')]")).click();
			log.info("Selected "+menuName+" from the Menu options");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("Failed to Select "+menuName+" from the Menu options");
		}
	}
	
	
	public void loginByCredentials(String userName,String pwd) throws InterruptedException {
		try {
			String parentWindow = driver.getWindowHandle().toString();
			loginButton.click();
			Set<String> windows = driver.getWindowHandles();
			for(String windowName : windows) {
				if(!windowName.equalsIgnoreCase(parentWindow)) {
					driver.switchTo().window(windowName);
					Thread.sleep(2000);
					wait.until(ExpectedConditions.elementToBeClickable(UserName));
					UserName.sendKeys(userName);
					log.info("Entered user name ");
					wait.until(ExpectedConditions.elementToBeClickable(UserNameNextButton));
					UserNameNextButton.click();
					log.info("Clicked on Next button ");
					Thread.sleep(5000);
					wait.until(ExpectedConditions.elementToBeClickable(PasswordNextButton));
					Password.sendKeys(pwd);
					log.info("Entered Password");
					PasswordNextButton.click();
					log.info("Clicked on Next button ");
					Thread.sleep(5000);
					driver.switchTo().window(parentWindow);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("Failed to Login");
		}
		//driver.switchTo().w
	}
	
	
	//Function to select Sub menu options
	//created by Chethan Bidare on 22-01-19

	public void selectSubMainMenuoption(String subMenuName) {
		try {
			//wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'"+subMenuName+"')]"))));
			driver.findElement(By.xpath("//a[contains(text(),'"+subMenuName+"')]")).click();
			log.info("Selected "+subMenuName+" from the main menu ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("Failed to select the "+subMenuName+" from the the main menu");
		}
	}
	
	
	public void clickOnNewEntry() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(NewEntryButton));
			Thread.sleep(2000);
			NewEntryButton.click();
			log.info("Clicked on New Entry button ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("Click action failed on New Entry button ");
		}
	}
	
	
	
	//This method is used to select the vendor from Vendor selection page
	//Created by Chethan Bidare on 24-01-19
	
	public void selectVendor(String vendorName) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(searchVendor));
			searchVendor.click();
			log.info("Clicked on Search Vendor ");
			searchVendor.sendKeys(vendorName);
			log.info("Entered Vendor Name ");
			SelectFromAutoSuggestionSearch(vendorName);
			ContinueButton.click();
			log.info("Clicked on Continue button after selecting the VENDOR");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("Selection of Vendor failed");
		}
	}
	
	public void clickOnContinueButton() {
		wait.until(ExpectedConditions.elementToBeClickable(ContinueButton));
		ContinueButton.click();
	}
	
		//This method will search the Vendor name from the Search bar
		//created by Chethan Bidare on 22-01-19
		
		public void searchByVendorName(String vendorName) throws InterruptedException {
			try {
				wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.id("search"))));
				VendorSearch.click();
				VendorSearch.sendKeys(vendorName);
				SelectFromAutoSuggestionSearch(vendorName);
				log.info("Entered Vendor Name from the Vendor Search");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.info("Failed to search the vendor");
			}
		}
		
		
		//This method will search the Invoice Number from the Search bar
		//created by Chethan Bidare on 22-01-19
		
		public void searchByInvoiceNumber(String invoiceNumber) throws InterruptedException {
			try {
				wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.id("search"))));
				InvoiceSearch.click();
				InvoiceSearch.sendKeys(invoiceNumber);
				InvoiceSearch.sendKeys(Keys.ENTER);
				log.info("Entered Invoice Number in invoice search window");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.info("failed to Enter Invoice Number in invoice search window");
				
			}
		}
		
		//This method will search the Ucode from the Search bar
		//created by Chethan Bidare on 22-01-19
		
		public void searchByUcode(String Ucode) throws InterruptedException {
			try {
				wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.id("search"))));
				UcodeSearch.click();
				UcodeSearch.sendKeys(Ucode);
				UcodeSearch.sendKeys(Keys.ENTER);
				log.info("Entered Ucode in Ucode search window");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.info("Failed to enter Ucode in Ucode search window");
			}
		}
		
		//This method will search the Ucode from the Search bar
				//created by Chethan Bidare on 23-01-19
		
		public void searchByDate(String fromDate,String toDate) throws InterruptedException {
			wait.until(ExpectedConditions.elementToBeClickable(DateSearch));
			DateSearch.click();
			Thread.sleep(3000);
			List<WebElement> dateList = driver.findElements(By.xpath("//*[@class='calendar-main-dates']/ul/li"));
			for(WebElement we : dateList) {
				String temp = fromDate ;
				int date = Integer.parseInt(temp);
				fromDate = String.valueOf(date);
				if(we.getText().equalsIgnoreCase(fromDate)) {
					we.click();
					log.info("Clicked on From date :"+fromDate);
					break ;
				}
			}
			for(WebElement we : dateList) {
				String temp = toDate ;
				int date = Integer.parseInt(temp);
				toDate = String.valueOf(date);
				if(we.getText().equalsIgnoreCase(toDate)) {
					we.click();
					log.info("Clicked on To Date "+toDate);
					break ;
				}			
			}
		}
		
		public String getToastMSG() {
			try {
				log.info("Gate Pass Entry Creation success message");
				return SuccessMSG.getText();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.info("Unable to Capture the Success message");
				return "";
			}
		}

		public void clickOnPaginationNEXTbutton() {
			wait.until(ExpectedConditions.elementToBeClickable(PaginationNextButton));
			PaginationNextButton.click();
		}
		
		public void clickOnPaginationPREVbutton() {
			wait.until(ExpectedConditions.elementToBeClickable(PaginationPREVButton));
			PaginationPREVButton.click();
		}
	
}
