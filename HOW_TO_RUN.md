# 🚀 How to Run AutoCare Hub - Step by Step

## ⚡ QUICK START (If Already Set Up)

**The project is ALREADY RUNNING!** Just open your browser:

```
http://localhost:8081/autocare-hub/login
```

**Login Credentials:**
- Admin: `admin@autocare.com` / `admin123`
- User: `rahul@example.com` / `admin123`

---

## 🔄 IF TOMCAT IS NOT RUNNING

### Step 1: Start Tomcat
```powershell
# Open PowerShell and run:
cd "C:\Program Files\Apache Software Foundation\Tomcat 10.1\bin"
.\startup.bat
```

### Step 2: Wait 15 Seconds
Tomcat needs time to start and deploy the application.

### Step 3: Open Browser
```
http://localhost:8081/autocare-hub/login
```

**That's it!** ✅

---

## 📋 COMPLETE SETUP (From Scratch)

If you need to set up the project from the beginning on a new machine:

### Prerequisites Check

**1. Verify Java is Installed**
```powershell
java -version
# Should show: Java 17 or higher
```

**2. Verify Maven is Installed**
```powershell
mvn -version
# Should show: Maven 3.9.9 or higher
```

**3. Verify MySQL is Running**
```powershell
mysql -u root -p
# Enter your MySQL password
```

**4. Verify Tomcat is Installed**
```powershell
# Check if this folder exists:
cd "C:\Program Files\Apache Software Foundation\Tomcat 10.1"
```

---

## 🗄️ STEP-BY-STEP SETUP

### Step 1: Set Up Database

**Option A: Using MySQL Command Line**
```powershell
# Navigate to project directory
cd "D:\9.DCL project\autocare-hub"

# Run schema.sql
mysql -u root -p < src\main\resources\sql\schema.sql
# Enter password: tiger
```

**Option B: Using MySQL Workbench**
1. Open MySQL Workbench
2. Connect to localhost
3. Open file: `src/main/resources/sql/schema.sql`
4. Click "Execute" (⚡ icon)

**Verify Database:**
```sql
mysql -u root -ptiger
USE autocare_hub;
SHOW TABLES;
-- Should show: users, vehicles, services, bookings
```

---

### Step 2: Configure Database Connection

**Edit:** `src/main/resources/db.properties`

```properties
db.url=jdbc:mysql://localhost:3306/autocare_hub?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
db.username=root
db.password=tiger
```

**⚠️ Important:** Change `tiger` to YOUR MySQL root password!

---

### Step 3: Build the Project

```powershell
# Navigate to project directory
cd "D:\9.DCL project\autocare-hub"

# Clean and build
mvn clean package

# Wait for "BUILD SUCCESS" message
```

**Expected Output:**
```
[INFO] BUILD SUCCESS
[INFO] Total time: 30-40 seconds
[INFO] WAR file: target/autocare-hub.war
```

---

### Step 4: Deploy to Tomcat

**Option A: Automatic Deployment (Recommended)**
```powershell
# Copy WAR to Tomcat webapps
Copy-Item "target\autocare-hub.war" "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps\" -Force
```

**Option B: Manual Deployment**
1. Open File Explorer
2. Navigate to: `D:\9.DCL project\autocare-hub\target\`
3. Copy `autocare-hub.war`
4. Paste to: `C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps\`

---

### Step 5: Start Tomcat

```powershell
# Navigate to Tomcat bin directory
cd "C:\Program Files\Apache Software Foundation\Tomcat 10.1\bin"

