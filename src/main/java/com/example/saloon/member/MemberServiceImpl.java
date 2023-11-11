package com.example.saloon.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepo memberRepo;

    private final MemberConverter memberConverter;


    @Override
    public MemberDto createMember(Member member) {
        return memberConverter.convert(memberRepo.save(member));
    }

    @Override
    public List<MemberDto> getAll() {
        return memberRepo.findAll().stream()
                .map(memberConverter::convert)
                .toList();
    }

    @Override
    public MemberDto getMemberByRoom(Integer room) {
        return memberConverter.convert(memberRepo.findByRoom(room));
    }

    @Override
    public void deleteMemberByRoom(Integer room) {
        Member user = memberRepo.findByRoom(room);
        if (user != null){
            memberRepo.delete(user);
        }
    }

    @Override
    public MemberDto updateMember(Member user) {
        if (memberRepo.existsById(user.getId())){
            return memberConverter.convert(memberRepo.save(user));
        } else {
            throw new RuntimeException("User does not exist");
        }
    }

    @Override
    public void deleteMemberByLogin(String login) {
        Member user = memberRepo.findByLogin(login);
        if (user != null){
            memberRepo.delete(user);
        }
    }

    @Override
    public Member findByLogin(String login) {
        return memberRepo.findByLogin(login);
    }

    @Override
    public boolean loginIsExist(String login) {
        return memberRepo.existsByLogin(login);
    }
}
