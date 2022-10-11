package com.example.demo.fsm.action;

import com.example.demo.fsm.TransferContext;

@FunctionalInterface
public interface Action {

    void execute(TransferContext context);
}
