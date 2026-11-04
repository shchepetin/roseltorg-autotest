package io.github.shchepetin.roseltorg.config;

import java.time.Duration;

public final class TestConfig {

    public static final String YANDEX_URL = "https://ya.ru";
    public static final String ROSELTORG_URL = "https://www.roseltorg.ru/";
    public static final Duration TIMEOUT = Duration.ofSeconds(15);
    public static final Duration POPUP_TIMEOUT = Duration.ofSeconds(5);
    public static final String SCREENSHOTS_DIR = "target/screenshots";

    private TestConfig() {
    }
}
