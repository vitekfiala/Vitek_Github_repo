package com.topmonks.assignment.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.topmonks.assignment.users.User;

public interface UserMapper {

	@Insert("INSERT INTO USER(email_id, first_name, last_name)" +
			"VALUES(#{emailId}, #{firstName}, #{lastName})")
	@Options(useGeneratedKeys=true, keyProperty="userId")
	public void insertUser(User user);

	@Select("SELECT	"
			+ "user_id as userId, "
			+ "email_id as emailId, "
			+ "first_name as firstName, "
			+ "last_name as lastName "
			+ "FROM USER "
			+ "WHERE USER_ID = #{userId}")
	public User getUserById(Integer userId);

	@Select("SELECT * FROM USER ")
	@Results({
		  @Result(id=true, property="userId", column="user_id"),
		  @Result(property="emailId", column="email_id"),
		  @Result(property="firstName", column="first_name"),
		  @Result(property="lastName", column="last_name")
	})
	public List<User> getAllUsers();

	@Update("UPDATE USER "
			+ "SET FIRST_NAME = #{firstName}, "
			+ "LAST_NAME = #{lastName}, "
			+ "EMAIL_ID=#{emailId} "
			+ "WHERE USER_ID = #{userId}")
	public void updateUser(User user);

	@Delete("DELETE FROM USER "
			+ "WHERE USER_ID=#{userId}")
	public void deleteUser(Integer userId);
	
	@Update("CREATE TABLE  user "
			+ "(user_id int(10) unsigned NOT NULL auto_increment, "
			+ "email_id varchar(45) NOT NULL, "
			+ "first_name varchar(45) NOT NULL, "
			+ "last_name varchar(45) default NULL, "
			+ "PRIMARY KEY  (user_id), "
			+ "UNIQUE KEY Index_2_email_uniq (email_id) )")
	public void createTableUser();

	@Update("DROP TABLE  user ")
	public void dropTableUser();
}
