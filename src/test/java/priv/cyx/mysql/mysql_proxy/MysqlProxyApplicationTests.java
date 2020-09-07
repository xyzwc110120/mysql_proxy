package priv.cyx.mysql.mysql_proxy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import priv.cyx.mysql.mysql_proxy.domin.Employee;
import priv.cyx.mysql.mysql_proxy.service.EmployeeService;

@SpringBootTest
class MysqlProxyApplicationTests {

    @Autowired
    private EmployeeService employeeService;

    @Test
    void testSaveEmployee() {
        Employee employee = new Employee();
        employee.setName("赵灵儿");
        employee.setSex(0);
        employee.setBirthday("2020-02-02");
        employee.setCellphone("12020200202");
        employee.setDepartmentId(4L);
        employee.setPositionId(5L);
        employeeService.saveEmployee(employee);
    }

    @Test
    void testGetEmployeeById() {
        System.out.println(employeeService.getEmployeeById(3L));
    }
}
