package es.codeurjc.ais.tictactoe;

import static org.hamcrest.MatcherAssert.*;

import static org.hamcrest.CoreMatchers.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

 public class SelenuimTicTacToeGameTest {
	 static WebDriver nav1;
	 static WebDriver nav2;
	 String nombre1 = "j1";
	 String nombre2 = "j2";
	WebElement inputnombre1;
	 WebElement button1;
	 WebElement button2;
	 WebElement inputnombre2;
	WebElement cell_0;
	WebElement cell_1;
	WebElement cell_2;
	WebElement cell_3;
	WebElement cell_4;
	WebElement cell_5;
	WebElement cell_6;
	WebElement cell_7;
	WebElement cell_8;
	WebElement alert1;
	WebElement alert2;
	WebDriverWait wait ;
	 WebDriverWait wait2;
	
	
	@BeforeClass
	public static void inicpartida() {
		System.setProperty("webdriver.gecko.driver",	"src/main/java/es/codeurjc/ais/tictactoe/geckodriver.exe");
		WebApp.start();
		nav1 = new  FirefoxDriver();
		nav2 = new  FirefoxDriver();
	
		
	}
	
	@Before
	public void moviiniciales() {
		wait=new WebDriverWait(nav1, 15);
		 wait2=new WebDriverWait(nav1, 15);
		nav1.get("http://localhost:8080/");
		nav2.get("http://localhost:8080/");

		inputnombre1 = nav1.findElement(By.id("nickname"));
		button1 = nav1.findElement(By.id("startBtn"));
		inputnombre1.sendKeys(nombre1);
		button1.click();

		inputnombre2 = nav2.findElement(By.id("nickname"));
		inputnombre2.sendKeys(nombre2);
		button2 = nav2.findElement(By.id("startBtn"));
		button2.click();

		// j1
		marcarCasilla(nav1,wait,"cell-7","X",true);
	
		// j2
		marcarCasilla(nav2,wait2,"cell-6","O",true);
		
		// j1
		marcarCasilla(nav1,wait,"cell-4","X",true);
	
		
		// j2
		marcarCasilla(nav2,wait2,"cell-3","O",true);
		
	
		
	}
	
	

	@Test
	public void primerJugadorGana() {
		
		

		// j1
		marcarCasilla(nav1,wait,"cell-1","X",false);
		
	
		
		wait.until(ExpectedConditions.alertIsPresent());
		wait2.until(ExpectedConditions.alertIsPresent());
		
		String mensaje = nav1.switchTo().alert().getText();
		assertThat(mensaje, is("j1 wins! j2 looses."));
	
		nav1.switchTo().alert().accept();
		nav2.switchTo().alert().accept();

	}

	@Test
	public void primerJugadorPierde() {
		// j1
		marcarCasilla(nav1,wait,"cell-5","X",true);
		
		// j2
		marcarCasilla(nav2,wait2,"cell-0","O",false);
		
		wait.until(ExpectedConditions.alertIsPresent());
		wait2.until(ExpectedConditions.alertIsPresent());

		String mensaje = nav1.switchTo().alert().getText();
		assertThat(mensaje, is("j2 wins! j1 looses."));
		
		nav1.switchTo().alert().accept();
		nav2.switchTo().alert().accept();
		
		
	}

	@Test
	public void empate() {
		
		// j1
		marcarCasilla(nav1,wait,"cell-5","X",true);
		// j2
		marcarCasilla(nav2,wait2,"cell-8","O",true);
		// j1
		marcarCasilla(nav1,wait,"cell-2","X",true);
		// j2
		marcarCasilla(nav2,wait2,"cell-1","O",true);
		// j1
		marcarCasilla(nav1,wait,"cell-0","X",false);
		wait.until(ExpectedConditions.alertIsPresent());
		wait2.until(ExpectedConditions.alertIsPresent());
		
		String mensaje = nav1.switchTo().alert().getText();
		assertThat(mensaje, is("Draw!"));
		
		nav1.switchTo().alert().accept();
		nav2.switchTo().alert().accept();
		
	
	}


    public boolean marcarCasilla(WebDriver navegador, WebDriverWait wait, String idcelda, String simbolo, boolean ultimacelda){
        WebElement celdaElement = navegador.findElement(By.id(idcelda));
        celdaElement.click();
        if (ultimacelda)
            return wait.until(ExpectedConditions.textToBe(By.id(idcelda), simbolo));
        else
            return true;
    }
	
    @AfterClass
	public static void finish (){
		nav1.quit();
		nav2.quit();
	}

    
}
