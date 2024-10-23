package Utility;

import drivermanager.DriverFactory;
import org.apache.poi.ss.usermodel.*;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Utilities extends DriverFactory {
    private static String screenshotName;
    private static final int explicitWaitDefault = 180;
    private static Utilities instance = null;
    private XSSFWorkbook workbook = null;
    public XSSFSheet sheet ;
    public XSSFRow row = null;
    public static Cell cell;
    public static synchronized Utilities getUtilities() {
        if (instance == null) {
            instance = new Utilities();
        }
        return instance;
    }

    XSSFWorkbook wb;
    XSSFSheet sheet1;
    File src;

    /**
     * get Datetime
     *
     * @return
     */
    public String getDateTime() {

        // Create object of SimpleDateFormat class and decide the format
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        // get current date time with Date()
        Date date = new Date();

        // Now format the date
        String currentDate = dateFormat.format(date);

        String newCurrentDate = currentDate.replace('/', '-');
        return newCurrentDate;

    }

    /**
     * Click on element
     *
     * @param element
     */
    public void clickOnElement(WebElement element) {
        element.click();
    }
    /**
     * click with Javascript Executor
     */
    public static void clickJavaScript(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
        System.out.println("Click with javascript");
    }

    /**
     * Send key
     *
     * @param element
     * @param value
     */
    public void sendKey(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    /**
     * Wait for element visible
     *
     * @param webElement
     * @param driver
     * @return
     */
    public boolean waitForVisibilityOfElement(WebElement webElement, WebDriver driver) {
        long timeOutSecond = 60;
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutSecond);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return true;
        } catch (Exception e) {
            System.out.println("Can not wait till element visible"+e.getMessage());
            return false;
        }
    }


    /**
     * Wait for element click
     *
     * @param element
     * @param driver
     */
    public void waitForElementTobeClickable(final WebElement element, WebDriver driver) {
        try {
            new WebDriverWait(driver, explicitWaitDefault)
                    .until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            System.out.println("Can not wait till element click");
        }
    }


    /**
     * return date stamp
     *
     * @param fileExtension
     * @return
     */
    public static String returnDateStamp(String fileExtension) {
        Date d = new Date();
        String date = d.toString().replace(":", "_").replace(" ", "_") + fileExtension;
        return date;
    }


    /**
     * Read data from Excel file
     *
     * @param filePath
     * @param sheetName
     * @param colName
     * @param rowNum
     * @return
     */
    public String readDataFromExcel(String filePath, String sheetName, String colName, int rowNum) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);
            fis.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            if (rowNum <= 0)
                return "";

            int index = workbook.getSheetIndex(sheetName);
            int col_Num = -1;
            if (index == -1)
                return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                // System.out.println(row.getCell(i).getStringCellValue().trim());
                if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
                    col_Num = i;
            }
            if (col_Num == -1)
                return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                return "";
            cell = row.getCell(col_Num);

            if (cell == null)
                return "";
            if (cell.getCellType().name().equals("STRING"))
                return cell.getStringCellValue();
            else if ((cell.getCellType().name().equals("NUMERIC")) || (cell.getCellType().name().equals("FORMULA"))) {

                String cellText = String.valueOf(cell.getNumericCellValue());
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // format in form of M/D/YY
                    double d = cell.getNumericCellValue();

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(HSSFDateUtil.getJavaDate(d));
                    cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
                    cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + 1 + "/" + cellText;

                }

                return cellText;
            } else if (cell.getCellType().BLANK != null)
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue());

        } catch (Exception e) {

            e.printStackTrace();
            return "row " + rowNum + " or column " + colName + " does not exist in xls";
        }
    }
    /*
    Select value by visible text
     */
    public void selectValueFromDropdown(WebElement element,String text){
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(text);

    }

}
