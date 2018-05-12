package com.onethird.insight.configurer;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class DataSourceConfigurer implements EnvironmentAware{
	
	private static Logger logger = Logger.getLogger(DataSourceConfigurer.class);
	private RelaxedPropertyResolver propertyResolver;
	public void setEnvironment(Environment env) {
		this.propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
	}
	
	/**
	 * 配置数据库连接池
	 * @return
	 */
    @Bean  
    public DruidDataSource dataSource() { 
    	logger.info("init druid Configuration...");
        DruidDataSource datasource = new DruidDataSource();  
        datasource.setUrl(propertyResolver.getProperty("url"));  
        datasource.setDriverClassName(propertyResolver.getProperty("driver-class-name"));  
        datasource.setUsername(propertyResolver.getProperty("username"));  
        datasource.setPassword(propertyResolver.getProperty("password"));  
        datasource.setInitialSize(Integer.valueOf(propertyResolver.getProperty("initialSize")));  
        datasource.setMinIdle(Integer.valueOf(propertyResolver.getProperty("minIdle")));  
        datasource.setMaxWait(Long.valueOf(propertyResolver.getProperty("maxWait")));  
        datasource.setMaxActive(Integer.valueOf(propertyResolver.getProperty("maxActive")));  
        datasource.setMinEvictableIdleTimeMillis(  
                Long.valueOf(propertyResolver.getProperty("minEvictableIdleTimeMillis")));  
        datasource.setTimeBetweenEvictionRunsMillis(
        		Long.valueOf(propertyResolver.getProperty("timeBetweenEvictionRunsMillis")));
        return datasource;  
    }  
    
    /**
     * 配置SqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
    	SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean(); 
    	//设置DataSource
    	sqlSessionFactoryBean.setDataSource(dataSource()); 
    	//设置typeAlias 包扫描路径
    	sqlSessionFactoryBean.setTypeAliasesPackage("com.onethird.insight");
    	//设置mybatis configuration 扫描路径 
//    	sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
    	//扫描sql配置文件
    	PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    	sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/*.xml"));
    	return sqlSessionFactoryBean.getObject(); 
    }
    
    /**
     * 配置扫描Dao接口包，动态实现Dao接口，注入到Spring容器
     * @return
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
    	MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
    	mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
    	mapperScannerConfigurer.setBasePackage("com.onethird.insight");
    	return mapperScannerConfigurer;
    }

}
