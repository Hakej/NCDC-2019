import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Krolik {

    public static void main(String[] args)
    {
        String searchedElementInput = "";
        if(args.length > 0)
        {
            searchedElementInput = args[0];
            searchedElementInput = searchedElementInput.replace("'", "");
        }

        String searchedElement = "{".concat(searchedElementInput).concat("}");
        List<List<String>> table = getTableFromFile();
        int searchedColumn = findColumnByElement(table, searchedElement);

        if(searchedColumn == -1) // if element was not found OR if there is more than 1 element in the table
        {
            System.out.println("klops");
        }
        else
        {
            int sum = 0;
            for (List<String> strings : table)
            {
                try
                {
                    sum += Integer.parseInt(strings.get(searchedColumn));
                }
                catch (NumberFormatException expected)
                {
                    // parseInt has failed, ignore it
                }
            }
            System.out.println(sum);
        }
    } // main

    private static List<List<String>> getTableFromFile()
    {
        List<List<String>> table = new ArrayList<>();
        Scanner csvFileBuffer = new Scanner(System.in);
        String cvsSplitBy = ",";
        String line = "";
        while (csvFileBuffer.hasNext())
        {
            line = csvFileBuffer.nextLine();
            List<String> wholeRow = Arrays.asList(line.split(cvsSplitBy));
            table.add(wholeRow);
        }
        csvFileBuffer.close();
        return table;
    }

    private static int findColumnByElement(List<List<String>> tableToSearch, String searchedElement)
    {
        int searchedColumn = -1;
        for (List<String> strings : tableToSearch)
        {
            if ((strings.indexOf(searchedElement)) != -1)
            {
                if (searchedColumn != -1)
                {
                    return -1;
                }
                else
                {
                    searchedColumn = strings.indexOf(searchedElement);
                }
            }
        }
        return searchedColumn;
    }
}