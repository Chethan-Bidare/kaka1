package com.pharmeasy.MercuryUI.Page;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.pharmeasy.MercuryUI.Base.TestBase;


public class PurchaseEntryPage extends TestBase {
	
	
	public static final Logger log = Logger.getLogger(PurchaseEntryPage.class.getSimpleName());
	
	@FindBy(xpath="//h2[@class='invoicemap-header-heading']")
	WebElement PurchaseEntryHeader ;
	
	@FindBy(xpath="//*[@placeholder='Enter Invoice No']")
	WebElement InvoiceNumberField ;
	
	@FindBy(xpath="//td/button[contains(text(),'+')]")
	WebElement AddRowButton ;
	
	@FindBy(xpath="//td/button[contains(text(),'-')]")
	WebElement RemoveRowButton ;
	
	@FindBy(xpath="//button[@class='buttonthemed']")
	WebElement ReviewButton	 ;
	
	@FindBy(xpath="//button[@class='buttonthemed continue']")
	WebElement SaveAndContinueButton ;
	
	@FindBy(xpath="//button[contains(text(),'Create')]")
	WebElement CreateButton ;
	
	@FindBy(xpath="//div[2]/div/div[1]/div[1]/div[1]/input")
	WebElement xtraDiscPer ;
	
	@FindBy(xpath="//div[2]/div/div[1]/div[1]/div[4]/input")
	WebElement RoundOFF ;
	
	@FindBy(xpath="//div[2]/div/div[1]/div[1]/div[2]/input")
	WebElement CreditAmount ;
	
	@FindBy(xpath="//div[2]/label/span")
	WebElement creditAmtSlider ;
	
	@FindBy(xpath="//button[contains(text(),'Manual Entry')]")
	WebElement ManualEntryButton ;
	
	
	@FindBy(xpath="//nav/div/label/span")
	WebElement RaiseIssueSlider ;
	
	@FindBy(xpath="//p[@class='centerpopup-main-title']")
	WebElement RaiseIssueItemName ;
	
	@FindBy(xpath="//p[@class='centerpopup-main-titlecaption']")
	WebElement RaiseIssueItemBatch ;
	
	@FindBy(xpath="//*[@placeholder='Select Reason']")
	WebElement SelectReasonForRaiseIssue ;
	
	@FindBy(xpath="//*[@placeholder='Select Quantity']")
	WebElement SelectQtyForRaiseIssue ;
	
	@FindBy(xpath="//div[@class='centerpopup-main-addnew']")
	WebElement AddNewToSelectReason ;
	
	@FindBy(xpath="//button[contains(text(),'Continue')]")
	WebElement RaiseIssueContinueButton ;
	
	@FindBy(xpath="//button[contains(text(),'Save')]")
	WebElement SaveButton ;
	
    
    public PurchaseEntryPage()
	{
		PageFactory.initElements(driver, this);
	}
    
    /*
    This method will store all the GatePass IDs from the purchase page
    in an array list            */
  //Created By Chethan K Bidare on 28-01-19
    
    public ArrayList<Integer> fetchGatePassIDFromPurchasePage() {
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//*[@class='purchase-rTable']/div"))));
		int totalRows = driver.findElements(By.xpath("//*[@class='purchase-rTable']/div")).size();
		ArrayList<Integer> gatePassIDs = new ArrayList<Integer>();
		for(int i=2; i<=totalRows; i++) {
			String orderID = driver.findElement(By.xpath("//*[@class='purchase-rTable']/div["+i+"]/div[1]")).getText();
			orderID = orderID.trim();
			gatePassIDs.add(Integer.parseInt(orderID));
		}
		Collections.sort(gatePassIDs);
		log.info("Fetching all the gate Pass IDs from the Purchase page");
		return gatePassIDs ;
	}
	
