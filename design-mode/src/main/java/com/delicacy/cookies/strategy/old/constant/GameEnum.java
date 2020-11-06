package com.delicacy.cookies.strategy.old.constant;

/**
 * @author linzhenghui
 * @date 2020/11/5
 */
public enum GameEnum {

    /**
     * 游戏
     */
    CHOICE(1, "选择"),
    LIGATURE(2, "连线"),
    COLOUR(3, "填色");


    public final int type;
    public final String desc;

    GameEnum(int type, String desc){
        this.type = type;
        this.desc = desc;
    }


}
