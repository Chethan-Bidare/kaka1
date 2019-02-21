package com.pharmeasy.MercuryUI.PurchaseEntry;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.LandingPage;
import com.pharmeasy.MercuryUI.Page.PurchaseEntryPage;

public class TC029_VerifyPaginationInPurchaseEntryPage extends TestBase{

public static final Logger log = Logger.getLogger(TC029_VerifyPaginationInPurchaseEntryPage.class.getSimpleName());
	
	@Test(priority=0)
	public void verifyPaginationInPurchaseEntry() throws InterruptedException {

		log.info(" ====== Test Started ======");
		
		LandingPage landingPage = new LandingPage();
		PurchaseEntryPage purchaseEntry = new PurchaseEntryPage();
		
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		Thread.sleep(5000);
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Open Purchase Entries");
		
		for (int i = 1; i <5; i++) {
			ArrayList<Integer> beforePagination = purchaseEntry.fetchGatePassIDFromPurchasePage();
			landingPage.clickOnPaginationNEXTbutton();
			Thread.sleep(3000);
			ArrayList<Integer> afterPagination = purchaseEntry.fetchGatePassIDFromPurchasePage();
			Assert.assertTrue(!beforePagination.equals(afterPagination));
		}
		
		for (int i = 1; i <3; i++) {
			ArrayList<Integer> beforePagination = purchaseEntry.fetchGatePassIDFromPurchasePage();
			landingPage.clickOnPaginationPREVbutton();
			Thread.sleep(3000);
			ArrayList<Integer> afterPagination = purchaseEntry.fetchGatePassIDFromPurchasePage();
			Assert.assertTrue(!beforePagination.equals(afterPagination));
		}
		
		log.info(" ====== Test Completed ======");
	}
	
	
	@Test(priority=1)
	public void verifyPaginationInPendingPurchaseEntry() throws InterruptedException {

		log.info(" ====== Test Started ======");
		
		LandingPage landingPage = new LandingPage();
		PurchaseEntryPage purchaseEntry = new PurchaseEntryPage();
		
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		Thread.sleep(5000);
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Pending Entries");
		
		for (int i = 1; i <5; i++) {
			ArrayList<Integer> beforePagination = purchaseEntry.fetchGatePassIDFromPurchasePage();
			landingPage.clickOnPaginationNEXTbutton();
			Thread.sleep(3000);
			ArrayList<Integer> afterPagination = purchaseEntry.fetchGatePassIDFromPurchasePage();
			Assert.assertTrue(!beforePagination.equals(afterPagination));
		}
		
		for (int i = 1; i <3; i++) {
			ArrayList<Integer> beforePagination = purchaseEntry.fetchGatePassIDFromPurchasePage();
			landingPage.clickOnPaginationPREVbutton();
			Thread.sleep(3000);
			ArrayList<Integer> afterPagination = purchaseEntry.fetchGatePassIDFromPurchasePage();
			Assert.assertTrue(!beforePagination.equals(afterPagination));
		}
		
		log.info(" ====== Test Completed ======");
	}
	
	@Test(priority=2)
	public void verifyPaginationInPurchaseHistory() throws InterruptedException {

		log.info(" ====== Test Started ======");
		
		LandingPage landingPage = new LandingPage();
		PurchaseEntryPage purchaseEntry = new PurchaseEntryPage();
		
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		Thread.sleep(5000);
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Purchase History");
		
		for (int i = 1; i <5; i++) {
			ArrayList<Integer> beforePagination = purchaseEntry.fetchGatePassIDFromPurchasePage();
			landingPage.clickOnPaginationNEXTbutton();
			Thread.sleep(3000);
			ArrayList<Integer> afterPagination = purchaseEntry.fetchGatePassIDFromPurchasePage();
			Assert.assertTrue(!beforePagination.equals(afterPagination));
		}
		
		for (int i = 1; i <3; i++) {
			ArrayList<Integer> beforePagination = purchaseEntry.fetchGatePassIDFromPurchasePage();
			landingPage.clickOnPaginationPREVbutton();
			Thread.sleep(3000);
			ArrayList<Integer> afterPagination = purchaseEntry.fetchGatePassIDFromPurchasePage();
			Assert.assertTrue(!beforePagination.equals(afterPagination));
		}
		
		log.info(" ====== Test Completed ======");
	}
	
	@Test(priority=3)
	public void verifyPaginationInPendingInventory() throws InterruptedException {

		log.info(" ====== Test Started ======");
		
		LandingPage landingPage = new LandingPage();
		PurchaseEntryPage purchaseEntry = new PurchaseEntryPage();
		
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		Thread.sleep(5000);
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Pending Inventory Live");
		
		for (int i = 1; i <5; i++) {
			ArrayList<Integer> beforePagination = purchaseEntry.fetchGatePassIDFromPurchasePage();
			landingPage.clickOnPaginationNEXTbutton();
			Thread.sleep(3000);
			ArrayList<Integer> afterPagination = purchaseEntry.fetchGatePassIDFromPurchasePage();
			Assert.assertTrue(!beforePagination.equals(afterPagination));
		}
		
		for (int i = 1; i <3; i++) {
			ArrayList<Integer> beforePagination = purchaseEntry.fetchGatePassIDFromPurchasePage();
			landingPage.clickOnPaginationPREVbutton();
			Thread.sleep(3000);
			ArrayList<Integer> afterPagination = purchaseEntry.fetchGatePassIDFromPurchasePage();
			Assert.assertTrue(!beforePagination.equals(afterPagination));
		}
		
		log.info(" ====== Test Completed ======");
	}
	
	
	@Test(priority=4)
	public void verifyPaginationInSplitVerifier() throws InterruptedException {

		log.info(" ====== Test Started ======");
		
		LandingPage landingPage = new LandingPage();
		PurchaseEntryPage purchaseEntry = new PurchaseEntryPage();
		
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		Thread.sleep(5000);
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Split Verifier");
		
		for (int i = 1; i <5; i++) {
			ArrayList<Integer> beforePagination = purchaseEntry.fetchGatePassIDFromPurchasePage();
			landingPage.clickOnPaginationNEXTbutton();
			Thread.sleep(3000);
			ArrayList<Integer> afterPagination = purchaseEntry.fetchGatePassIDFromPurchasePage();
			Assert.assertTrue(!beforePagination.equals(afterPagination));
		}
		
		for (int i = 1; i <3; i++) {
			ArrayList<Integer> beforePagination = purchaseEntry.fetchGatePassIDFromPurchasePage();
			landingPage.clickOnPaginationPREVbutton();
			Thread.sleep(3000);
			ArrayList<Integer> afterPagination = purchaseEntry.fetchGatePassIDFromPurchasePage();
			Assert.assertTrue(!beforePagination.equals(afterPagination));
		}
		
		log.info(" ====== Test Completed ======");
	}
}
