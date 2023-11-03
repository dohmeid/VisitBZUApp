package com.example.visitbzu;

import java.util.List;

public class SaraIssaDataModel {

    private List<String> nestedList;
    private String itemText;
    private boolean isExpandable;

    public SaraIssaDataModel(List<String> nestedList, String itemText) {
        this.nestedList = nestedList;
        this.itemText = itemText;
        isExpandable = false;
    }

    public List<String> getNestedList() {
        return nestedList;
    }

    public String getItemText() {
        return itemText;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }
}
