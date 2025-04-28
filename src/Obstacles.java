public class Obstacles {

    private int[][] layout;
    private final int OBSTACLEONE = 1;
    private final int OBSTACLETWO = 2;
    private final int OBSTACLETHREE = 3;
    private final int ROAD = 4;
    private final int PLAYER = 5;

    Obstacles(){
        layout = new int[30][40];
        layout[29][20] = PLAYER;
    }

    public void fillPlay(){




    }
    public void generateMore(){




    }
    public int[][] exportField(){
        return layout; 
    }
}
