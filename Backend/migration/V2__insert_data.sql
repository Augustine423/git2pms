CREATE TABLE `admin`
(
    id        BIGINT       NOT NULL,
    education VARCHAR(255) NULL,
    CONSTRAINT pk_admin PRIMARY KEY (id)
);

CREATE TABLE admin_roles
(
    admin_id BIGINT NOT NULL,
    roles_id INT    NOT NULL,
    CONSTRAINT pk_users_roles PRIMARY KEY (admin_id, roles_id)
);

CREATE TABLE approval
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    approver_id        BIGINT                NULL,
    approval_rank      VARCHAR(255)          NULL,
    approval_timestamp datetime              NULL,
    report_request_id  BIGINT                NULL,
    CONSTRAINT pk_approval PRIMARY KEY (id)
);

CREATE TABLE approval_log
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    report_request_id BIGINT                NULL,
    CONSTRAINT pk_approvallog PRIMARY KEY (id)
);

CREATE TABLE company
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    created_at       datetime              NULL,
    created_by       VARCHAR(255)          NULL,
    updated_at       datetime              NULL,
    updated_by       VARCHAR(255)          NULL,
    name             VARCHAR(255)          NULL,
    registered_by    VARCHAR(255)          NOT NULL,
    company_address  VARCHAR(255)          NULL,
    company_email    VARCHAR(255)          NULL,
    year_established VARCHAR(255)          NULL,
    company_phone    VARCHAR(255)          NULL,
    company_fax      VARCHAR(255)          NULL,
    CONSTRAINT pk_company PRIMARY KEY (id)
);

CREATE TABLE `component`
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    machinery_id   BIGINT                NULL,
    component_name VARCHAR(255)          NULL,
    CONSTRAINT pk_component PRIMARY KEY (id)
);

CREATE TABLE component_tasks
(
    component_id BIGINT NOT NULL,
    tasks_id     BIGINT NOT NULL
);

CREATE TABLE crew
(
    id                  BIGINT       NOT NULL,
    gender              VARCHAR(255) NULL,
    phone               VARCHAR(255) NULL,
    img_url             VARCHAR(255) NULL,
    birth_date          date         NULL,
    joined_date         date         NULL,
    active              BIT(1)       NOT NULL,
    crew_rank           VARCHAR(255) NULL,
    nationality         VARCHAR(255) NULL,
    emergency_phone     VARCHAR(255) NULL,
    emergency_email     VARCHAR(255) NULL,
    photo_url           VARCHAR(255) NULL,
    certificates        VARCHAR(255) NULL,
    certificates_expiry date         NULL,
    license_no          VARCHAR(255) NULL,
    license_expiry      date         NULL,
    CONSTRAINT pk_crew PRIMARY KEY (id)
);

CREATE TABLE crew_assignment
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    crew_id     BIGINT                NULL,
    ship_id     BIGINT                NULL,
    assigned_by BIGINT                NULL,
    report_to   BIGINT                NULL,
    section     VARCHAR(255)          NULL,
    active      BIT(1)                NOT NULL,
    remarks     VARCHAR(512)          NULL,
    position    VARCHAR(255)          NULL,
    start_date  date                  NULL,
    end_date    date                  NULL,
    CONSTRAINT pk_crewassignment PRIMARY KEY (id)
);

CREATE TABLE crew_roles
(
    crew_id  BIGINT NOT NULL,
    roles_id INT    NOT NULL,
    CONSTRAINT pk_users_roles PRIMARY KEY (crew_id, roles_id)
);

CREATE TABLE mach_group
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    mach_group_name VARCHAR(255)          NULL,
    CONSTRAINT pk_machgroup PRIMARY KEY (id)
);

CREATE TABLE machinery
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    mach_group_id  BIGINT                NULL,
    machinery_name VARCHAR(255)          NULL,
    CONSTRAINT pk_machinery PRIMARY KEY (id)
);

CREATE TABLE maintenance_log
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    task_id        BIGINT                NULL,
    crew_id        BIGINT                NOT NULL,
    verified_by_id BIGINT                NULL,
    next_due       date                  NULL,
    remarks        VARCHAR(255)          NOT NULL,
    logged_at      date                  NOT NULL,
    status         VARCHAR(255)          NULL,
    last_work      date                  NULL,
    media_urls     VARCHAR(1000)         NULL,
    CONSTRAINT pk_maintenancelog PRIMARY KEY (id)
);

CREATE TABLE material
(
    id                   BIGINT AUTO_INCREMENT NOT NULL,
    serial_no            VARCHAR(255)          NULL,
    name                 VARCHAR(255)          NULL,
    `description`        VARCHAR(255)          NULL,
    type                 VARCHAR(255)          NULL,
    quantity             INT                   NOT NULL,
    status               VARCHAR(255)          NULL,
    manufacturer         VARCHAR(255)          NULL,
    price                BIGINT                NOT NULL,
    material_condition   VARCHAR(255)          NULL,
    supplier_info        VARCHAR(255)          NULL,
    received_date        date                  NULL,
    life_time_hours      INT                   NOT NULL,
    expected_expiry_date date                  NULL,
    location_code        VARCHAR(255)          NULL,
    ship_id              BIGINT                NULL,
    CONSTRAINT pk_material PRIMARY KEY (id)
);

