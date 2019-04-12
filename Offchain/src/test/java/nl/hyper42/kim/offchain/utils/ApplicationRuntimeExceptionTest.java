package nl.hyper42.kim.offchain.utils;

import org.junit.Assert;
import org.junit.Test;

public class ApplicationRuntimeExceptionTest {

    @Test
    public void testApplicationRuntimeException() {
        try {
            throw new ApplicationRuntimeException();
        } catch (ApplicationRuntimeException are) {
            Assert.assertNull(are.getMessage());
            Assert.assertNull(are.getCause());
        }
    }

    @Test
    public void testApplicationRuntimeExceptionMessage() {
        try {
            throw new ApplicationRuntimeException("message");
        } catch (ApplicationRuntimeException are) {
            Assert.assertEquals("message", are.getMessage());
            Assert.assertNull(are.getCause());
        }
    }

    @Test
    public void testApplicationRuntimeExceptionSource() {
        try {
            throw new ApplicationRuntimeException(new RuntimeException());
        } catch (ApplicationRuntimeException are) {
            Assert.assertEquals("java.lang.RuntimeException", are.getCause().getClass().getName());
        }
    }

    @Test
    public void testApplicationRuntimeExceptionMessageSource() {
        try {
            throw new ApplicationRuntimeException("message", new RuntimeException());
        } catch (ApplicationRuntimeException are) {
            Assert.assertEquals("java.lang.RuntimeException", are.getCause().getClass().getName());
            Assert.assertEquals("message", are.getMessage());
        }
    }
}
