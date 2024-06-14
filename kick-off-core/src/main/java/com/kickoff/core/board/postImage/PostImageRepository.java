package com.kickoff.core.board.postImage;

import com.kickoff.core.board.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostImageRepository extends JpaRepository<PostImage,Long> {
}