CREATE TABLE material_report_request
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    material_id       BIGINT                NULL,
    report_request_id BIGINT                NULL,
    CONSTRAINT pk_materialreportrequest PRIMARY KEY (id)
);

CREATE TABLE material_usage_log
(
    end_at  datetime     NULL,
    used_by VARCHAR(255) NULL,
    remarks VARCHAR(255) NULL,
    id      BIGINT       NOT NULL,
    used_at datetime     NOT NULL,
    ship_id INT          NOT NULL,
    CONSTRAINT pk_materialusagelog PRIMARY KEY (id, used_at, ship_id)
);

CREATE TABLE report_request
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    title              VARCHAR(255)          NULL,
    crew_id            BIGINT                NULL,
    content            VARCHAR(255)          NULL,
    task_assignment_id BIGINT                NULL,
    report_type        VARCHAR(255)          NULL,
    request_date       date                  NULL,
    status             VARCHAR(255)          NULL,
    CONSTRAINT pk_reportrequest PRIMARY KEY (id)
);

CREATE TABLE `role`
(
    id        INT AUTO_INCREMENT NOT NULL,
    role_name SMALLINT           NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE ship
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    created_at  datetime              NULL,
    created_by  VARCHAR(255)          NULL,
    updated_at  datetime              NULL,
    updated_by  VARCHAR(255)          NULL,
    name        VARCHAR(255)          NULL,
    imo_number  VARCHAR(255)          NULL,
    flag        VARCHAR(255)          NULL,
    type        VARCHAR(255)          NULL,
    year_built  date                  NULL,
    mmsi        VARCHAR(255)          NULL,
    ship_office VARCHAR(255)          NULL,
    company_id  BIGINT                NULL,
    CONSTRAINT pk_ship PRIMARY KEY (id)
);

CREATE TABLE task
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    title          VARCHAR(255)          NULL,
    `description`  VARCHAR(255)          NULL,
    position       VARCHAR(255)          NULL,
    critical       BIT(1)                NOT NULL,
    component_id   BIGINT                NULL,
    interval_value INT                   NOT NULL,
    interval_unit  VARCHAR(255)          NULL,
    kind           VARCHAR(255)          NULL,
    CONSTRAINT pk_task PRIMARY KEY (id)
);

CREATE TABLE task_assignment
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    task_id       BIGINT                NULL,
    crew_id       BIGINT                NULL,
    ship_id       BIGINT                NULL,
    report_to_id  BIGINT                NULL,
    assigned_date date                  NULL,
    deadline_date date                  NULL,
    status        VARCHAR(255)          NULL,
    CONSTRAINT pk_taskassignment PRIMARY KEY (id)
);

CREATE TABLE task_schedule
(
    id           BIGINT       NOT NULL,
    task_id      BIGINT       NULL,
    next_due     date         NULL,
    frequency    VARCHAR(255) NULL,
    repeat_count INT          NOT NULL,
    CONSTRAINT pk_taskschedule PRIMARY KEY (id)
);

CREATE TABLE users
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime              NULL,
    created_by VARCHAR(255)          NULL,
    updated_at datetime              NULL,
    updated_by VARCHAR(255)          NULL,
    first_name VARCHAR(255)          NULL,
    last_name  VARCHAR(255)          NULL,
    password   VARCHAR(255)          NULL,
    email      VARCHAR(255)          NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE users_roles
(
    user_id  BIGINT NOT NULL,
    roles_id INT    NOT NULL,
    CONSTRAINT pk_users_roles PRIMARY KEY (user_id, roles_id)
);

ALTER TABLE approval_log
    ADD CONSTRAINT uc_approvallog_report_request UNIQUE (report_request_id);

ALTER TABLE component_tasks
    ADD CONSTRAINT uc_component_tasks_tasks UNIQUE (tasks_id);

ALTER TABLE material
    ADD CONSTRAINT uc_material_serialno UNIQUE (serial_no);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE `admin`
    ADD CONSTRAINT FK_ADMIN_ON_ID FOREIGN KEY (id) REFERENCES users (id);

ALTER TABLE approval_log
    ADD CONSTRAINT FK_APPROVALLOG_ON_REPORT_REQUEST FOREIGN KEY (report_request_id) REFERENCES report_request (id);

ALTER TABLE approval
    ADD CONSTRAINT FK_APPROVAL_ON_APPROVER FOREIGN KEY (approver_id) REFERENCES crew (id);

ALTER TABLE approval
    ADD CONSTRAINT FK_APPROVAL_ON_REPORTREQUEST FOREIGN KEY (report_request_id) REFERENCES report_request (id);

ALTER TABLE `component`
    ADD CONSTRAINT FK_COMPONENT_ON_MACHINERY FOREIGN KEY (machinery_id) REFERENCES machinery (id);

