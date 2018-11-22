package com.dragleo.ms.user.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dragleo.ms.user.domain.UserVO;

@Mapper
public interface IUserDao {

	@Select("select * from ms_user where id =#{id}")
	public UserVO load(@Param("id")int id);

	@Select("select * from ms_user where username=#{username} and password=#{password}")
	public UserVO findUserByUsernameAndPwd(@Param("username")String username,@Param("password") String password);

	@Insert("insert into ms_user values (#{id},#{username},#{password})")
	public void insertVO(UserVO userVO);

	
}
