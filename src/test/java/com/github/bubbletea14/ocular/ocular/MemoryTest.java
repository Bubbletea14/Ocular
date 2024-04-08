package com.github.bubbletea14.ocular.ocular;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.github.bubbletea14.ocular.ocular.tables.Memory;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MemoryTest {

    private Memory memory = new Memory();

    @Test
    public void testGettersAndSetters() {
        final LocalDateTime currentTime = LocalDateTime.now();
        memory.setDateTime(currentTime);
        memory.setMemoryUsage(50.5);
        memory.setMemorySpeed(1600);
        memory.setMemAvailable(2048);
        
        assertEquals(currentTime, memory.getDateTime());
        assertEquals(50.5, memory.getMemoryUsage());
        assertEquals(1600, memory.getMemorySpeed());
        assertEquals(2048, memory.getMemAvailable());
    }

}
