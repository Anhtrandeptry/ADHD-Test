package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {

    @GetMapping("/")
    public String redirectToQuiz() {
        return "redirect:/adhd/quiz";
    }
}
