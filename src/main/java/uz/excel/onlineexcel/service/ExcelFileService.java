package uz.excel.onlineexcel.service;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;
import uz.excel.onlineexcel.dto.student.StudentDto;
import uz.excel.onlineexcel.entity.Student;
import uz.excel.onlineexcel.enums.AcademicLevel;
import uz.excel.onlineexcel.enums.AcademicType;
import uz.excel.onlineexcel.enums.StudyType;
import uz.excel.onlineexcel.repository.StudentRepository;
import uz.excel.onlineexcel.service.base.BaseService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ExcelFileService implements BaseService {

    private final StudentRepository repository;


    public String createExcelFile(List<StudentDto> list) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        workbook.setWorkbookType(XSSFWorkbookType.XLSX);
        String fileName = System.currentTimeMillis() + UUID.randomUUID().toString();
        File file = new File("src/main/resources/" + fileName + ".xlsx");
        try {
            file = File.createTempFile("src/main/resources/" + fileName, ".xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileOutputStream outputStream = new FileOutputStream(file.getAbsolutePath())) {

            XSSFSheet xssfSheet = workbook.createSheet("Students");
            XSSFRow xssfRow = xssfSheet.createRow(0);

            Font font = workbook.createFont();
            font.setFontHeightInPoints((short) 11);
            font.setFontName("Times New Roman");
            font.setBold(true);

            CellStyle style = getCellStyle(workbook, font);

            List<String> rowNames = new ArrayList<>();
            rowNames.add("№");
            rowNames.add("OTM nomi");
            rowNames.add("Familiyasi, ismi, sharifi");
            rowNames.add("O'qishga kirgan yili");
            rowNames.add("Bitirgan yili");
            rowNames.add("Fakulteti");
            rowNames.add("Yo'nalishi");
            rowNames.add("O'qish turi");
            rowNames.add("Bo'lim");
            rowNames.add("Diplom seriyasi");
            rowNames.add("Diplom ro'yxatga olish raqami");
            rowNames.add("Berilgan vaqti");
            rowNames.add("Magistr/Bakalavr");
            rowNames.add("Ilova");

            for (int i = 0; i <= 13; i++) {
                XSSFCell cell = xssfRow.createCell(i);
                cell.setCellStyle(style);
                cell.setCellValue(rowNames.get(i));
            }

            Font font1 = workbook.createFont();
            font1.setFontHeightInPoints((short) 11);
            font1.setFontName("Times New Roman");

            CellStyle style1 = getCellStyle(workbook, font1);

            for (int i = 0; i < list.size(); i++) {

                XSSFRow row = xssfSheet.createRow(i + 1);
//                row.setRowStyle(style1);

                List<String> studentValues = new ArrayList<>();
                studentValues.add(String.valueOf(i + 1));
                studentValues.add(list.get(i).getUniversityName());
                studentValues.add(list.get(i).getFullName());
                studentValues.add(list.get(i).getEntranceYear());
                studentValues.add(list.get(i).getGraduationYear());
                studentValues.add(list.get(i).getFaculty());
                studentValues.add(list.get(i).getSpeciality());
                studentValues.add(list.get(i).getStudyType());
                studentValues.add(list.get(i).getAcademicType());
                studentValues.add(list.get(i).getDiplomaSerial());
                studentValues.add(list.get(i).getDiplomaRegistrationNumber());
                studentValues.add(list.get(i).getGivenDate());
                studentValues.add(list.get(i).getAcademicLevel());
                studentValues.add(list.get(i).getAppendixNumber());

                for (int j = 0; j <= 13; j++) {
                    XSSFCell cell = row.createCell(j);
                    cell.setCellValue(studentValues.get(j));
                    cell.setCellStyle(style1);
                }
            }
            workbook.write(outputStream);
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean upload() {
        File file = new File("src/main/resources/fileNew.xlsx");
        try {
            List<Student> students = new ArrayList<>();
            FileInputStream fileInputStream = new FileInputStream(file);
            //workbook created
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

            //total number of sheets
            int numberOfSheets = workbook.getNumberOfSheets();

            for (int sheetIndex = 0; sheetIndex < numberOfSheets; sheetIndex++) {
                //sheet at current index
                XSSFSheet sheetAt = workbook.getSheetAt(sheetIndex);

                //total count of rows in current sheet
                int lastRowNum = sheetAt.getLastRowNum();

                for (int rowIndex = 1; rowIndex <= lastRowNum; rowIndex++) {
                    //row at current index of current sheet
                    XSSFRow row = sheetAt.getRow(rowIndex);

                    if (Objects.isNull(row)) {
                        continue;
                    }
                    String universityName = getValueFromCell(row, 1);
                    String fullName = getValueFromCell(row, 2);
                    String entranceYear = getValueFromCell(row, 3);
                    String graduationYear = getValueFromCell(row, 4);
                    String faculty = getValueFromCell(row, 5);
                    String speciality = getValueFromCell(row, 6);

                    String studyType = getValueFromCell(row, 7).toLowerCase();

                    List<String> values = new ArrayList<>();
                    Arrays.stream(StudyType.values()).forEach(value -> {
                        values.add(value.getLatin());
                        values.add(value.getCyrillic());
                    });

                    studyType = checkValue(values, studyType);

                    String academicType = getValueFromCell(row, 8).toLowerCase();

                    List<String> values1 = new ArrayList<>();
                    Arrays.stream(AcademicType.values()).forEach(value -> {
                        values1.add(value.getLatin());
                        values1.add(value.getCyrillic());
                    });

                    academicType = checkValue(values1, academicType);

                    String diplomaSerial = getValueFromCell(row, 9);
                    String diplomaRegistrationNumber = getValueFromCell(row, 10);
                    String givenDate = getValueFromCell(row, 11);

                    String academicLevel = getValueFromCell(row, 12).toLowerCase();

                    List<String> values2 = new ArrayList<>();
                    Arrays.stream(AcademicLevel.values()).forEach(value -> {
                        values2.add(value.getLatin());
                        values2.add(value.getCyrillic());
                    });

                    academicLevel = checkValue(values2, academicLevel);

                    String appendixNumber = getValueFromCell(row, 13);
                    Student student = new Student();
                    student.setUniversityName(universityName);
                    student.setFullName(fullName);
                    student.setEntranceYear(entranceYear);
                    student.setGraduationYear(graduationYear);
                    student.setFaculty(faculty);
                    student.setSpeciality(speciality);
                    student.setStudyType(studyType);
                    student.setAcademicType(academicType);
                    student.setDiplomaSerial(diplomaSerial);
                    student.setDiplomaRegistrationNumber(diplomaRegistrationNumber);
                    student.setGivenDate(givenDate);
                    student.setAcademicLevel(academicLevel);
                    student.setAppendixNumber(appendixNumber);
                    student.setOrganizationId(1L);

                    if (
                            !(
                                    (
                                            student.getFullName().equals("")
                                                    && student.getDiplomaSerial().equals("")
                                                    && student.getDiplomaRegistrationNumber().equals("")
                                                    && student.getEntranceYear().equals("")
                                    ) || (
                                            student.getFullName().equals("null")
                                                    && student.getDiplomaSerial().equals("null")
                                                    && student.getDiplomaRegistrationNumber().equals("null")
                                                    && student.getEntranceYear().equals("null")

                                    ) || (
                                            student.getFullName().equals("-")
                                                    && student.getDiplomaSerial().equals("-")
                                                    && student.getDiplomaRegistrationNumber().equals("-")
                                                    && student.getEntranceYear().equals("-")

                                    )
                            )
                    ) {

                        students.add(student);
                    }

                } //rows

            } //sheets

            repository.saveAll(students);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String checkValue(List<String> typeValues, String value) {
        return typeValues
                .stream()
                .filter(typeValue -> value.startsWith(typeValue.toLowerCase().substring(0, 2)))
                .findFirst()
                .orElse(value);
    }

    private String getValueFromCell(XSSFRow row, int cellIndex) {

        XSSFCell cell = row.getCell(cellIndex);
        if (Objects.isNull(cell)) {
            return "-";
        }
        CellType cellType = cell.getCellType();

        if (cellType.equals(CellType.STRING)) {
            String value = cell.getStringCellValue();
            if (Objects.isNull(value) || value.isEmpty() || Objects.equals(value, "null")) {
                return "-";
            }
            return value;

        } else if (cellType.equals(CellType.NUMERIC)) {
            double numericCellValue = cell.getNumericCellValue();
            int intValue = (int) numericCellValue;
            if (numericCellValue == intValue) {
                return String.valueOf(intValue);
            } else {
                return String.valueOf(numericCellValue);
            }
        } else if (cellType.equals(CellType.BOOLEAN)) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cellType.equals(CellType.FORMULA)) {
            return cell.getCellFormula();
        } else if (cellType.equals(CellType.ERROR)) {
            return cell.getErrorCellString();
        } else {
            String value = String.valueOf(cell.getDateCellValue());
            if (Objects.isNull(value) || value.isBlank() || Objects.equals(value, "null")) {
                return "-";
            }
            return value;
        }
    }

    private CellStyle getCellStyle(XSSFWorkbook workbook, Font font) {
        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);
        style.setFont(font);
        return style;
    }
}
