package cn.dgut.jsf.service;

import cn.dgut.jsf.bean.Activity;
import cn.dgut.jsf.bean.FacilityEquipment;
import cn.dgut.jsf.bean.Notice;

import java.util.List;

public interface WelcomeService {
    public List<Activity> findActivityUp(); // 查询所有在线的活动

    public List<FacilityEquipment> findEquipmentUp(); // 查询上线的招聘信息

    public List<Notice> findNoticeUp(); // 查询上线的公告信息
    public int findCoachs();
    public int findUsers();
    public int findMembers();
    public int findAdmins();
    public int findActivities();
    public int findFes();
    public int findNotices();
}
