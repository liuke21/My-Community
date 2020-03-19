package com.lk.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lk.model.User;
@Mapper
public interface userMapper {
	@Insert("insert into user (cound_id,name,avatar_url,token) values (#{cound_id},#{name},#{avatar_url},#{token})")
	void insertUser(User user);
	
	@Select("select * from user where token=#{token}")
	User selectUser(@Param("token") String token);
	
	@Select("select * from user where name=#{autor}")
	User seUserByAutor(@Param("autor") String autor);
	
	@Update("update user set token=#{token} where name=#{autor}")
	void addUser(@Param("token") String token,@Param("autor") String autor);
	
	@Select("select * from user where id=#{id}")
	User selectUserById(@Param("id") int id);
}