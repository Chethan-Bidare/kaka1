package com.pharmeasy.MercuryUI.PurchaseEntry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.LandingPage;
import com.pharmeasy.MercuryUI.Page.PurchaseEntryPage;

public class TC021_VerifyAllSearchFieldsInPendingEntries extends TestBase{

	public static final Logger log = Logger.getLogger(TC021_VerifyAllSearchFieldsInPendingEntries.class.getSimpleName());
	
	
	@Test(priority=0)
	public void verifySearchByDateFunctionality() throws InterruptedException {
		log.info("================ Test started =================");
		LandingPage landingPage = new LandingPage();
		PurchaseEntryPage purchaseEntry = new PurchaseEntryPage();
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		Thread.sleep(5000);
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Pending Entries");
		Thread.sleep(2000);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
		String fullDate = formatter.format(cal.getTime());
		String date = fullDate.substring(0, 2);
		landingPage.searchByDate(date,date);
		Thread.sleep(5000);
		
		ArrayList<String> dates = purchaseEntry.fetchDatesFromPendingEntriesPage();
		for(String orderDate : dates) {
			Assert.assertTrue(orderDate.contains(fullDate));
		}
		log.info("=========== Test Completed =======================");
	}
	
	
	@Test(priority=1)
	public void verifyVendorSearch() throws InterruptedException {
		
		log.info("=========== Test Started ===========");
		
		LandingPage landingPage = new LandingPage();
		PurchaseEntryPage purchaseEntry = new PurchaseEntryPage();
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		Thread.sleep(5000);
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Pending Entries");
		Thread.sleep(2000);
		landingPage.searchByVendorName(OR.getProperty("vendorName"));
		Thread.sleep(5000);
		
		ArrayList<Integer> orderIDS = purchaseEntry.fetchGatePassIDFromPurchasePage();
		HashMap<Integer, ArrayList<String>> orderDetails = purchaseEntry.fetchGatePassDetailsByIDFromPurchasePage();
		for(int orderNum : orderIDS) {
			Assert.assertTrue(orderDetails.get(orderNum).contains(OR.getProperty("vendorName")));
		}
		
		log.info("============ Test Completed ============");
		
	}
	
	
	
	
	@Test(priority=2)
	public void verifyInvoiceNumberSearch() throws InterruptedException {
		
		log.info("=========== Test Started ===========");
		
		LandingPage landingPage = new LandingPage();
		PurchaseEntryPage purchaseEntry = new PurchaseEntryPage();
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		Thread.sleep(5000);
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Pending Entries");
		Thread.sleep(2000);
		String val = "4334" ;
		landingPage.searchByInvoiceNumber(val);
		Thread.sleep(5000);
		
		ArrayList<String> invoiceNums = purchaseEntry.fetchInvoiceNumbersFromPendingEntriesPage();
		
		for (String invNum : invoiceNums) {
			Assert.assertTrue(invNum.equals(val));
		}
		log.info("============ Test Completed ============");
		
	}
	
	
	@Test(priority=3)
	public void verifyUcodeSearch() throws InterruptedException {
		
		log.info("=========== Test Started ===========");
		
		LandingPage landingPage = new LandingPage();
		PurchaseEntryPage purchaseEntry = new PurchaseEntryPage();
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		Thread.sleep(5000);
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Pending Entries");
		Thread.sleep(2000);
		landingPage.searchByUcode(OR.getProperty("testItemUcode"));
		Thread.sleep(5000);
		ArrayList<String> invoiceNumbers = purchaseEntry.fetchInvoiceNumbersFromPendingEntriesPage();
		for(String invoiceNum : invoiceNumbers) {
			purchaseEntry.clickOnPendingEntriesBasedOnInvoiceNumber(invoiceNum);
			Thread.sleep(2000);
			HashMap<String, HashMap<String, Object>> itemDetailsForInvoice = purchaseEntry.getItemDetailsFromPurchaseEntry();
			Set<String> itemNames = itemDetailsForInvoice.keySet();
			ArrayList<String> itemList = new ArrayList<String>();
			for(String itemName : itemNames) {
				itemList.add(String.valueOf(itemDetailsForInvoice.get(itemName).get("Ucode")));
			}
			
			Assert.assertTrue(itemList.contains(OR.getProperty("testItemUcode")));
			driver.findElement(By.xpath("//span[@class='invoicemap-header-backbutton']")).click();
			Thread.sleep(1000);
			
		}
		
		log.info("============ Test Completed ============");
		
	}
}