    /*
    this method will store all the details of gatepass in
    purchase page. Gatepass id is unique. So based on gatepass id
    all the other details of that gatepass can be fetched   
      */
  //Created By Chethan K Bidare on 28-01-19
    
    
	public HashMap<Integer,ArrayList<String>> fetchGatePassDetailsByIDFromPurchasePage() {
		HashMap<Integer, ArrayList<String>> gatePassDetails = new HashMap<Integer, ArrayList<String>>();
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//*[@class='purchase-rTable']/div"))));
		int totalRows = driver.findElements(By.xpath("//*[@class='purchase-rTable']/div")).size();
		int totalCols = driver.findElements(By.xpath("//*[@class='purchase-rTable']/div[1]/div")).size();
		int key = 0 ;
		ArrayList<String> values ;
		for(int i=2; i<=totalRows; i++) {
			values = new ArrayList<String>();
			for(int j=1; j<=totalCols; j++) {
			
				if(j==1) {
					String orderID= driver.findElement(By.xpath("//*[@class='purchase-rTable']/div["+i+"]/div["+j+"]")).getText();
					orderID = orderID.trim();
					key = Integer.parseInt(orderID);	
				}
				else {
					values.add(driver.findElement(By.xpath("//*[@class='purchase-rTable']/div["+i+"]/div["+j+"]")).getText());
				}
			}
			gatePassDetails.put(key, values);
		}
	
		log.info("Fetching all the gate Pass order details from the Order ID in Purchase page");
		return gatePassDetails ;
		
	}
	
	
	public ArrayList<String> fetchDetailsFromPendingEntriesPage() {
		int totalRows = driver.findElements(By.xpath("//*[@class='purchase-rTable']/div")).size();
		int totalCols = driver.findElements(By.xpath("//*[@class='purchase-rTable']/div[1]/div")).size();
		ArrayList<String> values = new ArrayList<String>();
		for(int i=2; i<=totalRows; i++) {
			for(int j=1; j<=totalCols; j++) {
					values.add(driver.findElement(By.xpath("//*[@class='purchase-rTable']/div["+i+"]/div["+j+"]")).getText());
				}
			}
		return values ;
		}
	
		
	
    
	public ArrayList<String> fetchGatePassDateFromPurchasePage() {
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//*[@class='purchase-rTable']/div"))));
		int totalRows = driver.findElements(By.xpath("//*[@class='purchase-rTable']/div")).size();
		
		ArrayList<String> gatePassIDs = new ArrayList<String>();
		
		if(totalRows==0) {
			System.out.println("NO order IDs Exist for the date");
			return null ;
		}
		else {
			for (int i = 2; i <=totalRows; i++) {
				String orderID = driver.findElement(By.xpath("//*[@class='purchase-rTable']/div[" + i + "]/div[7]"))
						.getText();
				orderID = orderID.substring(0, 8).trim();
				gatePassIDs.add(orderID);
			}
			log.info("Fetching all the gate Pass order ID Dates from the Purchase page");
			return gatePassIDs ;
		}
		
	}
	
	
	public void clickOnGatePassByOrderIDFromPurchasePage(int orderID) {
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//*[@class='purchase-rTable']/div"))));
		int totalRows = driver.findElements(By.xpath("//*[@class='purchase-rTable']/div")).size();
		
		for(int i=2; i<=totalRows; i++) {
			String getOrderID = driver.findElement(By.xpath("//*[@class='purchase-rTable']/div["+i+"]/div[1]")).getText();
			getOrderID = getOrderID.trim();
			int gatepassID = Integer.parseInt(getOrderID);
			if(gatepassID==orderID) {
				driver.findElement(By.xpath("//*[@class='purchase-rTable']/div["+i+"]/div[1]")).click();
				log.info("Clicked on the specified Gatepass ID "+orderID);
				break ;
			}
		}
	}
	
	
	public void clickOnGatePassByInvoiceNumberFromPurchasePage(String invNum) {
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//*[@class='purchase-rTable']/div"))));
		int totalRows = driver.findElements(By.xpath("//*[@class='purchase-rTable']/div")).size();
		
		for(int i=2; i<=totalRows; i++) {
			String getInvNum = driver.findElement(By.xpath("//*[@class='purchase-rTable']/div["+i+"]/div[4]")).getText();
			getInvNum = getInvNum.trim();
			//String gatepassID = Integer.parseInt(getInvNum);
			if(getInvNum.equalsIgnoreCase(invNum)) {
				driver.findElement(By.xpath("//*[@class='purchase-rTable']/div["+i+"]/div[4]")).click();
				log.info("Clicked on the specified Invoice number : "+invNum);
				break ;
			}
		}
	}
    
