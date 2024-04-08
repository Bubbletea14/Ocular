package com.github.bubbletea14.ocular.ocular;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.github.bubbletea14.ocular.ocular.tables.Cpu;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CpuTest {

    private Cpu cpu = new Cpu();

    @Test
    public void testGettersAndSetters() {
        cpu.setDateTime(LocalDateTime.now());
        cpu.setProcessorType("ProcessorType");
        cpu.setProcessorSpeed(2000);
        cpu.setCount(4);
        //cpu.setUpTime(LocalDateTime.now());
        
        assertEquals(LocalDateTime.now(), cpu.getDateTime());
        assertEquals("ProcessorType", cpu.getProcessorType());
        assertEquals(2000, cpu.getProcessorSpeed());
        assertEquals(4, cpu.getCount());
        //assertEquals(LocalDateTime.now(), cpu.getUptime());
    }

}
