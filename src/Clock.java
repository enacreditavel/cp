import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Clock {
    public static void main(String[] args) {
        System.out.println(sevenSegmentify("13:40"));
    }
    public static String sevenSegmentify(String time) {
        StringBuilder line = new StringBuilder();
        /*
         * Check if the time starts with 0, to start with the first blank digit or not
         */
        if (time.startsWith("0")) {
            time = time.substring(1);
            line.append("   \n   \n   \n");
        }

        /*
         *  Converts all time characters to display format
         */
        for (int i = 0; i < time.length(); i++) {
            if (NUMBERS_DISPLAY.containsKey(time.substring(i, i + 1))) {
                line.append(NUMBERS_DISPLAY.get(time.substring(i, i + 1)));
                }

        }

        List<String> toDisplay = reorganizingLineToDisplay(line.toString());


        return creatingDisplay(toDisplay);
    }

    /*
     *  Method to Rearrange the String of All Numbers
     *  Receives a String with all the numbers in the format for the display
     *  Returns an Array with the Strings rearranged to be able to mount the display
     */
    private static List<String> reorganizingLineToDisplay(String line) {
        List<String> lineArray = List.of(line.split("\n"));
        List<String> reorganizedArray = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = i; j < lineArray.size(); j += 3) {
                reorganizedArray.add(lineArray.get(j));
            }
        }
        return reorganizedArray;
    }

    /*
     *  Method to create the properly formatted final String for the display
     *  Receives an Array containing the Strings of the parts of the numbers rearranged to assemble the display
     *  Returns the final String with the display mounted
     */
    private static String creatingDisplay(List<String> array) {
        List<String> finalArray = new ArrayList<>();
        for (int i = 0; i < array.size(); i += 5) {
            StringBuilder s = new StringBuilder();
            int endArray = Math.min(i + 5, array.size());
            for (int j = i; j < endArray; j++) {
                s.append(array.get(j));
            }
            finalArray.add(s.toString());
        }

        return String.join("\n", finalArray);
    }

    /*
     *  Method for identifying the number and setting the format for the display
     *  Returns a String with the combination of characters that form the number (0 to 9) and the :
     */

    private static final Map<String, String> NUMBERS_DISPLAY = Map.ofEntries(
            Map.entry("1", "   \n  |\n  |\n"),
            Map.entry("2", " _ \n _|\n|_ \n"),
            Map.entry("3", " _ \n _|\n _|\n"),
            Map.entry("4", "   \n|_|\n  |\n"),
            Map.entry("5", " _ \n|_ \n _|\n"),
            Map.entry("6", " _ \n|_ \n|_|\n"),
            Map.entry("7", " _ \n  |\n  |\n"),
            Map.entry("8", " _ \n|_|\n|_|\n"),
            Map.entry("9", " _ \n|_|\n _|\n"),
            Map.entry("0", " _ \n| |\n|_|\n"),
            Map.entry(":", "   \n . \n . \n"));


}
