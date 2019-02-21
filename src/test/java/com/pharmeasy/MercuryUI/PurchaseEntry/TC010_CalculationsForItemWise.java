package com.pharmeasy.MercuryUI.PurchaseEntry;

import org.testng.annotations.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.Assert;
import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.LandingPage;
import com.pharmeasy.MercuryUI.Page.PurchaseEntryPage;

public class TC010_CalculationsForItemWise extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC010_CalculationsForItemWise.class.getSimpleName());
	
	@Test
	public void verifyCalculationsItemWise() throws InterruptedException {
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
		purchaseEntry.enterDiscPercentage("20");
		
		HashMap<String, HashMap<String, Object>> itemDetailsFromPurEntry = purchaseEntry.getItemDetailsFromPurchaseEntry();
		
		int actualTotalQty = Integer.valueOf(String.valueOf(itemDetailsFromPurEntry.get(OR.getProperty("testItem")).get("Total Qty")));
		double discAmt = Double.valueOf(String.valueOf(itemDetailsFromPurEntry.get(OR.getProperty("testItem")).get("Item Disc Amt")));
		double schDiscAmount = Double.valueOf(String.valueOf(itemDetailsFromPurEntry.get(OR.getProperty("testItem")).get("Sch Disc Amt")));
		double grossAmt = Double.valueOf(String.valueOf(itemDetailsFromPurEntry.get(OR.getProperty("testItem")).get("Gross Amt")));
		double totalTaxAmt = Double.valueOf(String.valueOf(itemDetailsFromPurEntry.get(OR.getProperty("testItem")).get("Total.Tax Amt")));
		
		double purRtAftSch = Double.valueOf(String.valueOf(itemDetailsFromPurEntry.get(OR.getProperty("testItem")).get("Pur.Rt. Aft.Sch")));
		double purRtAftSchAftDisc = Double.valueOf(String.valueOf(itemDetailsFromPurEntry.get(OR.getProperty("testItem")).get("Pur.Rt Aft Sch Aft Dis")));
		double EPR = Double.valueOf(String.valueOf(purchaseEntry.getItemDetailsFromPurchaseEntry().get(OR.getProperty("testItem")).get("EPR")));
		double AbatedMRP = Double.valueOf(String.valueOf(itemDetailsFromPurEntry.get(OR.getProperty("testItem")).get("Abt MRP")));
		double margin = Double.valueOf(String.valueOf(itemDetailsFromPurEntry.get(OR.getProperty("testItem")).get("Margin%")));
		double cgstAmt=Double.valueOf(String.valueOf(itemDetailsFromPurEntry.get(OR.getProperty("testItem")).get("CGST")));
		double sgstAmt=Double.valueOf(String.valueOf(itemDetailsFromPurEntry.get(OR.getProperty("testItem")).get("SGST")));
		
		HashMap<String, HashMap<String, Double>> calculatedValues = purchaseEntry.getCalculatedValuesItemwise();
		
		Assert.assertEquals(actualTotalQty, Math.round(calculatedValues.get(OR.getProperty("testItem")).get("Total Qty")));
		Assert.assertEquals(discAmt, new BigDecimal(calculatedValues.get(OR.getProperty("testItem")).get("Item Disc Amt")).setScale(2, RoundingMode.HALF_UP).doubleValue());
		
		Assert.assertEquals(schDiscAmount, new BigDecimal(calculatedValues.get(OR.getProperty("testItem")).get("Sch Disc Amt")).setScale(2, RoundingMode.HALF_UP).doubleValue());
		Assert.assertEquals(grossAmt, calculatedValues.get(OR.getProperty("testItem")).get("Gross Amt"));
		Assert.assertEquals(purRtAftSch, new BigDecimal(calculatedValues.get(OR.getProperty("testItem")).get("Pur.Rt. Aft.Sch")).setScale(2, RoundingMode.HALF_UP).doubleValue());
		Assert.assertEquals(purRtAftSchAftDisc, new BigDecimal(calculatedValues.get(OR.getProperty("testItem")).get("Pur.Rt Aft Sch Aft Dis")).setScale(2, RoundingMode.HALF_UP).doubleValue());
		Assert.assertEquals(totalTaxAmt, new BigDecimal(calculatedValues.get(OR.getProperty("testItem")).get("Total.Tax Amt")).setScale(2, RoundingMode.HALF_UP).doubleValue());
		Assert.assertEquals(EPR, new BigDecimal(calculatedValues.get(OR.getProperty("testItem")).get("EPR")).setScale(2, RoundingMode.HALF_UP).doubleValue());
		Assert.assertEquals(AbatedMRP,new BigDecimal(calculatedValues.get(OR.getProperty("testItem")).get("Abt MRP")).setScale(2, RoundingMode.HALF_UP).doubleValue());
		Assert.assertEquals(margin, new BigDecimal(calculatedValues.get(OR.getProperty("testItem")).get("Margin%")).setScale(2, RoundingMode.HALF_UP).doubleValue());
		Assert.assertEquals(cgstAmt, new BigDecimal(calculatedValues.get(OR.getProperty("testItem")).get("CGST")).setScale(2, RoundingMode.HALF_UP).doubleValue());
		Assert.assertEquals(sgstAmt, new BigDecimal(calculatedValues.get(OR.getProperty("testItem")).get("SGST")).setScale(2, RoundingMode.HALF_UP).doubleValue());
		
		log.info(" ====== Test Completed ====== ");
	}

}