    public void clickOnManualEntryButton() {
    	wait.until(ExpectedConditions.elementToBeClickable(ManualEntryButton));
    	ManualEntryButton.click();
    }
    
    public String getHeaderNameFromPurchaseEntry() {
    	return PurchaseEntryHeader.getText();
    }
    
  
    public void enterInvoiceNumber(String invoiceNumber) {
    	InvoiceNumberField.sendKeys(invoiceNumber);
    	log.info("Entered Invoice Number ");
    }
    
    public void enterItemDetailsWithoutBatchCreation(String medicineName,String qty,String purRate) throws InterruptedException {
    	
    	int itemRowSize = driver.findElements(By.xpath("//*/tbody/tr")).size();
    	int colSize = driver.findElements(By.xpath("//*/tbody/tr["+itemRowSize+"]/td")).size();
    	for(int i=1; i<=colSize; i++) {
    		
    		if(i==1) {
    			driver.findElement(By.xpath("//*/tbody/tr["+itemRowSize+"]/td["+i+"]/input")).click();
    			driver.findElement(By.xpath("//*[@id='search' and @placeholder='Search Medicine']")).sendKeys(medicineName);
    			Thread.sleep(3000);
    			SelectFromAutoSuggestionSearch(medicineName);
    			Thread.sleep(2000);
    			log.info("Selected "+medicineName+" from the item list");
    		}
    		else if(i==7) {
    			driver.findElement(By.xpath("//*/tbody/tr["+itemRowSize+"]/td["+i+"]/input")).clear();
    			driver.findElement(By.xpath("//*/tbody/tr["+itemRowSize+"]/td["+i+"]/input")).sendKeys(qty);
    			log.info("Entered "+qty+" qty");
    			
    		}
    		else if(i==11) {
    			driver.findElement(By.xpath("//*/tbody/tr["+itemRowSize+"]/td["+i+"]/input")).clear();
    			driver.findElement(By.xpath("//*/tbody/tr["+itemRowSize+"]/td["+i+"]/input")).sendKeys(purRate);
    			log.info("Entered Pur rate : "+purRate);
    		}
    	}
    }
    
    
    public void enterSchemeQty(String schQty) {
    	int itemRowSize = driver.findElements(By.xpath("//*/tbody/tr")).size();
    	driver.findElement(By.xpath("//*/tbody/tr["+itemRowSize+"]/td[8]/input")).clear();
    	driver.findElement(By.xpath("//*/tbody/tr["+itemRowSize+"]/td[8]/input")).sendKeys(schQty);
		log.info("Entered Scheme Qty : "+schQty);
    }
    
    public void enterDiscPercentage(String discPer) {
    	int itemRowSize = driver.findElements(By.xpath("//*/tbody/tr")).size();
    	driver.findElement(By.xpath("//*/tbody/tr["+itemRowSize+"]/td[12]/input")).clear();
    	driver.findElement(By.xpath("//*/tbody/tr["+itemRowSize+"]/td[12]/input")).sendKeys(discPer);
    	log.info("Entered Discount % "+discPer);
    	
    }
    