# Start Tomcat
.\startup.bat
```

**You should see:**
```
Using CATALINA_BASE: "C:\Program Files\Apache Software Foundation\Tomcat 10.1"
Using CATALINA_HOME: "C:\Program Files\Apache Software Foundation\Tomcat 10.1"
...
```

---

### Step 6: Wait for Deployment

**Wait 15-20 seconds** for Tomcat to:
1. Start the server
2. Deploy the WAR file
3. Initialize the application

**Check if Tomcat is running:**
```powershell
netstat -ano | findstr :8081
```

**You should see:**
```
TCP    0.0.0.0:8081    0.0.0.0:0    LISTENING    <PID>
```

---

### Step 7: Access the Application

**Open your browser and go to:**
```
http://localhost:8081/autocare-hub/login
```

**You should see the AutoCare Hub login page!** 🎉

---

## 🧪 TEST THE APPLICATION

### Test 1: User Login
1. Go to: http://localhost:8081/autocare-hub/login
2. Enter:
   - Email: `rahul@example.com`
   - Password: `admin123`
3. Click "Login"
4. **Expected:** You see the user dashboard

### Test 2: Admin Login
1. Logout (click "Logout" button)
2. Login again with:
   - Email: `admin@autocare.com`
   - Password: `admin123`
3. Click "Login"
4. **Expected:** You see the admin panel

### Test 3: Register New User
1. Go to: http://localhost:8081/autocare-hub/register
2. Fill in:
   - Name: Your Name
   - Email: your@email.com
   - Password: yourpassword
3. Click "Register"
4. **Expected:** Success message, redirected to login

### Test 4: Add Vehicle
1. Login as user
2. Click "Vehicles" in navigation
3. Fill in:
   - Type: Car
   - Model: Honda City
   - Number: KA04XY1234
4. Click "Add Vehicle"
5. **Expected:** Vehicle added successfully

### Test 5: Book Service
1. Click "Book Service" in navigation
2. Select:
   - Vehicle: Honda City
   - Service: Oil Change
   - Date: Tomorrow
   - Time: 10:00
3. Click "Confirm Booking"
4. **Expected:** Booking created, redirected to history

---

## 🔄 HOW TO RESTART

### If You Close Everything

**1. Start MySQL (if not running)**
```powershell
# MySQL usually runs as a service
# Check in Services: Win + R → services.msc → MySQL80
```

**2. Start Tomcat**
```powershell
cd "C:\Program Files\Apache Software Foundation\Tomcat 10.1\bin"
.\startup.bat
```

**3. Wait 15 seconds**

**4. Open browser**
```
http://localhost:8081/autocare-hub/login
```

---

### If You Make Code Changes

**1. Stop Tomcat**
```powershell
cd "C:\Program Files\Apache Software Foundation\Tomcat 10.1\bin"
.\shutdown.bat
```

**2. Rebuild Project**
```powershell
cd "D:\9.DCL project\autocare-hub"
mvn clean package
```

**3. Remove Old Deployment**
```powershell
Remove-Item "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps\autocare-hub.war" -Force
Remove-Item "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps\autocare-hub" -Recurse -Force
```

**4. Deploy New WAR**
```powershell
Copy-Item "target\autocare-hub.war" "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps\" -Force
```

**5. Start Tomcat**
```powershell
cd "C:\Program Files\Apache Software Foundation\Tomcat 10.1\bin"
.\startup.bat
```

---

## 🔧 TROUBLESHOOTING

### Problem 1: "Port 8081 already in use"

**Solution:**
```powershell
# Find what's using port 8081
netstat -ano | findstr :8081

# Kill the process (replace <PID> with actual number)
taskkill /PID <PID> /F

# Start Tomcat again
cd "C:\Program Files\Apache Software Foundation\Tomcat 10.1\bin"
.\startup.bat
```

---

### Problem 2: "Database connection failed"

**Check 1: MySQL is running**
```powershell
mysql -u root -ptiger -e "SELECT 1;"
```

**Check 2: Database exists**
```powershell
mysql -u root -ptiger -e "SHOW DATABASES;" | findstr autocare_hub
```

**Check 3: Correct password in db.properties**
```
Open: src/main/resources/db.properties
Verify: db.password=tiger (or your MySQL password)
```

**Fix: Recreate database**
```powershell
mysql -u root -ptiger < src\main\resources\sql\schema.sql
```

---

### Problem 3: "404 - Application not found"

**Check 1: WAR file exists**
```powershell
Get-ChildItem "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps" | findstr autocare
```

**Check 2: Tomcat logs**
```powershell
Get-Content "C:\Program Files\Apache Software Foundation\Tomcat 10.1\logs\catalina.*.log" -Tail 50
```

**Fix: Redeploy**
```powershell
# Stop Tomcat
cd "C:\Program Files\Apache Software Foundation\Tomcat 10.1\bin"
.\shutdown.bat

# Copy WAR again
Copy-Item "D:\9.DCL project\autocare-hub\target\autocare-hub.war" "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps\" -Force

