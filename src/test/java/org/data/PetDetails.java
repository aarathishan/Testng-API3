package org.data;

public class PetDetails {
	
	public static String petData(int id,String name)
	{
		return"{\r\n" + 
				"  \"id\": "+id+",\r\n" + 
				"  \"category\": {\r\n" + 
				"    \"id\": 16,\r\n" + 
				"    \"name\": \"dog\"\r\n" + 
				"  },\r\n" + 
				"  \"name\": \""+name+"\",\r\n" + 
				"  \"photoUrls\": [\r\n" + 
				"    \"string\"\r\n" + 
				"  ],\r\n" + 
				"  \"tags\": [\r\n" + 
				"    {\r\n" + 
				"      \"id\": 1,\r\n" + 
				"      \"name\": \"Dolmation\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"id\": 2,\r\n" + 
				"      \"name\": \"Dolmation2\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"id\": 3,\r\n" + 
				"      \"name\": \"Dolmation3\"\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"status\": \"available\"\r\n" + 
				"}";
	}

}
