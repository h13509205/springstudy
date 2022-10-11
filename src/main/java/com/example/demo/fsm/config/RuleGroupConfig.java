package com.example.demo.fsm.config;

import java.util.List;

public class RuleGroupConfig {

    private String name;

    private String verifyType;

    private List<RuleConfig> compositeRules;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVerifyType() {
        return verifyType;
    }

    public void setVerifyType(String verifyType) {
        this.verifyType = verifyType;
    }

    public List<RuleConfig> getCompositeRules() {
        return compositeRules;
    }

    public void setCompositeRules(List<RuleConfig> compositeRules) {
        this.compositeRules = compositeRules;
    }
}
