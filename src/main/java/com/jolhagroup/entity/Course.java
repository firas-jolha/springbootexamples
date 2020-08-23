package com.jolhagroup.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_id")
    @Id
    private int courseId;

    @JsonIgnore(value = true)
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses", cascade = {CascadeType.ALL})
    private Collection<Student> students;

    public Course(String courseName, int courseId, Collection<Student> students) {
        this.courseName = courseName;
        this.courseId = courseId;
        this.students = students;
    }

    public Course() {

    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }

    public void setCourse(Course c){
        this.setStudents(c.students);
        this.setCourseName(c.courseName);
        this.setCourseId(c.courseId);
    }

}
