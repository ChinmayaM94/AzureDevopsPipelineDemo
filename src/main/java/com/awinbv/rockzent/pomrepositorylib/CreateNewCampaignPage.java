package com.awinbv.rockzent.pomrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewCampaignPage {
	WebDriver driver;
	
	public CreateNewCampaignPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement newCampaignsText;
	
	@FindBy(name = "campaignname")
	private WebElement campaignNameTF;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	public WebElement getNewCampaignsText() {
		return newCampaignsText;
	}

	public WebElement getCampaignNameTF() {
		return campaignNameTF;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public void createCampaign(String campaignName) {
		campaignNameTF.sendKeys(campaignName);
		saveBtn.click();
	}

}
