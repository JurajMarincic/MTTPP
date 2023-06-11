import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FirstTest {
    public WebDriver driver;
    public String testURL = "https://www.amazon.com/";

    @BeforeMethod
    public void setupTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\mttpp1\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://www.google.com/");
    }

    @Test (priority = 1)
    public void amazonSearchTest() throws InterruptedException {

        WebElement searchTextBox = driver.findElement(By.name("q"));
        Thread.sleep(2000);

        searchTextBox.sendKeys("amazon");
        searchTextBox.submit();
        Thread.sleep(2000);

        WebElement amazonLink = driver.findElement(By.partialLinkText("Amazon.com"));
        Assert.assertTrue(amazonLink.isDisplayed(), "Amazon link is not displayed");
        amazonLink.click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.getTitle(), "Amazon.com. Spend less. Smile more.");
    }

    @Test (priority = 2)
    public void searchAndClickFirstItemTest() throws InterruptedException {
        driver.navigate().to(testURL);
        WebElement searchTextBox = driver.findElement(By.id("twotabsearchtextbox"));
        Thread.sleep(2000);

        searchTextBox.sendKeys("toy");
        searchTextBox.submit();
        Thread.sleep(2000);

        WebElement firstItem = driver.findElement(By.xpath("(//img[@class='s-image'])[1]"));
        firstItem.click();
        Thread.sleep(2000);

    }

    @Test (priority = 3)
    public void addToCartTest() throws InterruptedException {
        driver.navigate().to(testURL);
        WebElement searchTextBox = driver.findElement(By.id("twotabsearchtextbox"));
        Thread.sleep(2000);

        searchTextBox.sendKeys("toy");
        searchTextBox.submit();
        Thread.sleep(2000);

        WebElement firstItem = driver.findElement(By.xpath("(//img[@class='s-image'])[1]"));
        firstItem.click();
        Thread.sleep(2000);

        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-button"));
        addToCartButton.click();
        Thread.sleep(2000);

        WebElement cartCountElement = driver.findElement(By.id("nav-cart-count"));
        int cartCount = Integer.parseInt(cartCountElement.getText());
        Assert.assertEquals(cartCount, 1, "Item was not added to cart successfully.");


    }

    @Test (priority = 4)
    public void changeLanguageSettingTest() throws InterruptedException {
        driver.navigate().to(testURL);
        WebElement languageSelector = driver.findElement(By.id("icp-nav-flyout"));
        Actions actions = new Actions(driver);

        actions.moveToElement(languageSelector).perform();
        Thread.sleep(2000);

        WebElement deutschLanguageOption = driver.findElement(By.xpath("//div[@id='nav-flyout-icp']//span[contains(text(), 'Deutsch')]"));
        actions.moveToElement(deutschLanguageOption).perform();
        Thread.sleep( 2000);

        deutschLanguageOption.click();
        Thread.sleep(2000);

    }

    @Test (priority = 5)
    public void priceChangeTest() throws InterruptedException {
        driver.navigate().to(testURL);
        WebElement searchTextBox = driver.findElement(By.id("twotabsearchtextbox"));
        Thread.sleep(2000);

        searchTextBox.sendKeys("shirt");
        searchTextBox.submit();
        Thread.sleep(2000);

        WebElement firstItem = driver.findElement(By.xpath("(//img[@class='s-image'])[1]"));
        firstItem.click();
        Thread.sleep(2000);

        WebElement initialPrice1 = driver.findElement(By.xpath("//*[@id=\"corePriceDisplay_desktop_feature_div\"]/div[1]/span[1]/span[2]/span[2]"));
        WebElement initialPrice2 = driver.findElement(By.xpath("//*[@id=\"corePriceDisplay_desktop_feature_div\"]/div[1]/span[1]/span[2]/span[3]"));
        String initialPrice1Text = initialPrice1.getText();
        String initialPrice2Text = initialPrice2.getText();
        Thread.sleep(2000);


        WebElement priceChanger = driver.findElement(By.xpath("//*[@id=\"a-autoid-10-announce\"]/div/div[1]/img"));
        Actions actions = new Actions(driver);
        actions.moveToElement(priceChanger).perform();
        Thread.sleep(2000);

        priceChanger.click();
        Thread.sleep(2000);

        WebElement updatedPrice1 = driver.findElement(By.xpath("//*[@id=\"corePriceDisplay_desktop_feature_div\"]/div[1]/span[1]/span[2]/span[2]"));
        WebElement updatedPrice2 = driver.findElement(By.xpath("//*[@id=\"corePriceDisplay_desktop_feature_div\"]/div[1]/span[1]/span[2]/span[3]"));
        String updatedPrice1Text = updatedPrice1.getText();
        String updatedPrice2Text = updatedPrice2.getText();

        boolean priceChanged = (!updatedPrice1Text.equals(initialPrice1Text)) || (!updatedPrice2Text.equals(initialPrice2Text));
        String errorMessage = "Price hasn't changed!";
        assert priceChanged : errorMessage;


    }

    @AfterMethod
    public void teardownTest() {
        driver.quit();
    }
}