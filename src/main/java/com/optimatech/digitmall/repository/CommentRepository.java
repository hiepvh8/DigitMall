package com.optimatech.digitmall.repository;

import com.optimatech.digitmall.model.entity.Comment;
import com.optimatech.digitmall.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
