package com.example.communityxchange;

/**
 * Represents the data class for storing the business information.
 */
public class DataClass {

    //private String dataTitle;
    private String dataBusinessName;
    private String dataDescription;
    private String dataContactDetails;
    private String dataOwner;
    private String dataImage;

    //for data delete
    private String key;

    /**
     * Gets the key used for data deletion.
     *
     * @return The key for data deletion.
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the key used for data deletion.
     *
     * @param key The key for data deletion.
     */
    public void setKey(String key) {
        this.key = key;
    }


    //Constructor
    /**
     * Constructor for creating a DataClass object with business information.
     *
     * @param dataBusinessName   The name of the business.
     * @param dataDescription    The description of the business.
     * @param dataContactDetails The contact details of the business owner.
     * @param dataOwner          The owner of the business.
     * @param dataImage          The URL of the business image.
     */
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
    /**
     * Gets the business name.
     *
     * @return The business name.
     */
    public String getDataBusinessName() {
        return dataBusinessName;
    }


    /**
     * Gets the business description.
     *
     * @return The business description.
     */
    public String getDataDescription() {
        return dataDescription;
    }


    /**
     * Gets the contact details of the business owner.
     *
     * @return The contact details of the business owner.
     */
    public String getDataContactDetails() {
        return dataContactDetails;
    }


    /**
     * Gets the owner of the business.
     *
     * @return The owner of the business.
     */
    public String getDataOwner() {
        return dataOwner;
    }



    /**
     * Gets the URL of the business image.
     *
     * @return The URL of the business image.
     */
    public String getDataImage() {
        return dataImage;
    }


    /**
     * Default constructor for creating an empty DataClass object.
     * Used for retrieving data from Firebase and displaying.
     */
    //code for retrieved data from firebase and displaying
    public DataClass(){

    }
}
