package cn.dgut.jsf;

import cn.dgut.jsf.bean.ClassHour;
import cn.dgut.jsf.bean.Coach;
import cn.dgut.jsf.bean.Member;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import static cn.dgut.jsf.utils.MyBatisUtil.getResultMapNew;


@SpringBootApplication()
@MapperScan("cn.dgut.jsf.mapper")
public class JsfApplication {

    public static void main(String[] args) throws Exception {
        //Member a= new Member();
        //Coach b= new Coach();
        //ClassHour a=new ClassHour();
        //System.out.println(getResultMapNew(a.getClass()));
        //System.out.println(getResultMapNew(b.getClass()));
        SpringApplication.run(JsfApplication.class, args);
    }

}
