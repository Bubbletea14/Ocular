package com.mscs.project.ocular.ocular.Tables;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class CPU {
    @Id
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateTime;

    private double cpuUtilization;
    private double cpuSpeed;
    private LocalDateTime cpuUptime;
    private int cpuProcesses;
    private int cpuThreads;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;

    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public double getCpuUtilization() {
        return cpuUtilization;
    }
    public void setCpuUtilization(double cpuUtilization) {
        this.cpuUtilization = cpuUtilization;
    }

    public double getCpuSpeed() {
        return cpuSpeed;
    }
    public void setCpuSpeed(double cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }

    public LocalDateTime getCpuUptime() {
        return cpuUptime;
    }
    public void setCpuUptime(LocalDateTime cpuUptime) {
        this.cpuUptime = cpuUptime;
    }

    public int getCpuProcesses() {
        return cpuProcesses;
    }
    public void setCpuProcesses(int cpuProcesses) {
        this.cpuProcesses = cpuProcesses;
    }

    public int getCpuThreads() {
        return cpuThreads;
    }
    public void setCpuThreads(int cpuThreads) {
        this.cpuThreads = cpuThreads;
    }

}
