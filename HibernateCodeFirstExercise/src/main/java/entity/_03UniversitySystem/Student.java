package entity._03UniversitySystem;

import javax.persistence.*;
import java.util.Set;


@Entity(name = "students")
public class Student extends Person {

    @Column(name = "average_grade", nullable = false)
    private float averageGrade;

    private int attendance;

    @ManyToMany
    @JoinTable(name = "student_courses", joinColumns = @JoinColumn(name = "students_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "courses_id", referencedColumnName = "id"))
    private Set<Course> courses;

    public float getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(float averageGrade) {
        this.averageGrade = averageGrade;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}