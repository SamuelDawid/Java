package com.YellowFlash.Library.Manager;

import com.YellowFlash.Library.Manager.Exceptions.MemberNotFoundException;
import com.YellowFlash.Library.Manager.model.Member;
import com.YellowFlash.Library.Manager.repository.MemberRepository;
import com.YellowFlash.Library.Manager.service.LibraryService;
import com.YellowFlash.Library.Manager.service.MemberService;
import com.YellowFlash.Library.Manager.service.MenuHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {
    final LibraryService libraryService;
    final MenuHandler menuHandler;
    final MemberService memberService;

    @Autowired
    public Runner(LibraryService libraryService, MenuHandler menuHandler,MemberService memberService) {
        this.libraryService = libraryService;
        this.menuHandler = menuHandler;
        this.memberService = memberService;
    }

    @Override
    public void run(String... args) {
        seedData();
        while (true){
            try{
                Member currentMember = menuHandler.showLoginMenu();
                menuHandler.showMenu(currentMember);
            } catch (MemberNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void seedData() {
        memberService.registerMember("admin","admin","admin@admin.com","555-555-555");
        libraryService.addBook("Effective Java", "Joshua Bloch", "EFJB1");
        libraryService.addBook("Clean Code", "Robert Martin", "CCRM1");
        libraryService.addBook("Design Patterns", "Gang of Four", "DPGF1");
        libraryService.addBook("Spring in Action", "Craig Walls", "SIA01");
        libraryService.addBook("The Pragmatic Programmer", "Hunt and Thomas", "TPP01");
    }

}
