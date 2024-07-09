# Automation Framework

## Overview

This project is an automation framework built using Maven, Java, Selenium, and TestNG. It supports running tests on any website and includes features for logging and reporting. The framework uses Log4j for logging and Extent Reports for generating test reports.

## Project Structure

- **src/main/java**: Contains the main code for the framework.
    - **base**: Contains the `BaseTest` class which sets up the WebDriver and common utilities.
    - **utilities**: Contains utility classes like `ExtentManager` and `ReadPropertyFile`.
- **src/test/java**: Contains the test cases.
    - **testcase**: Contains test classes like `TestClass`.
- **resources**: Contains configuration files and test data.
    - **configfiles**: Contains configuration properties files.
    - **testdata**: Contains test data files.
- **reports**: Contains the generated test reports.

## Dependencies

- Maven
- Java
- Selenium
- TestNG
- Log4j
- Extent Reports

## Configuration

Configuration properties are stored in `src/test/resources/configfiles/config.properties`. Here you can set up various configurations like the browser type, test URL, etc.

### Available Properties

- **browser**: Specifies the browser to use (e.g., `chrome`, `firefox`).
- **testUrl**: Specifies the URL of the website to test.

### Customizing Properties

Currently, the properties are hardcoded in the `config.properties` file. Future updates will include the ability to set these properties dynamically from test data.

## Usage

### Setup Instructions

1. Clone the repository.
2. Install dependencies using Maven.
3. Set up any required environment variables or configuration files.

### Running Tests

#### Using IDE

To run individual test cases, use the run button in your IDE's GUI.

#### Using Command Line

To run the entire test suite, execute the following Maven command:

```sh
mvn test
```

### Logging

The framework uses Log4j for logging. Logs are generated for each test run and can be found in the `logs` directory.

### Reporting

Extent Reports are generated for each test run. After running tests, the reports can be found in the `reports/extentReports.html` file.

## Sample Test

Here is a sample test class demonstrating how to use the framework:

```java
package testcase;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.logging.Logger;

class TestClass extends BaseTest {
    private static final Logger logger = Logger.getLogger(TestClass.class.getName());

    @Test
    public static void loginTest() throws InterruptedException {
        logger.info("Starting loginTest");
        clickSignIn();
        enterEmail("kartikdixit37093@gmail.com");
        clickNext();
    }

    private static void clickSignIn() {
        driverTools.findElement(By.linkText("Sign in")).click();
        logger.info("Clicked on Sign in button");
    }

    private static void enterEmail(String email) throws InterruptedException {
        WebElement inputID = driverTools.findElement(By.id("login_id"));
        logger.info("Found the email input field");
        Thread.sleep(4000);
        logger.info("Waiting for 4 seconds");
        inputID.sendKeys(email);
        logger.info("Entered the email id");
    }

    private static void clickNext() {
        driverTools.findElement(By.xpath("//button[contains(@class,'btn blue') and contains(@id,'nextbtn')]")).click();
        logger.info("Clicked on Next button");
    }
}
```

## Troubleshooting

### Common Issues

1. **Browser not opening**: Ensure the correct browser driver is set up in the properties file.
2. **Properties file not loading**: Check the path to the `config.properties` file.

## Contributing

Contributions are welcome! Please follow these guidelines:

1. Fork the repository.
2. Create a new branch (`feature-branch`).
3. Make your changes.
4. Submit a pull request.

## Contact Information

For support or questions, you can reach me on  [LinkedIn](https://www.linkedin.com/in/kartikdixit03/).

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---
