package stepDefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class FlightSearchResults {

    WebDriver driver;

    @Given("^I navigate to \"([^\"]*)\"$")
    public void iNavigateTo(String url)  {
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }

    @When("^I Search for flight from \"([^\"]*)\" and to \"([^\"]*)\" on Date \"([^\"]*)\"$")
    public void iSearchForFlightFromAndToOnDate(String from, String to, String date) throws Throwable {

        driver.findElement(By.name("Departure airport")).clear();
        driver.findElement(By.name("Departure airport")).sendKeys(from);
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//div[contains(@aria-label,'"+from+"')])[1]")).click();
        driver.findElement(By.name("Arrival airport")).clear();
        driver.findElement(By.name("Arrival airport")).sendKeys(to);
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//div[contains(@aria-label,'"+to+"')])[1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//label[@class='checkbox one-way']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//a[contains(@aria-label,'"+date+"')])[1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@class='cta cta--large cta--primary js-widget-submit ']")).click();

    }

    @Then("^it should display the flight results and display the cheapest flight number$")
    public void itShouldDisplayTheFlightResultsAndDisplayTheCheapestFlightNumber() throws Throwable {

        Thread.sleep(5000);
        Boolean found=false;
        List<WebElement> gTotalCount=driver.findElements(By.xpath("//div[@class='ts-fbr-flight-list-row__header-col ts-fbr-flight-list-row__panel']"));
        for (int i=1;i<=gTotalCount.size();i++)
        {
            String gLowerText=driver.findElement(By.xpath("(//div[contains(@class,'ts-fbr-flight-list-row__header-col RPA ts-fbr-flight-list')])["+i+"]")).getText();
            if (gLowerText.contains("Lowest price"))
            {
                String gLowestPrice=driver.findElement(By.xpath("//span[@class='summary-curr-only']")).getText();
                String gFlightNumber=driver.findElement(By.xpath("(//div[@class='ts-fbr-flight-list-row__header-col ts-fbr-flight-list-row__panel'])["+i+"]//p[@class='ts-fip__aircraft']")).getText();
                System.out.println("The cheapest Flight Number is "+gFlightNumber +" and the price is INR" +gLowestPrice.trim());
                found=true;
            }

        }
        if (!found)
        {
            throw new IllegalArgumentException("Cheapest flight is not available");
        }

    }


}
