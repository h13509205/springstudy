package com.example.demo.fsm.rule;

import com.example.demo.fsm.TransferContext;
import com.example.demo.fsm.condition.RuleCondition;
import com.example.demo.fsm.event.CheckEventEnum;
import org.springframework.stereotype.Component;

import java.io.Serializable;

public class StartRule implements IRule, Serializable {
    @Override
    public CheckEventEnum check(TransferContext context) {
        return CheckEventEnum.PASS;
    }

    @Override
    public int compareTo(IRule o) {
        return 0;
    }

    @RuleCondition
    public boolean evaluate(TransferContext context){
        return false;
    }
}
