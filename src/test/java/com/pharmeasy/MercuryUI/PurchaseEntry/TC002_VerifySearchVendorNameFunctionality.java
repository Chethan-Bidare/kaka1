package com.pharmeasy.MercuryUI.PurchaseEntry;

import org.testng.annotations.Test;
import org.testng.Assert;


import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;
import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.LandingPage;
import com.pharmeasy.MercuryUI.Page.PurchaseEntryPage;

public class TC002_VerifySearchVendorNameFunctionality extends TestBase{

	public static final Logger log = Logger.getLogger(TC002_VerifySearchVendorNameFunctionality.class.getSimpleName());
	
	//Created by Chethan Bidare on 22-01-19
	
		@Test
		public void verifyVendorSearch() throws InterruptedException {
			
			log.info("=========== Test Started ===========");
			
			LandingPage landingPage = new LandingPage();
			PurchaseEntryPage purchaseEntry = new PurchaseEntryPage();
			landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
			Thread.sleep(5000);
			landingPage.selectMainMenuOption("Pur.Entry");
			Thread.sleep(1000);
			landingPage.selectSubMainMenuoption("Open Purchase Entries");
			landingPage.searchByVendorName(OR.getProperty("vendorName"));
			Thread.sleep(5000);
			
			ArrayList<Integer> orderIDS = purchaseEntry.fetchGatePassIDFromPurchasePage();
			HashMap<Integer, ArrayList<String>> orderDetails = purchaseEntry.fetchGatePassDetailsByIDFromPurchasePage();
			for(int orderNum : orderIDS) {
				Assert.assertTrue(orderDetails.get(orderNum).contains(OR.getProperty("vendorName")));
			}
			
			log.info("============ Test Completed ============");
			
		}
}
