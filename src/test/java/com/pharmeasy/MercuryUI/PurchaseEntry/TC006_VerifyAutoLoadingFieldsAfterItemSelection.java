package com.pharmeasy.MercuryUI.PurchaseEntry;

import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import org.testng.Assert;

import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.LandingPage;
import com.pharmeasy.MercuryUI.Page.PurchaseEntryPage;

public class TC006_VerifyAutoLoadingFieldsAfterItemSelection extends TestBase{

	public static final Logger log = Logger.getLogger(TC006_VerifyAutoLoadingFieldsAfterItemSelection.class.getSimpleName());
	
	@Test
	public void verifyUcodeLoading() throws InterruptedException {
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
		purchaseEntry.enterInvoiceNumber("SI-897");
		purchaseEntry.enterItemDetailsWithoutBatchCreation(OR.getProperty("testItem"),"5","");
		Thread.sleep(1000);
		Assert.assertEquals(String.valueOf(purchaseEntry.getItemDetailsFromPurchaseEntry().get(OR.getProperty("testItem")).get("Ucode")), OR.getProperty("testItemUcode"));
		Assert.assertEquals(String.valueOf(purchaseEntry.getItemDetailsFromPurchaseEntry().get(OR.getProperty("testItem")).get("Pack Form")), OR.getProperty("testItemPackForm"));
		Assert.assertEquals(String.valueOf(purchaseEntry.getItemDetailsFromPurchaseEntry().get(OR.getProperty("testItem")).get("Batch No")), OR.getProperty("testItemBatch"));
		Assert.assertEquals(String.valueOf(purchaseEntry.getItemDetailsFromPurchaseEntry().get(OR.getProperty("testItem")).get("MRP")), OR.getProperty("testItemMRP"));
		Assert.assertEquals(String.valueOf(purchaseEntry.getItemDetailsFromPurchaseEntry().get(OR.getProperty("testItem")).get("Exp.")), OR.getProperty("testItemExp"));
		log.info("===== Test Completed =====");
	}
	
	
}
