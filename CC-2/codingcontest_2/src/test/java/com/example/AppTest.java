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

    @Test(priority = 1)
    public void CodingContest() throws Exception {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("https://www.barnesandnoble.com/");
    driver.findElement(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]"));
    WebElement element = driver.findElement(By.linkText("All"));
    element.click();
    WebElement book = driver.findElement(By.linkText("Books"));
    book.click();
    // Select dropdown = new Select(element);
    // dropdown.selectByIndex(1);
    FileInputStream fs = new
    FileInputStream("C:\\Users\\sunda\\Downloads\\cc2.xlsx");
    XSSFWorkbook workbook = new XSSFWorkbook(fs);
    XSSFSheet sheet1 = workbook.getSheet("Sheet1");
    XSSFRow row = sheet1.getRow(0);
    String name = row.getCell(0).getStringCellValue();
    WebElement search = driver
    .findElement(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/div[2]/div/input[1]"));
    search.sendKeys(name);
    search.submit();

    }

    @Test(priority = 2)
    public void testcase2() throws Exception {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("https://www.barnesandnoble.com/");

    WebElement audiobook =
    driver.findElement(By.xpath("//*[@id=\"rhfCategoryFlyout_Audiobooks\"]"));
    audiobook.click();
    WebElement top100 = driver.findElement(By.linkText("Audiobooks Top 100"));
    top100.click();
    WebElement firstAudiobook =
    driver.findElement(By.xpath("//ol[@class='product-shelf-list']/li[1]"));
    WebElement addToCartButton =
    firstAudiobook.findElement(By.cssSelector(".add-to-cart"));
    addToCartButton.click();

    }
    @Test(priority = 3)
    public void testcase3() throws Exception {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.barnesandnoble.com/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,600)");
        WebElement mem = driver.findElement(By.linkText("B&N MEMBERSHIP"));
        driver.findElement(By.xpath("/html/body/main/div[3]/div[3]/div/section/div/div/div/div/div/a[1]/div")).click();
        js.executeScript("window.scrollBy(0,100)");
        WebElement reward = driver.findElement(By.linkText("JOIN REWARDS"));
        reward.click();
    }
}
