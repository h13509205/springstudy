package com.example.demo.fsm.rule;

import com.example.demo.fsm.TransferContext;
import com.example.demo.fsm.event.CheckEventEnum;
import org.springframework.stereotype.Component;

public class RemindRule implements IRule{
    @Override
    public CheckEventEnum check(TransferContext context) {
        if(context.getInteger()%5 == 0){
            return CheckEventEnum.REMIND;
        }else {
            return CheckEventEnum.PASS;
        }
    }


    @Override
    public int compareTo(IRule o) {
        return 0;
    }
}
