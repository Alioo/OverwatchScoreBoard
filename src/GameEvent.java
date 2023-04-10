import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class GameEvent {
    private String userName;
    private String region;
    private String platform;
    private String character;
    private String targetPlayer;
    private String targetCharacter;
    private String attack;
    private int damage;

    private static final String STATUS_OKAY = "successful";
    private static final String STATUS_NOT_FOUND = "not found";

    public GameEvent() throws IOException {
        Optional<JSONObject> jsonOption = getNextEvent();
        JSONObject json = null;
        String returnStatus;
        String statusReason;
        if (jsonOption.isPresent()) {
            json = jsonOption.get();
            returnStatus = (String) json.get("status");
            statusReason = (String) json.get("reason");
        } else {
            returnStatus = STATUS_NOT_FOUND;
            statusReason = "json is empty";
        }

        if (returnStatus.equals(STATUS_OKAY)) {
            JSONArray payload = json.getJSONArray("payload");
            JSONObject payloadMap = (JSONObject) payload.get(0);
            userName = payloadMap.getString("source_player_id");
            region = payloadMap.getString("region");
            platform = payloadMap.getString("platform");
            character = payloadMap.getString("source_character");
            targetPlayer = payloadMap.getString("target_player_id");
            targetCharacter = payloadMap.getString("target_character");
            attack = payloadMap.getString("method");
            damage = payloadMap.getInt("damage");
        } else {
            System.out.println(returnStatus + statusReason);
        }

    }
    private Optional<JSONObject> getNextEvent() {
        try {
            String json = IOUtils.toString(new URL("http://interview.wptdev.com/api/killfeed"));
            return Optional.of(new JSONObject(json));
        } catch (IOException e) {
            System.out.println("Error getting game event: " + e.getMessage());
            return Optional.of(new JSONObject());
        }
    }

    public String getUserName() {
        return userName;
    }

    public String getRegion() {
        return region;
    }

    public String getPlatform() {
        return platform;
    }

    public String getCharacter() {
        return character;
    }

    public String getTargetPlayer() {
        return targetPlayer;
    }

    public String getTargetCharacter() {
        return targetCharacter;
    }

    public String getAttack() {
        return attack;
    }

    public int getDamage() {
        return damage;
    }
}
