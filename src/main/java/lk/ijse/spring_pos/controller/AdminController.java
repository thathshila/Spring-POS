package lk.ijse.spring_pos.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/admin")
public class AdminController {
@GetMapping("/checkRole")
public String checkRole() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String role = authentication.getAuthorities().stream()
            .map(authority -> authority.getAuthority())
            .findFirst()
            .orElse("UNKNOWN");

    return "{\"role\": \"" + role + "\"}";
}

    @GetMapping("/test1")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String check() {
        return "passed~!1";
    }

    @GetMapping("/test2")
    @PreAuthorize("hasAuthority('USER')")
    public String checks() {
        return "passed~!2";
    }

    @GetMapping("/test3")
    @PreAuthorize("hasAuthority('USER')")
    public String checkss() {
        return "passed~!3";
    }
}
