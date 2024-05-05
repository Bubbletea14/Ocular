package com.github.bubbletea14.ocular.ocular.tables;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
public class Processes {
    @Id
    @JsonProperty("pId")
    private Long pId;
    private LocalDateTime dateTime;
    private long parentPId;
    private String name;
    private double cpuPercent;
    private double memPercent;

    public Processes() {

    }

    // Construcor
    public Processes(long pId, 
                    long parentPId, 
                    String name,
                    double cpuPercent,
                    double memPercent) {
        this.pId = pId;
        this.parentPId = parentPId;
        this.name = name;
        this.cpuPercent = cpuPercent;
        this.memPercent = memPercent;
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
    
    public long getPId(){
        return pId;
    }

    public void setPId(long newPid){
        this.pId = newPid;
    }

    public long getParentPId() {
        return parentPId;
    }

    public void setParentPId(long parentPId) {
        this.parentPId = parentPId;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public double getCpuPercent() {
        return cpuPercent;
    }

    public void setCpuPercent(double cpuPercent) {
        this.cpuPercent = cpuPercent;
    }

    public double getMemPercent() {
        return memPercent;
    }

    public void setMemPercent(double memPercent) {
        this.memPercent = memPercent;
    }
}
