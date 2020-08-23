package com.jolhagroup.service;

import com.jolhagroup.dao.StudentDao;
import com.jolhagroup.entity.Student;
import com.jolhagroup.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Deprecated
public class StudentService {


//    @Autowired
    private StudentRepository repository;

    public Collection<Student> getAllStudents(){
        List<Student> students = StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
        if (students.size()>0) return students;
        else return null;
    }

}
