package com.example.equipme;

import java.lang.reflect.Array;

public class Employee {

    String jobTitle;
    String name;
    String emailAddress;
    String employeeNumber;
    Array equipment;
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

    void setEquipment(Array equip){this.equipment = equip;}
    Array getEquipment(){return equipment;}

    void setNotes(String note){this.notes = note;}
    String getNotes(){return notes;}

    void display(){}
}
