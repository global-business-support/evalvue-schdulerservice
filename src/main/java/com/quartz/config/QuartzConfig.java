package com.quartz.config;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

//    @Value("${spring.datasource.url}")
//    private String datasourceUrl;
//
//    @Value("${spring.datasource.username}")
//    private String datasourceUsername;
//
//    @Value("${spring.datasource.password}")
//    private String datasourcePassword;
//
//    @Value("${spring.datasource.driver-class-name}")
//    private String datasourceDriverClassName;
//
//    @Autowired
//    @Lazy
//    private JobFactory jobFactory;
//
//    @Bean
//    public DataSource quartzDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setUrl(datasourceUrl);
//        dataSource.setUsername(datasourceUsername);
//        dataSource.setPassword(datasourcePassword);
//        dataSource.setDriverClassName(datasourceDriverClassName);
//        return dataSource;
//    }

//    @Bean
//    public SchedulerFactoryBean schedulerFactoryBean(DataSource quartzDataSource) {
//        SchedulerFactoryBean factory = new SchedulerFactoryBean();
//        factory.setJobFactory(jobFactory);
//        factory.setDataSource(quartzDataSource);
//        factory.setQuartzProperties(quartzProperties());
//        factory.setWaitForJobsToCompleteOnShutdown(true);
//        return factory;
//    }
//
//    @Bean
//    public Properties quartzProperties() {
//        Properties properties = new Properties();
//        properties.setProperty("org.quartz.scheduler.instanceName", "MyScheduler");
//        properties.setProperty("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
//        properties.setProperty("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.MSSQLDelegate");
//        properties.setProperty("org.quartz.jobStore.useProperties", "false");
////        properties.setProperty("org.quartz.jobStore.dataSource", "testdb");
////        properties.setProperty("org.quartz.dataSource.testdb.driver", datasourceDriverClassName);
////        properties.setProperty("org.quartz.dataSource.testdb.URL", datasourceUrl);
////        properties.setProperty("org.quartz.dataSource.testdb.user", datasourceUsername);
////        properties.setProperty("org.quartz.dataSource.testdb.password", datasourcePassword);
////        properties.setProperty("org.quartz.dataSource.testdb.maxConnections", "5");
//        return properties;
//    }
}
