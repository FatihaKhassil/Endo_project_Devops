package com.example.paincare.Models;

import com.spmenais.paincare.Models.DataModel;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class DataModelTest {

    private DataModel dataModel;

    @Before
    public void setUp() {
        List<String> options = Arrays.asList("Option A", "Option B", "Option C");
        dataModel = new DataModel(options, "Symptômes douloureux");
    }

    @Test
    public void testGetTitle() {
        assertEquals("Symptômes douloureux", dataModel.getTitle());
    }

    @Test
    public void testGetOptionsList_size() {
        assertEquals(3, dataModel.getOptionsList().size());
    }

    @Test
    public void testGetOptionsList_content() {
        assertTrue(dataModel.getOptionsList().contains("Option A"));
        assertTrue(dataModel.getOptionsList().contains("Option B"));
        assertTrue(dataModel.getOptionsList().contains("Option C"));
    }

    @Test
    public void testExpandable_defaultFalse() {
        assertFalse(dataModel.isExpandable());
    }

    @Test
    public void testSetExpandable_toTrue() {
        dataModel.setExpandable(true);
        assertTrue(dataModel.isExpandable());
    }

    @Test
    public void testSetExpandable_backToFalse() {
        dataModel.setExpandable(true);
        dataModel.setExpandable(false);
        assertFalse(dataModel.isExpandable());
    }

    @Test
    public void testGetNestedList_emptyByDefault() {
        assertNotNull(dataModel.getNestedList());
        assertEquals(0, dataModel.getNestedList().size());
    }

    @Test
    public void testGetSelectedPositions_emptyByDefault() {
        assertNotNull(dataModel.getSelectedPositions());
        assertEquals(0, dataModel.getSelectedPositions().size());
    }

    @Test
    public void testSetSelectedPositions() {
        List<Integer> positions = Arrays.asList(0, 2);
        dataModel.setSelectedPositions(positions);
        assertEquals(2, dataModel.getSelectedPositions().size());
        assertEquals(Integer.valueOf(0), dataModel.getSelectedPositions().get(0));
        assertEquals(Integer.valueOf(2), dataModel.getSelectedPositions().get(1));
    }

    @Test
    public void testGetSelectedOptions_emptyByDefault() {
        assertNotNull(dataModel.getSelectedOptions());
        assertEquals(0, dataModel.getSelectedOptions().size());
    }

    @Test
    public void testSetSelectedOptions() {
        List<String> selected = Arrays.asList("Option A", "Option C");
        dataModel.setSelectedOptions(selected);
        assertEquals(2, dataModel.getSelectedOptions().size());
        assertTrue(dataModel.getSelectedOptions().contains("Option A"));
    }

    @Test
    public void testTitle_notNull() {
        assertNotNull(dataModel.getTitle());
    }

    @Test
    public void testOptionsList_notNull() {
        assertNotNull(dataModel.getOptionsList());
    }

    @Test
    public void testDataModel_withSingleOption() {
        List<String> single = Arrays.asList("Seule option");
        DataModel dm = new DataModel(single, "Titre test");
        assertEquals(1, dm.getOptionsList().size());
        assertFalse(dm.isExpandable());
    }
}
