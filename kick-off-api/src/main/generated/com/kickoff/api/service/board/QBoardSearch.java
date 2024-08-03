package com.kickoff.api.service.board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoardSearch is a Querydsl query type for BoardSearch
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardSearch extends EntityPathBase<BoardSearch> {

    private static final long serialVersionUID = 2068741831L;

    public static final QBoardSearch boardSearch = new QBoardSearch("boardSearch");

    public final NumberPath<Integer> likeCount = createNumber("likeCount", Integer.class);

    public final NumberPath<Long> postId = createNumber("postId", Long.class);

    public final NumberPath<Integer> viewCount = createNumber("viewCount", Integer.class);

    public QBoardSearch(String variable) {
        super(BoardSearch.class, forVariable(variable));
    }

    public QBoardSearch(Path<? extends BoardSearch> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardSearch(PathMetadata metadata) {
        super(BoardSearch.class, metadata);
    }

}

