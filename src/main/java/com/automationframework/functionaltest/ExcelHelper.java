package com.automationframework.functionaltest;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

public class ExcelHelper {

    public static String ReadCell(String _ExcelFileName, String _SheetName, int row, int column) throws IOException {

        String cellvalue = "";

        String path = FileSystems.getDefault().getPath(_ExcelFileName).toString();

        try {
            //Create a workbook object from the file at specified location.
            //Change the path of the file as per the location on your computer.
            Workbook wrk = Workbook.getWorkbook(new File(path));

            //Obtain the reference to the first sheet in the workbook
            Sheet sheet = wrk.getSheet(_SheetName);

            //Obtain reference to the Cell using getCell(int col, int row) method of sheet
            Cell cell = sheet.getCell(row, column);

            //Read the contents of the Cell using getContents() method, which will return
            //it as a String
            cellvalue = cell.getContents();

        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cellvalue;
    }

    public static String[][] ReadExcelSheet(String _ExcelFileName, String _SheetName) throws Exception {
        {
            String path = FileSystems.getDefault().getPath(_ExcelFileName).toString();

            String strArr[][] = null;
            Sheet excelsheet = null;
            try {
                Workbook workbook = Workbook.getWorkbook(new File(path));  //should be global
                excelsheet = workbook.getSheet(_SheetName);
                int noOfRows = excelsheet.getRows();
                int noOfcols = excelsheet.getColumns();

                strArr = new String[noOfRows][noOfcols];
                Cell cell = null;
                String content = null;
                String key = null;
                int[] indexMapArray = new int[noOfcols];
                System.out.println("Number of Rows " + noOfRows);
                System.out.println("Number of Columns" + noOfcols);
                for (int row = 0; row < noOfRows; row++) {
                    for (int col = 0; col < noOfcols; col++) {
                        cell = excelsheet.getCell(col, row);
                        content = cell.getContents();
                        strArr[row][col] = content;
                    }
                }
                return strArr;
            } catch (Exception e) {
                throw e;
            } finally {
                excelsheet = null;
            }
        }

    }

}
