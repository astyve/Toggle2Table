package UnemployedVoodooFamily.Data;

import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Wrapper for WorkHours, where each field is wrapped in a Properties object,
 * for use with JavaFX components.
 */
public class WorkHoursModel {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd. LLLL yyyy");
    private SimpleObjectProperty<LocalDate> from;
    private SimpleObjectProperty<LocalDate> to;
    private SimpleObjectProperty<Double> hours;
    private SimpleObjectProperty<String> note;
    private WorkHours workHours;

    /**
     *
     * @param wh
     */
    public WorkHoursModel(WorkHours wh) {
        this.workHours = wh;
        this.from = new SimpleObjectProperty<>(wh.getFrom());
        this.to = new SimpleObjectProperty<>(wh.getTo());
        this.hours = new SimpleObjectProperty<>(wh.getHours());
        this.note = new SimpleObjectProperty<>(wh.getNote());
    }

    /**
     *
     * @return
     */
    public LocalDate getFrom() {
        return from.get();
    }

    /**
     *
     * @return
     */
    public SimpleObjectProperty<LocalDate> fromProperty() {
        return from;
    }

    /**
     *
     * @return
     */
    public LocalDate getTo() {
        return to.get();
    }

    /**
     *
     * @return
     */
    public SimpleObjectProperty<LocalDate> toProperty() {
        return to;
    }

    /**
     *
     * @return
     */
    public double getHours() {
        return hours.get();
    }

    /**
     *
     * @return
     */
    public SimpleObjectProperty<Double> hoursProperty() {
        return hours;
    }

    /**
     *
     * @return
     */
    public String getNote() {
        return note.get();
    }

    /**
     *
     * @return
     */
    public SimpleObjectProperty<String> noteProperty() {
        return note;
    }

    /**
     *
     * @return
     */
    public WorkHours getWorkHours() {
        return this.workHours;
    }
}
