package Galaktyka;

public class Galaktyka {
    public static void main(String[] args) {
        int telescopeSize;
        String orientation;
        try {
            if (args.length != 2) {
                throw new Exception("klops");
            } else {
                String sizeInput = args[0];
                orientation = args[1];
                if (!tryParseInt(sizeInput)) {
                    throw new Exception("klops");
                }
                telescopeSize = Integer.parseInt(sizeInput);
                if (!isInputCorrect(telescopeSize, orientation)) {
                    throw new Exception("klops");
                } else {
                    String[][] galacticTable = getGalacticTable(telescopeSize);
                    printOutGalactic(galacticTable);
                    System.out.println(galacticTable);
                }
            }
        } catch (Exception klopsException) {
            System.out.println(klopsException.getMessage());
        }
    } // main
    private static void printOutGalactic(String[][] galacticTable) {
        for (String[] row : galacticTable) {
            for (String cell : row) {
                if (cell == null) {
                    System.out.println(" ");
                } else {
                    System.out.print(cell);
                }
            }
            System.out.println();
        }
    }
    private static String[][] getGalacticTable(int telescopeSize) {
        int height = telescopeSize + 3;
        int width = telescopeSize + 2;
        String[][] galacticTable = new String[height][width];
        int T = 0;
        int L = 0;
        int B = height - 1;
        int R = width - 1;
        int direction = 0;
        int amountOfIterations = 0;
        while (L <= R && T <= B && amountOfIterations < 9) {
            switch (direction) {
                case 0:
                    for (int i = L; i <= R; i++) {
                        galacticTable[T][i] = "*";
                        amountOfIterations++;
                    }
                    T++;
                    break;
                case 1:
                    for (int i = T; i <= B; i++) {
                        galacticTable[i][R] = "*";
                        amountOfIterations++;
                    }
                    R--;
                    break;
                case 2:
                    for (int i = R; i >= L; i--) {
                        galacticTable[B][i] = "*";
                        amountOfIterations++;
                    }
                    B--;
                    break;
                case 3:
                    for (int i = B; i >= T; i--) {
                        galacticTable[i][L] = "*";
                        amountOfIterations++;
                    }
                    L++;
                    break;
            }
            direction = (direction + 1) % 4;
        }
        return galacticTable;
    }
    private static boolean isInputCorrect(int telescopeSize, String orientation) {
        switch(orientation) {
            case "N":
                break;
            case "S":
                break;
            case "E":
                break;
            case "W":
                break;
            default:
                return false;
        }
        return (telescopeSize >= 1) && (telescopeSize <= 10000);
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