    public void enterSchemeDiscPercentage(String schdiscPer) {
    	int itemRowSize = driver.findElements(By.xpath("//*/tbody/tr")).size();
    	driver.findElement(By.xpath("//*/tbody/tr["+itemRowSize+"]/td[12]/input")).clear();
    	driver.findElement(By.xpath("//*/tbody/tr["+itemRowSize+"]/td[12]/input")).sendKeys(schdiscPer);
		log.info("Entered Scheme Disc Per : "+schdiscPer);
    }
    
   
    
    
    public void createBatch(String batchNo,String MRP,String expiryMonth,String expiryYear) throws InterruptedException {
    	int itemRowSize = driver.findElements(By.xpath("//*/tbody/tr")).size();
    	int colSize = driver.findElements(By.xpath("//*/tbody/tr["+itemRowSize+"]/td")).size();
    	for(int i=1; i<=colSize; i++) {
    		
    		if(i==4) {
    			driver.findElement(By.xpath("//*/tbody/tr["+itemRowSize+"]/td["+i+"]/input")).clear();
    			driver.findElement(By.xpath("//*/tbody/tr["+itemRowSize+"]/td["+i+"]/input")).sendKeys(batchNo);
    			log.info("Entered Batch No "+batchNo);
    			
    		}
    		else if(i==5) {
    			driver.findElement(By.xpath("//*/tbody/tr["+itemRowSize+"]/td["+i+"]/input")).clear();
    			driver.findElement(By.xpath("//*/tbody/tr["+itemRowSize+"]/td["+i+"]/input")).sendKeys(MRP);
    			log.info("Entered MRP :"+MRP);
    			
    		}
    		else if(i==6) {
    			driver.findElement(By.xpath("//*/tbody/tr["+itemRowSize+"]/td["+i+"]/input")).clear();
    			driver.findElement(By.xpath("//*/tbody/tr["+itemRowSize+"]/td["+i+"]/input")).sendKeys(expiryMonth);
    			Thread.sleep(1000);
    			driver.findElement(By.xpath("//*/tbody/tr["+itemRowSize+"]/td["+i+"]/input")).sendKeys(Keys.chord(Keys.CONTROL,Keys.ARROW_RIGHT));
    			driver.findElement(By.xpath("//*/tbody/tr["+itemRowSize+"]/td["+i+"]/input")).sendKeys(expiryYear);
    			log.info("Entered Expiry : "+expiryMonth+"/"+expiryYear);
    		}
    	}
    }
    
    public void ClickOnaddRowButtonToAddItem() {
    	AddRowButton.click();
    	log.info("Clicked on + Button");
    }
    
    public void ClickOnremoveRowButtonToDeleteItem() {
    	RemoveRowButton.click();
    	log.info("Clicked on - Button");
    }
    
    public void clickOnReviewButton() {
    	ReviewButton.click();
    	log.info("Clicked on Review Button");
    }
    
    public void clickOnSaveAndContinueButton() {
    	SaveAndContinueButton.click();
    	log.info("Clicked on Save And Continue Button ");
    }
    
    public void clickOnCreateButton() {
    	CreateButton.click();
    	log.info("Clicked on Create button");
    }
    
    public void enterXtraDisc(String XtraDisc) {
    	xtraDiscPer.clear();
    	xtraDiscPer.sendKeys(XtraDisc);
    }
    
    public void enterRoundOffValue(String roundOff) {
    	RoundOFF.clear();
    	RoundOFF.sendKeys(roundOff);
    }
    
    
    /*     this method will store all the data from the item table.
           So item name will be the main key and
           item header label is nested key and the values are the nested values       */
  //Created By Chethan K Bidare on 28-01-19
    
    public HashMap<String,HashMap<String,Object>> getItemDetailsFromPurchaseEntry() {
    	HashMap<String, HashMap<String, Object>> itemDetails = new HashMap<String, HashMap<String,Object>>();
    	HashMap<String, Object> itemSubdetails ;
    	int noOfItems = driver.findElements(By.xpath("//*/tbody/tr")).size();
    	
    
    	for(int i=1; i<=noOfItems; i++) {
    		itemSubdetails = new HashMap<String, Object>();
    		String mainKey ;
    		int totalCols = driver.findElements(By.xpath("//*/tbody/tr["+i+"]/td")).size();
    		//driver.findElement(By.xpath("//*/tbody/tr[1]/td[1]/span")).getAttribute("innerHTML")
    		mainKey = driver.findElement(By.xpath("//*/tbody/tr["+i+"]/td[1]/span")).getAttribute("innerHTML").trim();
    		//mainKey = mainKey.trim();
    		
    		for (int j = 2; j <=totalCols; j++) {
					String subKey = driver.findElement(By.xpath("//*/thead/tr/th[" + j + "]")).getText().trim();
					String subValue;
						if (j!=12) {
							
							subValue = driver.findElement(By.xpath("//*/tbody/tr[" + i + "]/td[" + j + "]/span")).getAttribute("innerHTML").trim();
						}
						else {
							subValue = driver.findElement(By.xpath("//*/tbody/tr["+i+"]/td[12]/input")).getAttribute("value");
						}
					itemSubdetails.put(subKey, subValue);
			}
    		itemDetails.put(mainKey, itemSubdetails);
    	}
    	//System.out.println(itemDetails);
    	log.info("Fetching item Name and all the details for the item from the table");
    	return itemDetails ;
    }
    
