package io.github.shchepetin.roseltorg.tests;

import io.github.shchepetin.roseltorg.base.BaseTest;
import io.github.shchepetin.roseltorg.pages.SupplierForm;
import io.github.shchepetin.roseltorg.pages.YandexSearchPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Росэлторг.Бизнес")
class SupplierTest extends BaseTest {

    @Test
    @DisplayName("Заполнение анкеты поставщика в разделе Частный бизнес")
    void fillSupplierForm() {
        SupplierForm supplierForm = new YandexSearchPage(driver)
                .open()
                .search("Росэлторг")
                .openRoseltorgSite()
                .openPrivateBusiness()
                .clickStartNow()
                .answerWhatYouSell("Песок")
                .clickContinue()
                .selectMaxOrderAmount()
                .clickContinue()
                .selectRegions("г. Москва", "г. Санкт-Петербург")
                .clickContinue()
                .answerWantToIssueSignature()
                .expandAgreements()
                .selectPrivacyPolicy()
                .selectPersonalDataConsent()
                .takeScreenshot();

        assertTrue(supplierForm.isPrivacyPolicySelected(),
                "Не отмечен чекбокс Политики конфиденциальности");
        assertTrue(supplierForm.isPersonalDataConsentSelected(),
                "Не отмечен чекбокс Согласия на обработку персональных данных");
    }
}
