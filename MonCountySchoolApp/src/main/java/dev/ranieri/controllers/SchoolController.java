package dev.ranieri.controllers;

import java.util.Set;

import com.google.gson.Gson;

import dev.ranieri.entities.School;
import dev.ranieri.services.SchoolService;
import dev.ranieri.services.SchoolServiceImpl;
import io.javalin.http.Handler;

public class SchoolController {

	public static SchoolService sserv = new SchoolServiceImpl();
	private static Gson gson = new Gson();
	
	// will return all schools as a JSON
	public static Handler getAllSchools = (ctx) ->{
		Set<School> schools = sserv.getAllSchools();				
		String json = gson.toJson(schools);	
		
		String capQ = ctx.queryParam("capcitylessthan");
		
		if(capQ != null) {
			Set<School> smallSchools = sserv.getSchoolsByCapacityLessThan(Integer.parseInt(capQ));
			ctx.result(gson.toJson(smallSchools));
		}else {
			ctx.result(json);	
		}
		
			
	};
	
	// your services should not have to deal with JSONs only your controllers
	public static Handler createSchool= (ctx) ->{
		//context object ctx is an object that contains the http request and response objects
		// it contains a whole bunch of method for dealing with getting information from the request
		// and sending information in the http response
		
		String schoolJson = ctx.body();
		School school = gson.fromJson(schoolJson, School.class); // transform the json into a Shcool object
		sserv.establishSchool(school);
		ctx.status(201); // 201 is the status code to return if you successfully add something
		
		//usually you want to return the created object
		ctx.result(gson.toJson(school));
	};
	
	
	public static Handler getSchoolById = (ctx)->{
		String id = ctx.pathParam("sid");
		School school = sserv.getSchoolById(Integer.parseInt(id));
		//school object but I want it sent back as a JSON		
		String json = gson.toJson(school);
		ctx.result(json);	
	};
	
	public static Handler updateSchool = (ctx)->{
		String schoolJson = ctx.body();
		School school = gson.fromJson(schoolJson, School.class);
		sserv.updateSchool(school);	
		ctx.result(gson.toJson(school));
	};
	
	public static Handler deleteSchool = (ctx) ->{
		String id = ctx.pathParam("sid");
		boolean result = sserv.deleteSchoolById(Integer.parseInt(id));
		
	};
	
	
}
