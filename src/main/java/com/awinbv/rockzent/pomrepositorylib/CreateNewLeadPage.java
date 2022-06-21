package com.awinbv.rockzent.pomrepositorylib;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.awinbv.rockzent.generalUtility.WebDriverUtility;

public class CreateNewLeadPage extends WebDriverUtility {
	WebDriver driver;
	
	public CreateNewLeadPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "lastname")
	private WebElement lastNameEdt;
	
	@FindBy(name = "company")
	private WebElement companyEdt;
	
	@FindBy(xpath = "//input[@value='T']")
	private WebElement groupRadioBtn;
	
	@FindBy(xpath = "//input[@value='U']")
	private WebElement userRadioBtn;
	
	@FindBy(name = "assigned_user_id")
	private WebElement userDropDownList;
	
	@FindBy(name = "assigned_group_id")
	private WebElement groupDropDownList;
	
	@FindBy(name = "annualrevenue")
	private WebElement annualRevenueEdt;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement createNewLeadHeader;
	
	public WebElement getCreateNewLeadHeader() {
		return createNewLeadHeader;
	}

	public WebElement getLastNameEdt() {
		return lastNameEdt;
	}

	public WebElement getCompanyEdt() {
		return companyEdt;
	}

	public WebElement getGroupRadioBtn() {
		return groupRadioBtn;
	}

	public WebElement getUserRadioBtn() {
		return userRadioBtn;
	}

	public WebElement getUserDropDownList() {
		return userDropDownList;
	}

	public WebElement getGroupDropDownList() {
		return groupDropDownList;
	}

	public WebElement getAnnualRevenueEdt() {
		return annualRevenueEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public boolean clickOnAssignedToRadioBtn(List<String> dropDownOptions, List<WebElement> options) {
		List<String> allOptions = new ArrayList<String>();
		for(WebElement ele:options) {
			allOptions.add(ele.getText());
		}
		if(allOptions.equals(dropDownOptions)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void createNewLead(String lastName, String company) {
		lastNameEdt.sendKeys(lastName);
		companyEdt.sendKeys(company);
		saveBtn.click();
	}
	
	public void createNewLeadByAnnualRevenue(String lastName, String company, String annualRevenue) {
		lastNameEdt.sendKeys(lastName);
		companyEdt.sendKeys(company);
		annualRevenueEdt.clear();
		annualRevenueEdt.sendKeys(annualRevenue);
		saveBtn.click();
	}

	

}
