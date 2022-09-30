package com.example.demo.fsm.rule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class RuleFactory {
    private Map<String, IRule> ruleMap = new HashMap<>();

    @Autowired
    RuleFactory(Map<String, IRule> ruleMap){
        this.ruleMap = ruleMap;
    }

    public IRule getRule(String ruleName) {
        if (ruleMap.containsKey(ruleName)){
            return ruleMap.get(ruleName);
        }else {
            throw new RuntimeException("no such rule");
        }
    }
}
