package com.awinbv.rockzent.pomrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignsPage {
	WebDriver driver;
	
	public CampaignsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@class='hdrLink']")
	private WebElement campaignsHeader;
	
	@FindBy(xpath = "//img[@alt='Create Campaign...']")
	private WebElement createCampaignLink;

	public WebElement getCampaignsHeader() {
		return campaignsHeader;
	}

	public WebElement getCreateCampaignLink() {
		return createCampaignLink;
	}

}
