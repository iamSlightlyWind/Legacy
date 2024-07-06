package edge.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.edge.driver", "./msedgedriver");

        EdgeOptions options = new EdgeOptions();
        options.setBinary("/usr/bin/microsoft-edge-stable");
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new EdgeDriver(options);

        try {
            driver.get("http://localhost:8080/auth/login.jsp");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}