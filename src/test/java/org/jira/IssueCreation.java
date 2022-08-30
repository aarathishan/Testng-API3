package org.jira;

public class IssueCreation {

	public static String credentials(String name,String pwd) {
		
		return "{ \"username\": \"aarathi.kalai\", \"password\": \"anithajoshni\" }";

	}
	
	public static String issueCreation(String key,String value) {
		
		return "{\r\n" + 
				"    \"fields\": {\r\n" + 
				"       \"project\":\r\n" + 
				"       {\r\n" + 
				"          \"key\": \"RES\"\r\n" + 
				"       },\r\n" + 
				"       \"summary\": \"Login functionalities are not working.\",\r\n" + 
				"       \"description\": \"Creating of an issue using ids for projects and issue types using the REST API\",\r\n" + 
				"       \"issuetype\": {\r\n" + 
				"          \"name\": \"Bug\"\r\n" + 
				"       }\r\n" + 
				"   }\r\n" + 
				"}";
	}
	
public static String comment(String type,String value) {
		
		return "{\r\n" + 
				"    \"body\": \"This is the first comment from eclipse tool.\",\r\n" + 
				"    \"visibility\": {\r\n" + 
				"        \"type\": \"role\",\r\n" + 
				"        \"value\": \"Administrators\"\r\n" + 
				"    }\r\n" + 
				"}";
	}

}
