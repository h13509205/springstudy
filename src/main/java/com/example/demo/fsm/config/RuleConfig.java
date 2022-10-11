package com.example.demo.fsm.config;

import java.util.List;

public class RuleConfig {

    private String name;

    private Integer priority;

    private String conditionFunction;

    private String hitEvent;

    private String unHitEvent;

    private List<ActionConfig> hitAction;

    private List<ActionConfig> unHitAction;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getConditionFunction() {
        return conditionFunction;
    }

    public void setConditionFunction(String conditionFunction) {
        this.conditionFunction = conditionFunction;
    }

    public String getHitEvent() {
        return hitEvent;
    }

    public void setHitEvent(String hitEvent) {
        this.hitEvent = hitEvent;
    }

    public String getUnHitEvent() {
        return unHitEvent;
    }

    public void setUnHitEvent(String unHitEvent) {
        this.unHitEvent = unHitEvent;
    }

    public List<ActionConfig> getHitAction() {
        return hitAction;
    }

    public void setHitAction(List<ActionConfig> hitAction) {
        this.hitAction = hitAction;
    }

    public List<ActionConfig> getUnHitAction() {
        return unHitAction;
    }

    public void setUnHitAction(List<ActionConfig> unHitAction) {
        this.unHitAction = unHitAction;
    }
}
