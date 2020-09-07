package priv.cyx.mysql.mysql_proxy.service;

import priv.cyx.mysql.mysql_proxy.domin.Employee;

public interface EmployeeService {

    void saveEmployee(Employee employee);

    Employee getEmployeeById(Long id);
}
