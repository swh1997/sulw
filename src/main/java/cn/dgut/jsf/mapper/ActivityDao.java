package cn.dgut.jsf.mapper;

import cn.dgut.jsf.bean.Activity;
import cn.dgut.jsf.bean.Coach;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Customer接口文件
 */

@Repository
public interface ActivityDao {
    public List<Activity> findActivityUp(); //查询所有在线的活动
    public List<Activity> findActivityUpByAct_name(@Param("condition")String condition,  @Param("input") String input); //根据活动主题查询所有在线的活动
    public List<Activity> findActivityUpByCoach_name( String condition, String input); //根据教练名称查询所有在线的活动
    public Activity findActivityById(int act_id); // 根据活动id查询活动信息
    public int updateActivityState(Activity act);
    public List<Coach> findAllCoaName();
    public List<Coach> findCoachByCoaExpert(String coach_expert);
    public int addActivity(Activity activity);
}
