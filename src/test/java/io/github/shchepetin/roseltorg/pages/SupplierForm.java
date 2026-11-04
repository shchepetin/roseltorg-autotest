package io.github.shchepetin.roseltorg.pages;

import io.github.shchepetin.roseltorg.utils.Screenshots;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SupplierForm extends BasePage {

    private final By questionTitle = By.cssSelector("#quiz-steps .form-quiz__step-questions");
    private final By continueButton = By.xpath("//div[@id = 'quiz-steps']//button[normalize-space() = 'Продолжить']");
    private final By offerField = By.id("q-offer");
    private final By amountSliderHandle = By.cssSelector("#quiz-steps .noUi-handle");
    private final By amountSliderTrack = By.cssSelector("#quiz-steps .noUi-base");
    private final By selectedAmount = By.cssSelector("#quiz-steps .range-slider__legend .active");
    private final By regionsField = By.id("q-region-ts-control");
    private final By regionsDropdown = By.id("q-region-ts-dropdown");
    private final By wantToIssueSignatureButton =
            By.xpath("//div[@id = 'quiz-steps']//button[normalize-space() = 'Хочу выпустить']");
    private final By agreementsToggle = By.id("agreement-title");
    private final By privacyPolicyCheckbox = By.cssSelector("input[name='privacy'] + div.checkbox");
    private final By personalDataCheckbox = By.cssSelector("input[name='personal_data'] + div.checkbox");
    private final By privacyPolicyInput = By.name("privacy");
    private final By personalDataInput = By.name("personal_data");

    public SupplierForm(WebDriver driver) {
        super(driver);
    }

    @Step("Что вы продаёте? Написать - {text}")
    public SupplierForm answerWhatYouSell(String text) {
        waitForQuestion("Что вы продаёте?");
        type(offerField, text);
        return this;
    }

    @Step("Кликнуть - Продолжить")
    public SupplierForm clickContinue() {
        click(continueButton);
        return this;
    }

    @Step("На какую сумму вы готовы выполнить заказ? Выбрать - свыше 10 млн")
    public SupplierForm selectMaxOrderAmount() {
        waitForQuestion("На какую сумму вы готовы выполнить заказ?");
        WebElement handle = waitVisible(amountSliderHandle);
        int trackWidth = driver.findElement(amountSliderTrack).getSize().getWidth();
        new Actions(driver).dragAndDropBy(handle, trackWidth, 0).perform();
        wait.until(ExpectedConditions.textToBe(selectedAmount, "свыше 10 млн"));
        return this;
    }

    @Step("В каких регионах вы готовы исполнять заказы? Выбрать - {firstRegion}, {secondRegion}")
    public SupplierForm selectRegions(String firstRegion, String secondRegion) {
        waitForQuestion("В каких регионах вы готовы исполнять заказы?");
        click(regionsField);
        selectRegion(firstRegion);
        selectRegion(secondRegion);
        driver.findElement(regionsField).sendKeys(Keys.ESCAPE);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(regionsDropdown));
        return this;
    }

    private void selectRegion(String region) {
        click(By.cssSelector("#q-region-ts-dropdown [data-value='" + region + "']"));
    }

    @Step("Есть ли у вас действующая электронная подпись? Кликнуть - Хочу выпустить")
    public SupplierForm answerWantToIssueSignature() {
        waitForQuestion("Есть ли у вас действующая электронная подпись?");
        click(wantToIssueSignatureButton);
        return this;
    }

    @Step("Контактные данные. Развернуть - Ознакомлен и согласен с документами")
    public SupplierForm expandAgreements() {
        waitForQuestion("Контактные данные");
        click(agreementsToggle);
        waitVisible(personalDataCheckbox);
        return this;
    }

    @Step("Выбрать - Принимаю Политику конфиденциальности")
    public SupplierForm selectPrivacyPolicy() {
        click(privacyPolicyCheckbox);
        return this;
    }

    @Step("Выбрать - Даю своё Согласие на обработку персональных данных")
    public SupplierForm selectPersonalDataConsent() {
        click(personalDataCheckbox);
        return this;
    }

    @Step("Сделать скриншот")
    public SupplierForm takeScreenshot() {
        Screenshots.take(driver, "Скриншот анкеты");
        return this;
    }

    public boolean isPrivacyPolicySelected() {
        return driver.findElement(privacyPolicyInput).isSelected();
    }

    public boolean isPersonalDataConsentSelected() {
        return driver.findElement(personalDataInput).isSelected();
    }

    private void waitForQuestion(String question) {
        wait.until(ExpectedConditions.textToBe(questionTitle, question));
    }
}
