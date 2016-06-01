import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String regexIP4 = "(?i)^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|0x[0-9a-f]{2}|0[0-7]{3})\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|0x[0-9a-f]{2}|0[0-7]{3})\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|0x[0-9a-f]{2}|0[0-7]{3})\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|0x[0-9a-f]{2}|0[0-7]{3})|0x[0-9a-f]{8}|(1|[1-7][0-7]|[1-3][0-7]{2})[0-7]{8}|[1-9][0-9]{7,9})$";
        String regexDomain = "(?i)^https?\\:\\/\\/([a-z0-9]+|[a-z0-9][a-z0-9\\-?\\.]*[a-z0-9])\\.[a-z]+\\/?$";

        regex_match("ip.txt", regexIP4);
        regex_match("domain.txt", regexDomain);
    }

    public static void regex_match(String pathToTxtFileWithDataList, String regex) throws IOException {
        String newFileName;
        if (pathToTxtFileWithDataList.contains("."))
            newFileName = pathToTxtFileWithDataList.substring(0, pathToTxtFileWithDataList.lastIndexOf('.')) + "_result" + pathToTxtFileWithDataList.substring(pathToTxtFileWithDataList.lastIndexOf('.'));
        else
            newFileName = pathToTxtFileWithDataList + "_result";

        BufferedWriter writer = new BufferedWriter(new FileWriter(newFileName));
        Scanner scanner = new Scanner(new File(pathToTxtFileWithDataList));

        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            if (s.isEmpty())
                continue;

            if (s.matches(regex))
                writer.write(s + "\t" + "match");
            else
                writer.write(s + "\t" + "no match");

            if (scanner.hasNext())
                writer.newLine();
        }

        scanner.close();
        writer.close();
    }

}
