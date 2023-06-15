import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;


public class myFirstClass {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.nagwa.com/en/");
        driver.findElement(By.className("dropdown")).click();
        driver.findElement(By.xpath("//a[@data-val='en']")).click();
        //driver.findElement(By.className("dropdown")).clear();
        driver.findElement(By.className("search")).click();
//        driver.findElement(By.id("txt_search_query")).sendKeys();
//        driver.findElement(By.id("btn_global_search")).click();
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
            WebElement searchResult = driver.findElement(By.xpath("/html/body/div/div/div/div/main/div[3]/ul"));
            if (searchResult != null) {
                List<WebElement> resultsItems = searchResult.findElements(By.tagName("li"));
                if (resultsItems.size() > 1) {
                    resultsItems.get(1).findElement(By.tagName("a")).click();
                    driver.findElement(By.xpath("/html/body/div/div/div/div/main/section[3]/div/div[2]/div/a")).click();
                    driver.findElement(By.className("instances"));
                    int questionCount = driver.findElements(By.className("instance")).size();
                    System.out.println("Number of questions: " + questionCount);
                } else {
                    System.out.println("Results found are less than 2");
                }
            } else {
                System.out.println("No results found");
            }
        }
        catch(Exception e){
            System.out.println("Couldn't continue running the test: "+ e);
        }
        driver.quit();

        }
    }