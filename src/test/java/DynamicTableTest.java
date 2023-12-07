import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class DynamicTableTest {

    private WebDriver driver;

    public DynamicTableTest() {

        // Create a new instance of the ChromeDriver
        this.driver = new ChromeDriver();

        // Maximize the window and set implicit wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void runTest() {
        try {
            navigateToPage();
            performTest();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }

    private void navigateToPage() {
        String url = "https://testpages.herokuapp.com/styled/tag/dynamic-table.html";
        driver.get(url); //Calling the URl
    }

    private void performTest() { // This method inputs the data to the textbox and verifies the table data with Original Data
        openTableDataSection();
        inputDataIntoTextArea();
        refreshTable();
        verifyTableData();
    }

    private void openTableDataSection() { //Expands the Input box by clicking on Table Data
        WebElement tableDataSection = driver.findElement(By.xpath("//summary[contains(text(),'Table Data')]"));
        tableDataSection.click();
        sleep(2000);
    }

    private void inputDataIntoTextArea() { // Finds input text box and enter the sample data
        String sampleData = "[{\"name\":\"Bob\",\"age\":20,\"gender\":\"male\"},{\"name\":\"George\",\"age\":42,\"gender\":\"male\"},{\"name\":\"Sara\",\"age\":42,\"gender\":\"female\"},{\"name\":\"Conor\",\"age\":40,\"gender\":\"male\"},{\"name\":\"Jennifer\",\"age\":42,\"gender\":\"female\"}]";
        WebElement inputBox = driver.findElement(By.xpath("//textarea[@id='jsondata']"));
        inputBox.clear();
        inputBox.sendKeys(sampleData);
        sleep(2000);
    }

    private void refreshTable() { //Clicks on refresh table
        WebElement refreshButton = driver.findElement(By.xpath("//button[@id='refreshtable']"));
        refreshButton.click();
        sleep(2000);
    }

    private void verifyTableData() { // Retrieves Table data and verifies with sample data
        WebElement table = driver.findElement(By.id("dynamictable"));
        TableDataExtractor tableDataExtractor = new TableDataExtractor(table);
        String tableData = tableDataExtractor.getTableDataAsString();
        System.out.println("Retrieved Data from the table : " + tableData);

        String sampleData = "[{\"name\" : \"Bob\", \"age\" : 20, \"gender\": \"male\"}, {\"name\" : \"George\", \"age\" : 42, \"gender\": \"male\"}, {\"name\" : \"Sara\", \"age\" : 42, \"gender\": \"female\"}, {\"name\" : \"Conor\", \"age\" : 40, \"gender\": \"male\"}, {\"name\" : \"Jennifer\", \"age\" : 42, \"gender\": \"female\"}]";

        if (tableData.equals(sampleData)) {
            System.out.println("Test Case Passed");
        } else {
            System.out.println("Test Case Failed");
        }
    }

    private void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        DynamicTableTest dynamicTableTest = new DynamicTableTest();
        dynamicTableTest.runTest();
    }
}


