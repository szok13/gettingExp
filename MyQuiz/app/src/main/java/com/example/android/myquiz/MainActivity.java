package com.example.android.myquiz;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Question> questions = new ArrayList<>();
    private int currentQuestionIndex = 0;
    private Integer correctAnswer = 0;
    private int score = 0;
    private boolean questionModeIsOn = true;
    private boolean buttonsAreOn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeQuiz();
    }

    /**
     * resets current question and score data
     */
    private void resetQuiz() {
        currentQuestionIndex = 0;
        score = 0;
    }

    /**
     * Get questions and set first question
     */
    private void initializeQuiz() {
        QuestionsProvider questionsProvider = new QuestionsProvider();
        questions = questionsProvider.generateQuestions();
        toggleQuestion();
    }

    /**
     * set next question
     */
    private void toggleQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);

        TextView textView = (TextView) findViewById(R.id.questionContent);
        textView.setText(currentQuestion.getQuestionContent());

        int imageResource = getResources().getIdentifier(currentQuestion.getQuestionImageURI(), "drawable", getPackageName());
        ImageView imageView = (ImageView) findViewById(R.id.questionImage);
        imageView.setImageResource(imageResource);


        List<String> answers = currentQuestion.getAnswers();
        int buttonNumber = 1;
        for (String ans : answers) {
            int buttonResource = getResources().getIdentifier("answer" + buttonNumber++, "id", getPackageName());
            Button button = (Button) findViewById(buttonResource);
            button.setText(ans);
        }

        correctAnswer = currentQuestion.getCorrectAnswer();
        currentQuestionIndex++;
    }

    /**
     * handle clicking an answer
     * @param view
     */
    public void answerQuestion(View view) {
        toggleButtonsEnability();
        boolean correct = checkAnswer(view);
        TextView answerInfo = (TextView) findViewById(R.id.answerInfo);
        answerInfo.setVisibility(View.VISIBLE);

        if (correct) {
            answerInfo.setText("Correct answer");
            answerInfo.setBackgroundColor(Color.parseColor("#76FF03"));
            score++;
        } else {
            answerInfo.setText("Incorrect answer");
            answerInfo.setBackgroundColor(Color.parseColor("#F44336"));
        }

        Toast.makeText(this, "Your score " + score + " / " + questions.size(), Toast.LENGTH_SHORT).show();
    }

    /**
     * disables and enables answers buttons
     */
    private void toggleButtonsEnability() {
        for (int i = 1; i <=4; i++) {
            int buttonResource = getResources().getIdentifier("answer" + i, "id", getPackageName());
            Button button = (Button) findViewById(buttonResource);
            button.setEnabled(!buttonsAreOn);
        }
        buttonsAreOn = !buttonsAreOn;
    }


    /**
     * checks if answer is correct
     * @param view
     * @return
     */
    private boolean checkAnswer(View view) {
        String tag = (String) view.getTag();

        Integer intTag = Integer.parseInt(tag);
        if (intTag.equals(correctAnswer)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * sets up next question or quiz summary
     * @param view
     */
    public void nextQuestion(View view) {
        ImageView imageView = (ImageView) findViewById(R.id.questionImage);
        imageView.setImageResource(0);
        if (currentQuestionIndex == questions.size()) {
            turnOffQuestionViewVisibility();
            toggleScoreVisibility(View.VISIBLE);
            toggleNextQuestionAndPlayAgainButtonAndImageViewVisibility();
        } else {
            TextView answerInfo = (TextView) findViewById(R.id.answerInfo);
            answerInfo.setVisibility(View.INVISIBLE);
            toggleQuestion();
        }
        toggleButtonsEnability();
    }

    /**
     * shows score in summary and hides in questions
     * @param visibility
     */
    private void toggleScoreVisibility(int visibility) {
        TextView scoreText = (TextView) findViewById(R.id.scoreText);
        scoreText.setText("Your score: " + score + " / " + questions.size());
        scoreText.setVisibility(visibility);
    }

    /**
     * toggles visibility of NextQuestion button, PlayAgain button and ImageView between question mode and quiz summary
     */
    private void toggleNextQuestionAndPlayAgainButtonAndImageViewVisibility(){
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        Button nextQuestionButton = (Button) findViewById(R.id.nextQuestionButton);
        ImageView imageView = (ImageView) findViewById(R.id.questionImage);
        if(questionModeIsOn){
            playAgainButton.setVisibility(View.VISIBLE);
            nextQuestionButton.setVisibility(View.GONE);
            imageView.setVisibility(View.GONE);
            questionModeIsOn = false;
        } else{
            playAgainButton.setVisibility(View.GONE);
            nextQuestionButton.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
            questionModeIsOn = true;
        }
    }

    /**
     * turns off question and answers and answerInfo before summary is on
     */
    private void turnOffQuestionViewVisibility() {
        toggleQuestionVisibility(View.GONE);
        toggleAnswerInfoVisibility(View.GONE);
        toggleAnswerButtonsVisibility(View.GONE);
    }

    /**
     * changes AnswerInfo button visibility
     * @param visibility
     */
    private void toggleAnswerInfoVisibility(int visibility) {
        TextView answerInfo = (TextView) findViewById(R.id.answerInfo);
        answerInfo.setVisibility(visibility);
    }


    /**
     * changes Answer buttons visibility
     * @param visibility
     */
    private void toggleAnswerButtonsVisibility(int visibility) {
        Button button;
        for (int i = 1; i <= 4; i++) {
            int buttonResource = getResources().getIdentifier("answer" + i, "id", getPackageName());
            button = (Button) findViewById(buttonResource);
            button.setVisibility(visibility);
        }
    }

    /**
     * changes question content visibility
     * @param visibility
     */
    private void toggleQuestionVisibility(int visibility){
        TextView questionContent = (TextView) findViewById(R.id.questionContent);
        questionContent.setVisibility(visibility);
    }

    /**
     * resets variables values and views visibilities for new game
     * @param view
     */
    public void playAgain(View view){
        resetQuiz();
        initializeQuiz();
        toggleAnswerButtonsVisibility(View.VISIBLE);
        toggleAnswerInfoVisibility(View.INVISIBLE);
        toggleQuestionVisibility(View.VISIBLE);
        toggleScoreVisibility(View.GONE);
        toggleNextQuestionAndPlayAgainButtonAndImageViewVisibility();
    }
}
