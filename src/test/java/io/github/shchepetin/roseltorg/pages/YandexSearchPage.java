package io.github.shchepetin.roseltorg.pages;

import io.github.shchepetin.roseltorg.config.TestConfig;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class YandexSearchPage extends BasePage {

    private final By searchField = By.id("text");

    public YandexSearchPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть страницу - ya.ru")
    public YandexSearchPage open() {
        driver.get(TestConfig.YANDEX_URL);
        return this;
    }

    @Step("Ввести в поле поиска - {query}")
    public YandexResultsPage search(String query) {
        type(searchField, query);
        driver.findElement(searchField).sendKeys(Keys.ENTER);
        return new YandexResultsPage(driver);
    }
}
