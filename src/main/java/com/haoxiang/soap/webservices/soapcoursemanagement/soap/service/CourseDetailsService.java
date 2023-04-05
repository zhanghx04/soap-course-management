package com.haoxiang.soap.webservices.soapcoursemanagement.soap.service;

import com.haoxiang.soap.webservices.soapcoursemanagement.soap.bean.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CourseDetailsService {
    
    private static List<Course> courses = new ArrayList<>();
    
    static {
        Course course1 = new Course(1, "Intro to C++", "Learn C++");
        courses.add(course1);

        Course course2 = new Course(2, "Intro to Java", "Learn Java");
        courses.add(course2);

        Course course3 = new Course(3, "Intro to Python", "Learn Python");
        courses.add(course3);

        Course course4 = new Course(4, "Intro to Ruby", "Learn Ruby");
        courses.add(course4);
    }
    
    // Course - 1
    public Course findById(int id) {
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        return null;
    }
    
    // Courses
    public List<Course> findAll() {
        return courses;
    }
    
    // Delete course
    public int deleteById(int id) {
        Iterator<Course> iterator = courses.iterator();

        while (iterator.hasNext()) {
            Course course = iterator.next();
            if (course.getId() == id) {
                iterator.remove();
                return 1;
            }
        }
        return 0;
    }
    
    // update course & new course
}
