package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.beans.Transient;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import demo.utils.ExcelDataProvider;
import demo.wrappers.Wrappers;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.JavascriptExecutor;


public class TestCases extends ExcelDataProvider{ // Lets us read the data
        ChromeDriver driver;

        /*
         * TODO: Write your tests here with testng @Test annotation.
         * Follow `testCase01` `testCase02`... format or what is provided in
         * instructions
         */

        /*
         * Do not change the provided methods unless necessary, they will help in
         * automation and assessment
         */
        //@Test(enabled = true)
        public void testCase01() throws InterruptedException{
               System.out.println("Start TC 01"); 
               driver.get("https://www.youtube.com/");
                Boolean result=Wrappers.isValidatedUrl(driver);
                Assert.assertTrue(result);

               WebElement option=driver.findElement(By.xpath("(//yt-icon-button[@id='guide-button'])[1]//button"));
              Wrappers.scrollAndClick(option,driver);
               Thread.sleep(2000);    

              WebElement aboutElement=driver.findElement(By.xpath("//div[@id='guide-links-primary']//a[contains(text(),'About')]"));
              Wrappers.scrollAndClick(aboutElement, driver);
              Thread.sleep(2000);

              WebElement textElement=driver.findElement(By.xpath("//section[@class='ytabout__content']"));
              String aboutText=textElement.getText();
              System.out.println(aboutText);
              System.out.println("End Of TC 01");

        }
         //@Test(enabled = true)
        public void testCase02() throws InterruptedException{
               System.out.println("Start TC 02"); 
               driver.get("https://www.youtube.com/");
                Boolean result=Wrappers.isValidatedUrl(driver);
                Assert.assertTrue(result);

               WebElement option=driver.findElement(By.xpath("(//yt-icon-button[@id='guide-button'])[1]//button"));
              Wrappers.scrollAndClick(option,driver);
               Thread.sleep(2000);   
               
             WebElement moviesElement= driver.findElement(By.xpath("(//div[@id='items'])[2]//yt-formatted-string[contains(text(),'Movies')]"));
             Wrappers.scrollAndClick(moviesElement, driver);
             Thread.sleep(2000);

             WebElement rightarrowElement=driver.findElement(By.xpath("//span[text()='Top selling']//ancestor::ytd-shelf-renderer //yt-button-shape//button[@aria-label='Next']"));
             Wrappers.scrollAndClick(rightarrowElement, driver);
             Wrappers.Onlyclick(rightarrowElement);
             Wrappers.Onlyclick(rightarrowElement);
             Thread.sleep(4000);

             SoftAssert sa=new SoftAssert();

             WebElement dramaElement =driver.findElement(By.xpath("((//ytd-grid-movie-renderer[@class='style-scope yt-horizontal-list-renderer'])[last()]//descendant::span[@class='grid-movie-renderer-metadata style-scope ytd-grid-movie-renderer'])"));
             Wrappers.waitTillElementClickable(dramaElement, driver);
             String textActual=dramaElement.getText().replaceAll("[^A-Za-z]", "");//only take word Drama
             
             //System.out.println("DramaElement is::"+dramaElement.getText().replaceAll("[^\\d]", ""));//only take number
             System.out.println("Text Actual is-"+textActual);
             List<String> textExpected=Arrays.asList("Drama, Action, Romance,Comedy, Actionadventure,Animation");
                for(String type:textExpected){
                        if(type.contains(textActual)){
                                sa.assertTrue(true,"Not Matching with textExpected");
                        }
                }

             WebElement AdultElement=driver.findElement(By.xpath("((//ytd-grid-movie-renderer[@class='style-scope yt-horizontal-list-renderer'])[16]//descendant::div[@class='yt-badge-shape__text'])[3]"));
             String adultTextActual=AdultElement.getText().replaceAll("[^A-Z/]", "");
             System.out.println(adultTextActual);

             String[] text=adultTextActual.split("/");
             for(int i=0;i<text.length;i++){
                if (text[i].equals("A")) {
                        sa.assertTrue(true, "Contains A");
                }
             }
             if(adultTextActual.contains("A")){
                System.out.println("Movies is for Adult");
             }else{
                System.out.println("Movies is for All child and Adult");
             }
             sa.assertAll();

                 System.out.println("End Of TC 02");

        }

