package io.github.shchepetin.roseltorg.utils;

import io.github.shchepetin.roseltorg.config.TestConfig;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Screenshots {

    private static final DateTimeFormatter FILE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

    private Screenshots() {
    }

    public static void take(WebDriver driver, String name) {
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment(name, "image/png", new ByteArrayInputStream(screenshot), "png");
        saveToFile(screenshot);
    }

    private static void saveToFile(byte[] screenshot) {
        String fileName = "screenshot_" + LocalDateTime.now().format(FILE_TIME_FORMAT) + ".png";
        Path directory = Path.of(TestConfig.SCREENSHOTS_DIR);
        try {
            Files.createDirectories(directory);
            Files.write(directory.resolve(fileName), screenshot);
        } catch (IOException e) {
            throw new UncheckedIOException("Не удалось сохранить скриншот " + fileName, e);
        }
    }
}
