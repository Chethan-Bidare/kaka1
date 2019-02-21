package com.pharmeasy.MercuryUI.PurchaseEntry;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.GatepassPage;
import com.pharmeasy.MercuryUI.Page.LandingPage;
import com.pharmeasy.MercuryUI.Page.PurchaseEntryPage;

public class TC028_CreatePurchase extends TestBase{

	public static final Logger log = Logger.getLogger(TC028_CreatePurchase.class.getSimpleName());
	
	
	@Test(priority=0)
	public void createPurchaseFromNewEntry() throws InterruptedException {
		
		log.info(" ====== Test Started ======");
		
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
		String invNum = purchaseEntry.getInvoiceNum();
		purchaseEntry.enterInvoiceNumber(invNum);
		purchaseEntry.enterItemDetailsWithoutBatchCreation(OR.getProperty("testItem"),"5","20");
		purchaseEntry.clickOnReviewButton();
		Thread.sleep(2000);
		purchaseEntry.clickOnCreateButton();
		Thread.sleep(2000);
		
		Assert.assertEquals(gatePassPage.getToastMSG(), APP.getProperty("PurEntrySuccessMSG"));
		
		log.info(" ====== Test Completed ======");
	}
	
	@Test(priority=1)
	public void createPurchaseForGatePass() throws InterruptedException {
		
		log.info(" ====== Test Started ======");
		
		LandingPage landingPage = new LandingPage();
		GatepassPage gatePassPage = new GatepassPage();
		PurchaseEntryPage purchaseEntry = new PurchaseEntryPage();
		
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		landingPage.clickOnNewEntry();
		landingPage.selectVendor(OR.getProperty("vendorName"));
		String invNum = purchaseEntry.getInvoiceNum();
		gatePassPage.enterGatePassEntryDetails(invNum,"100");
		gatePassPage.disablePOavailableslider();
		gatePassPage.clickOnCreateGatePassButton();
		
		Thread.sleep(2000);
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Open Purchase Entries");
		Thread.sleep(1000);
		purchaseEntry.clickOnGatePassByInvoiceNumberFromPurchasePage(invNum);
		Thread.sleep(1000);
		purchaseEntry.clickOnManualEntryButton();
		Thread.sleep(1000);
		purchaseEntry.enterItemDetailsWithoutBatchCreation(OR.getProperty("testItem"),"5","20");
		purchaseEntry.clickOnReviewButton();
		Thread.sleep(2000);
		purchaseEntry.clickOnCreateButton();
		Thread.sleep(2000);
		
		Assert.assertEquals(gatePassPage.getToastMSG(), APP.getProperty("PurEntrySuccessMSG"));
		
		log.info(" ====== Test Completed ======");
	}
}
