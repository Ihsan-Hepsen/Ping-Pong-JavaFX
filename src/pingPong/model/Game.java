package pingPong.model;

public class Game {

    private final Player player1;
    private final Player player2;
    private int round;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.round = 5; // 5 rounds by default
    }

    public Game(Player player1, Player player2, int round) {
        this(player1, player2);
        this.round = round;
    }


    public void updateScore(int playerNo, int newScore) {
        if (playerNo == 1) {
            player1.setScore(newScore);
        } else if (playerNo == 2){
            player2.setScore(newScore);
        }
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public int getRounds() {
        return round;
    }
}
