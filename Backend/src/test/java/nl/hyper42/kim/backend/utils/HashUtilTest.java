package nl.hyper42.kim.backend.utils;

import org.junit.Assert;
import org.junit.Test;

public class HashUtilTest {

    @Test
    public void testGetSHA() throws Exception {
        String sha = HashUtil.getSHA("somestring");
        Assert.assertEquals("63f6fe797026d794e0dc3e2bd279aee19dd2f8db67488172a644bb68792a570c", sha);
    }

}
