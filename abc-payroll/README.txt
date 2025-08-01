ABC Payroll System
A simple Java payroll application with employee management and authentication.

Features
Employee database management

Secure login with password hashing

View employee information

Quick Start
Prerequisites
Java 21+

Maven 3.6+

SQLite (embedded)

Installation
Clone the repository:

bash
git clone https://github.com/yourusername/abc-payroll.git
cd abc-payroll
Initialize the database:

bash
sqlite3 payroll.db < database/schema.sql
sqlite3 payroll.db < database/populate.db.sql
Running the Application
bash
mvn clean compile
mvn exec:java -Dexec.mainClass="com.abc.payroll.Main"
Default Credentials
text
Username: admin
Password: admin123
Database Setup
The SQLite database payroll.db will be created automatically in the project root.

Troubleshooting
If authentication fails:

Reset the admin password:

bash
sqlite3 payroll.db "UPDATE users SET password_hash = '$2a$10$N9qo8uLOickgx2ZMRZoMy.MqrqQVq7Z1D8nE8tZG5M1B8uQk7vqO' WHERE username = 'admin';"
Use the exact password admin123

Project Structure
text
abc-payroll/
├── src/main/java/com/abc/payroll/       # Source code
├── database/                            # SQL scripts
├── lib/                                 # Dependency JARs
├── target/                              # Compiled classes
└── payroll.db                           # Database file