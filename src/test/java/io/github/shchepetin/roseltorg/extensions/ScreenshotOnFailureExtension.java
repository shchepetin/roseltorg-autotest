package io.github.shchepetin.roseltorg.extensions;

import io.github.shchepetin.roseltorg.base.BaseTest;
import io.github.shchepetin.roseltorg.utils.Screenshots;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

public class ScreenshotOnFailureExtension implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) {
        if (context.getExecutionException().isEmpty()) {
            return;
        }
        BaseTest test = (BaseTest) context.getRequiredTestInstance();
        WebDriver driver = test.getDriver();
        if (driver != null) {
            Screenshots.take(driver, "Скриншот при падении теста");
        }
    }
}
