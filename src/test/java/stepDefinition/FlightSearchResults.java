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
//        List<WebElement> gPrice=driver.findElements(By.xpath("//div[@data-ts-comp='FbrOption']"));
        for (int i=1;i<=3;i++)
        {
            String gLowerText=driver.findElement(By.xpath("(//div[@data-ts-comp='FbrOption'])["+i+"]")).getText();
            if (gLowerText.contains("Lowest price"))
            {
                String gFlightNumber=driver.findElement(By.xpath("(//div[@class='ts-fbr-flight-list-row__header-col ts-fbr-flight-list-row__panel'])["+i+"]//p[@class='ts-fip__aircraft']")).getText();
                System.out.println("The cheapest Flight Number is "+gFlightNumber );
                found=true;
            }

        }
        if (!found)
        {
            throw new IllegalArgumentException("Cheapest flight is not available");
        }

    }

    public void selectDate(String monthyear, String Selectday) throws InterruptedException
    {
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='ui-datepicker-title']/span[1]"));

        for (int i=0; i<elements.size();i++)
        {
            System.out.println(elements.get(i).getText());
            //Selecting the month
            if(elements.get(i).getText().equals(monthyear))
            {
                //Selecting the date
                List<WebElement> days = driver.findElements(By.xpath("//div[@class='ui-datepicker-inline ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all ui-datepicker-multi ui-datepicker-multi-2']/div[2]/table/tbody/tr/td/a"));

                for (WebElement d:days)
                {
                    System.out.println(d.getText());
                    if(d.getText().equals(Selectday))
                    {
                        d.click();
                        Thread.sleep(10000);
                        return;
                    }
                }

            }

        }
        driver.findElement(By.xpath("//div[@class='ui-datepicker-inline ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all ui-datepicker-multi ui-datepicker-multi-2']/div[2]/div/a/span")).click();
        selectDate(monthyear,Selectday);

    }
}
