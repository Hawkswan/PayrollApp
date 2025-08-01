BEGIN TRANSACTION;

CREATE TABLE employees (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    position TEXT,
    salary REAL,
    hire_date TEXT
);

CREATE TABLE users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT UNIQUE NOT NULL,
    password_hash TEXT NOT NULL,
    employee_id INTEGER REFERENCES employees(id)
);

-- Insert admin employees
INSERT INTO employees VALUES 
(1, 'Admin', 'User', 'admin@company.com', 'Administrator', 100000, '2020-01-01'),
(2, 'Sarah', 'Johnson', 'sarah.j@company.com', 'HR Manager', 85000, '2020-02-15');

-- Insert test employees
INSERT INTO employees VALUES
(3, 'Michael', 'Brown', 'michael.b@company.com', 'Developer', 75000, '2020-03-10'),
(4, 'Emily', 'Davis', 'emily.d@company.com', 'Developer', 78000, '2020-04-05'),
(5, 'Robert', 'Wilson', 'robert.w@company.com', 'QA Engineer', 65000, '2020-05-12'),
(6, 'Jennifer', 'Taylor', 'jennifer.t@company.com', 'Designer', 70000, '2020-06-20'),
(7, 'David', 'Anderson', 'david.a@company.com', 'DevOps', 82000, '2020-07-08'),
(8, 'Jessica', 'Thomas', 'jessica.t@company.com', 'Project Manager', 90000, '2020-08-17'),
(9, 'William', 'Jackson', 'william.j@company.com', 'Developer', 77000, '2020-09-25'),
(10, 'Amanda', 'White', 'amanda.w@company.com', 'UX Designer', 72000, '2020-10-30'),
(11, 'Christopher', 'Harris', 'chris.h@company.com', 'Database Admin', 83000, '2020-11-14'),
(12, 'Elizabeth', 'Martin', 'elizabeth.m@company.com', 'Marketing', 68000, '2020-12-03'),
(13, 'Matthew', 'Thompson', 'matt.t@company.com', 'Support', 60000, '2021-01-22'),
(14, 'Ashley', 'Garcia', 'ashley.g@company.com', 'Developer', 76000, '2021-02-11');

-- Insert admin users (password for both is 'admin123')
INSERT INTO users VALUES 
(1, 'admin', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MqrqQVq7Z1D8nE8tZG5M1B8uQk7vqO', 1),
(2, 'sarah_admin', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MqrqQVq7Z1D8nE8tZG5M1B8uQk7vqO', 2);

COMMIT;