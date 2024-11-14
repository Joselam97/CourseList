# 📚 Course List Application

A Jakarta EE-based web application to manage a list of courses, allowing users to add, view, update, and delete courses stored in a MySQL database. This application demonstrates CRUD functionality with a clean, organized interface.

## Features
- **Add Courses**: Easily create new course entries with relevant details.
- **View Course List**: Access a full list of available courses.
- **Update Courses**: Modify course information as needed.
- **Delete Courses**: Remove courses from the list.
- **Database Integration**: Connects with a MySQL database to persist course data.
- **Annotations with CDI**: Utilizes CDI annotations for dependency injection, making component management efficient.

## 📖 Usage
1. **Manage Courses**: Use the interface to add, update, view, and delete courses.
2. **Filter Courses**: Search and filter courses by name or other attributes to find specific entries quickly.
3. **Database Synchronization**: All operations are synchronized with MySQL for persistent data storage.
4. **Responsive Design**: Experience a user-friendly interface, styled with Bootstrap.

## 🛠️ Tech Stack
- **Jakarta EE** - Core framework for enterprise-level functionality.
- **Java** - Language used to build the application.
- **CDI Annotations** - Provides dependency injection for efficient component management.
- **JSP and Servlets** - Creating dynamic pages and handling requests.
- **MySQL** - Database for storing course data.
- **Bootstrap** - Used for styling the user interface.

## 🚀 Installation
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Joselam97/CourseList.git

2. **Navigate to the Project Directory**:
   ```bash
   cd CourseList

3. **Set up the SQL Database**:
- Import the provided .sql tables located in the resources directory into MySQL.
- Update the database connection settings in the project configuration to match your MySQL credentials.

4. **Build the Project with Maven**:
   ```bash
   mvn clean install

5. **Deploy the Application**:
- Deploy the project on a Jakarta EE Tomcat server.

6. **Access the Application**:
- Open a web browser and access the application via your server's URL.
