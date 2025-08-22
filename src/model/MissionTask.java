package model;

import exception.ValException;

public class MissionTask {
    private String name;
    private String description;
    private long requiredTroopsSize;

    private Mission mission;

    public MissionTask(String name, String description, long requiredTroopsSize, Mission mission) {
        setName(name);
        setDescription(description);
        setRequiredTroopsSize(requiredTroopsSize);
        setMission(mission);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getRequiredTroopsSize() {
        return requiredTroopsSize;
    }

    public void setRequiredTroopsSize(long requiredTroopsSize) {
        this.requiredTroopsSize = requiredTroopsSize;
    }

    public Mission getMission() {
        if(mission == null){
            throw new ValException("mission info is required");
        }
        return mission;
    }

    private void setMission(Mission mission) {
        if(mission == null){
            throw new ValException("mission info is required");
        }
        this.mission = mission;
        mission.addMissionTask(this);
    }

    public void removeAll(){
        if(this.mission != null){
            Mission tmpMission = this.mission;
            this.mission = null;
            tmpMission.removeMissionTask(this);
        }
    }

    @Override
    public String toString() {
        return getName()+", "+
                getDescription()+", "+
                getRequiredTroopsSize();
    }
}
