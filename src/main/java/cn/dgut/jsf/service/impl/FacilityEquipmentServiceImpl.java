package cn.dgut.jsf.service.impl;


import cn.dgut.jsf.bean.FacilityEquipment;
import cn.dgut.jsf.mapper.AdminDao;
import cn.dgut.jsf.mapper.FacilityEquipmentDao;
import cn.dgut.jsf.service.AdminService;
import cn.dgut.jsf.service.FacilityEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class FacilityEquipmentServiceImpl implements FacilityEquipmentService {
	@Autowired
	private FacilityEquipmentDao facilityEquipment;

	@Override
	public List<FacilityEquipment> findFacilityEquipmentUp() {
		return facilityEquipment.findFacilityEquipmentUp();
	}

	@Override
	public List<FacilityEquipment> findFacilityEquipmentByTitle(String condition, String input) {
		return facilityEquipment.findFacilityEquipmentByTitle(condition,input);
	}

	@Override
	public FacilityEquipment findFacilityEquipmentById(int equ_id) {
		return  facilityEquipment.findFacilityEquipmentById(equ_id);
	}
}
