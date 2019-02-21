package com.pharmeasy.MercuryUI.Gatepass;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.GatepassPage;
import com.pharmeasy.MercuryUI.Page.LandingPage;
import com.pharmeasy.MercuryUI.Page.PurchaseEntryPage;

public class TC_003_VerifyGatePassCreation extends TestBase{

	
	@DataProvider(name="TestData")
	public Object[][] getTestData(){
		String[][] getData = readExcel("TestData.xlsx", "CreateGatePass");
		return getData ;
		
	}
	
		//Verifying the New gate Pass creation functionality 
		//This method is created by Chethan Bidare on 22-01-19
		
		@Test(dataProvider="TestData")
		public void verifyGatePassCreation(String vendorName,String invNumber,String invAmount) throws InterruptedException {
			
			LandingPage landingPage = new LandingPage();
			landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
			GatepassPage gatePassPage = new GatepassPage();
			PurchaseEntryPage purEntry = new PurchaseEntryPage();
			
			
			landingPage.clickOnNewEntry();
			landingPage.selectVendor(vendorName);
			gatePassPage.enterGatePassEntryDetails(purEntry.getInvoiceNum(),invAmount);
			gatePassPage.disablePOavailableslider();
			gatePassPage.clickOnCreateGatePassButton();
			Thread.sleep(1000);
			Assert.assertEquals(gatePassPage.getToastMSG(), APP.getProperty("GatePassSuccessMSG"));
			
		}
}
