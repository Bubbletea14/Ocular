package com.github.bubbletea14.ocular.ocular.tables;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class Cpu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateTime;
    private String processorType;
    private double processorSpeed;
    private Long count;
    private Long processorUptime;
    private LocalDateTime upTime;
    private double processorUsage;

    public Cpu() {

    }

    public Cpu(long id,
                String processorType, 
                double processorSpeed,
                double processorUsage,
                Long count,
                LocalDateTime upTime){
        this.id = id;
        this.processorType = processorType;
        this.processorSpeed = processorSpeed;
        this.processorUsage = processorUsage;
        this.count = count;
        this.upTime = upTime;
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

    public void setId(Long newId) {
        this.id = newId;
    }

    public String getProcessorType(){
        return processorType;
    }

    public void setProcessorType(String processorType){
        this.processorType = processorType;
    }

    public double getProcessorSpeed(){
        return processorSpeed;
    }

    public void setProcessorSpeed(double processorSpeed) {
        this.processorSpeed = processorSpeed;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count){
        this.count = count;
    }

    public LocalDateTime getUpTime(){
        return upTime;
    }

    public void setUpTime(LocalDateTime upTime) {
        this.upTime = upTime;
    }

    public Long getProcessorUptime() {
        return processorUptime;
    }

    public void setProcessorUptime(Long processorUptime) {
        this.processorUptime = processorUptime;
    }

    public double getProcessorUsage() {
        return processorUsage;
    }

    public void setProcessorUsage(double processorUsage) {
        this.processorUsage = processorUsage;
    }

    @Override
    public String toString () {
        return "Cpu {" + 
                "CpuID=" + id + 
                "ProcessorType=" + processorType +
                ", ProcessorSpeed=" + processorSpeed + 
                ", Count='" + count + '\'' + 
                ", upTime='" + upTime + '\'' + 
                '}';
    }
}

