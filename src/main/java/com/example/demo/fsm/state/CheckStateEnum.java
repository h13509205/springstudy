package com.example.demo.fsm.state;

public enum CheckStateEnum {
    START(0), NORMAL(1), INTERRUPTED(2), NEED_REINFORCE(3), NEED_REMIND(4);

    private Integer state;

    CheckStateEnum(Integer integer){
        state = integer;
    }
}
