import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class GradePrinter {
    public static void main(String[] args) throws IOException {
        System.setProperty("webdriver.chrome.driver", "/Users/yiseboge/chromedriver");

        WebDriver driver;
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        try {
            driver.get("http://portal.aait.edu.et");
            driver.findElement(By.xpath("//*[@id=\"UserName\"]")).sendKeys("ATR/0000/09");
            driver.findElement(By.xpath("//*[@id=\"Password\"]")).sendKeys("0000");
            driver.findElement(By.xpath("//*[@id=\"home\"]/div[2]/div[2]/form/div[4]/div/button\n")).submit();
            driver.get("https://portal.aait.edu.et/Grade/GradeReport\n");

            String grade = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[1]/div")).getText();

            PrintWriter writer = new PrintWriter("grades.txt", StandardCharsets.UTF_8);
            writer.println(grade);
            writer.close();
        } finally {
            driver.close();
        }

    }
}
