package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestData {
	
	public AddPlace AddPlacePayload(String name, String address, int accuracy)
	{
		AddPlace p = new AddPlace();
		p.setAccuracy(accuracy);
		p.setAddress(address);
		p.setLanguage("Urdu");
		p.setName(name);
		p.setPhone_number("7278090585");
		p.setWebsite("www.example.com");
		List<String> myList = new ArrayList<String>();
		myList.add("shoe");
		myList.add("slippers");
		p.setTypes(myList);
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		return p;
	}
	
	public String DeletePlacePayload(String place_id)
	{
		return "{\r\n\"place_id\":\""+place_id+"\"\r\n}";
	}

}
