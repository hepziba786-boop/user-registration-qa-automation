package com.userapp.automation.utils;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class TestResultWatcher implements TestWatcher {

    private static final Logger logger = LoggerFactory.getLogger(TestResultWatcher.class);

    @Override
    public void testSuccessful(ExtensionContext context) {
        logger.info("✓ Test PASSED: {}", context.getDisplayName());
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        logger.warn("⊘ Test ABORTED: {} - {}", context.getDisplayName(), cause.getMessage());
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        logger.info("⊘ Test DISABLED: {} - {}", context.getDisplayName(), reason.orElse("No reason"));
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        logger.error("✗ Test FAILED: {} - {}", context.getDisplayName(), cause.getMessage());
    }

}
