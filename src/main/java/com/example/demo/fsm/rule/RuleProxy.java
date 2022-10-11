package com.example.demo.fsm.rule;

import com.example.demo.fsm.TransferContext;
import com.example.demo.fsm.action.Action;
import com.example.demo.fsm.condition.Condition;
import com.example.demo.fsm.event.CheckEventEnum;

import java.util.ArrayList;
import java.util.List;

public class RuleProxy implements IRule{
    protected String name;

    protected String description;

    protected Integer priority;

    protected CheckEventEnum hitEvent;

    protected CheckEventEnum unHitEvent;

    private Condition condition;

    private List<Action> hit_actions;

    private List<Action> unHit_actions;

    @Override
    public CheckEventEnum check(TransferContext context) {
        if(evaluate(context)){
            executeActions(hit_actions, context);
            return hitEvent;
        }else {
            executeActions(unHit_actions, context);
            return unHitEvent;
        }
    }

    public boolean evaluate(TransferContext context){
        if(condition != null){
            return condition.evaluate(context);
        }else {
            return false;
        }
    }

    public void executeActions(List<Action> actions, TransferContext context){
        for (Action a: actions) {
            a.execute(context);
        }
    }

    public void addHitAction(Action action){
        if (hit_actions == null){
            hit_actions = new ArrayList<>();
        }
        hit_actions.add(action);
    }

    public void addUnHitAction(Action action){
        if(unHit_actions == null){
            unHit_actions = new ArrayList<>();
        }
        unHit_actions.add(action);
    }


    public void setHitEvent(CheckEventEnum hitEvent) {
        this.hitEvent = hitEvent;
    }

    public void setUnHitEvent(CheckEventEnum unHitEvent) {
        this.unHitEvent = unHitEvent;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(IRule o) {
        if(getPriority() < o.getPriority()){
            return -1;
        } else if (getPriority() > o.getPriority()) {
            return 1;
        }else {
            return getName().compareTo(o.getName());
        }
    }
}
