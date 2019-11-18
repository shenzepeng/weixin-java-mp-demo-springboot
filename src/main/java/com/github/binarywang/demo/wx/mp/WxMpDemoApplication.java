package com.github.binarywang.demo.wx.mp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@EnableSwagger2
@EnableScheduling
@SpringBootApplication
public class WxMpDemoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WxMpDemoApplication.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WxMpDemoApplication.class);
    }
}
