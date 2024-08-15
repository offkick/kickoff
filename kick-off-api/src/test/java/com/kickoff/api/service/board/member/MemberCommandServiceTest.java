package com.kickoff.api.service.board.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;


class MemberCommandServiceTest {
    public static List<String> filters = List.of("javascript:");

    public static boolean s(String v){
        try {
            String decode = URLDecoder.decode(v, StandardCharsets.UTF_8.toString());
            if (filters.stream().anyMatch(s-> v.contains(s) || decode.contains(s))){
                return false;
            }

            return true;
        } catch (UnsupportedEncodingException e) {
        }

        return true;
    }
    @Test
    public void dd()
    {
        boolean s = s("javascript:eqeqwewqe");
        Assertions.assertThat(s).isTrue();
    }
}