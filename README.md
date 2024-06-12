# Expense Tracker Application

### How to run the application ->
1. Download and install an IDE such as Eclipse or IntelliJ IDEA.
2. Clone or download the zip from the master branch.
3. Open the IDE and import the project.
4. Resolve any project dependencies and ensure that the JDK is installed and configured.
5. Open the Main class.
6. Right-click on the main class and select "Run as" -> "Java Application".
7. The application should start running, and you can interact with it from the console.

#### For cloning link:
````
https://github.com/Saloni-041/expenseTracker.git
````
#### Overview:
The Expense Tracker Application is a web application designed to help users track, view, and manage their expenses. It features user authentication, allowing users to securely log in and access their personal expense data. The app ensures data privacy and integrity using JWT (JSON Web Token) and Spring Security.

#### Features:
- User Registration and Login: Users can register and log in to the application.
- JWT Authentication: Upon logging in, users receive a JWT token for secure access.
- Expense Management: Users can add, view, edit, and delete their expenses.
- Secure Access Control: Only authenticated users can access their own expense data, ensuring no unauthorized access.

#### Technologies Used:
1. Spring Boot: Backend framework for creating the REST API.
2. Spring Security: For securing the application and handling user authentication and authorization.
3. JWT: For secure token-based authentication.
4. Hibernate: For ORM (Object-Relational Mapping) to interact with the database.
5. MySQL: Database to store user and expense data.
6. Maven: For project management and dependency management.

#### Getting Started:
To run the Expense Tracker Application locally, follow these steps:
1. Clone the repository:
2. Set up the database:
    - Create a MySQL database named expensetracker as in my application.properties configuration db name is expensetracker.
    - Update the database configuration in src/main/resources/application.properties with your database credentials.
3. Access the application:
    - Once the application is running, you can access it using postman.

#### Usage
- Register a new user: Go to the registration page and create a new account.
- Login:Log in with your credentials to receive a JWT token.
- Manage Expenses:Use the application to add, view, edit, or delete your expenses.
