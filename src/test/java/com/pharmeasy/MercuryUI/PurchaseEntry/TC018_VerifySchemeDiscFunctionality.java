package com.pharmeasy.MercuryUI.PurchaseEntry;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.Assert;
import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.LandingPage;
import com.pharmeasy.MercuryUI.Page.PurchaseEntryPage;

public class TC018_VerifySchemeDiscFunctionality extends TestBase{
	
	
	public static final Logger log = Logger.getLogger(TC018_VerifySchemeDiscFunctionality.class.getSimpleName());
	
	@Test(priority=0)
	public void verifySchemeFunctionalityWithSchemeQtyWithoutSchemeDiscPercentage() throws InterruptedException {
		
		log.info(" ====== Test Started ======");
		
		LandingPage landingPage = new LandingPage();
		PurchaseEntryPage purchaseEntry = new PurchaseEntryPage();
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		Thread.sleep(5000);
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Open Purchase Entries");
		landingPage.clickOnNewEntry();
		landingPage.selectVendor(OR.getProperty("vendorName"));
		purchaseEntry.enterInvoiceNumber(purchaseEntry.getInvoiceNum());
		purchaseEntry.enterItemDetailsWithoutBatchCreation(OR.getProperty("testItem"),"5","20");
		purchaseEntry.enterSchemeQty("2");
		
		 double expectedValue = new BigDecimal(purchaseEntry.getCalculatedValuesItemwise().get(OR.getProperty("testItem")).get("Sch Disc Amt")).setScale(2, RoundingMode.HALF_UP).doubleValue();
		 double actualValue = Double.valueOf(String.valueOf(purchaseEntry.getItemDetailsFromPurchaseEntry().get(OR.getProperty("testItem")).get("Sch Disc Amt")));
		 
		Assert.assertEquals(actualValue, expectedValue);
		
		log.info("===== Test Completed =====");
	}
	
	
	@Test(priority=1)
	public void verifySchemeFunctionalityWithSchemeDiscPercentageWithoutSchemeQty() throws InterruptedException {
		
		log.info(" ====== Test Started ======");
		
		LandingPage landingPage = new LandingPage();
		PurchaseEntryPage purchaseEntry = new PurchaseEntryPage();
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		Thread.sleep(5000);
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Open Purchase Entries");
		landingPage.clickOnNewEntry();
		landingPage.selectVendor(OR.getProperty("vendorName"));
		purchaseEntry.enterInvoiceNumber(purchaseEntry.getInvoiceNum());
		purchaseEntry.enterItemDetailsWithoutBatchCreation(OR.getProperty("testItem"),"5","20");
		purchaseEntry.enterSchemeDiscPercentage("10");
		
		 double expectedValue = new BigDecimal(purchaseEntry.getCalculatedValuesItemwise().get(OR.getProperty("testItem")).get("Sch Disc Amt")).setScale(2, RoundingMode.HALF_UP).doubleValue();
		 double actualValue = Double.valueOf(String.valueOf(purchaseEntry.getItemDetailsFromPurchaseEntry().get(OR.getProperty("testItem")).get("Sch Disc Amt")));
		 
		Assert.assertEquals(actualValue, expectedValue);
		
		log.info("===== Test Completed =====");
	}
	
	@Test(priority=2)
	public void verifySchemeFunctionalityWithSchemeQtyWithDiscWithoutSchemeDiscPercentage() throws InterruptedException {
		
		log.info(" ====== Test Started ======");
		
		LandingPage landingPage = new LandingPage();
		PurchaseEntryPage purchaseEntry = new PurchaseEntryPage();
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		Thread.sleep(5000);
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Open Purchase Entries");
		landingPage.clickOnNewEntry();
		landingPage.selectVendor(OR.getProperty("vendorName"));
		purchaseEntry.enterInvoiceNumber(purchaseEntry.getInvoiceNum());
		purchaseEntry.enterItemDetailsWithoutBatchCreation(OR.getProperty("testItem"),"5","20");
		purchaseEntry.enterSchemeQty("2");
		purchaseEntry.enterDiscPercentage("8");
		
		 double expectedValue = new BigDecimal(purchaseEntry.getCalculatedValuesItemwise().get(OR.getProperty("testItem")).get("Sch Disc Amt")).setScale(2, RoundingMode.HALF_UP).doubleValue();
		 double actualValue = Double.valueOf(String.valueOf(purchaseEntry.getItemDetailsFromPurchaseEntry().get(OR.getProperty("testItem")).get("Sch Disc Amt")));
		 
		Assert.assertEquals(actualValue, expectedValue);		
		
		log.info("===== Test Completed =====");
	}
	
	
	@Test(priority=3)
	public void verifySchemeFunctionalityWithSchemeDiscPercentageWithDiscWithoutSchemeQty() throws InterruptedException {
		
		log.info(" ====== Test Started ======");
		
		LandingPage landingPage = new LandingPage();
		PurchaseEntryPage purchaseEntry = new PurchaseEntryPage();
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		Thread.sleep(5000);
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Open Purchase Entries");
		landingPage.clickOnNewEntry();
		landingPage.selectVendor(OR.getProperty("vendorName"));
		purchaseEntry.enterInvoiceNumber(purchaseEntry.getInvoiceNum());
		purchaseEntry.enterItemDetailsWithoutBatchCreation(OR.getProperty("testItem"),"5","20");
		purchaseEntry.enterSchemeDiscPercentage("10");
		purchaseEntry.enterDiscPercentage("8");
		
		 double expectedValue = new BigDecimal(purchaseEntry.getCalculatedValuesItemwise().get(OR.getProperty("testItem")).get("Sch Disc Amt")).setScale(2, RoundingMode.HALF_UP).doubleValue();
		 double actualValue = Double.valueOf(String.valueOf(purchaseEntry.getItemDetailsFromPurchaseEntry().get(OR.getProperty("testItem")).get("Sch Disc Amt")));
		 
		Assert.assertEquals(actualValue, expectedValue);		
		
		log.info("===== Test Completed =====");
	}

}
