package com.example.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.example.dao.StudentDAO;
import com.example.model.Student;

@WebServlet(urlPatterns = "/student")  // This maps all URLs starting with /student/ to this servlet
public class StudentController extends HttpServlet {
  
	private static final long serialVersionUID = 1L;
	private StudentDAO studentDAO;

    public void init() {
        studentDAO = new StudentDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "list";  // default action
        }

        try {
            switch (action) {
                case "create":
                    createStudent(request, response);
                    break;
                case "read":
                    readStudent(request, response);
                    break;
                case "update":
                    showUpdateForm(request, response);
                    break;
                case "updateStudent":
                    updateStudent(request, response);
                    break;
                case "delete":
                    deleteStudent(request, response);
                    break;
                case "list":
                    listStudents(request, response);
                    break;
                default:
                    listStudents(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void createStudent(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String name = request.getParameter("sname");
        int age = Integer.parseInt(request.getParameter("sage"));
        String address = request.getParameter("saddress");

        Student student = new Student(name, age, address);
        try {
            studentDAO.insertStudent(student);
            response.sendRedirect("success.html");
        } catch (Exception e) {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<html><body>");
            out.println("<h3>Error Creating Student: " + e.getMessage() + "</h3>");
            out.println("<a href='index.html'>Back to Home</a>");
            out.println("</body></html>");
        }
    }

    private void readStudent(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        try {
            int id = Integer.parseInt(request.getParameter("sid"));
            Student student = studentDAO.getStudentById(id);

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Student Details</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 20px; background-color: #f5f5f5; }");
            out.println(".container { max-width: 800px; margin: 0 auto; background-color: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }");
            out.println(".student-info { margin: 20px 0; }");
            out.println(".back-link { display: inline-block; margin-top: 20px; color: #2196F3; text-decoration: none; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");

            if (student != null) {
                out.println("<h3>Student Details</h3>");
                out.println("<div class='student-info'>");
                out.println("<p><strong>ID:</strong> " + student.getId() + "</p>");
                out.println("<p><strong>Name:</strong> " + student.getName() + "</p>");
                out.println("<p><strong>Age:</strong> " + student.getAge() + "</p>");
                out.println("<p><strong>Address:</strong> " + student.getAddress() + "</p>");
                out.println("</div>");
            } else {
                out.println("<h3>Student Not Found!</h3>");
            }

            out.println("<a href='index.html' class='back-link'>Back to Home</a>");
            out.println("</div>");
            out.println("</body></html>");
        } catch (Exception e) {
            out.println("<html><body>");
            out.println("<h3>Error Reading Student: " + e.getMessage() + "</h3>");
            out.println("<a href='index.html'>Back to Home</a>");
            out.println("</body></html>");
        }
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            int id = Integer.parseInt(request.getParameter("sid"));
            Student student = studentDAO.getStudentById(id);

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Update Student</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 20px; background-color: #f5f5f5; }");
            out.println(".container { max-width: 800px; margin: 0 auto; background-color: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }");
            out.println(".form-group { margin-bottom: 15px; }");
            out.println("label { display: inline-block; width: 100px; font-weight: bold; }");
            out.println("input[type='text'], input[type='number'] { width: 200px; padding: 8px; border: 1px solid #ddd; border-radius: 4px; margin-top: 5px; }");
            out.println(".submit-btn { padding: 8px 15px; background-color: #2196F3; color: white; border: none; border-radius: 4px; cursor: pointer; }");
            out.println(".submit-btn:hover { background-color: #1976D2; }");
            out.println(".back-link { display: inline-block; margin-top: 20px; color: #2196F3; text-decoration: none; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");

            if (student != null) {
                out.println("<h3>Update Student Details</h3>");
                out.println("<form action='student' method='get'>");
                out.println("<input type='hidden' name='action' value='updateStudent'>");
                out.println("<input type='hidden' name='sid' value='" + student.getId() + "'>");
                
                out.println("<div class='form-group'>");
                out.println("<label>Name:</label>");
                out.println("<input type='text' name='sname' value='" + student.getName() + "' required>");
                out.println("</div>");
                
                out.println("<div class='form-group'>");
                out.println("<label>Age:</label>");
                out.println("<input type='number' name='sage' value='" + student.getAge() + "' required>");
                out.println("</div>");
                
                out.println("<div class='form-group'>");
                out.println("<label>Address:</label>");
                out.println("<input type='text' name='saddress' value='" + student.getAddress() + "' required>");
                out.println("</div>");
                
                out.println("<button type='submit' class='submit-btn'>Update Student</button>");
                out.println("</form>");
            } else {
                out.println("<h3>Student Not Found!</h3>");
            }

            out.println("<a href='index.html' class='back-link'>Back to Home</a>");
            out.println("</div>");
            out.println("</body></html>");
        } catch (Exception e) {
            out.println("<html><body>");
            out.println("<h3>Error Loading Student: " + e.getMessage() + "</h3>");
            out.println("<a href='index.html'>Back to Home</a>");
            out.println("</body></html>");
        }
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("sid"));
            String name = request.getParameter("sname");
            int age = Integer.parseInt(request.getParameter("sage"));
            String address = request.getParameter("saddress");

            Student student = new Student(id, name, age, address);
            boolean success = studentDAO.updateStudent(student);

            if (success) {
                response.sendRedirect("success.html");
            } else {
                PrintWriter out = response.getWriter();
                response.setContentType("text/html");
                out.println("<html><body>");
                out.println("<h3>Update Failed!</h3>");
                out.println("<a href='index.html'>Back to Home</a>");
                out.println("</body></html>");
            }
        } catch (Exception e) {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<html><body>");
            out.println("<h3>Error Updating Student: " + e.getMessage() + "</h3>");
            out.println("<a href='index.html'>Back to Home</a>");
            out.println("</body></html>");
        }
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("sid"));
            boolean success = studentDAO.deleteStudent(id);

