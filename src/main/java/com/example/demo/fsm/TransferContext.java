package com.example.demo.fsm;

import com.example.demo.fsm.rule.IRule;
import com.example.demo.fsm.state.CheckStateEnum;

import java.util.LinkedList;

public class TransferContext {
    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public CheckStateEnum getCheckState() {
        return checkState;
    }

    public void setCheckState(CheckStateEnum checkState) {
        this.checkState = checkState;
    }

    public LinkedList<IRule> getRuleList() {
        return ruleList;
    }

    public void setRuleList(LinkedList<IRule> ruleList) {
        this.ruleList = ruleList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer integer;

    private Integer id;

    private CheckStateEnum checkState;

    private LinkedList<IRule> ruleList;
}
