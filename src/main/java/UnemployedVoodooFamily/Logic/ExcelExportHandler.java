package UnemployedVoodooFamily.Logic;

import UnemployedVoodooFamily.Data.DailyFormattedDataModel;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.Month;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//This class should gather all the data necessary and write them to the excel document

/**
 *
 * @author asty
 */
public class ExcelExportHandler {

    private ExcelWriter excelWriter;
    private HashMap<String, List> monthlyDataLists;
    private int year;
    private String file;

    /**
     *
     * @param timeEntries
     * @param year
     */
    public ExcelExportHandler(Map<YearMonth, List<DailyFormattedDataModel>> timeEntries, int year, String file) {
        excelWriter = new ExcelWriter();
        this.year = year;
        this.file = file;
        monthlyDataLists = new HashMap<>();
        if(null != timeEntries) {
            for(Month month: Month.values()) {
                monthlyDataLists.put(StringUtils.capitalize(month.toString().toLowerCase()),
                                     timeEntries.get(YearMonth.of(year, month)));
            }
        }
    }

    /**
     *
     * @return
     * @throws IOException
     */
    public boolean makeExcelDocument() throws IOException {
        boolean exportSuccess = excelWriter.generateExcelSheet(this.monthlyDataLists, this.year, this.file);
        if(exportSuccess)   {
        }
        return exportSuccess;
    }
}
