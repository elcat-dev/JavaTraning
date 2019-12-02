package com.ertc.taskman.configs;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("com.ertc.taskman")
public class AppConfigs implements WebMvcConfigurer {
}
