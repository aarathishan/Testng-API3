package org.petstore;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import org.data.PetDetails;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class RestServices {
	static int petid;
	@DataProvider(name="pet")
	public Object[][] getPet(){
		return new Object[][] {{268,"rocky"},{278,"peter"},{288,"danny"}};
	}
    
	@Test(priority=1,dataProvider="pet")
	public void addPet(int id,String name) {
		 
		RestAssured.baseURI="https://petstore.swagger.io/v2/";
		//add a new pet
		String postResponse=given().log().all().header("Content-Type","application/json").body(PetDetails.petData(id,name))
		.when().post("/pet")
		.then().assertThat().statusCode(200).extract().response().asString();
		System.out.println("response :"+postResponse);
		JsonPath j=new JsonPath(postResponse);
		petid=j.get("id");
		System.out.println("pet id:"+petid);
	}
	
	@Test(priority=2)
	public void retrievePet() {
		// get petid
		given().log().all().header("Content-Type","application/json").pathParam("id",petid)
		.when().get("/pet/{id}")
        .then().log().all().assertThat().statusCode(200);
	}
		
	@Test(priority=3)
	public void updatePet() {
		//update an existing pet
		given().log().all().header("Content-Type","application/json").body(PetDetails.petData(298,"crabby"))
		.when().put("/pet")
		.then().log().all().assertThat().statusCode(200);
	}
	@Test(priority=4)
	public void petByStatus() {		
	   //find pet by status
		given().log().all().header("Content-Type","application/json").queryParam("status","sold")
		.when().get("/pet/findByStatus")
		.then().log().all().assertThat().statusCode(200);
	}

}
