package uk.co.sky.movie;

import uk.co.sky.parentcontrol.ParentalControlLevel;

/**
 * Created by dinesh on 24/04/2017.
 */
public class Movie {
    private long id;
    private String name;
    private String description;
    private ParentalControlLevel classification;

    public Movie(String name, String description, ParentalControlLevel classification) {
        this.name = name;
        this.description = description;
        this.classification = classification;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ParentalControlLevel getClassification() {
        return classification;
    }

    public void setClassification(ParentalControlLevel classification) {
        this.classification = classification;
    }
}
