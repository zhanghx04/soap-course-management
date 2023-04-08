package com.haoxiang.soap.webservices.soapcoursemanagement.soap;

import com.haoxiang.courses.*;
import com.haoxiang.courses.Status;
import com.haoxiang.soap.webservices.soapcoursemanagement.soap.bean.Course;
import com.haoxiang.soap.webservices.soapcoursemanagement.soap.exception.CourseNotFoundException;
import com.haoxiang.soap.webservices.soapcoursemanagement.soap.service.CourseDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class CourseDetailsEndpoint {

    @Autowired
    CourseDetailsService service;

    // Method
    // Input - GetCourseDetailsRequest
    // OutPut - GetCourseDetailsResponse

    // http://haoxiang.com/courses
    // GetCourseDetailsRequest
    @PayloadRoot(namespace = "http://haoxiang.com/courses", localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
        Course course = service.findById(request.getId());

        if (course == null) {
            throw new CourseNotFoundException("Invalid Course Id " + request.getId());
        }

        return mapCourseDetails(course);
    }

    private static GetCourseDetailsResponse mapCourseDetails(Course course) {
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();

        CourseDetails courseDetails = mapCourse(course);

        response.setCourseDetails(courseDetails);

        return response;
    }

    private static GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses) {
        GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();

        for (Course course : courses) {
            CourseDetails courseDetails = mapCourse(course);
            response.getCourseDetails().add(courseDetails);
        }

        return response;
    }

    private static CourseDetails mapCourse(Course course) {
        CourseDetails courseDetails = new CourseDetails();

        // Set a course details
        courseDetails.setId(course.getId());
        courseDetails.setName(course.getName());
        courseDetails.setDescription(course.getDescription());
        return courseDetails;
    }

    @PayloadRoot(namespace = "http://haoxiang.com/courses", localPart = "GetAllCourseDetailsRequest")
    @ResponsePayload
    public GetAllCourseDetailsResponse processAllCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest request) {
        List<Course> courses = service.findAll();

        return mapAllCourseDetails(courses);
    }

    @PayloadRoot(namespace = "http://haoxiang.com/courses", localPart = "DeleteCourseDetailsRequest")
    @ResponsePayload
    public DeleteCourseDetailsResponse deleteCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest request) {
        CourseDetailsService.Status status = service.deleteById(request.getId());

        DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
        response.setStatus(mapStatus(status));

        return response;
    }

    private Status mapStatus(CourseDetailsService.Status status) {
        if (status == CourseDetailsService.Status.FAILURE) {
            return Status.FAILURE;
        }

        return Status.SUCCESS;
    }


}
