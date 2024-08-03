package com.kickoff.core.member;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    @EntityGraph(attributePaths = {"memberRoles"})
    @Query("select m from Member m where m.email = :email")
    Member getWithRoles(@Param("email") String email);

    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<Member> findByMemberIdAndIsDeletedFalse(Long memberId);
}
