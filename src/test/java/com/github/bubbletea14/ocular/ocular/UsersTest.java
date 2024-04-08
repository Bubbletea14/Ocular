package com.github.bubbletea14.ocular.ocular;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.github.bubbletea14.ocular.ocular.tables.Users;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UsersTest {

    private Users user = new Users();

    @Test
    public void testGettersAndSetters() {
        user.setId(1L);
        user.setUsername("username");
        user.setPassword("password");
        user.setDob(LocalDate.of(2000, 1, 1));
        user.setEmail("user@example.com");
        
        assertEquals(1L, user.getId());
        assertEquals("username", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals(LocalDate.of(2000, 1, 1), user.getDob());
        assertEquals("user@example.com", user.getEmail());
    }
}
