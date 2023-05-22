package jp.co.scsk.miniapp.sample.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * swagger配置
 *
 * @author : LiShuai
 * @since : 2022/2/8 17:05
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI springShopOpenAPI () {
        return new OpenAPI().info(
                new Info()
                        .title("mini-app-sample-api")
                        .description("mini-app-sample-api")
                        .version("v0.1")
        );
    }

}
