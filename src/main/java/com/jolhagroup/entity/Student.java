package com.jolhagroup.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "Please provide the email!")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_email")
    private Email email;

    @NotEmpty(message = "Please specify a dept!")
    @Column(name="student_dept")
    private String dept;


    @JsonIgnore(value = true)
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="students_courses",
            joinColumns = {@JoinColumn(name="student_id")},
            inverseJoinColumns = {@JoinColumn(name="course_id")}
    )
    private Collection<Course> courses;


    public Collection<Course> getCourses() {
        return courses;
    }

//    public void setStudent(Student std){
//        this.setCourses(std.courses);
//        this.setId(std.id);
//        this.setName(std.name);
//        this.setEmail(std.email);
//    }

    @Override
    public Student clone(){
        try{
        return (Student) super.clone();
        }catch (CloneNotSupportedException exception){
            return new Student();
        }
    }
}
