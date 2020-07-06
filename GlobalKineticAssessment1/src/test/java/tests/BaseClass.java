package tests;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class BaseClass {
	WebDriver driver;
	
    /**
    * The page objects 
    */
    public static String  formsCardXpath()
    {
        return "//div[@class='card-body']/h5[text()='Forms']";
    }
    public static String  formsCardHeaderXpath()
    {
        return "//div[@class='main-header']";
    }
    
    public static String  practiceFormXpath()
    {
        return "//span[text()='Practice Form']";
    }
    public static String  firstNameXpath()
    {
        return "//input[@id='firstName']";
    }
    public static String  lastNameXpath()
    {
        return "//input[@id='lastName']";
    }
    
    
    
    public static String  emailAddressXpath()
    {
        return "//input[@id='userEmail']";
    }
    public static String  maleRadioBtnXpath()
    {
        return "//label[contains(text(),'Male')]";
    }
    public static String  userNumberXpath()
    {
        return "//input[@id='userNumber']";
    }
    public static String  readingXpath()
    {
        return "//label[contains(text(),'Reading')]";
    }
    public static String  currentAddressXpath()
    {
        return "//textarea[@id='currentAddress']";
    }
    public static String  submitButtonXpath()
    {
        return "//button[text()='Submit']";
    }
    public static String  thankYouPopupXpath()
    {
        return "//div[text()='Thanks for submitting the form']";
    }
    public static String  closeButtonXpath()
    {
        return "//div//button[text()='Close']";
    }
    
     public static String  showFormXpath()
    {
        return "//div[contains(text(),'Forms')]";
    }
     
    
     /**
      * Functions to be used in the test 
      */
     public static boolean waitForElement(String xPath, WebDriver driver)
     {
         boolean elementFound;
         System.out.println("INFO: Waiting for element: "+xPath);
         try
         {
             elementFound = false;
             while(elementFound != true)
             {
                 WebDriverWait wait = new WebDriverWait(driver, 1);
                 while(wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)))!=null)
                 {
                     elementFound = true;
                     break;
                 }
                 
                 return elementFound;
             }
         } catch (Exception e)
         {
             elementFound = false;
             System.out.println("ERROR: There was an error while waiting for element: "+xPath);
         }
         
         return elementFound;
     }
     
     
     public static boolean scrollToElement(String elementXpath, WebDriver driver)
     {
         try
         {
             WebElement element = driver.findElement(By.xpath(elementXpath));
             ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

             return true;
         }
         catch (Exception e)
         {
             System.err.println("Error scrolling to element - " + elementXpath + " - " + e.getStackTrace());
             return false;
         }

     }
     
     public static boolean clickElementbyXpath(String elementXpath, WebDriver driver)
     {
         try
         {
             System.out.println("INFO: Clicking element by Xpath : " + elementXpath);
             waitForElement(elementXpath,driver);
             WebDriverWait wait = new WebDriverWait(driver, 5);
             wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
             WebElement elementToClick = driver.findElement(By.xpath(elementXpath));
             elementToClick.click();
             return true;
         }
         catch (Exception e)
         {
             System.err.println("[Error] Failed to click on element by Xpath - " + e.getMessage());
             return false;
         }
     }
     
     public static boolean enterTextByXpath(String elementXpath, WebDriver driver, String textToEnter)
     {
         try
         {
             waitForElement(elementXpath,driver);
             WebElement elementToTypeIn = driver.findElement(By.xpath(elementXpath));
             elementToTypeIn.sendKeys(Keys.chord(Keys.CONTROL, "a"), textToEnter);

             return true;
         }
         catch (Exception e)
         {
             System.err.println("Error entering text - " + elementXpath + " - " + e.getMessage());
             return false;
         }
     }
     
     public static boolean selectRadioButtonUsingElementXpath(String radioButtonXpath, WebDriver driver)
     {
         try
         {
             WebElement radioButton = driver.findElement(By.xpath(radioButtonXpath));
             radioButton.click();
             
         }
         catch (Exception ex)
         {
             System.err.println("[ERROR] failed to find element: " + ex.getMessage());
             return false;
         }
         return true;
     }
     
     public static String switchToNewWindow(WebDriver driver)
     {
         // get the current window handle
         String parentHandle = driver.getWindowHandle();
         while (parentHandle.equalsIgnoreCase(driver.getWindowHandle()))
         {
             for (String winHandle : driver.getWindowHandles())
             {

                 // switch focus of WebDriver to the next found window handle (that's your newly opened window)
                 driver.switchTo().window(winHandle);
             }

         }

         return parentHandle;
     }
     
     public static void pause(){
         try{
             Thread.sleep(3000);
         }catch (InterruptedException e) {
             e.printStackTrace();
         }
     }
	@BeforeTest
	public void setup(){
		 String exePath = "/Users/mandla.tyindyi/eclipse-workspace/GlobalKineticAssessment1/src/test/resources/drivers/chromedriver";
	     System.setProperty("webdriver.chrome.driver", exePath);
	     String URL = "https://demoqa.com/";
	     
	     driver = new ChromeDriver();
	     driver.get(URL);
	     driver.manage().window().maximize();   
	}
	
	@Test
	public void sampleTest(){
		System.out.println("Test started...");
		if(!(waitForElement(formsCardXpath(),driver))){
            System.out.println("Failed to wait for the forms card");
        }
        
        if(!(scrollToElement(formsCardXpath(),driver))){
            System.out.println("Failed to scroll to the forms card");
        }
        
        if(!(clickElementbyXpath(formsCardXpath(),driver))){
            System.out.println("Failed to click on the forms card");
        }
        clickElementbyXpath(formsCardXpath(),driver);
        
        //-----------------------------
        if(!(clickElementbyXpath(showFormXpath(),driver))){
            System.out.println("Failed to click on the link to show form");
        }
        
        if(!(clickElementbyXpath(showFormXpath(),driver))){
            System.out.println("Failed to click on the link to show form");
        }
        if(!(clickElementbyXpath(showFormXpath(),driver))){
            System.out.println("Failed to click on the link to show form");
        }
        //-----------------------------------
        
        if(!(waitForElement(practiceFormXpath(),driver))){
            System.out.println("Failed to wait for the practice form link");
        }
        
        if(!(clickElementbyXpath(practiceFormXpath(),driver))){
            System.out.println("Failed to click on the practice forms link");
        }
        
        if(!(waitForElement(formsCardHeaderXpath(),driver))){
            System.out.println("Failed to wait for the practice form header");
        }
        
        if(!(enterTextByXpath(firstNameXpath(), driver, "Test"))){
            System.out.println("Failed to enter First Name");
        }
        
        if(!(enterTextByXpath(lastNameXpath(), driver, "Test"))){
            System.out.println("Failed to enter Last Name");
        }
        if(!(enterTextByXpath(emailAddressXpath(), driver, "test@test.com"))){
            System.out.println("Failed to enter Email Address");
        }
        
        if(!(selectRadioButtonUsingElementXpath(maleRadioBtnXpath(), driver))){
            System.out.println("Failed to select radio button");
        }
        
        if(!(enterTextByXpath(userNumberXpath(), driver, "0728384759"))){
            System.out.println("Failed to enter Phone Number");
        }
        
        if(!(selectRadioButtonUsingElementXpath(readingXpath(), driver))){
            System.out.println("Failed to click checkbox");
        }
        
        if(!(enterTextByXpath(currentAddressXpath(), driver, "73 Test Street, Bellvile, Cape Town"))){
            System.out.println("Failed to enter Current Address");
        }
        if(!(scrollToElement(submitButtonXpath(),driver))){
            System.out.println("Failed to scroll to the submit button");
        }
        if(!(clickElementbyXpath(submitButtonXpath(),driver))){
            System.out.println("Failed to click the submit button");
        }
       
        //switchToNewWindow(driver);
        
        if(!(waitForElement(thankYouPopupXpath(),driver))){
            System.out.println("Failed to wait for the Thank You popup");
        }
        pause();
        if(!(scrollToElement(closeButtonXpath(),driver))){
            System.out.println("Failed to scroll to the close button");
        }
        if(!(clickElementbyXpath(closeButtonXpath(),driver))){
            System.out.println("Failed to click the close button");
        }
        
        pause();
		System.out.println("Test completed...");
	}
	
	@AfterTest
	public void teardown(){
		driver.close();
		driver.quit();
	}

}