# Start Tomcat
.\startup.bat
```

---

### Problem 4: "Maven build failed"

**Solution 1: Clean Maven cache**
```powershell
mvn clean
mvn clean package
```

**Solution 2: Force update dependencies**
```powershell
mvn clean install -U
```

**Solution 3: Check Java version**
```powershell
java -version
# Must be Java 11 or higher
```

---

### Problem 5: "Tomcat won't start"

**Check 1: JAVA_HOME is set**
```powershell
echo $env:JAVA_HOME
# Should show Java installation path
```

**Check 2: Port 8081 is free**
```powershell
netstat -ano | findstr :8081
# Should show nothing
```

**Check 3: Tomcat logs for errors**
```powershell
Get-Content "C:\Program Files\Apache Software Foundation\Tomcat 10.1\logs\catalina.*.log" -Tail 100
```

---

## 📱 ACCESSING FROM OTHER DEVICES

### Access from Phone/Tablet on Same Network

**1. Find your computer's IP address**
```powershell
ipconfig
# Look for "IPv4 Address" under your active network
# Example: 192.168.1.100
```

**2. Open browser on phone/tablet**
```
http://192.168.1.100:8081/autocare-hub/login
```

**⚠️ Note:** Your computer's firewall must allow port 8081

---

## 🎯 QUICK COMMAND REFERENCE

### Start Everything
```powershell
# Start Tomcat
cd "C:\Program Files\Apache Software Foundation\Tomcat 10.1\bin"
.\startup.bat

# Wait 15 seconds, then open:
# http://localhost:8081/autocare-hub/login
```

### Stop Everything
```powershell
# Stop Tomcat
cd "C:\Program Files\Apache Software Foundation\Tomcat 10.1\bin"
.\shutdown.bat
```

### Rebuild & Redeploy
```powershell
# 1. Stop Tomcat
cd "C:\Program Files\Apache Software Foundation\Tomcat 10.1\bin"
.\shutdown.bat

# 2. Rebuild
cd "D:\9.DCL project\autocare-hub"
mvn clean package

# 3. Deploy
Copy-Item "target\autocare-hub.war" "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps\" -Force

# 4. Start Tomcat
cd "C:\Program Files\Apache Software Foundation\Tomcat 10.1\bin"
.\startup.bat
```

### Check Status
```powershell
# Check if Tomcat is running
netstat -ano | findstr :8081

# Check if database is accessible
mysql -u root -ptiger -e "USE autocare_hub; SELECT COUNT(*) FROM users;"
```

---

## 📚 IMPORTANT FILES & LOCATIONS

| Item | Location |
|------|----------|
| Project Directory | `D:\9.DCL project\autocare-hub` |
| WAR File | `D:\9.DCL project\autocare-hub\target\autocare-hub.war` |
| Tomcat Directory | `C:\Program Files\Apache Software Foundation\Tomcat 10.1` |
| Tomcat Webapps | `C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps` |
| Tomcat Logs | `C:\Program Files\Apache Software Foundation\Tomcat 10.1\logs` |
| Database Config | `src/main/resources/db.properties` |
| Database Schema | `src/main/resources/sql/schema.sql` |

---

## 🎓 WHAT HAPPENS WHEN YOU RUN

### 1. When you run `mvn clean package`:
- Maven downloads dependencies (if needed)
- Compiles Java files (`.java` → `.class`)
- Copies resources (JSP, CSS, JS, properties)
- Packages everything into `autocare-hub.war`

### 2. When you copy WAR to Tomcat webapps:
- Tomcat detects the new WAR file
- Automatically extracts it to `autocare-hub/` folder
- Deploys the application

### 3. When you start Tomcat:
- Tomcat starts on port 8081
- Loads all web applications in webapps/
- Initializes servlets
- Connects to MySQL database
- Application becomes accessible

### 4. When you access the URL:
- Browser sends request to Tomcat
- Tomcat routes to appropriate servlet
- Servlet processes request (queries database if needed)
- JSP renders the response
- Browser displays the page

---

## ✅ SUCCESS CHECKLIST

Before testing, verify:

- [ ] MySQL is running
- [ ] Database `autocare_hub` exists
- [ ] `db.properties` has correct password
- [ ] Maven build succeeded (BUILD SUCCESS)
- [ ] WAR file exists in `target/`
- [ ] WAR copied to Tomcat webapps
- [ ] Tomcat started successfully
- [ ] Port 8081 is listening
- [ ] No errors in Tomcat logs
- [ ] Browser can access login page

---

## 🎉 YOU'RE READY!

**Open your browser and go to:**
```
http://localhost:8081/autocare-hub/login
```

**Login with:**
- Admin: `admin@autocare.com` / `admin123`
- User: `rahul@example.com` / `admin123`

**Enjoy testing your AutoCare Hub application!** 🚗🔧

---

## 📞 NEED HELP?

Check these files for more information:
- `PROJECT_SETUP_GUIDE.md` - Complete documentation
- `QUICK_REFERENCE.md` - Quick commands
- `DEPLOYMENT_SUMMARY.md` - What was deployed

**Common Issues:**
- Port already in use → Kill process on port 8081
- Database error → Check MySQL password in db.properties
- 404 error → Redeploy WAR file
- Build failed → Run `mvn clean` first

---

**Happy Testing! 🎊**
