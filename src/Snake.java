import java.util.ArrayList;

public class Snake {
    public static void main(String[] args) {
        // Exemplo de Input conforme solicitado: ArrayList<String>
        ArrayList<String> grid = new ArrayList<>();
        grid.add("           ");
        grid.add(" >>v  >>>h ");
        grid.add(" ^ >>>^ v  ");
        grid.add(" ^<<<<<<<  ");
        grid.add("           ");

        ArrayList<ArrayList<Integer>> caminho = findSnakeOnGrid(grid);

        System.out.println(caminho);
    }
    public static ArrayList<ArrayList<Integer>> findSnakeOnGrid(ArrayList<String> grid) {
        ArrayList<ArrayList<Integer>> coordinates = new ArrayList<ArrayList<Integer>>();

        int rows = grid.size();

        Integer currX = -1;
        Integer currY = -1;

        // Finding the Snake's head (h)
        boolean headFounded = false;

        // Iterate between the lines to find the line which contains the h
        for (int y = 0; y < rows; y++){
            String row = grid.get(y);

            // Iterate between the chars of the row until find the h
            for (int x = 0; x < row.length(); x++){
                // When the h is found, select the x and y coordinates
                if (row.charAt(x) == 'h'){
                    currX = x;
                    currY = y;
                    headFounded = true;
                    break;
                }
                if (headFounded) break;
            }
        }
        // Add the HEAD coordinates to the final list
        coordinates.add(getCoordinate(currX, currY));

        // Loop needed to track the snake body (<>^v)
        boolean foundNext = true;
        int lastX = -1;
        int lastY = -1;

        while (foundNext){
            foundNext = false;
            int nextX = -1;
            int nextY = -1;

            // Check if the left char (currX-1, currY) of the head (h) is '>'
            if(isValid(grid, currX - 1, currY) && getChar(grid, currX - 1, currY) == '>'){
                // Then check if track isn't going back
                if (currX - 1 != lastX || currY != lastY){
                    // Finally assume the new Coordinates to the next part of the body
                    nextX = currX - 1;
                    nextY = currY;
                    foundNext = true;
                }
            }
            // Check if the right char (currX+1, currY) of the head (h) is '<'
            else if(isValid(grid, currX + 1, currY) && getChar(grid, currX + 1, currY) == '<'){
                if (currX + 1 != lastX || currY != lastY){
                    nextX = currX + 1;
                    nextY = currY;
                    foundNext = true;
                }
            }
            // Check if the top char (currX, currY-1) of the head (h) is 'v'
            else if(isValid(grid, currX, currY - 1) && getChar(grid, currX, currY - 1) == 'v'){
                if (currX != lastX || currY - 1 != lastY){
                    nextX = currX;
                    nextY = currY - 1;
                    foundNext = true;
                }
            }
            // Check if the bottom char (currX, currY+1) of the head (h) is '^'
            else if(isValid(grid, currX, currY + 1) && getChar(grid, currX, currY + 1) == '^'){
                if (currX != lastX || currY + 1 != lastY){
                    nextX = currX;
                    nextY = currY + 1;
                    foundNext = true;
                }
            }

            // When the next part of the snake's body has been found, the coordinates will be added to the result
            if (foundNext){
                coordinates.add(getCoordinate(nextX, nextY));

                // Then update the position in the grid
                lastX = currX;
                lastY = currY;
                currX = nextX;
                currY = nextY;
            }
        }

        return coordinates;
    }
    /*
      Create a ArrayList whit booth coordinates x and y, to add to the list of coordinates
    */
    private static ArrayList<Integer> getCoordinate(Integer x, Integer y){
        ArrayList<Integer> coordinate = new ArrayList<>();
        coordinate.add(x);
        coordinate.add(y);
        return coordinate;
    }

    /*
      Get a character of the grid
    */
    private static char getChar(ArrayList<String> grid, int x, int y){
        return grid.get(y).charAt(x);
    }

    // Checks if the coordinate is within the grid boundaries
    private static boolean isValid(ArrayList<String> grid, int x, int y){
        if (y < 0 || y >= grid.size()) return false;
        String row = grid.get(y);
        if (x < 0 || x >= row.length()) return false;
        return true;
    }
}
