package com.github.bubbletea14.ocular.ocular.tables;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class Processes {
    @Id
    private LocalDateTime dateTime;

    private int pId;
    private int parentPId;
    private String name;
    private double cpuPercent;
    private double memPercent;

    //Construcor
    public Processes() {

    }

    //Construcor
    public Processes(int pId, String name) {
        this.pId = pId;
        this.name = name;
    }

    //Construcor
    public Processes (int pId, String name, double cpuPercent, double memPercent) {
        this.pId = pId;
        this.name = name;
        this.cpuPercent = cpuPercent;
        this.memPercent = memPercent;
    }

    // Getter and Setter
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    
    public int getPId(){
        return pId;
    }

    public void setPId(int newPid){
        this.pId = newPid;
    }

    public int getParentPId() {
        return parentPId;
    }

    public void setParentPId(int parentPId) {
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

    @Override
    public String toString () {
        return "Processes {" + 
                "PID=" + pId +
                ", Name=" + name + 
                '}';
    }
}
