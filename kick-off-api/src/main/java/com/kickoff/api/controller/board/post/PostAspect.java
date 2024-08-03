package com.kickoff.api.controller.board.post;

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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

@Log4j2
@Aspect
@Component
@RequiredArgsConstructor
public class PostAspect {
    private final PostService postService;

    @AfterReturning(value = "execution(* com.kickoff.core.board.post.PostQuerydslRepository.findPost(..)) && args(postId, request, response)", returning = "result")
    public void searchAdvice(JoinPoint joinPoint, Long postId, HttpServletRequest request, HttpServletResponse response, Object result)
    {
        PostSearchResponse res = (PostSearchResponse) result;
        viewCountValidation(res.postId(), request, response);
    }

    public void viewCountValidation(Long postId, HttpServletRequest request, HttpServletResponse response)
    {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        boolean isCookie = false;

        for (int i = 0; cookies != null && i < cookies.length; i++)
        {
            // postView 쿠키가 있을 때
            if (cookies[i].getName().equals("postView")) {
                // cookie 변수에 저장
                cookie = cookies[i];
                // 만약 cookie 값에 현재 게시글 번호가 없을 때
                if (!cookie.getValue().contains("[" + postId + "]")) {
                    // 해당 게시글 조회수를 증가시키고, 쿠키 값에 해당 게시글 번호를 추가
                    postService.addViewCount(postId);
                    cookie.setValue(cookie.getValue() + "[" + postId + "]");
                }
                isCookie = true;
                break;
            }
        }

        if (!isCookie) {
            postService.addViewCount(postId);
            cookie = new Cookie("postView", "[" + postId + "]"); // oldCookie에 새 쿠키 생성
        }

        // 쿠키 유지시간을 오늘 하루 자정까지로 설정
        long todayEndSecond = LocalDate.now().atTime(LocalTime.MAX).toEpochSecond(ZoneOffset.UTC);
        long currentSecond = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        cookie.setPath("/"); // 모든 경로에서 접근 가능
        cookie.setMaxAge((int) (todayEndSecond - currentSecond));
        response.addCookie(cookie);
    }
}
