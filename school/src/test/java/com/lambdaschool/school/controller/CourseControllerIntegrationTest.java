package com.lambdaschool.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.awt.*;
import java.util.ArrayList;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.number.OrderingComparison.lessThan;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CourseControllerIntegrationTest
{
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void initialiseRestAssuredMockMvcWebApplicationContext()
    {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    // GET /courses/courses
    @Test
    public void whenMeasuredReponseTime()
    {
        given().when().get("/courses/courses").then().time(lessThan(5000L));
    }


    // POST /courses/courses/add
    @Test
    public void givenPostACourse() throws Exception
    {
        ArrayList<Instructor> thisInst = new ArrayList<>();
        String course55Name = "Number 55 DS -- test";
        Course course55 = new Course(course55Name);

        ObjectMapper mapper = new ObjectMapper();
        String stringR3 = mapper.writeValueAsString(course55);

        given().contentType("application/json").body(stringR3).when().post("/courses/courses/add").then().statusCode(201);
    }
}
