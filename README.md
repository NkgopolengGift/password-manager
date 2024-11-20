# Password Manager API

The Password Manager API is a Spring Boot application designed to manage user credentials securely. It provides functionality to create, retrieve, update, and delete passwords while ensuring data security with JWT authentication, encryption, and email integration for notifications and password recovery.

---

## Features

- **User Authentication**: Secure login with JWT tokens.
- **Password Management**: Create, retrieve, update, delete (soft delete) stored passwords.
- **Encryption**: All sensitive data is encrypted before storage.
- **Email Notifications**: Integrated with an SMTP server for account and security-related emails.
- **Role-Based Access Control**: Default roles for users and administrators.

---

## Prerequisites

Ensure the following are installed:

1. Java 21+
2. Maven 3.8+
3. MySQL or compatible relational database
4. A valid SMTP server (e.g., Gmail)

---

## Setup Instructions

1. Create a `.env` file in the root directory with the following content:
   ```env
   # Application Properties
   SPRING_SERVER_PORT=8080

   # Database Configuration
   SPRING_DATABASE_URL=jdbc:mysql://localhost:3306/password_manager
   SPRING_DATABASE_USERNAME=root
   SPRING_DATABASE_PASSWORD=yourpassword

   # JWT Secret Key
   JWT_SECRETE_KEY=your_jwt_secret_key

   # Encryption Key
   ENCRYPTION_KEY=your_encryption_key

   # Mail Configuration
   MAIL_HOST=smtp.gmail.com
   MAIL_PORT=587
   MAIL_USERNAME=your_email@example.com
   MAIL_PASSWORD=your_email_password
   ```

## Environment Variables

The `.env` file contains the necessary environment variables for the application:

- `SPRING_SERVER_PORT`: The port the server will run on.
- `SPRING_DATABASE_URL`: JDBC connection string for the database.
- `SPRING_DATABASE_USERNAME`: Database username.
- `SPRING_DATABASE_PASSWORD`: Database password.
- `JWT_SECRETE_KEY`: Secret key for signing JWT tokens.
- `ENCRYPTION_KEY`: Key for encrypting sensitive data.
- `MAIL_HOST`: SMTP server host (default is Gmail).
- `MAIL_PORT`: SMTP server port (587 for Gmail).
- `MAIL_USERNAME`: Email address for sending notifications.
- `MAIL_PASSWORD`: Password for the email account.

---

## Security Best Practices

1. **Do not commit the `.env` file**: Add `.env` to your `.gitignore`.
2. **Use strong keys**: Generate robust `JWT_SECRETE_KEY` and `ENCRYPTION_KEY`.
3. **Secure your database**: Use a dedicated user for database connections with minimal privileges.
4. **Use environment variables**: Ensure all sensitive data is stored securely in environment variables.

---
