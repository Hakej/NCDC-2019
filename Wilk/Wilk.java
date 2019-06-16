package Wilk;

import java.util.*;

public class Wilk {
    public static void main(String[] args ) {
        List<List<Double>> tableOfDoubles = new ArrayList<>();
        int allowedAmountOfTaps = 1000000;
        int allowedWidthOfTable = 2;
        boolean wasInputCorrect = true;
        try {
            tableOfDoubles = tableOfDoublesFromFile();
            if (!areTemperaturesCorrect(tableOfDoubles)) {
                throw new Exception("klops");
            }
            if (!isTableCorrectSize(tableOfDoubles, allowedWidthOfTable, allowedAmountOfTaps)) {
                throw new Exception("klops");
            }
        } catch (Exception klopsException) {
            System.out.println(klopsException.getMessage());
            wasInputCorrect = false;
        }

        if (wasInputCorrect) {
            Double neededAmountOfFuel = tableOfDoubles.get(0).get(0);
            Double sumOfFlows = 0.0;
            Double sumOfTemperatures = 0.0;
            boolean firstIterationHasPassed = false;
            for (List<Double> singleTap : tableOfDoubles) {
                if (!firstIterationHasPassed) {
                    firstIterationHasPassed = true;
                    continue;
                };
                sumOfFlows += singleTap.get(0);
                sumOfTemperatures += singleTap.get(1);
            }
            int amountOfTaps = tableOfDoubles.size() - 1;
            Double finalTemperature = sumOfTemperatures / amountOfTaps;
            Double fuelingTime = (neededAmountOfFuel / sumOfFlows) * 60000;

            System.out.println(roundDoubleValueToFiveDecimalPlaces(fuelingTime));
            System.out.println(roundDoubleValueToFiveDecimalPlaces(finalTemperature));
        }
    } // main
    private static boolean areTemperaturesCorrect(List<List<Double>> tableToCheck) {
        boolean hasFirstIterationPassed = false;
        for (List<Double> singleRow : tableToCheck) {
            if (!hasFirstIterationPassed) {
                hasFirstIterationPassed = true;
                continue;
            }
            Double singleTemperature = singleRow.get(1);
            if (singleTemperature < 1.0 || singleTemperature > 90.0) {
                return false;
            }
        }
        return true;
    }
    private static List<List<Double>> tableOfDoublesFromFile() throws Exception {
        List<List<Double>> tableOfDoubles = new ArrayList<>();
        Scanner csvFileBuffer = new Scanner(System.in);
        String csvSplitBy = " ";
        String line;
        while (csvFileBuffer.hasNext()) {
            line = csvFileBuffer.nextLine();
            List<String> wholeRow = Arrays.asList(line.split(csvSplitBy));
            List<Double> wholeRowAsDouble = new ArrayList<>();
            for (String singleCell : wholeRow) {
                if (tryParseDouble(singleCell)) {
                    wholeRowAsDouble.add(Double.parseDouble(singleCell));
                } else {
                    throw new Exception("klops");
                }
            }
            tableOfDoubles.add(wholeRowAsDouble);
        }
        csvFileBuffer.close();
        return tableOfDoubles;
    }
    private static boolean tryParseDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private static Double roundDoubleValueToFiveDecimalPlaces(Double valueToRound) {
        return Math.round(valueToRound * 100000d) / 100000d;
    }
    private static boolean isTableCorrectSize(List<List<Double>> tableToCheck, int allowedWidth, int allowedHeight) {
        if (tableToCheck.size() > allowedHeight) {
            return false;
        }
        boolean hasFirstIterationPassed = false;
        for (List<Double> singleRow : tableToCheck) {
            if (!hasFirstIterationPassed) {
                if (singleRow.size() != 1) {
                    return false;
                }
                hasFirstIterationPassed = true;
                continue;
            }
            if(singleRow.size() > allowedWidth) {
                return false;
            }
        }
        return true;
    }
}
