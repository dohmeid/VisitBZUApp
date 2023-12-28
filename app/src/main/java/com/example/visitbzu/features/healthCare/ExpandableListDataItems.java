package com.example.visitbzu.features.healthCare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataItems {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableDetailList = new HashMap<String, List<String>>();

        List<String> fruits = new ArrayList<String>();
        fruits.add("Apple");
        fruits.add("Orange");
        fruits.add("Pineapple");

        List<String> vegetables = new ArrayList<String>();
        vegetables.add("Tomato");

        List<String> nuts = new ArrayList<String>();
        nuts.add("Cashews");
        nuts.add("Walnut");

        List<String> l1 = new ArrayList<String>();
        List<String> l2 = new ArrayList<String>();
        List<String> l3 = new ArrayList<String>();


        // expandableDetailList object is used to map the group header strings to their respective children using an ArrayList of Strings.
        expandableDetailList.put("Dental clinic", fruits);
        expandableDetailList.put("General Medicine Clinic", vegetables);
        expandableDetailList.put("Ambulance and Emergency Services", nuts);
        expandableDetailList.put("Medical Laboratory Examinations", l1);
        expandableDetailList.put("Dispensing Medications for Students", l2);
        expandableDetailList.put("Management of Student Health Insurance", l3);
        return expandableDetailList;
    }
}
