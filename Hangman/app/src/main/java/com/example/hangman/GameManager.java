package com.example.hangman;

public class GameManager {
    private String answer;
    private char[] correctAnswer = {'B','A','N','A','N','A'};

    public GameManager(String answer){
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return getAnswer();
    }

    public boolean lengthChecker(String answer){
        return answer.length() != correctAnswer.length;
    }

    public boolean checker(String answer){
        char[] temp = new char[6];
        temp = this.answer.toUpperCase().toCharArray();
        boolean check = true;
        for(int i = 0; i < temp.length; i++){
            if(temp[i] != correctAnswer[i]){
                temp[i] = '*';
                check = false;
            }
        }
        this.answer = new String(temp);
        return check;
    }
}
