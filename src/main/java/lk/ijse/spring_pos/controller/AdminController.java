package lk.ijse.spring_pos.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("api/v1/admin")
public class AdminController {
    @GetMapping("/test1")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String check(){
        return "passed~!1";
    }

    @GetMapping("/test2")
    @PreAuthorize("hasAuthority('USER')")
    public String checks(){
        return "passed~!2";
    }

    @GetMapping("/test3")
    @PreAuthorize("hasAuthority('USER')")
    public String checkss(){
        return "passed~!2";
    }

}
