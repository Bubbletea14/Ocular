package com.github.bubbletea14.ocular.ocular;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.github.bubbletea14.ocular.ocular.tables.*;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TableObjectTests {
    @Autowired
    private Memory memory;
    @Autowired
    private Cpu cpu;
    @Autowired
    private Processes processes;
    @Autowired
    private Users user;

    @Test
    public void MemoryTest() {
        final LocalDateTime currentTime = LocalDateTime.now();
        memory.setDateTime(currentTime);
        memory.setMemoryUsagePercentage(50.5f);
        memory.setMemorySpeed(1600f);
        memory.setFreeMemory(2048f);
        
        assertEquals(currentTime, memory.getDateTime());
        assertEquals(50.5f, memory.getMemoryUsagePercentage());
        assertEquals(1600f, memory.getMemorySpeed());
        assertEquals(2048f, memory.getFreeMemory());
    }

    @Test
    public void CpuTest() {
        final LocalDateTime time = LocalDateTime.now();
        cpu.setDateTime(time);
        cpu.setProcessorType("ProcessorType");
        cpu.setProcessorSpeed(2000);
        cpu.setCount(4);
        cpu.setProcessorUptime(1L);
        
        assertEquals(time, cpu.getDateTime());
        assertEquals("ProcessorType", cpu.getProcessorType());
        assertEquals(2000, cpu.getProcessorSpeed());
        assertEquals(4, cpu.getCount());
        assertEquals(1L, cpu.getProcessorUptime());
    }

    @Test
    public void ProcessesTest() {
        LocalDateTime currentTime = LocalDateTime.now();
        processes.setDateTime(currentTime);
        processes.setPId(123);
        processes.setName("ProcessName");
        processes.setCpuPercent(50);
        processes.setMemPercent(30);
        
        assertEquals(currentTime, processes.getDateTime());
        assertEquals(123L, processes.getPId());
        assertEquals("ProcessName", processes.getName());
        assertEquals(50, processes.getCpuPercent());
        assertEquals(30, processes.getMemPercent());
    }

    @Test
    public void testGettersAndSetters() {
        user.setId(1L);
        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("user@example.com");
        
        assertEquals(1L, user.getId());
        assertEquals("username", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals("user@example.com", user.getEmail());
    }
}