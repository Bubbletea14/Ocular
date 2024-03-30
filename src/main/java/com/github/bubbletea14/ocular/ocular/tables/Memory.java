package com.github.bubbletea14.ocular.ocular.tables;

public class Memory {
    private float memoryUsage;
    private float memorySpeed;

    // Constructor
    public Memory (float memoryUsage, float memorySpeed) {
        this.memoryUsage = memoryUsage;
        this.memorySpeed = memorySpeed;
    }

    //Getter and Setter
    public float getMemoryUsage() {
        return memoryUsage;
    }
    
    public void setMemoryUsage(float memoryUsage){
        this.memoryUsage = memoryUsage;
    }

    public float getMemorySpeed() {
        return memorySpeed;
    }
    
    public void setMemorySpeed(float memorySpeed){
        this.memorySpeed = memorySpeed;
    }
}


