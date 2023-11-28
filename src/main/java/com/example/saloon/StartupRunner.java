package com.example.saloon;

import com.example.saloon.member.Member;
import com.example.saloon.member.MemberRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StartupRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(StartupRunner.class);

    @Autowired
    private MemberRepo memberRepo;
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        List<Member> members = memberRepo.findAll();
        members.forEach(member -> logger.info(member.getName() + " " + member.getSurname() + " member ID: "
                + member.getId()));

//        Optional<Member> serviceMember = Optional.of(memberRepo.findByLogin("service"));
//        serviceMember.ifPresent(member -> {
//            logger.info("Hashed password for member with login 'service': " + passwordEncoder.encode(member.getPassword()));
//        });
    }
}
