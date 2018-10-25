/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bskyb.internettv.parental_control_implement;

import java.util.Hashtable;

/**
 *
 * @author prasanna
 */
public class ControlLevels {

    // constructor
    public ControlLevels() {

    }

    /**
     * Get Level from basic structure
     *
     * @param controlLevel
     * @return Level ID
     * @throws Exception
     */
    public static Integer getLevel(String controlLevel) throws Exception {

        try {
            Hashtable<String, Integer> levels = new Hashtable();
            levels.put("U", 1);
            levels.put("PG", 2);
            levels.put("12", 3);
            levels.put("15", 4);
            levels.put("18", 5);

            int level = levels.get(controlLevel);

            return level;
        } catch (Exception e) {
            throw new Exception("Not defined control level: " + controlLevel + " in ", e);
        }
    }

}
