package com.valtteri.userdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class QuizGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizGameApplication.class, args);
    }

    @GetMapping("/")
    public String getInfo() {
        return "Tervetuloa visailupeliin! Voit aloittaa pelin lähettämällä POST-pyynnön /start-endpointille.";
    }

    @GetMapping("/question")
    public Question getQuestion() {
        // Kysymys haetaan
        return new Question("Mikä on maailman suurin valtameri?");
    }

    @GetMapping("/answer")
    public String getAnswer() {
        // Oikea vastaus
        return "Tyynimeri";
    }

    @PostMapping("/start")
    public String startGame() {
        // Uusi peli
        return "Uusi peli aloitettu. Lähetä GET-pyyntö /question saadaksesi ensimmäisen kysymyksen.";
    }

    @PostMapping("/check-answer")
    public String checkAnswer(@RequestBody AnswerRequest answerRequest) {
        String userAnswer = answerRequest.getAnswer();
        String correctAnswer = "Tyynimeri"; // Oikea vastaus

        if (userAnswer.equals(correctAnswer)) {
            return "Oikein! Seuraava kysymys saatavilla GET-pyynnöllä /question.";
        } else {
            return "Väärin! Yritä uudelleen.";
        }
    }
}