   //this method is used to create random numbers to be used in invoice
  //Created By Chethan K Bidare on 28-01-19
    
   public String getInvoiceNum() {
	   Random random = new Random();
	   int invNo = random.nextInt(50000);
	   String invNumber = String.valueOf(invNo);
	   log.info("Generating random Number for Invoice Number");
	   return invNumber ;
   }
    
   //This method is used to Calculate the sum of important fields(total item rate, total disc etc) for the N number of rows in item list table
   //created by Chethan Bidare on 28-01-19
   
    public HashMap<String,Double> getCalculatedFooterDetailsFromItemTable() {
    	HashMap<String, Double> calculatedFooterDetails = new HashMap<String, Double>();
    	//Set<String> items = getItemDetailsFromPurchaseEntry().keySet();
    	
    	double totalItemDiscAmt = 0;
    	double totalTaxAmt = 0;
    	double totalinvoiceAmt = 0;
    	double totalSchDisc = 0;
    	double totalGrossAmount = 0;
    	HashMap<String, HashMap<String, Object>> itemDetailsFromPurchaseEntry = getItemDetailsFromPurchaseEntry();
    	Set<String> items = itemDetailsFromPurchaseEntry.keySet();
    	for(String itemName : items) {
    		totalItemDiscAmt+=Double.valueOf(String.valueOf(itemDetailsFromPurchaseEntry.get(itemName).get("Item Disc Amt")));
    		totalTaxAmt+=Double.valueOf(String.valueOf(itemDetailsFromPurchaseEntry.get(itemName).get("Total.Tax Amt")));
    		totalinvoiceAmt+=Double.valueOf(String.valueOf(itemDetailsFromPurchaseEntry.get(itemName).get("NetAmount")));
    		totalSchDisc+=Double.valueOf(String.valueOf(itemDetailsFromPurchaseEntry.get(itemName).get("Sch Disc Amt")));
    		totalGrossAmount+=Double.valueOf(String.valueOf(itemDetailsFromPurchaseEntry.get(itemName).get("Gross Amt")));
    	}
    	
    	//xtraDiscPer = Double.valueOf(this.xtraDiscPer.getAttribute("value"));
    	
    	 
    	
    	totalGrossAmount = totalGrossAmount - totalItemDiscAmt - totalSchDisc ;
    	
 
    	calculatedFooterDetails.put("Total Item Disc.",totalItemDiscAmt);
    	calculatedFooterDetails.put("Total Tax",totalTaxAmt);
    	calculatedFooterDetails.put("Total Invoice Amt",totalinvoiceAmt);
    	calculatedFooterDetails.put("Total Sch Disc",totalSchDisc);
    	calculatedFooterDetails.put("Total Gross Amount",totalGrossAmount);
    	log.info("Calculating the sum of all the item rows ");
    	
    	return calculatedFooterDetails ;
    }
    
    //This method will get the footer details(Total Inv Amt,Total Sch Disc,Total tax etc) from the Pur Entry Page
    //Created by Chethan Bidare on 28-01-19
    
    public HashMap<String,Double> getFooterDetails() {
    	List<WebElement> list = driver.findElements(By.xpath("//*[@class='invoicemap-footer-formdiv-flexrow-item right']/div"));
    	List<WebElement> listValues = driver.findElements(By.xpath("//*[@class='invoicemap-footer-formdiv-flexrow-item right']/input"));
    	String key = null ;
    	double value = 0 ;
    	HashMap<String, Double> footerDetails = new HashMap<String, Double>();
    	for(int i=0,j=0; i<list.size() && j<listValues.size(); i++,j++) {
    		//key = list.get(i).getText().trim();
    		key = list.get(i).getAttribute("innerHTML").trim();
    		if(key.equals("")) {
    			key="test" ;
    			value = 0.0 ;
    		}
    		
    		else {
    			value = Double.valueOf(listValues.get(j).getAttribute("value").trim());
    		}
    		
    		
    		footerDetails.put(key, value);
    		//System.out.println(footerDetails);
    	}
    	log.info("Footer details are stored in hashmap and can be fetched as per the key value");
    	return footerDetails ;
    }
   
