import java.io.File;
import java.util.Map;
import java.util.Scanner;


import managgers.Schoolmanager;
        import models.School;
        import models.Student;
        import models.Teacher;
        import javax.xml.bind.JAXBContext;
        import javax.xml.bind.Marshaller;
        import javax.xml.bind.Unmarshaller;
        import java.io.File;
        import java.util.Map;
        import java.util.Scanner;

public class App {

    public static Schoolmanager sm = new Schoolmanager();
    public static Menu menu = new Menu();

    static class XmlManagger {
        static File xmlFile = new File("SchoolManagment.xml");

        public static void xmlLoad() {
            try {
                JAXBContext jc = JAXBContext.newInstance(Schoolmanager.class);
                Unmarshaller unmarshaller = jc.createUnmarshaller();
                sm = (Schoolmanager) unmarshaller.unmarshal(xmlFile);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        public static void xmlWrite() {
            try {
                JAXBContext context = JAXBContext.newInstance(Schoolmanager.class);
                Marshaller marsh = context.createMarshaller();
                marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

                marsh.marshal(sm, new File(String.valueOf(xmlFile)));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }


    public static class Menu {

        public int menuPontBeker(int max){
            Scanner s = new Scanner(System.in);
            System.out.println("Válasszon menüpontot(sorszámot): ");
            int sorszam =  s.nextInt();
            if(max<sorszam || 1>sorszam){
                System.out.println("Nem létezik ilyen menüpont!");
                menuPontBeker(max);
            }
            return sorszam;
        }
        public void mainMenu(){
            System.out.println("""
                    ===Iskola Project===
                1.Iskola hozzáadása / törlése / listázása.
                2.Tanár hozzáadása / törlése / listázása.
                3.Diák hozzáadása / törlése / listázása.
                4.Kilépés.
                """);
            switch (menuPontBeker(4)) {
                case 1 -> iskolaSubMenu();
                case 2 -> teacherSubMenu();
                case 3 -> studentSubMenu();
                case 4 -> System.exit(0);
            }
            mainMenu();
        }

        // =============================
        // ||   SCHOOL MENU METHODS   ||
        // =============================

        public void iskolaSubMenu(){
            System.out.println("""
                ===Iskola Project Iskolák===
                1.Iskolák listázása.
                2.Iskola hozzáadása.
                3.Iskola törlése.
                4.Vissza.""");
            switch (menuPontBeker(4)) {
                case 1 -> schoolListingMenuPoint();
                case 2 -> schoolAddMenuPoint();
                case 3 -> schoolRemoveMenuPoint();
                case 4 -> mainMenu();
            }
            iskolaSubMenu();
        }
        private void schoolListingMenuPoint() {
            listSchoolsByUser();
            iskolaSubMenu();
        }
        private void schoolAddMenuPoint() {
            addSchoolByUser();
            iskolaSubMenu();
        }
        private void schoolRemoveMenuPoint() {
            removeSchoolByUser();
            iskolaSubMenu();
        }


        // ==========================
        // ||   TEACHER METHODS    ||
        // ==========================

        public void teacherSubMenu(){
            System.out.println("""
                    ===Iskola Project Tanárok Almenü===
                1.Tanárok listázása.
                2.Tanár hozzáadása.
                3.Tanár törlése.
                4.Vissza.""");
            switch (menuPontBeker(4)) {
                case 1 -> teacherListingMenuPoint();
                case 2 -> teacherAddMenuPoint();
                case 3 -> teacherRemoveMenuPoint();
                case 4 -> mainMenu();
            }
            teacherSubMenu();
        }
        private void teacherListingMenuPoint() {
            listTeachersByUser();
            teacherSubMenu();
        }
        private void teacherAddMenuPoint() {
            addTeacherByUser();
            teacherSubMenu();
        }
        private void teacherRemoveMenuPoint() {
            removeTeacherByUser();
            teacherSubMenu();
        }


        // ==========================
        // ||   STUDENT METHODS    ||
        // ==========================

        public void studentSubMenu(){
            System.out.println("""
                    ===Iskola Project Diákok Almenü===
                1.Diákok listázása.
                2.Diák hozzáadása.
                3.Diák törlése.
                4.Vissza.""");
            switch (menuPontBeker(4)) {
                case 1 -> studentListingMenuPoint();
                case 2 -> studentAddMenuPoint();
                case 3 -> studentRemoveMenuPoint();
                case 4 -> mainMenu();
            }
            studentSubMenu();
        }
        private void studentListingMenuPoint() {
            listStudentsByUser();
            studentSubMenu();
        }
        private void studentAddMenuPoint() {
            addStudentByUser();
            studentSubMenu();
        }
        private void studentRemoveMenuPoint() {
            removeStudentByUser();
            studentSubMenu();
        }

    }

    void run(){
        XmlManagger.xmlLoad();
        menu.mainMenu();
    }

    // ========================
    // ||   ADDING METHODS   ||
    // ========================

    static void addSchool(School school){
        sm.addSchool(school);
        XmlManagger.xmlWrite();
    }
    static void addTeacher(School school, Teacher teacher){
        school.addTeacher(teacher);
        XmlManagger.xmlWrite();
    }
    static void addStudent(School school, Student student){
        school.addStudent(student);
        XmlManagger.xmlWrite();
    }


    // =============================
    // ||   USER ADDING METHODS   ||
    // =============================

    static void addSchoolByUser(){
        System.out.println("Kérem irja be az iskola nevét:");
        addSchool(new School(new Scanner(System.in).nextLine()));
    }
    static void addTeacherByUser(){
        School choosedSchool = chooseSchool();

        Scanner s = new Scanner(System.in);
        System.out.print("Kérem irja be a tanár nevét: ");
        String nev = s.nextLine();
        System.out.print("Kérem irja be a tanár korát: ");
        int age = s.nextInt();
        System.out.print("Kérem irja be a tanár fizetését: ");
        int salary = s.nextInt();
        addTeacher(choosedSchool, new Teacher(nev, age, salary));
    }
    static void addStudentByUser(){
        School choosedSchool = chooseSchool();

        Scanner s = new Scanner(System.in);
        System.out.print("Kérem irja be a diák nevét: ");
        String nev = s.nextLine();
        System.out.print("Kérem irja be a diák korát: ");
        int age = s.nextInt();
        System.out.print("Kérem irja be a diák átlagát(pl:3,4): ");
        float average = s.nextFloat();
        addStudent(choosedSchool, new Student(nev, age, average));
    }


    // ========================
    // ||   REMOVE METHODS   ||
    // ========================

    static void removeSchool(School school){
        sm.getSchools().values().remove(school);
        XmlManagger.xmlWrite();
    }
    static void removeTeacher(School school, Teacher teacher){
        school.removeTeacher(teacher);
        XmlManagger.xmlWrite();
    }
    static void removeStudent(School school, Student student){
        school.removeStudent(student);
        XmlManagger.xmlWrite();
    }

    // =============================
    // ||   USER REMOVE METHODS   ||
    // =============================

    static void removeSchoolByUser(){
        School choosed_school = chooseSchool();
        removeSchool(choosed_school);
    }

    static void removeTeacherByUser(){
        School choosed_school = chooseSchool();
        removeTeacher(choosed_school, chooseTeacher(choosed_school));
    }

    static void removeStudentByUser(){
        School choosed_school = chooseSchool();
        removeStudent(choosed_school, chooseStudent(choosed_school));
    }



    // ===========================
    // ||   CHOOSING METHODS   ||
    // ===========================

    static School chooseSchool(){
        try {
            Scanner s = new Scanner(System.in);
            System.out.println("Kérem válasszon iskolát az id megadásával:");
            listSchools();
            return sm.getSchools().get(s.nextInt());
        }
        catch (Exception e){
            System.out.println("Hibás érték, kérem próbálja újra: ");
            return chooseSchool();
        }
    }

    static Student chooseStudent(School school){
        try {
            Scanner s = new Scanner(System.in);
            System.out.println("Kérem válasszon diákot az id megadásával:");
            listStudents(school);
            return school.getStudents().get(s.nextInt()-1);
        }
        catch (Exception e){
            System.out.println("Hibás érték, kérem próbálja újra: ");
            return chooseStudent(school);
        }
    }

    static Teacher chooseTeacher(School school){
        try {
            Scanner s = new Scanner(System.in);
            System.out.println("Kérem válasszon tanárt az id megadásával:");
            listTeachers(school);
            return school.getTeachers().get(s.nextInt()-1);
        }
        catch (Exception e){
            System.out.println("Hibás érték, kérem próbálja újra: ");
            return chooseTeacher(school);
        }
    }


    // ==========================
    // ||   PRINTING METHODS   ||
    // ==========================

    static void listSchools(){
        for (Map.Entry<Integer, School> is: sm.getSchools().entrySet()){
            System.out.println("\t" + (is.getKey()) + ": " + is.getValue());
        }
    }

    static void listTeachers(School school){
        for (int i = 0; i < school.getTeachers().size(); i++) {
            System.out.println((i + 1) + ". " + school.getTeachers().get(i));
        }
    }

    static void listStudents(School school){
        for (int i = 0; i < school.getStudents().size(); i++) {
            System.out.println((i + 1) + ". " + school.getStudents().get(i));
        }
    }


    // ==================================
    // ||   PRINTING BY USER METHODS   ||
    // ==================================

    static void listSchoolsByUser(){
        listSchools();
    }

    static void listTeachersByUser(){
        School choosed_school = chooseSchool();
        listTeachers(choosed_school);
    }

    static void listStudentsByUser(){
        School choosed_school = chooseSchool();
        listStudents(choosed_school);
    }


}