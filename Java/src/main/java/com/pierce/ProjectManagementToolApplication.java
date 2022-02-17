package com.pierce;

import java.util.Arrays;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
@EnableCaching
public class ProjectManagementToolApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagementToolApplication.class, args);
	}
	
	@Bean
    public OpenAPI openApiConfig() {
        return new OpenAPI().info(apiInfo());
    }

    public Info apiInfo() {
        Info info = new Info();
        info
                .title("Project Management Tool")
                .description("Allows user to easily manage projects and their respective project tasks")
                .version("v1.0.0");
        return info;
    }

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
          new ConcurrentMapCache("backlogs"), 
          new ConcurrentMapCache("tasks"),
          new ConcurrentMapCache("projects")));
        return cacheManager;
    }
}
