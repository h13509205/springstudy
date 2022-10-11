package com.example.demo.fsm;

import com.example.demo.fsm.TransferContext;
import com.example.demo.fsm.rule.IRule;
import com.example.demo.fsm.rule.RuleFactory;
import com.example.demo.fsm.state.CheckStateEnum;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.LinkedList;

public class RuleChecker {
    private TransferContext transferContext;

    private RuleFactory ruleFactory;

    RuleChecker(TransferContext transferContext, RuleFactory ruleFactory){
        Assert.notNull(transferContext,"transferContext should not be null");
        Assert.notNull(transferContext,"ruleFactory should not be null");
        this.transferContext = transferContext;
        this.ruleFactory = ruleFactory;
    }

    public CheckStateEnum start(){
        initRuleChain(transferContext);
        return CheckStateEnum.NORMAL;
    }

    private LinkedList<IRule> initRuleChain(TransferContext context){
        LinkedList<IRule> rules = new LinkedList<>();

        return rules;
    }
}
