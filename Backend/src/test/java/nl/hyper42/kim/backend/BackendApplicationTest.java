package nl.hyper42.kim.backend;

import nl.hyper42.kim.backend.config.LocalTestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { LocalTestConfig.class })
public class BackendApplicationTest {
    @Test
    public void contextLoads() {
        // Will fail if beans are not created correctly.
    }

}
