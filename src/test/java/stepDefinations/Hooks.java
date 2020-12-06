package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		stepDefination m = new stepDefination();
		if(stepDefination.place_id == null)
		{
		m.add_place_payload_with("Sharful", "Braod Street", 10);
		m.user_calls_using_http_request("AddPlaceAPI", "Post");
		m.verify_place_id_created_maps_to_using("Sharful", "GetPlaceAPI");
		}
		
	}

}
