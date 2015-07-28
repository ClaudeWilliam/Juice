package com.gbros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.gbros.modle.Student;
import com.gbros.service.StudentService;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Student> list() {

		return studentService.getListStudent();
	}

	@RequestMapping(method = RequestMethod.DELETE)
	@ResponseBody
	public List<Student> delete(@RequestBody String text) {
		Student student = (Student) JSON.parseObject(text, Student.class);
		studentService.deleteById(student.getId());
		return studentService.getListStudent();
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public List<Student> update(@RequestBody String text) {
		Student student = JSON.parseObject(text, Student.class);
		System.out.println(student);
		studentService.updateById(student.getName(), student.getGrade(),
				student.getLocation(), student.getId());
		return studentService.getListStudent();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public List<Student> save(@RequestBody String text) {
		System.out.println(text);
		Student student = JSON.parseObject(text, Student.class);
		System.out.println("-------------------" + student);
		studentService.Save(student.getId(), student.getName(),
				student.getGrade(), student.getLocation());
		return studentService.getListStudent();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Student getStudentById(@PathVariable("id") String Id) {
		System.out.println(Id);
		return studentService.getStudentByid(Id);

	}
}
