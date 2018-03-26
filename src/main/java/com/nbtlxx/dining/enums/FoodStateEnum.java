package com.nbtlxx.dining.enums;

/**
 * Created by chenxu on 18/3/19.
 */
public enum FoodStateEnum {
    NULL(-1,"参数非法"),
    ONLINE(0,"上线"),
    OFFLINE(1,"下线");


    private int state;
    private String stateInfo;

    FoodStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static FoodStateEnum stateOf(int state){
        for (FoodStateEnum stateEnum: values()){
            if (stateEnum.getState()== state){
                return stateEnum;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }
}
