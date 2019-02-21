package com.pharmeasy.MercuryUI.Page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.pharmeasy.MercuryUI.Base.TestBase;

public class GatepassPage extends TestBase  {
	
	
	public static final Logger log = Logger.getLogger(GatepassPage.class.getSimpleName());
	
	
	@FindBy(xpath="//*[@placeholder='Enter Invoice No.']")
	WebElement InvoiceNum ;
	
	@FindBy(xpath="//*/div/div[1]/div[7]/div/label/span[@class='slider round']")
	WebElement POAvailableCheckBox ;
	
	
	@FindBy(xpath="//button[@class='buttonthemed']")
	WebElement CreateGatePassButton	 ;
	
	@FindBy(xpath="//*/div[3]/div/input")
	WebElement InvoiceAmount ;
	
	@FindBy(xpath="//*[@id='toasty']")
	WebElement SuccessMSG ;
	
	@FindBy(xpath="//header[@class='vendor-header']")
	WebElement VendorHeader ;
	
	@FindBy(xpath="//header[@class='gatepassentry-header']")
	WebElement GatePassEntryHeader ;
	
	@FindBy(xpath="//*[@id=\"thatForm\"]/div[1]/app-entryform/div/div[1]/div[1]/div/input")
	WebElement InvoiceNumError ;
	
	@FindBy(xpath="//*[@placeholder='Search Invoice No']")
	WebElement InvoiceSearch ;
	
	@FindBy(xpath="//button[@class='buttonthemed']")
	WebElement UpdateGatePassButton	 ;
	
	
	public GatepassPage() {
		PageFactory.initElements(driver, this);
	}
	
	

	
	public void enterGatePassEntryDetails(String invNo,String invAmount) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(InvoiceNum));
			InvoiceNum.clear();
			InvoiceNum.sendKeys(invNo);
			log.info("Entered invoice Number");
			InvoiceAmount.clear();
			InvoiceAmount.sendKeys(invAmount);
			log.info("Entered invoice amount");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("Failed to enter gate pass entry details");
		}
		
	}
	
	public void enablePOavailableslider() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(POAvailableCheckBox));
			POAvailableCheckBox.click();
			log.info("Enabled PO Available Slider");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("Failed to enable PO Available Slider");
		}
	}
	
	
	public void clickOnCreateGatePassButton() {
		try {
			CreateGatePassButton.click();
			log.info("Clicked on Create gate pass button");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("Failed to click on Create Gate Pass button");
		}
	}
	
	public void clickOnUpdateGatePassButton() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(UpdateGatePassButton));
			UpdateGatePassButton.click();
			log.info("Clicked on Update gate pass button");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("Failed to click on Update Gate Pass button");
		}
	}
	
	
	public void disablePOavailableslider() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(POAvailableCheckBox));
			POAvailableCheckBox.click();
			log.info("Disabled PO Available Slider");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("Failed to Disable PO Available Slider");
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
	
	public String getTextFromvendorSelectionPage() throws InterruptedException {
		//wait.until(ExpectedConditions.textToBePresentInElement(VendorHeader, APP.getProperty("GatePassNewEntry")));
		Thread.sleep(2000);
		return VendorHeader.getText();
	}
	
	public String getTextFromGatePassEntryDetails() {
		return GatePassEntryHeader.getText();
	}
	
	public ArrayList<Integer> fetchGatePassID() {
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//*[@class='gatepass-rTable']/div"))));
		int totalRows = driver.findElements(By.xpath("//*[@class='gatepass-rTable']/div")).size();
		ArrayList<Integer> gatePassIDs = new ArrayList<Integer>();
		for(int i=2; i<=totalRows; i++) {
			String orderID = driver.findElement(By.xpath("//*[@class='gatepass-rTable']/div["+i+"]/div[1]")).getText();
			orderID = orderID.trim();
			gatePassIDs.add(Integer.parseInt(orderID));
		}
		Collections.sort(gatePassIDs);
		log.info("Fetching all the gate Pass order IDs from the Gate pass page");
		return gatePassIDs ;
	}
	
	public HashMap<Integer,ArrayList<String>> fetchGatePassDetailsByID() {
		HashMap<Integer, ArrayList<String>> gatePassDetails = new HashMap<Integer, ArrayList<String>>();
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//*[@class='gatepass-rTable']/div"))));
		int totalRows = driver.findElements(By.xpath("//*[@class='gatepass-rTable']/div")).size();
		int totalCols = driver.findElements(By.xpath("//*[@class='gatepass-rTable']/div[1]/div")).size();
		int key = 0 ;
		ArrayList<String> values = new ArrayList<String>();
		for(int i=2; i<=totalRows; i++) {
			values = new ArrayList<String>();
			for(int j=1; j<=totalCols; j++) {
			
				if(j==1) {
					String orderID= driver.findElement(By.xpath("//*[@class='gatepass-rTable']/div["+i+"]/div["+j+"]")).getText();
					orderID = orderID.trim();
					key = Integer.parseInt(orderID);	
				}
				else {
					values.add(driver.findElement(By.xpath("//*[@class='gatepass-rTable']/div["+i+"]/div["+j+"]")).getText());
				}
			}
			gatePassDetails.put(key, values);
		}
	
		log.info("Fetching all the gate Pass order details from the Order ID in the  Gate pass page");
		return gatePassDetails ;
		
	}
	
	
	
	
	public String getBorderColorForInvoiceAmount() {
		String invAmountColor = InvoiceAmount.getCssValue("border-color");
		return invAmountColor ;
	}
	
	public String getBorderColorForInvoiceNumber() {
		String invNumColor = InvoiceNumError.getCssValue("border-color");
		return invNumColor ;
	}
	
	public void searchByInvoiceNumberInGatepassPage(String invoiceNumber) throws InterruptedException {
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
	
	public ArrayList<String> fetchGatePassDate() {
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//*[@class='gatepass-rTable']/div"))));
		int totalRows = driver.findElements(By.xpath("//*[@class='gatepass-rTable']/div")).size();
		ArrayList<String> gatePassIDs = new ArrayList<String>();
		for(int i=2; i<=totalRows; i++) {
			
			try {
				String orderID = driver.findElement(By.xpath("//*[@class='gatepass-rTable']/div["+i+"]/div[7]")).getText();
				orderID = orderID.substring(0,8).trim();
				gatePassIDs.add(orderID);
			} catch (Exception e) {
				log.info("NO order IDs Exist for the date");
			}
		}
		//Collections.sort(gatePassIDs);
		log.info("Fetching all the gate Pass order ID Dates from the Gate pass page");
		return gatePassIDs ;
	}
	
	
	public void clickOnGatePassByOrderID(int orderID) {
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//*[@class='gatepass-rTable']/div"))));
		int totalRows = driver.findElements(By.xpath("//*[@class='gatepass-rTable']/div")).size();
		
		for(int i=2; i<=totalRows; i++) {
			String getOrderID = driver.findElement(By.xpath("//*[@class='gatepass-rTable']/div["+i+"]/div[1]")).getText();
			getOrderID = getOrderID.trim();
			int gatepassID = Integer.parseInt(getOrderID);
			if(gatepassID==orderID) {
				driver.findElement(By.xpath("//*[@class='gatepass-rTable']/div["+i+"]/div[1]")).click();
				log.info("Clicked on the specified Gatepass ID "+orderID);
				break ;
			}
		}
	}
	 
}
