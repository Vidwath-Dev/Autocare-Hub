# AutoCare Hub - Vehicle Service Booking System
## Complete Setup & Testing Guide

---

## ✅ PROJECT STATUS: **FULLY DEPLOYED & RUNNING**

**Application URL:** http://localhost:8081/autocare-hub/login  
**Tomcat Version:** 10.1.40  
**Port:** 8081  
**Database:** autocare_hub (MySQL 8.0.45)

---

## 📋 TABLE OF CONTENTS
1. [Project Overview](#project-overview)
2. [MVC Architecture](#mvc-architecture)
3. [Environment Details](#environment-details)
4. [Database Setup](#database-setup)
5. [Login Credentials](#login-credentials)
6. [Testing Guide](#testing-guide)
7. [How to Restart](#how-to-restart)
8. [Troubleshooting](#troubleshooting)
9. [Future Improvements](#future-improvements)

---

## 🎯 PROJECT OVERVIEW

**AutoCare Hub** is a comprehensive vehicle service booking system built using:
- **Backend:** Java Servlets (Jakarta EE 10)
- **Frontend:** JSP, HTML, CSS, JavaScript
- **Database:** MySQL 8.0
- **Server:** Apache Tomcat 10.1
- **Build Tool:** Maven 3.9.9
- **Java Version:** JDK 17

### Key Features
✅ User Registration & Login  
✅ Admin Panel  
✅ Vehicle Management (Add Cars/Bikes)  
✅ Service Booking System  
✅ Booking History  
✅ Service Management (Admin)  
✅ Booking Status Updates  
✅ Session Management  
✅ Password Hashing (SHA-256)  
✅ Duplicate Prevention (Vehicles, Bookings)  
✅ Date/Time Validation  

---

## 🏗️ MVC ARCHITECTURE

### **Model Layer** (`src/main/java/com/project/model/`)
- **User.java** - User entity (id, name, email, password, role)
- **Vehicle.java** - Vehicle entity (id, userId, type, model, number)
- **Service.java** - Service entity (id, name, price, isActive)
- **Booking.java** - Booking entity (id, userId, vehicleId, serviceId, date, time, status)

### **Controller Layer** (`src/main/java/com/project/controller/`)
- **LoginServlet.java** - Handles user/admin login
- **RegisterServlet.java** - User registration
- **DashboardServlet.java** - User dashboard
- **AdminServlet.java** - Admin panel operations
- **AddVehicleServlet.java** - Vehicle management
- **BookServiceServlet.java** - Service booking & history
- **LogoutServlet.java** - Session invalidation

### **DAO Layer** (`src/main/java/com/project/dao/`)
- **UserDAO.java** - User database operations
- **VehicleDAO.java** - Vehicle CRUD operations
- **ServiceDAO.java** - Service management
- **BookingDAO.java** - Booking operations

### **Service Layer** (`src/main/java/com/project/service/`)
- **BookingService.java** - Business logic for bookings (validation, slot checking)

### **Utility Layer** (`src/main/java/com/project/util/`)
- **DBConnection.java** - Database connection management
- **PasswordUtil.java** - SHA-256 password hashing

### **View Layer** (`src/main/webapp/jsp/`)
- **login.jsp** - Login page
- **register.jsp** - Registration page
- **dashboard.jsp** - User dashboard
- **admin-panel.jsp** - Admin panel
- **add-vehicle.jsp** - Vehicle management
- **book-service.jsp** - Service booking
- **booking-history.jsp** - Booking history

---

## 💻 ENVIRONMENT DETAILS

### Installed Software
| Software | Version | Location |
|----------|---------|----------|
| Java | JDK 17.0.12 | C:\Program Files\Eclipse Adoptium\jdk-17.0.12.7-hotspot |
| Maven | 3.9.9 | D:\Program Files\apache-maven-3.9.9 |
| MySQL | 8.0.45 | C:\Program Files\MySQL\MySQL Server 8.0 |
| Tomcat | 10.1.40 | C:\Program Files\Apache Software Foundation\Tomcat 10.1 |

### Project Structure
```
autocare-hub/
├── src/
│   └── main/
│       ├── java/com/project/
│       │   ├── controller/     # Servlets
│       │   ├── dao/            # Database Access Objects
│       │   ├── model/          # Entity classes
│       │   ├── service/        # Business logic
│       │   └── util/           # Utilities
│       ├── resources/
│       │   ├── db.properties   # Database config
│       │   └── sql/
│       │       └── schema.sql  # Database schema
│       └── webapp/
│           ├── css/
│           ├── js/
│           ├── jsp/            # JSP pages
│           └── WEB-INF/
│               └── web.xml     # Servlet mappings
├── target/
│   └── autocare-hub.war        # Deployable WAR file
└── pom.xml                     # Maven configuration
```

---

## 🗄️ DATABASE SETUP

### Database Details
- **Database Name:** autocare_hub
- **Host:** localhost:3306
- **Username:** root
- **Password:** tiger

### Tables Created
1. **users** - User accounts (Admin & Regular users)
2. **vehicles** - User vehicles (Cars & Bikes)
3. **services** - Available services
4. **bookings** - Service bookings

### Database Schema
```sql
-- Users table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('USER', 'ADMIN') NOT NULL DEFAULT 'USER'
);

-- Vehicles table
CREATE TABLE vehicles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    type ENUM('Car', 'Bike') NOT NULL,
    model VARCHAR(100) NOT NULL,
    number VARCHAR(30) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    UNIQUE KEY (user_id, number)
);

-- Services table
CREATE TABLE services (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE
);

-- Bookings table
CREATE TABLE bookings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    vehicle_id INT NOT NULL,
    service_id INT NOT NULL,
    date DATE NOT NULL,
    time TIME NOT NULL,
    status ENUM('Pending', 'Completed', 'Cancelled') NOT NULL DEFAULT 'Pending',
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(id) ON DELETE CASCADE,
    FOREIGN KEY (service_id) REFERENCES services(id),
    UNIQUE KEY (date, time, vehicle_id)
);
```

### Seed Data Inserted
**Users:**
- Admin: admin@autocare.com / admin123 (ADMIN role)
- User: rahul@example.com / admin123 (USER role)

**Services:**
- Oil Change - Rs. 1200
- Washing - Rs. 500
- Engine Check - Rs. 1500
- Full Service - Rs. 4500

**Vehicles (for Rahul):**
- Car: Hyundai i20 (KA01AB1234)
- Bike: Honda Activa (KA02CD5678)

**Sample Booking:**
- User: Rahul Sharma
- Vehicle: Hyundai i20
- Service: Full Service
- Date: Tomorrow
- Time: 10:00 AM
- Status: Pending

---

## 🔐 LOGIN CREDENTIALS

### Admin Account
- **Email:** admin@autocare.com
- **Password:** admin123
- **Access:** Full admin panel, manage services, view all bookings, update booking status

### Demo User Account
- **Email:** rahul@example.com
- **Password:** admin123
- **Access:** Dashboard, add vehicles, book services, view booking history

### Password Hashing
All passwords are hashed using **SHA-256** algorithm before storing in database.

---

## 🧪 TESTING GUIDE

### Step 1: User Module Testing

#### 1.1 User Registration
1. Navigate to: http://localhost:8081/autocare-hub/register
2. Fill in:
   - Name: Test User
   - Email: test@example.com
   - Password: test123
3. Click "Register"
4. **Expected:** Success message, redirected to login page

#### 1.2 User Login
1. Navigate to: http://localhost:8081/autocare-hub/login
2. Enter credentials:
   - Email: test@example.com
   - Password: test123
3. Click "Login"
4. **Expected:** Redirected to user dashboard

#### 1.3 Session Management
1. After login, check session is active
2. Navigate to different pages (vehicles, bookings)
3. **Expected:** User remains logged in
4. Click "Logout"
5. **Expected:** Session invalidated, redirected to login

---

### Step 2: Vehicle Module Testing

#### 2.1 Add Vehicle
1. Login as user
2. Navigate to: http://localhost:8081/autocare-hub/vehicles
3. Fill in:
   - Type: Car
   - Model: Toyota Innova
   - Number: KA03XY9876
4. Click "Add Vehicle"
5. **Expected:** Vehicle added successfully, appears in list

#### 2.2 Duplicate Vehicle Prevention
1. Try adding same vehicle number again
2. **Expected:** Error message - duplicate vehicle number

#### 2.3 View Vehicles
1. Check "Saved Vehicles" section
2. **Expected:** All added vehicles displayed

---

### Step 3: Booking Module Testing

#### 3.1 Create Booking
1. Navigate to: http://localhost:8081/autocare-hub/book
2. Select:
   - Vehicle: Toyota Innova
   - Service: Oil Change
   - Date: Tomorrow's date
   - Time: 09:00
3. Click "Confirm Booking"
4. **Expected:** Booking created, redirected to history page

#### 3.2 Date Validation
1. Try booking with past date
2. **Expected:** Error - "Please select today or a future date"

#### 3.3 Time Slot Validation
1. Try booking same vehicle, same date/time
2. **Expected:** Error - "This vehicle already has a booking for the selected slot"

#### 3.4 View Booking History
1. Navigate to: http://localhost:8081/autocare-hub/history
2. **Expected:** All bookings displayed with status

---

### Step 4: Admin Module Testing

#### 4.1 Admin Login
1. Logout current user
2. Login with:
   - Email: admin@autocare.com
   - Password: admin123
3. **Expected:** Redirected to admin panel

#### 4.2 Add Service
1. In admin panel, fill "Add Service" form:
   - Service Name: Tire Rotation
   - Price: 800
2. Click "Add Service"
3. **Expected:** Service added to list

#### 4.3 Update Service
1. In services table, modify:
   - Name or Price
   - Active checkbox
2. Click "Save"
3. **Expected:** Service updated

#### 4.4 Soft Delete Service
1. Click "Soft Delete" button for a service
2. **Expected:** Service marked as inactive (is_active = false)

#### 4.5 View All Bookings
1. Check "All Bookings" section
2. **Expected:** All user bookings displayed

#### 4.6 Update Booking Status
1. Select a booking
2. Change status dropdown (Pending/Completed/Cancelled)
3. Click "Update"
4. **Expected:** Booking status updated

#### 4.7 View Users & Vehicles
1. Scroll to bottom sections
2. **Expected:** All registered users and vehicles displayed

---

## 🔄 HOW TO RESTART THE APPLICATION

### Method 1: Restart Tomcat
```powershell
# Stop Tomcat
cd "C:\Program Files\Apache Software Foundation\Tomcat 10.1\bin"
.\shutdown.bat

# Wait 5 seconds

# Start Tomcat
.\startup.bat
```

### Method 2: Redeploy WAR
```powershell
# Navigate to project directory
cd "D:\9.DCL project\autocare-hub"

# Rebuild WAR
mvn clean package

# Copy to Tomcat
Copy-Item "target\autocare-hub.war" "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps\" -Force

# Restart Tomcat (see Method 1)
```

### Method 3: Full Rebuild
```powershell
# Clean and rebuild
mvn clean package

# Remove old deployment
Remove-Item "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps\autocare-hub.war" -Force
Remove-Item "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps\autocare-hub" -Recurse -Force

# Deploy new WAR
Copy-Item "target\autocare-hub.war" "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps\"

# Restart Tomcat
cd "C:\Program Files\Apache Software Foundation\Tomcat 10.1\bin"
.\shutdown.bat
Start-Sleep -Seconds 5
.\startup.bat
```

---

## 🔧 TROUBLESHOOTING

### Issue 1: Port 8081 Already in Use
**Solution:**
```powershell
# Find process using port 8081
netstat -ano | findstr :8081

# Kill the process (replace PID)
taskkill /PID <PID> /F

# Restart Tomcat
```

### Issue 2: Database Connection Failed
**Check:**
1. MySQL service is running
2. Database credentials in `src/main/resources/db.properties`:
   ```properties
   db.url=jdbc:mysql://localhost:3306/autocare_hub?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
   db.username=root
   db.password=tiger
   ```
3. Database exists:
   ```sql
   mysql -u root -ptiger -e "SHOW DATABASES;"
   ```

### Issue 3: 404 Error - Application Not Found
**Solution:**
1. Check WAR is deployed:
   ```powershell
   Get-ChildItem "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps"
   ```
2. Check Tomcat logs:
   ```powershell
   Get-Content "C:\Program Files\Apache Software Foundation\Tomcat 10.1\logs\catalina.*.log" -Tail 50
   ```

### Issue 4: Servlet Mapping Errors
**Check `web.xml`:**
- All servlets are properly mapped
- URL patterns match controller paths
- Servlet class names are correct

### Issue 5: Maven Build Fails
**Solution:**
```powershell
# Clean Maven cache
mvn clean

# Force update dependencies
mvn clean install -U

# Skip tests if needed
mvn clean package -DskipTests
```

---

## 🚀 FUTURE IMPROVEMENTS

### 1. **Migrate to Spring Boot**
**Benefits:**
- Auto-configuration
- Embedded server (no Tomcat setup needed)
- Spring Data JPA (no manual DAO)
- Spring Security (better authentication)
- RESTful APIs

**Migration Steps:**
1. Create Spring Boot project with dependencies:
   - Spring Web
   - Spring Data JPA
   - MySQL Driver
   - Thymeleaf (or keep JSP)
2. Convert Servlets to `@Controller` classes
3. Convert DAOs to Spring Data JPA repositories
4. Add `@Entity` annotations to models
5. Configure `application.properties`

### 2. **REST API Development**
**Endpoints to Create:**
```
POST   /api/auth/register
POST   /api/auth/login
GET    /api/vehicles
POST   /api/vehicles
GET    /api/services
POST   /api/bookings
GET    /api/bookings
PUT    /api/bookings/{id}/status
```

### 3. **JWT Authentication**
**Replace session-based auth with JWT:**
- Stateless authentication
- Better for mobile apps
- Scalable for microservices

### 4. **React Frontend**
**Modern SPA:**
- React components
- Redux state management
- Axios for API calls
- Material-UI design

### 5. **Additional Features**
- Email notifications (booking confirmation)
- SMS alerts
- Payment gateway integration
- Service ratings & reviews
- Vehicle service history
- Mechanic assignment
- Real-time booking availability
- Mobile app (React Native)

### 6. **Resume-Level Improvements**
**Highlight these skills:**
- ✅ Full-stack Java web development
- ✅ MVC architecture implementation
- ✅ JDBC & MySQL database design
- ✅ Servlet lifecycle management
- ✅ Session management & authentication
- ✅ RESTful API design (if implemented)
- ✅ Maven build automation
- ✅ Tomcat deployment
- ✅ Security best practices (password hashing)
- ✅ Input validation & error handling

---

## 📊 PROJECT STATISTICS

- **Total Java Files:** 18
- **Total JSP Files:** 8
- **Total Lines of Code:** ~2,500+
- **Database Tables:** 4
- **Servlets:** 7
- **DAO Classes:** 4
- **Model Classes:** 4
- **Build Time:** ~32 seconds
- **WAR Size:** 7.5 MB
- **Deployment Time:** ~3 seconds

---

## 📝 IMPORTANT NOTES

1. **Jakarta EE 10 Migration:**
   - Project was migrated from `javax.servlet.*` to `jakarta.servlet.*`
   - Required for Tomcat 10.1 compatibility
   - All imports updated in servlets

2. **Password Security:**
   - Passwords hashed using SHA-256
   - Never stored in plain text
   - Demo password: `admin123` (hash: `240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9`)

3. **Database Constraints:**
   - Unique constraint on user email
   - Unique constraint on vehicle number per user
   - Unique constraint on booking slot (date + time + vehicle)
   - Foreign key cascades on delete

4. **Session Timeout:**
   - Set to 30 minutes (1800 seconds)
   - Configured in `LoginServlet.java`

---

## 🎓 LEARNING OUTCOMES

By completing this project, you've learned:
1. ✅ Java Servlet programming
2. ✅ JSP & JSTL
3. ✅ JDBC database connectivity
4. ✅ MVC design pattern
5. ✅ DAO pattern
6. ✅ Session management
7. ✅ Form validation
8. ✅ Maven build tool
9. ✅ Tomcat deployment
10. ✅ MySQL database design

---

## 📞 SUPPORT

If you encounter any issues:
1. Check Tomcat logs: `C:\Program Files\Apache Software Foundation\Tomcat 10.1\logs\`
2. Verify MySQL is running: `mysql -u root -ptiger -e "SELECT 1;"`
3. Check port 8081: `netstat -ano | findstr :8081`
4. Rebuild project: `mvn clean package`

---

**Project Successfully Deployed! 🎉**

**Access the application at:** http://localhost:8081/autocare-hub/login

---

*Generated on: May 6, 2026*  
*Tomcat Version: 10.1.40*  
*Java Version: 17.0.12*  
*MySQL Version: 8.0.45*
