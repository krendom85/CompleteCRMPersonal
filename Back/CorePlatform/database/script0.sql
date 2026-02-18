-- Triggers de timestamps para todas las tablas relevantes
CREATE TRIGGER role_set_timestamps
BEFORE INSERT OR UPDATE ON role
FOR EACH ROW EXECUTE FUNCTION set_timestamps();

CREATE TRIGGER permission_set_timestamps
BEFORE INSERT OR UPDATE ON permission
FOR EACH ROW EXECUTE FUNCTION set_timestamps();

CREATE TRIGGER role_permission_set_timestamps
BEFORE INSERT OR UPDATE ON role_permission
FOR EACH ROW EXECUTE FUNCTION set_timestamps();

CREATE TRIGGER user_role_set_timestamps
BEFORE INSERT OR UPDATE ON user_role
FOR EACH ROW EXECUTE FUNCTION set_timestamps();

-- Sistema de auditoría
CREATE TABLE audit_log (
    id SERIAL PRIMARY KEY,
    table_name VARCHAR(100) NOT NULL,
    operation VARCHAR(10) NOT NULL, -- INSERT, UPDATE, DELETE
    record_id INTEGER NOT NULL,
    changed_data JSONB,
    changed_at TIMESTAMP DEFAULT NOW(),
    changed_by INTEGER
);

CREATE OR REPLACE FUNCTION audit_trigger_func() RETURNS TRIGGER AS $$
BEGIN
    IF TG_OP = 'DELETE' THEN
        INSERT INTO audit_log(table_name, operation, record_id, changed_data, changed_by)
        VALUES (TG_TABLE_NAME, TG_OP, OLD.id, row_to_json(OLD), OLD.updated_by);
        RETURN OLD;
    ELSE
        INSERT INTO audit_log(table_name, operation, record_id, changed_data, changed_by)
        VALUES (TG_TABLE_NAME, TG_OP, NEW.id, row_to_json(NEW), NEW.updated_by);
        RETURN NEW;
    END IF;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER company_audit_trigger
AFTER INSERT OR UPDATE OR DELETE ON company
FOR EACH ROW EXECUTE FUNCTION audit_trigger_func();

CREATE TRIGGER user_audit_trigger
AFTER INSERT OR UPDATE OR DELETE ON "user"
FOR EACH ROW EXECUTE FUNCTION audit_trigger_func();

CREATE TRIGGER client_audit_trigger
AFTER INSERT OR UPDATE OR DELETE ON client
FOR EACH ROW EXECUTE FUNCTION audit_trigger_func();

CREATE TRIGGER role_audit_trigger
AFTER INSERT OR UPDATE OR DELETE ON role
FOR EACH ROW EXECUTE FUNCTION audit_trigger_func();

CREATE TRIGGER permission_audit_trigger
AFTER INSERT OR UPDATE OR DELETE ON permission
FOR EACH ROW EXECUTE FUNCTION audit_trigger_func();

CREATE TRIGGER role_permission_audit_trigger
AFTER INSERT OR UPDATE OR DELETE ON role_permission
FOR EACH ROW EXECUTE FUNCTION audit_trigger_func();

CREATE TRIGGER user_role_audit_trigger
AFTER INSERT OR UPDATE OR DELETE ON user_role
FOR EACH ROW EXECUTE FUNCTION audit_trigger_func();
-- =============================
-- 1. Definición de tablas
-- =============================

CREATE TABLE company (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    logo_url VARCHAR(500),
    address VARCHAR(255),
    phone VARCHAR(50),
    website VARCHAR(255),
    status VARCHAR(20) DEFAULT 'active',
    locale VARCHAR(10) DEFAULT 'es',
    timezone VARCHAR(50) DEFAULT 'America/Bogota',
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP,
    created_by INTEGER,
    updated_by INTEGER
);

CREATE TABLE role (
    id SERIAL PRIMARY KEY,
    company_id INTEGER REFERENCES company(id),
    name VARCHAR(100) NOT NULL
);

