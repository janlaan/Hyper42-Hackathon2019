package nl.hyper42.kim.offchain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Basic SpringBootRunner for KIM's Offchain Data application.
 *
 * @author Micha Wensveen
 */
@SuppressWarnings("PMD") // Ignore PMDs' utility class message
@SpringBootApplication(scanBasePackages = { "nl.hyper42.kim.offchain" })
public class DataApplication {

    /**
     * Main function.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(DataApplication.class, args);
    }
}