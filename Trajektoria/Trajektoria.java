package Trajektoria;

import java.util.ArrayList;
import java.util.List;

public class Trajektoria {
    public static void main(String[] args) {
        int height = 0;
        int width = 0;
        try {
            if (args.length != 2) {
                throw new Exception("klops");
            } else {
                String heightInput = args[0];
                String widthInput = args[1];
                if (!tryParseInt(heightInput) || !tryParseInt(widthInput)) {
                    throw new Exception("klops");
                }
                height = Integer.parseInt(heightInput);
                width = Integer.parseInt(widthInput);
                if (!isInputCorrect(height, width)) {
                    throw new Exception("klops");
                } else {
                    List<String> trajectoryOutput = getLinesToPrint(height, width);
                    printOutTrajectory(trajectoryOutput);
                }
            }
        } catch (Exception klopsException) {
            System.out.println(klopsException.getMessage());
        }

    } // main

    private static void printOutTrajectory(List<String> output) {
        for (String line : output) {
            System.out.println(line);
        }
    }

    private static List<String> getLinesToPrint(int height, int width) {
        List<String> linesToPrint = new ArrayList<>();
        int iterator = 1;
        if (height == 1) {
            linesToPrint.add("*");
            return linesToPrint;
        }
        for (int i = 0; i < height; i += iterator) {
            if (i >= width) {
                break;
            }
            for (int j = 0; j < width; ++j) {
                String line;
                if (i < linesToPrint.size()) {
                    line = linesToPrint.get(i);
                } else {
                    line = "";
                }
                if (i == (j % ((height * 2) - 2))) {
                    line = line + "*";
                } else if ((i + j) % ((height * 2) - 2) == 0) {
                    line = line + "*";
                } else {
                    line = line + " ";
                }
                if (i < linesToPrint.size()) {
                    linesToPrint.set(i, line);
                } else {
                    linesToPrint.add(line);
                }
            }
        }
        return linesToPrint;
    }

    private static boolean isInputCorrect(int h, int w) {
        int allowedHeight = 50000;
        int allowedWidth = 50000;
        if (h > allowedHeight || h < 1) return false;
        return w <= allowedWidth && w >= 1;
    }
    private static boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
