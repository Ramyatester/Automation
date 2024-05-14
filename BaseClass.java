package cardatausage_Base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;




public class BaseClass {
	protected Map<String, String> data = null;
	public static RemoteWebDriver driver;
	public DesiredCapabilities c;
	public static WebElement element;
	static ExtentReports reports;
	protected static ExtentTest test;
	public static String testName;;
	static String TestName;
	public static List<String> expRe;
	public static String sd;
	public static ArrayList<String> ar ;
	public static WebDriverWait wait ;

	private static FileInputStream fileInputStream;
	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	private static Cell cell;
	private static ProcessBuilder pb;
	private static String autoitExe;

	protected static Actions actions;

	@BeforeSuite
	public static void passBrowserUrl() throws MalformedURLException
	{		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\z030183\\Downloads\\CAR_DATA_USAGE\\CAR_DATA_USAGE\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get("https://cc-system-web-staging.apps.stage-eu.kamereon.io");
		//driver.get("https://admportal.sit.emea.avnext.net/");
		reports = new ExtentReports(System.getProperty("user.dir")+"\\Reports\\Nominal_DHMI1Error.html",true);
		//test = reports.startTest(testName);

	}


	@BeforeMethod
	public void setUp(Method method) throws IOException, SQLException {
		
		TestName = method.getName();
		System.out.println("current executed Test case is : "+TestName);
		test=reports.startTest(TestName);
		

	}

	@AfterMethod
	public void afterExecution(ITestResult iTestResult) throws IOException {
		testName=iTestResult.getName();
		System.out.println("Executed TestName is : "+TestName);
		if (iTestResult.getStatus()==iTestResult.SUCCESS) {
			System.out.println("TestCase is Passed"+TestName);
			//screenShot(testName);
			test.log(LogStatus.PASS, "TestCase is is Passed"+TestName);
			//test.log(LogStatus.INFO,test.addScreenCapture(captureScreen())+testName);
			test.addBase64ScreenShot(captureScreen());
		} 
		else if(iTestResult.getStatus()==iTestResult.FAILURE){
			System.out.println("TestCase is Failed"+testName); captureScreen(testName);
			//test.log(LogStatus.FAIL, "TestCase is is Failed"+testName);
			test.log(LogStatus.FAIL,test.addScreenCapture(captureScreen())+testName);
			//test.addScreenCapture(testName); test.addBase64ScreenShot(captureScreen()); 
		}

		else if(iTestResult.getStatus()==iTestResult.SKIP){
			System.out.println("TestCase is skip"+testName); captureScreen(testName);

			test.log(LogStatus.SKIP, "TestCase is is skip"+testName);
			test.log(LogStatus.SKIP,test.addScreenCapture(captureScreen())+testName);
		}

	}


	/*public static void signIn(String user) throws InterruptedException, IOException {
		if (user=="rakesh") {
			DTA_fexcel
			eys(LocType.xpath, Pages1.Email, "rakesh.gm-extern@rntbci.com");
			test.log(LogStatus.INFO,test.addScreenCapture(captureScreen()));
		}
		else if(user=="sneha") {
			DTA_SendKeys(LocType.xpath, Pages1.Email, "sneha.sharavan-kumar@rntbci.com");
			test.log(LogStatus.INFO,test.addScreenCapture(captureScreen()));
		}
		javaScriptClick(LocType.xpath, Pages1.Next);
		sleep(5000);
		click(LocType.xpath, Pages1.Sign_In_Certificate);
		javaScriptClick(LocType.xpath, Pages1.Yes);
	}

	/*
	public static String capture() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File Dest = new File(System.getProperty("user.dir")+"\\Screenshots\\" + System.currentTimeMillis()+ ".png");
		String errflpath = Dest.getAbsolutePath();
		FileHandler.copy(scrFile, Dest);
		return errflpath;
	}
/*

	/*
	 * private void getTestData(String testName) throws Exception { 
	 * int z; 
	 * data = new HashMap<String, String>(); 
	 * Sheet dataSheet; 
	 * try { FileInputStream
	 * fileInputStream=ne 
	 * dataSheet = XSSFWorkbook(new File(("data.spreadsheet.name"))).getSheet(getTestName());
	 * System.out.println(dataSheet.getRows()); for (z=1;z<dataSheet.getRows();z++)
	 * { if (dataSheet.getCell(0, z).getContents().equalsIgnoreCase(Testcaseid)) {
	 * System.out.println(Testcaseid); break; } }
	 * 
	 * for (int i = 0; i < dataSheet.getColumns(); i++) { String key =
	 * dataSheet.getCell(i, 0).getContents(); System.out.println(key); String value
	 * = dataSheet.getCell(i, z).getContents(); System.out.println(value);
	 * data.put(key, value); } } catch (Exception e) { test.log(LogStatus.ERROR,
	 * "Issue with Datasheet Loading"); e.printStackTrace(); } }
	 */	 


