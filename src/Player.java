public class Player {
    private String name;
    private int score;
    private static int bestScore;


    Player(String name){
        this.name = name;
        score = 0;
        bestScore = 0;
    }
    public void addScore(int add){
        score += add;
    }
    public int getScore(){
        return score;
    }
    public String getName(){
        return name;
    }
    public void compareScore(){
        if(getScore() > bestScore){
            bestScore = score;
        }
    }
}
