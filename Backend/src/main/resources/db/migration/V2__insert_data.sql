-- 1. Create independent tables first
-- USERS
INSERT INTO users (id, created_at, created_by, updated_at, updated_by, email, first_name, last_name, password) VALUES
                                                                                                                   (1, '2025-06-24 09:28:52.721213', 'Register User', NULL, NULL, 'admin@gmail.com', 'Admin', 'User', '$2a$10$phX2ubBOBuwfcoTKv14f0ucahnEe8w8rSJcW1eSxjg9UihedFegK2'),
                                                                                                                   (2, '2025-06-24 09:29:52.664803', 'Register User', NULL, NULL, 'park@gmail.com', 'Mr', 'Park', '$2a$10$FWB.PnuhtEexaMzLpsz2LubLPfEwSkbNhuG4CheeeDj9m9WyqGpK.'),
                                                                                                                   (4, '2025-06-24 09:31:48.824168', 'Register User', NULL, NULL, 'pyae@gmail.com', 'Ko', 'Pyae', '$2a$10$3mOsenq8hbxJu5heNzBAk.fpGbaGHGibMCWErRy1cqfPJVRRiBcX.'),
                                                                                                                   (5, '2025-06-24 09:33:08.141232', 'Register User', NULL, NULL, 'julie@gmail.com', 'Julie', 'Smith', '$2a$10$U5GApsEUXunls60FW0/co.BbVUFtVUkMPaSOvfASn52pnfoOs7FFi'),
                                                                                                                   (7, '2025-06-24 10:23:20.780601', 'Register User', NULL, NULL, 'kyaw@gmail.com', 'Ko', 'Kyaw', '$2a$10$/PtyqQgd1mBGS962SOjzv.sKeuGNPhcShpgdVhmQaPP./SGix8U9.'),
                                                                                                                   (8, '2025-06-26 06:56:56.673696', 'Register User', NULL, NULL, 'admin@gmail.com+', 'Admin2', 'User+', '$2a$10$kg0CS9.EMON74GXHa6C0L.tpUMykK4VNh10DTne2c2MfkT4TYql6W');

-- ROLE
INSERT INTO role (id, role_name) VALUES
                                     (1, 'WORKER'),
                                     (2, 'THIRD_LEADER'),
                                     (3, 'FIRST_LEADER'),
                                     (4, 'SECOND_LEADER'),
                                     (5, 'CAPTAIN'),
                                     (6, 'ADMIN'),
                                     (13, 'ADMIN');

-- USERS_ROLES
INSERT INTO users_roles (user_id, roles_id) VALUES
                                                (5, 1),
                                                (4, 2),
                                                (2, 3),
                                                (7, 4),
                                                (1, 6),
                                                (8, 13);

-- ADMIN
INSERT INTO admin (education, id) VALUES
                                      ('Education', 1),
                                      ('Education', 8);

-- CREW
INSERT INTO crew (active, birth_date, certificates, certificates_expiry, crew_rank, emergency_email, emergency_phone, gender, joined_date, license_expiry, license_no, nationality, phone, photo_url, section, id) VALUES
                                                                                                                                                                                                                       (b'1', '2025-06-01', 'STCW, Advanced Firefighting', '2025-07-12', 'first', 'emergency.contact@example.com', '+1 (555) 987-7503', 'MALE', '2025-06-01', '2025-06-06', 'USCG-12345678', 'Myanmar', '+1 (555) 126-4567', NULL, 'section1', 2),
                                                                                                                                                                                                                       (b'1', '2003-06-03', 'STCW, Advanced Firefighting', '2025-07-06', 'third', 'emergency.contact@example.com', '+1 (555) 987-6543', 'MALE', '2025-06-18', '2025-06-28', 'USCG-12345678', 'Myanmar', '09777-990', NULL, 'section1', 4),
                                                                                                                                                                                                                       (b'1', '2002-06-01', 'STCW, Advanced Firefighting', '2025-06-20', 'general', 'emergency.contact@example.com', '+1 (555) 987-6543', 'FEMALE', '2025-06-02', '2025-06-04', 'USCG-12345678', 'Myanmar', '+1 (555) 126-4567', NULL, 'section1', 5),
                                                                                                                                                                                                                       (b'1', '2002-06-01', 'STCW, Advanced Firefighting', '2025-07-12', 'second', 'emergency.contact@example.com', '+1 (555) 987-6543', 'MALE', '2025-06-01', '2025-06-20', 'USCG-12345678', 'Myanmar', '09777-990', NULL, 'section1', 7);

