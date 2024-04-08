package com.github.bubbletea14.ocular.ocular.tables;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Memory {
    @Id
    private LocalDateTime dateTime;

    private double memoryUsage;
    private double memorySpeed;
    private double memAvailable;

    public Memory() {

    }

    // Constructor
    public Memory (double memoryUsage, double memorySpeed) {
        this.memoryUsage = memoryUsage;
        this.memorySpeed = memorySpeed;
    }

    //Getter and Setter
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    
    public double getMemoryUsage() {
        return memoryUsage;
    }
    
    public void setMemoryUsage(double memoryUsage){
        this.memoryUsage = memoryUsage;
    }

    public double getMemorySpeed() {
        return memorySpeed;
    }
    
    public void setMemorySpeed(double memorySpeed){
        this.memorySpeed = memorySpeed;
    }

    public double getMemAvailable() {
        return memAvailable;
    }

    public void setMemAvailable(double memAvailable) {
        this.memAvailable = memAvailable;
    }
}


