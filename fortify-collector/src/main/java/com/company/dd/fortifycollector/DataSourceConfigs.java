package com.company.dd.fortifycollector;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfigs {

	
	@Bean(name = "taskDataSource")
	@ConfigurationProperties(prefix="spring1.task.datasource")
	public DataSource getDataSource() {
		return DataSourceBuilder.create().build();
	}	
	
	@Primary
    @Bean(name = "FortifyDataSource")
    @ConfigurationProperties(prefix = "fortify.datasource")
    public DataSource dataSource() {
	  return DataSourceBuilder.create().build();
   }
	  
}
