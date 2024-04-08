package com.github.bubbletea14.ocular.ocular;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.bubbletea14.ocular.ocular.tables.*;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DatabaseIntegrationTest {

    @Autowired
    private CPURepository cpuRepository;

    @Autowired
    private MemoryRepository memoryRepository;

    @Autowired
    private ProcessesRepository processesRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Test
    public void testCpuDataPersistence() {
        Cpu cpu = new Cpu();
        cpu.setDateTime(LocalDateTime.now());
        cpu.setProcessorType("TestProcessor");
        cpu.setProcessorSpeed(2500);
        cpu.setCount(8);
        cpu.setUpTime(LocalDateTime.now());

        cpuRepository.save(cpu);

        Cpu savedCpu = cpuRepository.findByDateTime(cpu.getDateTime());
        assertEquals("TestProcessor", savedCpu.getProcessorType());
        assertEquals(2500, savedCpu.getProcessorSpeed());
        // Add more assertions to verify other fields
    }

    @Test
    public void testMemoryDataPersistence() {
        Memory memory = new Memory();
        memory.setDateTime(LocalDateTime.now());
        memory.setMemoryUsage(50.5);
        memory.setMemorySpeed(1600);
        memory.setMemAvailable(2048);

        memoryRepository.save(memory);

        Memory savedMemory = memoryRepository.findByDateTime(memory.getDateTime());
        assertEquals(50.5, savedMemory.getMemoryUsage());
        assertEquals(1600, savedMemory.getMemorySpeed());
        // Add more assertions to verify other fields
    }

    @Test
    public void testProcessesDataPersistence() {
        Processes processes = new Processes();
        processes.setDateTime(LocalDateTime.now());
        processes.setPId(123L);
        processes.setName("TestProcess");
        processes.setCpuPercent(50);
        processes.setMemPercent(30);

        processesRepository.save(processes);

        Processes savedProcesses = processesRepository.findByDateTime(processes.getDateTime());
        assertEquals(123L, savedProcesses.getPId());
        assertEquals("TestProcess", savedProcesses.getName());
        // Add more assertions to verify other fields
    }

    @Test
    public void testUsersDataPersistence() {
        Users user = new Users();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user.setEmail("test@example.com");

        //usersRepository.save(user);

        //Users savedUser = usersRepository.findByusername(user.getUsername());
        //assertEquals("testUser", savedUser.getUsername());
        //assertEquals("testPassword", savedUser.getPassword());
        //assertEquals("test@example.com", savedUser.getEmail());
        // Add more assertions to verify other fields
    }
}