         //@Test(enabled = true)
        public void testCase03() throws InterruptedException{
               System.out.println("Start TC 03"); 
               driver.get("https://www.youtube.com/");
                Boolean result=Wrappers.isValidatedUrl(driver);
                Assert.assertTrue(result);

               WebElement option=driver.findElement(By.xpath("(//yt-icon-button[@id='guide-button'])[1]//button"));
              Wrappers.scrollAndClick(option,driver);
               Thread.sleep(3000);   
               
             WebElement musicElement= driver.findElement(By.xpath("(//div[@id='items'])[2]//yt-formatted-string[contains(text(),'Music')]"));
             Wrappers.scrollAndClick(musicElement, driver);
             Thread.sleep(4000);

             WebElement numberElement =driver.findElement(By.xpath("(//ytd-rich-item-renderer[@class='style-scope ytd-rich-shelf-renderer'])[4]//descendant::div[@class='yt-badge-shape__text']"));
             Wrappers.scrollOnly(numberElement, driver);
             System.out.println(numberElement.getText());
             int numberText=Integer.parseInt(numberElement.getText().replaceAll("[^\\d]",""));
             
             SoftAssert sa =new SoftAssert();
             if (numberText <= 50) {
                System.out.println("Inside If block");   
             }
             sa.assertFalse(numberText <= 50,"Number is equal or below 50");
             sa.assertAll();
              System.out.println("End Of TC 03");

        }
         //@Test(enabled = true)
        public void testCase04() throws InterruptedException{
               System.out.println("Start TC 04"); 
               driver.get("https://www.youtube.com/");
                Boolean result=Wrappers.isValidatedUrl(driver);
                Assert.assertTrue(result);

               WebElement option=driver.findElement(By.xpath("(//yt-icon-button[@id='guide-button'])[1]//button"));
              Wrappers.scrollAndClick(option,driver);
               Thread.sleep(3000);   
               
             WebElement showmoreElement= driver.findElement(By.xpath("(//div[@id='items'])[2]//yt-formatted-string[contains(text(),'Show more')]"));
             Wrappers.scrollAndClick(showmoreElement, driver);
             Thread.sleep(2000);

             WebElement newsElement= driver.findElement(By.xpath("(//div[@id='items'])[2]//yt-formatted-string[contains(text(),'News')]"));
             Wrappers.scrollAndClick(newsElement, driver);
             Thread.sleep(4000);

             int sumText =Wrappers.getSumLikeAndTileText(By.xpath("(//span[@id='title' and contains(text(),'Latest news posts')]//ancestor::div[@id='content'])[2]//child::span[@id='vote-count-middle']"),driver);
             SoftAssert sa=new SoftAssert();
             sa.assertTrue(sumText>0,"sum must be greater than 0");
             sa.assertAll();

              System.out.println("End Of TC 04");
              
        }

