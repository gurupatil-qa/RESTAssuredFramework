package rest.city.api;

import com.google.gson.annotations.Expose;

public class PartialUpdateCityPayload {

	@Expose
	private String cityname;

	public String getCityname() {
		
		return cityname;
	}

	public void setCityname(String cityname) {
		
		this.cityname = cityname;
	}

}
