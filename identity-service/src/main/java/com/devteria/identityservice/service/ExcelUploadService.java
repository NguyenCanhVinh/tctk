package com.devteria.identityservice.service;

import com.devteria.identityservice.entity.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ExcelUploadService {

  public static boolean isValidExcelFile(MultipartFile file){
    return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" );
  }

  public static List<User> getCustomersDataFromExcel(InputStream inputStream){
    List<User> users = new ArrayList<>();
    try {
      XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
      XSSFSheet sheet = workbook.getSheet("users");
      int rowIndex =0;
      for (Row row : sheet){
        if (rowIndex ==0){
          rowIndex++;
          continue;
        }
        Iterator<Cell> cellIterator = row.iterator();
        int cellIndex = 0;
        User user = new User();
        while (cellIterator.hasNext()){
          Cell cell = cellIterator.next();
          switch (cellIndex){
            case 0 -> user.setId(cell.getStringCellValue());
            case 1 -> user.setFirstName(cell.getStringCellValue());
            case 2 -> user.setLastName(cell.getStringCellValue());
            case 3 -> user.setUsername(cell.getStringCellValue());
            case 4 -> user.setPassword(cell.getStringCellValue());
            case 5 -> user.setDob(cell.getLocalDateTimeCellValue().toLocalDate());
            default -> {
            }
          }
          cellIndex++;
        }
        users.add(user);
      }
    } catch (IOException e) {
      e.getStackTrace();
    }
    return users;
  }


}
