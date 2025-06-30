-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: shipmgmt
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `education` varchar(255) DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKqer4e53tfnl17s22ior7fcsv8` FOREIGN KEY (`id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('Education',1),('Education',8);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `approval`
--

DROP TABLE IF EXISTS `approval`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `approval` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `approval_timestamp` datetime(6) DEFAULT NULL,
  `position` enum('first','general','second','third') DEFAULT NULL,
  `crew_id` bigint DEFAULT NULL,
  `report_request_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtixltnuas3nlb2kfewsf5p170` (`crew_id`),
  KEY `FKq6xu4ilsmfrncgwmjgaicmcw3` (`report_request_id`),
  CONSTRAINT `FKq6xu4ilsmfrncgwmjgaicmcw3` FOREIGN KEY (`report_request_id`) REFERENCES `report_request` (`id`),
  CONSTRAINT `FKtixltnuas3nlb2kfewsf5p170` FOREIGN KEY (`crew_id`) REFERENCES `crew` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `approval`
--

LOCK TABLES `approval` WRITE;
/*!40000 ALTER TABLE `approval` DISABLE KEYS */;
INSERT INTO `approval` VALUES (1,'2025-06-24 10:18:24.648561','third',4,2),(2,'2025-06-24 10:23:37.759477','second',7,2);
/*!40000 ALTER TABLE `approval` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `approval_log`
--

DROP TABLE IF EXISTS `approval_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `approval_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `report_request_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK45g1rn3baqgeo4pk4rix3vvum` (`report_request_id`),
  CONSTRAINT `FKp3fdid36jvxi4w2v4nbm8f81b` FOREIGN KEY (`report_request_id`) REFERENCES `report_request` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `approval_log`
--

LOCK TABLES `approval_log` WRITE;
/*!40000 ALTER TABLE `approval_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `approval_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `company_address` varchar(255) DEFAULT NULL,
  `company_email` varchar(255) DEFAULT NULL,
  `company_fax` varchar(255) DEFAULT NULL,
  `company_phone` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `registered_by` varchar(255) NOT NULL,
  `year_established` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,'2025-06-24 09:33:45.780101','Register User',NULL,NULL,'Yangon,Myanmar','contact@acme.com','COMPANY FAX','+1 (212) 555-1234','MDT','Mr.Park','2004-09-08');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company_ships`
--

DROP TABLE IF EXISTS `company_ships`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company_ships` (
  `company_id` bigint NOT NULL,
  `ships_id` bigint NOT NULL,
  UNIQUE KEY `UKm1899893l3hmh8v206sb70qan` (`ships_id`),
  KEY `FKrsvo90f46asl4merexl3kgx4x` (`company_id`),
  CONSTRAINT `FK6c9htqdb3qndc1f3vh50e08fm` FOREIGN KEY (`ships_id`) REFERENCES `ship` (`id`),
  CONSTRAINT `FKrsvo90f46asl4merexl3kgx4x` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_ships`
--

LOCK TABLES `company_ships` WRITE;
/*!40000 ALTER TABLE `company_ships` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_ships` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `component`
--

DROP TABLE IF EXISTS `component`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `component` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `component_name` varchar(255) DEFAULT NULL,
  `machinery_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnj57l321sp1haolumdsyv0497` (`machinery_id`),
  CONSTRAINT `FKnj57l321sp1haolumdsyv0497` FOREIGN KEY (`machinery_id`) REFERENCES `machinery` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `component`
--

LOCK TABLES `component` WRITE;
/*!40000 ALTER TABLE `component` DISABLE KEYS */;
INSERT INTO `component` VALUES (1,'UV UNIT',1),(2,'ELECTRO  POWER UNIT FOR WINCHES',2),(3,'FIREMAN OUTFIT',3),(4,'LIFE BOAT DAVIT & WINCH(ST\'BD)',4),(5,'ENGINE SIDE MANEUVERING SYSTEM',5),(6,'BRIDGE MANEUVERING SYSTEM',5),(7,'AUX\' BLOWER',6),(8,'AIR INTER COOLER',6),(9,'STARTING AIR DISTRIBUTOR',6),(10,'CROSS HEAD WITH CONNECTING ROD',6);
/*!40000 ALTER TABLE `component` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `component_tasks`
--

DROP TABLE IF EXISTS `component_tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `component_tasks` (
  `component_id` bigint NOT NULL,
  `tasks_id` bigint NOT NULL,
  UNIQUE KEY `UKmrd3sice8tc9o7mdqpnnp6wuu` (`tasks_id`),
  KEY `FKi5f8lq9wwro32cdivgbuj6c5l` (`component_id`),
  CONSTRAINT `FKi5f8lq9wwro32cdivgbuj6c5l` FOREIGN KEY (`component_id`) REFERENCES `component` (`id`),
  CONSTRAINT `FKm2ldg1n786rbo854g6tr2g4f5` FOREIGN KEY (`tasks_id`) REFERENCES `task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `component_tasks`
--

LOCK TABLES `component_tasks` WRITE;
/*!40000 ALTER TABLE `component_tasks` DISABLE KEYS */;
/*!40000 ALTER TABLE `component_tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crew`
--

DROP TABLE IF EXISTS `crew`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `crew` (
  `active` bit(1) NOT NULL,
  `birth_date` date DEFAULT NULL,
  `certificates` varchar(255) DEFAULT NULL,
  `certificates_expiry` date DEFAULT NULL,
  `crew_rank` enum('first','general','second','third') DEFAULT NULL,
  `emergency_email` varchar(255) DEFAULT NULL,
  `emergency_phone` varchar(255) DEFAULT NULL,
  `gender` enum('FEMALE','MALE') DEFAULT NULL,
  `joined_date` date DEFAULT NULL,
  `license_expiry` varchar(255) DEFAULT NULL,
  `license_no` varchar(255) DEFAULT NULL,
  `nationality` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo_url` varchar(255) DEFAULT NULL,
  `section` enum('section1','section2') DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKjl5dmc5ejvn8o6ybxdg9e3jcw` FOREIGN KEY (`id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crew`
--

LOCK TABLES `crew` WRITE;
/*!40000 ALTER TABLE `crew` DISABLE KEYS */;
INSERT INTO `crew` VALUES (_binary '','2025-06-01','STCW, Advanced Firefighting','2025-07-12','first','emergency.contact@example.com','+1 (555) 987-7503','MALE','2025-06-01','2025-06-06','USCG-12345678','Myanmar','+1 (555) 126-4567',NULL,'section1',2),(_binary '','2003-06-03','STCW, Advanced Firefighting','2025-07-06','third','emergency.contact@example.com','+1 (555) 987-6543','MALE','2025-06-18','2025-06-28','USCG-12345678','Myanmar','09777-990',NULL,'section1',4),(_binary '','2002-06-01','STCW, Advanced Firefighting','2025-06-20','general','emergency.contact@example.com','+1 (555) 987-6543','FEMALE','2025-06-02','2025-06-04','USCG-12345678','Myanmar','+1 (555) 126-4567',NULL,'section1',5),(_binary '','2002-06-01','STCW, Advanced Firefighting','2025-07-12','second','emergency.contact@example.com','+1 (555) 987-6543','MALE','2025-06-01','2025-06-20','USCG-12345678','Myanmar','09777-990',NULL,'section1',7);
/*!40000 ALTER TABLE `crew` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crew_assignment`
--

DROP TABLE IF EXISTS `crew_assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `crew_assignment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `end_date` date DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `crew_id` bigint DEFAULT NULL,
  `ship_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKot02b068rhiscwqw36atfrw8x` (`crew_id`),
  KEY `FK2175h3l4dwwuo2q3fh0mdqd7w` (`ship_id`),
  CONSTRAINT `FK2175h3l4dwwuo2q3fh0mdqd7w` FOREIGN KEY (`ship_id`) REFERENCES `ship` (`id`),
  CONSTRAINT `FKot02b068rhiscwqw36atfrw8x` FOREIGN KEY (`crew_id`) REFERENCES `crew` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crew_assignment`
--

LOCK TABLES `crew_assignment` WRITE;
/*!40000 ALTER TABLE `crew_assignment` DISABLE KEYS */;
INSERT INTO `crew_assignment` VALUES (1,'2025-07-12','FIRSTLEADER','2025-06-23',2,1),(3,'2025-07-12','THIRDLEADER','2025-06-23',4,1),(4,'2025-07-12','WORKER','2025-06-23',5,1);
/*!40000 ALTER TABLE `crew_assignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mach_group`
--

DROP TABLE IF EXISTS `mach_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mach_group` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `mach_group_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mach_group`
--

LOCK TABLES `mach_group` WRITE;
/*!40000 ALTER TABLE `mach_group` DISABLE KEYS */;
INSERT INTO `mach_group` VALUES (1,'D2 DECK MACHINERY'),(2,'D5 SAFETY EQUIPMENTS'),(3,'M1 MAIN ENGINE AND SHAFTING');
/*!40000 ALTER TABLE `mach_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `machinery`
--

DROP TABLE IF EXISTS `machinery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `machinery` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `machinery_name` varchar(255) DEFAULT NULL,
  `mach_group_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqnimtq0jlmc2vyei42cv94nq5` (`mach_group_id`),
  CONSTRAINT `FKqnimtq0jlmc2vyei42cv94nq5` FOREIGN KEY (`mach_group_id`) REFERENCES `mach_group` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `machinery`
--

LOCK TABLES `machinery` WRITE;
/*!40000 ALTER TABLE `machinery` DISABLE KEYS */;
INSERT INTO `machinery` VALUES (1,'BALLAST WATER TREATMENT SYSTEM',1),(2,'WINDLASS & MOORING WINCH',1),(3,'FIRE FIGHTING SYSTEM',2),(4,'LIFE BOAT SYSTEM',2),(5,'M/E BRIDGE MANEUVERING SYSTEM',3),(6,'DIESEL MAIN ENGINE',3);
/*!40000 ALTER TABLE `machinery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `maintenance`
--

DROP TABLE IF EXISTS `maintenance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `maintenance` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maintenance`
--

LOCK TABLES `maintenance` WRITE;
/*!40000 ALTER TABLE `maintenance` DISABLE KEYS */;
/*!40000 ALTER TABLE `maintenance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `maintenance_log`
--

DROP TABLE IF EXISTS `maintenance_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `maintenance_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `last_work` date DEFAULT NULL,
  `next_due` date DEFAULT NULL,
  `remark` varchar(255) NOT NULL,
  `status` enum('COMPLETED','FAILED','PENDING') DEFAULT NULL,
  `crew_id` bigint NOT NULL,
  `task_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1n7iqjt5fbk3hdygmpmp8lk7f` (`crew_id`),
  KEY `FKhecgwya5rw6168w9jp637o7s3` (`task_id`),
  CONSTRAINT `FK1n7iqjt5fbk3hdygmpmp8lk7f` FOREIGN KEY (`crew_id`) REFERENCES `crew` (`id`),
  CONSTRAINT `FKhecgwya5rw6168w9jp637o7s3` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maintenance_log`
--

LOCK TABLES `maintenance_log` WRITE;
/*!40000 ALTER TABLE `maintenance_log` DISABLE KEYS */;
INSERT INTO `maintenance_log` VALUES (1,'2025-06-25','2025-07-25','notes-remarks','COMPLETED',5,6);
/*!40000 ALTER TABLE `maintenance_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `material_condition` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `life_time` varchar(255) DEFAULT NULL,
  `manufacturer` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` bigint NOT NULL,
  `received_date` date DEFAULT NULL,
  `serial_no` varchar(255) DEFAULT NULL,
  `supplier_info` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `use_status` bit(1) NOT NULL,
  `ship_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbq4san9x6qo5g78yi2lyocdwn` (`ship_id`),
  CONSTRAINT `FKbq4san9x6qo5g78yi2lyocdwn` FOREIGN KEY (`ship_id`) REFERENCES `ship` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material`
--

LOCK TABLES `material` WRITE;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT INTO `material` VALUES (1,'2025-06-24 09:38:07.888791','Register User',NULL,NULL,'Excellent','DESC','','Bosch Hydraulics','Hydraulic1001',2000,'2025-06-23','MAT-2023-001','Marine Parts Co., contact: John Smith (john@marineparts.com)','lubricant',_binary '\0',NULL),(2,'2025-06-24 09:38:42.450520','Register User',NULL,NULL,'Excellent','desc','','Bosch Hydraulics','Hydraulic1001',1000,'2025-06-23','MAT-2023-002','Marine Parts Co., contact: John Smith (john@marineparts.com)','lubricant',_binary '\0',NULL),(3,'2025-06-24 09:39:16.460249','Register User',NULL,NULL,'Excellent','desc','','Bosch Hydraulics','Hydraulic1001',1000,'2025-06-23','MAT-2023-003','Marine Parts Co., contact: John Smith (john@marineparts.com)','lubricant',_binary '\0',NULL),(4,'2025-06-24 09:39:43.493830','Register User',NULL,NULL,'Excellent','desc','','Bosch Hydraulics','Hydraulic1002',1000,'2025-06-23','MAT-2023-004','Marine Parts Co., contact: John Smith (john@marineparts.com)','lubricant',_binary '\0',NULL);
/*!40000 ALTER TABLE `material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material_report_request`
--

DROP TABLE IF EXISTS `material_report_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material_report_request` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `material_id` bigint DEFAULT NULL,
  `report_request_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3t8xd1wf98m6vbf4cpg30pgb7` (`material_id`),
  KEY `FKilvcmwdsckyfvlhcfsx4mgfxg` (`report_request_id`),
  CONSTRAINT `FK3t8xd1wf98m6vbf4cpg30pgb7` FOREIGN KEY (`material_id`) REFERENCES `material` (`id`),
  CONSTRAINT `FKilvcmwdsckyfvlhcfsx4mgfxg` FOREIGN KEY (`report_request_id`) REFERENCES `report_request` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_report_request`
--

LOCK TABLES `material_report_request` WRITE;
/*!40000 ALTER TABLE `material_report_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `material_report_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material_usage_log`
--

DROP TABLE IF EXISTS `material_usage_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material_usage_log` (
  `id` bigint NOT NULL,
  `ship_id` int NOT NULL,
  `used_at` datetime(6) NOT NULL,
  `end_at` datetime(6) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `used_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`ship_id`,`used_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_usage_log`
--

LOCK TABLES `material_usage_log` WRITE;
/*!40000 ALTER TABLE `material_usage_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `material_usage_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_request`
--

DROP TABLE IF EXISTS `report_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report_request` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `approved` bit(1) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `report_type` varchar(255) DEFAULT NULL,
  `request_date` date DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `crew_id` bigint DEFAULT NULL,
  `task_assignment_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKbh3fnjnllwh8royi4ty9qkfl3` (`task_assignment_id`),
  KEY `FK1gobx0bo9b77oc28fym987fju` (`crew_id`),
  CONSTRAINT `FK1gobx0bo9b77oc28fym987fju` FOREIGN KEY (`crew_id`) REFERENCES `crew` (`id`),
  CONSTRAINT `FKl5sopic6g5u7k21k7rq3bmkj3` FOREIGN KEY (`task_assignment_id`) REFERENCES `task_assignment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_request`
--

LOCK TABLES `report_request` WRITE;
/*!40000 ALTER TABLE `report_request` DISABLE KEYS */;
INSERT INTO `report_request` VALUES (2,_binary '\0','desc',NULL,'2025-06-24','Request For make an oil analysis',5,1);
/*!40000 ALTER TABLE `report_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'WORKER'),(2,'THIRD_LEADER'),(3,'FIRST_LEADER'),(4,'SECOND_LEADER'),(5,'CAPTAIN'),(6,'ADMIN'),(13,'ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ship`
--

DROP TABLE IF EXISTS `ship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ship` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `flag` varchar(255) DEFAULT NULL,
  `imo_number` varchar(255) DEFAULT NULL,
  `mmsi` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `ship_office` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `year_built` date DEFAULT NULL,
  `company_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7xv8oj4dbuhb7pd8ejafyr3i1` (`company_id`),
  CONSTRAINT `FK7xv8oj4dbuhb7pd8ejafyr3i1` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ship`
--

LOCK TABLES `ship` WRITE;
/*!40000 ALTER TABLE `ship` DISABLE KEYS */;
INSERT INTO `ship` VALUES (1,'2025-06-24 09:34:09.778185','Register User',NULL,NULL,'myanmar','8999','3423-0909','Ace Corporation 7','yangon','container','2004-09-08',1);
/*!40000 ALTER TABLE `ship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `critical` bit(1) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `kind` enum('CMS','PMS','SMS') DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `task_type` enum('Annual','Daily','Monthly','Quarterly','Semi','Weekly') DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `component_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK881gf9faomnuecox0nscfq08s` (`component_id`),
  CONSTRAINT `FK881gf9faomnuecox0nscfq08s` FOREIGN KEY (`component_id`) REFERENCES `component` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (1,_binary '\0','desc','PMS','C/O','Monthly','BWTS UV UNIT QUARTZ SLEEVE CHEMICAL(ALCOHOL) CLEANING',1),(2,_binary '\0','desc','PMS','C/O','Monthly','BWTS UV TEMP\' TRANSMITTER & UV INTENSITY TRANSMITTER CALIBRATION',1),(3,_binary '\0','desc','PMS','C/O','Monthly','BWTS RENEWED UV UNIT WIPER',1),(4,_binary '\0','desc','PMS','C/O','Monthly','BWTS UV UNIT PURGING UNIT OPERATING CONDITION CHECK',1),(5,_binary '\0','desc','PMS','C/O','Monthly','BWTS RENEWED UV LAMP',1),(6,_binary '\0','desc','PMS','C/O','Monthly','MAKE AN OIL ANALYSIS',2),(7,_binary '\0','desc','PMS','C/O','Monthly','TAKE A INVENTORY & CHECK STORING CONDITION',3),(8,_binary '\0','desc','PMS','C/O','Monthly','CARRY OUT LAUNCHING ON THE SEA',4),(9,_binary '\0','desc','PMS','C/O','Monthly','CARRY OUT W/H SIDE TEST OF M/E  MANEUVERING SYSTEM',6),(10,_binary '\0','desc','PMS','C/O','Monthly','OVHL\' NO.1 AUX\' BLOWER',7),(11,_binary '\0','desc','PMS','C/O','Monthly','OVHL\' STARTING AIR DISTRIBUTOR FOR M/E',9),(12,_binary '\0','desc','PMS','C/O','Monthly','INSPECT THE NO.7 CYL\' CROSS HEAD & CON-ROD',10),(13,_binary '','Desc','CMS','C/O','Weekly','I love you',1),(14,_binary '\0','desc','PMS','C/O','Monthly','INSPECT THE NO.7 CYL\' CROSS HEAD & CON-ROD',10);
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_assignment`
--

DROP TABLE IF EXISTS `task_assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task_assignment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `assigned_date` date DEFAULT NULL,
  `completed` bit(1) NOT NULL,
  `deadline_date` date DEFAULT NULL,
  `crew_id` bigint DEFAULT NULL,
  `ship_id` bigint DEFAULT NULL,
  `task_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh5nxle4pif8din0fiud4mqfbo` (`crew_id`),
  KEY `FK6cuedl3lqyofgutegijoqhdaq` (`ship_id`),
  KEY `FKjec38nl7lfmlgdn036w0h3hbt` (`task_id`),
  CONSTRAINT `FK6cuedl3lqyofgutegijoqhdaq` FOREIGN KEY (`ship_id`) REFERENCES `ship` (`id`),
  CONSTRAINT `FKh5nxle4pif8din0fiud4mqfbo` FOREIGN KEY (`crew_id`) REFERENCES `crew` (`id`),
  CONSTRAINT `FKjec38nl7lfmlgdn036w0h3hbt` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_assignment`
--

LOCK TABLES `task_assignment` WRITE;
/*!40000 ALTER TABLE `task_assignment` DISABLE KEYS */;
INSERT INTO `task_assignment` VALUES (1,'2025-06-24',_binary '','2025-06-24',5,1,6),(2,'2025-06-24',_binary '\0','2025-06-24',5,1,3),(3,'2025-06-25',_binary '\0','2025-07-25',5,1,6);
/*!40000 ALTER TABLE `task_assignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_schedule`
--

DROP TABLE IF EXISTS `task_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task_schedule` (
  `id` bigint NOT NULL,
  `frequency` varchar(255) DEFAULT NULL,
  `next_due` date DEFAULT NULL,
  `repeat_count` int NOT NULL,
  `task_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp1gb713ieea12w96hrkl4fqtj` (`task_id`),
  CONSTRAINT `FKp1gb713ieea12w96hrkl4fqtj` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_schedule`
--

LOCK TABLES `task_schedule` WRITE;
/*!40000 ALTER TABLE `task_schedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `task_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_schedule_seq`
--

DROP TABLE IF EXISTS `task_schedule_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task_schedule_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_schedule_seq`
--

LOCK TABLES `task_schedule_seq` WRITE;
/*!40000 ALTER TABLE `task_schedule_seq` DISABLE KEYS */;
INSERT INTO `task_schedule_seq` VALUES (1);
/*!40000 ALTER TABLE `task_schedule_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2025-06-24 09:28:52.721213','Register User',NULL,NULL,'admin@gmail.com','Admin','User','$2a$10$phX2ubBOBuwfcoTKv14f0ucahnEe8w8rSJcW1eSxjg9UihedFegK2'),(2,'2025-06-24 09:29:52.664803','Register User',NULL,NULL,'park@gmail.com','Mr','Park','$2a$10$FWB.PnuhtEexaMzLpsz2LubLPfEwSkbNhuG4CheeeDj9m9WyqGpK.'),(4,'2025-06-24 09:31:48.824168','Register User',NULL,NULL,'pyae@gmail.com','Ko','Pyae','$2a$10$3mOsenq8hbxJu5heNzBAk.fpGbaGHGibMCWErRy1cqfPJVRRiBcX.'),(5,'2025-06-24 09:33:08.141232','Register User',NULL,NULL,'julie@gmail.com','Julie','Smith','$2a$10$U5GApsEUXunls60FW0/co.BbVUFtVUkMPaSOvfASn52pnfoOs7FFi'),(7,'2025-06-24 10:23:20.780601','Register User',NULL,NULL,'kyaw@gmail.com','Ko','Kyaw','$2a$10$/PtyqQgd1mBGS962SOjzv.sKeuGNPhcShpgdVhmQaPP./SGix8U9.'),(8,'2025-06-26 06:56:56.673696','Register User',NULL,NULL,'admin@gmail.com+','Admin2','User+','$2a$10$kg0CS9.EMON74GXHa6C0L.tpUMykK4VNh10DTne2c2MfkT4TYql6W');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `user_id` bigint NOT NULL,
  `roles_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`roles_id`),
  KEY `FK15d410tj6juko0sq9k4km60xq` (`roles_id`),
  CONSTRAINT `FK15d410tj6juko0sq9k4km60xq` FOREIGN KEY (`roles_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (5,1),(4,2),(2,3),(7,4),(1,6),(8,13);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-27 10:54:24
