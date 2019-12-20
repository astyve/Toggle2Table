package UnemployedVoodooFamily.Data;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Object representing an "ordinary work hours" time period.
 */
public class WorkHours {

    private LocalDate from;
    private LocalDate to;
    private Double hours;
    private String note;

    /**
     *
     * @param from
     * @param to
     * @param hours
     */
    public WorkHours(LocalDate from, LocalDate to, Double hours) {
        this.from = from;
        this.to = to;
        this.hours = hours;
        this.note = "";
    }

    /**
     *
     * @param from
     * @param to
     * @param hours
     * @param note
     */
    public WorkHours(LocalDate from, LocalDate to, Double hours, String note) {
        this.from = from;
        this.to = to;
        this.hours = hours;
        this.note = note;
    }

    /**
     *
     * @return
     */
    public LocalDate getFrom() {
        return from;
    }

    /**
     *
     * @return
     */
    public LocalDate getTo() {
        return to;
    }

    /**
     *
     * @return
     */
    public Double getHours() {
        return hours;
    }

    /**
     *
     * @return
     */
    public String getNote() {
        return note;
    }

    /**
     *
     * @return
     */
    public DateRange getRange() {
        return DateRange.of(this.from, this.to);
    }

    /**
     *
     * @param from
     */
    public void setFrom(LocalDate from) {
        this.from = from;
    }

    /**
     *
     * @param to
     */
    public void setTo(LocalDate to) {
        this.to = to;
    }

    /**
     *
     * @param hours
     */
    public void setHours(Double hours) {
        this.hours = hours;
    }

    /**
     *
     * @param note
     */
    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        WorkHours workHours = (WorkHours) o;
        return Objects.equals(from, workHours.from) && Objects.equals(to, workHours.to) && Objects
                .equals(hours, workHours.hours) && Objects.equals(note, workHours.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, hours, note);
    }
}
