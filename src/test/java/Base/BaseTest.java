package Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.BeforeClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;



public class BaseTest {

	public static Response response;
	public static Properties properties;
	public FileInputStream fis;

	public Logger logger;

	@BeforeSuite
	public void setUp() {

		try {
			properties = new Properties();
			fis = new FileInputStream(

					System.getProperty("user.dir") + "/src/test/java/prop/config.properties");
		}

		catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		try {
			properties.load(fis);
		} catch (IOException e) {

			e.printStackTrace();
		}

		RestAssured.baseURI = properties.getProperty("baseURI");
		RestAssured.basePath = properties.getProperty("basePath");

	}

	@AfterSuite
	public void tearDown() {

	}

	@BeforeClass
	public void setupLog() {

		logger = Logger.getLogger("EmployeeRestAPI");
		PropertyConfigurator.configure("Log4j.properties");
		logger.setLevel(Level.DEBUG);
	}
}
