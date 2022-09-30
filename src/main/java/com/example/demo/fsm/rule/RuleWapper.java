package com.example.demo.fsm.rule;

import com.example.demo.fsm.TransferContext;
import com.example.demo.fsm.event.CheckEventEnum;
import com.example.demo.fsm.state.CheckStateEnum;
import org.springframework.util.Assert;

public class RuleWapper implements IRule{

    private IRule rule;

    private CheckStateEnum checkState;

    RuleWapper(IRule rule){
        Assert.notNull(rule,"rule should not be null");
        this.rule = rule;
        checkState = CheckStateEnum.START;
    }

    @Override
    public CheckEventEnum check(TransferContext context) {
        if(checkState.equals(CheckStateEnum.NORMAL)){
            return CheckEventEnum.PASS;
        } else {
            return rule.check(context);
        }
    }
}
