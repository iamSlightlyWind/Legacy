package test;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class AIO {
    static String localDomain = "http://localhost:8080";
    static String remoteDomain = "https://choice.themajorones.dev";

    static String domain = localDomain;

    static int smallSleep = 250;
    static int mediumSleep = 500;
    static int longSleep = 2000;
    static int extremeSleep = 5000;

    static EdgeOptions options = new EdgeOptions();
    static WebDriver driver;

    static void setup() {
        System.setProperty("webdriver.edge.driver", "./msedgedriver");
        options.setBinary("/usr/bin/microsoft-edge-stable");
        options.addArguments("--remote-allow-origins=*");
        driver = new EdgeDriver(options);
    }

    static void assertEquals(String textClass, String value) {
        String text = driver.findElement(By.className(textClass)).getText();
        assert text.equals(value) : text + " != " + value;
    }

    static void assertNotEquals(String textClass, String value) {
        String text = driver.findElement(By.className(textClass)).getText();
        assert !text.equals(value) : text + " == " + value;
    }

    static void quit() throws InterruptedException {
        Thread.sleep(longSleep);
        driver.quit();
    }

    static void checkout() throws InterruptedException {
        driver.get(domain + "/Cart");
        driver.findElement(By.className("TestCartCheckout")).click();
        driver.findElement(By.className("TestCartPayment")).click();
        Thread.sleep(longSleep);
    }

    static void openNewTab(String URL) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
        driver.get(domain + URL);
    }

    static void clearCart() throws InterruptedException {
        driver.get(domain + "/Cart");
        Thread.sleep(smallSleep);
        driver.findElement(By.className("testCartClear")).click();
        Thread.sleep(mediumSleep);
    }

    static void increase(int n) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            driver.findElement(By.className("testProductIncrease")).click();
            Thread.sleep(smallSleep);
        }
    }

    static void addItem(String item) throws InterruptedException {
        driver.get(domain + "/view/detail/product?" + item);
        Thread.sleep(smallSleep);
        driver.findElement(By.className("testProductAdd")).click();
        Thread.sleep(mediumSleep);
    }

    static void login() throws InterruptedException {
        driver.get(domain + "/auth/login.jsp");
        Thread.sleep(smallSleep);
        driver.findElement(By.className("testLoginUser")).sendKeys("phong");
        driver.findElement(By.className("testLoginPassword")).sendKeys("phong");
        driver.findElement(By.className("testLoginSubmit")).click();
        Thread.sleep(mediumSleep);
    }
}