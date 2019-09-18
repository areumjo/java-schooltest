package com.lambdaschool.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.model.Student;
import com.lambdaschool.school.service.CourseService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CourseController.class, secure = false)
public class CourseControllerUnitTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    private ArrayList<Course> courseList;

//    @Before
//    public void SetUp() throws Exception
//    {
//        Instructor i1 = new Instructor("Sally");
//        Instructor i2 = new Instructor("Lucy");
//        Instructor i3 = new Instructor("Charlie");
//​
//        instructrepos.save(i1);
//        instructrepos.save(i2);
//        instructrepos.save(i3);
//​
//        Course c1 = new Course("Data Science", i1);
//        Course c2 = new Course("JavaScript", i1);
//        Course c3 = new Course("Node.js", i1);
//        Course c4 = new Course("Java Back End", i2);
//        Course c5 = new Course("Mobile IOS", i2);
//        Course c6 = new Course("Mobile Android", i3);
//​
//        c1 = courseService.save(c1);
//        c2 = courseService.save(c2);
//        c3 = courseService.save(c3);
//        c4 = courseService.save(c4);
//        c5 = courseService.save(c5);
//        c6 = courseService.save(c6);
//​
//        Student s1 = new Student("John");
//        s1.getCourses().add(courseService.findCourseById(c1.getCourseid()));
//        s1.getCourses().add(courseService.findCourseById(c4.getCourseid()));
//​
//        Student s2 = new Student("Julian");
//        s2.getCourses().add(courseService.findCourseById(c2.getCourseid()));
//​
//        Student s3 = new Student("Mary");
//        s3.getCourses().add(courseService.findCourseById(c3.getCourseid()));
//        s3.getCourses().add(courseService.findCourseById(c1.getCourseid()));
//        s3.getCourses().add(courseService.findCourseById(c6.getCourseid()));
//​
//        studentService.save(s1);
//        studentService.save(s2);
//        studentService.save(s3);
//    }

    @After
    public void tearDown() throws Exception
    {
    }

    // TODO check this out later
    @Test
    public void listAllCourses() throws Exception
    {
        String apiUrl = "/courses/courses";
        Mockito.when(courseService.findAll()).thenReturn(courseList);
        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);

        // the following actually performs a real controller call
        MvcResult r = mockMvc.perform(rb).andReturn(); // this could throw an exception
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(courseList);

        System.out.println("***  Unit Test ***");
        System.out.println("Expected " + er);
        System.out.println("Actual   " + tr);
        System.out.println("***  Unit Test ***");

        assertEquals("Rest API Returns List", er, tr);
    }

    // localhost:2019/courses/course/add -- post
    @Test
    public void addNewCourse() throws Exception
    {
        String apiUrl = "/courses/course/add";

        // new course
        ArrayList<Instructor> thisInst = new ArrayList<>();
        String course55Name = "Number 55 DS -- test";
        Course course55 = new Course(course55Name);

        course55.setCourseid(55);

        ObjectMapper mapper = new ObjectMapper();
        String courseString = mapper.writeValueAsString(course55);

        Mockito.when(courseService.save(any(Course.class))).thenReturn(course55);

        RequestBuilder rb = MockMvcRequestBuilders.post(apiUrl)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(courseString);
        mockMvc.perform(rb).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }
}
