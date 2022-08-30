package org.jira;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class Jira3 {
	
	
	String jira_id;
	SessionFilter session=new SessionFilter();
	
	  @Test(priority=1)
	  public void session(){
	//session creation
			RestAssured.baseURI="http://localhost:8080";
			String sessionResponse=given().log().all().header("Content-Type","application/json")
			.body(IssueCreation.credentials("name","pwd")).filter(session)
	        .when().post("/rest/auth/1/session")
	        .then().log().all().assertThat().statusCode(200).extract().response().asString();
	  }	
	  @Test	(priority=2)
	  public void createIssue() {
	  //create an issue
			String issueResponse=given().log().all().header("Content-Type","application/json").filter(session)
			.body(IssueCreation.issueCreation("key","value"))
			.when().post("/rest/api/2/issue")
			.then().log().all().assertThat().statusCode(201).extract().asString();
			JsonPath j =new JsonPath(issueResponse);
			jira_id=j.get("id");
	  }	
	  @Test	(priority=3)	
	  public void addComment() {     
	        //add comment
			given().log().all().pathParam("jira_id", jira_id).header("Content-Type","application/json").filter(session)
			.body(IssueCreation.comment("type", "value"))
			.when().post("/rest/api/2/issue/{jira_id}/comment")
			.then().log().all().assertThat().statusCode(201).extract().response().asString();
	  }
	  @Test (priority=4)
	  public void attachment() {
			//add attachments
			given().log().all().pathParam("jira_id", jira_id).header("Content-Type","multipart/form-data")
			.header("X-Atlassian-Token","no-check").filter(session)
			.multiPart(new File(".//jira.txt"))
			.when().post("/rest/api/2/issue/{jira_id}/attachments")
			.then().log().all().assertThat().statusCode(200);
	  }	
	  @Test (priority=5)
	  public void getIssue() {
			//get issue
			given().log().all().pathParam("jira_id", jira_id).header("Content-Type","application/json").filter(session)
			.when().get("/rest/api/2/issue/{jira_id}")
			.then().log().all().assertThat().statusCode(200);
	  }
	  @Test (priority=6)
	  public void getComments() {
			//get comments
			given().log().all().pathParam("jira_id", jira_id).header("Content-Type","application/json").filter(session)
			.when().get("/rest/api/2/issue/{jira_id}/comment")
			.then().log().all().assertThat().statusCode(200);
	  }
}
