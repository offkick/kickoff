package com.kickoff.api.controller.board.post;

import com.kickoff.api.util.CookieUtils;
import com.kickoff.core.board.post.dto.PostSearchResponse;
import com.kickoff.core.board.post.service.PostService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Log4j2
@Aspect
@Component
@RequiredArgsConstructor
public class PostAspect {
    private final PostService postService;
    private static final String POST_VIEW_COOKIE = "POST_VIEW";

    @AfterReturning(value = "execution(* com.kickoff.api.controller.board.post.PostSearchController.findPost(..)) && args(postId, request, response)", returning = "result")
    public void searchAdvice(JoinPoint joinPoint, Long postId, HttpServletRequest request, HttpServletResponse response, Object result)
    {
        PostSearchResponse res = (PostSearchResponse) result;
        viewCountValidation(res.postId(), request, response);
    }

    public void viewCountValidation(Long postId, HttpServletRequest request, HttpServletResponse response)
    {
        Cookie cookie = CookieUtils.getCookie(request, POST_VIEW_COOKIE, postId.toString());

        if (cookie == null)
        {
            postService.addViewCount(postId);
            CookieUtils.setCookie(response, POST_VIEW_COOKIE, postId.toString());
        }
    }
}
