package game;
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
public class Player extends End {
    private String name;
    private String sign;
    private int score;
    public static int countPlayers = 0;
    public Player(String id, String name, String sign) {
        super(id); 
        this.name = name;
        this.sign = sign;
        this.score = 0;
        countPlayers++;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSign() {
        return sign;
    }
    public int getScore() {
        return score;
    }
    public void updateScore() {
        this.score++;
    }
    @Override
    public void finish() {
        this.score = 0;
    }
<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
