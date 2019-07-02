package org.my.oa.sys.domain;

public enum SexEnum {
    MAN("男", 1), WOMAN("女", 2);

    private String name;
    private int code;

    private SexEnum(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public static String getName(int code) {
        for (SexEnum sex : SexEnum.values()) {
            if (sex.getCode() == code) {
                return sex.getName();
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
