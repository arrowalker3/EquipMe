package com.example.equipme;

import org.junit.Test;

import java.lang.reflect.Field;

import static junit.framework.TestCase.assertEquals;

public class EquipmentTests {
    @Test
    public void setTypeTest() throws NoSuchFieldException {
        try {
            Equipment equipment = new Equipment();
            equipment.setType("brandA");

            Field typeField = equipment.getClass().getDeclaredField("type");
            typeField.setAccessible(true);

            assertEquals("brandA", typeField.get(equipment));
        } catch (Exception e) {

        }
    }
}
