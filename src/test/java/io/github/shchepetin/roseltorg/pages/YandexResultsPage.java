package io.github.shchepetin.roseltorg.pages;

import io.github.shchepetin.roseltorg.config.TestConfig;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class YandexResultsPage extends BasePage {

    private final By roseltorgResultLink = By.cssSelector("#search-result a[href='" + TestConfig.ROSELTORG_URL + "']");
    private final By defaultSearchPopupCloseButton = By.cssSelector(".DistributionActions .DistributionButtonClose");

    public YandexResultsPage(WebDriver driver) {
        super(driver);
    }

    public RoseltorgMainPage openRoseltorgSite() {
        waitVisible(roseltorgResultLink);
        if (isShownWithin(defaultSearchPopupCloseButton, TestConfig.POPUP_TIMEOUT)) {
            closeDefaultSearchPopup();
        }
        clickRoseltorgResult();
        return new RoseltorgMainPage(driver);
    }

    @Step("Сделать Яндекс основным поиском? Кликнуть - Нет, спасибо")
    private void closeDefaultSearchPopup() {
        click(defaultSearchPopupCloseButton);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(defaultSearchPopupCloseButton));
    }

    @Step("Перейти кликом из результатов поиска на страницу - " + TestConfig.ROSELTORG_URL)
    private void clickRoseltorgResult() {
        clickAndSwitchToNewTab(roseltorgResultLink);
    }
}
