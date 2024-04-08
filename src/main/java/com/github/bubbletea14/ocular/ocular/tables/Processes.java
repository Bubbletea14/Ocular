package com.github.bubbletea14.ocular.ocular.tables;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class Processes {
    @Id
    private LocalDateTime dateTime;

    private Long pId;
    private String name;
    private int cpuPercent;
    private int memPercent;

    //Construcor
    public Processes() {

    }

    //Construcor
    public Processes(Long pId, String name) {
        this.pId = pId;
        this.name = name;
    }

    //Construcor
    public Processes (Long pId, String name, int cpuPercent, int memPercent) {
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
    
    public Long getPId(){
        return pId;
    }

    public void setPId(Long newPid){
        this.pId = newPid;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getCpuPercent() {
        return cpuPercent;
    }

    public void setCpuPercent(int cpuPercent) {
        this.cpuPercent = cpuPercent;
    }

    public int getMemPercent() {
        return memPercent;
    }

    public void setMemPercent(int memPercent) {
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
