package Utils;

import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public abstract class BaseSuite {

    @BeforeSuite
    public void beforeSuite(ITestContext iTestContext) {
        String suiteName = iTestContext.getSuite().getName();
        System.out.println("Before Suite has started for suite with name: " + suiteName);
    }

    @AfterSuite
    public void afterSuite(ITestContext iTestContext) {
        String suiteName = iTestContext.getSuite().getName();
        System.out.println("After Suite has started for suite with name: " + suiteName);
    }
}
