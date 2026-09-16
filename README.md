# OWASP Juice Shop Selenium Test Automation Suite

![Selenium](https://img.shields.io/badge/-Selenium-%2343B02A?style=for-the-badge&logo=selenium&logoColor=white)
 ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)                               ![Maven](https://img.shields.io/badge/apachemaven-%23C71A36.svg?style=for-the-badge&logo=apachemaven&logoColor=white)     
## 🎯 What This Portfolio Demonstrates
 
- **Page Object Model (POM)** for maintainable, reusable test code
- **End-to-end Selenium automation** with TestNG
- **Maven-based build automation**

## Tech Stack
- **Language:** Java 21
- **Build Tool:** Maven
- **Automation:** Selenium WebDriver (v4.48.0)
- **Testing Framework:** TestNG (v7.12.0)
- **Logging:** Logback / SLF4J
- **Containerization:** Docker

## Prerequisites
 Before running the tests, ensure you have the following installed:
* **JDK 21** or higher
* **Apache Maven** (v3.8+)
* **Docker** (for AUT setup)

 
## 🚀 How to Run

1. **Clone the repository:**
   ```bash
   git clone https://github.com/ashraf2033/juice-shop-selenium-tests.git
   cd juice-shop-selenium-tests

   ```
2. **Install the Application Under Test (AUT):**
    ```bash
    docker compose up -d
    ```

 

3. **Clean and compile the project:**
   ```bash
    mvn clean test
   ```

## 📁 Project Structure


```
juice-shop-selenium-tests/
├── src/test/java/
│   ├── pages/                   # Page Object Model classes
│   ├── tests/                   # Test cases
│   └── utils/                   # Utilities & helpers
├── docker-compose.yml           # AUT setup
├── pom.xml                      # Maven dependencies & plugins
├── testing-config.yml           # AUT configurations
└── README.md
```
## 📝 Example Test
```java
@Test(description = "Verify login success with valid credentials", groups = {"login", "smoke"})
public void validLoginTest() {
    landingPage = loginPage
        .loginWithNameAndPass("basil@juice-sh.op", "password123");
    
    landingPage.expandAccountMenu();
    
    Assert.assertTrue(
        landingPage.getPageSource().contains("basil@juice-sh.op"),
        "User email should appear in account menu after successful login"
    );
}
```
## 🔗 About the AUT
 
The application under test is  [OWASP Juice Shop](https://github.com/juice-shop/juice-shop) an intentionally insecure e-commerce application ideal for testing real-world scenarios.
