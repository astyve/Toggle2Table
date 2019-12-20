package UnemployedVoodooFamily.Data;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author asty
 */
public class RawTimeDataModel {

    private SimpleStringProperty project;
    private SimpleStringProperty client;
    private SimpleStringProperty description;
    private SimpleStringProperty startDate;
    private SimpleStringProperty startTime;
    private SimpleStringProperty endDate;
    private SimpleStringProperty endTime;
    private SimpleStringProperty duration;

    /**
     * Creates a RawTimeDataModel object
     * Used to structure data for the corresponding TableView
     * @param string
     * @param string1
     * @param description String with description
     * @param string3
     * @param startTime   String with start time
     * @param string4
     * @param string6
     * @param string5
     * @param string7
     */
    public RawTimeDataModel(String project, String client, String description, String startDate, String startTime,
                            String endDate, String endTime, String duration) {

        this.project = new SimpleStringProperty(project);
        this.client = new SimpleStringProperty(client);
        this.description = new SimpleStringProperty(description);
        this.startDate = new SimpleStringProperty(startDate);
        this.startTime = new SimpleStringProperty(startTime);
        this.endDate = new SimpleStringProperty(endDate);
        this.endTime = new SimpleStringProperty(endTime);
        this.duration = new SimpleStringProperty(duration);
    }

    /**
     *
     * @return
     */
    public String getClient() {
        return client.get();
    }

    /**
     *
     * @return
     */
    public SimpleStringProperty clientProperty() {
        return client;
    }

    /**
     * Returns the project name
     * @return the project name
     */
    public String getProject() {
        return project.get();
    }


    /**
     * Returns the description
     * @return the description
     */
    public String getDescription() {
        return description.get();
    }

    /**
     * Returns the start date as a string
     * @return the start date as a string
     */
    public String getStartDate() {
        return startDate.get();
    }

    /**
     * Returns the start time as a string
     * @return the start time as a string
     */
    public String getStartTime() {
        return startTime.get();
    }

    /**
     * Returns the end date as a string
     * @return the end date as a string
     */
    public String getEndDate() {
        return endDate.get();
    }

    /**
     * Returns the end time as a string
     * @return the end time as a string
     */
    public String getEndTime() {
        return endTime.get();
    }

    /**
     * Returns the duration as a string
     * @return the duration as a string
     */
    public String getDuration() {
        return duration.get();
    }
}
