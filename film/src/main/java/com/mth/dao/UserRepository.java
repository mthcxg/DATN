package com.mth.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mth.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query(value = "SELECT * FROM user u WHERE u.email = :email AND u.password = :password", nativeQuery = true)
	public User findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

	public User findByEmail(@Param("email") String email);

	User findByUsername(String username);

	List<User> findAll();

}
