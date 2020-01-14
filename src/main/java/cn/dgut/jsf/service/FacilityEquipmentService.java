package cn.dgut.jsf.service;

import cn.dgut.jsf.bean.FacilityEquipment;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FacilityEquipmentService {
    public List<FacilityEquipment> findFacilityEquipmentUp(); // 查询上线的招聘信息
    public  List<FacilityEquipment> findFacilityEquipmentByTitle( String condition,  String input);//根据名称查询场地设备信息
    public  FacilityEquipment findFacilityEquipmentById(int equ_id);//通过id发现场地设备
}
