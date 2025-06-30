-- Drop existing root user for any host if it exists
DROP USER IF EXISTS 'root'@'%';
-- Create root user with mysql_native_password
CREATE USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'admin';
-- Grant all privileges on shipmgmt database
GRANT ALL PRIVILEGES ON shipmgmt.* TO 'root'@'%';
-- Ensure the shipmgmt database exists
CREATE DATABASE IF NOT EXISTS shipmgmt;
-- Apply privileges
FLUSH PRIVILEGES;
