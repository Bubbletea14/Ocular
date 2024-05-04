package com.github.bubbletea14.ocular.ocular;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.Random;
import com.github.bubbletea14.ocular.ocular.tables.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class DatabaseIntegrationTest {
    Random random = new Random();

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
        cpu.setProcessorType("TestProcessor" + random.nextInt(100));
        cpu.setProcessorSpeed(random.nextInt(4001) + 1000);
        cpu.setCount(random.nextInt(16) + 1);
        cpu.setUpTime(LocalDateTime.now());
        cpu.setProcessorUptime(1L);

        cpuRepository.save(cpu);

        Cpu savedCpu = cpuRepository.findByDateTime(cpu.getDateTime());
        assertEquals(cpu.getProcessorType(), savedCpu.getProcessorType());
        assertEquals(cpu.getProcessorSpeed(), savedCpu.getProcessorSpeed());
        assertEquals(cpu.getCount(), savedCpu.getCount());
        assertEquals(cpu.getProcessorUptime(), savedCpu.getProcessorUptime());
        assertEquals(cpu.getUpTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")), savedCpu.getUpTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")));
    }

    @Test
    public void testMemoryDataPersistence() {
        Memory memory = new Memory();
        memory.setDateTime(LocalDateTime.now());
        memory.setMemoryUsagePercentage(50.5f);
        memory.setMemorySpeed(1600f);
        memory.setFreeMemory(2048f);

        memoryRepository.save(memory);

        Memory savedMemory = memoryRepository.findByDateTime(memory.getDateTime());
        assertEquals(memory.getMemoryUsagePercentage(), savedMemory.getMemoryUsagePercentage());
        assertEquals(memory.getMemorySpeed(), savedMemory.getMemorySpeed());
        assertEquals(memory.getFreeMemory(), savedMemory.getFreeMemory());
    }

    @Test
    public void testProcessesDataPersistence() {
        Processes processes = new Processes();
        processes.setDateTime(LocalDateTime.now());
        processes.setPId(random.nextInt(1000) + 1);
        processes.setName("TestProcess - 1" + random.nextInt(100));
        processes.setCpuPercent(random.nextInt(101));
        processes.setMemPercent(random.nextInt(101));

        processesRepository.save(processes);

        Optional<Processes> optionalProcess = processesRepository.findBypId(processes.getPId());
        if(optionalProcess.isPresent()) {
            Processes savedProcesses = optionalProcess.get();
            assertEquals(processes.getPId(), savedProcesses.getPId());
            assertEquals(processes.getName(), savedProcesses.getName());
            assertEquals(processes.getCpuPercent(), savedProcesses.getCpuPercent());
            assertEquals(processes.getMemPercent(), savedProcesses.getMemPercent());
            assertEquals(processes.getCpuPercent(), savedProcesses.getCpuPercent());
        } else{
            fail("Failed to retrieve the saved process from the database.");
        }
    }

    @Test
    public void testUsersDataPersistence() {
        Users user = new Users();
        String userName = "testUser-" + UUID.randomUUID().toString().substring(0,8);
        user.setUsername(userName);
        user.setPassword("testPassword");
        user.setEmail("test@example.com");

        usersRepository.save(user);

        Users savedUser = usersRepository.findByusername(user.getUsername());
        assertEquals(user.getUsername(), savedUser.getUsername());
        assertEquals(user.getPassword(), savedUser.getPassword());
        assertEquals(user.getEmail(), savedUser.getEmail());
    }
}
