package com.sifast.socle.javaee.functional.test;

/*import java.util.concurrent.TimeUnit;

 import org.apache.commons.configuration.PropertiesConfiguration;
 import org.openqa.selenium.By;
 import org.openqa.selenium.WebDriver;
 import org.openqa.selenium.firefox.FirefoxDriver;
 import org.testng.Assert;
 import org.testng.annotations.AfterSuite;
 import org.testng.annotations.BeforeSuite;
 import org.testng.annotations.Test;*/

public class LoginTest {
    /*
     * private WebDriver driver; private PropertiesConfiguration propertiesConfiguration;
     * 
     * @BeforeSuite public void initDriver() throws Exception { driver = new FirefoxDriver(); propertiesConfiguration = new
     * PropertiesConfiguration("functional_test_config.properties"); }
     * 
     * @Test public void loginSuccssefulCase() { driver.navigate().to(propertiesConfiguration.getProperty("host") + "/socle-javaee-web"); driver.manage().window().maximize();
     * driver.findElement(By.id("j_username")).sendKeys("bileljallouli11@hotmail.fr"); driver.findElement(By.id("j_password")).sendKeys("123456a");
     * driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); driver.findElement(By.id("login")).click(); Assert.assertEquals(propertiesConfiguration.getProperty("host")
     * + "/socle-javaee-web/views/Index.xhtml", driver.getCurrentUrl().toString()); driver.navigate().to(propertiesConfiguration.getProperty("host") + "/socle-javaee-web/logout");
     * }
     * 
     * @Test(dependsOnMethods = { "loginSuccssefulCase" }) public void loginFailerCase() { driver.navigate().to(propertiesConfiguration.getProperty("host") + "/socle-javaee-web");
     * driver.manage().window().maximize(); driver.findElement(By.id("j_username")).sendKeys("azerty@hotmail.fr"); driver.findElement(By.id("j_password")).sendKeys("123456a");
     * driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); driver.findElement(By.id("login")).click(); Assert.assertEquals(propertiesConfiguration.getProperty("host")
     * + "/socle-javaee-web/views/Login.xhtml?auth=fail", driver.getCurrentUrl().toString()); }
     * 
     * @AfterSuite public void quitDriver() throws Exception { driver.quit(); }
     */
}
