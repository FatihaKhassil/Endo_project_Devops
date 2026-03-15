package com.example.paincare.Models;
import com.spmenais.paincare.Models.Questions;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

// ============================================================
// Tests pour Questions (FAQ)
// ============================================================
public class QuestionsTest {

    private Questions question;

    @Before
    public void setUp() {
        question = new Questions(
                "Qu'est-ce que l'endométriose ?",
                "C'est une maladie chronique qui affecte les femmes.",
                false
        );
    }

    @Test
    public void testGetTitle() {
        assertEquals("Qu'est-ce que l'endométriose ?", question.getTitle());
    }

    @Test
    public void testGetAnswer() {
        assertEquals("C'est une maladie chronique qui affecte les femmes.", question.getAnswer());
    }

    @Test
    public void testExpandable_defaultFalse() {
        assertFalse(question.isExpandable());
    }

    @Test
    public void testSetExpandable_toTrue() {
        question.setExpandable(true);
        assertTrue(question.isExpandable());
    }

    @Test
    public void testSetExpandable_toggleBackToFalse() {
        question.setExpandable(true);
        question.setExpandable(false);
        assertFalse(question.isExpandable());
    }

    @Test
    public void testToString_containsTitle() {
        String result = question.toString();
        assertTrue(result.contains("Qu'est-ce que l'endométriose ?"));
    }

    @Test
    public void testToString_containsAnswer() {
        String result = question.toString();
        assertTrue(result.contains("C'est une maladie chronique qui affecte les femmes."));
    }

    @Test
    public void testCreation_withExpandableTrue() {
        Questions q = new Questions("Titre", "Réponse", true);
        assertTrue(q.isExpandable());
    }

    @Test
    public void testTitle_notNull() {
        assertNotNull(question.getTitle());
    }

    @Test
    public void testAnswer_notNull() {
        assertNotNull(question.getAnswer());
    }
}
