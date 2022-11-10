package datadriven1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class dadrivenfirst {
	
	public static String url="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	WebDriver driver;
	
	
	@Test
	public void test() throws IOException, InterruptedException
	{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get(url);
			
		File excel=new File("C://Users//babu1//Desktop//OrangeHRM.xlsx");
		FileInputStream fis=new FileInputStream(excel);
		XSSFWorkbook wb=new XSSFWorkbook(fis);//total number of sheets
		XSSFSheet sh=wb.getSheet("Sheet1");//particular sheet
		int rowcount=sh.getLastRowNum();
		System.out.println(rowcount);
		for(int i=0;i<=rowcount;i++)
		{
			String user=sh.getRow(i).getCell(0).getStringCellValue();
			
			System.out.println(user);
			
			String password=sh.getRow(i).getCell(1).getStringCellValue();
			System.out.println(password);
			
			
			driver.manage().window().maximize();
			Thread.sleep(5000);
			
			driver.findElement(By.name("username")).sendKeys(user);
			driver.findElement(By.name("password")).sendKeys(password);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@type='submit']")).click();
			Thread.sleep(7000);
			String str=driver.getCurrentUrl();
			if(str.equalsIgnoreCase("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index"))
			{
				Thread.sleep(5000);
				driver.findElement(By.xpath("//*[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//*[@class='oxd-dropdown-menu']/li[4]/a")).click();
				
				//driver.close();
				
			}
			else
			{
			
				
				
				for(int j=i+1;j<=i;j++)
				{
					String user1=sh.getRow(j).getCell(0).getStringCellValue();
					String pass2=sh.getRow(j).getCell(1).getStringCellValue();
					
					//System.out.println(user1);
					//System.out.println(pass2);
					Thread.sleep(4000);
					
					driver.findElement(By.name("username")).sendKeys(user1);
					driver.findElement(By.name("password")).sendKeys(pass2);
					
					driver.findElement(By.xpath("//*[@type='submit']")).click();
					Thread.sleep(3000);
					break;
					
					
					
					
				}
			
				
				
			}
			
			
			
			
		}
		
		driver.close();
		
		
		
	}
	
	
	

}
