public class Obstacles {

    private int[][] layout;
    private final int OBSTACLEONE = 1;
    private final int OBSTACLETWO = 2;
    private final int OBSTACLETHREE = 3;
    private final int ROAD = 4;
    private final int PLAYER = 5;
    private int playerRow = 29;
    private int playerCol = 20;

    //Constructor, sets up a new field when created
    Obstacles() {
        layout = new int[30][40];
        initialField();
    }

    //CREATES THE FIRST ITERATION OF THE FIELD
    public void initialField() {
        for (int row = 29; row >= 0; row--) {
            for (int col = 39; col >= 0; col--) {
                int nextBlock = (int) (Math.random() * 4) + 1;
                if (!hasRoad(row, col) && nextBlock != 4) {
                    nextBlock = 4;
                }
                if (row == 29 && col == 20) {
                    layout[row][col] = PLAYER;
                } else if (nextBlock == 1) {
                    layout[row][col] = OBSTACLEONE;
                } else if (nextBlock == 2) {
                    layout[row][col] = OBSTACLETWO;
                } else if (nextBlock == 3) {
                    layout[row][col] = OBSTACLETHREE;
                } else if (nextBlock == 4) {
                    layout[row][col] = ROAD;
                }
            }
        }
    }

    //ONCE PLAYER MOVES UP, A NEW LAYER IS GENERATED AND THE OLD ONE IS DELETED

    //lowkey gotta look into if the parameters still match the current situation
    public void generateMore() {
        if (playerRow != 20) {
            for (int row = 29; row > 0; row--) {
                System.arraycopy(layout[row - 1], 0, layout[row], 0, 40);
            }
            for (int col = 39; col >= 0; col--) {
                int nextBlock = (int) (Math.random() * 4) + 1;
                if (nextBlock == 1) {
                    layout[0][col] = OBSTACLEONE;
                } else if (nextBlock == 2) {
                    layout[0][col] = OBSTACLETWO;
                } else if (nextBlock == 3) {
                    layout[0][col] = OBSTACLETHREE;
                } else if (nextBlock == 4) {
                    layout[0][col] = ROAD;
                }
            }
        }
    }

    //GETTERS AND SETTERS
    public int[][] exportField() {
        return layout;
    }
    public int getPlayerRow() {
        return playerRow;
    }
    public void setPlayerRow(int playerRow) {
        this.playerRow = playerRow;
    }
    public int getPlayerCol() {
        return playerCol;
    }
    public void setPlayerCol(int playerCol) {
        this.playerCol = playerCol;
    }

    //USED FOR GENERATING THE FIELD, CHECKS FOR ROAD NEAR ALREADY EXISTING ROAD
    //TO MAKE SURE THERE IS A CONTINUOUS PATH
    public boolean hasRoad(int row, int col) {
        if (col == 0 || col == 39) {
            return true;
        }
        else{
            return roadAdjacent(row, col);
        }
    }

    //HELPED METHOD FOR hasRoad() TO CHECK ADJACENT SPOTS
    public boolean roadAdjacent(int row, int col) {
        int moreThanOne = 0;

        if(row == 0){
            if (layout[row][col + 1] == ROAD) {
                moreThanOne ++;
            }
            if (layout[row][col - 1] == ROAD) {
                moreThanOne ++;
            }
            if (layout[row + 1][col] == ROAD) {
                moreThanOne ++;
            }
            return moreThanOne >= 2;
        }
        else{
            if (layout[row][col - 1] == ROAD) {
                moreThanOne ++;
            }
            if (layout[row][col + 1] == ROAD) {
                moreThanOne ++;
            }
            if(row == 29){
                if (layout[row - 1][col] == ROAD) {
                    moreThanOne ++;
                }
            }
            else if (layout[row + 1][col] == ROAD) {
                moreThanOne ++;
            }

        }
        return moreThanOne >= 2;
    }
}

