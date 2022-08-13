package com.example.demo.mybatis.enums;

public enum UserNameEnum {
    HUWENTAO("huwentao"),WUSHUYAN("wushuyan");
    private UserNameEnum(String userName){
        this.userName = userName;
    }
    private String userName;
    public static UserNameEnum getEnum(String userName){
        for (UserNameEnum u : UserNameEnum.values()){
            if(u.userName.equals(userName.toLowerCase())){
                return u;
            }
        }
        throw new RuntimeException("no enum qualified");
    }

    @Override
    public String toString() {
        return userName;
    }
}
