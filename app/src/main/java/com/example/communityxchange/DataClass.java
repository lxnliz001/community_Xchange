package com.example.communityxchange;

public class DataClass {

    //private String dataTitle;
    private String dataBusinessName;
    private String dataDescription;
    private String dataContactDetails;
    private String dataOwner;
    private String dataImage;

    //Constructor
    public DataClass(String dataBusinessName, String dataDescription,
                     String dataContactDetails,
                     String dataOwner, String dataImage) {
        this.dataBusinessName = dataBusinessName;
        this.dataDescription = dataDescription;
        this.dataContactDetails = dataContactDetails;
        this.dataOwner = dataOwner;
        this.dataImage = dataImage;
    }

    //Getters (accessors)
    public String getDataBusinessName() {
        return dataBusinessName;
    }

    public String getDataDescription() {
        return dataDescription;
    }

    public String getDataContactDetails() {
        return dataContactDetails;
    }

    public String getDataOwner() {
        return dataOwner;
    }

    public String getDataImage() {
        return dataImage;
    }

    //code for retrieved data from firebase and displaying
    public DataClass(){

    }
}
