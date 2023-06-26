package com.mongodb.demo;

import com.mongodb.demo.entity.Comment;
import com.mongodb.demo.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.List;

@SpringBootTest
class MongodbDemoApplicationTests {

    @Autowired
    private CommentService commentService;


    @Test
    public void testList() {
//        List<Comment> comments = commentService.find();

        Comment comment = commentService.findById("1");
        System.out.println(comment);
    }

    @Test
    public void testFindCommentByPatientId() {
        Page<Comment> page = commentService.findCommentListByParentId("3", 1, 2);
        System.out.println(page.getContent());
        System.out.println(page.getTotalElements());
    }

    @Test
    public void testUpdateCommentLikenum() {
        commentService.updateCommentLikenum("1");
    }
}
