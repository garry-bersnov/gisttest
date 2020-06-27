import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.*;

public class testGist {
    private WebDriver driver;
    public static String gistLink = "https://gist.github.com";
    public static String email = "your email";
    public static String password = "password";

    @Before
    //Setup Driver
    public void setup(){
        String chromeDriverPath = "/Users/garryujudeda/Downloads/chromedriver2";
        System.setProperty("webdriver.chrome.driver",chromeDriverPath);
        driver = new ChromeDriver();
    }

    @After
    public void openGitPage(){
        driver.navigate().to(gistLink);
    }

    public void loginGistAccount(){
        openGitPage();
        driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/a[1]")).click();
        driver.findElement(By.id("login_field")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id=\"login\"]/form/div[3]/input[9]")).click();

    }

    public void selectGistList(){
        driver.findElement(By.xpath("//*[@id=\"user-links\"]/details/summary")).click();
        driver.findElement(By.xpath("//*[@id=\"user-links\"]/details/details-menu/a[1]")).click();
    }

    @Test
    public void createPublicGist(){
        loginGistAccount();
        openGitPage();
        driver.findElement(By.xpath("//*[@id=\"gists\"]/input")).sendKeys("As a user, I want to create a public gist.");
        driver.findElement(By.xpath("//*[@id=\"gists\"]/div[2]/div/div[1]/div[1]/input[2]")).sendKeys("shopeegist.txt");
        driver.findElement(By.xpath("//*[@id=\"gists\"]/div[2]/div/div[2]/div/div[5]/div[1]/div/div/div/div[5]/div/pre")).sendKeys("Shopee gist");
        driver.findElement(By.xpath("//*[@id=\"new_gist\"]/div/div[2]/button[1]")).click();
    }

    @Test
    public void editExistingGist(){
        loginGistAccount();
        openGitPage();
        selectGistList();
        driver.findElements(By.xpath("//*[@id=\"file-shopeegist-txt-LC1\"]")).get(0).click();
        driver.findElement(By.xpath("//*[@id=\"gist-pjax-container\"]/div[1]/div/div[1]/ul/li[1]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"gists\"]/div[2]/div[2]/div[2]/div/div[5]/div[1]/div/div/div/div[5]/div/pre")).clear();
        driver.findElement(By.xpath("//*[@id=\"gists\"]/div[2]/div[2]/div[2]/div/div[5]/div[1]/div/div/div/div[5]/div/pre")).sendKeys("As a user, I want to edit an existing gist.");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Add file'])[1]/following::button[1]")).click();
    }

    @Test
    public void deleteExistingGist(){
        loginGistAccount();
        openGitPage();
        selectGistList();
        driver.findElements(By.xpath("//*[@id=\"file-shopeegist-txt-LC1\"]")).get(0).click();
        driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
        driver.switchTo().alert().accept();
    }

    @Test
    public void seeExistingGist() {
        loginGistAccount();
        openGitPage();
        selectGistList();
    }
}
