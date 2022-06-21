package com.awinbv.rockzent.pomrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.awinbv.rockzent.generalUtility.WebDriverUtility;

public class HomePage extends WebDriverUtility {
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText = "Leads")
	private WebElement leadsLink;
	
	@FindBy(linkText = "Organizations")
	private WebElement organizationsLink;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactsLink;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement administratorLink;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signOutLink;
	
	@FindBy(xpath = "//a[text()='More']")
	private WebElement moreLink;
	
	@FindBy(name = "Campaigns")
	private WebElement campaignsLink;
	
	public WebElement getLeadsLink() {
		return leadsLink;
	}

	public WebElement getOrganizationsLink() {
		return organizationsLink;
	}

	public WebElement getContactsLink() {
		return contactsLink;
	}
	
	public WebElement getAdministratorLink() {
		return administratorLink;
	}
	
	public WebElement getSignOutLink() {
		return signOutLink;
	}
	
	public WebElement getMoreLink() {
		return moreLink;
	}

	public WebElement getCampaignsLink() {
		return campaignsLink;
	}

	public void signOut() {
		mouseHover(driver, administratorLink);
		signOutLink.click();
	}

}
