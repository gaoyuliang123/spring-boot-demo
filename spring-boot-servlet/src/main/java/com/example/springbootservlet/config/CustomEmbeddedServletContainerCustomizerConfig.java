package com.example.springbootservlet.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: TGL
 * @date: 2018/6/24 22:24
 */
@Configuration
public class CustomEmbeddedServletContainerCustomizerConfig {
    @Bean
    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer() {
        EmbeddedServletContainerCustomizer customizer = new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                container.setPort(8081);

            }
        };
        return customizer;
    }
}
