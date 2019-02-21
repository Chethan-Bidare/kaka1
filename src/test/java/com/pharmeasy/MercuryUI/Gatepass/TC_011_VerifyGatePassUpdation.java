package com.pharmeasy.MercuryUI.Gatepass;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import java.util.ArrayList;

import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.GatepassPage;
import com.pharmeasy.MercuryUI.Page.LandingPage;
import com.pharmeasy.MercuryUI.Page.PurchaseEntryPage;

public class TC_011_VerifyGatePassUpdation extends TestBase{
	
	@DataProvider(name="TestData")
	public Object[][] getTestData(){
		String[][] getData = readExcel("TestData.xlsx", "UpdateGatePass");
		return getData ;		
	}

	@Test(priority=0)
	public void verifyUpdateMSGText() throws InterruptedException {
		LandingPage landingPage = new LandingPage();
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		GatepassPage gatePassPage = new GatepassPage();
		ArrayList<Integer> gatePassIDS = gatePassPage.fetchGatePassID();
		Thread.sleep(1000);
		gatePassPage.clickOnGatePassByOrderID(gatePassIDS.get(gatePassIDS.size()-1));
		Thread.sleep(2000);
		gatePassPage.clickOnUpdateGatePassButton();
		Thread.sleep(1000);
		Assert.assertEquals(gatePassPage.getToastMSG(), APP.getProperty("GatePassUpdateSuccessMSG"));
	}
	
	
	@Test(priority=1,dataProvider="TestData")
	public void verifyInvoiceNumUpdate(String invNumber, String invAmount) throws InterruptedException {
		LandingPage landingPage = new LandingPage();
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		GatepassPage gatePassPage = new GatepassPage();
		PurchaseEntryPage pe = new PurchaseEntryPage() ;
		ArrayList<Integer> gatePassIDS = gatePassPage.fetchGatePassID();
		int gatepassID = gatePassIDS.get(gatePassIDS.size()-1);
		gatePassPage.clickOnGatePassByOrderID(gatepassID);
		Thread.sleep(2000);
		String genInvNum = pe.getInvoiceNum();
		gatePassPage.enterGatePassEntryDetails(genInvNum, invAmount);
		Thread.sleep(2000);
		gatePassPage.clickOnUpdateGatePassButton();
		Thread.sleep(3000);
		Assert.assertTrue(gatePassPage.fetchGatePassDetailsByID().get(gatepassID).contains(genInvNum));
		
	}
	
	@Test(priority=2,dataProvider="TestData")
	public void verifyInvoiceAmountUpdate(String invNumber, String invAmount) throws InterruptedException {
		
		LandingPage landingPage = new LandingPage();
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		GatepassPage gatePassPage = new GatepassPage();
		PurchaseEntryPage pe = new PurchaseEntryPage() ;
		ArrayList<Integer> gatePassIDS = gatePassPage.fetchGatePassID();
		int gatepassID = gatePassIDS.get(gatePassIDS.size()-1);
		gatePassPage.clickOnGatePassByOrderID(gatepassID);
		Thread.sleep(2000);
		String genInvNum = pe.getInvoiceNum();
		gatePassPage.enterGatePassEntryDetails(genInvNum, invAmount);
		Thread.sleep(2000);
		gatePassPage.clickOnUpdateGatePassButton();
		Thread.sleep(3000);
		invAmount = invAmount.substring(0,invAmount.length()-2);
		Assert.assertTrue(gatePassPage.fetchGatePassDetailsByID().get(gatepassID).contains("\u20B9"+invAmount));
		
	}
}
