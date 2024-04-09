package com.github.bubbletea14.ocular.ocular;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.github.bubbletea14.ocular.ocular.tables.*;
import java.time.LocalDate;
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
        memory.setMemoryUsage(50.5);
        memory.setMemorySpeed(1600);
        memory.setMemAvailable(2048);
        
        assertEquals(currentTime, memory.getDateTime());
        assertEquals(50.5, memory.getMemoryUsage());
        assertEquals(1600, memory.getMemorySpeed());
        assertEquals(2048, memory.getMemAvailable());
    }

    @Test
    public void CpuTest() {
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

    @Test
    public void ProcessesTest() {
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