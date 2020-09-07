package priv.cyx.mysql.mysql_proxy.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 当前项目中具有路由功能的连接池
 */
public class RBACRoutingDataSource extends AbstractRoutingDataSource {

    /**
     * 如何选择连接池
     * @return 对应连接池的 key
     */
    @Override
    protected Object determineCurrentLookupKey() {
        Object key = RoutingUtil.getRoutingOption();
        // 使用 err 打印，红色显眼
        System.err.println("路由选择：" + key);
        return key;
    }
}
