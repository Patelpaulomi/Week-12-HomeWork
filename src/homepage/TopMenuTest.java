package homepage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class TopMenuTest extends Utility {
    String baseUrl = " https://demo.nopcommerce.com/";

    @Before
    public void setup() {
        openBrowser(baseUrl);
    }
    //

    public void selectMenu(String menu) {
        List<WebElement> topMenuName = driver.findElements(By.xpath("//ul[@class='top-menu notmobile']/li"));
        for (WebElement name : topMenuName) {
            if (name.getText().equalsIgnoreCase(menu)) {
                name.click();
                break;


            }
        }
    }



@Test
    public void verifyPageNavigation() {
     selectMenu("Computers");
     getTextFromElement(By.linkText("Computers"));

    }
    @After
    public void tearDown(){
        closeBrowser();
    }


}



