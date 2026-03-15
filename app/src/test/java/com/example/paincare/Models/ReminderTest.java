package com.example.paincare.Models;

import com.spmenais.paincare.Models.Reminder;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class ReminderTest {

    private Reminder reminder;

    @Before
    public void setUp() {
        boolean[] days = {true, false, true, false, true, false, false};
        reminder = new Reminder("id123", "Médication", "08:30", true, days);
    }

    @Test
    public void testReminderCreation_withFullConstructor() {
        assertEquals("id123", reminder.getId());
        assertEquals("Médication", reminder.getTitle());
        assertEquals("08:30", reminder.getTime());
        assertTrue(reminder.isActive());
    }

    @Test
    public void testReminderCreation_withShortConstructor() {
        boolean[] days = {false, true, false, true, false, false, false};
        Reminder r = new Reminder("Vitamines", "09:00", days);
        assertEquals("Vitamines", r.getTitle());
        assertEquals("09:00", r.getTime());
        assertNotNull(r.getRepeatDays());
    }

    @Test
    public void testSetTitle() {
        reminder.setTitle("Nouveau titre");
        assertEquals("Nouveau titre", reminder.getTitle());
    }

    @Test
    public void testSetTime() {
        reminder.setTime("14:45");
        assertEquals("14:45", reminder.getTime());
    }

    @Test
    public void testSetActive_toFalse() {
        reminder.setActive(false);
        assertFalse(reminder.isActive());
    }

    @Test
    public void testSetActive_toTrue() {
        reminder.setActive(false);
        reminder.setActive(true);
        assertTrue(reminder.isActive());
    }

    @Test
    public void testSetId() {
        reminder.setId("newId456");
        assertEquals("newId456", reminder.getId());
    }

    @Test
    public void testRepeatDays_correctLength() {
        assertEquals(7, reminder.getRepeatDays().length);
    }

    @Test
    public void testRepeatDays_values() {
        boolean[] days = reminder.getRepeatDays();
        assertTrue(days[0]);   // Monday
        assertFalse(days[1]);  // Tuesday
        assertTrue(days[2]);   // Wednesday
        assertFalse(days[3]);  // Thursday
        assertTrue(days[4]);   // Friday
        assertFalse(days[5]);  // Saturday
        assertFalse(days[6]);  // Sunday
    }

    @Test
    public void testSetRepeatDays() {
        boolean[] newDays = {false, false, false, false, false, true, true};
        reminder.setRepeatDays(newDays);
        assertTrue(reminder.getRepeatDays()[5]);
        assertTrue(reminder.getRepeatDays()[6]);
        assertFalse(reminder.getRepeatDays()[0]);
    }

    @Test
    public void testTimeFormat_isValidHHmm() {
        String time = reminder.getTime();
        assertTrue(time.matches("\\d{2}:\\d{2}"));
    }

    @Test
    public void testReminderWithMidnightTime() {
        boolean[] days = {true, true, true, true, true, true, true};
        Reminder r = new Reminder("id2", "Test minuit", "00:00", true, days);
        assertEquals("00:00", r.getTime());
    }

    @Test
    public void testReminderWithLateTime() {
        boolean[] days = {false, false, false, false, false, false, false};
        Reminder r = new Reminder("id3", "Test tard", "23:59", false, days);
        assertEquals("23:59", r.getTime());
        assertFalse(r.isActive());
    }

    @Test
    public void testActiveDaysCount() {
        boolean[] days = reminder.getRepeatDays();
        int count = 0;
        for (boolean d : days) if (d) count++;
        assertEquals(3, count); // Lundi, Mercredi, Vendredi
    }
}
