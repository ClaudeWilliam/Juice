package com.gbros.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.pool.DruidDataSource;
import com.gbros.modle.Student;
import com.gbros.utils.DAO;

@Service("studentService")
public class StudentService {
	@Autowired
	private DataSource dataSource;
	@Autowired
	private DAO dao;

	public Student getStudentByid(String Id) {
		String sql = "select * from stu where id=?;";
		return dao.select(Student.class, sql, Id);
	}

	public List<Student> getListStudent() {
		String sql = "select * from stu;";
		return dao.selectList(Student.class, sql);
	}

	public void deleteById(String Id) {
		String sql = "delete from stu where id=?;";
		dao.delete(sql, Id);
	}

	public void updateById(String name, String grade, String location, String id) {
		String sql = "update stu set name=?,grade=?,location=? where id=?";
		dao.update(sql, name, grade,location, id);
	}
	public void Save(String id,String name,String grade,String location){
		String sql="INSERT into stu(id,name,grade,location) values(?,?,?,?);";
		dao.insert(sql, id,name,grade,location);
		
	}

}
