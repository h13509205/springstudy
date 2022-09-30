package com.example.demo.fsm.rule;

import com.example.demo.fsm.TransferContext;
import com.example.demo.fsm.event.CheckEventEnum;
import org.springframework.stereotype.Component;

@Component
public class InterruptRule implements IRule{
    @Override
    public CheckEventEnum check(TransferContext context) {
        if(context.getInteger()%6 == 0){
            return CheckEventEnum.INTERRPUT;
        }else {
            return CheckEventEnum.PASS;
        }
    }
}
