package com.pharmeasy.MercuryUI.Page;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.pharmeasy.MercuryUI.Base.TestBase;

public class PurchaseReturn extends TestBase{

	public static final Logger log = Logger.getLogger(PurchaseReturn.class.getSimpleName());
	
	
	
	
	
	
	
	
	
	
	
	
	public PurchaseReturn() {
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	
	public ArrayList<String> getInvoiceNumberFromPendingPurchaseReturnPage() {
		//wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//*[@class='purchase-rTable']/div"))));
		int totalRows = driver.findElements(By.xpath("//*[@class='purchase-return-rTable']/div")).size();
		
		ArrayList<String> invoiceNumbers = new ArrayList<>();
		for(int i=2; i<=totalRows; i++) {
			String InvNum = driver.findElement(By.xpath("//*[@class='purchase-return-rTable']/div["+i+"]/div[3]")).getText();
			InvNum = InvNum.trim();
			invoiceNumbers.add(InvNum);
		}
		
		return invoiceNumbers ;
	}
	
	
	
}
