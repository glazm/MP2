package model;

import exception.ValException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Conflict {
    private String region;
    private Map<String, Mission> missionMap =new HashMap<>();

    public Conflict(String region) {
        setRegion(region);
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Map<String, Mission> getMissionMap() {
        return this.missionMap;
    }

    public List<Mission> getMissionList(){
        return new ArrayList<>(missionMap.values());
    }

    public void addMission(Mission mission){
        if(this.missionMap.containsKey(mission.getCodename())){//duplication check
            return;
        }
        if(mission == null){
            throw new ValException("mission info is required");
        }
        this.missionMap.put(mission.getCodename(), mission);
        mission.setConflict(this);
    }

    public void removeMission(Mission mission){
        if(!this.missionMap.containsKey(mission.getCodename())){//duplication check
            return;
        }
        if(mission == null){
            throw new ValException("mission info is required");
        }
        this.missionMap.remove(mission.getCodename());
        mission.setConflict(null);
    }


    public Long troopsInMissionByCodename(String codename){
        if(!this.missionMap.containsKey(codename)){
            throw new ValException("codename info is required");
        }
        if(codename == null){
            throw new ValException("codename cannot be null");
        }
        return this.missionMap.get(codename).getNumberOfTroops();
    }
    public Mission findMissionByCodename(String codename){
        if(!this.missionMap.containsKey(codename)){
            throw new ValException("codename info is required");
        }
        if(codename == null){
            throw new ValException("mission cannot be found cause given null");
        }
        return this.missionMap.get(codename);
    }


    @Override
    public String toString() {
        return getRegion();
    }
}
