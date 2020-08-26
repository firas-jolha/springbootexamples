package com.jolhagroup.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "courses")
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Column(name = "course_name")
    private String courseName;

    @Id
    @Column(name = "course_id")
    private int courseId;

    @JsonIgnore(value = true)
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses", cascade = {CascadeType.ALL})
    private Collection<Student> students;

    public Collection<Student> getStudents() {
        return students;
    }

}
