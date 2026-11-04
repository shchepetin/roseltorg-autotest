package io.github.shchepetin.roseltorg.pages;

import io.github.shchepetin.roseltorg.config.TestConfig;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RoseltorgMainPage extends BasePage {

    private final By privateBusinessMenuItem =
            By.xpath("//a[contains(@class, 'menu__category') and normalize-space() = 'Частный бизнес']");
    private final By tourNextButton = By.cssSelector("button[data-action='primary'][aria-label='Дальше']");
    private final By tourFinishButton = By.cssSelector("button[data-action='primary'][aria-label='Хорошо']");
    private final By tourOverlay = By.cssSelector(".react-joyride__overlay");

    public RoseltorgMainPage(WebDriver driver) {
        super(driver);
    }

    public PrivateBusinessPage openPrivateBusiness() {
        waitVisible(privateBusinessMenuItem);
        if (isShownWithin(tourNextButton, TestConfig.POPUP_TIMEOUT)) {
            closeTour();
        }
        clickPrivateBusinessMenuItem();
        return new PrivateBusinessPage(driver);
    }

    @Step("Вход в торговые секции и сервисы. Кликнуть - Дальше, Хорошо")
    private void closeTour() {
        click(tourNextButton);
        click(tourFinishButton);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(tourOverlay));
    }

    @Step("Перейти в верхнем меню - Частный бизнес")
    private void clickPrivateBusinessMenuItem() {
        clickAndSwitchToNewTab(privateBusinessMenuItem);
    }
}
