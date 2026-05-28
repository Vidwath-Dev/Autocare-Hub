-- Railway init script (runs on Railway MySQL)
-- Does NOT drop database - Railway manages the DB

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('USER', 'ADMIN') NOT NULL DEFAULT 'USER'
);

CREATE TABLE IF NOT EXISTS vehicles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    type ENUM('Car', 'Bike') NOT NULL,
    model VARCHAR(100) NOT NULL,
    number VARCHAR(30) NOT NULL,
    CONSTRAINT fk_vehicles_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT uq_user_vehicle_number UNIQUE (user_id, number)
);

CREATE TABLE IF NOT EXISTS services (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS bookings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    vehicle_id INT NOT NULL,
    service_id INT NOT NULL,
    `date` DATE NOT NULL,
    `time` TIME NOT NULL,
    status ENUM('Pending', 'Completed', 'Cancelled') NOT NULL DEFAULT 'Pending',
    CONSTRAINT fk_bookings_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_bookings_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicles(id) ON DELETE CASCADE,
    CONSTRAINT fk_bookings_service FOREIGN KEY (service_id) REFERENCES services(id),
    CONSTRAINT uq_vehicle_slot UNIQUE (`date`, `time`, vehicle_id)
);

-- Insert demo users (password = admin123 hashed with SHA-256)
INSERT IGNORE INTO users (name, email, password, role) VALUES
('Admin', 'admin@autocare.com', '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9', 'ADMIN'),
('Rahul Sharma', 'rahul@example.com', '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9', 'USER');

-- Insert demo vehicles
INSERT IGNORE INTO vehicles (user_id, type, model, number) VALUES
(2, 'Car', 'Hyundai i20', 'KA01AB1234'),
(2, 'Bike', 'Honda Activa', 'KA02CD5678');

-- Insert services
INSERT IGNORE INTO services (name, price, is_active) VALUES
('Oil Change', 1200.00, TRUE),
('Washing', 500.00, TRUE),
('Engine Check', 1500.00, TRUE),
('Full Service', 4500.00, TRUE);
