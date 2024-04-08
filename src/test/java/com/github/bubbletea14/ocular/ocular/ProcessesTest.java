package com.github.bubbletea14.ocular.ocular;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.github.bubbletea14.ocular.ocular.tables.Processes;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProcessesTest {

    private Processes processes = new Processes();

    @Test
    public void testGettersAndSetters() {
        LocalDateTime currentTime = LocalDateTime.now();
        processes.setDateTime(currentTime);
        processes.setPId(123L);
        processes.setName("ProcessName");
        processes.setCpuPercent(50);
        processes.setMemPercent(30);
        
        assertEquals(currentTime, processes.getDateTime());
        assertEquals(123L, processes.getPId());
        assertEquals("ProcessName", processes.getName());
        assertEquals(50, processes.getCpuPercent());
        assertEquals(30, processes.getMemPercent());
    }

}
