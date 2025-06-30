-- Create users table
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create role table
CREATE TABLE `role` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `role_name` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create users_roles join table
CREATE TABLE `users_roles` (
                               `user_id` bigint NOT NULL,
                               `roles_id` int NOT NULL,
                               PRIMARY KEY (`user_id`,`roles_id`),
                               KEY `FK15d410tj6juko0sq9k4km60xq` (`roles_id`),
                               CONSTRAINT `FK15d410tj6juko0sq9k4km60xq` FOREIGN KEY (`roles_id`) REFERENCES `role` (`id`),
                               CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create admin table
CREATE TABLE `admin` (
                         `education` varchar(255) DEFAULT NULL,
                         `id` bigint NOT NULL,
                         PRIMARY KEY (`id`),
                         CONSTRAINT `FKqer4e53tfnl17s22ior7fcsv8` FOREIGN KEY (`id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create crew table
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

-- Create company table
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create ship table
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create company_ships join table
CREATE TABLE `company_ships` (
                                 `company_id` bigint NOT NULL,
                                 `ships_id` bigint NOT NULL,
                                 UNIQUE KEY `UKm1899893l3hmh8v206sb70qan` (`ships_id`),
                                 KEY `FKrsvo90f46asl4merexl3kgx4x` (`company_id`),
                                 CONSTRAINT `FK6c9htqdb3qndc1f3vh50e08fm` FOREIGN KEY (`ships_id`) REFERENCES `ship` (`id`),
                                 CONSTRAINT `FKrsvo90f46asl4merexl3kgx4x` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create crew_assignment table
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create mach_group table
CREATE TABLE `mach_group` (
                              `id` bigint NOT NULL AUTO_INCREMENT,
                              `mach_group_name` varchar(255) DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create machinery table
CREATE TABLE `machinery` (
                             `id` bigint NOT NULL AUTO_INCREMENT,
                             `machinery_name` varchar(255) DEFAULT NULL,
                             `mach_group_id` bigint DEFAULT NULL,
                             PRIMARY KEY (`id`),
                             KEY `FKqnimtq0jlmc2vyei42cv94nq5` (`mach_group_id`),
                             CONSTRAINT `FKqnimtq0jlmc2vyei42cv94nq5` FOREIGN KEY (`mach_group_id`) REFERENCES `mach_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create component table
CREATE TABLE `component` (
                             `id` bigint NOT NULL AUTO_INCREMENT,
                             `component_name` varchar(255) DEFAULT NULL,
                             `machinery_id` bigint DEFAULT NULL,
                             PRIMARY KEY (`id`),
                             KEY `FKnj57l321sp1haolumdsyv0497` (`machinery_id`),
                             CONSTRAINT `FKnj57l321sp1haolumdsyv0497` FOREIGN KEY (`machinery_id`) REFERENCES `machinery` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create task table
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create component_tasks join table
CREATE TABLE `component_tasks` (
                                   `component_id` bigint NOT NULL,
                                   `tasks_id` bigint NOT NULL,
                                   UNIQUE KEY `UKmrd3sice8tc9o7mdqpnnp6wuu` (`tasks_id`),
                                   KEY `FKi5f8lq9wwro32cdivgbuj6c5l` (`component_id`),
                                   CONSTRAINT `FKi5f8lq9wwro32cdivgbuj6c5l` FOREIGN KEY (`component_id`) REFERENCES `component` (`id`),
                                   CONSTRAINT `FKm2ldg1n786rbo854g6tr2g4f5` FOREIGN KEY (`tasks_id`) REFERENCES `task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create task_assignment table
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create report_request table
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create approval table
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create approval_log table
CREATE TABLE `approval_log` (
                                `id` bigint NOT NULL AUTO_INCREMENT,
                                `report_request_id` bigint DEFAULT NULL,
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `UK45g1rn3baqgeo4pk4rix3vvum` (`report_request_id`),
                                CONSTRAINT `FKp3fdid36jvxi4w2v4nbm8f81b` FOREIGN KEY (`report_request_id`) REFERENCES `report_request` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create material table
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create material_report_request join table
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

-- Create material_usage_log table
CREATE TABLE `material_usage_log` (
                                      `id` bigint NOT NULL,
                                      `ship_id` int NOT NULL,
                                      `used_at` datetime(6) NOT NULL,
                                      `end_at` datetime(6) DEFAULT NULL,
                                      `remarks` varchar(255) DEFAULT NULL,
                                      `used_by` varchar(255) DEFAULT NULL,
                                      PRIMARY KEY (`id`,`ship_id`,`used_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create maintenance table
CREATE TABLE `maintenance` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create maintenance_log table
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create task_schedule table
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

-- Create task_schedule_seq table
CREATE TABLE `task_schedule_seq` (
                                     `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