-- COMPANY
INSERT INTO company (id, created_at, created_by, updated_at, updated_by, company_address, company_email, company_fax, company_phone, name, registered_by, year_established) VALUES
    (1, '2025-06-24 09:33:45.780101', 'Register User', NULL, NULL, 'Yangon,Myanmar', 'contact@acme.com', 'COMPANY FAX', '+1 (212) 555-1234', 'MDT', 'Mr.Park', '2004-09-08');

-- SHIP
INSERT INTO ship (id, created_at, created_by, updated_at, updated_by, flag, imo_number, mmsi, name, ship_office, type, year_built, company_id) VALUES
    (1, '2025-06-24 09:34:09.778185', 'Register User', NULL, NULL, 'myanmar', '8999', '3423-0909', 'Ace Corporation 7', 'yangon', 'container', '2004-09-08', 1);

-- COMPANY_SHIPS
INSERT INTO company_ships (company_id, ships_id) VALUES
    (1, 1);

-- MACH_GROUP
INSERT INTO mach_group (id, mach_group_name) VALUES
                                                 (1, 'D2 DECK MACHINERY'),
                                                 (2, 'D5 SAFETY EQUIPMENTS'),
                                                 (3, 'M1 MAIN ENGINE AND SHAFTING');

-- MACHINERY (must come before components)
INSERT INTO machinery (id, machinery_name, mach_group_id) VALUES
                                                              (1, 'BALLAST WATER TREATMENT SYSTEM', 1),
                                                              (2, 'WINDLASS & MOORING WINCH', 1),
                                                              (3, 'FIRE FIGHTING SYSTEM', 2),
                                                              (4, 'LIFE BOAT SYSTEM', 2),
                                                              (5, 'M/E BRIDGE MANEUVERING SYSTEM', 3),
                                                              (6, 'DIESEL MAIN ENGINE', 3);

-- COMPONENT (must come before tasks)
INSERT INTO component (id, component_name, machinery_id) VALUES
                                                             (1, 'UV UNIT', 1),
                                                             (2, 'ELECTRO POWER UNIT FOR WINCHES', 2),
                                                             (3, 'FIREMAN OUTFIT', 3),
                                                             (4, 'LIFE BOAT DAVIT & WINCH(ST\'BD)', 4),
                                                             (5, 'ENGINE SIDE MANEUVERING SYSTEM', 5),
                                                             (6, 'BRIDGE MANEUVERING SYSTEM', 5),
                                                             (7, 'AUX\' BLOWER', 6),
                                                             (8, 'AIR INTER COOLER', 6),
                                                             (9, 'STARTING AIR DISTRIBUTOR', 6),
                                                             (10, 'CROSS HEAD WITH CONNECTING ROD', 6);

-- Now we can insert TASKS (depends on components)
INSERT INTO task (id, critical, description, kind, position, task_type, title, component_id) VALUES
                                                                                                 (1, b'0', 'desc', 'PMS', 'C/O', 'Monthly', 'BWTS UV UNIT QUARTZ SLEEVE CHEMICAL(ALCOHOL) CLEANING', 1),
                                                                                                 (2, b'0', 'desc', 'PMS', 'C/O', 'Monthly', 'BWTS UV TEMP\' TRANSMITTER & UV INTENSITY TRANSMITTER CALIBRATION', 1),
                                                                                                 (3, b'0', 'desc', 'PMS', 'C/O', 'Monthly', 'BWTS RENEWED UV UNIT WIPER', 1),
                                                                                                 (4, b'0', 'desc', 'PMS', 'C/O', 'Monthly', 'BWTS UV UNIT PURGING UNIT OPERATING CONDITION CHECK', 1),
                                                                                                 (5, b'0', 'desc', 'PMS', 'C/O', 'Monthly', 'BWTS RENEWED UV LAMP', 1),
                                                                                                 (6, b'0', 'desc', 'PMS', 'C/O', 'Monthly', 'MAKE AN OIL ANALYSIS', 2),
                                                                                                 (7, b'0', 'desc', 'PMS', 'C/O', 'Monthly', 'TAKE A INVENTORY & CHECK STORING CONDITION', 3),
                                                                                                 (8, b'0', 'desc', 'PMS', 'C/O', 'Monthly', 'CARRY OUT LAUNCHING ON THE SEA', 4),
                                                                                                 (9, b'0', 'desc', 'PMS', 'C/O', 'Monthly', 'CARRY OUT W/H SIDE TEST OF M/E MANEUVERING SYSTEM', 6),
                                                                                                 (10, b'0', 'desc', 'PMS', 'C/O', 'Monthly', 'OVHL\' NO.1 AUX\' BLOWER', 7),
                                                                                                 (11, b'0', 'desc', 'PMS', 'C/O', 'Monthly', 'OVHL\' STARTING AIR DISTRIBUTOR FOR M/E', 9),
                                                                                                 (12, b'0', 'desc', 'PMS', 'C/O', 'Monthly', 'INSPECT THE NO.7 CYL\' CROSS HEAD & CON-ROD', 10),
                                                                                                 (13, b'1', 'Desc', 'CMS', 'C/O', 'Weekly', 'I love you', 1),
                                                                                                 (14, b'0', 'desc', 'PMS', 'C/O', 'Monthly', 'INSPECT THE NO.7 CYL\' CROSS HEAD & CON-ROD', 10);

