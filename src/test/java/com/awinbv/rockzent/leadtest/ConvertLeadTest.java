package com.awinbv.rockzent.leadtest;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.awinbv.rockzent.generalUtility.BaseClass;
import com.awinbv.rockzent.generalUtility.RetryAnalyzer;
import com.awinbv.rockzent.pomrepositorylib.CreateNewLeadPage;
import com.awinbv.rockzent.pomrepositorylib.HomePage;
import com.awinbv.rockzent.pomrepositorylib.LeadInfoPage;
import com.awinbv.rockzent.pomrepositorylib.LeadsPage;

@Listeners(com.awinbv.rockzent.generalUtility.LisImplementClass.class)
public class ConvertLeadTest extends BaseClass {
	
	/*TC_22*/
	@Test(groups = { "regressionTest" } , retryAnalyzer = RetryAnalyzer.class )
	public void convertLeadBySelectingAllCheckboxTest() throws Throwable {
		/* Test script data */
		String partialHomeTitle = eLib.getDataFromExcel("LeadsModule", 10, 2);
		String lastName = eLib.getDataFromExcel("LeadsModule", 10, 3)+randumNum;
		String companyName = eLib.getDataFromExcel("LeadsModule", 10, 4);
		String closeDate = eLib.getDataFromExcel("LeadsModule", 10, 5);
		String expectedOrgTitle = eLib.getDataFromExcel("LeadsModule", 10, 6);
		
		wLib.waitForTitleVisibility(driver);
		boolean title = driver.getTitle().contains(partialHomeTitle);
		Assert.assertTrue(title, "Home page is not displayed,");
		Reporter.log("PASS: Home page is displayed");
		
		/*Click on "Lead" and select a lead*/
		HomePage homePage = new HomePage(driver);
		homePage.getLeadsLink().click();
		
		/*precondition*/
		LeadsPage leadPage = new LeadsPage(driver);
		leadPage.getCreateLeadImg().click();;
		CreateNewLeadPage createLead = new CreateNewLeadPage(driver);
		createLead.createNewLead(lastName, companyName);
		
		LeadInfoPage infoPage = new LeadInfoPage(driver);
		String updatedLastName = infoPage.getLastName().getText();
		
		homePage.getLeadsLink().click();
		leadPage.selectALead(updatedLastName);
		
		boolean isLeadInfoPage = infoPage.getSuccessfulMsg().getText().contains(updatedLastName);
		Assert.assertTrue(isLeadInfoPage, "LeadInfoPage is not displayed,");
		Reporter.log("PASS: LeadInfoPage is displayed");
		
		/*Goto "Action" and click on "Convert Lead" link*/
		infoPage.getConvertLeadLink().click();
		boolean isConvertLeadPage = infoPage.getConvertLeadHeader().isDisplayed();
		Assert.assertTrue(isConvertLeadPage, "Convert Lead Page is not displayed,");
		Reporter.log("PASS: Convert Lead Page is displayed");
		
		/*Select All the checkbok and fill all the mandatory fields and click on "Save"*/
		infoPage.getOpportunityCheckBox().click();
		infoPage.getOpportunityCloseDate().sendKeys(closeDate);
		infoPage.getConvertLeadSaveBtn().click();
		Assert.assertTrue(driver.getTitle().contains(expectedOrgTitle), "Organization Page is not displayed,");
		Reporter.log("PASS: Organization Page is displayed");
	}
	
