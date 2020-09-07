package priv.cyx.mysql.mysql_proxy.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import priv.cyx.mysql.mysql_proxy.domin.Employee;

@Repository
public interface EmployeeMapper {

    @Insert("INSERT INTO employee(`name`, sex, birthday, cellphone, department_id, position_id) " +
                    "VALUES (#{name}, #{sex}, #{birthday}, #{cellphone}, #{departmentId}, #{positionId})")
    void saveEmployee(Employee employee);

    @Select("SELECT e.`name`, e.sex, e.birthday, e.cellphone FROM employee e WHERE e.id = #{id}")
    Employee getEmployeeById(Long id);
}
