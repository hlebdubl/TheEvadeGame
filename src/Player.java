public class Player {
    private String name;
    private int score;
    private static int bestScore;

    //Constructor, sets up a basic player with name and 0 score once created
    Player(String name){
        this.name = name;
        score = 0;
        bestScore = 0;
    }

    //ADDS SCORE
    public void addScore(int add){
        score += add;
    }
    //SET A NEW HIGH SCORE IF NEEDED
    public void compareScore(){
        if(score > bestScore){
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
    public int getBest(){
        return bestScore;
    }

}

