package com.jolhagroup.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Data
@Entity
@Table(name="students")
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Cloneable {

    @Id
    @Column(name="student_id")
    private int id;

    @Column(name="student_name")
    private String name;

    @JsonIgnore(value = true)
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="students_courses",
            joinColumns = {@JoinColumn(name="student_id")},
            inverseJoinColumns = {@JoinColumn(name="course_id")}
    )
    private Collection<Course> courses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Course> getCourses() {
        return courses;
    }

    public void setCourses(Collection<Course> courses) {
        this.courses = courses;
    }

    public void setStudent(Student std){
        this.setCourses(std.courses);
        this.setId(std.id);
        this.setName(std.name);
    }

    @Override
    public Student clone(){
        try{
        return (Student) super.clone();
        }catch (CloneNotSupportedException exception){
            return new Student();
        }
    }
}