CREATE TABLE permission (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE role_permission (
    role_id INTEGER REFERENCES role(id),
    permission_id INTEGER REFERENCES permission(id),
    PRIMARY KEY (role_id, permission_id)
);

CREATE TABLE "user" (
    id SERIAL PRIMARY KEY,
    company_id INTEGER REFERENCES company(id),
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    avatar_url VARCHAR(500),
    phone VARCHAR(50),
    address VARCHAR(255),
    is_superuser BOOLEAN DEFAULT FALSE,
    status VARCHAR(20) DEFAULT 'active',
    locale VARCHAR(10) DEFAULT 'es',
    timezone VARCHAR(50) DEFAULT 'America/Bogota',
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP,
    created_by INTEGER,
    updated_by INTEGER
);

CREATE TABLE user_role (
    user_id INTEGER REFERENCES "user"(id),
    role_id INTEGER REFERENCES role(id),
    PRIMARY KEY (user_id, role_id)
);

CREATE TABLE client (
    id SERIAL PRIMARY KEY,
    company_id INTEGER REFERENCES company(id),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    phone VARCHAR(50),
    address VARCHAR(255),
    status VARCHAR(20) DEFAULT 'active',
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP,
    created_by INTEGER,
    updated_by INTEGER
);

CREATE TABLE audit_log (
    id SERIAL PRIMARY KEY,
    table_name VARCHAR(100) NOT NULL,
    operation VARCHAR(10) NOT NULL, -- INSERT, UPDATE, DELETE
    record_id INTEGER NOT NULL,
    changed_data JSONB,
    changed_at TIMESTAMP DEFAULT NOW(),
    changed_by INTEGER
);

-- =============================
-- 2. Funciones para triggers
-- =============================

-- Timestamps automáticos
CREATE OR REPLACE FUNCTION set_timestamps()
RETURNS TRIGGER AS $$
BEGIN
    IF TG_OP = 'INSERT' THEN
        NEW.created_at := COALESCE(NEW.created_at, NOW());
        NEW.updated_at := NOW();
    ELSIF TG_OP = 'UPDATE' THEN
        NEW.updated_at := NOW();
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Auditoría
CREATE OR REPLACE FUNCTION audit_trigger_func() RETURNS TRIGGER AS $$
BEGIN
    IF TG_OP = 'DELETE' THEN
        INSERT INTO audit_log(table_name, operation, record_id, changed_data, changed_by)
        VALUES (TG_TABLE_NAME, TG_OP, OLD.id, row_to_json(OLD), OLD.updated_by);
        RETURN OLD;
    ELSE
        INSERT INTO audit_log(table_name, operation, record_id, changed_data, changed_by)
        VALUES (TG_TABLE_NAME, TG_OP, NEW.id, row_to_json(NEW), NEW.updated_by);
        RETURN NEW;
    END IF;
END;
$$ LANGUAGE plpgsql;

-- =============================
-- 3. Triggers de timestamps
-- =============================

CREATE TRIGGER company_set_timestamps
BEFORE INSERT OR UPDATE ON company
FOR EACH ROW EXECUTE FUNCTION set_timestamps();

CREATE TRIGGER role_set_timestamps
BEFORE INSERT OR UPDATE ON role
FOR EACH ROW EXECUTE FUNCTION set_timestamps();

CREATE TRIGGER permission_set_timestamps
BEFORE INSERT OR UPDATE ON permission
FOR EACH ROW EXECUTE FUNCTION set_timestamps();

CREATE TRIGGER role_permission_set_timestamps
BEFORE INSERT OR UPDATE ON role_permission
FOR EACH ROW EXECUTE FUNCTION set_timestamps();

CREATE TRIGGER "user_set_timestamps"
BEFORE INSERT OR UPDATE ON "user"
FOR EACH ROW EXECUTE FUNCTION set_timestamps();

CREATE TRIGGER user_role_set_timestamps
BEFORE INSERT OR UPDATE ON user_role
FOR EACH ROW EXECUTE FUNCTION set_timestamps();

CREATE TRIGGER client_set_timestamps
BEFORE INSERT OR UPDATE ON client
FOR EACH ROW EXECUTE FUNCTION set_timestamps();

-- =============================
-- 4. Triggers de auditoría
-- =============================

CREATE TRIGGER company_audit_trigger
AFTER INSERT OR UPDATE OR DELETE ON company
FOR EACH ROW EXECUTE FUNCTION audit_trigger_func();

CREATE TRIGGER role_audit_trigger
AFTER INSERT OR UPDATE OR DELETE ON role
FOR EACH ROW EXECUTE FUNCTION audit_trigger_func();

CREATE TRIGGER permission_audit_trigger
AFTER INSERT OR UPDATE OR DELETE ON permission
FOR EACH ROW EXECUTE FUNCTION audit_trigger_func();

CREATE TRIGGER role_permission_audit_trigger
AFTER INSERT OR UPDATE OR DELETE ON role_permission
FOR EACH ROW EXECUTE FUNCTION audit_trigger_func();

CREATE TRIGGER "user_audit_trigger"
AFTER INSERT OR UPDATE OR DELETE ON "user"
FOR EACH ROW EXECUTE FUNCTION audit_trigger_func();

CREATE TRIGGER user_role_audit_trigger
AFTER INSERT OR UPDATE OR DELETE ON user_role
FOR EACH ROW EXECUTE FUNCTION audit_trigger_func();

CREATE TRIGGER client_audit_trigger
AFTER INSERT OR UPDATE OR DELETE ON client
FOR EACH ROW EXECUTE FUNCTION audit_trigger_func();
CREATE TRIGGER client_set_timestamps
BEFORE INSERT OR UPDATE ON client
FOR EACH ROW EXECUTE FUNCTION set_timestamps();
CREATE TABLE company (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    logo_url VARCHAR(500),
    address VARCHAR(255),
    phone VARCHAR(50),
    website VARCHAR(255),
    status VARCHAR(20) DEFAULT 'active',
    locale VARCHAR(10) DEFAULT 'es',
    timezone VARCHAR(50) DEFAULT 'America/Bogota',
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP,
    created_by INTEGER,
    updated_by INTEGER
);

CREATE TABLE role (
    id SERIAL PRIMARY KEY,
    company_id INTEGER REFERENCES company(id),
    name VARCHAR(100) NOT NULL
);

CREATE TABLE permission (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE role_permission (
    role_id INTEGER REFERENCES role(id),
    permission_id INTEGER REFERENCES permission(id),
    PRIMARY KEY (role_id, permission_id)
);

CREATE TABLE "user" (
    id SERIAL PRIMARY KEY,
    company_id INTEGER REFERENCES company(id),
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    avatar_url VARCHAR(500),
    phone VARCHAR(50),
    address VARCHAR(255),
    is_superuser BOOLEAN DEFAULT FALSE,
    status VARCHAR(20) DEFAULT 'active',
    locale VARCHAR(10) DEFAULT 'es',
    timezone VARCHAR(50) DEFAULT 'America/Bogota',
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP,
    created_by INTEGER,
    updated_by INTEGER
);

CREATE TABLE user_role (
    user_id INTEGER REFERENCES "user"(id),
    role_id INTEGER REFERENCES role(id),
    PRIMARY KEY (user_id, role_id)
);

CREATE TABLE client (
    id SERIAL PRIMARY KEY,
    company_id INTEGER REFERENCES company(id),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    phone VARCHAR(50),
    address VARCHAR(255),
    status VARCHAR(20) DEFAULT 'active',
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP,
    created_by INTEGER,
    updated_by INTEGER
);

-- Función para setear created_at y updated_at automáticamente en INSERT y UPDATE
CREATE OR REPLACE FUNCTION set_timestamps()
RETURNS TRIGGER AS $$
BEGIN
    IF TG_OP = 'INSERT' THEN
        NEW.created_at := COALESCE(NEW.created_at, NOW());
        NEW.updated_at := NOW();
    ELSIF TG_OP = 'UPDATE' THEN
        NEW.updated_at := NOW();
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Triggers para tablas principales
CREATE TRIGGER company_set_timestamps
BEFORE INSERT OR UPDATE ON company
FOR EACH ROW EXECUTE FUNCTION set_timestamps();
