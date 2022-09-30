package com.example.demo.fsm.event;

public enum CheckEventEnum {

    PASS(0), INTERRPUT(1), REMIND(2), REINFORCE(3);

    private Integer event;

    CheckEventEnum(Integer integer){
        event = integer;
    }
}
