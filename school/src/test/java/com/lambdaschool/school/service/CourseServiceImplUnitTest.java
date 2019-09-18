package com.lambdaschool.school.service;

import com.lambdaschool.school.SchoolApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplication.class)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseServiceImplUnitTest
{
    @Autowired
    private CourseService courseService;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void getCountStudentsInCourse() {
    }

    @Test
    public void delete()
    {
        courseService.delete(2);
        assertEquals(5, courseService.findAll().size());
    }

    @Test (expected = EntityNotFoundException.class)
    public void deleteNotFound()
    {
        courseService.delete(100);
        assertEquals(5, courseService.findAll().size());
    }

    @Test
    public void findCourseById()
    {
        assertEquals("Java Back End", courseService.findCourseById(4).getCoursename());
    }
}