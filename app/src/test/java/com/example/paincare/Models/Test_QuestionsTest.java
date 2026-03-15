package com.example.paincare.Models;

import com.spmenais.paincare.Models.Test_Questions;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test_QuestionsTest {

    private Test_Questions testQuestion;

    @Before
    public void setUp() {
        List<String> options = Arrays.asList("Jamais", "Rarement", "Souvent", "Toujours");
        Map<String, Long> scores = new HashMap<>();
        scores.put("Jamais", 0L);
        scores.put("Rarement", 1L);
        scores.put("Souvent", 2L);
        scores.put("Toujours", 3L);

        testQuestion = new Test_Questions(
                "Avez-vous des douleurs pelviennes ?",
                options,
                scores,
                "single"
        );
    }

    @Test
    public void testGetText() {
        assertEquals("Avez-vous des douleurs pelviennes ?", testQuestion.getText());
    }

    @Test
    public void testGetOptions_size() {
        assertEquals(4, testQuestion.getOptions().size());
    }

    @Test
    public void testGetOptions_content() {
        assertTrue(testQuestion.getOptions().contains("Jamais"));
        assertTrue(testQuestion.getOptions().contains("Toujours"));
    }

    @Test
    public void testGetOptionScores() {
        assertEquals(Long.valueOf(0L), testQuestion.getOptionScores().get("Jamais"));
        assertEquals(Long.valueOf(3L), testQuestion.getOptionScores().get("Toujours"));
    }

    @Test
    public void testGetType() {
        assertEquals("single", testQuestion.getType());
    }

    @Test
    public void testDefaultConstructor() {
        Test_Questions q = new Test_Questions();
        assertNull(q.getText());
        assertNull(q.getOptions());
        assertNull(q.getOptionScores());
        assertNull(q.getType());
    }

    @Test
    public void testSetText() {
        testQuestion.setText("Nouvelle question");
        assertEquals("Nouvelle question", testQuestion.getText());
    }

    @Test
    public void testSetType() {
        testQuestion.setType("multiple");
        assertEquals("multiple", testQuestion.getType());
    }

    @Test
    public void testSetOptions() {
        List<String> newOptions = Arrays.asList("Oui", "Non");
        testQuestion.setOptions(newOptions);
        assertEquals(2, testQuestion.getOptions().size());
    }

    @Test
    public void testSetOptionScores() {
        Map<String, Long> newScores = new HashMap<>();
        newScores.put("Oui", 5L);
        newScores.put("Non", 0L);
        testQuestion.setOptionScores(newScores);
        assertEquals(Long.valueOf(5L), testQuestion.getOptionScores().get("Oui"));
    }

    @Test
    public void testScoreCalculation_sumAllScores() {
        long total = 0;
        for (Long score : testQuestion.getOptionScores().values()) {
            total += score;
        }
        assertEquals(6L, total); // 0+1+2+3 = 6
    }

    @Test
    public void testScoreCalculation_maxScore() {
        long max = 0;
        for (Long score : testQuestion.getOptionScores().values()) {
            if (score > max) max = score;
        }
        assertEquals(3L, max);
    }

    @Test
    public void testOptionScores_allOptionsHaveScore() {
        for (String option : testQuestion.getOptions()) {
            assertTrue(testQuestion.getOptionScores().containsKey(option));
        }
    }
}
