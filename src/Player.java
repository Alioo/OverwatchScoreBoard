import java.util.HashMap;
import java.util.Map;

public class Player {
    private int damageDealt;
    private String region;
    private String platform;
    private String character;
    private int highestDamage;
    private Map<String, Integer> playersDamaged;
    private String mostDamagingAttack;
    private String userName;


    public Player(String region, String platform, String character, String userName) {
        this.region = region;
        this.platform = platform;
        this.character = character;
        this.userName = userName;
        this.playersDamaged = new HashMap<>();

    }

    public void dealDamage(int damage, String target, String attack) {
        damageDealt += damage;
        if(playersDamaged != null && playersDamaged.containsKey(target)) {
            int newDmg = playersDamaged.get(target);
            playersDamaged.put(target, newDmg + damage);
        } else {
            playersDamaged.put(target, damage);
        }
        if (damage > highestDamage) {
            highestDamage = damage;
            mostDamagingAttack = attack;
        }
    }

    private String getMostDamagedPlayer() {
        Map.Entry<String, Integer> maxDmg = null;
        for (Map.Entry<String, Integer> entry : playersDamaged.entrySet()) {
            if (maxDmg == null || entry.getValue().compareTo(maxDmg.getValue()) > 0) {
                maxDmg = entry;
            }
        }
        return maxDmg == null ? null : maxDmg.getKey();
    }

    public void printScore() {
        System.out.println(userName + " | " + character + " | " + damageDealt + "               | " + getMostDamagedPlayer() + " | " + mostDamagingAttack + " | " + highestDamage);
    }

    public String getUserName() {
        return userName;
    }

    public int getDamageDealt() {
        return damageDealt;
    }

    public void setDamageDealt(int damageDealt) {
        this.damageDealt = damageDealt;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public int getHighestDamage() {
        return highestDamage;
    }

    public void setHighestDamage(int highestDamage) {
        this.highestDamage = highestDamage;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }
}
