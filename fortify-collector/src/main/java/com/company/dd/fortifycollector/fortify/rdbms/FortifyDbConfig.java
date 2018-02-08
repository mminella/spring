package com.company.dd.fortifycollector.fortify.rdbms;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  entityManagerFactoryRef = "fortifyEntityManagerFactory",
  basePackages = { "com.verizon.dd.fortifycollector.fortify" },
  transactionManagerRef = "fortifyTransactionManager"
  
)
public class FortifyDbConfig {

	  @Bean(name = "fortifyEntityManagerFactory")
	  public LocalContainerEntityManagerFactoryBean 
	  fortifyEntityManagerFactory(
	    EntityManagerFactoryBuilder builder,
	    @Qualifier("FortifyDataSource") DataSource dataSource
	  ) {
	    return builder
	      .dataSource(dataSource)
	      .packages("com.verizon.dd.fortifycollector.fortify")
	      .persistenceUnit("fortify")
	      .build();
	  }
	    
	  
	  @Bean(name = "fortifyTransactionManager")
	  public PlatformTransactionManager transactionManager(
	    @Qualifier("fortifyEntityManagerFactory") EntityManagerFactory 
	    entityManagerFactory
	  ) {
	    return new JpaTransactionManager(entityManagerFactory);
	  }
}
