package priv.cyx.mysql.mysql_proxy.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import priv.cyx.mysql.mysql_proxy.util.RBACRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据库连接池配置文件
 */
@Configuration
public class DataSourceConfig {

    /*
     * 创建对应数据库连接池：
     *      Master 数据库连接池
     *      Slave 数据库连接池
     *      具有路由功能的数据库连接池
     */

    /**
     * 创建 Master 数据库连接池
     */
    @Bean
    public DataSource masterDataSource() {
        DruidDataSource master = new DruidDataSource();
        master.setUrl("jdbc:mysql://119.45.212.129:3306/mysql_demo?serverTimezone=UTC");
        master.setUsername("root");
        master.setPassword("feixia92");
        return master;
    }

    /**
     * 创建 Slave 数据库连接池
     */
    @Bean
    public DataSource slaveDataSource() {
        DruidDataSource slave = new DruidDataSource();
        slave.setUrl("jdbc:mysql://localhost:3306/mysql_demo?serverTimezone=UTC");
        slave.setUsername("root");
        slave.setPassword("admin");
        return slave;
    }

    /**
     * 具有路由功能的连接池，该对象才是真正暴露出去被使用的
     */
    @Bean
    public DataSource routingDataSource(
            DataSource masterDataSource, DataSource slaveDataSource) {
        RBACRoutingDataSource routingDataSource = new RBACRoutingDataSource();

        // 封装要管理的连接池
        Map<Object, Object> map = new HashMap<>();
        map.put("master", masterDataSource);
        map.put("slave", slaveDataSource);
        // 管理那些连接池（需要传入一个 Map 集合）
        routingDataSource.setTargetDataSources(map);
        // 设置默认选择的连接池
        routingDataSource.setDefaultTargetDataSource(masterDataSource);
        return routingDataSource;
    }
}
