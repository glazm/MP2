package model;


import java.time.LocalDate;

public class Main {
    public static void main(String[]args){
        System.out.println("*Binary association*");
        Soldier soldier1 = new Soldier("John","Rambo","Sergeant",13);
        MilitaryBranch militaryBranch1= new MilitaryBranch("Special Air Service","David Stirling","SAS","Special operations",soldier1);
        Soldier soldier2 = new Soldier("Tom","Tommy","Captain",14);

        soldier2.setMilitaryBranch(militaryBranch1);

        System.out.println("    "+militaryBranch1.getSoldiers().toString());
        System.out.println("    "+soldier1.getMilitaryBranch().toString());
        System.out.println("    "+soldier2.getMilitaryBranch().toString());

        System.out.println("*Association with attribute*");
        Mission mission1 = new Mission("Desert Shield",150000);
        MissionAssignment missionAssignment1 = new MissionAssignment(LocalDate.of(2016, 02, 01),LocalDate.of(2020, 02, 01),soldier2,mission1);

        System.out.println("    "+missionAssignment1.getSoldier().toString());
        System.out.println("    "+missionAssignment1.getMission().toString());
        System.out.println("    "+soldier2.getAssignments().toString());
        System.out.println("    "+mission1.getAssignments().toString());

        System.out.println("*Qualified association*");
        Conflict conflict1 = new Conflict("America");
        Mission mission2 = new Mission("Hiding Duck",9500);
        conflict1.addMission(mission1);
        conflict1.addMission(mission2);
        //conflict1.troopsInMissionByCodename("ABC");

        for(Mission m: conflict1.getMissionList()){
            System.out.println("    "+m.toString());
        }
        System.out.println("    Conflict region for mission1: "+mission1.getConflict());
        System.out.println("    Conflict region for mission2: "+mission2.getConflict());
        System.out.println("    "+conflict1.troopsInMissionByCodename("Hiding Duck"));
        System.out.println("    "+conflict1.findMissionByCodename("Hiding Duck"));

        System.out.println("*Composition*");
        MissionTask missionTask1= new MissionTask("Reconnaissance mission","Search for hostiles in region",12,mission1);
        MissionTask missionTask2= new MissionTask("Rescue mission","Find and rescue refugees in region",50,mission1);
        MissionTask missionTask3= new MissionTask("Attack enemy","Attack enemy in the night",150,mission1);
        mission1.addMissionTask(missionTask3);
        mission1.addMissionTask(missionTask3);
        mission1.addMissionTask(missionTask3);
        mission1.addMissionTask(missionTask3);
        mission1.addMissionTask(missionTask3);
        mission1.addMissionTask(missionTask3);

        for(MissionTask mt: mission1.getMissionTasks()){
            System.out.println("    "+mt.toString());
        }

        System.out.println("    "+missionTask1.getMission().toString());
        System.out.println("    "+missionTask2.getMission().toString());
        System.out.println("    "+missionTask3.getMission().toString());

        mission1.removeMissionTask(missionTask2);
        missionTask1.removeAll();

        for(MissionTask mt: mission1.getMissionTasks()){
            System.out.println("    "+mt.toString());
        }

//        mission1.removeAll();
//        System.out.println("    "+missionTask3.getMission().toString());




    }
}
