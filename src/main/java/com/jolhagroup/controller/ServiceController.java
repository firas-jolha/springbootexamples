package com.jolhagroup.controller;

import com.jolhagroup.entity.Course;
import com.jolhagroup.entity.Student;
import com.jolhagroup.repository.CourseRepository;
import com.jolhagroup.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.RequestParamMapMethodArgumentResolver;

import javax.validation.Valid;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class ServiceController {

//    private static StudentService studentService = new StudentService();

//    private static CourseService courseService = new CourseService();

    @Autowired
    private final StudentRepository studentRepository;

    @Autowired
    private final CourseRepository courseRepository;

    public ServiceController(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/students")
    public Iterable<Student> getStudentsByCourse(@RequestParam(name = "courseid") Optional<Integer> id){
        if (!id.isPresent()) {
            Iterable<Student> students = studentRepository.findAll();
            return students;
        }else {
            Course course = courseRepository.findById(id.get()).get();
            return course.getStudents();
        }
    }


    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable("id") int id){
        Student std = studentRepository.findById(id).orElseThrow();
        return std;
    }

    @PostMapping("/students/add")
    public Student addStudent(@Valid @RequestBody Student stds){
        return studentRepository.save(stds);
    }

    @DeleteMapping("/students/del/{id}")
    public Student deleteStudent(@PathVariable("id") int id){
        Student std = studentRepository.findById(id).get();
        studentRepository.delete(std);
        return std;
    }

    @PutMapping("/students/upd/{id}")
    public Student updateStudent(@RequestBody Student std, @RequestParam("courseid") Optional<Integer> courseid){
        if (courseid.isPresent()){
            try {
                std.getCourses().add(courseRepository.findById(courseid.get()).get());
            }catch (RuntimeException ex){
                handleError();
            }
        }
        return studentRepository.save(std);
    }

    @GetMapping("/courses")
    public Iterable<Course> getCourses(@RequestParam(name = "studentid") Optional<Integer> id) {
        if (!id.isPresent())
            return courseRepository.findAll();
        else {
            Student std = studentRepository.findById(id.get()).get();
            return std.getCourses();
        }
    }

    @GetMapping("/courses/{id}")
    public Course getCourse(@PathVariable int id){
        return courseRepository.findById(id).get();
    }

    @PostMapping("/courses/add")
    public Course addCourse(@RequestBody Course c){
        return courseRepository.save(c);
    }

    @DeleteMapping("/courses/del/{id}")
    public Course deleteCourse(@PathVariable int i){
        courseRepository.deleteById(i);
        return courseRepository.findById(i).get();
    }

    @PutMapping("courses/upd/{id}")
    public Course updateCourse(@RequestBody Course c){
        return addCourse(c);
    }

    @ExceptionHandler({Exception.class})
    public String handleError(){
        return "An error has been occurred";
    }

}
