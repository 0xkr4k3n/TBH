# TBH - Back End

## **Setup Instructions**

### 1. **Build the Project**
Run the following Maven command to clean, compile, and package the application while skipping tests:
```bash
mvn clean package -DskipTests
```

This will create a JAR file in the `target/` directory.

---

### 2. **Run DNSMASQ**
Macos:
```bash
sudo brew services start dnsmasq
```

---

### 3. **Run the Application**
you can run hte project locally for dev or testing purposes 
<br>OR<br>
Use Docker Compose to build and run the application with its dependencies:
```bash
docker-compose up --build
```

This command:
- Builds the Docker images for the application.
- Spins up all required services, such as the backend and database.
