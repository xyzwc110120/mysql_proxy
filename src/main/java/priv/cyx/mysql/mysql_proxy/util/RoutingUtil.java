package priv.cyx.mysql.mysql_proxy.util;

/**
 * 帮助我们选择连接池的路由工具类
 */
public class RoutingUtil {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    /**
     * 设置当前线程局部变量为 master
     */
    public static void setMaster() {
        threadLocal.set("master");
    }

    /**
     * 设置当前线程局部变量为 slave
     */
    public static void setSlave() {
        threadLocal.set("slave");
    }

    /**
     * 获取路由选项
     */
    public static Object getRoutingOption() {
        return threadLocal.get();
    }
}
