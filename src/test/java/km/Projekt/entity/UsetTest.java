package km.Projekt.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsetTest {

    @Test
    public void testSetAndGetName() {
        User user = new User();

        user.setName("TestUser");
        assertEquals("TestUser", user.getName());
    }

    @Test
    public void testSetAndGetPassword() {
        User user = new User();

        user.setPassword("password123");
        assertEquals("password123", user.getPassword());
    }

    @Test
    public void testSetAndGetLogin() {
        User user = new User();

        user.setLogin("TestLogin");
        assertEquals("TestLogin", user.getLogin());
    }

    @Test
    public void testSetAndGetSurname() {
        User user = new User();

        user.setSurname("TestSurname");
        assertEquals("TestSurname", user.getSurname());
    }

    @Test
    public void testDisplayInfo() {
        User user = new User();

        user.setUserid(10L);
        user.setName("TestName");

        user.displayInfo();
    }
}
