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

public class AddCourseToStudentDemo {

	private static final Logger logger = LoggerFactory.getLogger(AddCourseToStudentDemo.class);

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
        	
        	logger.info("Gettin the course");
        	Course tempCourse = new Course("MOngssoDBssss");
        	Course temp2 = new Course("JakartaEEss ss10sss");
        	Student st = session.get(Student.class, 7);
        	st.setCourses(List.of(tempCourse, temp2));
        	logger.info("saving courses");
        	session.save(st);
        } finally {
        	session.close();
        	factory.close();
        }
	}
}
