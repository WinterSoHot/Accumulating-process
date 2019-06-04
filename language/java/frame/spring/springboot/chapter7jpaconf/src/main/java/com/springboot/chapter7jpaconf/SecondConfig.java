package com.springboot.chapter7jpaconf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryBeanSecond",
        transactionManagerRef = "transactionManagerSecond",
        basePackages = {"com.springboot.chapter7jpaconf.domain.b"} //设置Repository所在位置
)
public class SecondConfig {

    @Autowired
    @Qualifier("secondDataSource")
    private DataSource SecondDataSource;

    @Bean(name = "entityManagerFactoryBeanSecond")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBeanSecond(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(SecondDataSource)
                .properties(getVendorProperties(SecondDataSource))
                .packages("com.springboot.chapter7jpaconf.domain.b") //设置实体所在的位置
                .persistenceUnit("SecondPersistenceUnit")
                .build();
    }

    @Autowired
    private JpaProperties jpaProperties;

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }


    @Bean(name = "entityManagerSecond")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryBeanSecond(builder).getObject().createEntityManager();
    }

    @Bean(name = "transactionManagerSecond")
    public PlatformTransactionManager transactionManagerSecond(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryBeanSecond(builder).getObject());
    }


}
