package config;

/**
 * Created by yanni on 05.05.2016.
 */
public class Config {

    public final static int GRIDSIZE = 40;
    public final static int BORDER_X = 200;
    public final static int BORDER_Y = 50;
    public final static int ROWS = 10;
    public final static int COLlUMNS = 24;

    private static float FRAMERATE = 5;


    public static float getFRAMERATE() {
        return FRAMERATE;
    }

    public static void setFRAMERATE(float framerate){
        FRAMERATE = framerate;
    }

}
