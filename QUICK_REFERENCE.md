# AutoCare Hub - Quick Reference Card

## 🚀 APPLICATION ACCESS

**Main URL:** http://localhost:8081/autocare-hub/login  
**Port:** 8081  
**Status:** ✅ RUNNING

---

## 🔐 LOGIN CREDENTIALS

### Admin Account
```
Email: admin@autocare.com
Password: admin123
```

### Demo User Account
```
Email: rahul@example.com
Password: admin123
```

---

## 📍 KEY URLs

| Page | URL |
|------|-----|
| Login | http://localhost:8081/autocare-hub/login |
| Register | http://localhost:8081/autocare-hub/register |
| Dashboard | http://localhost:8081/autocare-hub/dashboard |
| Vehicles | http://localhost:8081/autocare-hub/vehicles |
| Book Service | http://localhost:8081/autocare-hub/book |
| Booking History | http://localhost:8081/autocare-hub/history |
| Admin Panel | http://localhost:8081/autocare-hub/admin |
| Logout | http://localhost:8081/autocare-hub/logout |

---

## 🗄️ DATABASE

```
Host: localhost:3306
Database: autocare_hub
Username: root
Password: tiger
```

**Quick MySQL Access:**
```bash
mysql -u root -ptiger autocare_hub
```

---

## 🔄 RESTART COMMANDS

### Stop Tomcat
```powershell
cd "C:\Program Files\Apache Software Foundation\Tomcat 10.1\bin"
.\shutdown.bat
```

### Start Tomcat
```powershell
cd "C:\Program Files\Apache Software Foundation\Tomcat 10.1\bin"
.\startup.bat
```

### Rebuild & Redeploy
```powershell
cd "D:\9.DCL project\autocare-hub"
mvn clean package
Copy-Item "target\autocare-hub.war" "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps\" -Force
```

---

## 📊 PROJECT STRUCTURE

```
autocare-hub/
├── src/main/java/com/project/
│   ├── controller/    # Servlets (7 files)
│   ├── dao/          # Database Access (4 files)
│   ├── model/        # Entities (4 files)
│   ├── service/      # Business Logic (1 file)
│   └── util/         # Utilities (2 files)
├── src/main/webapp/
│   ├── jsp/          # JSP Pages (8 files)
│   ├── css/          # Stylesheets
│   ├── js/           # JavaScript
│   └── WEB-INF/
│       └── web.xml   # Servlet Mappings
└── target/
    └── autocare-hub.war  # Deployable WAR
```

---

## 🧪 QUICK TEST CHECKLIST

### User Flow
- [ ] Register new user
- [ ] Login with credentials
- [ ] Add vehicle
- [ ] Book service
- [ ] View booking history
- [ ] Logout

### Admin Flow
- [ ] Login as admin
- [ ] Add new service
- [ ] Update service price
- [ ] View all bookings
- [ ] Update booking status
- [ ] View all users/vehicles

---

## 🔧 TROUBLESHOOTING

### Port Already in Use
```powershell
netstat -ano | findstr :8081
taskkill /PID <PID> /F
```

### Check Tomcat Status
```powershell
netstat -ano | findstr :8081
```

### View Tomcat Logs
```powershell
Get-Content "C:\Program Files\Apache Software Foundation\Tomcat 10.1\logs\catalina.*.log" -Tail 50
```

### Check Database
```bash
mysql -u root -ptiger -e "USE autocare_hub; SHOW TABLES;"
```

---

## 📦 MAVEN COMMANDS

```bash
# Clean build
mvn clean

# Compile
mvn compile

# Package WAR
mvn package

# Clean + Package
mvn clean package

# Skip tests
mvn clean package -DskipTests
```

---

## 🎯 FEATURES IMPLEMENTED

✅ User Registration & Login  
✅ Admin Panel  
✅ Vehicle Management  
✅ Service Booking  
✅ Booking History  
✅ Service Management  
✅ Booking Status Updates  
✅ Session Management  
✅ Password Hashing (SHA-256)  
✅ Duplicate Prevention  
✅ Date/Time Validation  

---

## 📈 TECH STACK

| Layer | Technology |
|-------|-----------|
| Frontend | JSP, HTML, CSS, JavaScript |
| Backend | Java Servlets (Jakarta EE 10) |
| Database | MySQL 8.0.45 |
| Server | Apache Tomcat 10.1.40 |
| Build | Maven 3.9.9 |
| Java | JDK 17.0.12 |

---

## 🎓 MVC COMPONENTS

**Model:** User, Vehicle, Service, Booking  
**View:** JSP pages (login, dashboard, admin, etc.)  
**Controller:** Servlets (Login, Register, Dashboard, Admin, etc.)  
**DAO:** UserDAO, VehicleDAO, ServiceDAO, BookingDAO  
**Service:** BookingService (business logic)  
**Util:** DBConnection, PasswordUtil  

---

## 📝 IMPORTANT FILES

| File | Purpose |
|------|---------|
| `pom.xml` | Maven dependencies |
| `web.xml` | Servlet mappings |
| `db.properties` | Database config |
| `schema.sql` | Database schema |
| `DBConnection.java` | DB connection |
| `PasswordUtil.java` | Password hashing |

---

## 🔐 SECURITY FEATURES

- SHA-256 password hashing
- Session-based authentication
- SQL injection prevention (PreparedStatement)
- Input validation
- Session timeout (30 minutes)
- Role-based access control (USER/ADMIN)

---

## 📞 QUICK HELP

**Application not loading?**
1. Check Tomcat is running: `netstat -ano | findstr :8081`
2. Check logs: View catalina.log
3. Restart Tomcat

**Database error?**
1. Check MySQL is running
2. Verify credentials in db.properties
3. Run: `mysql -u root -ptiger -e "SHOW DATABASES;"`

**Build failed?**
1. Run: `mvn clean`
2. Run: `mvn clean package`
3. Check Java version: `java -version`

---

**🎉 Application is LIVE and READY for testing!**

Access now: http://localhost:8081/autocare-hub/login
