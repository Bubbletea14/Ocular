package com.mscs.project.ocular.ocular.Tables;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Memory {
    @Id
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime time;
    
    private double memoryUsage;
    private int memorySpeed;
    private double memAvailable;

    public LocalDateTime getTime() {
        return time;
    }
    public void setTime(LocalDateTime time) {
        this.time = time;
    }
    public double getMemoryUsage() {
        return memoryUsage;
    }
    public void setMemoryUsage(double memoryUsage) {
        this.memoryUsage = memoryUsage;
    }
    public int getMemorySpeed() {
        return memorySpeed;
    }
    public void setMemorySpeed(int memorySpeed) {
        this.memorySpeed = memorySpeed;
    }
    public double getMemAvailable() {
        return memAvailable;
    }
    public void setMemAvailable(double memAvailable) {
        this.memAvailable = memAvailable;
    }
}
