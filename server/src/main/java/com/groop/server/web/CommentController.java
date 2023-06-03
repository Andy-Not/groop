package com.groop.server.web;

import com.groop.server.domain.Comment;
import com.groop.server.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author joandy alejo garcia
 */
@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id){
        Optional<Comment> optionalComment = commentService.findCommentById(id);
        try {
            if (optionalComment.isPresent()){
                commentService.deleteComment(optionalComment.get());
                return new ResponseEntity<>("the comment has been deleted", HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>("comment does not exist", HttpStatus.BAD_GATEWAY);
        }catch (Exception e){
            return errorMessage();
        }
    }
    ResponseEntity<?> errorMessage(){
        return new ResponseEntity<>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
