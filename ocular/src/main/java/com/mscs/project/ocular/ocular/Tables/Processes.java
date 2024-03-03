package com.mscs.project.ocular.ocular.Tables;

import java.time.LocalDateTime;

import jakarta.persistence.*;;

public class Processes {
    @Id
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateTime;

    private String processName;
    private int pId;
    private int cpuPercent;
    private int memPercent;

    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getProcessName() {
        return processName;
    }
    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public int getpId() {
        return pId;
    }
    public void setpId(int pId) {
        this.pId = pId;
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

}
