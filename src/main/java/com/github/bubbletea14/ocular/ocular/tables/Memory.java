package com.github.bubbletea14.ocular.ocular.tables;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Memory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateTime;
    private float totalMemory;
    private float freeMemory;
    private float usedMemory;
    private float memorySpeed;
    private float memoryUsagePercentage;

    public Memory() {

    }

    // Constructor
    public Memory (long id, 
                    float totalMemory, 
                    float freeMemory,
                    float usedMemory,
                    float memorySpeed,
                    float memoryUsagePercentage) {
        this.id = id;
        this.totalMemory = totalMemory;
        this.freeMemory = freeMemory;
        this.usedMemory = usedMemory;
        this.memorySpeed = memorySpeed;
        this.memoryUsagePercentage = memoryUsagePercentage;
    }

    // Getter and Setter
    // Getter methods to access the state from other classes
    // Setter methods to modify the state from other classes
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getTotalMemory() {
        return totalMemory;
    }
    
    public void setTotalMemory(float totalMemory){
        this.totalMemory = totalMemory;
    }

    public float getFreeMemory() {
        return freeMemory;
    }
    
    public void setFreeMemory(float freeMemory){
        this.freeMemory = freeMemory;
    }

    public float getUsedMemory() {
        return usedMemory;
    }
    
    public void setUsedMemory(float usedMemory){
        this.usedMemory = usedMemory;
    }

    public float getMemorySpeed(){
        return memorySpeed;
    }

    public void setMemorySpeed(float memorySpeed){
        this.memorySpeed = memorySpeed;
    }

    public float getMemoryUsagePercentage() {
        return memoryUsagePercentage;
    }
    
    public void setMemoryUsagePercentage(float memoryUsagePercentage){
        this.memoryUsagePercentage = memoryUsagePercentage;
    }
}


