import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Agent2 {
    private static int MY_ID;
    private static int HEIGHT;
    private static int WIDTH;
    private static final HashMap<String, int[]> NEXT_POS;
    static
    {
        NEXT_POS = new HashMap<String, int[]>();
        int[] up_next_pos = {-1, 0};
        NEXT_POS.put("UP", up_next_pos);

        int[] down_next_pos = {1, 0};
        NEXT_POS.put("DOWN", down_next_pos);

        int[] right_next_pos = {0, 1};
        NEXT_POS.put("RIGHT", right_next_pos);

        int[] left_next_pos = {0, -1};
        NEXT_POS.put("LEFT", left_next_pos);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        MY_ID = scanner.nextInt();
        HEIGHT = scanner.nextInt();
        WIDTH = scanner.nextInt();
        final String[][] GRID = new String[HEIGHT][WIDTH];

        while (true) {
            System.err.println(
                    String.format("%d %d %d", MY_ID, HEIGHT, WIDTH));
            String[] directions = {"UP", "RIGHT", "DOWN", "LEFT"};
            System.out.println(
                    directions[(new Random()).nextInt(directions.length)]);

            for(int i=0; i<HEIGHT+1; i++){
                System.err.println(String.format("%d", i));
                String line = scanner.nextLine();
                System.err.println(line);
                // GRID[i] = line.split(" ");
            }

            System.err.println("----------------");

            for(int i=0; i<HEIGHT; i++){
                // System.err.println(String.format("%d", i));
                // System.err.println(String.join(" ", Arrays.asList(GRID[i])));
            }

            // Simulate move for each directions
            for(String direction: directions) {
                // System.err.println(direction);
                String[][] grid_copy = GRID.clone();

                switch (direction) {
                    case "UP":
                        // String[][] next_grid = move_up(Integer.toString(MY_ID),
                                // grid_copy);
                        break;
                    case "DOWN":
                        break;
                    case "RIGHT":
                        break;
                    case "LEFT":
                        break;
                }
            }
        }
    }

    private static String[][] move_up(String player_id, String[][] grid) {
        for(int col=0; col<WIDTH; col++) {
            for (int y = 0; y<HEIGHT; y++) {
                if (grid[y][col].equals(player_id)) {
                    single_move_up(player_id, y, col, grid);
                }
            }
        }

        return grid;
    }

    private static boolean single_move_up(String player_id, int y, int x, String[][] grid) {
        int next_y = y - 1;
        if(next_y < 0) { // player moves out of the map
            move_player(player_id, y, x, "UP", true, grid);
            return true;
        }

        String above_cell_state = grid[next_y][x];
        if(cell_has_player(above_cell_state)){
            single_move_up(above_cell_state, y - 1, x, grid);
            single_move_up(player_id, y, x, grid);
        } else {
            move_player(player_id, y, x, "UP", above_cell_state.equals("x"), grid);
        }

        return true;
    }

    private static void move_player(String player_id, int y, int x, String direction,
                             boolean remove_after, String[][] grid) {
        if(!remove_after) {
            int[] dir_next_pos = NEXT_POS.get(direction);
            int[] next_pos_coord = {y+dir_next_pos[0], x+dir_next_pos[1]};
            grid[next_pos_coord[0]][next_pos_coord[1]] = player_id;
        }
        grid[y][x] = "-";
    }

    private static boolean cell_has_player(String cell_value) {
        // Check if a grid cell has a player on it
        return cell_value.equals("0") || cell_value.equals("1");
    }
}
