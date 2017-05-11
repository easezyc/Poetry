package com.bnu.zhuyongchun.poetry.entity;

/**
 * Created by zhuyongchun on 2017/5/8.
 */
public class Config {
    private volatile static Config config;
    private static String unselected_color="#FF000000",selected_color="#97FFFF";
    private Config (){}
    public static Config getConfig() {
        if (config == null) {
            synchronized (Config.class) {
                if (config == null) {
                    config = new Config();
                }
            }
        }
        return config;
    }
    public String getUnselected_color()
    {
        return unselected_color;
    }
    public String getSelected_color()
    {
        return selected_color;
    }
}
