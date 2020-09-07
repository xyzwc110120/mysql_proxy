package priv.cyx.mysql.mysql_proxy.util;

import com.alibaba.druid.support.spring.stat.SpringStatUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 面向切面，对路由选择进行增强（动态选择）
 */
/*
 * @Order
 *      定义 Spring IOC 容器中 Bean 的执行顺序的优先级(默认值最低优先级，int 类型的最大值：2147483647)
 */
@Aspect
@Order(0)       // 因为事务注解 @Transactional 的优先级要高于切面代理的优先级，所以我们要设置优先级，让增强方法先执行
@Component      // 当前切面类交给 Spring 管理
public class RoutingAopAspect {

    /*
     * @Pointcut(Pointcut 语法)：
     *      切入点，需要为哪些包中的哪些类中的哪些方法做增强
     */
    @Pointcut("execution(* priv.cyx.mysql.mysql_proxy.service.impl.*ServiceImpl.*(..))")
    public void pointcut() {}

    /**
     * 路由选择方法
     * 因为在业务实行之前选择连接池，所以是前置增强
     *
     * @param point 连接点，被拦截到的需要被增强的方法
     */
    @Before("pointcut()")
    public void setRouting(JoinPoint point) {
        // 根据当前执行方法，来设置使用的连接池 key
        // 获取当前方法的方法名
        String methodName = point.getSignature().getName();
        if (isSlave(methodName)) {
            RoutingUtil.setSlave();
            System.err.println("设置路由 slave");
        } else {
            RoutingUtil.setMaster();
            System.err.println("设置路由 master");
        }
    }

    /**
     * 判断是否使用 slave 连接池，若是方法以 get 开头，则使用 slave 连接池
     */
    private static boolean isSlave(String methodName) {
        return methodName.startsWith("get");
    }
}
