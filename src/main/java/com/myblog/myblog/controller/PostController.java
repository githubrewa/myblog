package com.myblog.myblog.controller;

import com.myblog.myblog.payload.PostDto;
import com.myblog.myblog.service.PostService;
import com.myblog.myblog.service.impl.PostResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //http://localhost:8080/api/posts
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto, BindingResult result) {
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),
                   HttpStatus.INTERNAL_SERVER_ERROR );
        }

        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }
    //http://localhost:8080/api/posts?pageNo=0&pageSize=10&sortBy=title&sortDir=asc
    @GetMapping()
    public PostResponse getAllPPosts(
                    @RequestParam(value="pageNo", defaultValue = "0", required = false) int pageNo,
                    @RequestParam(value = "pageSize", defaultValue = "10",required = false) int pageSize,
                    @RequestParam(value="sortBy", defaultValue = "id", required = false) String sortBy,
                    @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir

                    ) {
        PostResponse postResponse= postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
        return postResponse;

    }

    //http://localhost:8080/api/posts/1

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(

            @PathVariable("id") long id) {
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);

    }

    //http://localhost:8080/api/posts/1
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable("id") long id) {
        PostDto postResponse = postService.updatePost(postDto, id);
        return ResponseEntity.ok(postResponse);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(
            @PathVariable(name="id") long id
    ){
       postService.deletePostById(id);

        return new ResponseEntity<String>("post is deleted!!",HttpStatus.OK);
    }
}
 