	/*TC_23*/
	@Test(groups = { "regressionTest" } , retryAnalyzer = RetryAnalyzer.class )
	public void convertLeadBySelectingOnlyOrgCheckboxTest() throws Throwable {
		/* Test script data */
		String partialHomeTitle = eLib.getDataFromExcel("LeadsModule", 13, 2);
		String lastName = eLib.getDataFromExcel("LeadsModule", 13, 3)+randumNum;
		String companyName = eLib.getDataFromExcel("LeadsModule", 13, 4);
		String expectedAlertText = eLib.getDataFromExcel("LeadsModule", 13, 5);
		
		wLib.waitForTitleVisibility(driver);
		boolean title = driver.getTitle().contains(partialHomeTitle);
		Assert.assertTrue(title, "Home page is not displayed,");
		Reporter.log("PASS: Home page is displayed");
		
		/*Click on "Lead" and select a lead*/
		HomePage homePage = new HomePage(driver);
		homePage.getLeadsLink().click();
		
		/*precondition*/
		LeadsPage leadPage = new LeadsPage(driver);
		leadPage.getCreateLeadImg().click();;
		CreateNewLeadPage createLead = new CreateNewLeadPage(driver);
		createLead.createNewLead(lastName, companyName);
		
		LeadInfoPage infoPage = new LeadInfoPage(driver);
		String updatedLastName = infoPage.getLastName().getText();
		
		homePage.getLeadsLink().click();
		leadPage.selectALead(updatedLastName);
		
		boolean isLeadInfoPage = infoPage.getSuccessfulMsg().getText().contains(updatedLastName);
		Assert.assertTrue(isLeadInfoPage, "LeadInfoPage is not displayed,");
		Reporter.log("PASS: LeadInfoPage is displayed");
		
		/*Goto "Action" and click on "Convert Lead" link*/
		infoPage.getConvertLeadLink().click();
		boolean isConvertLeadPage = infoPage.getConvertLeadHeader().isDisplayed();
		Assert.assertTrue(isConvertLeadPage, "Convert Lead Page is not displayed,");
		Reporter.log("PASS: Convert Lead Page is displayed");
		
		/*Select only "Organisation" checkbok and fill all the mandatory fielsand click on "Save"*/
		if(!infoPage.getOrganizationsCheckBox().isSelected()) {
			infoPage.getOrganizationsCheckBox().click();
		}
		if(infoPage.getOpportunityCheckBox().isSelected()) {
			infoPage.getOpportunityCheckBox().click();
		}
		if(infoPage.getContactCheckBox().isSelected()) {
			infoPage.getContactCheckBox().click();
		}
		infoPage.getConvertLeadSaveBtn().click();
		
		try {
			boolean isAlertTextCorrect = wLib.getAlertText(driver).contains(expectedAlertText);
			Assert.assertTrue(isAlertTextCorrect, "\"Contact should be selected to transfer related records \" message is not displayed,");
			Reporter.log("PASS: \"Contact should be selected to transfer related records \" message is displayed");
			wLib.acceptAlert(driver);
		} catch(Exception e) {
			Assert.assertTrue(false);
		}
	}
	
	/*TC_24*/
	@Test(groups = { "regressionTest" } , retryAnalyzer = RetryAnalyzer.class )
	public void convertLeadBySelectingOnlyOpportunityCheckboxTest() throws Throwable {
		/* Test script data */
		String partialHomeTitle = eLib.getDataFromExcel("LeadsModule", 16, 2);
		String lastName = eLib.getDataFromExcel("LeadsModule", 16, 3)+randumNum;
		String companyName = eLib.getDataFromExcel("LeadsModule", 16, 4);
		String closeDate = eLib.getDataFromExcel("LeadsModule", 16, 5);
		String expectedAlertText = eLib.getDataFromExcel("LeadsModule", 16, 6);
		
		wLib.waitForTitleVisibility(driver);
		boolean title = driver.getTitle().contains(partialHomeTitle);
		Assert.assertTrue(title, "Home page is not displayed,");
		Reporter.log("PASS: Home page is displayed");
		
		/*Click on "Lead" and select a lead*/
		HomePage homePage = new HomePage(driver);
		homePage.getLeadsLink().click();
		
		/*precondition*/
		LeadsPage leadPage = new LeadsPage(driver);
		leadPage.getCreateLeadImg().click();
		CreateNewLeadPage createLead = new CreateNewLeadPage(driver);
		createLead.createNewLead(lastName, companyName);
		
		LeadInfoPage infoPage = new LeadInfoPage(driver);
		String updatedLastName = infoPage.getLastName().getText();
		
		homePage.getLeadsLink().click();
		leadPage.selectALead(updatedLastName);
		
		boolean isLeadInfoPage = infoPage.getSuccessfulMsg().getText().contains(updatedLastName);
		Assert.assertTrue(isLeadInfoPage, "LeadInfoPage is not displayed,");
		Reporter.log("PASS: LeadInfoPage is displayed");
		
		/*Goto "Action" and click on "Convert Lead" link*/
		infoPage.getConvertLeadLink().click();
		boolean isConvertLeadPage = infoPage.getConvertLeadHeader().isDisplayed();
		Assert.assertTrue(isConvertLeadPage, "Convert Lead Page is not displayed,");
		Reporter.log("PASS: Convert Lead Page is displayed");
		
		/*Select only "Opporunity" checkbok and fill all the mandatory fielsand click on "Save"*/
		if(infoPage.getOrganizationsCheckBox().isSelected()) {
			infoPage.getOrganizationsCheckBox().click();
		}
		if(!infoPage.getOpportunityCheckBox().isSelected()) {
			infoPage.getOpportunityCheckBox().click();
			infoPage.getOpportunityCloseDate().sendKeys(closeDate);
		}
		if(infoPage.getContactCheckBox().isSelected()) {
			infoPage.getContactCheckBox().click();
		}
		
		infoPage.getConvertLeadSaveBtn().click();
		
		try {
			boolean isAlertTextCorrect = wLib.getAlertText(driver).contains(expectedAlertText);
			Assert.assertTrue(isAlertTextCorrect, "\"Select either Organization or Contact to convert the lead\" message is not displayed,");
			Reporter.log("PASS: \"Select either Organization or Contact to convert the lead\" message is displayed");
			wLib.acceptAlert(driver);
		} catch(Exception e) {
			Assert.assertTrue(false);
		}
	}
}