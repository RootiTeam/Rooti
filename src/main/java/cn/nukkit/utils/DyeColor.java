package cn.nukkit.utils;

public enum DyeColor {


    BLACK(0, 15, "Black", BlockColor.BLACK_BLOCK_COLOR),
    RED(1, 14, "Red", BlockColor.RED_BLOCK_COLOR),
    GREEN(2, 13, "Green", BlockColor.GREEN_BLOCK_COLOR),
    BROWN(3, 12, "Brown", BlockColor.BROWN_BLOCK_COLOR),
    BLUE(4, 11, "Blue", BlockColor.BLUE_BLOCK_COLOR),
    PURPLE(5, 10, "Purple", BlockColor.PURPLE_BLOCK_COLOR),
    CYAN(6, 9, "Cyan", BlockColor.CYAN_BLOCK_COLOR),
    LIGHT_GRAY(7, 8, "Light Gray", BlockColor.LIGHT_GRAY_BLOCK_COLOR),
    GRAY(8, 7, "Gray", BlockColor.GRAY_BLOCK_COLOR),
    PINK(9, 6, "Pink", BlockColor.PINK_BLOCK_COLOR),
    LIME(10, 5, "Lime", BlockColor.LIME_BLOCK_COLOR),
    YELLOW(11, 4, "Yellow", BlockColor.YELLOW_BLOCK_COLOR),
    LIGHT_BLUE(12, 3, "Light Blue", BlockColor.LIGHT_BLUE_BLOCK_COLOR),
    MAGENTA(13, 2, "Magenta", BlockColor.MAGENTA_BLOCK_COLOR),
    ORANGE(14, 1, "Orange", BlockColor.ORANGE_BLOCK_COLOR),
    WHITE(15, 0, "White", BlockColor.WHITE_BLOCK_COLOR);


    private int dyeColorMeta;
    private int dyedColorMeta;
    private String colorName;
    private BlockColor blockColor;


    private final static DyeColor[] BY_DYED_DATA;
    private final static DyeColor[] BY_DYE_DATA;

    DyeColor(int dyeColorMeta, int dyedColorMeta, String colorName, BlockColor blockColor) {
        this.dyeColorMeta = dyeColorMeta;
        this.dyedColorMeta = dyedColorMeta;
        this.colorName = colorName;
        this.blockColor = blockColor;
    }

    public BlockColor getColor() {
        return this.blockColor;
    }

    public int getDyeData() {
        return this.dyeColorMeta;
    }

    public int getDyedData() {
        return this.dyedColorMeta;
    }

    public String getName() {
        return this.colorName;
    }

    static {
        BY_DYE_DATA = values();
        BY_DYED_DATA = values();

        for (DyeColor color : values()) {
            BY_DYED_DATA[color.dyedColorMeta & 0x0f] = color;
            BY_DYE_DATA[color.dyeColorMeta & 0x0f] = color;
        }
    }

    public static DyeColor getByDyeData(int dyeColorMeta) {
        return BY_DYE_DATA[dyeColorMeta & 0x0f];
    }

    public static DyeColor getByDyedData(int dyedColorMeta) {
        return BY_DYED_DATA[dyedColorMeta & 0x0f];
    }


}
