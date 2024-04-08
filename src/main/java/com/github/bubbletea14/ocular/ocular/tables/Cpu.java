package com.github.bubbletea14.ocular.ocular.tables;
import java.lang.annotation.Inherited;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class Cpu {
    @Id
    private LocalDateTime dateTime;
    
    private String processorType;
    private int processorSpeed;
    private Long count;
    private LocalDateTime upTime;

    public Cpu() {

    }

    public Cpu(String processorType, 
                int processorSpeed,
                Long count,
                LocalDateTime upTime){
        this.processorType = processorType;
        this.processorSpeed = processorSpeed;
        this.count = count;
        this.upTime = upTime;
    }
    
    // Getter and Setter
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getProcessorType(){
        return processorType;
    }

    public void setProcessorType(String processorType){
        this.processorType = processorType;
    }

    public int getProcessorSpeed(){
        return processorSpeed;
    }

    public void setProcessorSpeed(int processorSpeed) {
        this.processorSpeed = processorSpeed;
    }

    public float getCount() {
        return count;
    }

    public void setCount(long count){
        this.count = count;
    }

    public LocalDateTime getUptime(){
        return upTime;
    }

    public void setUpTime(LocalDateTime upTime) {
        this.upTime = upTime;
    }

    @Override
    public String toString () {
        return "Cpu {" + 
                "ProcessorType=" + processorType +
                ", ProcessorSpeed=" + processorSpeed + 
                ", Count='" + count + '\'' + 
                ", upTime='" + upTime + '\'' + 
                '}';
    }
}

