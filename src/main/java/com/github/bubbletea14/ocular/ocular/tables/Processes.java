package com.github.bubbletea14.ocular.ocular.tables;

public class Processes {
    private Long pId;
    private String name;

    //Construcor
    public Processes (Long pId, String name) {
        this.pId = pId;
        this.name = name;
    }

    // Getter and Setter
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

       //TODO: CPU, Memory, RAM percentage..

    @Override
    public String toString () {
        return "Processes {" + 
                "PID=" + pId +
                ", Name=" + name + 
                '}';
    }
}
