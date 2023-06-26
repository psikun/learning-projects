package com.mongodb.demo.mapper;

import com.mongodb.demo.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author psikun
 * @Description CommentMapper
 * @Date 2023/06/25/ 15:51
 */
public interface CommentMapper extends MongoRepository<Comment, String> {

    Page<Comment> findByParentid(String parentid, Pageable pageable);
}
