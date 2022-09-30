package com.example.demo.fsm.rule;

import com.example.demo.fsm.TransferContext;
import com.example.demo.fsm.event.CheckEventEnum;
import org.springframework.stereotype.Component;

@Component
public class ReinforceRule implements IRule{
    @Override
    public CheckEventEnum check(TransferContext context) {
        if(context.getInteger()%7 == 0){
            return CheckEventEnum.REINFORCE;
        }else {
            return CheckEventEnum.PASS;
        }
    }
}
