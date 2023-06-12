# MTTPP
Kod se nalazi na master branchu.

public void setupTest() - Before metoda, locira googledriver te ga navigira na google.com
public void amazonSearchTest() - Upisuje "amazon" u google te searcha, nakon toga locira Amazon.com stranicu te klika na nju, test pada ako se naslov stranice ne poklapa
public void searchAndClickFirstItemTest() - Test upisuje "toy" u amazon searchbox te klika na prvi item
public void addToCartTest() - Test upisuje "toy" u amazon searchbox, klika na prvi item te ga dodaje u cart clickom na "Add to cart"
public void changeLanguageSettingTest()- Test hovera zastavu, nakon što se izbornik prikaže hovera "Deutsch" te ga klika i provjerava je li jezik promijenjen
public void priceChangeTest() - Test upisuje "shirt" u amazon searchbox, klika na prvi item te klikom mijenja varijantu itema, nakon toga provjerava je li cijena promijenjena
public void teardownTest() - After metoda gasi driver

Projekt je napravljen u IntelliJ IDEA Community Edition 2022.3.2 programskog okruženju pomoću Java programskog jezika. 
Tehnologije koje su korištene su Maven tj. alat za automatizaciju izgradnje projekta pomoću kojeg je moguće upravljanje detaljima projekta te upravljanje ovisnostima. Također su korištene tehnologije TestNG te Selenium čije je korištenje omogućio Maven.

Selenium je alat koji nam omogćuje interakciju s web stranicama tj. elementima i njihovim podatcima te su korištene navedene biblioteke:
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

Uključivanjem navedenih biblioteka Selenium nam je omogućio stvaranje WebDrivera tj. korištenje Chrome drivera koji nam je omogućio pristup i upravljanje Google Chromeom. Driver je ključan za testiranje pošto pomoću njega 90% koda ne bi moglo biti izvršeno.
Omogućene su funkcije kao što su findElement(By) preko kojeg "pronalazimo" tj. pristupamo određenom elementu web stranice te sendKeys(), submit(), i click() koje, naravno, direktno upravljaju elementima stranice tj. izvršavaju neku radnju.

TestNG je framework za testiranje koji omogućuje lakšu organizaciju testova te pomaže u izvršavanju i izvještavanju o istima.
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

Navedene biblioteke omogućuju notaciju koja uvelike pomaže organizaciji projekta te samoj funkcionalnosti. Za kreiranje tj. označavanje onoga što je test koristi se @Test notacija, a za metode koje želimo izvršiti prije ili poslije svih testova kao što je npr. inicijalizacija i isključivanje drivera brinu se @AfterMethod i @BeforeMethod notacija. Uz navedeno, omogućeno je mijenjanje redoslijeda izvedbe testova pomoću priority notacije koja je korištena u kodu (npr. @Test (priority = 1)). 
Osim pomoći u organizaciji, uključivanjem biblioteke org.testng.Assert omogućena je metoda Assert pomoću koje možemo provjeravati jesu li određeni uvjeti istiniti te rušiti testove u slučaju nezadovoljavajućeg rezultata. 
Assert je moguće koristiti na više načina:

        boolean priceChanged = (!updatedPrice1Text.equals(initialPrice1Text)) || (!updatedPrice2Text.equals(initialPrice2Text));
        String errorMessage = "Price hasn't changed!";
        assert priceChanged : errorMessage;

U navedenom primjeru assert će ispisati "errorMessage" i srušiti test ako je priceChanged false tj. logička 0. 

        WebElement amazonLink = driver.findElement(By.partialLinkText("Amazon.com"));
        Assert.assertTrue(amazonLink.isDisplayed(), "Amazon link is not displayed");
        amazonLink.click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.getTitle(), "Amazon.com. Spend less. Smile more.");

U ovome primjeru Assert provjerava poklapa li se naslov kojeg je driver uspio pohraniti s naslovom koji je hardkodiran tj. drugi parametar assertEquals funckije. Ako se ne poklapaju Assert ruši test.
