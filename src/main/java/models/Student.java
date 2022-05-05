package models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Student extends Person {
    private float average;

    public Student() {
    }

    public Student(String name, int age, float average) {
        super(name, age);
        this.average = average;
    }

    public float getAverage() {
        return average;
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.average;
    }
}