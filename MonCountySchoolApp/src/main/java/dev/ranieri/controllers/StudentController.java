package dev.ranieri.controllers;

import java.util.Set;

import com.google.gson.Gson;

import dev.ranieri.entities.Student;
import dev.ranieri.services.SchoolService;
import dev.ranieri.services.SchoolServiceImpl;
import dev.ranieri.services.StudentService;
import dev.ranieri.services.StudentServiceImpl;
import io.javalin.http.Handler;

public class StudentController {
	
	private static StudentService stuServ = new StudentServiceImpl();
	private static SchoolService schServ= new SchoolServiceImpl();
	private static Gson gson = new Gson();
	
	// ctx Context object that Javalin gives us to get information from the HTTP request and form an HTTP response
	public static Handler getAllStudentsFromSchoolId = (ctx)->{
		
		int schoolId = Integer.parseInt(ctx.pathParam("id"));	
		Set<Student> students = stuServ.getStudentsBySchoolId(schoolId);
		String json = gson.toJson(students);
		
		ctx.result(json);
	};
	
	public static Handler getStudentById = (ctx)->{
		int studentId = Integer.parseInt(ctx.pathParam("stuid"));
		Student student = stuServ.getStudentById(studentId);
		String json = gson.toJson(student);
		
		ctx.result(json);
	};
	
	public static Handler createStudent = (ctx)->{
		int schoolId = Integer.parseInt(ctx.pathParam("id"));	
		Student student = gson.fromJson(ctx.body(), Student.class); // convert the JSON body of the request into an object
		student.setsId(schoolId);
		stuServ.enrollStudent(student);
		
		String json = gson.toJson(student);
		ctx.status(201);
		
		ctx.result(json);	
	};
	
	public static Handler updateStudent = (ctx)->{
		Student student = gson.fromJson(ctx.body(), Student.class);
		stuServ.updateStudent(student);
		String json = gson.toJson(student);
		ctx.result(json);
	};
	
	
	public static Handler deleteStudent = (ctx) ->{
		int studentId = Integer.parseInt(ctx.pathParam("stuid"));
		Boolean result = stuServ.deleteStudent(studentId);
		ctx.result(result.toString());
		
	};
	


}
