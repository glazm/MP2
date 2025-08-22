package model;

import exception.ValException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MissionAssignment {
    private LocalDate startDate;
    private LocalDate endDate;

    private Soldier soldier;
    private Mission mission;

    private static List<MissionAssignment> assignmentExtent = new ArrayList<>();

    public MissionAssignment(LocalDate startDate, Soldier soldier, Mission mission) {
        setStartDate(startDate);
        setSoldier(soldier);
        setMission(mission);
        unigueCheck();
        assignmentExtent.add(this);
    }

    public MissionAssignment(LocalDate startDate, LocalDate endDate, Soldier soldier, Mission mission) {
        setStartDate(startDate);
        setEndDate(endDate);
        setSoldier(soldier);
        setMission(mission);
        unigueCheck();
        assignmentExtent.add(this);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Soldier getSoldier() {
        return soldier;
    }

    private void setSoldier(Soldier soldier) {
        if(soldier == null){
            throw new ValException("soldier info is required");
        }
        this.soldier = soldier;
        soldier.addAssignment(this);
    }

    public Mission getMission() {
        return mission;
    }

    private void setMission(Mission mission) {
        if(mission == null){
            throw new ValException("mission info is required");
        }
        this.mission = mission;
        mission.addAssignment(this);

    }

    private void unigueCheck(){
        for(MissionAssignment missionAssignment: assignmentExtent){
            if(missionAssignment.soldier.equals(this.soldier) &&
                missionAssignment.mission.equals(this.mission)){
                throw new ValException("missionAssignment is not unique");
            }
        }
    }


    public void removeAll(){
        if(this.mission != null){
            Mission tmpMission = this.mission;
            this.mission = null;
            tmpMission.removeAssignment(this);
        }
        if(this.soldier != null){
            Soldier tmpSoldier = this.soldier;
            this.soldier = null;
            tmpSoldier.removeAssignment(this);
        }
        assignmentExtent.remove(this);
    }

    @Override
    public String toString() {
        if(this.endDate!=null) {
            return getStartDate() + ", " +
                    getEndDate();
        }
        return getStartDate().toString();
    }
}
