package nl.hyper42.kim.offchain.config;

import com.google.common.base.Predicates;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuration for Swagger rest.
 *
 * @author Micha Wensveen,
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Holds the contact information
     */
    private static final Contact DEFAULT_CONTACT = new Contact("Team Hyper 42", "https://www.hyper42.nl", "hyper42@example.com");

    /**
     * Holds the api information
     */
    @SuppressWarnings("unused") // PMD sees this as unused method, so we suppress
    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Offchain API", "Offchain Api voor KIM", "1.0", "urn:tos", DEFAULT_CONTACT, "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0", Collections.emptySet());

    /**
     * Hold the media type information
     */
    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(Collections.singletonList("application/json"));

    /**
     * SpringBean for the Docket.
     *
     * @return the Docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().paths(Predicates.not(PathSelectors.regex("/error.*|/|/actuator.*"))).build()
                .produces(DEFAULT_PRODUCES_AND_CONSUMES).consumes(DEFAULT_PRODUCES_AND_CONSUMES).genericModelSubstitutes(Optional.class)
                .useDefaultResponseMessages(false);
    }
}