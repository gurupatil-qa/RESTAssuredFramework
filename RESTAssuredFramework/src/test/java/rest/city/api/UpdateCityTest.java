package rest.city.api;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import resources.Base;

public class UpdateCityTest extends Base {

	private CityPayload updateCity;
	private CityPayload receivedPayload;
	private Gson gson;
	private String payload;
	private Properties prop;

	@BeforeClass
	public void setup() throws IOException {

		prop = getGlobalVariable();
		RestAssured.baseURI = prop.getProperty("baseUrl");
		gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

		updateCity = new CityPayload();
		updateCity.setCityname(prop.getProperty("updatecityname2"));
		updateCity.setStatecode(prop.getProperty("updatestatecode"));
		updateCity.setStatename(prop.getProperty("updatestatename"));
		updateCity.setLatitude(prop.getProperty("updatelatitude"));
		updateCity.setLongitude(prop.getProperty("updatelongitude"));
		updateCity.setPopulation(prop.getProperty("updatepopulation"));
		updateCity.setDensity(prop.getProperty("updatedensity"));
		updateCity.setZipcode(prop.getProperty("updatezipcode"));

		payload = gson.toJson(updateCity);
	}

	@Test(dataProvider = "getData")
	public void verifyUpdateCity(String id) {
				
	Response res =  given().
							contentType(ContentType.JSON).
							pathParam("cityid", id).
							body(payload).
					 when().
					 		put("/city/{cityid}").
					 then().
					 		assertThat().statusCode(202).
					 		extract().response();
	
	
	receivedPayload = gson.fromJson(res.asString(), CityPayload.class);
	
	Assert.assertEquals(receivedPayload.getCityname(), prop.getProperty("updatecityname2"));
	Assert.assertEquals(receivedPayload.getStatecode(), prop.getProperty("updatestatecode"));
	Assert.assertEquals(receivedPayload.getStatename(), prop.getProperty("updatestatename"));
	Assert.assertEquals(receivedPayload.getLatitude(), prop.getProperty("updatelatitude"));
	Assert.assertEquals(receivedPayload.getLongitude(), prop.getProperty("updatelongitude"));
	Assert.assertEquals(receivedPayload.getPopulation(), prop.getProperty("updatepopulation"));
	Assert.assertEquals(receivedPayload.getDensity(), prop.getProperty("updatedensity"));
	Assert.assertEquals(receivedPayload.getZipcode(), prop.getProperty("updatezipcode"));
	
	}

	@DataProvider
	public Object[] getData() throws IOException {

		Object[] data = { "" + getCityId() + "" };

		return data;
	}

}
