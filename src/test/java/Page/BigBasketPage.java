package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BigBasketPage {

    private WebDriver driver;
    private static final Logger logger = Logger.getLogger(BigBasketPage.class.getName());

    public BigBasketPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Checks if a specific section is present and retrieves all product names, prices, and image URLs within it.
     * @param sectionName The name of the section to look for (e.g., "Best Sellers").
     */
    public void checkRequiredSection(String sectionName) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement section = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class, 'Label-sc') and contains(@class, 'SingleTab___') and contains(text(), '" + sectionName + "')]")));
            String sectionUI = section.getText();
            logger.info("Section '" + sectionName + "' is present on the page.");

            // Retrieve product names and prices
            List<WebElement> productNames = driver.findElements(By.xpath("//span[contains(@class, 'SingleTab') and text()='" + sectionName + "']/preceding::h3[contains(@class, 'line-clamp-2')]"));
            List<WebElement> productPrices = driver.findElements(By.xpath("//div[contains(@class, 'Pricing')]//span[contains(@class, 'Label') and contains(@class, 'StyledLabel')][1]"));

            // Retrieve image URLs
            List<WebElement> productImages = driver.findElements(By.xpath("//img[contains(@class, 'DeckImage___StyledImage') and contains(@class, 'cSWRCd')]"));

            // Assertions to ensure lists are not empty and have matching sizes
            Assert.assertFalse(productNames.isEmpty(), "Product names list is empty.");
            Assert.assertFalse(productPrices.isEmpty(), "Product prices list is empty.");
            Assert.assertEquals(productNames.size(), productPrices.size(), "Mismatch between product names and prices count.");
            Assert.assertEquals(productNames.size(), productImages.size(), "Mismatch between product names and images count.");

            List<String> productDetails = new ArrayList<>();

            // Log each product's name, price, and image URL
            for (int i = 0; i < productNames.size(); i++) {
                String name = productNames.get(i).getText();
                String price = productPrices.get(i).getText();
                String imageUrl = productImages.get(i).getAttribute("src");

                // Assertions for individual values
                Assert.assertNotNull(name, "Product name is null.");
                Assert.assertNotNull(price, "Product price is null.");
                Assert.assertNotNull(imageUrl, "Product image URL is null.");
                Assert.assertFalse(name.isEmpty(), "Product name is empty.");
                Assert.assertFalse(price.isEmpty(), "Product price is empty.");
                Assert.assertFalse(imageUrl.isEmpty(), "Product image URL is empty.");

                String productInfo = "Product Name: " + name + " | Price: " + price + " | Image URL: " + imageUrl;
                productDetails.add(productInfo);
            }

            // Log all product details
            logger.info("Retrieved product details:");
            productDetails.forEach(logger::info);

        } catch (Exception e) {
            logger.severe("An error occurred while retrieving the section: " + e.getMessage());
        }
    }
}
