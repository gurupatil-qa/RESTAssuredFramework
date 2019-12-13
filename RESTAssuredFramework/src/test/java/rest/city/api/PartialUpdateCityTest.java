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
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import resources.Base;

public class PartialUpdateCityTest extends Base {

	private PartialUpdateCityPayload partialUpdateCity;
	private CityPayload cityPayload;
	private Properties prop;
	private Gson gson;

	@BeforeClass
	public void setup() throws IOException {

		gson = new Gson();
		prop = getGlobalVariable();
		RestAssured.baseURI = prop.getProperty("baseUrl");

		partialUpdateCity = new PartialUpdateCityPayload();
		partialUpdateCity.setCityname(prop.getProperty("updatecityname1"));

	}

	@Test(dataProvider = "getData")
	public void verifyPartialUpdateCity(String id) {
				
	Response res =  given().
							contentType(ContentType.JSON).
							pathParam("cityid", id).
							body(partialUpdateCity).
					 when().
					 		patch("/city/{cityid}").
					 then().
					 		assertThat().
					 		statusCode(202).
					 		extract().response();
	
	cityPayload = gson.fromJson(res.asString(), CityPayload.class);
	
	Assert.assertEquals(cityPayload.getCityname(), prop.getProperty("updatecityname1"));
	
	}

	@DataProvider
	public Object[] getData() throws IOException {

		Object[] data = { "" + getCityId() + "" };

		return data;
	}

}
