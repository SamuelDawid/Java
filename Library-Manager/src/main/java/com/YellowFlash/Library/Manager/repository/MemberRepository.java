package com.YellowFlash.Library.Manager.repository;

import com.YellowFlash.Library.Manager.model.Member;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<@NonNull Member,@NonNull Long> {

    Optional<Member> findByEmail(String email);
    List<Member> findByLastNameIgnoreCase(String lastName);
    boolean existsByEmail(String email);
}
