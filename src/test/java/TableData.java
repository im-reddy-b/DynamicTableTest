import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

class TableDataExtractor {

    private WebElement table;

    public TableDataExtractor(WebElement table) {
        this.table = table;
    }

    public String getTableDataAsString() {
        StringBuilder tableData = new StringBuilder("[");
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        // Skip the header row (assuming the first row is the header)
        for (int i = 1; i < rows.size(); i++) {
            List<WebElement> columns = rows.get(i).findElements(By.tagName("td"));

            tableData.append("{");
            tableData.append("\"name\" : \"").append(columns.get(0).getText()).append("\", ");
            tableData.append("\"age\" : ").append(columns.get(1).getText()).append(", ");
            tableData.append("\"gender\": \"").append(columns.get(2).getText()).append("\"");
            tableData.append("}");

            // Add a comma if it's not the last row
            if (i < rows.size() - 1) {
                tableData.append(", ");
            }
        }

        tableData.append("]");
        return tableData.toString();
    }
}