   //this method is used to calculate the sum of item discount for all the items
  //Created By Chethan K Bidare on 28-01-19
    
    public double calculateSumOfDiscAmount() {
    	HashMap<String, HashMap<String, Object>> itemDetails = getItemDetailsFromPurchaseEntry();
    	Set<String> itemNames = itemDetails.keySet();
    	double discAmt = 0 ;
    	for(String item : itemNames) {
    		discAmt+=Double.valueOf(String.valueOf(itemDetails.get(item).get("Item Disc Amt")));
    	}
    	return discAmt ;
    }
  
    
    
    
    
    //this method is used to calculate all the formulaes in Pur Entry screen
    //Created By Chethan K Bidare on 28-01-19
    
	public HashMap<String,HashMap<String,Double>> getCalculatedValuesItemwise() {
		HashMap<String, HashMap<String, Object>> itemDetails = getItemDetailsFromPurchaseEntry();
		Set<String> itemNames = itemDetails.keySet();
		double purRate ;
		double mrp ;
		int qty;
		int schQty;
		double totalQty ;
		int discPer ;
		int schDiscPer ;
		double schDisc ;
		double schDiscAmt ;
		double itemDiscAmt ;
		double grossAmt ;
		int totalTaxPer ;
		double purchaseRateAfterScheme ;
		double purchaseRateAfterDiscount ;
		double effPurRate ;
		double xtraDisPer ;
		double taxAmt ;
		double abatedMRP ;
		double margin ;
		double cgstAmt ;
		double sgstAmt ;
		double igstAmt ;
		
		HashMap<String, HashMap<String,Double>> calculatedItemDetails = new HashMap<String, HashMap<String,Double>>();
		HashMap<String, Double> calculatedDetails = new HashMap<String,Double>();
		
		for(String item : itemNames) {
			purRate = Double.valueOf(String.valueOf(itemDetails.get(item).get("Pur. Rt")));
			mrp = Double.valueOf(String.valueOf(itemDetails.get(item).get("MRP")));
			qty = Integer.valueOf(String.valueOf(itemDetails.get(item).get("Qty")));
			schQty=Integer.valueOf(String.valueOf(itemDetails.get(item).get("Sch. Qty")));
			totalQty = qty + schQty ;
			discPer = Integer.valueOf(String.valueOf(itemDetails.get(item).get("D %")));
			schDiscPer =Integer.valueOf(String.valueOf(itemDetails.get(item).get("Sch Disc %")));
			schDisc = 0.0 ;
			schDisc = purRate - ((purRate * qty )/totalQty);
			if(schDiscPer > 0 && schQty==0) {
				schDisc = (schDiscPer / 100.0) * purRate ;
			}
			schDiscAmt = schDisc * totalQty ;
			schDiscAmt = schDiscAmt - (schDiscAmt * discPer / 100.0);
			itemDiscAmt = (discPer / 100.0) * purRate * totalQty ;	
			grossAmt = purRate * totalQty ;
			totalTaxPer = Integer.valueOf(String.valueOf(itemDetails.get(item).get("CSGT%"))) + Integer.valueOf(String.valueOf(itemDetails.get(item).get("SGST%"))) ;
			
			if(schDisc!=0.0) {
				purchaseRateAfterScheme = purRate - schDisc ;
			}
			else {
				purchaseRateAfterScheme = purRate ;
			}
			purchaseRateAfterDiscount = purchaseRateAfterScheme - (purchaseRateAfterScheme * discPer / 100.0 );
			effPurRate = purchaseRateAfterDiscount ;
			xtraDisPer = getFooterDetails().get("Xtra Dis%");
			if(xtraDisPer!=0) {
				effPurRate = effPurRate - ( effPurRate * xtraDisPer/100.0) ;
			}
			taxAmt = effPurRate * qty * totalTaxPer / 100.0 ;
			abatedMRP = mrp / (1 + totalTaxPer / 100.0);
			margin = (1 - (effPurRate/abatedMRP)) * 100.0 ;
			cgstAmt = (effPurRate * totalQty * Integer.valueOf(String.valueOf(itemDetails.get(item).get("CSGT%")))) / 100.0 ;
			sgstAmt = (effPurRate * totalQty * Integer.valueOf(String.valueOf(itemDetails.get(item).get("SGST%")))) / 100.0 ;
			igstAmt = (effPurRate * totalQty * Integer.valueOf(String.valueOf(itemDetails.get(item).get("IGST%")))) / 100.0 ;
			calculatedDetails.put("Total Qty", totalQty);
			calculatedDetails.put("Sch Disc Amt", schDiscAmt);
			calculatedDetails.put("Item Disc Amt", itemDiscAmt);
			calculatedDetails.put("Gross Amt", grossAmt);
			calculatedDetails.put("Pur.Rt. Aft.Sch", purchaseRateAfterScheme);
			calculatedDetails.put("Pur.Rt Aft Sch Aft Dis", purchaseRateAfterDiscount);
			calculatedDetails.put("EPR", effPurRate);
			calculatedDetails.put("Total.Tax Amt", taxAmt);
			calculatedDetails.put("Abt MRP", abatedMRP);
			calculatedDetails.put("Margin%", margin);
			calculatedDetails.put("CGST", cgstAmt);
			calculatedDetails.put("SGST", sgstAmt);
			calculatedDetails.put("IGST", igstAmt);
			calculatedItemDetails.put(item, calculatedDetails);
		}
		
		return calculatedItemDetails ;
		
	}
	
	
	public void clickOnPendingEntriesBasedOnInvoiceNumber(String invoiceNumber) {
		int totalRows = driver.findElements(By.xpath("//*[@class='purchase-rTable']/div")).size();
		for(int i=2; i<=totalRows; i++) {
				String invNum = driver.findElement(By.xpath("//*[@class='purchase-rTable']/div["+i+"]/div[5]")).getText().trim();
				if(invNum.equals(invoiceNumber)) {
					driver.findElement(By.xpath("//*[@class='purchase-rTable']/div["+i+"]/div[5]")).click();
					log.info("Clicked on the invoice number "+invoiceNumber+" in pending entries page of purchase menu");
					break ;
				}
			}
	}
	
	
	public ArrayList<String> fetchDatesFromPendingEntriesPage() {
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//*[@class='purchase-rTable']/div"))));
		int totalRows = driver.findElements(By.xpath("//*[@class='purchase-rTable']/div")).size();
		ArrayList<String> datesOfPendingEntries = new ArrayList<String>();
		for(int i=2; i<=totalRows; i++) {
			
			try {
				String orderID = driver.findElement(By.xpath("//*[@class='purchase-rTable']/div["+i+"]/div[9]")).getText();
				orderID = orderID.substring(0,8).trim();
				datesOfPendingEntries.add(orderID);
			} catch (Exception e) {
				log.info("NO order IDs Exist for the date");
			}
		}
		//Collections.sort(gatePassIDs);
		log.info("Fetching all the Dates from the Pending Entries page");
		return datesOfPendingEntries ;
	}
	
	
	public ArrayList<String> fetchInvoiceNumbersFromPendingEntriesPage() {
		int totalRows = driver.findElements(By.xpath("//*[@class='purchase-rTable']/div")).size();
		ArrayList<String> values = new ArrayList<String>();
		for(int i=2; i<=totalRows; i++) {
					values.add(driver.findElement(By.xpath("//*[@class='purchase-rTable']/div["+i+"]/div[5]")).getText());
			}
		return values ;
		}
	
