package com.YellowFlash.Library.Manager.service;


import com.YellowFlash.Library.Manager.Exceptions.MemberNotFoundException;
import com.YellowFlash.Library.Manager.model.Member;
import com.YellowFlash.Library.Manager.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member registerMember(String firstName, String lastName, String email, String phone) {
        if (firstName.isEmpty()) throw new RuntimeException("empty Name");
        if (lastName.isEmpty()) throw new RuntimeException("empty Last name");
        if (email.isEmpty()) throw new RuntimeException("empty email");
        if (phone.isEmpty()) throw new RuntimeException("empty phoneNumber");

        if (memberRepository.existsByEmail(email)) throw new RuntimeException("email is in use");
        if (!email.contains("@")) throw new RuntimeException("Email not valid");

        Member newMember = Member.builder().firstName(firstName).lastName(lastName).email(email).phoneNumber(phone).build();
        return memberRepository.save(newMember);

    }

    // Zwraca wszystkich aktywnych memberów
    public List<Member> getAllActiveMembers() {
        return memberRepository.findAll().stream().filter(Member::isActive).toList();
    }

    public Member login(String email) {
        Optional<Member> memberToLogin = memberRepository.findByEmail(email);
        return memberToLogin.orElseThrow(() -> new MemberNotFoundException(email));

    }
}
