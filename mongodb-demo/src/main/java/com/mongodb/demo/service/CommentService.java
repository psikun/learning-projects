package com.mongodb.demo.service;

import com.mongodb.demo.entity.Comment;
import com.mongodb.demo.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author psikun
 * @Description CommentService
 * @Date 2023/06/25/ 15:52
 */

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 保存一个评论
     *
     * @param comment
     */
    public void save(Comment comment) {
        commentMapper.save(comment);
    }

    /**
     * 更新评论
     *
     * @param comment
     */
    public void update(Comment comment) {
        commentMapper.save(comment);
    }

    /**
     * 根据id删除评论
     *
     * @param id
     */
    public void deleteById(String id) {
        commentMapper.deleteById(id);
    }

    /**
     * 查询所有评论
     *
     * @return
     */
    public List<Comment> find() {
        return commentMapper.findAll();
    }

    /**
     * 根据id查询评论
     *
     * @param id
     * @return
     */
    public Comment findById(String id) {

        return commentMapper.findById(id).get();
    }

    public Page<Comment> findCommentListByParentId(String parentId, int page, int size) {
        return commentMapper.findByParentid(parentId, PageRequest.of(page - 1, size));
    }

    public void updateCommentLikenum(String id) {
        // 查询条件
        Query query = Query.query(Criteria.where("_id").is(id));

        // 更新条件
        Update update = new Update();
        update.inc("likenum");

        mongoTemplate.updateFirst(query, update, Comment.class);
    }
}

