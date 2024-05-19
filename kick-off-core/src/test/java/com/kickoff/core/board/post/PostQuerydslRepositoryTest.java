package com.kickoff.core.board.post;


import com.kickoff.core.TestConfiguration;
import com.kickoff.core.board.post.dto.SearchPostResponse;
import com.kickoff.core.board.postcomment.PostComment;
import com.kickoff.core.board.postcomment.PostCommentRepository;
import com.kickoff.core.member.Member;
import com.kickoff.core.member.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;


@ActiveProfiles("core")
@ContextConfiguration(classes = TestConfiguration.class)
@SpringBootTest
public class PostQuerydslRepositoryTest {
    @Autowired
    PostQuerydslRepository postQuerydslRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostCommentRepository postCommentRepository;

    @Test
    @DisplayName("포스트 찾기 테스트")
    public void searchPost()
    {
        //given
        Member member = memberRepository.save(
                Member.builder()
                        .nickName("hi2")
                        .password("password")
                        .build()
        );
        Post post = postRepository.save(
                Post.builder()
                        .title("title2")
                        .category("category")
                        .member(member)
                        .build()
        );
        PostComment postComment = postCommentRepository.save(
                PostComment.builder()
                        .comment("comment")
                        .post(post)
                        .member(member)
                        .build()
        );
        PostComment postComment2 = postCommentRepository.save(
                PostComment.builder()
                        .comment("comment2")
                        .post(post)
                        .member(member)
                        .build()
        );

        //when
        SearchPostResponse searchPostResponse = postQuerydslRepository.searchPost(post.getPostId());
        // then
        Assertions.assertNotNull(searchPostResponse);
    }
}
