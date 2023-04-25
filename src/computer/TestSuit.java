package computer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSuit extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setup(){
        openBrowser(baseUrl);
    }
    @Test
    public void verifyProductArrangeInAlphaBaticalOrder(){
       clickOnElement(By.linkText("Computers"));
       clickOnElement(By.linkText("Desktops"));
       selectByVisibleTextFromDropDown(By.id("products-orderby"),"Name: Z to A");
        List<WebElement>beforeFilterNameZtoA = driver.findElements(By.xpath("item-grid"));
        List<Double>beforeFilterNameZtoAList =new ArrayList<>();
        for (WebElement nameZtoA : beforeFilterNameZtoA) {
            beforeFilterNameZtoAList.add(Double.valueOf(nameZtoA.getText().replace("$", "")));
        }
        // Select dropdown = new Select(driver.findElement(By.id("products-orderby")));
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='products-orderby']"),"Name: Z to A");
        List<WebElement>afterFilterNameZtoA = driver.findElements(By.xpath("item-grid"));
        List<Double> afterFilterNameZtoAList=new ArrayList<>();
        for (WebElement n : afterFilterNameZtoA) {
            afterFilterNameZtoAList.add(Double.valueOf(n.getText().replace("$","")));
        }
        Collections.sort(beforeFilterNameZtoAList);// sort the value in the list
        Assert.assertEquals(beforeFilterNameZtoAList,afterFilterNameZtoAList);
    }
    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        clickOnElement(By.linkText("Computers"));
        clickOnElement(By.linkText("Desktops"));
        selectByVisibleTextFromDropDown(By.id("products-orderby"),"Name: A to Z");
        Thread.sleep(2000);
        clickOnElement(By.xpath("//div[@class='product-item']/div[2]/div[3]/div[2]/button[1]"));
        //Verify the Text "Build your own computer"
        //verifyText("Build your own computer",By.xpath("//h1[contains(text(),'Build your own computer')]"),"Build your own computer");
        //Select "2.2 GHz Intel Pentium Dual-Core E2200" using Select class
        selectByVisibleTextFromDropDown(By.id("product_attribute_1"), "2.2 GHz Intel Pentium Dual-Core E2200");
        //Select "8GB [+$60.00]" using Select class.
        selectByVisibleTextFromDropDown(By.id("product_attribute_2"), "8GB [+$60.00]");
        //Select HDD radio "400 GB [+$100.00]"
        clickOnElement(By.xpath("//input[@id='product_attribute_3_7']"));
        //Select OS radio "Vista Premium [+$60.00]"
        clickOnElement(By.xpath("//input[@id='product_attribute_4_9']"));
        // Check Two Check boxes "Microsoft Office [+$50.00]" and "Total Commander
        clickOnElement(By.xpath("//input[@id='product_attribute_5_12']"));
        Thread.sleep(2000);
        //Verify the price "$1,475.00"
       verifyText("$1,475.00", By.xpath("//span[@id='price-value-1']"), "$1,475.00");
        // Click on "ADD TO CARD" Button.
       clickOnElement(By.xpath("//button[@id='add-to-cart-button-1']"));
        // Verify the Message "The product has been added to your shopping cart" on Top green Bar After that close the bar clicking on the cross button.
        verifyText("The product has been added to your shopping cart", By.xpath("//div[@class='bar-notification success']"), "The product has been added to your shopping cart");
        clickOnElement(By.xpath("//body/div[@id='bar-notification']/div[1]/span[1]"));
        // Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        mouseHoverAndClick(By.xpath("//li[@id='topcartlink']"));
        clickOnElement(By.xpath("//div[@class='buttons']/button"));
        //Verify the message "Shopping cart"
        verifyText("Shopping cart", By.xpath("//div[@class='page-title']/h1"), "Add to cart text validation");
        //clear text from the field
        Thread.sleep(2000);
        clearTextFromField(By.xpath("//td[@class='quantity']/input"));
        //send keys to field
        Thread.sleep(2000);
        sendTextToElement(By.xpath("//td[@class='quantity']/input"), "2");
        //click on update cart
        clickOnElement(By.id("updatecart"));
        //verify the price
        verifyText("$2,950.00", By.xpath("//td[@class='subtotal']/span"), "Verify price");
        //click on the term box
       clickOnElement(By.id("termsofservice"));
        //click on checkout button
        clickOnElement(By.id("checkout"));
        //verify welcome please sign in
        verifyText("Welcome, Please Sign In!", By.xpath("//div[@class='page-title']/h1"), "welcome sign in text validaation");
        // click on checkout as guest
       clickOnElement(By.xpath("//button[contains(text(),'Checkout as Guest')]"));
        //send text to first name field
        sendTextToElement(By.id("BillingNewAddress_FirstName"), "lee");
        //send text to password field
        sendTextToElement(By.id("BillingNewAddress_LastName"), "lolo");
        //send text to email address
        sendTextToElement(By.id("BillingNewAddress_Email"), "lee123@gmail.com");
        Thread.sleep(8000);
        //select country from the dropdown
        selectByVisibleTextFromDropDown(By.id("BillingNewAddress_CountryId"), "India");
        //send text to city field
        sendTextToElement(By.id("BillingNewAddress_City"), "surat");
        //send text to address 1
        sendTextToElement(By.id("BillingNewAddress_Address1"), "dumas road");
        //send text to postal code field
        sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"), "395002");
        //send text to phone number field
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"), "9898989898");
        //click on continue button
        clickOnElement(By.xpath("//div[@id='billing-buttons-container']/button[4]"));
        Thread.sleep(8000);
        //Click on next day air($0.00)
       clickOnElement(By.xpath("//ul[@class='method-list']/li[2]/div[1]/input"));
        //click on continue button
        clickOnElement(By.xpath("//form[@id='co-shipping-method-form']/div[2]/button"));
        //click on credit card radio button
        clickOnElement(By.xpath("//ul[@class='method-list']/li[2]/div/div[2]/input"));
        //Click on continue button
        clickOnElement(By.xpath("//div[@id='checkout-step-payment-method']/div/button"));
        Thread.sleep(5000);
        //Select master card from the dropdown
        selectByVisibleTextFromDropDown(By.id("CreditCardType"), "Master card");
        //send text to cardholder name field
        sendTextToElement(By.id("CardholderName"), "Mr lee");
        //Send text to cardnumber field
        sendTextToElement(By.id("CardNumber"), "5105105105105100");
        //select month from dropdown
        selectByValueFromDropdown(By.id("ExpireMonth"), "11");
        //select year from dropdown
        selectByVisibleTextFromDropDown(By.id("ExpireYear"), "2023");
        //send text to card code
        sendTextToElement(By.id("CardCode"), "007");
        //Click on continue button
        clickOnElement(By.xpath("//div[@id='payment-info-buttons-container']/button"));
        //verify credit card text
        verifyText("Credit Card", By.xpath("//li[@class='payment-method']/span[2]"), "Verifying credit crad text");
        //verify shipping method next day air
        verifyText("Next Day Air", By.xpath("//li[@class='shipping-method']/span[2]"), "Verifying shipping method next day air");
        //verify price
        verifyText("$2,950.00", By.xpath("//div[@class='table-wrapper']/table[1]/tbody/tr/td[6]/span"), "Verify Price ");
        //click on continue button
        clickOnElement(By.xpath("//div[@id='confirm-order-buttons-container']/button"));
        Thread.sleep(5000);
        //Verify Thank you text
        verifyText("Thank you", By.xpath("//div[@class='page-title']/h1"), "Verify thank you text");
        //verify your order has been successfully processed
        verifyText("Your order has been successfully processed!", By.xpath("//div[@class='section order-completed']/div[1]"), "Verify successfully processed text");
        //click on continue button
        clickOnElement(By.xpath("//div[@class='section order-completed']/div[3]/button"));
        //verify welcome to our store
        verifyText("Welcome to our store", By.xpath("//div[@class='topic-block-title']/h2"), "Verify welcome to iur store");

    }
    @After
    public void tearDwon(){
        closeBrowser();

    }


}
