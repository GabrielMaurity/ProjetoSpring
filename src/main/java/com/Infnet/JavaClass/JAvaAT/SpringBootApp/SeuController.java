package com.Infnet.JavaClass.JAvaAT.SpringBootApp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeuController {

    @GetMapping("/")
    public String home() {
        return "Bem-vindo à minha aplicação!";
    }
}
