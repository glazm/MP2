package model;

import exception.ValException;

import java.util.ArrayList;
import java.util.List;

public class MilitaryBranch {
    private String name;
    private String commander;
    private String nameAcronym;
    private String specialization;

    private List<Soldier> soldiers = new ArrayList<>();

    public MilitaryBranch(String name, String commander, String nameAcronym, String specialization, Soldier soldier) {
        setName(name);
        setCommander(commander);
        setNameAcronym(nameAcronym);
        setSpecialization(specialization);
        addSoldier(soldier);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommander() {
        return commander;
    }

    public void setCommander(String commander) {
        this.commander = commander;
    }

    public String getNameAcronym() {
        return nameAcronym;
    }

    public void setNameAcronym(String nameAcronym) {
        this.nameAcronym = nameAcronym;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<Soldier> getSoldiers() {
        return this.soldiers;
    }

    public void addSoldier(Soldier soldier){
        if(this.soldiers.contains(soldier)){//duplication check
            return;
        }
        if(soldier == null){
            throw new ValException("soldier info is required");
        }
        this.soldiers.add(soldier);//creating association for both sides
        soldier.setMilitaryBranch(this);//creating association for both sides
    }

    public void removeSoldier(Soldier soldier){
        if(this.soldiers.size() == 1){
            throw new ValException("military branch can't exists without soldiers");
        }
        if(!this.soldiers.contains(soldier)){
            return;
        }
        this.soldiers.remove(soldier);
        soldier.setMilitaryBranch(null);
    }

    @Override
    public String toString() {
        return getName()+", "+
        getCommander()+", "+
        getNameAcronym()+", "+
        getSpecialization();
    }
}
