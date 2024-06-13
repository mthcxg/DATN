package com.mth.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mth.model.Comments;

public interface CommentRepository extends JpaRepository<Comments, Long>{

}
