package com.capco.capcopay;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2UiConfiguration {
    
    @Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
				.apiInfo(apiInfo()).select().paths(postPaths()).build();
	}

	private Predicate<String> postPaths() {
		return or(regex("/capcopay/posts.*"), regex("/capcopay.*"));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Capco Pay API")
				.description("Capco Pay API reference for developers")
				//.termsOfServiceUrl("http://localhost:8080/capcopay")
				//.contact("team@capco.com").license("Capco Pay")
				.licenseUrl("team@capco.com").version("1.0").build();
	}
}
