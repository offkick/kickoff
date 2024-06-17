package com.kickoff.api.controller.board.post;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    @PostMapping
    public Long createPost(
        @RequestBody CreatePostServiceRequest request)
    {
        return postService.create(request.toServiceDto());
    }

    @PutMapping("/{postId}")
    public Long updatePost(@RequestBody UpdatePostServiceRequest request)
    {
        return postService.update(request.toServiceDto);
    }
    
    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable(vale="postId") Long postId)
    {
        postService.delete(postId);
    }

}
