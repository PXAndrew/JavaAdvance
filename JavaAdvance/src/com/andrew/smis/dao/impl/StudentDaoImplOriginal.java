package com.andrew.smis.dao.impl;

import com.andrew.smis.dao.IStudentDao;
import com.andrew.smis.domain.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImplOriginal implements IStudentDao {

	@Override
    public void save(Student student) {

	    String sqlString = "INSERT INTO s_student (name, age) VALUES ('" + student.getName() + "', " + student.getAge() + ")";
	    System.out.print(sqlString);
	    Connection connection = null;
	    Statement statement = null;

	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbcdemo", "root", "1Csandrew");
	        statement = connection.createStatement();
	        statement.executeUpdate(sqlString);
        } catch (Exception e) {
	        e.printStackTrace();

        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void delete(Long id) {
        String sqlString = "DELETE FROM s_student WHERE id = " + id;

        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbcdemo", "root", "1Csandrew");
            statement = connection.createStatement();
            statement.executeUpdate(sqlString);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(Student student) {
	    String sqlString = "UPDATE s_student SET name = '" + student.getName() + "', age = '" + student.getAge() + "' WHERE id = " + student.getId();

	    Connection connection = null;
	    Statement statement = null;
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbcdemo", "root", "1Csandrew");
	        statement = connection.createStatement();
	        statement.executeUpdate(sqlString);
        } catch (Exception e) {
	        e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Student get(Long id) {
        String sqlString = "SELECT * FROM s_student WHERE id = " + id;
        Student student = new Student();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo?user=root&password=1Csandrew&useUnicode=true&characterEncoding=UTF-8");
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlString);
            if (resultSet.next()) {
            		student.setId(resultSet.getLong("id"));
            		student.setName(resultSet.getString("name"));
            		student.setAge(resultSet.getInt("age"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return student;
    }

    @Override
    public List<Student> list() {
        String sqlString = "SELECT * FROM s_student";
        List<Student> studentList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbcdemo?user=root&password=1Csandrew&useUnicode=true&characterEncoding=UTF-8");
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlString);

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
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return studentList;
    }
}
