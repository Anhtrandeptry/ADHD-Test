package com.example.demo.controller;

import com.example.demo.model.ADHDRequest;
import com.example.demo.model.QuestionAnswer;
import com.example.demo.service.CohereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // ✅ cần import đúng
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/adhd")
public class ADHDController {

    @Autowired
    private CohereService cohereService;

    @GetMapping("/quiz")
    public String showQuiz(Model model) {
        List<QuestionAnswer> questions = List.of(
                new QuestionAnswer("Does your child find it difficult to finish a task or homework at home or at school?", ""),
                new QuestionAnswer("Does your child find it difficult to organise themselves for school and other activities?", ""),
                new QuestionAnswer("Does your child lose or misplace personal belongings at home or school?", ""),
                new QuestionAnswer("Does your child often fidget or squirm and find it difficult to sit still?", ""),
                new QuestionAnswer("Would you describe your child as being “always on the go” and always feel compelled to do things as if he or she is driven by a “motor”?", ""),
                new QuestionAnswer("Does your child find it hard to remain focused in group settings?", ""),
                new QuestionAnswer("Can you say that your child’s mind often feels cluttered making it hard for him or her to concentrate on one thing at a time?", ""),
                new QuestionAnswer("Does your child often become irritable, like he or she has a short fuse?", ""),
                new QuestionAnswer("Does your child have mood swings, sometimes he or she feels quite elated and other times low?", ""),
                new QuestionAnswer("Does your child often miss what is being said to him or her in conversations?", "")
        );
        model.addAttribute("questions", questions);
        model.addAttribute("result", null);
        return "result";
    }

    @PostMapping("/analyze")
    public String analyze(@ModelAttribute ADHDRequest request, Model model) {
        String result = cohereService.analyzeWithCohere(request.getResponses());
        model.addAttribute("result", result);
        return "result";
    }
}
