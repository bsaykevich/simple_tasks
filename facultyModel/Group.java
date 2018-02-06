package facultyModel;

import java.io.*;
import java.util.Arrays;

public class Group {
    //array holds students` values
   private Student[] students;

   //TODO: add groupNumber and its assignment for every student in group
    //TODO: add int courseNumber. Add 'upgrade()'. 'course' value must suit for every student in group

    public Group() {
        super();
        students = new Student[10];
    }

    public Group(int groupSize){
        students = new Student[groupSize];
    }

    public Group(Student[] students) {
        this.students = students;
        repack();
    }

    public Student[] getStudents() {
        return students;
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

        Student[] newStudents = new Student[10];
        int arrIndex = 0;

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line = new String();
            while ((line = reader.readLine()) != null){
                // TODO: parse line to args
                //String[] args = line.split("=");

                // TODO: create new Student and add to newStudents
                if (arrIndex < 10) {
                    arrIndex++;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return new Group(newStudents);
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

    //TODO: add sorting algorithms by name, surname and age


    @Override
    public String toString() {
        repack();
        StringBuilder sb = new StringBuilder("Group:");
        sb.append(System.lineSeparator());
        for (int i = 0; i < size(); i++){
            sb.append(students[i].toString());
        }
        return sb.toString();
    }
}
