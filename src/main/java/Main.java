import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        regex_match("in.txt", "(?i)^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|0x[0-9a-f]{2}|0[0-7]{3})\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|0x[0-9a-f]{2}|0[0-7]{3})\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|0x[0-9a-f]{2}|0[0-7]{3})\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|0x[0-9a-f]{2}|0[0-7]{3})|0x[0-9a-f]{8}|(1|[1-7][0-7]|[1-3][0-7]{2})[0-7]{8}|[1-9][0-9]{7,9})$");
    }

    public static void regex_match(String pathToTxtFileWithDataList, String regex) throws IOException {
        String newFileName;
        if (pathToTxtFileWithDataList.contains("."))
            newFileName = pathToTxtFileWithDataList.substring(0, pathToTxtFileWithDataList.lastIndexOf('.')) + "_result" + pathToTxtFileWithDataList.substring(pathToTxtFileWithDataList.lastIndexOf('.'));
        else
            newFileName = pathToTxtFileWithDataList + "_result";
        File resultFile = new File(newFileName);
        //resultFile.createNewFile();

        BufferedWriter writer = new BufferedWriter(new FileWriter(resultFile));
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

//    public static boolean check(String testedString, String regex){
//        Pattern p = Pattern.compile(regex);
//        Matcher m = p.matcher(testedString);
//        return m.matches();
//    }
}
