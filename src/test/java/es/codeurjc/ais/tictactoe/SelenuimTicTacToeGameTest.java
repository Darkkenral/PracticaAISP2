package es.codeurjc.ais.tictactoe;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

import static org.hamcrest.MatcherAssert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

class SelenuimTicTacToeGameTest {
	WebDriver nav1;
	WebDriver nav2;
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

	@BeforeEach
	void test() {
		System.setProperty("webdriver.gecko.driver",	"src/main/java/es/codeurjc/ais/tictactoe/geckodriver.exe");

		nav1 = new  FirefoxDriver();
		nav2 = new  FirefoxDriver();

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
		
		cell_7 = nav1.findElement(By.id("cell-7"));
		cell_7.click();
		cell_6 = nav2.findElement(By.id("cell-6"));
		cell_6.click();
		cell_4 = nav1.findElement(By.id("cell-4"));
		cell_4.click();
		
		cell_3 = nav2.findElement(By.id("cell-3"));
		cell_3.click();
	
	
	}

	@Test
	public void primerJugadorGana() {
		// j1
		cell_1 = nav1.findElement(By.id("cell-1"));
		cell_1.click();
		String mensaje = nav1.switchTo().alert().getText();
		assertThat(mensaje, is("j1 wins! j2 looses."));
		nav1.quit();
		nav2.quit();
	}

	@Test
	public void primerJugadorPierde() {
		// j1
		cell_5 = nav1.findElement(By.id("cell-5"));
		cell_5.click();
		// j2
		cell_0 = nav2.findElement(By.id("cell-0"));
		cell_0.click();
		String mensaje = nav1.switchTo().alert().getText();
		assertThat(mensaje, is("j2 wins! j1 looses."));
		nav1.quit();
		nav2.quit();
	}

	@Test
	public void empate() {
		// j1
		cell_5 = nav1.findElement(By.id("cell-5"));
		cell_5.click();

		// j2
		cell_8 = nav2.findElement(By.id("cell-8"));
		cell_8.click();
		// j1
		cell_2 = nav1.findElement(By.id("cell-2"));
		cell_2.click();

		// j2
		cell_1 = nav2.findElement(By.id("cell-1"));
		cell_1.click();

		// j1
		cell_0 = nav1.findElement(By.id("cell-0"));
		cell_0.click();

		String mensaje = nav1.switchTo().alert().getText();
		assertThat(mensaje, is("Draw!"));
		nav1.quit();
		nav2.quit();

	}

}