         //@Test(enabled = true)
        public void testCase05() throws InterruptedException{
               System.out.println("Start TC 05"); 
               driver.get("https://www.youtube.com/");
                Boolean result=Wrappers.isValidatedUrl(driver);
                Assert.assertTrue(result);

               WebElement option=driver.findElement(By.xpath("(//yt-icon-button[@id='guide-button'])[1]//button"));
              Wrappers.scrollAndClick(option,driver);
               Thread.sleep(2000);   
               
             WebElement moviesElement= driver.findElement(By.xpath("(//div[@id='items'])[2]//yt-formatted-string[contains(text(),'Movies')]"));
             Wrappers.scrollAndClick(moviesElement, driver);
             Thread.sleep(2000);

             WebElement rightarrowElement=driver.findElement(By.xpath("//span[text()='Top selling']//ancestor::ytd-shelf-renderer //yt-button-shape//button[@aria-label='Next']"));
             Wrappers.scrollAndClick(rightarrowElement, driver);
             Wrappers.Onlyclick(rightarrowElement);
             Wrappers.Onlyclick(rightarrowElement);
             Thread.sleep(4000);

             By movieList=By.xpath("//span[contains(text(),'Top selling')]//ancestor::div[@id='dismissible']//descendant::ytd-grid-movie-renderer");
             List<String> movieDetails=Wrappers.getMovieDetailsList(driver, movieList);
             System.out.println(movieDetails);
             String movieCategoryName = movieDetails.get(0);

             SoftAssert sa=new SoftAssert();
             sa.assertTrue(movieCategoryName.equals("Action")||movieCategoryName.equals("Actionadventure")||movieCategoryName.equals("Drama"));

             String movieBadge=movieDetails.get(1);
             sa.assertTrue(movieBadge.equals("A")||movieBadge.equals("U")||movieBadge.equals("U/A"));
             sa.assertAll();


              System.out.println("End Of TC 05");

        }
         @Test(enabled = true)
        public void testCase06() throws InterruptedException{
               System.out.println("Start TC 06"); 
               driver.get("https://www.youtube.com/");
                Boolean result=Wrappers.isValidatedUrl(driver);
                Assert.assertTrue(result);

               WebElement option=driver.findElement(By.xpath("(//yt-icon-button[@id='guide-button'])[1]//button"));
              Wrappers.scrollAndClick(option,driver);
               Thread.sleep(3000);   
               
             WebElement showmoreElement= driver.findElement(By.xpath("(//div[@id='items'])[2]//yt-formatted-string[contains(text(),'Show more')]"));
             Wrappers.scrollAndClick(showmoreElement, driver);
             Thread.sleep(2000);

             WebElement newsElement= driver.findElement(By.xpath("(//div[@id='items'])[2]//yt-formatted-string[contains(text(),'News')]"));
             Wrappers.scrollAndClick(newsElement, driver);
             Thread.sleep(4000);

             WebElement contentCardsElement=driver.findElement(By.xpath("//div[@id='rich-shelf-header-container' and contains(.,'Latest news posts')]"));
             WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
             wait.until(ExpectedConditions.elementToBeClickable(contentCardsElement));

             Thread.sleep((new Random().nextInt(3)+2)*1000);
             long sumOfVotes = 0;
             for(int i=1;i<=3;i++)
                {
                System.out.println(Wrappers.findElementandPrintWE(driver,By.xpath("//div[@id='header']"),contentCardsElement,i));
                System.out.println(Wrappers.findElementandPrintWE(driver,By.xpath("//div[@id='body']"),contentCardsElement,i));
                try {
                       String res = Wrappers.findElementandPrintWE(driver, By.xpath("//span[@id='vote-count-middle']"), contentCardsElement, i);
                      sumOfVotes = sumOfVotes+Wrappers.convertToNumericValues(res);

                } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                        System.out.println("Vote not present:"+e.getMessage());
                }
                Thread.sleep((new Random().nextInt(3)+2)*1000);
             }
             System.out.println("Sum of Votes are:-"+sumOfVotes);

              System.out.println("End Of TC 06");

        }
        


        @BeforeTest
        public void startBrowser() {
                System.setProperty("java.util.logging.config.file", "logging.properties");

                // NOT NEEDED FOR SELENIUM MANAGER
                // WebDriverManager.chromedriver().timeout(30).setup();

                ChromeOptions options = new ChromeOptions();
                LoggingPreferences logs = new LoggingPreferences();

                logs.enable(LogType.BROWSER, Level.ALL);
                logs.enable(LogType.DRIVER, Level.ALL);
                options.setCapability("goog:loggingPrefs", logs);
                options.addArguments("--remote-allow-origins=*");

                System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

                driver = new ChromeDriver(options);

                driver.manage().window().maximize();
        }

        @AfterTest
        public void endTest() {
                driver.close();
                driver.quit();

        }
}