package priv.cyx.mysql.mysql_proxy.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Mybatis 配置类
 */
@Configuration
@MapperScan("priv.cyx.mysql.mysql_proxy.mapper")
@EnableTransactionManagement
public class MyBatisConfig {

    @Autowired
    private DataSource routingDataSource;

    /**
     * 创建 SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(routingDataSource);
        factoryBean.setTypeAliasesPackage("priv.cyx.mysql.mysql_proxy.domain");
        return factoryBean.getObject();
    }

    /**
     * 创建事务管理器
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(routingDataSource);
    }
}