ALTER TABLE crew_assignment
    ADD CONSTRAINT FK_CREWASSIGNMENT_ON_ASSIGNED_BY FOREIGN KEY (assigned_by) REFERENCES crew (id);

ALTER TABLE crew_assignment
    ADD CONSTRAINT FK_CREWASSIGNMENT_ON_CREW FOREIGN KEY (crew_id) REFERENCES crew (id);

ALTER TABLE crew_assignment
    ADD CONSTRAINT FK_CREWASSIGNMENT_ON_REPORT_TO FOREIGN KEY (report_to) REFERENCES crew (id);

ALTER TABLE crew_assignment
    ADD CONSTRAINT FK_CREWASSIGNMENT_ON_SHIP FOREIGN KEY (ship_id) REFERENCES ship (id);

ALTER TABLE crew
    ADD CONSTRAINT FK_CREW_ON_ID FOREIGN KEY (id) REFERENCES users (id);

ALTER TABLE machinery
    ADD CONSTRAINT FK_MACHINERY_ON_MACHGROUP FOREIGN KEY (mach_group_id) REFERENCES mach_group (id);

ALTER TABLE maintenance_log
    ADD CONSTRAINT FK_MAINTENANCELOG_ON_CREW FOREIGN KEY (crew_id) REFERENCES crew (id);

ALTER TABLE maintenance_log
    ADD CONSTRAINT FK_MAINTENANCELOG_ON_TASK FOREIGN KEY (task_id) REFERENCES task (id);

ALTER TABLE maintenance_log
    ADD CONSTRAINT FK_MAINTENANCELOG_ON_VERIFIED_BY FOREIGN KEY (verified_by_id) REFERENCES crew (id);

ALTER TABLE material_report_request
    ADD CONSTRAINT FK_MATERIALREPORTREQUEST_ON_MATERIAL FOREIGN KEY (material_id) REFERENCES material (id);

ALTER TABLE material_report_request
    ADD CONSTRAINT FK_MATERIALREPORTREQUEST_ON_REPORT_REQUEST FOREIGN KEY (report_request_id) REFERENCES report_request (id);

ALTER TABLE material
    ADD CONSTRAINT FK_MATERIAL_ON_SHIP FOREIGN KEY (ship_id) REFERENCES ship (id);

ALTER TABLE report_request
    ADD CONSTRAINT FK_REPORTREQUEST_ON_CREW FOREIGN KEY (crew_id) REFERENCES crew (id);

ALTER TABLE report_request
    ADD CONSTRAINT FK_REPORTREQUEST_ON_TASKASSIGNMENT FOREIGN KEY (task_assignment_id) REFERENCES task_assignment (id);

ALTER TABLE ship
    ADD CONSTRAINT FK_SHIP_ON_COMPANY FOREIGN KEY (company_id) REFERENCES company (id);

ALTER TABLE task_assignment
    ADD CONSTRAINT FK_TASKASSIGNMENT_ON_CREW FOREIGN KEY (crew_id) REFERENCES crew (id);

ALTER TABLE task_assignment
    ADD CONSTRAINT FK_TASKASSIGNMENT_ON_REPORTTO FOREIGN KEY (report_to_id) REFERENCES crew (id);

ALTER TABLE task_assignment
    ADD CONSTRAINT FK_TASKASSIGNMENT_ON_SHIP FOREIGN KEY (ship_id) REFERENCES ship (id);

ALTER TABLE task_assignment
    ADD CONSTRAINT FK_TASKASSIGNMENT_ON_TASK FOREIGN KEY (task_id) REFERENCES task (id);

ALTER TABLE task_schedule
    ADD CONSTRAINT FK_TASKSCHEDULE_ON_TASK FOREIGN KEY (task_id) REFERENCES task (id);

ALTER TABLE task
    ADD CONSTRAINT FK_TASK_ON_COMPONENT FOREIGN KEY (component_id) REFERENCES `component` (id);

ALTER TABLE admin_roles
    ADD CONSTRAINT fk_admrol_on_admin FOREIGN KEY (admin_id) REFERENCES `admin` (id);

ALTER TABLE admin_roles
    ADD CONSTRAINT fk_admrol_on_role FOREIGN KEY (roles_id) REFERENCES `role` (id);

ALTER TABLE component_tasks
    ADD CONSTRAINT fk_comtas_on_component FOREIGN KEY (component_id) REFERENCES `component` (id);

ALTER TABLE component_tasks
    ADD CONSTRAINT fk_comtas_on_task FOREIGN KEY (tasks_id) REFERENCES task (id);

ALTER TABLE crew_roles
    ADD CONSTRAINT fk_crerol_on_crew FOREIGN KEY (crew_id) REFERENCES crew (id);

ALTER TABLE crew_roles
    ADD CONSTRAINT fk_crerol_on_role FOREIGN KEY (roles_id) REFERENCES `role` (id);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (roles_id) REFERENCES `role` (id);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES users (id);
