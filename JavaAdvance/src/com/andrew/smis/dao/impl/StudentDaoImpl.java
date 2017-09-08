package com.andrew.smis.dao.impl;

import com.andrew.smis.dao.IStudentDao;
import com.andrew.smis.domain.Student;
import com.andrew.smis.util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements IStudentDao {

	@Override
    public void save(Student student) {

	    Connection connection = null;
        PreparedStatement preparedStatement = null;
	    try {
	        connection = JdbcUtil.createConnection();
            preparedStatement = connection.prepareStatement("INSERT INT s_student (name, age) VALUES (?, ?)");
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
	        e.printStackTrace();
        } finally {
            JdbcUtil.closeConnection(connection, preparedStatement, null);
        }
    }

    @Override
    public void delete(Long id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtil.createConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM s_student WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeConnection(connection, preparedStatement, null);
        }
    }

    @Override
    public void update(Student student) {

	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    try {
	        connection = JdbcUtil.createConnection();
            preparedStatement = connection.prepareStatement("UPDATE  s_student SET name = ?, age = ? WHERE id = ?");
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setLong(3, student.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
	        e.printStackTrace();
        } finally {
            JdbcUtil.closeConnection(connection, preparedStatement, null);
        }
    }

    @Override
    public Student get(Long id) {

        Student student = new Student();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtil.createConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM s_student WHERE id = ?");
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
            		student.setId(resultSet.getLong("id"));
            		student.setName(resultSet.getString("name"));
            		student.setAge(resultSet.getInt("age"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeConnection(connection, preparedStatement, resultSet);
        }
        return student;
    }

    @Override
    public List<Student> list() {

        List<Student> studentList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtil.createConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM s_student");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                studentList.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeConnection(connection, preparedStatement, resultSet);
        }
        return studentList;
    }

    public static void main(String[] args) {
	    StudentDaoImpl studentDao = new StudentDaoImpl();
	    List<Student> list = studentDao.list();
        System.out.println(list);
    }

}
