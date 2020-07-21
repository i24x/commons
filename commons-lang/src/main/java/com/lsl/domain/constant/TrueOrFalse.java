package com.lsl.domain.constant;

public enum TrueOrFalse {
    TRUE(true, "成功", "1", "是"), FALSE(false, "失败", "0", "否");

    boolean value;
    String intValueNum;
    String success;
    String valueCn;

    TrueOrFalse(boolean value, String success, String intValueNum, String valueCn) {
        this.value = value;
        this.success = success;
        this.intValueNum = intValueNum;
        this.valueCn = valueCn;
    }

    public boolean getValue() {
        return value;
    }

    public String getIntValueNum() {
        return intValueNum;
    }

    public String success() {
        return success;
    }

    public String getValueCn() {
        return valueCn;
    }

    public static TrueOrFalse match(boolean value) {
        if (value) {
            return TRUE;
        }
        return FALSE;
    }

}
