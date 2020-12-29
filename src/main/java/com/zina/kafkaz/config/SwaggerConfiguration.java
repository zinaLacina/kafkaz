package com.zina.kafkaz.config;

import com.google.common.base.Predicate;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.*;
import org.springframework.http.*;
import springfox.documentation.*;
import springfox.documentation.builders.*;
import springfox.documentation.spi.*;
import springfox.documentation.spring.web.plugins.*;
import springfox.documentation.swagger2.annotations.*;

/**
 * @author : lacinazina
 * @created : 12/28/20, Monday
 **/
@Configuration
@EnableSwagger2
@ConditionalOnProperty(value = "swagger.enabled", matchIfMissing = true)
public class SwaggerConfiguration {
    @Bean
    public Docket swagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(new ApiInfoBuilder()
                        .title("KafKaZ API")
                        .description("JSON APIs for KafkaZ")
                        .build())
                .select()
                .apis(new JsonRequestHandlerPredicate())
                .paths(new IgnoreDebugPathPredicate())
                .build();
    }

    /**
     *  Swagger Predicate for only selecting JSON endpoints.
     */
    public final class JsonRequestHandlerPredicate implements Predicate<RequestHandler> {
        @Override
        public boolean apply(RequestHandler input) {
            return input.produces().contains(MediaType.APPLICATION_JSON);
        }
    }

    /**
     *  Swagger Predicate for ignoring {@code /actuator} endpoints.
     */
    public final class IgnoreDebugPathPredicate implements Predicate<String> {
        @Override
        public boolean apply(String input) {
            return !input.startsWith("/actuator");
        }
    }
}
