
   
  package com.example;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Unit test for simple App.
 */
public class AppTest {
    WebDriver driver;

    @BeforeTest
    public void setup() throws Exception {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        FileInputStream fs = new FileInputStream("C:\\Users\\sunda\\Downloads\\cc2.xlsx");
    }

    @Test(priority = 1)
    public void ChetanVerify() throws Exception {
        driver.get("https://www.barnesandnoble.com/");
        driver.findElement(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]"));
        WebElement element = driver.findElement(By.linkText("All"));
        element.click();
        WebElement book = driver.findElement(By.linkText("Books"));
        book.click();
        // Select dropdown = new Select(element);
        // dropdown.selectByIndex(1);
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet1 = workbook.getSheet("Sheet1");
        XSSFRow row = sheet1.getRow(0);
        String name = row.getCell(0).getStringCellValue();
        WebElement search = driver
                .findElement(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/div[2]/div/input[1]"));
        search.sendKeys(name);
        search.submit();
        // Verify that it contains Chetan Bhagat
        String res = driver
                .findElement(By.xpath("//*[@id=\"searchGrid\"]/div/section[1]/section[1]/div/div[1]/div[1]/h1/span"))
                .getText();
        if (res.equals("Chetan Bhagat")) {
            System.out.println("Yes is Contains Chetan Bhagat");
        } else {
            System.out.println("No it does not contain Chetan Bhagat");
        }
    }

    @Test(priority = 2)
    public void Audio() throws Exception {
        driver.get("https://www.barnesandnoble.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/div/ul/li[5]"))).build()
                .perform();
        driver.findElement(
                By.xpath("//*[@id=\"navbarSupportedContent\"]/div/ul/li[5]/div/div/div[1]/div/div[2]/div[1]/dd/a[1]"))
                .click();

        // Add to Cart
        driver.findElement(By.xpath("//*[@id=\"addToBagForm_2940159543998\"]/input[11]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"plpErrors\"]/em")));
        String txt = driver.findElement(By.xpath("//*[@id=\"plpErrors\"]/em")).getText();
        System.out.println(txt);

    }

    @Test(priority = 3)
    public void testcase3() throws Exception {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.barnesandnoble.com/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,700)");
        WebElement mem = driver.findElement(By.linkText("B&N MEMBERSHIP"));
        mem.click();
        driver.findElement(By.xpath("/html/body/main/div[3]/div[3]/div/section/div/div/div/div/div/a[1]/div")).click();
        js.executeScript("window.scrollBy(0,100)");
        // click the rewards
        driver.findElement(By.id("rewards-model-link")).click();
        WebElement ele = driver.findElement(By.xpath("/html/body/div[7]/div/iframe"));
        driver.switchTo().frame(ele);
    }

    @AfterTest
    public void finishsetup() {
        driver.quit();
    }
}
