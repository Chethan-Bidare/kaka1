package com.pharmeasy.MercuryUI.PurchaseEntry;

import org.testng.annotations.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.log4j.Logger;
import org.testng.Assert;
import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.LandingPage;
import com.pharmeasy.MercuryUI.Page.PurchaseEntryPage;

public class TC016_VerifyXtraDiscPercentage extends TestBase{

	public static final Logger log = Logger.getLogger(TC016_VerifyXtraDiscPercentage.class.getSimpleName());
	
	@Test
	public void verifyXtraDisc() throws InterruptedException {
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
		purchaseEntry.enterDiscPercentage("5");
		double invAmt = purchaseEntry.getFooterDetails().get("Total Invoice Amt");
		
		purchaseEntry.enterXtraDisc("10");
		Thread.sleep(1000);
		double invAmtAftXtraDisc = purchaseEntry.getFooterDetails().get("Total Invoice Amt");
		double xtraDiscAmt = new BigDecimal(invAmt-invAmtAftXtraDisc).setScale(2, RoundingMode.HALF_UP).doubleValue();
		
		double calculatedXtraDiscAmt =invAmt * (purchaseEntry.getFooterDetails().get("Xtra Dis%") / 100.0 );
		Assert.assertEquals(xtraDiscAmt,new BigDecimal(calculatedXtraDiscAmt).setScale(2, RoundingMode.HALF_UP).doubleValue());
		log.info("===== Test Completed =====");
		
		
	}
}
