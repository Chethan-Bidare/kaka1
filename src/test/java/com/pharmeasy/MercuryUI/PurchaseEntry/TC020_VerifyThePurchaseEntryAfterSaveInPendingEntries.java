package com.pharmeasy.MercuryUI.PurchaseEntry;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.LandingPage;
import com.pharmeasy.MercuryUI.Page.PurchaseEntryPage;

public class TC020_VerifyThePurchaseEntryAfterSaveInPendingEntries extends TestBase{

	public static final Logger log = Logger.getLogger(TC020_VerifyThePurchaseEntryAfterSaveInPendingEntries.class.getSimpleName());
	
	/* Create Purchase Entry
	 * Click on Save And Continue button
	 * Open the Purchase entry against Inv Number in Pending Entries Page
	 * Verifies all the Item details and purchase footer from that page
	 */
	
	@Test
	public void verifyThePurchaseEntryAfterSaveInPendingEntries() throws InterruptedException {
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
		String invNumber = purchaseEntry.getInvoiceNum() ;
		purchaseEntry.enterInvoiceNumber(invNumber);
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
		
		HashMap<String, HashMap<String, Object>> itemDetails = purchaseEntry.getItemDetailsFromPurchaseEntry();
		HashMap<String, Double> purFooter = purchaseEntry.getFooterDetails();
		
		purchaseEntry.clickOnSaveAndContinueButton();
		Thread.sleep(5000);
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Pending Entries");
		Thread.sleep(2000);
		purchaseEntry.clickOnPendingEntriesBasedOnInvoiceNumber(invNumber);
		Thread.sleep(2000);
		HashMap<String, HashMap<String, Object>> itemDetailsAfterSave = purchaseEntry.getItemDetailsFromPurchaseEntry();
		HashMap<String, Double> purFooterAfterSave = purchaseEntry.getFooterDetails();
		
		
		log.info("Item Details Before Save : "+itemDetails);
		log.info("Item Details After Save : "+itemDetailsAfterSave);
		log.info("Purchase footer details Before Save : "+purFooter);
		log.info("Purchase footer details After Save : "+purFooterAfterSave);
		Assert.assertTrue(itemDetails.equals(itemDetailsAfterSave));
		
		Assert.assertTrue(purFooter.equals(purFooterAfterSave));
		
		log.info(" ====== Test Completed ======");
		
	}
}
