package drivermanager;

import Utility.Utilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pages.SignUpPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    static {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        System.setProperty("current.date.time", dateFormat.format(new Date()));
    }
    public static WebDriver driver;
    public String  folderPath, cmd;
    public SignUpPage signUpPage;


    @BeforeSuite
    public void setup(){

        System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.addArguments("enable-automation");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-browser-side-navigation");
            options.addArguments("--disable-gpu");
             driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            System.out.println("Start Time: " + Utilities.getUtilities().getDateTime());
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);



        signUpPage = PageFactory.initElements(driver, SignUpPage.class);
    }


    @AfterSuite
    public void tearDown() throws InterruptedException, IOException {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH.mm.ss");
        String currentDateTime = format.format(date);
        if (getClass().getCanonicalName().contains("SignUpTest")) {
            folderPath = System.getProperty("user.dir") + "/allure-results/Reports/SignUpTest/" + "__" + currentDateTime;
        }
        File theDir = new File(folderPath);

        // if the directory does not exist, create it
        if (!theDir.exists()) {
           // System.out.println("creating directory: " + theDir.getName());
            boolean result = false;
            try {
                theDir.mkdirs();
                result = true;
            } catch (SecurityException se) {
                System.out.println(se.getMessage());
            }
            if (result) {
               // System.out.println("Folder created");
            }
        } else if (theDir.exists()) {
            //System.out.println("Folder exist");
        }
        if (getClass().getCanonicalName().contains("SignUpTest")) {
            cmd = Constant.allurePathWin + " generate" + " " + System.getProperty("user.dir") + "/allure-results -o" + " " + System.getProperty("user.dir") + "\\allure-results\\Reports\\SignUpTest\\" + theDir.getName();
            System.out.println(cmd);
        }
        //System.out.println("Before Report Process");
        //System.out.println("This is CMD : " + cmd);
        Process process = Runtime.getRuntime().exec(cmd);
        Thread.sleep(10000);
       // System.out.println("Generating Report");
        process.waitFor(60, TimeUnit.SECONDS);
      //  System.out.println("After Report Process");
        Thread.sleep(10000);
     killChromeDriver();
    }

    /**
     * kill ChromeDriver
     */
    public void killChromeDriver() {
        String cmd;

            cmd = "taskkill /f /t /IM chromedriver.exe";

        Process process = null;
        try {
            process = Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