            if (success) {
                response.sendRedirect("success.html");
            } else {
                PrintWriter out = response.getWriter();
                response.setContentType("text/html");
                out.println("<html><body>");
                out.println("<h3>Delete Failed!</h3>");
                out.println("<a href='index.html'>Back to Home</a>");
                out.println("</body></html>");
            }
        } catch (Exception e) {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<html><body>");
            out.println("<h3>Error Deleting Student: " + e.getMessage() + "</h3>");
            out.println("<a href='index.html'>Back to Home</a>");
            out.println("</body></html>");
        }
    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            List<Student> students = studentDAO.getAllStudents();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>All Students</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 20px; background-color: #f5f5f5; }");
            out.println(".container { max-width: 800px; margin: 0 auto; background-color: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }");
            out.println("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
            out.println("th, td { padding: 12px; text-align: left; border-bottom: 1px solid #ddd; }");
            out.println("th { background-color: #f2f2f2; }");
            out.println("tr:hover { background-color: #f5f5f5; }");
            out.println(".back-link { display: inline-block; margin-top: 20px; color: #2196F3; text-decoration: none; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<h3>All Students</h3>");

            if (!students.isEmpty()) {
                out.println("<table>");
                out.println("<tr>");
                out.println("<th>ID</th>");
                out.println("<th>Name</th>");
                out.println("<th>Age</th>");
                out.println("<th>Address</th>");
                out.println("</tr>");

                for (Student student : students) {
                    out.println("<tr>");
                    out.println("<td>" + student.getId() + "</td>");
                    out.println("<td>" + student.getName() + "</td>");
                    out.println("<td>" + student.getAge() + "</td>");
                    out.println("<td>" + student.getAddress() + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            } else {
                out.println("<p>No students found.</p>");
            }

            out.println("<a href='index.html' class='back-link'>Back to Home</a>");
            out.println("</div>");
            out.println("</body></html>");
        } catch (Exception e) {
            out.println("<html><body>");
            out.println("<h3>Error Listing Students: " + e.getMessage() + "</h3>");
            out.println("<a href='index.html'>Back to Home</a>");
            out.println("</body></html>");
        }
    }
}
