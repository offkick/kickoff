package com.kickoff.api.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

public class CookieUtils {

    private CookieUtils()
    {
        throw new AssertionError();
    }

    public static Cookie getCookie(HttpServletRequest request, String name, String value)
    {
        Cookie[] cookies = request.getCookies();

        if (cookies == null)
        {
            return null;
        }

        for (Cookie cookie : cookies)
        {
            if (cookie.getName().contains(name) && cookie.getValue().equals(value))
            {
                return cookie;
            }
        }

        return null;
    }

    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value)
    {
        Cookie cookie = new Cookie(name, value);
        long todayEndSecond = LocalDate.now().atTime(LocalTime.MAX).toEpochSecond(ZoneOffset.UTC);
        long currentSecond = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        cookie.setPath(request.getRequestURI());
        cookie.setMaxAge((int) (todayEndSecond - currentSecond));
        response.addCookie(cookie);
    }
}
