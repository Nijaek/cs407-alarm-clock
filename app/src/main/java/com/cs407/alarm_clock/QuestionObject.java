package com.cs407.alarm_clock;

public class QuestionObject {
    private long id;
    private String question;
    private String answer;
    private String false1;
    private String false2;
    private String false3;

    public QuestionObject(long id, String question, String answer, String false1, String false2, String false3) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.false1 = false1;
        this.false2 = false2;
        this.false3 = false3;
    }

    public long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getFalse1() {
        return false1;
    }

    public String getFalse2() {
        return false2;
    }

    public String getFalse3() {
        return false3;
    }
}
