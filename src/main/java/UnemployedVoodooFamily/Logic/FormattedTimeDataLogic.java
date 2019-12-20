package UnemployedVoodooFamily.Logic;

import UnemployedVoodooFamily.Data.DailyFormattedDataModel;
import UnemployedVoodooFamily.Data.WeeklyFormattedDataListFactory;
import UnemployedVoodooFamily.Data.MonthlyFormattedDataListFactory;
import ch.simas.jtoggl.TimeEntry;
import org.threeten.extra.YearWeek;
import java.io.IOException;
import java.time.*;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;

/**
 *
 * @author asty
 */
public class FormattedTimeDataLogic {

    private static Map<YearWeek, List<DailyFormattedDataModel>> weeklyMasterData;
    private static Map<YearMonth, List<DailyFormattedDataModel>> monthlyMasterData;

    private int selectedYear;
    private int selectedWeek;
    private Month selectedMonth;

    /**
     *
     */
    public FormattedTimeDataLogic() {
        //Get current year
        selectedYear = LocalDate.now().getYear();

        //Get current week
        LocalDate date = LocalDate.now();
        TemporalField weekFields = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        selectedWeek = date.get(weekFields);

        selectedMonth = LocalDate.now().getMonth();

    }


    //Called when the "export to excel" button is pressed

    /**
     *
     * @param timeEntries
     * @param year
     * @return
     * @throws IOException
     */
    public boolean exportToExcelDocument(Map<YearMonth, List<DailyFormattedDataModel>> timeEntries, int year) throws IOException {
        if(timeEntries == null) {
            return false;
        }
        ExcelExportHandler exportHandler = new ExcelExportHandler(timeEntries, year);
        return exportHandler.makeExcelDocument();
    }

    /**
     *
     * @param yearWeek
     * @return
     */
    public List<DailyFormattedDataModel> getWeeklyData(YearWeek yearWeek) {
        if(weeklyMasterData == null) {
            throw new RuntimeException("Weekly master data not yet initalized");
        }
        return weeklyMasterData.getOrDefault(yearWeek, Collections.emptyList());
    }

    /**
     *
     * @param yearMonth
     * @return
     */
    public List<DailyFormattedDataModel> getMonthlyData(YearMonth yearMonth) {
        if(monthlyMasterData == null) {
            throw new RuntimeException("Monthly master data not yet initalized");
        }
        return monthlyMasterData.getOrDefault(yearMonth, Collections.emptyList());
    }

    /**
     * Build the masterdata for one full year, from scratch.
     * @param timeEntries
     * @param year
     */
    public void buildMasterData(List<TimeEntry> timeEntries, int year) {

        weeklyMasterData = new HashMap<>();
        monthlyMasterData = new HashMap<>();

        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);
        YearWeek startWeek = YearWeek.from(startDate);
        YearWeek endWeek = YearWeek.from(endDate);

        double accumulatedOffset = 0d;

        for(YearWeek date = startWeek; date.isBefore(endWeek.plusWeeks(1)); date = date.plusWeeks(1)) {
            weeklyMasterData.put(date, new WeeklyFormattedDataListFactory().buildWeeklyDataList(timeEntries, date, accumulatedOffset));
            List<DailyFormattedDataModel> latestWeek = weeklyMasterData.get(date);
            accumulatedOffset = latestWeek.get(latestWeek.size() - 1).getAccumulatedHours();
        }

        for(Month month: Month.values()) {

            List<DailyFormattedDataModel> list = new MonthlyFormattedDataListFactory()
                    .buildMonthlyDataList(weeklyMasterData, month, year);
            YearMonth yearMonth = YearMonth.of(year, month);

            monthlyMasterData.put(yearMonth, list);
        }

    }

    /**
     *
     * @param year
     */
    public void setSelectedYear(int year) {
        this.selectedYear = year;
    }

    /**
     *
     * @return
     */
    public int getSelectedYear() {
        return this.selectedYear;
    }

    /**
     *
     * @param selectedWeek
     */
    public void setSelectedWeek(int selectedWeek) {
        this.selectedWeek = selectedWeek;
    }

    /**
     *
     * @return
     */
    public int getSelectedWeek() {
        return selectedWeek;
    }

    /**
     *
     * @param selectedMonth
     */
    public void setSelectedMonth(Month selectedMonth) {
        this.selectedMonth = selectedMonth;
    }

    /**
     *
     * @return
     */
    public Month getSelectedMonth() {
        return selectedMonth;
    }

    /**
     *
     * @return
     */
    public Map<YearWeek, List<DailyFormattedDataModel>> getWeeklyMasterData() {
        return weeklyMasterData;
    }

    /**
     *
     * @return
     */
    public Map<YearMonth, List<DailyFormattedDataModel>> getMonthlyMasterData() {
        return monthlyMasterData;
    }

    /**
     *
     * @return
     */
    public static Map<YearMonth, List<DailyFormattedDataModel>> getMonthlyMaterData() {
        return monthlyMasterData;
    }
}
