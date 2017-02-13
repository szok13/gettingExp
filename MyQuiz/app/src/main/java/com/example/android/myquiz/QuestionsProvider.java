package com.example.android.myquiz;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piotr on 2017-02-08.
 */

public class QuestionsProvider {

    public List<Question> generateQuestions(){
        List<Question> questions = new ArrayList<>();
        List<String> answers = generateAnswers("Intake", "Compression", "Power", "Exclusion");
        Question question = new Question("Which is not one of four engine strokes?", answers, 4, "", false );
        questions.add(question);

        answers = generateAnswers("Super useful vehicle", "Sport utility vehicle", "Sport user vehicle", "Super ultra vehicle");
        question = new Question("What SUV stands for?", answers, 2, "", false );
        questions.add(question);

        answers = generateAnswers("Fiat", "Toyota", "Ford", "Volvo");
        question = new Question("The first car factory was created by?", answers, 3, "", false );
        questions.add(question);

        answers = generateAnswers("Joint", "Crank shaft", "Engine", "Piston");
        question = new Question("What item is on the picture?", answers, 4, "piston", true );
        questions.add(question);

        answers = generateAnswers("140 km/h", "130 km/h", "120 km/h", "None");
        question = new Question("What is the speed limit on German highway?", answers, 4, "", false );
        questions.add(question);

        answers = generateAnswers("Elena", "Eleanor", "Eliza", "Elsa");
        question = new Question("What was the name of a Mustang Shelby GT500 in \"Gone in Sixty Seconds\"?", answers, 2, "", false );
        questions.add(question);

        return questions;
    }

    public List<String> generateAnswers(String ans1, String ans2, String ans3, String ans4){
        List<String> answers = new ArrayList<>();
        answers.add(ans1);
        answers.add(ans2);
        answers.add(ans3);
        answers.add(ans4);
        return answers;
    }
}
