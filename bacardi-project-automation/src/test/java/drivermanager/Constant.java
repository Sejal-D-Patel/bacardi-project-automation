package drivermanager;

public class Constant {
        public final static String allurePathWin =System.getProperty("user.dir") +"/allureConfiguration/allure-2.6.0/bin/allure.bat";
        public final static String tecStubExcelFile =System.getProperty("user.dir") +"/excel_files/signUpData.xlsx";
        public final static String resultDataExcelFile =System.getProperty("user.dir") +"/excel_files/resultData.xlsx";

        public final static String[] expectedMessages = {
                "Enter your first name.",
                "Enter your last name.",
                "Please enter bacardi.com,sgws.com,weareteam.com email address.",
                "Please Enter more than 6 character",
                "Enter your confirm password.",
                "Enter your address.",
                "Enter your zipcode.",
                "Enter your city.",
                "Select your state.",
                "",
                "Enter Valid Phone Number. Format xxx-xxx-xxxx, xxx xxx xxxx, xxx.xxx.xxxx, xxxxxxxxxx"

        };

}
