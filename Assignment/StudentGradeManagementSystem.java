package com.wt;

import java.util.ArrayList;

class Student {
 private int studentId;
 private String name;
 private ArrayList<Grade> grades;

 public Student(int studentId, String name) {
     this.studentId = studentId;
     this.name = name;
     this.grades = new ArrayList<>();
 }

 public int getStudentId() {
     return studentId;
 }

 public String getName() {
     return name;
 }

 public ArrayList<Grade> getGrades() {
     return grades;
 }

 public void addGrade(Grade grade) {
     grades.add(grade);
 }

 public double calculateGPA() {
     if (grades.isEmpty()) return 0.0;
     double totalPoints = 0;
     for (Grade grade : grades) {
         totalPoints += grade.getGradePoint();
     }
     return totalPoints / grades.size();
 }
}

class Course {
 private int courseId;
 private String courseName;

 public Course(int courseId, String courseName) {
     this.courseId = courseId;
     this.courseName = courseName;
 }

 public int getCourseId() {
     return courseId;
 }

 public String getCourseName() {
     return courseName;
 }
}

class Grade {
 private Course course;
 private double gradePoint;

 public Grade(Course course, double gradePoint) {
     this.course = course;
     this.gradePoint = gradePoint;
 }

 public Course getCourse() {
     return course;
 }

 public double getGradePoint() {
     return gradePoint;
 }
}

public class StudentGradeManagementSystem {
 private ArrayList<Student> students;
 private ArrayList<Course> courses;

 public StudentGradeManagementSystem() {
     students = new ArrayList<>();
     courses = new ArrayList<>();
 }

 public void addStudent(int studentId, String name) {
     students.add(new Student(studentId, name));
 }

 public void addCourse(int courseId, String courseName) {
     courses.add(new Course(courseId, courseName));
 }

 public void assignGrade(int studentId, int courseId, double gradePoint) {
     Student student = findStudentById(studentId);
     Course course = findCourseById(courseId);

     if (student != null && course != null) {
         student.addGrade(new Grade(course, gradePoint));
     } else {
         System.out.println("Student or Course not found.");
     }
 }

 public void displayStudentDetails(int studentId) {
     Student student = findStudentById(studentId);
     if (student != null) {
         System.out.println("Student ID: " + student.getStudentId());
         System.out.println("Name: " + student.getName());
         System.out.println("Grades:");
         for (Grade grade : student.getGrades()) {
             System.out.println("  Course: " + grade.getCourse().getCourseName() +
                     ", Grade: " + grade.getGradePoint());
         }
         System.out.printf("GPA: %.2f\n", student.calculateGPA());
     } else {
         System.out.println("Student not found.");
     }
 }

 private Student findStudentById(int studentId) {
     for (Student student : students) {
         if (student.getStudentId() == studentId) {
             return student;
         }
     }
     return null;
 }

 private Course findCourseById(int courseId) {
     for (Course course : courses) {
         if (course.getCourseId() == courseId) {
             return course;
         }
     }
     return null;
 }

 public static void main(String[] args) {
     StudentGradeManagementSystem system = new StudentGradeManagementSystem();

     system.addCourse(101, "Mathematics");
     system.addCourse(102, "Physics");

     system.addStudent(1, "Alice");
     system.addStudent(2, "Bob");

     system.assignGrade(1, 101, 4.0); 
     system.assignGrade(1, 102, 3.5); 
     system.assignGrade(2, 101, 3.0); // Bob gets B in Mathematics

     system.displayStudentDetails(1); 
     system.displayStudentDetails(2); 
 }
}

