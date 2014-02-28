package challenge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Data {
	String data;
	Data() throws IOException{
		BufferedReader in = new BufferedReader(new FileReader("src/challenge/inputfile.txt"));
		data=in.readLine();
		in.close();
	}
	public JSONArray getData(){
		JSONObject obj = new JSONObject(this.data);
		return obj.getJSONArray("results");
	}

}
