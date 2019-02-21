package com.pharmeasy.MercuryUI.PurchaseEntry;

import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.LandingPage;
import com.pharmeasy.MercuryUI.Page.PurchaseEntryPage;
import com.pharmeasy.MercuryUI.Page.PurchaseReturn;

public class TC030_verifyRaiseIssueFunctionality extends TestBase{

	
	public static final Logger log = Logger.getLogger(TC030_verifyRaiseIssueFunctionality.class.getSimpleName());
	
	
	
	/*
	 * Enable Raise issue slider
	 * In the pop up, verify item name and batch No
	 */
	
	@Test(priority=0)
	public void verifyIssueItemNameAndBatch() throws InterruptedException {

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
		String invNum = purchaseEntry.getInvoiceNum();
		purchaseEntry.enterInvoiceNumber(invNum);
		purchaseEntry.enterItemDetailsWithoutBatchCreation(OR.getProperty("testItem"),"5","20");
		
		String batchNo = String.valueOf(purchaseEntry.getItemDetailsFromPurchaseEntry().get(OR.getProperty("testItem")).get("Batch No"));
		purchaseEntry.enableRaiseIssueSlider();
		Thread.sleep(1000);
		
		Assert.assertEquals(purchaseEntry.getRaiseIssueItemName(), OR.getProperty("testItem"));
		
		Assert.assertTrue(purchaseEntry.getRaiseIssueItemBatchName().contains(batchNo));

		log.info(" ====== Test Completed ======");
	}
	
	/*
	 * Verifies all the error msgs 
	 */
	
	@Test(priority=1)
	public void verifyErrorMSGS() throws InterruptedException {
		
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
		String invNum = purchaseEntry.getInvoiceNum();
		purchaseEntry.enterInvoiceNumber(invNum);
		//purchaseEntry.
		purchaseEntry.enterItemDetailsWithoutBatchCreation(OR.getProperty("testItem"),"","");
		purchaseEntry.enableRaiseIssueSlider();
		Thread.sleep(1000);
		
		Assert.assertEquals(landingPage.getToastMSG(), APP.getProperty("RaiseIssueErrorMSG"));
		
		purchaseEntry.disableRaiseIssueSlider();
		purchaseEntry.enterItemDetailsWithoutBatchCreation(OR.getProperty("testItem"),"5","20");
		purchaseEntry.enableRaiseIssueSlider();
		Thread.sleep(1000);
		purchaseEntry.clickOnRaiseIssueContinueButton();
		
		Assert.assertEquals(landingPage.getToastMSG(), APP.getProperty("RaiseIssueReasonErrorMSG"));
		
		purchaseEntry.selectReasonForReturnFromRaiseIssue("Medicine Un-deliverable");
		purchaseEntry.clickOnRaiseIssueContinueButton();
		
		Assert.assertEquals(landingPage.getToastMSG(), APP.getProperty("RaiseIssueReasonErrorMSG"));
		
		log.info(" ====== Test Completed ======");
	}
	
	
	/*
	 * Selects reason and package qty from the dropdown 
	 * clicks on COntinue
	 * verifies the same reason and package qty is displayed in pur entry screen 
	 */
	
	@Test(priority=2)
	public void verifyReasonAndPackageQtyDropdowns() throws InterruptedException {

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
		String invNum = purchaseEntry.getInvoiceNum();
		purchaseEntry.enterInvoiceNumber(invNum);
		purchaseEntry.enterItemDetailsWithoutBatchCreation(OR.getProperty("testItem"),"5","20");
		purchaseEntry.enableRaiseIssueSlider();
		Thread.sleep(1000);
		purchaseEntry.selectReasonForReturnFromRaiseIssue(APP.getProperty("RaiseIssueReason"));
		Thread.sleep(1000);
		purchaseEntry.selectQTYForReturnFromRaiseIssue(APP.getProperty("RaiseIssueQty"));
		Thread.sleep(2000);
		purchaseEntry.clickOnRaiseIssueContinueButton();
		Thread.sleep(2000);
		
		Hashtable<String, Hashtable<String, String>> issueItemDetails = purchaseEntry.getItemDetailsFromIssueBucket();
		String reason = issueItemDetails.get(OR.getProperty("testItem")).get("Reason of Return");
		String reasonQuantity = issueItemDetails.get(OR.getProperty("testItem")).get("Qty");
				
		Assert.assertEquals(reason, APP.getProperty("RaiseIssueReason"));
		
		Assert.assertEquals(reasonQuantity, APP.getProperty("RaiseIssueQty"));
		
		log.info(" ====== Test Completed ======");
	}
	
	
	/*
	 * raise issue for an item
	 * check the issue bucket is displayed with the same details
	 * click on Save button
	 * In the review page, verify the same issue bucket details is displayed
	 */
	
