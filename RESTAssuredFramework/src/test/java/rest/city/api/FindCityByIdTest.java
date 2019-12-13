package rest.city.api;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import resources.Base;

public class FindCityByIdTest extends Base {
	
	private Properties prop;
	
	@BeforeClass
	public void setup() throws IOException {
		
		prop = getGlobalVariable();
		RestAssured.baseURI = prop.getProperty("baseUrl");		
	}

	@Test(dataProvider = "getData")
	public void verifyFindCity(String id) {
				
	Response res  = given().
							pathParam("cityid", id).
					 when().
					 		get("/city/{cityid}").
					 then().
					 		assertThat().statusCode(200).extract().response();
	
	Gson gson = new Gson();
	CityPayload receivedResponse = gson.fromJson(res.asString(), CityPayload.class);
	
	Assert.assertEquals(receivedResponse.getId(), id);
	Assert.assertEquals(receivedResponse.getCityname(), prop.getProperty("cityname"));
	Assert.assertEquals(receivedResponse.getStatecode(), prop.getProperty("statecode"));
	Assert.assertEquals(receivedResponse.getStatename(), prop.getProperty("statename"));
	Assert.assertEquals(receivedResponse.getLatitude(), prop.getProperty("latitude"));
	Assert.assertEquals(receivedResponse.getLongitude(), prop.getProperty("longitude"));
	Assert.assertEquals(receivedResponse.getPopulation(), prop.getProperty("population"));
	Assert.assertEquals(receivedResponse.getDensity(), prop.getProperty("density"));
	Assert.assertEquals(receivedResponse.getZipcode(), prop.getProperty("zipcode"));
		
	}
	
	@DataProvider
	public Object[] getData() throws IOException {
		
		Object[] data = {""+getCityId()+""};		
		return data;
		
	}	
}
