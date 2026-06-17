INSERT INTO roles(role_name)
VALUES ('CLINIC_ADMIN'),
       ('COMPANY_HR')
ON CONFLICT (role_name) DO NOTHING;

CREATE TABLE IF NOT EXISTS organizations (
    id bigserial PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL,
    bin VARCHAR(14) UNIQUE NOT NULL,
    address TEXT,
    phone VARCHAR(20),
    email VARCHAR(255),
    is_active BOOLEAN DEFAULT true,
    contract_start DATE,
    contract_end DATE,
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS organization_users (
    id bigserial PRIMARY KEY,
    organization_id bigint REFERENCES organizations(id),
    user_id bigint REFERENCES users(id),
    role VARCHAR(50) NOT NULL,
    department VARCHAR(100),
    is_active BOOLEAN DEFAULT true,
    joined_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS patient_consents (
    id bigserial PRIMARY KEY,
    patient_id bigint REFERENCES users(id),
    organization_id bigint REFERENCES organizations(id),
    consent_type VARCHAR(50) NOT NULL,
    granted_at TIMESTAMP DEFAULT NOW(),
    expires_at TIMESTAMP,
    revoked_at TIMESTAMP,
    granted_by_patient BOOLEAN DEFAULT true
);

CREATE TABLE IF NOT EXISTS medical_visits (
    id bigserial PRIMARY KEY,
    patient_id bigint REFERENCES users(id),
    clinic_id bigint REFERENCES organizations(id),
    doctor_id bigint REFERENCES organization_users(id),
    visit_date TIMESTAMP NOT NULL,
    visit_type VARCHAR(50),
    chief_complaint TEXT,
    diagnosis_primary VARCHAR(20),
    diagnosis_secondary VARCHAR(500),
    treatment_plan TEXT,
    notes TEXT,
    duration_minutes INT,
    cost DECIMAL(10,2),
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS employee_health_groups (
    id bigserial PRIMARY KEY,
    organization_id bigint REFERENCES organizations(id),
    patient_id bigint REFERENCES users(id),
    employee_id VARCHAR(50),
    department VARCHAR(100),
    position VARCHAR(100),
    health_group VARCHAR(10),
    last_checkup_date DATE,
    next_checkup_date DATE,
    is_active BOOLEAN DEFAULT true,
    joined_at DATE
);

CREATE TABLE IF NOT EXISTS analytics_events (
    id bigserial PRIMARY KEY,
    event_type VARCHAR(100) NOT NULL,
    patient_id bigint,
    organization_id bigint REFERENCES organizations(id),
    metadata JSONB,
    event_date TIMESTAMP DEFAULT NOW(),
    age_group VARCHAR(20),
    gender VARCHAR(10),
    region VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS access_logs (
    id bigserial PRIMARY KEY,
    patient_id bigint REFERENCES users(id),
    accessed_by_user_id bigint REFERENCES users(id),
    organization_id bigint REFERENCES organizations(id),
    access_type VARCHAR(50),
    accessed_at TIMESTAMP DEFAULT NOW(),
    ip_address VARCHAR(45)
);
