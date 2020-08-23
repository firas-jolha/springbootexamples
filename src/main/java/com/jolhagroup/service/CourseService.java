package com.jolhagroup.service;

import com.jolhagroup.entity.Course;
import com.jolhagroup.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Deprecated
public class CourseService {


//    @Autowired
    private CourseRepository repository;

    public List<Course> getAllCourses(){
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void addCourse(Course c){
        repository.save(c);
    }

}
