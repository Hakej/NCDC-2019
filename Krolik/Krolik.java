package Krolik;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Krolik {
    /*
    TODO:
    Problem z włączaniem programu dla
    1. Krolik " < plik.csv
    2. Krolik argument
    */
    public static void main(String[] args){
        if(args.length > 0) {
            String searchedElementInput = args[0];
            //searchedElementInput = searchedElementInput.replace("'", ""); // NCDC error?
            String searchedElement = "{".concat(searchedElementInput).concat("}");

            int allowedWidth = 5000;
            int allowedHeight = 5000;

            List<List<String>> tableOfStrings = getTableOfStringsFromFile();
            List<Integer> searchedElementColumnIndexes = findColumnIndexesByElementValue(tableOfStrings, searchedElement);

            if(searchedElementColumnIndexes.size() != 1 || !isTableCorrectSize(tableOfStrings, allowedWidth, allowedHeight)) {
                System.out.println("klops");
            } else {
                int searchedColumnIndex = searchedElementColumnIndexes.get(0);
                int sum = sumOfNumbersInSpecificColumn(searchedColumnIndex, tableOfStrings);
                System.out.println(sum);
            }
        } else {
            System.out.println("klops");
        }
    } // main
    private static List<List<String>> getTableOfStringsFromFile() {
        List<List<String>> tableOfStrings = new ArrayList<>();
        Scanner csvFileBuffer = new Scanner(System.in);
        String csvSplitBy = ",";
        String line;
        while (csvFileBuffer.hasNext()) {
            line = csvFileBuffer.nextLine();
            List<String> wholeRow = Arrays.asList(line.split(csvSplitBy));
            tableOfStrings.add(wholeRow);
        }
        csvFileBuffer.close();
        return tableOfStrings;
    }
    private static List<Integer> findColumnIndexesByElementValue(List<List<String>> tableToSearch, String searchedElement){
        List<Integer> columnIndexesOfSearchedElement = new ArrayList<>();
        for (List<String> singleRow : tableToSearch) {
            if ((singleRow.indexOf(searchedElement)) != -1){
                columnIndexesOfSearchedElement.add(singleRow.indexOf(searchedElement));
            }
        }
        return columnIndexesOfSearchedElement;
    }
    private static boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private static int sumOfNumbersInSpecificColumn(int columnIndex, List<List<String>>tableOfStrings) {
        int sum = 0;
        String stringToParse = "";
        for (List<String> singleRow : tableOfStrings) {
            if (columnIndex < singleRow.size()) {
                stringToParse = singleRow.get(columnIndex);
                if (tryParseInt(stringToParse)) {
                    sum += Integer.parseInt(stringToParse);
                }
            }
        }
        return sum;
    }
    private static boolean isTableCorrectSize(List<List<String>> tableToCheck, int allowedWidth, int allowedHeight) {
        if (tableToCheck.size() > allowedHeight) {
            return false;
        }
        for (List<String> singleRow : tableToCheck) {
            if(singleRow.size() > allowedWidth) {
                return false;
            }
        }
        return true;
    }
} // Krolik class
