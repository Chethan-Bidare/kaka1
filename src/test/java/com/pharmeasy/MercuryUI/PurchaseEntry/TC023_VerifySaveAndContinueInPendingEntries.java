package com.pharmeasy.MercuryUI.PurchaseEntry;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.LandingPage;
import com.pharmeasy.MercuryUI.Page.PurchaseEntryPage;

public class TC023_VerifySaveAndContinueInPendingEntries extends TestBase{

	public static final Logger log = Logger.getLogger(TC023_VerifySaveAndContinueInPendingEntries.class.getSimpleName());
	
	
	@Test(priority=0)
	public void verifySaveAndFunctionalityByDeletingAnItem() throws InterruptedException {
		
		log.info("=========== Test Started ===========");
		
		LandingPage landingPage = new LandingPage();
		PurchaseEntryPage purchaseEntry = new PurchaseEntryPage();
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		Thread.sleep(5000);
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Open Purchase Entries");
		landingPage.clickOnNewEntry();
		landingPage.selectVendor(OR.getProperty("vendorName"));
		String invNumber = purchaseEntry.getInvoiceNum();
		purchaseEntry.enterInvoiceNumber(invNumber);
		purchaseEntry.enterItemDetailsWithoutBatchCreation(OR.getProperty("testItem"),"5","20");
		purchaseEntry.ClickOnaddRowButtonToAddItem();
		Thread.sleep(1000);
		purchaseEntry.enterItemDetailsWithoutBatchCreation(OR.getProperty("testItem2"),"5","10");
		purchaseEntry.clickOnSaveAndContinueButton();
		Thread.sleep(2000);
		
		
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Pending Entries");
		Thread.sleep(2000);
		purchaseEntry.clickOnPendingEntriesBasedOnInvoiceNumber(invNumber);
		Thread.sleep(1000);
		int itemCountBeforeDelete = purchaseEntry.getItemDetailsFromPurchaseEntry().keySet().size();
		purchaseEntry.clickOnItemToGetFocus();
		purchaseEntry.ClickOnremoveRowButtonToDeleteItem();
		Thread.sleep(2000);
		purchaseEntry.clickOnSaveAndContinueButton();
		Thread.sleep(1000);
		purchaseEntry.clickOnPendingEntriesBasedOnInvoiceNumber(invNumber);
		Thread.sleep(1000);
		int itemCountAfterDelete = purchaseEntry.getItemDetailsFromPurchaseEntry().keySet().size();
		
		Assert.assertTrue(itemCountBeforeDelete == itemCountAfterDelete+1);
		
		log.info("=========== Test Completed ===========");
		
	}
	
	
	@Test(priority=1)
	public void verifySaveAndFunctionalityByAddingAnItem() throws InterruptedException {
		
		log.info("=========== Test Started ===========");
		
		LandingPage landingPage = new LandingPage();
		PurchaseEntryPage purchaseEntry = new PurchaseEntryPage();
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		Thread.sleep(5000);
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Open Purchase Entries");
		landingPage.clickOnNewEntry();
		landingPage.selectVendor(OR.getProperty("vendorName"));
		String invNumber = purchaseEntry.getInvoiceNum();
		purchaseEntry.enterInvoiceNumber(invNumber);
		purchaseEntry.enterItemDetailsWithoutBatchCreation(OR.getProperty("testItem"),"5","20");
		purchaseEntry.ClickOnaddRowButtonToAddItem();
		Thread.sleep(1000);
		purchaseEntry.enterItemDetailsWithoutBatchCreation(OR.getProperty("testItem2"),"5","10");
		purchaseEntry.clickOnSaveAndContinueButton();
		Thread.sleep(2000);
		
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Pending Entries");
		Thread.sleep(2000);
		purchaseEntry.clickOnPendingEntriesBasedOnInvoiceNumber(invNumber);
		Thread.sleep(1000);
		int itemCountBeforeAdd = purchaseEntry.getItemDetailsFromPurchaseEntry().keySet().size();
		purchaseEntry.clickOnItemToGetFocus();
		purchaseEntry.ClickOnaddRowButtonToAddItem();
		purchaseEntry.enterItemDetailsWithoutBatchCreation("CROBIT CZ TAB", "2", "5");
		Thread.sleep(2000);
		purchaseEntry.clickOnSaveAndContinueButton();
		Thread.sleep(1000);
		purchaseEntry.clickOnPendingEntriesBasedOnInvoiceNumber(invNumber);
		Thread.sleep(1000);
		int itemCountAfterAdd = purchaseEntry.getItemDetailsFromPurchaseEntry().keySet().size();
		
		Assert.assertTrue(itemCountBeforeAdd == itemCountAfterAdd-1);
		
		log.info("=========== Test Completed ===========");
		
	}
}
