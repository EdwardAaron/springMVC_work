package com.example.conf;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;

/**
 * created by dfk
 * 4/4/2022
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.example.dao", "com.example.service"})
public class ApplicationConfig {
    //配置数据源
    @Bean
    DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource da = new ComboPooledDataSource();
        da.setJdbcUrl("jdbc:mysql://localhost:3306/ssh");
        da.setDriverClass("com.mysql.cj.jdbc.Driver");
        da.setUser("root");
        da.setPassword("root");
        return da;
    }

    //myBatis sqlSession
    @Bean
    SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws PropertyVetoException, IOException {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        factory.setDataSource(dataSource);
        factory.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return factory;
    }

    //SqlSessionTemplate 支持批量执行sql
    @Bean
    SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate sqlSessionTemplate =
                new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH);
        return sqlSessionTemplate;
    }

    @Bean
    TransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    //扫描mapper
    @Bean
    MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.example.dao");
        return mapperScannerConfigurer;
    }

}
