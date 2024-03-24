# Banking Management Application

## Overview
This is a Java Swing-based banking management application designed to provide a cost-effective and efficient solution for banking operations. It prioritizes security, ease of use, and offers multiple functionalities suitable for students and small-scale banking operations.

## Problem Statement and Solution
Existing banking management applications often demand extensive knowledge of application development, making them inaccessible to individuals without specialized skills. This project resolves this challenge by leveraging Java Swing, an extension of the Java language, to create a user-friendly banking management application without the complexity of app development.

## Unique Features
- **CSV Data Source:** Utilizes CSV files for data storage instead of complex SQL databases, simplifying data management.
- **Password Protection:** Implements password protection to enhance security.
- **Multiple Interfaces:** Provides separate interfaces for admin, user, worker, and advanced options, catering to specific roles and functionalities.

## App Flow
Insert App Flow Picture Here  
![App Flow](/JAVA_output_Image/Flow_of_the_App.png)

## Screens and Interfaces
- **Initial Screen:** Features buttons for admin login, user login, new account creation, and worker login.  
  ![Initial Screen](/JAVA_output_Image/initial_Screen.png)
  - **Admin Login:** Allows administrators to log in with their credentials.
  - **User Login:** Allows users to log in with their credentials.
  - **New Account Creation:** Redirects to the new account creation interface.
  - **Worker Login:** Allows workers to log in with their credentials.

- **Login Interface:** Allows users to input credentials for system access.  
  ![Login Interface](/JAVA_output_Image/Login_interface.png)
  - **Username Field:** Where users enter their username.
  - **Password Field:** Where users enter their password.
  - **Login Button:** Submits the credentials for authentication.

- **User Interface:** Displays user details and facilitates actions like depositing, withdrawing, and logging out.  
  ![User Interface](/JAVA_output_Image/User_interface.png)
  - **User Details:** Displays the user's name, account number, account type, and balance.
  - **Deposit Button:** Allows the user to deposit an amount into their account.
  - **Withdraw Button:** Allows the user to withdraw an amount from their account.
  - **Logout Button:** Logs the user out of the system.

- **New Account Interface:** Guides users through the account creation process.  
  ![New Account Interface](/JAVA_output_Image/New_Account_interface.png)
  - **Name Field:** Where users enter their name.
  - **Initial Balance Field:** Where users enter the initial balance for their account.
  - **Account Type Field:** Where users select the type of account they want to create.
  - **Username Field:** Where users enter their desired username.
  - **Password Field:** Where users enter their desired password.
  - **Confirm Password Field:** Where users confirm their password.
  - **Create Account Button:** Submits the information to create a new account.

- **Admin Interface:** Grants administrators access to user details, balance modification options, and search functionality.  
  ![Admin Interface](/JAVA_output_Image/Admin_interface.png)
  - **User Details:** Displays the details of all users, including their name, account number, account type, and balance.
  - **Refresh Button:** Refreshes the list of users.
  - **Modify Balance Button:** Allows the admin to modify the balance of a user's account.
  - **Search Field:** Where the admin can search for a user by username.
  - **Search Button:** Initiates the search based on the entered username.

- **Worker Interface:** Offers similar functionalities as the admin interface, tailored for workers.  
  ![Worker Interface](/JAVA_output_Image/Worker_interface.png)
  - **User Details:** Displays the details of all users, including their name, account number, account type, and balance.
  - **Refresh Button:** Refreshes the list of users.
  - **Add Interest Button:** Adds interest to the balance of all user accounts.
  - **Search Field:** Where the worker can search for a user by username.
  - **Search Button:** Initiates the search based on the entered username.
  - **Modify Balance Button:** Allows the worker to modify the balance of a user's account.
  - **Advanced Options Button:** Redirects to the advanced options interface.

- **Advanced Options Interface:** Presents advanced functionalities such as adding fixed deposits, applying for loans, and managing loans and fixed deposits.  
  ![Advanced Options Interface](/JAVA_output_Image/Advanced_Option_interface.png)
  - **User Details:** Displays the details of all users, including their name, account number, account type, and balance.
  - **Refresh Button:** Refreshes the list of users.
  - **Trace Users Button:** Allows the user to trace and view specific user details.
  - **Add Fixed Deposit Button:** Adds a fixed deposit to the user's account.
  - **Apply for Loan Button:** Allows the user to apply for a loan.
  - **Show Approved Loan Button:** Displays approved loans for users.
  - **Show Approved Fixed Deposit Button:** Displays approved fixed deposits for users.
  - **Loan Management Screen Button:** Redirects to the loan management screen.
  - **FD Management Button:** Redirects to the fixed deposit management screen.
  - **Back Button:** Returns to the worker interface.

## Future Aspects
- **Advanced Development:** Potential for enhancing the application with more sophisticated features and improved design.
- **Implementation of MySQL:** Integration of MySQL for secure data sharing and advanced data management.

## Usage
1. Clone the repository to your local machine.
2. Compile and run the Java files using a Java development environment.
3. Follow the on-screen instructions to navigate through the application.

## Contributions
Contributions to this project are welcome. Please feel free to submit pull requests or open issues for any enhancements or issues encountered.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
