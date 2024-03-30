package com.kickoff.domain.board.post;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class PostUnitTest {

    @Test
    public void updateTest()
    {
        // given
        Post post = Post.builder()
                .postDate(LocalDateTime.now())
                .content("content")
                .title("title")
                .build();

        // when
        post.update(Post.builder().title("updatedTitle").build());

        // then
        assertThat(post.getTitle()).isEqualTo("updatedTitle");
    }
}
