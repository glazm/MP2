package model;

import exception.ValException;

import java.util.ArrayList;
import java.util.List;

public class Mission {
    private String codename;
    private long numberOfTroops;

    private List<MissionAssignment> assignments = new ArrayList<>();
    private Conflict conflict;

    private List<MissionTask> missionTasks = new ArrayList<>();

    public Mission(String codename, long numberOfTroops) {
        setCodename(codename);
        setNumberOfTroops(numberOfTroops);
    }

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        if(codename == null || codename.isEmpty()){
            throw new ValException("codename info is required");
        }
        if(this.conflict != null){
            Conflict tmpConflict = this.conflict;
            this.conflict.removeMission(this);
            this.codename = codename;
            tmpConflict.addMission(this);
        } else{
            this.codename = codename;
        }

    }

    public long getNumberOfTroops() {
        return numberOfTroops;
    }

    public void setNumberOfTroops(long numberOfTroops) {
        this.numberOfTroops = numberOfTroops;
    }

    public List<MissionAssignment> getAssignments() {
        return this.assignments;
    }

    public void addAssignment(MissionAssignment assignment){
        if(this.assignments.contains(assignment)){//duplication check
            return;
        }
        if(assignment == null){
            throw new ValException("assignment info is required");
        }
        if(assignment.getMission() != this){
            throw new ValException("assignment is already connected with different mission");
        }
        this.assignments.add(assignment);
    }

    public void removeAssignment(MissionAssignment assignment){
        if(!this.assignments.contains(assignment)){
            return;
        }
        this.assignments.remove(assignment);
        assignment.removeAll();
    }

    public Conflict getConflict() {
        return conflict;
    }

    public void setConflict(Conflict conflict) {
        if(this.conflict== conflict){
            return;
        }
        addToConflict(conflict);
        removeFromConflict(conflict);
        reassignConflict(conflict);
    }

    private void addToConflict(Conflict conflict){
        if(this.conflict == null && conflict != null){
            this.conflict = conflict;
            conflict.addMission(this);
        }
    }
    private void removeFromConflict(Conflict conflict){
        if(this.conflict != null && conflict == null){
            Conflict tmpConflict = this.conflict;
            this.conflict = null;
            tmpConflict.removeMission(this);
        }
    }
    private void reassignConflict(Conflict conflict){
        if(this.conflict != null && conflict != null){
            Conflict tmpConflict = this.conflict;
            this.conflict = null;
            tmpConflict.removeMission(this);
            this.conflict = conflict;
            conflict.addMission(this);
        }
    }

    public List<MissionTask> getMissionTasks() {
        return this.missionTasks;
    }

    public void addMissionTask(MissionTask missionTask){
        if(missionTask == null){
            throw new ValException("missionTask info is required");
        }
        if(missionTask.getMission() != this){
            throw new ValException("missionTask is already connected with different mission");
        }
        if(this.missionTasks.contains(missionTask)){
            return;
        }
        this.missionTasks.add(missionTask);
    }

    public void removeMissionTask(MissionTask missionTask){
        if(!this.missionTasks.contains(missionTask)){
            return;
        }
        this.missionTasks.remove(missionTask);
        missionTask.removeAll();
    }

    public void removeAll(){
        List<MissionTask> tmpMissionTasks = new ArrayList<>(this.missionTasks);
        for(MissionTask missionTask : tmpMissionTasks){
            missionTask.removeAll();
        }
    }

    @Override
    public String toString() {
        return getCodename()+", "+
                getNumberOfTroops();
    }
}
