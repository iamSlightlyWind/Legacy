package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class AIO {

    static EdgeOptions options = new EdgeOptions();
    static WebDriver driver;

    static {
        System.setProperty("webdriver.edge.driver", "./msedgedriver");
        options.setBinary("/usr/bin/microsoft-edge-stable");
        options.addArguments("--remote-allow-origins=*");
        driver = new EdgeDriver(options);
    }

    static void clearCart() throws InterruptedException{
        driver.get("http://localhost:8080/Cart");
        Thread.sleep(500);
        driver.findElement(By.className("testCartClear")).click();
        Thread.sleep(1000);
    }

    static void increase(int n) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            driver.findElement(By.className("testProductIncrease")).click();
            Thread.sleep(500);
        }
    }

    static void addOneItem(String item) throws InterruptedException {
        driver.get("http://localhost:8080/view/detail/product?" + item);
        Thread.sleep(500);
        driver.findElement(By.className("testProductAdd")).click();
        Thread.sleep(1000);
    }

    static void login() throws InterruptedException {
        driver.get("http://localhost:8080/auth/login.jsp");
        Thread.sleep(500);
        driver.findElement(By.className("testLoginUser")).sendKeys("phong");
        driver.findElement(By.className("testLoginPassword")).sendKeys("phong");
        driver.findElement(By.className("testLoginSubmit")).click();
        Thread.sleep(1000);
    }
}