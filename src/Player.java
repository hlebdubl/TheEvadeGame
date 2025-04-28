public class Player {
    private String name;
    private int score;
    private static int bestScore;


    Player(String name){
        this.name = name;
        score = 0;
        bestScore = 0;
    }

    //ADDS SCORE PER A BLOC CROSSED
    public void addScore(int add){
        score += add;
    }
    //SET A NEW HIGH SCORE IF NEEDED
    public void compareScore(){
        if(getScore() > bestScore){
            bestScore = score;
        }
    }
    //GETTERS
    public int getScore(){
        return score;
    }
    public String getName(){
        return name;
    }

}

