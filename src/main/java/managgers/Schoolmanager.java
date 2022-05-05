package managgers;

import models.School;
import models.Teacher;

import javax.xml.bind.annotation.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

@XmlRootElement(name = "SM")
@XmlAccessorType(XmlAccessType.FIELD)
public class Schoolmanager {

    @XmlElement(name = "school")
    private HashMap<Integer, School> schools;

    public Schoolmanager() {
        this.schools = new HashMap<>();
    }

    public void addSchool(School school) {
        Random rnd = new Random();

        int id = rnd.nextInt(100000);
        for (Integer schoolId: schools.keySet()) {
            if (schoolId.equals(id)){
                addSchool(school);
            }
        }
        schools.put(id, school);
    }

    public HashMap<Integer, School> getSchools() {
        return schools;
    }

    @Override
    public String toString(){
        return this.schools.toString();
    }
}