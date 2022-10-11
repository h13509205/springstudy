package com.example.demo.fsm.condition;

import com.example.demo.fsm.TransferContext;

@FunctionalInterface
public interface Condition {

    boolean evaluate(TransferContext context);
}