	@Test(priority=3)
	public void verifyIssueItemDetailsAfterSave() throws InterruptedException {

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
		String invNum = purchaseEntry.getInvoiceNum();
		purchaseEntry.enterInvoiceNumber(invNum);
		purchaseEntry.enterItemDetailsWithoutBatchCreation(OR.getProperty("testItem"),"5","20");
		purchaseEntry.enableRaiseIssueSlider();
		Thread.sleep(1000);
		purchaseEntry.selectReasonForReturnFromRaiseIssue(APP.getProperty("RaiseIssueReason"));
		Thread.sleep(1000);
		purchaseEntry.selectQTYForReturnFromRaiseIssue(APP.getProperty("RaiseIssueQty"));
		Thread.sleep(2000);
		purchaseEntry.clickOnRaiseIssueContinueButton();
		Thread.sleep(2000);
		
		Hashtable<String, Hashtable<String, String>> issueItemDetailsBeforeSave = purchaseEntry.getItemDetailsFromIssueBucket();
		purchaseEntry.clickOnSaveButton();
		Thread.sleep(1000);
		purchaseEntry.clickOnReviewButton();
		Thread.sleep(1000);
		Hashtable<String, Hashtable<String, String>> issueItemDetailsAfterSave = purchaseEntry.getItemDetailsFromIssueBucket();
		
		Assert.assertEquals(issueItemDetailsBeforeSave, issueItemDetailsAfterSave);
		
		log.info(" ====== Test Completed ======");
		
	}
	
	/*
	 * create a Raise issue and complete the pur entry
	 * After the completion, navigates to pending pur return 
	 * Checks there is a pur return against the invoice number
	 */
	
	@Test(priority=4)
	public void verifyRaiseIssueInPurchaseReturn() throws InterruptedException {

		log.info(" ====== Test Started ======");
		
		LandingPage landingPage = new LandingPage();
		PurchaseEntryPage purchaseEntry = new PurchaseEntryPage();
		PurchaseReturn purchaseReturn = new PurchaseReturn();
		
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
		purchaseEntry.enableRaiseIssueSlider();
		Thread.sleep(1000);
		purchaseEntry.selectReasonForReturnFromRaiseIssue(APP.getProperty("RaiseIssueReason"));
		Thread.sleep(1000);
		purchaseEntry.selectQTYForReturnFromRaiseIssue(APP.getProperty("RaiseIssueQty"));
		Thread.sleep(2000);
		purchaseEntry.clickOnRaiseIssueContinueButton();
		Thread.sleep(2000);
		purchaseEntry.clickOnSaveButton();
		Thread.sleep(2000);
		purchaseEntry.clickOnReviewButton();
		Thread.sleep(2000);
		purchaseEntry.clickOnCreateButton();
		Thread.sleep(5000);
		driver.navigate().back();
		Thread.sleep(1000);
		landingPage.selectMainMenuOption("Pur Return");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Pending Return");
		Thread.sleep(1000);
		ArrayList<String> invNumsFromPurReturn = purchaseReturn.getInvoiceNumberFromPendingPurchaseReturnPage();
		System.out.println(invNumsFromPurReturn);
		
		Assert.assertTrue(invNumsFromPurReturn.contains(invNum));
		
		log.info(" ====== Test Completed ======");
		
	}
	
	
	
	
	
	
	
}
