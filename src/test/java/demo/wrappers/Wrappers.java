package demo.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class Wrappers {
    ChromeDriver driver;
    /*
     * Write your selenium wrappers here
     */
    public static Boolean isValidatedUrl(WebDriver driver){
        Boolean success=null;
        try {
            String urlExpected="https://www.youtube.com/";
           String urlText=driver.getCurrentUrl() ;
           if(urlText.equals(urlExpected)){
            success = true;
           }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Got Exception at TC 01");
            e.printStackTrace();
            success = false;
        }
        return success;
    }
    public static void scrollAndClick(WebElement element, ChromeDriver driver){
        try{
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",element);
        element.click();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
     public static void scrollOnly(WebElement element, ChromeDriver driver){
        try{
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",element);
       
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void Onlyclick(WebElement element){
        element.click();
    }

    public static void waitTillElementClickable(WebElement element,WebDriver driver){
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static int getSumLikeAndTileText(By locator,WebDriver driver){
       List<WebElement> numberTextElements=driver.findElements(locator);
             int sum=0;
             for(int i=0;i<3;i++){
               String numberTextString = numberTextElements.get(i).getText().trim().toLowerCase();
               if(numberTextString.isEmpty()) continue;

              Integer numberTextInt;
                        if(numberTextString.contains("k")){
                                //handle 1.2k
                                numberTextInt=(int)(Double.parseDouble((numberTextString.replace("k", "").replace(",", "")))*1000);
                        }else{
                        //handle 999, 1,234
                        numberTextInt= Integer.parseInt(numberTextString.replace("[^\\d]", ""));
                        }

               System.out.println(numberTextInt);
               sum+=numberTextInt;
               List<WebElement> authorElement=driver.findElements(By.xpath("(//span[@id='title' and contains(text(),'Latest news posts')]//ancestor::div[@id='content'])[2]//child::span[@id='vote-count-middle']//ancestor::div[@class='style-scope ytd-rich-item-renderer']//div[@id='author']"));
               System.out.println(authorElement.get(i).getText());
               
              List<WebElement> textAreaElement=driver.findElements(By.xpath("(//span[@id='title' and contains(text(),'Latest news posts')]//ancestor::div[@id='content'])[2]//child::span[@id='vote-count-middle']//ancestor::div[@class='style-scope ytd-rich-item-renderer']//yt-formatted-string[@id='home-content-text']"));
               System.out.println(textAreaElement.get(i).getText());

               

             }
             System.out.println("Sum is :="+sum); 
             return sum;
    }

    public static List<String> getMovieDetailsList(WebDriver driver , By locator){
        List<String> result=new ArrayList<>();
        List<WebElement> movieParentName=driver.findElements(locator);
        String movieCategory = movieParentName.get(movieParentName.size()-1).findElement(By.xpath(".//span[@class='grid-movie-renderer-metadata style-scope ytd-grid-movie-renderer']")).getText();
        System.out.println(movieCategory);
        movieCategory=movieCategory.replaceAll("[^A-Za-z]","");
        System.out.println(movieCategory);

        String movieBadge=movieParentName.get(movieParentName.size()-1).findElement(By.xpath(".//badge-shape[@class='yt-badge-shape yt-badge-shape--default yt-badge-shape--typography']")).getText();
        System.out.println(movieBadge);

        return Arrays.asList(movieCategory,movieBadge);


    }
}
