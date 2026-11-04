package io.github.shchepetin.roseltorg.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PrivateBusinessPage extends BasePage {

    private final By startNowButton =
            By.xpath("//section[contains(@class, 'intro--main')]//button[normalize-space() = 'Начать сейчас']");

    public PrivateBusinessPage(WebDriver driver) {
        super(driver);
    }

    @Step("Кликнуть по кнопке - Начать сейчас")
    public SupplierForm clickStartNow() {
        click(startNowButton);
        return new SupplierForm(driver);
    }
}
