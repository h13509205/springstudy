package com.example.demo.fsm.rule;

import com.example.demo.fsm.TransferContext;
import com.example.demo.fsm.event.CheckEventEnum;

public interface IRule{
    default CheckEventEnum check(TransferContext context){ return CheckEventEnum.PASS; }
}
