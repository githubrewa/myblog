package com.myblog.myblog.service;

import com.myblog.myblog.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
    List<CommentDto> getCommentsByPostId(long postId);
    CommentDto getCommentById(Long postId, Long commentId);

    CommentDto updateComment(long postId, long id, CommentDto commentDto);

    void deleteComment(long postId, long id);
}
