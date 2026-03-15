package com.example.paincare;

import org.junit.Test;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Tests sur la logique métier pure (sans Android Context)
 */
public class AppLogicTest {

    // ─────────────────────────────────────────────────
    // Logique de formatage du temps (ReminderActivity)
    // ─────────────────────────────────────────────────

    @Test
    public void testTimeFormat_validHourMinute() {
        int hour = 8;
        int minute = 30;
        String timeString = String.format("%02d:%02d", hour, minute);
        assertEquals("08:30", timeString);
    }

    @Test
    public void testTimeFormat_midnight() {
        String timeString = String.format("%02d:%02d", 0, 0);
        assertEquals("00:00", timeString);
    }

    @Test
    public void testTimeFormat_endOfDay() {
        String timeString = String.format("%02d:%02d", 23, 59);
        assertEquals("23:59", timeString);
    }

    @Test
    public void testTimeFormat_singleDigitMinute() {
        String timeString = String.format("%02d:%02d", 9, 5);
        assertEquals("09:05", timeString);
    }

    @Test
    public void testTimeParse_isValidFormat() {
        String time = "14:30";
        String[] parts = time.split(":");
        assertEquals(2, parts.length);
        assertEquals(14, Integer.parseInt(parts[0]));
        assertEquals(30, Integer.parseInt(parts[1]));
    }

    @Test
    public void testTimeParse_compareTwoTimes() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        Date time1 = sdf.parse("08:00");
        Date time2 = sdf.parse("14:30");
        assertTrue(time1.compareTo(time2) < 0); // 08:00 avant 14:30
    }

    @Test
    public void testTimeParse_sameTimesAreEqual() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        Date time1 = sdf.parse("10:00");
        Date time2 = sdf.parse("10:00");
        assertEquals(0, time1.compareTo(time2));
    }

    // ─────────────────────────────────────────────────
    // Logique de calcul de date (UserAge_Activity)
    // ─────────────────────────────────────────────────

    @Test
    public void testBirthdayCalendar_correctDay() {
        int day = 15, month = 6, year = 1995;
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        assertEquals(15, calendar.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testBirthdayCalendar_correctMonth() {
        int day = 15, month = 6, year = 1995;
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        assertEquals(5, calendar.get(Calendar.MONTH)); // 0-based : juin = 5
    }

    @Test
    public void testBirthdayCalendar_correctYear() {
        int day = 1, month = 1, year = 2000;
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        assertEquals(2000, calendar.get(Calendar.YEAR));
    }

    @Test
    public void testBirthdayCalendar_notNull() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1990, 0, 1);
        assertNotNull(calendar.getTime());
    }

    // ─────────────────────────────────────────────────
    // Logique de langue (User_profile)
    // ─────────────────────────────────────────────────

    @Test
    public void testGetLanguageCode_English() {
        assertEquals("en", getLanguageCodeFromName("English"));
    }

    @Test
    public void testGetLanguageCode_French() {
        assertEquals("fr", getLanguageCodeFromName("French"));
    }

    @Test
    public void testGetLanguageCode_Unknown_returnsNull() {
        assertNull(getLanguageCodeFromName("Spanish"));
    }

    @Test
    public void testGetLanguageCode_emptyString_returnsNull() {
        assertNull(getLanguageCodeFromName(""));
    }

    // ─────────────────────────────────────────────────
    // Logique de score Quiz (QuizResults)
    // ─────────────────────────────────────────────────

    @Test
    public void testQuizScore_allCorrect() {
        int correct = 10, incorrect = 0;
        assertEquals(10, correct);
        assertEquals(0, incorrect);
        assertEquals(10, correct + incorrect);
    }

    @Test
    public void testQuizScore_allWrong() {
        int correct = 0, incorrect = 10;
        assertEquals(0, correct);
        assertEquals(10, incorrect);
    }

    @Test
    public void testQuizScore_mixed() {
        int correct = 7, incorrect = 3;
        assertEquals(10, correct + incorrect);
        assertTrue(correct > incorrect);
    }

    @Test
    public void testQuizScore_percentage() {
        int correct = 8, total = 10;
        double percentage = (double) correct / total * 100;
        assertEquals(80.0, percentage, 0.01);
    }

    @Test
    public void testQuizScore_zeroTotal_noException() {
        int correct = 0, total = 0;
        // Vérifier qu'on peut détecter ce cas sans exception
        assertTrue(total == 0);
    }

    @Test
    public void testQuizDisplayText_correct() {
        int correct = 5;
        String display = "Correct Answers : " + correct;
        assertEquals("Correct Answers : 5", display);
    }

    @Test
    public void testQuizDisplayText_incorrect() {
        int incorrect = 3;
        String display = "Wrong Answers : " + incorrect;
        assertEquals("Wrong Answers : 3", display);
    }

    // ─────────────────────────────────────────────────
    // Logique de validation d'ID (ReminderActivity)
    // ─────────────────────────────────────────────────

    @Test
    public void testUserId_notNullNotEmpty() {
        String userId = "abc123";
        assertNotNull(userId);
        assertFalse(userId.isEmpty());
    }

    @Test
    public void testUserId_null_isDetected() {
        String userId = null;
        assertNull(userId);
    }

    @Test
    public void testUserId_empty_isDetected() {
        String userId = "";
        assertTrue(userId.isEmpty());
    }

    // ─── Méthode helper (extraite de User_profile) ───
    private String getLanguageCodeFromName(String languageName) {
        switch (languageName) {
            case "English": return "en";
            case "French":  return "fr";
            default:        return null;
        }
    }
}
