package com.abin.park_system.mapper;

import com.abin.park_system.entity.Notice;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoticeMapper {
	
	//添加信息
	@Insert("insert into Notice(title,content,createDate,admin_id,admin_name) values(#{title},#{content},SYSDATE(),#{admin_id},#{admin_name})")
    public void insertNotice(Notice notice);
	
	//删除信息
	@Delete("delete from Notice where id=#{id}")
	public void deleteNoticeById(int id);
	
	//修改信息
	@Update("update Notice set title=#{title},content=#{content} where id=#{id}")
	public void updateNotice(Notice notice);
	
	//查询信息
	@Select("select * from Notice where title like #{title}")
	List<Notice> findByTitle(@Param("title") String title);
	
	//查询信息
	@Select("select * from Notice where id=#{id}")
	Notice findById(@Param("id") int id);
	
	@Select("select * from Notice order by createDate desc limit 0,10")
	List<Notice> findAllNotice();
	
	
}
