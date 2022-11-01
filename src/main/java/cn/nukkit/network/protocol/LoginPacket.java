package cn.nukkit.network.protocol;

import lombok.ToString;
import cn.nukkit.entity.data.Skin;
import cn.nukkit.utils.Zlib;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * Created by on 15-10-13.
 * Updated by on 20-04-22.
 * Updated by on 01-11-22.
 */
@ToString
public class LoginPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.LOGIN_PACKET;

    public String username;
    public int protocol;
    public int deviceOS;
    public String deviceModel;
    public String languageCode;
    public byte gameEdition;
    public UUID clientUUID;
    public long clientId;
    public int clientInput;

    public Skin skin;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    @Override
    public void decode() {
        this.protocol = this.getInt();
        this.gameEdition = (byte) this.getByte();
        this.setBuffer(this.getByteArray(), 0);
        decodeChainData();
        decodeClientData();
    }

    @Override
    public void encode() {

    }

    public int getProtocol() {
        return protocol;
    }

    private void decodeChainData() {
        Map<String, List<String>> map = new Gson().fromJson(new String(this.get(getLInt()), StandardCharsets.UTF_8),
                new TypeToken<Map<String, List<String>>>() {
                }.getType());
        if (map.isEmpty() || !map.containsKey("chain") || map.get("chain").isEmpty()) return;
        List<String> chains = map.get("chain");
        for (String c : chains) {
            JsonObject chainMap = decodeToken(c);
            if (chainMap == null) continue;
            if (chainMap.has("extraData")) {
                JsonObject extra = chainMap.get("extraData").getAsJsonObject();
                if (extra.has("displayName")) this.username = extra.get("displayName").getAsString();
                if (extra.has("identity")) this.clientUUID = UUID.fromString(extra.get("identity").getAsString());
            }
        }
    }

    private void decodeClientData() {
        JsonObject clientData = decodeToken(new String(this.get(this.getLInt())));
        String skinId = null;
        if (clientData.has("ClientRandomId")) this.clientId = clientData.get("ClientRandomId").getAsLong();
        if (clientData.has("DeviceModel")) this.deviceModel = clientData.get("DeviceModel").getAsString();
        if (clientData.has("LanguageCode")) this.languageCode = clientData.get("LanguageCode").getAsString();
        if (clientData.has("DeviceOS")) this.deviceOS = clientData.get("DeviceOS").getAsInt();
        if (clientData.has("SkinId")) skinId = clientData.get("SkinId").getAsString();
        if (clientData.has("SkinData")) this.skin = new Skin(clientData.get("SkinData").getAsString(), skinId);
        if (clientData.has("CurrentInputMode")) this.clientInput = clientData.get("CurrentInputMode").getAsInt();
    }

    private JsonObject decodeToken(String token) {
        String[] base = token.split("\\.");
        if (base.length < 2) return null;
        return new Gson().fromJson(new String(Base64.getDecoder().decode(base[1]), StandardCharsets.UTF_8), JsonObject.class);
    }

    @Override
    public Skin getSkin() {
        return this.skin;
    }
}