-- COMPONENT_TASKS
INSERT INTO component_tasks (component_id, tasks_id) VALUES
                                                         (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 13),
                                                         (2, 6),
                                                         (3, 7),
                                                         (4, 8),
                                                         (6, 9),
                                                         (7, 10),
                                                         (9, 11),
                                                         (10, 12), (10, 14);

-- MATERIAL
INSERT INTO material (id, created_at, created_by, updated_at, updated_by, material_condition, description, life_time, manufacturer, name, price, received_date, serial_no, supplier_info, type, use_status, ship_id) VALUES
                                                                                                                                                                                                                         (1, '2025-06-24 09:38:07.888791', 'Register User', NULL, NULL, 'Excellent', 'DESC', '', 'Bosch Hydraulics', 'Hydraulic1001', 2000, '2025-06-23', 'MAT-2023-001', 'Marine Parts Co., contact: John Smith (john@marineparts.com)', 'lubricant', b'0', NULL),
                                                                                                                                                                                                                         (2, '2025-06-24 09:38:42.450520', 'Register User', NULL, NULL, 'Excellent', 'desc', '', 'Bosch Hydraulics', 'Hydraulic1001', 1000, '2025-06-23', 'MAT-2023-002', 'Marine Parts Co., contact: John Smith (john@marineparts.com)', 'lubricant', b'0', NULL),
                                                                                                                                                                                                                         (3, '2025-06-24 09:39:16.460249', 'Register User', NULL, NULL, 'Excellent', 'desc', '', 'Bosch Hydraulics', 'Hydraulic1001', 1000, '2025-06-23', 'MAT-2023-003', 'Marine Parts Co., contact: John Smith (john@marineparts.com)', 'lubricant', b'0', NULL),
                                                                                                                                                                                                                         (4, '2025-06-24 09:39:43.493830', 'Register User', NULL, NULL, 'Excellent', 'desc', '', 'Bosch Hydraulics', 'Hydraulic1002', 1000, '2025-06-23', 'MAT-2023-004', 'Marine Parts Co., contact: John Smith (john@marineparts.com)', 'lubricant', b'0', NULL);

-- CREW_ASSIGNMENT
INSERT INTO crew_assignment (id, end_date, position, start_date, crew_id, ship_id) VALUES
                                                                                       (1, '2025-07-12', 'FIRSTLEADER', '2025-06-23', 2, 1),
                                                                                       (3, '2025-07-12', 'THIRDLEADER', '2025-06-23', 4, 1),
                                                                                       (4, '2025-07-12', 'WORKER', '2025-06-23', 5, 1);

-- TASK_ASSIGNMENT
INSERT INTO task_assignment (id, assigned_date, completed, deadline_date, crew_id, ship_id, task_id) VALUES
                                                                                                         (1, '2025-06-24', b'1', '2025-06-24', 5, 1, 6),
                                                                                                         (2, '2025-06-24', b'0', '2025-06-24', 5, 1, 3),
                                                                                                         (3, '2025-06-25', b'0', '2025-07-25', 5, 1, 6);

-- REPORT_REQUEST
INSERT INTO report_request (id, approved, content, report_type, request_date, title, crew_id, task_assignment_id) VALUES
    (2, b'0', 'desc', NULL, '2025-06-24', 'Request For make an oil analysis', 5, 1);

-- APPROVAL
INSERT INTO approval (id, approval_timestamp, position, crew_id, report_request_id) VALUES
                                                                                        (1, '2025-06-24 10:18:24.648561', 'third', 4, 2),
                                                                                        (2, '2025-06-24 10:23:37.759477', 'second', 7, 2);

-- MAINTENANCE_LOG
INSERT INTO maintenance_log (id, last_work, next_due, remark, status, crew_id, task_id) VALUES
    (1, '2025-06-25', '2025-07-25', 'notes-remarks', 'COMPLETED', 5, 6);

-- TASK_SCHEDULE_SEQ
INSERT INTO task_schedule_seq (next_val) VALUES (1);