	public void clickOnItemToGetFocus() {
		try {
			driver.findElement(By.xpath("//tbody/tr[1]/td[2]")).click();
		} catch (Exception e) {
			System.out.println("FOCUS ON ITEM IS NOT HAPPENING ");
		}
	}
	
	
	public void enableCreditAmountSlider() {
		wait.until(ExpectedConditions.elementToBeClickable(creditAmtSlider));
		creditAmtSlider.click();
	}
	
	public void disableCreditAmountSlider() {
		wait.until(ExpectedConditions.elementToBeClickable(creditAmtSlider));
		creditAmtSlider.click();
	}
	
    public void enterCreditAmount(String creditAmount) {
    	wait.until(ExpectedConditions.elementToBeClickable(CreditAmount));
    	CreditAmount.clear();
    	CreditAmount.sendKeys(creditAmount);
    }
    
    
    public void enableRaiseIssueSlider() {
    	wait.until(ExpectedConditions.elementToBeClickable(RaiseIssueSlider));
    	RaiseIssueSlider.click();
    }
    
    public void disableRaiseIssueSlider() {
    	wait.until(ExpectedConditions.elementToBeClickable(RaiseIssueSlider));
    	RaiseIssueSlider.click();
    }
    
    
    public void selectReasonForReturnFromRaiseIssue(String reason) throws InterruptedException {
    	wait.until(ExpectedConditions.elementToBeClickable(SelectReasonForRaiseIssue));
    	SelectReasonForRaiseIssue.click();
    	Thread.sleep(1000);
    	List<WebElement> reasons = driver.findElements(By.tagName("li"));
    	for(WebElement we : reasons) {
    		if(we.getText().trim().equalsIgnoreCase(reason)) {
    			we.click();
    			break ;
    		}
    	}
    }
    
