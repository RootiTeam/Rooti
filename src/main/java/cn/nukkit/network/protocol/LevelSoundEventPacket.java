package cn.nukkit.network.protocol;


import cn.nukkit.math.Vector3f;

public class LevelSoundEventPacket extends DataPacket {
    public static final byte NETWORK_ID = ProtocolInfo.LEVEL_SOUND_EVENT_PACKET;

    public static final byte SOUND_ITEM_USE_ON = 0;
    public static final byte SOUND_HIT = 1;
    public static final byte SOUND_STEP = 2;
    public static final byte SOUND_JUMP = 3;
    public static final byte SOUND_BREAK = 4;
    public static final byte SOUND_PLACE = 5;
    public static final byte SOUND_HEAVY_STEP = 6;
    public static final byte SOUND_GALLOP = 7;
    public static final byte SOUND_FALL = 8;
    public static final byte SOUND_AMBIENT = 9;
    public static final byte SOUND_AMBIENT_BABY = 10;
    public static final byte SOUND_AMBIENT_IN_WATER = 11;
    public static final byte SOUND_BREATHE = 12;
    public static final byte SOUND_DEATH = 13;
    public static final byte SOUND_DEATH_IN_WATER = 14;
    public static final byte SOUND_DEATH_TO_ZOMBIE = 15;
    public static final byte SOUND_HURT = 16;
    public static final byte SOUND_HURT_IN_WATER = 17;
    public static final byte SOUND_MAD = 18;
    public static final byte SOUND_BOOST = 19;
    public static final byte SOUND_BOW = 20;
    public static final byte SOUND_SQUISH_BIG = 21;
    public static final byte SOUND_SQUISH_SMALL = 22;
    public static final byte SOUND_FALL_BIG = 23;
    public static final byte SOUND_FALL_SMALL = 24;
    public static final byte SOUND_SPLASH = 25;
    public static final byte SOUND_FIZZ = 26;
    public static final byte SOUND_FLAP = 27;
    public static final byte SOUND_SWIM = 28;
    public static final byte SOUND_DRINK = 29;
    public static final byte SOUND_EAT = 30;
    public static final byte SOUND_TAKEOFF = 31;
    public static final byte SOUND_SHAKE = 32;
    public static final byte SOUND_PLOP = 33;
    public static final byte SOUND_LAND = 34;
    public static final byte SOUND_SADDLE = 35;
    public static final byte SOUND_ARMOR = 36;
    public static final byte SOUND_ADD_CHEST = 37;
    public static final byte SOUND_THROW = 38;
    public static final byte SOUND_ATTACK = 39;
    public static final byte SOUND_ATTACK_NODAMAGE = 40;
    public static final byte SOUND_WARN = 41;
    public static final byte SOUND_SHEAR = 42;
    public static final byte SOUND_MILK = 43;
    public static final byte SOUND_THUNDER = 44;
    public static final byte SOUND_EXPLODE = 45;
    public static final byte SOUND_FIRE = 46;
    public static final byte SOUND_IGNITE = 47;
    public static final byte SOUND_FUSE = 48;
    public static final byte SOUND_STARE = 49;
    public static final byte SOUND_SPAWN = 50;
    public static final byte SOUND_SHOOT = 51;
    public static final byte SOUND_BREAK_BLOCK = 52;
    public static final byte SOUND_REMEDY = 53;
    public static final byte SOUND_UNFECT = 54;
    public static final byte SOUND_LEVELUP = 55;
    public static final byte SOUND_BOW_HIT = 56;
    public static final byte SOUND_BULLET_HIT = 57;
    public static final byte SOUND_EXTINGUISH_FIRE = 58;
    public static final byte SOUND_ITEM_FIZZ = 59;
    public static final byte SOUND_CHEST_OPEN = 60;
    public static final byte SOUND_CHEST_CLOSED = 61;
    public static final byte SOUND_SHULKERBOX_OPEN = 62;
    public static final byte SOUND_SHULKERBOX_CLOSED = 63;
    public static final byte SOUND_POWER_ON = 64;
    public static final byte SOUND_POWER_OFF = 65;
    public static final byte SOUND_ATTACH = 66;
    public static final byte SOUND_DETACH = 67;
    public static final byte SOUND_DENY = 68;
    public static final byte SOUND_TRIPOD = 69;
    public static final byte SOUND_POP = 70;
    public static final byte SOUND_DROP_SLOT = 71;
    public static final byte SOUND_NOTE = 72;
    public static final byte SOUND_THORNS = 73;
    public static final byte SOUND_PISTON_IN = 74;
    public static final byte SOUND_PISTON_OUT = 75;
    public static final byte SOUND_PORTAL = 76;
    public static final byte SOUND_WATER = 77;
    public static final byte SOUND_LAVA_POP = 78;
    public static final byte SOUND_LAVA = 79;
    public static final byte SOUND_BURP = 80;
    public static final byte SOUND_BUCKET_FILL_WATER = 81;
    public static final byte SOUND_BUCKET_FILL_LAVA = 82;
    public static final byte SOUND_BUCKET_EMPTY_WATER = 83;
    public static final byte SOUND_BUCKET_EMPTY_LAVA = 84;
    public static final byte SOUND_GUARDIAN_FLOP = 85;
    public static final byte SOUND_ELDERGUARDIAN_CURSE = 86;
    public static final byte SOUND_MOB_WARNING = 87;
    public static final byte SOUND_MOB_WARNING_BABY = 88;
    public static final byte SOUND_TELEPORT = 89;
    public static final byte SOUND_SHULKER_OPEN = 90;
    public static final byte SOUND_SHULKER_CLOSE = 91;
    public static final byte SOUND_HAGGLE = 92;
    public static final byte SOUND_HAGGLE_YES = 93;
    public static final byte SOUND_HAGGLE_NO = 94;
    public static final byte SOUND_HAGGLE_IDLE = 95;
    public static final byte SOUND_CHORUSGROW = 96;
    public static final byte SOUND_CHORUSDEATH = 97;
    public static final byte SOUND_GLASS = 98;
    public static final byte SOUND_CAST_SPELL = 99;
    public static final byte SOUND_PREPARE_ATTACK = 100;
    public static final byte SOUND_PREPARE_SUMMON = 101;
    public static final byte SOUND_PREPARE_WOLOLO = 102;
    public static final byte SOUND_FANG = 103;
    public static final byte SOUND_CHARGE = 104;
    public static final byte SOUND_CAMERA_TAKE_PICTURE = 105;
    public static final byte SOUND_DEFAULT = 106;
    public static final byte SOUND_UNDEFINED = 107;

    public int sound;
    public float x;
    public float y;
    public float z;
    public int extraData = -1;
    public int pitch = 1;
    public boolean unknownBool;
    public boolean disableRelativeVolume;

    @Override
    public void decode() {
        this.sound = this.getByte();
        Vector3f v = this.getVector3f();
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
        this.extraData = this.getVarInt();
        this.pitch = this.getVarInt();
        this.unknownBool = this.getBoolean();
        this.disableRelativeVolume = this.getBoolean();
    }

    @Override
    public void encode() {
        this.reset();
        this.putByte((byte) this.sound);
        this.putVector3f(this.x, this.y, this.z);
        this.putVarInt(this.extraData);
        this.putVarInt(this.pitch);
        this.putBoolean(this.unknownBool);
        this.putBoolean(this.disableRelativeVolume);
    }

    @Override
    public byte pid() {
        return NETWORK_ID;
    }
}
