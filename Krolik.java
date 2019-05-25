import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class InvalidElementException extends Exception{
    InvalidElementException(String s){
        super(s);
    }
}

public class Krolik {

    public static void main(String[] args){
        String searchedElementInput = "";
        if(args.length > 0){
            searchedElementInput = args[0];
            //searchedElementInput = searchedElementInput.replace("'", ""); // NCDC error?
        }

        String searchedElement = "{".concat(searchedElementInput).concat("}");
        List<List<String>> listOfLists = getListOfListsFromFile();

        try{
            int searchedColumnIndex = findColumnIndexByElement(listOfLists, searchedElement);
            int sum = 0;
            for (List<String> strings : listOfLists){
                try{
                    sum += Integer.parseInt(strings.get(searchedColumnIndex));
                }catch(NumberFormatException expected){
                    // parseInt has failed, ignore it
                }
            }
            System.out.println(sum);
        }catch(InvalidElementException IEE){
            System.out.println(IEE.getMessage());
        }
    } // main

    private static List<List<String>> getListOfListsFromFile(){
        List<List<String>> listOfLists = new ArrayList<>();
        Scanner csvFileBuffer = new Scanner(System.in);
        String csvSplitBy = ",";
        String line = "";
        while (csvFileBuffer.hasNext()){
            line = csvFileBuffer.nextLine();
            List<String> wholeRow = Arrays.asList(line.split(csvSplitBy));
            listOfLists.add(wholeRow);
        }
        csvFileBuffer.close();
        return listOfLists;
    }

    private static int findColumnIndexByElement(List<List<String>> tableToSearch, String searchedElement) throws InvalidElementException{
        int amountOfElemenentsFound = 0;
        int indexOfSearchedElement = 0;
        for (List<String> strings : tableToSearch){
            if ((strings.indexOf(searchedElement)) != -1){
                amountOfElemenentsFound++;
                indexOfSearchedElement = strings.indexOf(searchedElement);
            }
        }
        if(amountOfElemenentsFound != 1){
            throw new InvalidElementException("klops");
        }
        else{
            return indexOfSearchedElement;
        }
    }
}