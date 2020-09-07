package priv.cyx.mysql.mysql_proxy.domin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 员工对象
 */
@Getter@Setter@ToString
public class Employee {

    private Long id;
    private String name;
    private Integer sex;
    private String birthday;
    private String cellphone;
    private Long departmentId;
    private Long positionId;
}
