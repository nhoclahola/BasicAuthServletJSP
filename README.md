# Simple Authentication Application

This project is a simple web application demonstrating basic authentication features like **login** and **registration** using **Servlets**, **JSP**, and **Jakarta EE 9**. It uses **bcrypt** to securely store user passwords and **HikariCP** for efficient database connection management.

## Features

- **User Registration**: Users can register with a username and password.
- **Login**: Registered users can log in using their credentials. The login process includes:
  * **Session Management**: Users are authenticated and their session is created. If a session already exists, users are redirected to a different page (e.g., /waiting).
  * **Remember Me**: Users can choose to be remembered on their device. If selected, their username is stored in a cookie for future logins.
  * **Cookie Management**: The application checks for an existing cookie named username and, if found, uses it to automatically log the user in and create a session.
- **Password Security**: Passwords are hashed using **bcrypt** for secure storage.
- **Forgot Password**: Users can request a password reset via email (For simplicity, This feature does not involve sending OTPs).
- **Session Handling**: If a valid session is detected, users are redirected to the appropriate page. Sessions are managed server-side and linked to a cookie on the client-side.
- **Database Connection Pooling**: **HikariCP** is used to efficiently manage connections to the database.

## Technologies Used

- **Servlets** and **JSP** for the web layer
- **Jakarta EE 9** as the platform
- **Gradle** for project build and dependency management
- **HikariCP** for database connection pooling
- **Bcrypt** for password hashing
- **Tomcat** as the server
- **Lombok** for reducing boilerplate code by automatically generating common methods like getters, setters, and constructors.

## Setup Instructions

### Prerequisites

- Java 11 or higher: Required to run the application.
- Apache Tomcat 10 or higher: Tomcat 9 is incompatible with Jakarta EE, so Tomcat 10 or later is required.
- Gradle 7.x or higher: Ensure you have a compatible build tool version.
- MySQL: The database system used for managing data in this project.
- Configure the database connection in `DBConnectMySQL.java` (see the configuration section below)

### Database Configuration
Make sure to update the database connection settings in `src/main/java/org/example/study_03_servlet_jdbc/connection/DBConnectMySQL.java`:

```java
private static final String DB_URL = "jdbc:mysql://localhost:3306/yourdatabase";
private static final String USERNAME = "yourusername";
private static final String PASSWORD = "yourpassword";
```
Before deploying to Tomcat Server, you should create database which you have defined in DBConnectMySQL.java. When you start the web application for the first time, it will check and generate *roles* and *users* tables, then it will create ADMIN, MANAGER, USER roles, after that, it will create *admin* and *manager* username with default password same as username. The next time you start the web application, it will not generate tables and data anymore (unless you remove data which is related to which have been created before).

## How to run
Alternatively, you can open as a project in IDE (such as Intellij, Eclipse, Spring Tool Suite, etc.) and build dependencies there, then start at Tomcat Server. Or you can follow the steps below:
### Build the Project
1. Clone the repository:
```bash
git clone https://github.com/nhoclahola/BasicAuthServletJSP.git
cd BasicAuthServletJSP
```
2. Build the project using Gradle:
```bash
./gradlew build
```
3. The build will generate a WAR file in the build/libs/ directory.
### Deploying to Tomcat
1. Copy the generated WAR file to Tomcat's webapps folder.
2. Start your Tomcat server
- On Linux/macOS or Git Bash:
```bash
catalina.sh run
```
- On Windows:
```bash
catalina.bat run
```
4. Access the application by navigating to:
```bash
http://localhost:8080/your-war-file-name
```

## Demo
### Login Page
When you enter *http://localhost:8080/your-war-file-name*, it will redirect you to Login Page.
![image](https://github.com/user-attachments/assets/6ef3aca5-2f73-48f1-92be-001a2c3f4e30)

If you login information is invalid, a warning message will appear.
![image](https://github.com/user-attachments/assets/0c575160-37ae-452d-8ed6-5ccd70fc95a2)


### Register Page
You can navigate to the Registration Page by enter *http://localhost:8080/your-war-file-name/register* or just by clicking at a tag "Create an account" in Login Page.
![image](https://github.com/user-attachments/assets/557e4d68-e251-4160-a2c5-a7cf4c47be69)

If you create username or email which is existed in database, a warning message will appear.
![image](https://github.com/user-attachments/assets/3888d070-6579-485a-ad14-f1e431408638)

When you create account successfully, it will redirect you to the Login page.
![image](https://github.com/user-attachments/assets/3fb19346-7fc6-4753-91e3-0477bd9c7044)

If you check at the database, you can see passwords were hashed.
![image](https://github.com/user-attachments/assets/3ba80674-d60b-4554-890b-252534b9d2ca)


### Home Page
When you log in with an ADMIN role, you'll be redirected to the admin dashboard (/admin/home). Similarly, logging in with a MANAGER role takes you to the manager dashboard (/manager/home), while users with the USER role will be directed to the general homepage (/home)
![image](https://github.com/user-attachments/assets/aa7df3c1-8860-47d0-a056-c0016c6e39ec)
![image](https://github.com/user-attachments/assets/de93a27e-d3f5-4155-af80-f4acd04a8c61)
![image](https://github.com/user-attachments/assets/5856c4de-4d7f-4282-84c2-f7260dc8926a)

If your role lacks permission to access other homepages, you will be denied access.
![image](https://github.com/user-attachments/assets/fecfb56b-1d47-4eef-8ba4-db4e8e611a36)

Or when you haven't login.
![image](https://github.com/user-attachments/assets/82341e54-edd7-498d-b1d4-cf70dceb9b2c)

*Note: ADMIN and MANAGER roles can access USER Home page, but they can't access Home page of each other.

When you login, your Session ID should be valid in 30 minutes (by default), so you can interact at pages before it expired, to remember login, you can check at checkbox *Remember me" at the Login Page, it will keep you login by cookie in 24 hours (no encrypt so it can be bybass easily).

### My Account Page
This page will show your account's information.
![image](https://github.com/user-attachments/assets/821a06dd-95e1-4b47-b6ce-509dabcbf79f)

### Forgot Password Page
For simplicity, this application does not send OTPs via email. Instead, users can reset their password by providing their email and a new password.
![image](https://github.com/user-attachments/assets/166e0b3c-0dc5-4cb0-956b-0295371909f6)

If you enter wrong email, a warning message will appear.
![image](https://github.com/user-attachments/assets/12755a03-3e77-4461-969e-30941b141ae9)

In case you enter valid email, and password, you will be redirect to Login page.
![image](https://github.com/user-attachments/assets/3d6046b7-d9f4-445f-8c7d-8b0f6cd157e3)

