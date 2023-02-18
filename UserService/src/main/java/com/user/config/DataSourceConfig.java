package com.user.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.vendor.Database;

@Configuration
public class DataSourceConfig {

	@Bean
	JpaProperties jpaProperties() {
		JpaProperties jpaProperties = new JpaProperties();
		jpaProperties.setDatabase(Database.MYSQL);
		jpaProperties.setShowSql(true);
		jpaProperties.setGenerateDdl(true);
		return jpaProperties;
	}
	
    @Bean
    @Profile(value = "DEV1")
    DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .username("root")
                .password("root")
                .url("jdbc:mysql://localhost:3305/user_service")
                .build();
    }
    
    @Bean
    @Profile(value = "TEST")
    DataSource dataSourceTest() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .username("root")
                .password("root")
                .url("jdbc:mysql://localhost:3306/user_service")
                .build();
    }
}
