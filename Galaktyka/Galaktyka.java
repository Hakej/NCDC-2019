package Galaktyka;

public class Galaktyka {
    public enum Direction {
        TO_RIGHT, TO_BOTTOM, TO_LEFT, TO_TOP;
    }
    public static void main(String[] args) {
        int telescopeSize;
        String orientation;
        try {
            if (args.length != 1) {
                throw new Exception("klops");
            } else {
                String input = args[0];
                String sizeInput = input.replaceAll("\\D+","");
                orientation = input.replaceAll("\\d", "");
                if (!tryParseInt(sizeInput)) {
                    throw new Exception("klops");
                }
                telescopeSize = Integer.parseInt(sizeInput);
                if (!isInputCorrect(telescopeSize, orientation)) {
                    throw new Exception("klops");
                } else {
                    String[][] galacticTable = galacticTable(telescopeSize, orientation);
                    printOutGalactic(galacticTable);
                    System.out.println(amountOfSpaces(telescopeSize));
                }
            }
        } catch (Exception klopsException) {
            System.out.println(klopsException.getMessage());
        }
    } // main
    private static void printOutGalactic(String[][] galacticTable) {
        for (String[] row : galacticTable) {
            for (String cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }
    private static String[][] galacticTable(int telescopeSize, String orientation) {
        int height = telescopeSize;
        int width = telescopeSize;
        if (orientation.equals("N") || orientation.equals("S")) {
            height += 2;
            width += 3;
        } else {
            height += 3;
            width += 2;
        }
        Direction startingDirection = startingDirection(orientation);
        Direction direction = startingDirection;
        String[][] galacticTable = new String[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                galacticTable[i][j] = " ";
            }
        }
        int T = 0;
        int L = 0;
        int B = height - 1;
        int R = width - 1;
        int TLimit = 0;
        int LLimit = 0;
        int BLimit = 0;
        int RLimit = 0;
        switch(orientation) {
            case "N":
                RLimit++;
                break;
            case "S":
                LLimit++;
                break;
            case "E":
                BLimit++;
                break;
            default:
                TLimit++;
                break;
        }
        int amountOfIterations = 0;
        int limit = (height * width) - amountOfSpaces(telescopeSize);
        boolean hasFirstIterationPassed = false;
        while ((L <= R) && (T <= B) && (amountOfIterations < limit)) {
            if((direction == startingDirection) && (hasFirstIterationPassed)) {
                if (startingDirection != Direction.TO_RIGHT) {
                    L++;
                }
                if (startingDirection != Direction.TO_LEFT) {
                    R--;
                }
                if (startingDirection != Direction.TO_TOP) {
                    B--;
                }
                if (startingDirection != Direction.TO_BOTTOM) {
                    T++;
                }
            }
            switch (direction) {
                case TO_RIGHT:
                    for (int i = L; i <= R - RLimit; i++) {
                        galacticTable[T][i] = "*";
                        amountOfIterations++;
                        if (amountOfIterations >= limit) {
                            break;
                        }
                    }
                    if ((direction == startingDirection) && (hasFirstIterationPassed)) {
                        L++;
                    } else {
                        hasFirstIterationPassed = true;
                    }
                    T++;
                    direction = Direction.TO_BOTTOM;
                    break;
                case TO_BOTTOM:
                    for (int i = T; i <= B - BLimit; i++) {
                        galacticTable[i][R] = "*";
                        amountOfIterations++;
                        if (amountOfIterations >= limit) {
                            break;
                        }
                    }
                    if ((direction == startingDirection) && (hasFirstIterationPassed)) {
                        T++;
                    } else {
                        hasFirstIterationPassed = true;
                    }
                    R--;
                    direction = Direction.TO_LEFT;
                    break;
                case TO_LEFT:
                    for (int i = R; i >= L + LLimit; i--) {
                        galacticTable[B][i] = "*";
                        amountOfIterations++;
                        if (amountOfIterations >= limit) {
                            break;
                        }
                    }
                    if ((direction == startingDirection) && (hasFirstIterationPassed)) {
                        R--;
                    } else {
                        hasFirstIterationPassed = true;
                    }
                    B--;
                    direction = Direction.TO_TOP;
                    break;
                case TO_TOP:
                    for (int i = B; i >= T + TLimit; i--) {
                        galacticTable[i][L] = "*";
                        amountOfIterations++;
                        if (amountOfIterations >= limit) {
                            break;
                        }
                    }
                    if ((direction == startingDirection) && (hasFirstIterationPassed)) {
                        B--;
                    } else {
                        hasFirstIterationPassed = true;
                    }
                    L++;
                    direction = Direction.TO_RIGHT;
                    break;
            }
        }
        return galacticTable;
    }
    private static Direction startingDirection(String orientation) {
        switch(orientation) {
            case "N":
                return Direction.TO_BOTTOM;
            case "S":
                return Direction.TO_TOP;
            case "E":
                return Direction.TO_LEFT;
            default:
                return Direction.TO_RIGHT;
        }
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
    private static int amountOfSpaces(int telescopeSize) {
        int amountOfSpaces = 3;
        for (int i = 1; i < telescopeSize; i++) {
            amountOfSpaces += 3 + (i - 1);
        }
        return amountOfSpaces;
    }
}