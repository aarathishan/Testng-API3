package org.petstore;

import static io.restassured.RestAssured.given;

import org.data.PetDetails;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Normal {

	public static void main(String[] args) {
		
		RestAssured.baseURI="https://petstore.swagger.io/v2/";
		//add a new pet
		String postResponse=given().log().all().header("Content-Type","application/json").body(PetDetails.petData(298,"name"))
		.when().post("/pet")
		.then().assertThat().statusCode(200).extract().response().asString();
		System.out.println("response :"+postResponse);
		JsonPath j=new JsonPath(postResponse);
		int petid=j.get("id");
		String name=j.getString("clarie");
		String status=j.getString("status");
		System.out.println("pet id:"+petid);
		System.out.println(name);
		System.out.println(status);
		int catid=j.get("category.id");
		String dogid=j.get("category.name");
		System.out.println(catid);
		System.out.println(dogid);
		int tagid=j.get("tags[0].id");
		System.out.println(tagid);
		
		int size=j.get("tags.size()"); ;
		for(int i=0;i<size;i++)
		{
			System.out.println(j.get("tags["+i+"].id"));
			System.out.println(j.get("tags["+i+"].name"));
		}
	}

}
