package com.haoxiang.soap.webservices.soapcoursemanagement.soap;

import com.haoxiang.courses.CourseDetails;
import com.haoxiang.courses.GetCourseDetailsRequest;
import com.haoxiang.courses.GetCourseDetailsResponse;
import com.haoxiang.soap.webservices.soapcoursemanagement.soap.bean.Course;
import com.haoxiang.soap.webservices.soapcoursemanagement.soap.service.CourseDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

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

        GetCourseDetailsResponse response = mapCourse(course);

        return response;
    }

    private static GetCourseDetailsResponse mapCourse(Course course) {
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();

        CourseDetails courseDetails = new CourseDetails();

        // Set a course details
        courseDetails.setId(course.getId());
        courseDetails.setName(course.getName());
        courseDetails.setDescription(course.getDescription());

        response.setCourseDetails(courseDetails);
        return response;
    }
}
