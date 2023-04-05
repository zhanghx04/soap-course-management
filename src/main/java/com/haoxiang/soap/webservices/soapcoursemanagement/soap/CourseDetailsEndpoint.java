package com.haoxiang.soap.webservices.soapcoursemanagement.soap;

import com.haoxiang.courses.CourseDetails;
import com.haoxiang.courses.GetCourseDetailsRequest;
import com.haoxiang.courses.GetCourseDetailsResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CourseDetailsEndpoint {
    // Method
    // Input - GetCourseDetailsRequest
    // OutPut - GetCourseDetailsResponse

    // http://haoxiang.com/courses
    // GetCourseDetailsRequest
    @PayloadRoot(namespace = "http://haoxiang.com/courses", localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();
        CourseDetails courseDetails = new CourseDetails();

        // Set a course details
        courseDetails.setId(request.getId());
        courseDetails.setName("High Performance Computing");
        courseDetails.setDescription("You will learn different parallel programming methods in this course");

        response.setCourseDetails(courseDetails);

        return response;
    }
}
