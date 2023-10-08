import org.junit.Assert;
import org.junit.Test;
import org.minhtran.Request;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestPremium {
    public Request request = new Request("No cover", true, 0, 300, 300,
            "Public Place");
    public WebDriver driver;

    public TestPremium() {
        System.setProperty("webdriver.chrome.driver", "D:\\Tools\\chromedriver-win64\\chromedriver.exe");
        this.driver = new ChromeDriver();
    }

    public void CallLogin() {
        driver.get("https://demo.guru99.com/insurance/v3/index.php");
        //Login
        var EmailElement = driver.findElement(By.name("email"));
        EmailElement.sendKeys("lmaominh97@gmail.com");
        var PasswordElement = driver.findElement(By.name("password"));
        PasswordElement.sendKeys("tuanminh");
        var loginButton = driver.findElement(By.name("submit"));
        loginButton.click();
    }

    private double getDoubleValueFromText(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); ++i) {
            if (Character.isDigit(text.charAt(i))) {
                result.append(text.charAt(i));
            }
        }
        return Double.parseDouble(result.toString());
    }

    @Test
    public void ReadFromCSVFile() {
        System.out.println("Test started");
        final String path = "src\\main\\resources\\RequestData.csv";
        String line = "";
        int count = 1;
        try (var reader = new BufferedReader(new FileReader(path))) {
            //Skip a line
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                CallLogin();
                String[] lineSplits = line.split(",");
                request.Cover = lineSplits[0];
                request.HasWindScreenRepair = lineSplits[1].equals("Yes");
                request.NumOfAccidents = Integer.parseInt(lineSplits[2]);
                request.TotalMileage = Double.parseDouble(lineSplits[3]);
                request.EstimateValue = Double.parseDouble(lineSplits[4]);
                request.ParkingLocation = lineSplits[5];
                System.out.printf("[Test case %d]: %s\n", count, request.toString());
                PerformRequestQuotation();
                driver.close();
                ++count;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        driver.quit();
    }

    public void PerformRequestQuotation() {
        var RequestQuotationLink = driver.findElement(By.linkText("Request Quotation"));
        RequestQuotationLink.click();
        var BreakDownCoverDropdown = driver.findElement(By.name("breakdowncover"));
        Select BreakDownOptions = new Select(BreakDownCoverDropdown);
        BreakDownOptions.selectByVisibleText(request.Cover);
        var WindScreenRepairRadioYes = driver.findElement(By.id("quotation_windscreenrepair_t"));
        var WindScreenRepairRadioNo = driver.findElement(By.id("quotation_windscreenrepair_f"));
        if (request.HasWindScreenRepair) {
            WindScreenRepairRadioYes.click();
        } else {
            WindScreenRepairRadioNo.click();
        }
        var NumOfAccidentsElement = driver.findElement(By.name("incidents"));
        NumOfAccidentsElement.sendKeys(String.valueOf(request.NumOfAccidents));
        var EstimatedValueElement = driver.findElement(By.name("value"));
        EstimatedValueElement.sendKeys(String.valueOf(request.EstimateValue));
        var ParkingLocationElement = driver.findElement(By.name("parkinglocation"));
        Select ParkingLocationSelect = new Select(ParkingLocationElement);
        ParkingLocationSelect.selectByVisibleText(request.ParkingLocation);
        var CalculateButton = driver.findElement(By.xpath("//input[@value='Calculate Premium']"));
        CalculateButton.click();

        var discountElement = driver.findElement(By.xpath("//p[@id='calculatedpremium']/b[1]"));
        String discountElementText = discountElement.getText();
        double discount = getDoubleValueFromText(discountElementText);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        var premiumElement = driver.findElement(By.xpath("//p[@id='calculatedpremium']/b[3]"));
        String premiumElementText = premiumElement.getText();
        double premium = getDoubleValueFromText(premiumElementText);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        this.request.calculatePremium();
        double actualPremium = request.Premium;
        Assert.assertEquals(premium, actualPremium, 0.1);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
