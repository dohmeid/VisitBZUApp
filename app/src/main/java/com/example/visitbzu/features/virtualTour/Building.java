package com.example.visitbzu.features.virtualTour;

//this class is used to represent the buildings objects fetched from firestore database
public class Building {

    private String code = "";

    private String nameEN = "";
    private String facultyNameEN = "";
    private String descriptionEN = "";

    private String nameAR = "";
    private String facultyNameAR = "";
    private String descriptionAR = "";

    //default constructor
    public Building() {
    }

    public Building(String code, String nameEN, String facultyNameEN, String descriptionEN, String nameAR, String facultyNameAR, String descriptionAR) {
        this.code = code;
        this.nameEN = nameEN;
        this.facultyNameEN = facultyNameEN;
        this.descriptionEN = descriptionEN;
        this.nameAR = nameAR;
        this.facultyNameAR = facultyNameAR;
        this.descriptionAR = descriptionAR;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getFacultyNameEN() {
        return facultyNameEN;
    }

    public void setFacultyNameEN(String facultyNameEN) {
        this.facultyNameEN = facultyNameEN;
    }

    public String getDescriptionEN() {
        return descriptionEN;
    }

    public void setDescriptionEN(String descriptionEN) {
        this.descriptionEN = descriptionEN;
    }

    public String getNameAR() {
        return nameAR;
    }

    public void setNameAR(String nameAR) {
        this.nameAR = nameAR;
    }

    public String getFacultyNameAR() {
        return facultyNameAR;
    }

    public void setFacultyNameAR(String facultyNameAR) {
        this.facultyNameAR = facultyNameAR;
    }

    public String getDescriptionAR() {
        return descriptionAR;
    }

    public void setDescriptionAR(String descriptionAR) {
        this.descriptionAR = descriptionAR;
    }
}
