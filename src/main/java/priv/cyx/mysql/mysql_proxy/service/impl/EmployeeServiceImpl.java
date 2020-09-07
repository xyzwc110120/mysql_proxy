package priv.cyx.mysql.mysql_proxy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import priv.cyx.mysql.mysql_proxy.domin.Employee;
import priv.cyx.mysql.mysql_proxy.mapper.EmployeeMapper;
import priv.cyx.mysql.mysql_proxy.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Transactional
    @Override
    public void saveEmployee(Employee employee) {
        employeeMapper.saveEmployee(employee);
    }

    @Transactional(readOnly = true)
    @Override
    public Employee getEmployeeById(Long id) {
        return employeeMapper.getEmployeeById(id);
    }
}
