package com.myblog.myblog.service;

import com.myblog.myblog.payload.PostDto;
import com.myblog.myblog.service.impl.PostResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePostById(long id);
}
