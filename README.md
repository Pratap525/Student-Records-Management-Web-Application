# Student Management System

A simple yet effective Student Management System built with Java Servlets that allows you to create, read, update, and delete student records.

## Project Description

This Student Management System is a web-based application that provides an interface for educational institutions to manage student data. Built using Java Servlets, it follows the MVC (Model-View-Controller) architecture pattern to ensure maintainability and scalability.

The application allows administrators to:
- Add new students to the database
- View existing student information
- Update student details
- Remove students from the records

## Technologies Used

- **Java** - Core programming language
- **Servlets** - Handle HTTP requests and responses
- **JSP/HTML** - Frontend user interface
- **JDBC** - Database connectivity
- **CSS** - Styling the web pages
- **Maven/Gradle** - Dependency management and build tools
- **Tomcat** - Servlet container for running the application

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           ├── controller/
│   │           │   └── StudentController.java  # Handles HTTP requests
│   │           ├── dao/
│   │           │   └── StudentDAO.java         # Data Access Object for database operations
│   │           └── model/
│   │               └── Student.java            # Student entity class
│   └── webapp/
│       ├── META-INF/
│       │   └── MANIFEST.MF
│       ├── index.html                         # Main entry page
│       └── success.html                       # Success page after operations
```

## Features

- **Student Registration**: Add new students with personal and academic details
- **Student Listings**: View a comprehensive list of all registered students
- **Student Profile**: Detailed view of individual student information
- **Profile Updates**: Modify student details as needed
- **Record Removal**: Remove students from the database
- **Data Validation**: Ensure data integrity through validation rules
- **Error Handling**: Proper error messages and exception handling

## Setup Instructions

### Prerequisites

- JDK 8 or higher
- Apache Tomcat 8.5 or higher
- Maven or Gradle (for dependency management)
- Database (MySQL, PostgreSQL, etc.)

### Installation

1. Clone this repository
   ```
   git clone https://github.com/yourusername/student-management-system.git
   ```

2. Navigate to the project directory
   ```
   cd student-management-system
   ```

3. Configure database connection
   - Update database connection parameters in your DAO implementation

4. Build the project
   ```
   mvn clean package
   ```
   or with Gradle
   ```
   gradle build
   ```

5. Deploy the generated WAR file to Tomcat
   - Copy the WAR file from target/student-management-system.war to Tomcat's webapps directory

## Running the Application

1. Start your Tomcat server
   ```
   $CATALINA_HOME/bin/startup.sh
   ```
   or on Windows
   ```
   %CATALINA_HOME%\bin\startup.bat
   ```

2. Access the application in your web browser
   ```
   http://localhost:8080/student-management-system
   ```

3. Use the interface to manage student records

## Dependencies

- Java Servlet API (javax.servlet-api)
- JDBC Driver (depending on your database choice)
- JSTL (for JSP pages if used)
- Logger implementation (e.g., Log4j)
- Database connection pool (e.g., HikariCP, DBCP)

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License - see the LICENSE file for details.

