package com.dragleo.ms.user.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dragleo.ms.login.model.LoginVO;
import com.dragleo.ms.user.domain.UserVO;

@Mapper
public interface IUserDao {

	@Select("select * from ms_user where id =#{id}")
	public UserVO load(@Param("id")int id);

	

	@Insert("insert into ms_user values (#{id},#{username},#{password})")
	public void insertVO(UserVO userVO);
	
	@Select("select * from rms_user where mobile=#{mobile} and password=#{password}")
	public LoginVO login(LoginVO loginVo);

	
}
