package com.example.demo.fsm.rule;

import com.example.demo.fsm.TransferContext;
import com.example.demo.fsm.event.CheckEventEnum;
import org.springframework.stereotype.Component;

@Component
public class StartRule implements IRule{
    @Override
    public CheckEventEnum check(TransferContext context) {
        return CheckEventEnum.PASS;
    }
}
