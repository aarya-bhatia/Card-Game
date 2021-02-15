package com.aarya.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.logging.Level;
import java.util.logging.Logger;

public class InvokeTest {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(InvokeTest.class.getName());
        Result result = JUnitCore.runClasses(TestSuit.class);

        for(Failure failure: result.getFailures()) {
            logger.log(Level.WARNING, failure.toString());
        }

        logger.log(Level.INFO, "Result Run Count: " + result.getRunCount());
        logger.log(Level.INFO, "Result Failure Count: " + result.getFailureCount());
        logger.log(Level.INFO, "Was Successful: " + result.wasSuccessful());
    }
}
