package models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
public class School {
    private String name;

    @XmlElement(name = "students")
    private ArrayList<Student> students;

    @XmlElement(name = "teachers")
    private ArrayList<Teacher> teachers;

    public School() {
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
    }

    public School(String name) {
        this.name = name;
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }
    public void addTeacher(Teacher teacher){
        this.teachers.add(teacher);
    }
    public void addStudent(Student student){
        this.students.add(student);
    }

    public void removeTeacher(Teacher teacher){
        this.teachers.remove(teacher);
    }
    public void removeStudent(Student student){
        this.students.remove(student);
    }

    public ArrayList<Teacher> getTeachers(){
        return teachers;
    }
    public ArrayList<Student> getStudents(){
        return students;
    }
    @Override
    public String toString(){
        return this.name;
    }
}