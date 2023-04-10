import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Game game = new Game();
        while (true) {
            GameEvent event = new GameEvent();
            game.consumeEvent(event);
            game.printScoreBoard();
            Thread.sleep(60000);
        }
    }
}