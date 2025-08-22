package model;

import exception.ValException;

import java.util.ArrayList;
import java.util.List;

public class Soldier {
    private String firstName;
    private String lastName;
    private String militaryRank;
    private int unitNumber;

    private MilitaryBranch militaryBranch;
    private List<MissionAssignment> assignments = new ArrayList<>();

    public Soldier(String firstName, String lastName, String militaryRank, int unitNumber){
        setFirstName(firstName);
        setLastName(lastName);
        setMilitaryRank(militaryRank);
        setUnitNumber(unitNumber);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMilitaryRank() {
        return militaryRank;
    }

    public void setMilitaryRank(String militaryRank) {
        this.militaryRank = militaryRank;
    }

    public MilitaryBranch getMilitaryBranch() {
        return militaryBranch;
    }

    public void setMilitaryBranch(MilitaryBranch militaryBranch) {
        if(this.militaryBranch== militaryBranch){
            return;
        }
        if(this.militaryBranch == null && militaryBranch != null) {
            addToMilBranch(militaryBranch);
        }else if(this.militaryBranch != null && militaryBranch == null) {
            removeFromMilBranch(militaryBranch);
        }else if(this.militaryBranch != null && militaryBranch != null) {
            reassignMilBranch(militaryBranch);
        }
    }

    private void addToMilBranch(MilitaryBranch militaryBranch){
        if(this.militaryBranch == null && militaryBranch != null){
            this.militaryBranch = militaryBranch;
            militaryBranch.addSoldier(this);
        }
    }
    private void removeFromMilBranch(MilitaryBranch militaryBranch){
        if(this.militaryBranch != null && militaryBranch == null){
            MilitaryBranch tmpBranch = this.militaryBranch;
            this.militaryBranch = null;
            tmpBranch.removeSoldier(this);
        }
    }
    private void reassignMilBranch(MilitaryBranch militaryBranch){
        if(this.militaryBranch != null && militaryBranch != null){
            MilitaryBranch tmpBranch = this.militaryBranch;
            this.militaryBranch = null;
            tmpBranch.removeSoldier(this);
            this.militaryBranch = militaryBranch;
            militaryBranch.addSoldier(this);
        }
    }

    public long getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(int unitNumber) {
        this.unitNumber = unitNumber;
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
        if(assignment.getSoldier() != this){
            throw new ValException("assignment is already connected with different soldier");
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

    @Override
    public String toString() {
        return getFirstName()+", "+
                getLastName()+", "+
                getMilitaryRank()+", "+
                getUnitNumber();
    }
}
