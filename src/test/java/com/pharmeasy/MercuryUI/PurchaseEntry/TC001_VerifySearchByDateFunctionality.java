package com.pharmeasy.MercuryUI.PurchaseEntry;

import org.testng.Assert;
import org.testng.annotations.Test;

import org.apache.log4j.Logger;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.LandingPage;
import com.pharmeasy.MercuryUI.Page.PurchaseEntryPage;

public class TC001_VerifySearchByDateFunctionality extends TestBase{

	public static final Logger log = Logger.getLogger(TC001_VerifySearchByDateFunctionality.class.getSimpleName());
	
	
	@Test
	public void verifySearchByDateFunctionality() throws InterruptedException {
		log.info("================ Test started =================");
		LandingPage landingPage = new LandingPage();
		PurchaseEntryPage purchaseEntry = new PurchaseEntryPage();
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		Thread.sleep(5000);
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Open Purchase Entries");
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
		String fullDate = formatter.format(cal.getTime());
		String date = fullDate.substring(0, 2);
		landingPage.searchByDate(date,date);
		Thread.sleep(5000);
		
		ArrayList<String> dates = purchaseEntry.fetchGatePassDateFromPurchasePage();
		for(String orderDate : dates) {
			Assert.assertTrue(orderDate.contains(fullDate));
		}
		log.info("=========== Test Completed =======================");
	}
}
