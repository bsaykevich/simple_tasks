package facultyModel;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Group /*throws FacultyException*/{

   private Student[] students;
   private String name;
   private int course;

   //TODO: add groupNumber and its assignment for every student in group
    //TODO: add int courseNumber. Add 'upgrade()'. 'course' value must suit for every student in group

    public Group() {
        super();
        students = new Student[10];
        name = "undefined";
        course = 0;
    }

    public Group(int groupSize){
        this(groupSize, "undefined", 0);
    }

    public Group(int groupSize, String name, int course){
        this(new Student[groupSize], name, course);
    }

    public Group(Student[] students, String name, int course) {
        this.students = students;
        this.name = name;
        this.course = course;
        repack();
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }


    public int size(){
        int numberOfStudents = 0;

        for (int i = 0; i < students.length; i++){
            if (students[i] != null){
                numberOfStudents++;
            }
        }
        return numberOfStudents;
    }


    public boolean hasStudent(Student student){

        for(Student groupStudent : students){
            if(student.equals(groupStudent)){
                return true;
            }
        }
        return false;
    }

    public void addStudent(Student student){

        if(hasStudent(student)){
            System.out.println("This student is already in group!");
            return;
        }

        if(size() == students.length){
            System.out.println("Group is full. Please, find another group");
        }

        for(int i = 0; i <= students.length; i++){
            if(students[i] == null){
                students[i] = student;
                students[i].setCourse(this.getCourse());
                students[i].setGroupName(this.getName());
                return;
            }
        }
    }

    public void removeStudent(Student student){
        if(students.length == 0){
            System.out.println("There is no one student in the group");
            return;
        }
        for(int i = 0; i < students.length; i++){
            if (students[i].equals(student)){
                students[i].setGroupName("noGroup");
                students[i] = null;
                if(i != students.length-1) {
                    System.arraycopy(students, i+1, students, i, students.length-1 - i);
                }
                return;
            }
        }
        System.out.println("This student is not in this group");
    }

    public void repack(){
        //remove repeats
        for (int i = 0; i < students.length; i++){
            if(students[i] == null){
                continue;
            }
            for(int j = i+1; j < students.length; j++){
                if(students[i].equals(students[j])){
                    students[j] = null;
                }
            }
        }
        //shift records
        for(int i = 0; i < students.length - 1; i++){
            if (students[i] == null){
                System.arraycopy(students, i+1, students, i, students.length-1 - i);
            }
        }
    }

    //writes
    public void toFile(String fileName){

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
            /*for(int i = 0; i < 10; i++){
                writer.write(students[i].toString());
            }*/
            writer.write(toString());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public Group fromFile(String fileName){

        //ArrayList to hold students before create new Group
        ArrayList<Student> newStudents = new ArrayList<>();

        //read file and parse Students` param values from Strings
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String groupName;
            int course;
            String line;

            if((line = reader.readLine()) != null){
                int indexGroupStart = line.indexOf(":");
                int indexGroupFin = line.indexOf("course:") - 2;
                groupName = line.substring(indexGroupStart, indexGroupFin);

                int indexCourseStart = indexGroupFin + 6;
                int indexCourseFin = line.indexOf("course:") - 2;
                course = Integer.valueOf(line.substring(indexCourseStart, line.length()).trim());
            }else {
                System.out.println("The file is empty");
                return null;
            }
            while ((line = reader.readLine()) != null){
                if(!line.trim().startsWith("Student:")){
                    continue;
                }

                // String array to hold parsed Student`s param values before create new Student
                String[] studParams = new String[Student.PARAM_NUMBER];
                int eqPos = 0;
                int comaPos = 0;

                for (int i = 0; i < Student.PARAM_NUMBER - 2; i++) {
                    eqPos = line.indexOf('=');

                    if(eqPos == -1){
                        throw new FacultyException("Incorrect input data");
                    }

                    comaPos = line.indexOf(',', eqPos);
                    if(comaPos == -1){
                        comaPos = line.length();
                    }

                    studParams[i] = line.substring(eqPos, comaPos).trim();
                }

                //create new Student and add it to ArrayList
                try {
                    String name = studParams[1];
                    String surname = studParams[2];
                    int age = Integer.valueOf(studParams[3]);
                    boolean sex = studParams[4].equals("male");
                    if (!studParams[4].equals("female")) {
                        throw new FacultyException("Illegal sex value");
                    }

                    newStudents.add(new Student(name, surname, age, sex, course, groupName));
                } catch (IndexOutOfBoundsException e){
                    throw new FacultyException("Illegal student params in file");
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        //create new Group and add Students
        Group groupFromFile = new Group(newStudents.size());

        for(int i = 0; i < newStudents.size(); i++){
            groupFromFile.addStudent(newStudents.get(i));
        }
        return groupFromFile;
    }



    //TODO: add sorting algorithms by name, surname and age


    @Override
    public String toString() {
        repack();
        StringBuilder sb = new StringBuilder("Group: ");
        sb.append(name);
        sb.append(", course: ");
        sb.append(course);
        sb.append(System.lineSeparator());
        for (int i = 0; i < size(); i++){
            sb.append(students[i].toString());
        }
        return sb.toString();
    }
}
