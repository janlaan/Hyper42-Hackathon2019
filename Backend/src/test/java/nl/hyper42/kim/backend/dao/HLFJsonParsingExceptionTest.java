package nl.hyper42.kim.backend.dao;

import org.junit.Assert;
import org.junit.Test;

public class HLFJsonParsingExceptionTest {

    @Test
    public void testHLFParsingException() {
        try {
            throw new HLFJsonParsingException();
        } catch (HLFJsonParsingException hjpe) {
            Assert.assertNull(hjpe.getMessage());
            Assert.assertNull(hjpe.getCause());
        }
    }

    @Test
    public void testHLFParsingExceptionMessage() {
        try {
            throw new HLFJsonParsingException("message");
        } catch (HLFJsonParsingException hjpe) {
            Assert.assertEquals("message", hjpe.getMessage());
            Assert.assertNull(hjpe.getCause());
        }
    }

    @Test
    public void testHLFParsingExceptionMessageSource() {
        try {
            throw new HLFJsonParsingException("message", new RuntimeException());
        } catch (HLFJsonParsingException hjpe) {
            Assert.assertEquals("java.lang.RuntimeException", hjpe.getCause().getClass().getName());
            Assert.assertEquals("message", hjpe.getMessage());
        }
    }

}
