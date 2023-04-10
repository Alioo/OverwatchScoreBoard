import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Game {
    private final List<Player> players;

    public Game() {
        players = new ArrayList<>();
    }
    public void printScoreBoard() {

        System.out.println("Player  |  Character  |  Total Damage Dealt  | Most Damaged Player | Most Damaging Attack | Most Damage from a Single Attack");
        System.out.println("____________________________________________________________________________________________________________________________");

        for (Player player : players) {
            player.printScore();
            System.out.println("____________________________________________________________________________________________________________________________");
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
        }
    }

    public void consumeEvent(GameEvent event) {
        String playerName = event.getUserName();
        Optional<Player> optionalPlayer = playerInGame(playerName);
        Player player;
        if(optionalPlayer.isPresent()) {
            player = optionalPlayer.get();
            player.setCharacter(event.getCharacter()); // Character may have changed
            player.dealDamage(event.getDamage(), event.getTargetPlayer(), event.getAttack());
            players.set(players.indexOf(player), player);
        } else {
            player = new Player(event.getRegion(), event.getPlatform(), event.getCharacter(), event.getUserName());
            player.dealDamage(event.getDamage(), event.getTargetPlayer(), event.getAttack());
            players.add(player);
        }
    }

    private Optional<Player> playerInGame(String name) {
        return  players.stream().filter(player -> player.getUserName().equals(name)).findFirst();
    }
}
