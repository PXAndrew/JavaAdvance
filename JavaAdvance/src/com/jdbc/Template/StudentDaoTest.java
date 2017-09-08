package com.jdbc.Template;

import com.andrew.smis.dao.IStudentDao;
import com.andrew.smis.dao.impl.StudentDaoImpl;
import com.andrew.smis.domain.Student;
import org.junit.Test;

import java.util.List;

public class StudentDaoTest {

    private IStudentDao iStudentDao = new StudentDaoImpl();

    @Test
    public void testSave() {
        Student student = new Student("陆小凤", 22);
        iStudentDao.save(student);
    }

    @Test
    public void testDelete() {
        iStudentDao.delete(4L);
    }

    @Test
    public void testUpdate() {
        Student student = new Student();
        student.setId(5l);
        student.setName("JiaojianSB");
        student.setAge(26);
        iStudentDao.update(student);
    }

    @Test
    public void testGet() {
        Student student = iStudentDao.get(1L);
        System.out.println(student);
    }

    @Test
    public void testList() {
        List<Student> studentList = iStudentDao.list();
        System.out.println(studentList);
    }

    public static void main(String[] args) {


        StudentDaoTest studentDaoTest = new StudentDaoTest();
        studentDaoTest.testList();

    }


}
