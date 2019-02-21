package com.pharmeasy.MercuryUI.PurchaseEntry;

import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import org.testng.Assert;
import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.LandingPage;
import com.pharmeasy.MercuryUI.Page.PurchaseEntryPage;

public class TC011_VerifyTotalInvoiceAmountForMultipleItems extends TestBase{

	public static final Logger log = Logger.getLogger(TC011_VerifyTotalInvoiceAmountForMultipleItems.class.getSimpleName());
	
	@Test
	public void verifyTotalInvoiceAmt() throws InterruptedException {
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
		purchaseEntry.ClickOnaddRowButtonToAddItem();
		Thread.sleep(1000);
		purchaseEntry.enterItemDetailsWithoutBatchCreation(OR.getProperty("testItem2"),"5","10");
		purchaseEntry.enterSchemeQty("2");
		purchaseEntry.enterDiscPercentage("7");
		purchaseEntry.ClickOnaddRowButtonToAddItem();
		Thread.sleep(1000);
		purchaseEntry.enterItemDetailsWithoutBatchCreation(OR.getProperty("testItem3"),"5","70");
		purchaseEntry.enterSchemeQty("2");
		purchaseEntry.enterDiscPercentage("6");
		Thread.sleep(1000);
		
		
		double calculatedTotalInvAmt = purchaseEntry.getCalculatedFooterDetailsFromItemTable().get("Total Invoice Amt");
		
		Assert.assertEquals(purchaseEntry.getFooterDetails().get("Total Invoice Amt"), calculatedTotalInvAmt);
		
		log.info("===== Test Completed ===== ");
		
	}
}
