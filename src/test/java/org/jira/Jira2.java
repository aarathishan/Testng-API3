package org.jira;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class Jira2 {

	public static void main(String[] args) {
		
		
		//session creation
		SessionFilter session=new SessionFilter();
		RestAssured.baseURI="http://localhost:8080";
		String sessionResponse=given().log().all().header("Content-Type","application/json")
		.body(IssueCreation.credentials("name","pwd")).filter(session)
        .when().post("/rest/auth/1/session")
        .then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		//create an issue
		String issueResponse=given().log().all().header("Content-Type","application/json").filter(session)
		.body(IssueCreation.issueCreation("key","value"))
		.when().post("/rest/api/2/issue")
		.then().log().all().assertThat().statusCode(201).extract().asString();
		JsonPath j =new JsonPath(issueResponse);
		String jira_id=j.get("id");
		
		//add comment
		given().log().all().pathParam("jira_id", jira_id).header("Content-Type","application/json").filter(session)
		.body(IssueCreation.comment("type", "value"))
		.when().post("/rest/api/2/issue/{jira_id}/comment")
		.then().log().all().assertThat().statusCode(201).extract().response().asString();
		
		//add attachments
		given().log().all().pathParam("jira_id", jira_id).header("Content-Type","multipart/form-data")
		.header("X-Atlassian-Token","no-check").filter(session)
		.multiPart(new File(".//jira.txt"))
		.when().post("/rest/api/2/issue/{jira_id}/attachments")
		.then().log().all().assertThat().statusCode(200);
		
		//get issue
		given().log().all().pathParam("jira_id", jira_id).header("Content-Type","application/json").filter(session)
		.when().get("/rest/api/2/issue/{jira_id}")
		.then().log().all().assertThat().statusCode(200);
		
		//get comments
		given().log().all().pathParam("jira_id", jira_id).header("Content-Type","application/json").filter(session)
		.when().get("/rest/api/2/issue/{jira_id}/comment")
		.then().log().all().assertThat().statusCode(200);
	}

}
