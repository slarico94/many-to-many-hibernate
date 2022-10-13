package com.one_to_one.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.one_to_one.demo.entity.Course;
import com.one_to_one.demo.entity.Instructor;
import com.one_to_one.demo.entity.InstructorDetail;
import com.one_to_one.demo.entity.Review;
import com.one_to_one.demo.entity.Student;

public class CreateCourseAndStudentsDemo {

	private static final Logger logger = LoggerFactory.getLogger(CreateCourseAndStudentsDemo.class);

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
        		.configure("hibernate.cfg.xml")
        		.addAnnotatedClass(Instructor.class)
        		.addAnnotatedClass(InstructorDetail.class)
        		.addAnnotatedClass(Course.class)
        		.addAnnotatedClass(Review.class)
        		.addAnnotatedClass(Student.class)
        		.buildSessionFactory();
        Session session = factory.getCurrentSession();
        
        try {
        	session.beginTransaction();        	
        	
        	logger.info("Getting a course");
        	Course course = new Course("Hibernatessss bad really");
        	course.setStudents(List.of(
        			new Student("Alberto", "Ramirez", "alberto@mail.com"),
        			new Student("Carlos", "Rodriguez", "alberto@mail.com")
        			));
        	logger.info("saving students and course");
        	session.save(course);
        	
        	session.getTransaction().commit();
        } finally {
        	session.close();
        	factory.close();
        }
	}
}
