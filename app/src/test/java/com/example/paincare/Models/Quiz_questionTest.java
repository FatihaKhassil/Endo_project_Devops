package com.example.paincare.Models;
import com.spmenais.paincare.Models.Quiz_question;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class Quiz_questionTest {

    private Quiz_question question;

    @Before
    public void setUp() {
        List<String> answers = Arrays.asList("Réponse A", "Réponse C");
        question = new Quiz_question(
                "Quel est le symptôme principal ?",
                "Réponse A", "Réponse B", "Réponse C", "Réponse D",
                answers,
                "L'endométriose cause des douleurs pelviennes."
        );
    }

    @Test
    public void testQuestionCreation_withFullConstructor() {
        assertEquals("Quel est le symptôme principal ?", question.getQst());
        assertEquals("Réponse A", question.getOpt1());
        assertEquals("Réponse B", question.getOpt2());
        assertEquals("Réponse C", question.getOpt3());
        assertEquals("Réponse D", question.getOpt4());
        assertNotNull(question.getAnswers());
        assertEquals(2, question.getAnswers().size());
    }

    @Test
    public void testDefaultConstructor() {
        Quiz_question q = new Quiz_question();
        assertNull(q.getQst());
        assertNull(q.getOpt1());
        assertNull(q.getAnswers());
    }

    @Test
    public void testSetQst() {
        question.setQst("Nouvelle question ?");
        assertEquals("Nouvelle question ?", question.getQst());
    }

    @Test
    public void testSetOptions() {
        question.setOpt1("Option 1 modifiée");
        question.setOpt2("Option 2 modifiée");
        question.setOpt3("Option 3 modifiée");
        question.setOpt4("Option 4 modifiée");
        assertEquals("Option 1 modifiée", question.getOpt1());
        assertEquals("Option 2 modifiée", question.getOpt2());
        assertEquals("Option 3 modifiée", question.getOpt3());
        assertEquals("Option 4 modifiée", question.getOpt4());
    }

    @Test
    public void testSetAnswers() {
        List<String> newAnswers = Arrays.asList("Réponse B");
        question.setAnswers(newAnswers);
        assertEquals(1, question.getAnswers().size());
        assertEquals("Réponse B", question.getAnswers().get(0));
    }

    @Test
    public void testJustification() {
        assertEquals("L'endométriose cause des douleurs pelviennes.", question.getJustif());
    }

    @Test
    public void testSetJustif() {
        question.setJustif("Nouvelle justification.");
        assertEquals("Nouvelle justification.", question.getJustif());
    }

    @Test
    public void testUserSelectedAnswer_default() {
        assertNull(question.getUserSelectedAnswer());
    }

    @Test
    public void testSetUserSelectedAnswer() {
        question.setUserSelectedAnswer("Réponse A");
        assertEquals("Réponse A", question.getUserSelectedAnswer());
    }

    @Test
    public void testUserAnswer_isCorrect() {
        question.setUserSelectedAnswer("Réponse A");
        assertTrue(question.getAnswers().contains(question.getUserSelectedAnswer()));
    }

    @Test
    public void testUserAnswer_isWrong() {
        question.setUserSelectedAnswer("Réponse B");
        assertFalse(question.getAnswers().contains(question.getUserSelectedAnswer()));
    }

    @Test
    public void testAnswers_containsMultipleCorrect() {
        assertTrue(question.getAnswers().contains("Réponse A"));
        assertTrue(question.getAnswers().contains("Réponse C"));
        assertFalse(question.getAnswers().contains("Réponse B"));
    }

    @Test
    public void testSetAnswers_empty() {
        question.setAnswers(Arrays.asList());
        assertEquals(0, question.getAnswers().size());
    }
}