package com.pharmeasy.MercuryUI.PurchaseEntry;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.Assert;
import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.LandingPage;
import com.pharmeasy.MercuryUI.Page.PurchaseEntryPage;

public class TC022_VerifyCreditAmountFunctionality extends TestBase{

	
	public static final Logger log = Logger.getLogger(TC022_VerifyCreditAmountFunctionality.class.getSimpleName());
	
	/*
	 * Verifies Total amount by giving credit amount by enabling the slider 
	 * Verifies Total amount by giving credit amount by Disabling the slider
	 */
	
	@Test(priority=0)
	public void verifyCreditAmountByEnablingSlider() throws InterruptedException {
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
		purchaseEntry.ClickOnaddRowButtonToAddItem();
		Thread.sleep(1000);
		purchaseEntry.enterItemDetailsWithoutBatchCreation(OR.getProperty("testItem3"),"5","70");
		purchaseEntry.enterSchemeQty("2");
		purchaseEntry.enterDiscPercentage("7");
		Thread.sleep(1000);
		Double totalAmountBeforeCreditAmountEnabling = purchaseEntry.getFooterDetails().get("Total Invoice Amt");
		purchaseEntry.enableCreditAmountSlider();
		purchaseEntry.enterCreditAmount("10");
		Thread.sleep(1000);
		Double totalAmountAfterCreditAmountEnabling = purchaseEntry.getFooterDetails().get("Total Invoice Amt");
		
		Assert.assertEquals(new BigDecimal(totalAmountAfterCreditAmountEnabling + 10.0).setScale(2, RoundingMode.HALF_UP).doubleValue(), totalAmountBeforeCreditAmountEnabling );

		log.info(" ====== Test Completed ======");
		
	}
	
	
	@Test(priority=1)
	public void verifyCreditAmountByWithoutEnablingSlider() throws InterruptedException {
		
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
		purchaseEntry.ClickOnaddRowButtonToAddItem();
		Thread.sleep(1000);
		purchaseEntry.enterItemDetailsWithoutBatchCreation(OR.getProperty("testItem3"),"5","70");
		purchaseEntry.enterSchemeQty("2");
		purchaseEntry.enterDiscPercentage("7");
		Thread.sleep(1000);
		Double totalAmountBeforeCreditAmountEnabling = purchaseEntry.getFooterDetails().get("Total Invoice Amt");
		//purchaseEntry.enableCreditAmountSlider();
		purchaseEntry.enterCreditAmount("10");
		Thread.sleep(2000);
		Double totalAmountAfterCreditAmountEnabling = purchaseEntry.getFooterDetails().get("Total Invoice Amt");
		
		Assert.assertEquals(totalAmountAfterCreditAmountEnabling, totalAmountBeforeCreditAmountEnabling );
		
		log.info(" ====== Test Completed ======");

		
	}
}
