# 消息平台基础框架

#MyBatis3.3.0

#Spring Boot 1.3.0.RELEASE

项目使用Spring Boot 1.3.0.RELEASE + Mybatis3.3.0


项目集成了Mybatis分页插件和通用Mapper插件

项目使用的mysql数据库，根据需要可以切换为其他数据库

##说明

在集成MyBatis配置`MapperScannerConfigurer`需要特别注意，将该类单独放在一个配置文件中，例如本项目中的`MyBatisMapperScannerConfig`类：

```java
@Configuration
//注意，由于MapperScannerConfigurer执行的比较早，所以必须有下面的注解
//MyBatisConfig.class是一个包含了SqlSessionFactory配置的类
@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisMapperScannerConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("tk.mybatis.springboot.mapper");
        Properties properties = new Properties();
        properties.setProperty("mappers", MMyMapperperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");
        //这里使用的通用Mapper的MapperScannerConfigurer，所有有下面这个方法
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }

}
```


## Spring Boot Reference Guide
### http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/

## 深入学习微框架：Spring Boot 翻译
### http://www.infoq.com/cn/articles/microframeworks1-spring-boot


##MyBatis工具

###http://www.mybatis.tk

##推荐使用Mybatis通用Mapper3

###https://github.com/abel533/Mapper

##推荐使用Mybatis分页插件PageHelper

###https://github.com/pagehelper/Mybatis-PageHelper