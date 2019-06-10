package Paprotka;

import java.util.*;

public class Paprotka {
    public static void main(String[] args) {
        List<List<String>> tableOfStrings;
        Integer pot = 0;
        List<Integer> primeFactorsFromInput = new ArrayList<>();
        try {
            tableOfStrings = getTableOfStringsFromFile();
            if (!isTableCorrectSize(tableOfStrings)) {
                throw new Exception("klops");
            }
            pot = getPotValue(tableOfStrings.get(0).get(0));
            if (tableOfStrings.size() == 2) {
                primeFactorsFromInput = getPrimeFactors(tableOfStrings.get(1));
            }

            Integer multiplicationOfPrimeFactors = multiplyIntegers(primeFactorsFromInput);
            if (pot == 0) {
                System.out.println(multiplicationOfPrimeFactors);
            } else {
                if (multiplicationOfPrimeFactors.equals(pot)) {
                    System.out.println(pot);
                } else if (multiplicationOfPrimeFactors > pot) {
                    throw new Exception("klops");
                } else {
                    List<Integer> primeFactorsOfPot = getFactors(pot);
                    List<Integer> missingFactors = getMissingFactors(primeFactorsOfPot, primeFactorsFromInput);
                    System.out.println(pot);
                    System.out.println(missingFactors.toString().replace("[", "").replace("]", ""));
                }
            }
        } catch(Exception klopsException) {
            System.out.println(klopsException.getMessage());
        }
    } // main
    private static List<Integer> getMissingFactors(List<Integer> primeFactorsOfPot, List<Integer> primeFactorsFromInput) {
        if (primeFactorsFromInput.isEmpty()) {
            return primeFactorsOfPot;
        }
        List<Integer> missingFactors = new ArrayList<>();
        Collections.sort(primeFactorsFromInput);
        int potIterator = 0;
        int inputIterator = 0;
        int skippedFactors = 0;
        while (inputIterator < primeFactorsOfPot.size()) {
            Integer inputFactor;
            Integer potFactor;
            if ((inputIterator < primeFactorsFromInput.size()) && (potIterator < primeFactorsOfPot.size())) {
                potFactor = primeFactorsOfPot.get(potIterator);
                inputFactor = primeFactorsFromInput.get(inputIterator);
            } else {
                break;
            }
            if (potFactor.equals(inputFactor)) {
                ++potIterator;
                ++inputIterator;
            } else {
                missingFactors.add(potFactor);
                ++potIterator;
                if (inputFactor > potFactor) {
                    ++skippedFactors;
                }
            }
        }
        for (int i = inputIterator; i < primeFactorsOfPot.size() - skippedFactors; ++i) {
            missingFactors.add(primeFactorsOfPot.get(i));
        }
        return missingFactors;
    }
    private static Integer multiplyIntegers(List<Integer> primeFactors) {
        if (primeFactors.isEmpty()) {
            return 0;
        }
        Integer result = 1;
        for (Integer value : primeFactors) {
            result *= value;
        }
        return result;
    }
    private static List<Integer> getPrimeFactors(List<String> strings) throws Exception {
        List<Integer> primeFactors = new ArrayList<>();
        for (String singleCell : strings) {
            if (tryParseInteger(singleCell)) {
                primeFactors.add(Integer.parseInt(singleCell));
            } else {
                throw new Exception("klops");
            }
        }
        return primeFactors;
    }
    private static Integer getPotValue(String potValueAsString) throws Exception {
        if (potValueAsString.isEmpty()) {
            return 0;
        }
        if (!tryParseInteger(potValueAsString)) {
            throw new Exception("klops");
        }
        return Integer.parseInt(potValueAsString);
    }
    private static boolean isTableCorrectSize(List<List<String>> tableToCheck) {
        if ((tableToCheck.size() != 2) && (tableToCheck.size() != 1)) {
            return false;
        }
        return tableToCheck.get(0).size() <= 1;
    }
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
    private static boolean tryParseInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private static List<Integer> getFactors(int value) {
        List<Integer> a = new ArrayList<>();
        int j;
        int num = value;
        while (num % 2 == 0) {
            a.add(2);
            num /= 2;
        }
        j = 3;
        while (j <= Math.sqrt(num) + 1) {
            if (num % j == 0) {
                a.add(j);
                num /= j;
            } else {
                j += 2;
            }
        }
        if (num > 1) {
            a.add(num);
        }
        return a;
    }
}
