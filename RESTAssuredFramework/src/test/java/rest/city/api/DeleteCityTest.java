package rest.city.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import resources.Base;

public class DeleteCityTest extends Base{
	
	@BeforeClass
	public void setup() throws IOException {
		
		Properties prop = getGlobalVariable();
		
		RestAssured.baseURI = prop.getProperty("baseUrl");
	}

	@Test(dataProvider = "getData")
	public void verifyDeleteCity(String id) {
				
		             given().
							pathParam("cityid", id).
					 when().
					 		delete("/city/{cityid}").
					 then().
					 		assertThat().statusCode(202).body(equalTo("City successfully deleted with ID:"+id+""));
					
	}
	
	@DataProvider
	public Object[] getData() throws IOException {
		
		Object[] data = {""+getCityId()+""};
		
		return data;
	}


}
