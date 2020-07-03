package com.example.equipme;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Employee {

    String jobTitle;
    String name;
    String emailAddress;
    String employeeNumber;
    ArrayList<Equipment> equipment;
    String notes;

    Employee(){}

    void setJobTitle(String jTitle){this.jobTitle = jTitle;}
    String getJobTitle(){return jobTitle;}

    void setName(String NAME){this.name = NAME;}
    String getName(){return name;}

    void setEmailAddress(String email){this.emailAddress = email;}
    String getEmailAddress(){return emailAddress;}

    void setEmployeeNumber(String number){this.employeeNumber = number;}
    String getEmployeeNumber(){return employeeNumber;}

    void setEquipment(ArrayList equip){this.equipment = equip;}
    ArrayList<Equipment> getEquipment(){return equipment;}

    void setNotes(String note){this.notes = note;}
    String getNotes(){return notes;}

    void display(){}
}
