package nl.hyper42.kim.backend.dao;

import org.junit.Assert;
import org.junit.Test;

public class HLFRuntimeExceptionTest {

    @Test
    public void testHLFRuntimeException() {
        try {
            throw new HLFRuntimeException();
        } catch (HLFRuntimeException are) {
            Assert.assertNull(are.getMessage());
            Assert.assertNull(are.getCause());
        }
    }

    @Test
    public void testHLFRuntimeExceptionMessage() {
        try {
            throw new HLFRuntimeException("message");
        } catch (HLFRuntimeException are) {
            Assert.assertEquals("message", are.getMessage());
            Assert.assertNull(are.getCause());
        }
    }

    @Test
    public void testHLFRuntimeExceptionMessageSource() {
        try {
            throw new HLFRuntimeException("message", new RuntimeException());
        } catch (HLFRuntimeException are) {
            Assert.assertEquals("java.lang.RuntimeException", are.getCause().getClass().getName());
            Assert.assertEquals("message", are.getMessage());
        }
    }

}
