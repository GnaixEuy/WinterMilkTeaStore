package cn.chengyi.the_back_end.dao;

import cn.chengyi.the_back_end.entity.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdminDaoTest {
    @Autowired
    private AdminDao admindao;

    @Test
    void findAdminById(){
        Admin admin = admindao.findAdminById(1);
        System.out.println("admin.getAdminName() = " + admin.getAdminName());
        System.out.println("admin.getAdminPassword() = " + admin.getAdminPassword());
    }
}