	public String getTestName() {		
		String[] klassNameSplit = this.getClass().getName().split("\\.");
		return klassNameSplit[klassNameSplit.length - 1];
	}

	public static void btnClick(String xpath) throws IOException {
		//			String[] namesplit = xpath.split("'");
		//			String sname= namesplit[namesplit.length - 2];
		boolean isPresent = false;
		try	{

			WebDriverWait wait = new WebDriverWait (driver, 15); 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			driver.findElementByXPath(xpath).click();
			System.out.println(xpath+"   clicked");
			isPresent = true;	
			test.log(LogStatus.PASS,"Button Clicked in "+xpath);
		}
		catch(Exception e){
			isPresent = false;
			System.out.println(xpath+"  not found");
			test.log(LogStatus.FAIL, xpath+ "Element not found");

		}	
	}
	
	public static void pause(Integer milliseconds){
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	
	public static void btnClick_Mat(String className) throws IOException {
//      String[] namesplit = xpath.split("'");
//      String sname= namesplit[namesplit.length - 2];
      boolean isPresent = false;
      try    {
         
          WebDriverWait wait = new WebDriverWait (driver, 30);
          wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(className)));
          driver.findElementByClassName(className).click();
          System.out.println(className+"   clicked");
          isPresent = true;   
      //test.log(LogStatus.PASS,"Button Clicked in "+className);
      }
      catch(Exception e){
          isPresent = false;
          System.out.println(className+"  not found");
      //test.log(LogStatus.FAIL, className+ " Element not found");
         
  }}
	public static void enterTextByClass(String className, String input) throws IOException {
        
        boolean isPresent = false;
           
            try    {
               
                WebDriverWait wait=new WebDriverWait(driver, 15);
                wait.until(ExpectedConditions.presenceOfElementLocated(By.className(className)));   
                driver.findElementByClassName(className).sendKeys(input);
                isPresent = true;
                System.out.println(className+"   found");
                //test.log(LogStatus.PASS,"Text Entered in "+xpath, "Entered values: "+input);
                }
            catch(Exception e){
                System.out.println(className+"   not found");
                //test.log(LogStatus.FAIL, "Text not Entered in "+xpath, "Entered values failed: "+input);
               
                }
    }

	public static void enterText(String xpath, String input) throws IOException {
		String[] namesplit = xpath.split("'");
		String sname= namesplit[namesplit.length - 2];
		boolean isPresent = false;

		try	{

			WebDriverWait wait=new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));	
			driver.findElementByXPath(xpath).sendKeys(input);
			isPresent = true;
			System.out.println(xpath+"   found");
			test.log(LogStatus.PASS,"Text Entered in "+xpath, "Entered values: "+input);
		}
		catch(Exception e){
			test.log(LogStatus.FAIL, "+xpath+ Element not found");

		}
		
	}

	public static void selectDropdown(String xpath, String input)
	{
		WebDriverWait wait=new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		Select objSelect = new Select(driver.findElementByXPath(xpath)); //dropdown
		objSelect.selectByVisibleText(input);	
		System.out.println(xpath +" located with input :  " +input);
		//test.log(LogStatus.PASS, xpath+ "Dropdown selected");

	}

	public static void scrollToElement(String xpath) {
		JavascriptExecutor js = (JavascriptExecutor) driver;	
		WebDriverWait wait = new WebDriverWait (driver, 15); 

		//This will scroll the page till the element is found		
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		driver.findElementByXPath(xpath).click();
	}


	public String getTextFromElement(String xpath, BaseClass TestBase) throws IOException 
	{		
		String[] namesplit = xpath.split("'");
		String sname= namesplit[namesplit.length - 2];
		String text = null;
		boolean isPresent = false;
		try	{
			WebDriverWait wait=new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			text= driver.findElementByXPath(xpath).getText();
			System.out.println("The text is "+ text);
			isPresent = true;

			test.log(LogStatus.PASS,"Got text from "+sname, "Text is '"+text+"'" );
		}
		catch(Exception e){
			isPresent = false;
			test.log(LogStatus.FAIL,"Element "+sname+" not found"+test.addScreenCapture(TestBase.captureScreen(xpath)));
		}
		return text;
	}

	public static String captureScreen(String name) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String encodedBase64 = null;
		FileInputStream fileInputStreamReader = null;
		try {
			fileInputStreamReader = new FileInputStream(scrFile);
			byte[] bytes = new byte[(int)scrFile.length()];
			fileInputStreamReader.read(bytes);
			encodedBase64 = new String(Base64.encodeBase64(bytes));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "data:image/png;base64,"+encodedBase64;
	}
	public static String captureScreen() {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String encodedBase64 = null;
		FileInputStream fileInputStreamReader = null;
		try {
			fileInputStreamReader = new FileInputStream(scrFile);
			byte[] bytes = new byte[(int)scrFile.length()];
			fileInputStreamReader.read(bytes);
			encodedBase64 = new String(Base64.encodeBase64(bytes));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "data:image/png;base64,"+encodedBase64;
	}
	
	public void partialScreenShotCapture(String path) throws Throwable{
		WebElement getPartialScreen = driver.findElementByXPath(path);
		File file=getPartialScreen.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile((File) getPartialScreen, new File("screenshot.png"));
	}

	//@AfterMethod
	public void quitbrowser(){
		driver.quit();
		System.out.println("Browser closed successfully");

		reports.flush();
	}
	@AfterClass
	public void end()
	{
		
		System.out.println("Browser closed successfully");
		reports.endTest(test);
		reports.flush();
	}

	
    public static String getTimeStamp() {
    String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
      return timestamp;
    }
	
	public static void enterUrl(String url) {
		if(driver!=null) {
			try {
				driver.get(url);

			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("Driver pointing to NULL..");
			}
		}
	}

	public static void closeBrowser() {
		if(driver!=null) {
			try {
				driver.close();
			} catch (Exception e) {

			}
		}
	}

	public static void quitBrowser() {
		if (driver!=null) {
			try {
				driver.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static WebElement identifyElement(@Optional("xpath") String locType,String locValue) {
		if (locType.equalsIgnoreCase("id")) {
			element=driver.findElement(By.id(locValue));
		}
		else if (locType.equalsIgnoreCase("name")) {
			element=driver.findElement(By.name(locValue));
		}
		else if (locType.equalsIgnoreCase("className")) {
			element=driver.findElement(By.className(locValue));
		}
		else if (locType.equalsIgnoreCase("xpath")) {
			element=driver.findElement(By.xpath(locValue));
		}
		else if (locType.equalsIgnoreCase("cssSelector")) {
			element=driver.findElement(By.cssSelector(locValue));
		}
		else if (locType.equalsIgnoreCase("linkText")) {
			element=driver.findElement(By.linkText(locValue));
		}
		else if (locType.equalsIgnoreCase("partialLinkText")) {
			element=driver.findElement(By.partialLinkText(locValue));
		}
		else if (locType.equalsIgnoreCase("tagName")) {
			element=driver.findElement(By.tagName(locValue));
		}
		return element;
	}

	public static boolean elementIsVisible(WebElement element) {
		boolean b=true;
		try {
			b=element.isDisplayed()&&element.isEnabled()?true:false;

			test.log(LogStatus.PASS,"Element is visible "+element);
		} catch (Exception e) {
			
		}
		return b;
	}

	public static void click(String LocType,String locValue) {
		try {
			element=identifyElement(LocType, locValue);
			wait = new WebDriverWait (driver, 50); 
			wait.until(ExpectedConditions.visibilityOf(element));
			element.click();
			System.out.println(locValue + " : Clicked");
			if (elementIsVisible(element)) {

				System.out.println(locValue + " : Clicked");
			test.log(LogStatus.PASS," Clicked on Element "+locValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to perform click on : "+LocType+" : " + locValue);
			test.log(LogStatus.FAIL, locValue+ "Element not found");
		}
	}
	
	public static void uploadFileAutoit(String Filepath) {
		autoitExe=System.getProperty("user.dir")+"\\autoit\\autoit.exe";
		try {
		pb=new ProcessBuilder(autoitExe,Filepath);
		pb.start();
		} catch (Exception e) {
		e.printStackTrace();
		}
		}
	public static void pageDown() {
		actions=new Actions(driver);
		actions.sendKeys(Keys.PAGE_DOWN).build().perform();
	}

	public static void javaScriptClick(String locType,String locValue) {
		try {
			element=identifyElement(locType, locValue);
			wait = new WebDriverWait (driver, 50); 
			wait.until(ExpectedConditions.visibilityOf(element));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
			System.out.println(locValue + " : Clicked");
			test.log(LogStatus.PASS," Clicked on Element "+locValue);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to perform click on : "+locType+" : " + locValue);
			test.log(LogStatus.FAIL, locValue+ "Element not found");
		}
	}

	public static void select_Dropdown_Value(String locType,String locValue ,String Textvalue) {
		element=identifyElement(locType, locValue);
		wait = new WebDriverWait (driver, 50); 
		wait.until(ExpectedConditions.visibilityOf(element));
		Select select=new Select(element);
		String s="";
		int Indexvalue1;
		if(Textvalue!=null) {
			select.selectByVisibleText(Textvalue);
			test.log(LogStatus.PASS," Selected value "+Textvalue);
		}
		else if (Textvalue!=null) {
			select.selectByValue(Textvalue);
			test.log(LogStatus.PASS," Selected value "+Textvalue);
		}
	}




	public static void DTA_SendKeys(String locType,String locValue,String textData) {
		try {
			element=identifyElement(locType, locValue);
			wait = new WebDriverWait (driver, 50); 
			wait.until(ExpectedConditions.visibilityOf(element));
			if (elementIsVisible(element)) {
				element.clear();
				element.sendKeys(textData);
				System.out.println(locValue + " : Text entered");
				test.log(LogStatus.PASS,"Text entered Element "+locValue+ ": TestData : "+textData);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to perform DTA_SendKeys on : "+locType+" :"+ locValue);
			test.log(LogStatus.FAIL,"Unable to perform DTA_SendKeys on  "+locValue);
		}
	}
	public static void refresh() {
		driver.navigate().refresh();
	}

	public static void clear(String locType,String locValue) {
		element=identifyElement(locType, locValue);
		wait = new WebDriverWait (driver, 50); 
		wait.until(ExpectedConditions.visibilityOf(element));
		if (elementIsVisible(element)) {
			element.clear();
		}	

	}

	public static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void wait(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void getTitle() {
		driver.getTitle();
	}

	public static void read_CSV_Total_File(File csvFile) {
		try {
			expRe = new ArrayList<String>();
			//File csvFile = new File("C:\\Users\\z028979\\Downloads\\Custom Settings Configuration (Descmo).csv");
			BufferedReader br = new BufferedReader(new FileReader(csvFile));
			String line = "";
			StringTokenizer st = null;
			while ((line = br.readLine()) != null) {
				// use comma as token separator
				st = new StringTokenizer(line,",");
				while (st.hasMoreTokens()) {
					String sd = st.nextToken()+" ";
					if (sd != null) {
						expRe.add(sd);

					}
				}
			}
			System.out.print("Expected : "+expRe);
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public  static void read_CSV_File(File csvFile,String endsWith) {
		try {
			expRe = new ArrayList<String>();
			// File csvFile = new File("C:\\Users\\z028979\\Desktop\\9cac213e-2f6f-461c-b0aa-d2ec02e8be98.csv");
			BufferedReader br = new BufferedReader(new FileReader(csvFile));
			String line = "";
			StringTokenizer st = null;
			while ((line = br.readLine()).endsWith(endsWith) ) {
				// use comma as token separator
				st = new StringTokenizer(line,",");
				while (st.hasMoreTokens()) {
					sd = st.nextToken()+" ";
					if (sd != null) {

						expRe.add(sd);
					}
				}
			}
			System.out.print("Expected : "+expRe);
			System.out.println();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getText(String locType,String locValue) {
		String text=null;
		element=identifyElement(locType, locValue);
		wait = new WebDriverWait (driver, 50); 
		wait.until(ExpectedConditions.visibilityOf(element));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(className)element));
		text=element.getText();
		System.out.println(text);
		test.log(LogStatus.INFO,  text);
		return text;
	}

	public static void movetoElement(String locType,String locValue) {
		try {
			element=identifyElement(locType, locValue);
			Actions actions=new Actions(driver);
			wait = new WebDriverWait (driver, 50); 
			wait.until(ExpectedConditions.visibilityOf(element));
			actions.moveToElement(element).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void doubleClick(String locType,String locValue) {
		try {
			element=identifyElement(locType, locValue);
			Actions actions=new Actions(driver);
			wait = new WebDriverWait (driver, 50); 
			wait.until(ExpectedConditions.visibilityOf(element));
			actions.moveToElement(element).doubleClick().build().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String excel(String file,int sheet,int rowNum,int cellNum) throws IOException {
		//String file="";
		fileInputStream=new FileInputStream(file);
		workbook=new XSSFWorkbook(fileInputStream);

		cell=workbook.getSheetAt(sheet).getRow(rowNum).getCell(cellNum);

		DataFormatter dataFormatter=new DataFormatter();
		return dataFormatter.formatCellValue(cell);
	}

	public static void assertion(String actual,String expected) {
		try {
			wait = new WebDriverWait (driver, 50); 
			wait.until(ExpectedConditions.visibilityOf(element));
			Assert.assertEquals(actual, expected);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void typeDateIntheCalendar(String locType,String locValue,String valueDate) {   //8/4/2020 9:46 AM
		try {
			WebElement datefield=identifyElement(locType, locValue);
			Thread.sleep(1000);
			JavascriptExecutor js= (JavascriptExecutor)driver;
			js.executeScript("arguments[0].value='"+valueDate+"';", datefield);
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void scrollINElement(String selector,int yHeight) {
		EventFiringWebDriver eventFiringWebDriver=new EventFiringWebDriver(driver);
		eventFiringWebDriver.executeScript("document.querySelector('"+selector+"').scrollTop="+yHeight+"");

	}
	
	public static void scrollPageBy(int xDir,int yDir) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy("+xDir+","+yDir+")");
	}

	/*
	public static void main(String[] args) {
        try {
            String zipFilePath = "C:\\Users\\z024513\\Downloads\\awazu39.zip";
            ZipFile zf = new ZipFile(zipFilePath);
            System.out.println(zf.size());
           
            String destDirectory="C:\\Users\\z024513\\Downloads\\UnzipNew";
            File destDir = new File(destDirectory);
            if (!destDir.exists()) {
                destDir.mkdir();
            }
            ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
            ZipEntry entry = zipIn.getNextEntry();
            while (entry != null) {
                String filePath = destDirectory + File.separator + entry.getName();
                System.out.println("Filepath--------- "+filePath);
                if (!entry.isDirectory()) {
                    // if the entry is a file, extracts it
                    NR_CampaignLogs obj=new NR_CampaignLogs();
                    obj.extractFile(zipIn, filePath);
                } else {
                    // if the entry is a directory, make the directory
                    File dir = new File(filePath);
                    dir.mkdirs();
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
            zipIn.close();
        }catch(Exception e) {
                e.printStackTrace();
            }
    }
     public void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
            byte[] bytesIn = new byte[10];
            int read = 0;
            while ((read = zipIn.read(bytesIn)) != -1) {
                bos.write(bytesIn, 0, read);
            }
            bos.close();
        }

*/
	
	public static void unZip(String zipFile, String source) throws IOException {
		File dir = new File(source);
		if (!dir.exists())
			dir.mkdirs();
		ZipEntry ze = null;
		
		try(FileInputStream fis = new FileInputStream(zipFile);
			ZipInputStream zis = new ZipInputStream(fis);)
		
		{
			ze = zis.getNextEntry();
			while (ze != null) {
				String fileName = ze.getName();
				File newFile = new File(source + File.separator + fileName);
				String message = "Unzipping to " + newFile.getAbsolutePath();
				//logger.log(Level.INFO, message);
				new File(newFile.getParent()).mkdirs();
				zis.closeEntry();
				ze = zis.getNextEntry();
			}
		} catch (IOException e) {
			//logger.log(Level.SEVERE, e.getMessage(), e);
		} catch (Exception e1) {
			throw (e1);
		}
	}

			
			
	public static void readXMLFile(String xmlFilePath,String ElementsByTagName,String ElementsAttribute) {
		try{
			String filePath = xmlFilePath;
					//System.getProperty("user.dir")+"\\TestData\\LabelsAndLanguages.xml";
			File file = new File(filePath);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbf.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();
			System.out.println(doc.getDocumentElement().getNodeName());
			NodeList nodeList = doc.getElementsByTagName(ElementsByTagName);
			int tLength = nodeList.getLength();
			for(int i=0; i<tLength; i++){
				Node node = nodeList.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE){
					Element element = (Element)node;
					System.out.println(ElementsByTagName+" : "+element.getAttribute(ElementsAttribute));
				}
			}
		}
			catch (Exception e) {
				e.printStackTrace();
			}
	}
	public static void implicitWait(){
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	}
	public static void passRandomDigit(String xpath, String locType, String locValue){
		try {
			Random randomnumber = new Random();
			int digit = randomnumber.nextInt(9);
			element=identifyElement(locType, locValue);
			wait = new WebDriverWait (driver, 50); 
			wait.until(ExpectedConditions.visibilityOf(element));
			if (elementIsVisible(element)) {
				element.clear();
				element.findElement(By.xpath(locValue)).sendKeys();
				System.out.println(locValue + " : Text entered");
				test.log(LogStatus.PASS,"Text entered Element "+locValue+ ": TestData : "+digit);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to perform passRandomDigits : "+locType+" :"+ locValue);
			test.log(LogStatus.FAIL,"Unable to perform passRandomDigits  "+locValue);
		}
		
		}
	
	public static void cropImage(String xpath,String name) throws IOException {
		WebElement editFleetWarningMessage = driver.findElement(By.xpath(xpath));
		File src = editFleetWarningMessage.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("/C:\\Users\\z030183\\Downloads\\CAR_DATA_USAGE\\CAR_DATA_USAGE\\screenshot/"+name+".png"));
	
	}
}