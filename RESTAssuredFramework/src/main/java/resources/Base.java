package resources;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Base {

	public Properties getGlobalVariable() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(".\\globalconfig\\globalconfig.properties");
		prop.load(fis);
		return prop;
	}

	public void preserveCityId(String id) throws IOException {

		BufferedWriter writer = null;

		try {
			writer = new BufferedWriter(new FileWriter(".//testdata//cityid"));
			writer.write(id);

		} finally {

			writer.close();
		}
	}

	public String getCityId() throws IOException {

		BufferedReader reader = null;
		String cityId;

		try {
			reader = new BufferedReader(new FileReader(".//testdata//cityid"));
			cityId = reader.readLine();

		} finally {

			reader.close();
		}

		return cityId;
	}

}
