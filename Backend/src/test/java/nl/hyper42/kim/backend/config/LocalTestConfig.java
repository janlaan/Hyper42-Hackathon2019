package nl.hyper42.kim.backend.config;

import nl.hyper42.kim.backend.dao.HLFInvoker;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * Test configuration for HLF and encryption service with system property overruling, these are needed when testing spring configurations without environment
 * variables
 *
 * @author Kevin Kerkhoven
 */
@TestConfiguration
public class LocalTestConfig {

    @MockBean
    private HLFInvoker invoker;

}