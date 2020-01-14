package cn.dgut.jsf.service;

import cn.dgut.jsf.bean.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeService {
    public List<Notice> findAllNotice(); // 查询所有公告信息
    public Notice findNoticeById(int notice_id); // 根据公告id查询
    public int updateNotice(Notice notice); // 修改公告信息
    public int deleteNotice(String notice_id); // 删除过期公告信息
    public int updateNoticeState(Notice notice);//修改公告状态
    public int addNotice(Notice notice); // 新增公告
}
