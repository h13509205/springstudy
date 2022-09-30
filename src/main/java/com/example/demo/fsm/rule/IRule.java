package com.example.demo.fsm.rule;

import com.example.demo.fsm.TransferContext;
import com.example.demo.fsm.event.CheckEventEnum;

public interface IRule{
    CheckEventEnum check(TransferContext context);
}
