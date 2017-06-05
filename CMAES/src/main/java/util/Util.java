package util;

import domain.Female;
import domain.Male;
import domain.Person;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by dragos on 6/3/17.
 */
public class Util {

    public static final String resource = "CMAES/src/main/resources/";

    public static <T extends Person> List<T> readPersons(String fileName, int ageColumnIndex, int rateColumnIndex, Supplier<T> newPerson) {

        List<T> personList = new ArrayList<>();

        try {
            FileInputStream inputStream = new FileInputStream(new File(resource + fileName));

            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();

                T person = newPerson.get();
                person.setAge((int) currentRow.getCell(ageColumnIndex).getNumericCellValue());
                person.setRate(currentRow.getCell(rateColumnIndex).getNumericCellValue());
                personList.add(person);

            }

            workbook.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return personList;
    }

    public static List<Male> readMalePersons(String fileName, int ageColumnIndex, int rateColumnIndex) {
        return readPersons(fileName, ageColumnIndex, rateColumnIndex, Male::new);
    }

    public static List<Female> readFemalePersons(String fileName, int ageColumnIndex, int rateColumnIndex) {
        return readPersons(fileName, ageColumnIndex, rateColumnIndex, Female::new);
    }
}
