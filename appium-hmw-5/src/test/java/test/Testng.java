package test;

import device.Devices;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AddContact;
import pages.HomePage;
import utility.DeviceUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Testng {

    public static AppiumDriver<?> Driver;
    HomePage homePage;
    AddContact addContactPage;
    String oreo, kitkat;
    DesiredCapabilities capabilities;

    public Testng() {
        oreo = Devices.ANDROID_OREO.path;
        kitkat = Devices.ANDROID_KITKAT.path;
    }

    @BeforeClass
    public void setup() throws MalformedURLException {

        capabilities = new DesiredCapabilities();
        capabilities = DeviceUtils.pathToDesiredCapabilitites(this.oreo);
        capabilities.setCapability("app", new File("src/test/resources/apps/ContactManager.apk").getAbsolutePath());
        Driver = new AndroidDriver(new URL("http://127.0.0.1:8080/wd/hub"), capabilities);
        homePage = new HomePage();
        addContactPage = new AddContact();
        Driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
    }

    @Test(priority = 0)
    public void openAddContactOnOreo() throws NullPointerException, InterruptedException {

        homePage.getAddContactBtn().click();
        Thread.sleep(4000);
    }


    //User checks title whether it is "Add Contact"
    @Test(priority = 1)
    public void checkAddContactTitle() {
        Assert.assertEquals(addContactPage.getTitle().getText(), "Add Contact");
    }

    //User adds work account succesfully
    @Test(priority = 2)
    public void addWorkAccount() {
        try {
            addContactPage.getContactNameField().click();
            addContactPage.getContactNameField().sendKeys("work name");
            addContactPage.getContactEmailField().click();
            addContactPage.getContactEmailField().sendKeys("Workphone@phone.com");
            addContactPage.getContactPhoneField().click();
            addContactPage.getContactPhoneField().sendKeys("05555555555");
            addContactPage.getContactPhoneDrop().click();
            addContactPage.getContactPhoneDrop().sendKeys(Keys.ARROW_DOWN);
            addContactPage.getContactPhoneDrop().click();
        } catch (Exception e) {
            System.out.println("Something went wrong when try add work account" + e);
        }

    }

    //User check the account whether it is correct
    @Test(priority = 3)
    public void checkWorkAccountIsCorrect() {
        try {
            Assert.assertEquals(addContactPage.getContactNameField().getText(), "work name");
        } catch (Exception e) {
            System.out.println("Something went wrong when check work account" + e);
        }
    }

    //User adds a mobile account successfully
    @Test(priority = 4)
    public void addMobileAccount() {
        try {
            addContactPage.getContactNameField().click();
            addContactPage.getContactNameField().sendKeys("mobile name");
            addContactPage.getContactEmailField().click();
            addContactPage.getContactEmailField().sendKeys("mobilephone@phone.com");
            addContactPage.getContactPhoneField().click();
            addContactPage.getContactPhoneField().sendKeys("04444444444");
            addContactPage.getContactPhoneDrop().click();
            addContactPage.getContactPhoneDrop().sendKeys(Keys.ARROW_DOWN);
            addContactPage.getContactPhoneDrop().sendKeys(Keys.ARROW_DOWN);
            addContactPage.getContactPhoneDrop().click();
        } catch (Exception e) {
            System.out.println("Something went wrong when try add work account" + e);
        }

    }

    //User check the account whether it is correct
    @Test(priority = 5)
    public void checkMobileAccountIsCorrect() {
        try {
            Assert.assertEquals(addContactPage.getContactNameField().getText(), "mobile name");
        } catch (Exception e) {
            System.out.println("Something went wrong when check work account" + e);
        }
    }

    //User checks number limit for contact phone field
    @Test(priority = 6)
    public void checkNumberLimit() {
        try {
            addContactPage.getContactPhoneField().click();
            addContactPage.getContactPhoneField().sendKeys("9999999999999999999");
        } catch (Exception e) {
            System.out.println("Something went wrong when try check number limit" + e);
        }

    }

    //User checks email format for contact email field
    @Test(priority = 7)
    public void checkEmailFormat() {
        try {

            addContactPage.getContactEmailField().click();
            addContactPage.getContactEmailField().sendKeys("email@email.com");
            String email = String.valueOf(addContactPage.getContactEmailField().getText().contains("@"));
            Boolean include = false;

            if (email == addContactPage.getContactEmailField().getText()) {
                include = true;


            } else {
                include = false;
            }

        } catch (Exception e) {
            System.out.println("Something went wrong when try check number limit" + e);
        }

    }

    //User adds a home acocunt successfully
    @Test(priority = 8)
    public void addHomeAccount() {
        try {
            addContactPage.getContactNameField().click();
            addContactPage.getContactNameField().sendKeys("home name");
            addContactPage.getContactEmailField().click();
            addContactPage.getContactEmailField().sendKeys("homephone@phone.com");
            addContactPage.getContactPhoneField().click();
            addContactPage.getContactPhoneField().sendKeys("03333333334");
            addContactPage.getContactPhoneDrop().click();
            addContactPage.getContactPhoneDrop().click();
        } catch (Exception e) {
            System.out.println("Something went wrong when try add home account" + e);
        }

    }

    //User adds a home account with home email successfully
    @Test(priority = 9)
    public void addHomeEmail() {
        try {
            addContactPage.getContactNameField().click();
            addContactPage.getContactNameField().sendKeys("home name");
            addContactPage.getContactEmailField().click();
            addContactPage.getContactEmailField().sendKeys("homephone@phone.com");
            addContactPage.getContactEmailDrop().click();
            addContactPage.getContactPhoneField().click();
            addContactPage.getContactPhoneField().sendKeys("03333322334");
            addContactPage.getContactPhoneDrop().click();
            addContactPage.getContactPhoneDrop().click();
        } catch (Exception e) {
            System.out.println("Something went wrong when try add home account" + e);
        }

    }

    //User checks whether contact name accepts number and digit
    @Test(priority = 10)
    public void checkNameAcceptDigits() {
        try {
            addContactPage.getContactNameField().click();
            addContactPage.getContactNameField().sendKeys("90977^+//(&()_?)_)/é!'");
            addContactPage.getContactEmailField().click();
            addContactPage.getContactEmailField().sendKeys("homephone@phone.com");
            addContactPage.getContactEmailDrop().click();
            addContactPage.getContactPhoneField().click();
            addContactPage.getContactPhoneField().sendKeys("03333322334");
            addContactPage.getContactPhoneDrop().click();
            addContactPage.getContactPhoneDrop().click();
        } catch (Exception e) {
            System.out.println("Something went wrong when try check name accept number and digits" + e);
        }

    }

    //User checks speacial characters limit for contact name
    @Test(priority = 11)
    public void checkNameLimitSpecialChars() {
        try {
            addContactPage.getContactNameField().click();
            addContactPage.getContactNameField().sendKeys("^+//(&()_?)_)/é!'");
            addContactPage.getContactEmailField().click();
            addContactPage.getContactEmailField().sendKeys("homephone@phone.com");
            addContactPage.getContactEmailDrop().click();
            addContactPage.getContactPhoneField().click();
            addContactPage.getContactPhoneField().sendKeys("03333322334");
            addContactPage.getContactPhoneDrop().click();
            addContactPage.getContactPhoneDrop().click();
        } catch (Exception e) {
            System.out.println("Something went wrong when try check special characters" + e);
        }

    }


    //User checks required fields for add account page
    @Test(priority = 12)
    public void checkRequiredFields() {
        try {

        } catch (Exception e) {
            System.out.println("Something went wrong when try check all fields" + e);
        }

    }


    @AfterClass
    public void waiter() throws InterruptedException {
        Thread.sleep(5000);
    }

}