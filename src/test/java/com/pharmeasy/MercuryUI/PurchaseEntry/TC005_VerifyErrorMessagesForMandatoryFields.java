package com.pharmeasy.MercuryUI.PurchaseEntry;

import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import org.testng.Assert;

import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.GatepassPage;
import com.pharmeasy.MercuryUI.Page.LandingPage;
import com.pharmeasy.MercuryUI.Page.PurchaseEntryPage;

public class TC005_VerifyErrorMessagesForMandatoryFields extends TestBase{

	public static final Logger log = Logger.getLogger(TC005_VerifyErrorMessagesForMandatoryFields.class.getSimpleName());
	
	
	@Test(priority=0)
	public void verifyInvoiceNumberField() throws InterruptedException {
		
		log.info("======= Test Started =========");
		LandingPage landingPage = new LandingPage();
		PurchaseEntryPage purchaseEntry = new PurchaseEntryPage();
		GatepassPage gatePassPage = new GatepassPage();
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		Thread.sleep(5000);
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Open Purchase Entries");
		landingPage.clickOnNewEntry();
		landingPage.selectVendor(OR.getProperty("vendorName"));
		Thread.sleep(1000);
		purchaseEntry.clickOnReviewButton();
		Assert.assertEquals(gatePassPage.getToastMSG(),APP.getProperty("InvoiceNumErrorMSG"));
		log.info("======= Test Completed =========");
	}
	
	@Test(priority=1)
	public void verifyItemField() throws InterruptedException {
		
		log.info("======= Test Started =========");
		LandingPage landingPage = new LandingPage();
		PurchaseEntryPage purchaseEntry = new PurchaseEntryPage();
		GatepassPage gatePassPage = new GatepassPage();
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		Thread.sleep(5000);
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Open Purchase Entries");
		landingPage.clickOnNewEntry();
		landingPage.selectVendor(OR.getProperty("vendorName"));
		purchaseEntry.enterInvoiceNumber("SI-897");
		purchaseEntry.clickOnReviewButton();
		Thread.sleep(1000);
		System.out.println(gatePassPage.getToastMSG());
		Assert.assertEquals(gatePassPage.getToastMSG(),APP.getProperty("ItemFieldErrorMSG"));
		log.info("======= Test Completed =========");
	}
	
	
	@Test(priority=2)
	public void verifyBatchField() throws InterruptedException {
		
		log.info("======= Test Started =========");
		LandingPage landingPage = new LandingPage();
		PurchaseEntryPage purchaseEntry = new PurchaseEntryPage();
		GatepassPage gatePassPage = new GatepassPage();
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		Thread.sleep(5000);
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Open Purchase Entries");
		landingPage.clickOnNewEntry();
		landingPage.selectVendor(OR.getProperty("vendorName"));
		purchaseEntry.enterInvoiceNumber("SI-897");
		purchaseEntry.enterItemDetailsWithoutBatchCreation("DOLO (MICRO LABS) 120 MG SYRUP","","");
		Thread.sleep(1000);
		purchaseEntry.clickOnReviewButton();
		Assert.assertEquals(gatePassPage.getToastMSG(),APP.getProperty("BatchFieldErrorMSG"));
		log.info("======= Test Completed =========");
	}
	
	@Test(priority=3)
	public void verifyExpiryField() throws InterruptedException {
		
		log.info("======= Test Started =========");
		LandingPage landingPage = new LandingPage();
		PurchaseEntryPage purchaseEntry = new PurchaseEntryPage();
		GatepassPage gatePassPage = new GatepassPage();
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		Thread.sleep(5000);
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Open Purchase Entries");
		landingPage.clickOnNewEntry();
		landingPage.selectVendor(OR.getProperty("vendorName"));
		purchaseEntry.enterInvoiceNumber("SI-897");
		purchaseEntry.enterItemDetailsWithoutBatchCreation("DOLO (MICRO LABS) 120 MG SYRUP","","0");
		purchaseEntry.createBatch("1234", "", "","");
		purchaseEntry.clickOnReviewButton();
		Thread.sleep(1000);
		Assert.assertEquals(gatePassPage.getToastMSG(),APP.getProperty("ExpiryFieldErrorMSG"));
		log.info("======= Test Completed =========");
	}
	
	
	@Test(priority=4)
	public void verifyQtyField() throws InterruptedException {
		
		log.info("======= Test Started =========");
		LandingPage landingPage = new LandingPage();
		PurchaseEntryPage purchaseEntry = new PurchaseEntryPage();
		GatepassPage gatePassPage = new GatepassPage();
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		Thread.sleep(5000);
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Open Purchase Entries");
		landingPage.clickOnNewEntry();
		landingPage.selectVendor(OR.getProperty("vendorName"));
		purchaseEntry.enterInvoiceNumber("SI-897");
		purchaseEntry.enterItemDetailsWithoutBatchCreation("DOLO (MICRO LABS) 120 MG SYRUP","","");
		purchaseEntry.createBatch("1234", "", "12","22");
		Thread.sleep(10000);
		purchaseEntry.clickOnReviewButton();
		Thread.sleep(1000);
		Assert.assertEquals(gatePassPage.getToastMSG(),APP.getProperty("QtyFieldErrorMSG"));
		log.info("======= Test Completed =========");
	}
	
	@Test(priority=5)
	public void verifyMRPField() throws InterruptedException {
		
		log.info("======= Test Started =========");
		LandingPage landingPage = new LandingPage();
		PurchaseEntryPage purchaseEntry = new PurchaseEntryPage();
		GatepassPage gatePassPage = new GatepassPage();
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		Thread.sleep(5000);
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Open Purchase Entries");
		landingPage.clickOnNewEntry();
		landingPage.selectVendor(OR.getProperty("vendorName"));
		purchaseEntry.enterInvoiceNumber("SI-897");
		purchaseEntry.enterItemDetailsWithoutBatchCreation("DOLO (MICRO LABS) 120 MG SYRUP","5","");
		purchaseEntry.createBatch("1234", "", "12","22");
		purchaseEntry.clickOnReviewButton();
		Thread.sleep(1000);
		Assert.assertEquals(gatePassPage.getToastMSG(),APP.getProperty("MRPFieldErrorMSG"));
		log.info("======= Test Completed =========");
	}
}
