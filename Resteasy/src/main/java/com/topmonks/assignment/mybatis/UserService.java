package com.topmonks.assignment.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.topmonks.assignment.mappers.UserMapper;
import com.topmonks.assignment.users.User;

public class UserService implements UserMapper {

	@Override
	public void insertUser(User user) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			userMapper.insertUser(user);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public User getUserById(Integer userId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			return userMapper.getUserById(userId);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public List<User> getAllUsers() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			return userMapper.getAllUsers();
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public void updateUser(User user) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			userMapper.updateUser(user);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public void deleteUser(Integer userId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			userMapper.deleteUser(userId);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public void createTableUser() {
		// TODO Auto-generated method stub
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			userMapper.createTableUser();
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public void dropTableUser() {
		// TODO Auto-generated method stub
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			userMapper.dropTableUser();
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

}
