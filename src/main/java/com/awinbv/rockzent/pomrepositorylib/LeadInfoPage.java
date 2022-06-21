package com.awinbv.rockzent.pomrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadInfoPage {
	WebDriver driver;
	
	public LeadInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement successfulMsg;
	
	@FindBy(xpath = "//td[text()='Lead No']/following-sibling::td[@class='dvtCellInfo']")
	private WebElement uniqueLeadNo;
	
	@FindBy(id = "dtlview_Last Name")
	private WebElement lastName;
	
	@FindBy(linkText = "Convert Lead")
	private WebElement convertLeadLink;
	
	@FindBy(id = "select_potential")
	private WebElement opportunityCheckBox;
	
	@FindBy(name = "closingdate")
	private WebElement opportunityCloseDate;
	
	@FindBy(name = "Save")
	private WebElement convertLeadSaveBtn;
	
	@FindBy(id = "select_contact")
	private WebElement contactCheckBox;
	
	@FindBy(id = "select_account")
	private WebElement organizationsCheckBox;
	
	public WebElement getContactCheckBox() {
		return contactCheckBox;
	}

	public WebElement getOrganizationsCheckBox() {
		return organizationsCheckBox;
	}

	public WebElement getConvertLeadSaveBtn() {
		return convertLeadSaveBtn;
	}

	public WebElement getOpportunityCheckBox() {
		return opportunityCheckBox;
	}

	public WebElement getOpportunityCloseDate() {
		return opportunityCloseDate;
	}

	@FindBy(xpath = "//img[@src='themes/images/Leads.gif']/parent::td[@class='genHeaderSmall']")
	private WebElement convertLeadHeader;
	
	public WebElement getConvertLeadHeader() {
		return convertLeadHeader;
	}

	public WebElement getConvertLeadLink() {
		return convertLeadLink;
	}

	public WebElement getLastName() {
		return lastName;
	}

	public WebElement getSuccessfulMsg() {
		return successfulMsg;
	}

	public WebElement getUniqueLeadNo() {
		return uniqueLeadNo;
	}
	
}
