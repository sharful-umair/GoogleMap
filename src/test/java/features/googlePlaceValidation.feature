Feature: Google Add Place Validations

@AddPlace @Regression
Scenario Outline: Verify if the Place Successfully got added using Google API
    Given Add place payload with "<name>", "<address>", <accuracy>
    When User calls "AddPlaceAPI" using "Post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place id created maps to "<name>" using "GetPlaceAPI"
    
Examples:
		| name        | address    | accuracy  |
		| Myhouse     | MyLane     | 10        |
#		| SecondHouse | SecondLane | 20        |


@DeletePlace @Regression
Scenario: Verify place got deleted
    Given Delete Place Payload
    When User calls "DeletePlaceAPI" using "Post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"