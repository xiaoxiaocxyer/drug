package cn.tedu.drug.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.drug.entity.Employees;
import org.springframework.util.DigestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeesTestCase {

    @Autowired
    private IEmployeesService ee;

    @Test
    public void changePasswod() {
        try {
            String oldPassword = "321";
            String newPassword = "123";
            Integer uid = 13;
            ee.changePassword(oldPassword, newPassword, uid);
            System.err.println("OK");
        } catch (Exception e) {
            System.err.println(e.getClass().getName());
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void login() {
        try {
            //从数据库复制salt
            String salt = "495b7bc8-2688-4bbb-8905-8fa50ef62427";
            //不用改123456，默认生成的都是123456
            String password = "123456";
            String result = salt + password + salt;
            for (int i = 0; i < 5; i++) {
                result = DigestUtils.md5DigestAsHex(result.getBytes()).toUpperCase();
            }
            //把生成的结果粘到数据库的password栏
            System.out.println(result);
        } catch (Exception e) {
            System.err.println(e.getClass().getName());
            System.err.println(e.getMessage());
        }
    }
}
