package com.example.paincare.Models;

import com.spmenais.paincare.Models.Users;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class UsersTest {

    private Users user;

    @Before
    public void setUp() {
        user = new Users("uid001", "Fatiha", "fatiha@email.com", "https://example.com/img.jpg");
    }

    @Test
    public void testUserCreation_fullConstructor() {
        assertEquals("uid001", user.getUserId());
        assertEquals("Fatiha", user.getName());
        assertEquals("fatiha@email.com", user.getEmail());
        assertEquals("https://example.com/img.jpg", user.getImageUrl());
    }

    @Test
    public void testUserCreation_withIdAndName() {
        Users u = new Users("uid002", "Sara");
        assertEquals("uid002", u.getUserId());
        assertEquals("Sara", u.getName());
        assertNull(u.getEmail());
        assertNull(u.getImageUrl());
    }

    @Test
    public void testUserCreation_withIdNameEmail() {
        Users u = new Users("uid003", "Amina", "amina@email.com");
        assertEquals("uid003", u.getUserId());
        assertEquals("Amina", u.getName());
        assertEquals("amina@email.com", u.getEmail());
        assertNull(u.getImageUrl());
    }

    @Test
    public void testDefaultConstructor() {
        Users u = new Users();
        assertNull(u.getUserId());
        assertNull(u.getName());
        assertNull(u.getEmail());
        assertNull(u.getImageUrl());
    }

    @Test
    public void testSetUserId() {
        user.setUserId("newUid999");
        assertEquals("newUid999", user.getUserId());
    }

    @Test
    public void testSetName() {
        user.setName("Nouveau Nom");
        assertEquals("Nouveau Nom", user.getName());
    }

    @Test
    public void testSetEmail() {
        user.setEmail("nouveau@email.com");
        assertEquals("nouveau@email.com", user.getEmail());
    }

    @Test
    public void testSetImageUrl() {
        user.setImageUrl("https://example.com/newimg.jpg");
        assertEquals("https://example.com/newimg.jpg", user.getImageUrl());
    }

    @Test
    public void testSetImageUrl_toNull() {
        user.setImageUrl(null);
        assertNull(user.getImageUrl());
    }

    @Test
    public void testEmail_format_isValid() {
        String email = user.getEmail();
        assertTrue(email.contains("@"));
        assertTrue(email.contains("."));
    }

    @Test
    public void testUserId_notEmpty() {
        assertFalse(user.getUserId().isEmpty());
    }

    @Test
    public void testName_notEmpty() {
        assertFalse(user.getName().isEmpty());
    }
}