    public void selectQTYForReturnFromRaiseIssue(String quantity) throws InterruptedException {
    	wait.until(ExpectedConditions.elementToBeClickable(SelectQtyForRaiseIssue));
    	SelectQtyForRaiseIssue.click();
    	Thread.sleep(1000);
    	List<WebElement> qty = driver.findElements(By.tagName("li"));
    	for(WebElement we : qty) {
    		if(we.getText().trim().equalsIgnoreCase(quantity)) {
    			we.click();
    			break ;
    		}
    	}
    }
    
    public void clickOnAddNewLink() {
    	wait.until(ExpectedConditions.elementToBeClickable(AddNewToSelectReason));
    	AddNewToSelectReason.click();
    }
    
    public void clickOnRaiseIssueContinueButton() {
    	wait.until(ExpectedConditions.elementToBeClickable(RaiseIssueContinueButton));
    	RaiseIssueContinueButton.click();
    }
    
    public Hashtable<String,Hashtable<String,String>> getItemDetailsFromIssueBucket() {
    	Hashtable<String, Hashtable<String,String>> issueItemDetails = new Hashtable<String, Hashtable<String,String>>();
    	
    	List<WebElement> numberOfItems = driver.findElements(By.xpath("//div[@class='issuebucket-if-table']/table/tbody/tr"));
    	
    	for(int i=1; i<= numberOfItems.size(); i++) {
    		String itemName = driver.findElement(By.xpath("//div[@class='issuebucket-if-table']/table/tbody/tr["+i+"]/td[1]")).getText().trim();
    		
    		List<WebElement> totalItemDetails = driver.findElements(By.xpath("//div[@class='issuebucket-if-table']/table/tbody/tr["+i+"]/td"));
    		Hashtable<String, String> itemSubDetails = new Hashtable<>();
    		for(int j=2; j<= totalItemDetails.size(); j++) {
    			String itemSubDetailsHeader = driver.findElement(By.xpath("//div[@class='issuebucket-if-table']/table/thead/tr/th["+j+"]")).getText().trim();
    			String itemSubDetailsValue = driver.findElement(By.xpath("//div[@class='issuebucket-if-table']/table/tbody/tr["+i+"]/td["+j+"]")).getText().trim();
    			itemSubDetails.put(itemSubDetailsHeader, itemSubDetailsValue);
    		}
    		issueItemDetails.put(itemName, itemSubDetails);
    	}
    	
    	return issueItemDetails ;
    	
    }
    
  
    public String getRaiseIssueItemName() {
    	wait.until(ExpectedConditions.visibilityOf(RaiseIssueItemName));
    	return RaiseIssueItemName.getText().trim();
    }
    
    public String getRaiseIssueItemBatchName() {
    	wait.until(ExpectedConditions.visibilityOf(RaiseIssueItemBatch));
    	return RaiseIssueItemBatch.getText().trim();
    }
    
    public void clickOnSaveButton() {
    	wait.until(ExpectedConditions.elementToBeClickable(SaveButton));
    	SaveButton.click();
    }
    
    
    
    
    
    
    
    
 }




	

	

