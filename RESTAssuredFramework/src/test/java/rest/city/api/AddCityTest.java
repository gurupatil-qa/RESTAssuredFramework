package rest.city.api;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import resources.Base;

public class AddCityTest extends Base {

	private Properties prop;
	private CityPayload addCityPayload;
	private CityPayload receiveCityPayload;
	private String cityId;
	private Gson gson;
	private String payload;

	@BeforeClass
	public void setup() throws IOException {

		prop = getGlobalVariable();
		RestAssured.baseURI = prop.getProperty("baseUrl");
		gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

		addCityPayload = new CityPayload();
		addCityPayload.setCityname(prop.getProperty("cityname"));
		addCityPayload.setStatecode(prop.getProperty("statecode"));
		addCityPayload.setStatename(prop.getProperty("statename"));
		addCityPayload.setLatitude(prop.getProperty("latitude"));
		addCityPayload.setLongitude(prop.getProperty("longitude"));
		addCityPayload.setPopulation(prop.getProperty("population"));
		addCityPayload.setDensity(prop.getProperty("density"));
		addCityPayload.setZipcode(prop.getProperty("zipcode"));

		payload = gson.toJson(addCityPayload);
	}

	@Test
	public void verifyAddCity() {

		Response res = given().
								contentType(ContentType.JSON).
								body(payload).
						when().
								post("/city/addcity").
						then().
								assertThat().
								statusCode(201).
								extract().response();

		receiveCityPayload = gson.fromJson(res.asString(), CityPayload.class);
		cityId = receiveCityPayload.getId();

		Assert.assertEquals(receiveCityPayload.getCityname(), prop.getProperty("cityname"));
		Assert.assertEquals(receiveCityPayload.getStatecode(), prop.getProperty("statecode"));
		Assert.assertEquals(receiveCityPayload.getStatename(), prop.getProperty("statename"));
		Assert.assertEquals(receiveCityPayload.getLatitude(), prop.getProperty("latitude"));
		Assert.assertEquals(receiveCityPayload.getLongitude(), prop.getProperty("longitude"));
		Assert.assertEquals(receiveCityPayload.getPopulation(), prop.getProperty("population"));
		Assert.assertEquals(receiveCityPayload.getDensity(), prop.getProperty("density"));
		Assert.assertEquals(receiveCityPayload.getZipcode(), prop.getProperty("zipcode"));

	}

	@AfterClass
	public void storeCityId() throws IOException {

		preserveCityId(cityId);
	}

}
