package facultyModel;

public class Student {

    //TODO: add field "group". It must be changed while adding to some group
    //TODO: separate 'Human' and 'Student' attributes

    private String name;
    private String surname;
    private int age;
    private boolean sex;
    private int course;

    public Student(String name, String surname, int age, boolean sex, int course) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.sex = sex;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getCourse(){
        return course;
    }

    public void setCourse(int course){
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (age != student.age) return false;
        if (name != null ? !name.equals(student.name) : student.name != null) return false;
        if (surname != null ? !surname.equals(student.surname) : student.surname != null) return false;
        if (sex != student.sex) return false;
        return course == student.course;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (sex ? 1 : 0);
        result = 31 * result + course;
        return result;
    }

    @Override
    public String toString() {
        return "Student:" +
                "name= " + name +
                ", surname= " + surname +
                ", age= " + age +
                ", sex= " + (sex? "male" : "female") +
                ", course= " + course;
    }
}
