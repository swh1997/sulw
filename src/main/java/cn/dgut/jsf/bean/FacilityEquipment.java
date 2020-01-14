package cn.dgut.jsf.bean;

import lombok.Data;

@Data
public class FacilityEquipment {
    private int equ_id;//设备表id
    private String equ_name;//设备名称
    private int equ_quantity;//设备可用数量
    private int equ_repairquantity;//设备维修数量
    private String equ_content;//设备内容

}
