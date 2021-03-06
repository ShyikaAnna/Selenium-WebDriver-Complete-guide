package OnlineShop;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyStorepage extends Base {
    public MyStorepage(PageInitialization pageInitialization) {
        super(pageInitialization);
    }

    public MyStorepage(WebDriver driver)
    {
        super(driver);
    }

    private String pageFlag = "//*[@id='rslides1_s0']/a/img";
    private String stiker = "//div[contains(@class,'sticker')]";
    private String compaignProducts = "//*[@id='content']/ul/li[1]/a";
    private String productInCampaign ="//div[@id='box-campaigns']//div[@class='product column shadow hover-light']";
    private String ProductInCampaignLink=  "//div[@id='box-campaigns']/div/div";
    private String popularProducts = "//h3[contains(.,'Most Popular')]";
    private String productInPopular = ".//*[@id='box-most-popular']/div/div";
    private String productInCampaignLink = "//div[@id='box-most-popular']/div/div";
    private String latestProducts = ".//*[@id='box-latest-products']/div/ul/li/a";
    private String mainPage = "//img[@src='/litecart/images/slides/1-flying-cart.jpg']";
    private String nameOfProduct = ".//*[@id='box-campaigns']/div/ul/li";
    private String viewOfProduct = ".//*[@id='box-product']/div[2]/div[1]/a";
    private String regularPrice = "//div[@id='box-campaigns']//s[@class='regular-price']";
    private String campaignPrice = "//div[@id='box-campaigns']//strong[@class='campaign-price']";
    private String nameOfProductLatest = ".//*[@id='box-latest-products']/div/ul/li/a[1]";
    private String nameOfProductPopular = "//*[@id='box-most-popular']/div/ul/li[3]/a[1]";

    public boolean isMainPage()
    {
        By lPageFlag = By.xpath(pageFlag);
        wait.until(ExpectedConditions.visibilityOfElementLocated(lPageFlag));
        return driver.findElement(lPageFlag).isDisplayed();
    }
    public boolean MainPage() {
        By mPage = By.xpath(mainPage);
        wait.until(ExpectedConditions.visibilityOfElementLocated(mPage));
        return driver.findElement(mPage).isDisplayed();
    }
    public void goToCampaignProducts()
    {
        By CampProducts = By.xpath(compaignProducts);
        driver.findElement(CampProducts).click();
       // wait.until(ExpectedConditions.attributeContains(CampProducts, "class", "active"));
    }
    public int QuantityProductsInCampaign()
    {
        By lProductsInCampaign = By.xpath(productInCampaign);
        return driver.findElements(lProductsInCampaign).size();
    }
    public boolean checkStikerInCampaign(int k)
    {
        By ProductInCamp = By.xpath(String.format(ProductInCampaignLink, "" + k));
        WebElement product = driver.findElement(ProductInCamp);
        By Stikers = By.xpath(stiker);
        return (product.findElements(Stikers).size() == 1);
    }
    public String getProductText()
    {
        goToCampaignProducts();
        By lNameOfProduct = By.xpath(nameOfProduct);
        String sNameOfProduct = driver.findElement(lNameOfProduct).getText();
        System.out.println(sNameOfProduct);
        return sNameOfProduct;
    }
    public void goToProductBlock()
    {
        By lNameOfProduct = By.xpath(nameOfProduct);
        driver.findElement(lNameOfProduct).click();
        By lViewOfProduct = By.xpath(viewOfProduct);
        String driverName = driver.getClass().getSimpleName();
        System.out.println(driverName);
        if (driverName.equals("FirefoxDriver"))
        {
            try
            {
                wait.until(ExpectedConditions.elementToBeClickable(lViewOfProduct));
            }
            catch (Exception e)
            {
            }
        }
        if (driver.getCurrentUrl().equals("http://localhost/litecart/en/"))
        {
            driver.findElement(lViewOfProduct).click();
            wait.until(ExpectedConditions.urlContains("duck"));
        }
    }
    public String getRegularPrice()
    {
        goToCampaignProducts();
        By lRegularPrice = By.xpath(regularPrice);
        String sRegularPrice = driver.findElement(lRegularPrice).getText();
        System.out.println(sRegularPrice);
        return sRegularPrice;
    }
    public String getCampaignPrice()
    {
        By CompaignPrice1 = By.xpath(campaignPrice);
        String CompaignPrice = driver.findElement(CompaignPrice1).getText();
        System.out.println(CompaignPrice);
        return CompaignPrice;
    }
    public boolean checkRegularPriceColor()
    {
        goToCampaignProducts();
        By lRegularPrice = By.xpath(regularPrice);
        String sRegularPrice = driver.findElement(lRegularPrice).getCssValue("color");
        sRegularPrice = sRegularPrice.substring((sRegularPrice.indexOf("(") + 1), sRegularPrice.lastIndexOf(")"));
        System.out.println(sRegularPrice);
        String[] strColor = sRegularPrice.split(",");
        if (strColor[0].equals(strColor[1]) || strColor[1].equals(strColor[2]))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean checkCampaignPriceColor()
    {
        goToCampaignProducts();
        By lCampaignPrice = By.xpath(campaignPrice);
        String sCampaignPrice = driver.findElement(lCampaignPrice).getCssValue("color");
        sCampaignPrice = sCampaignPrice.substring((sCampaignPrice.indexOf("(") + 1), sCampaignPrice.lastIndexOf(")"));
        System.out.println(sCampaignPrice);
        String[] strColor = sCampaignPrice.split(",");
        if (Integer.parseInt(strColor[0]) != 0 || strColor[1].equals("0") || strColor[2].equals("0"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean checkBoldCampaignPrice()
    {
        goToCampaignProducts();
        By lCampaignPrice = By.xpath(campaignPrice);
        String sCampaignPrice = driver.findElement(lCampaignPrice).getTagName();
        System.out.println(sCampaignPrice);
        if (sCampaignPrice.equals("b") || sCampaignPrice.equals("strong"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean compareCampaignAndRegularPrices()
    {
        goToCampaignProducts();
        By lCampaignPrice = By.xpath(campaignPrice);
        Dimension sizeCampaignPrice = driver.findElement(lCampaignPrice).getSize();
        By lRegularPrice = By.xpath(regularPrice);
        Dimension sizeRegularPrice = driver.findElement(lRegularPrice).getSize();
        System.out.println("sizeCampaignPrice - " + sizeCampaignPrice.getHeight() * sizeCampaignPrice.getWidth() + ". "
                + "sizeRegularPrice - " + sizeRegularPrice.getWidth() * sizeRegularPrice.getHeight());
        if (sizeCampaignPrice.getHeight() * sizeCampaignPrice.getWidth() > sizeRegularPrice.getWidth() * sizeRegularPrice
                .getHeight())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public void clickPopularProducts()
    {
        By lPopularProducts = By.xpath(popularProducts);
        driver.findElement(lPopularProducts).click();
//        wait.until(ExpectedConditions.attributeContains(lPopularProducts, "class", "active"));
    }
    public int getProductsInPopular()
    {
        By lProductsInPopular = By.xpath(productInPopular);
        return driver.findElements(lProductsInPopular).size();
    }
    public void goToProductBlockPopular()
    {
        By lNameOfProductPopular = By.xpath(nameOfProductPopular);
        driver.findElement(lNameOfProductPopular).click();
        By lViewOfProduct = By.xpath(viewOfProduct);
        String driverName = driver.getClass().getSimpleName();
        System.out.println(driverName);
        if (driverName.equals("FirefoxDriver"))
        {
            try
            {
                wait.until(ExpectedConditions.elementToBeClickable(lViewOfProduct));
            }
            catch (Exception e)
            {
            }
        }
        if (driver.getCurrentUrl().equals("http://localhost/litecart/en/"))
        {
            driver.findElement(lViewOfProduct).click();
            wait.until(ExpectedConditions.urlContains("duck"));
        }
    }
    public void clickLatestProducts()
    {
        By lLatestProducts = By.xpath(latestProducts);
        driver.findElement(lLatestProducts).click();
    }
    public void goToProductBlockLatest()
    {
        By lNameOfProductLatest = By.xpath(nameOfProductLatest);
        driver.findElement(lNameOfProductLatest).click();
        By lViewOfProduct = By.xpath(viewOfProduct);
        String driverName = driver.getClass().getSimpleName();
        System.out.println(driverName);
        if (driverName.equals("FirefoxDriver"))
        {
            try
            {
                wait.until(ExpectedConditions.elementToBeClickable(lViewOfProduct));
            }
            catch (Exception e)
            {
            }
        }
        if (driver.getCurrentUrl().equals("http://localhost/litecart/en/"))
        {
            driver.findElement(lViewOfProduct).click();
            wait.until(ExpectedConditions.urlContains("duck"));
        }
    }
}
