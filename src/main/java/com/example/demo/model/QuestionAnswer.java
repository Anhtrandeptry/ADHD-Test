package com.example.demo.model;

public class QuestionAnswer {
    private String question;
    private String answer;

    // ✅ Constructor không tham số (bắt buộc cho Spring binding)
    public QuestionAnswer() {
    }

    // ✅ Constructor có tham số (dùng khi khởi tạo thủ công)
    public QuestionAnswer(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
