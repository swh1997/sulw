package cn.dgut.jsf.mapper;

import cn.dgut.jsf.bean.FacilityEquipment;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Customer接口文件
 */

@Repository
public interface FacilityEquipmentDao {
    public List<FacilityEquipment> findFacilityEquipmentUp(); // 查询上线的招聘信息
    public  List<FacilityEquipment> findFacilityEquipmentByTitle(@Param("condition") String condition,@Param("input") String input);//根据名称查询场地设备信息
    public  FacilityEquipment findFacilityEquipmentById(int equ_id);//通过id发现场地设备
	
}
