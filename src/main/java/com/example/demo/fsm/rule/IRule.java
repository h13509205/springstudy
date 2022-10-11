package com.example.demo.fsm.rule;

import com.example.demo.fsm.TransferContext;
import com.example.demo.fsm.event.CheckEventEnum;

public interface IRule extends Comparable<IRule>{
    String DEFAULT_NAME = "rule";

    String DEFAULT_DESCRIPTION = "description";

    Integer PRIORITY = Integer.MAX_VALUE-1;


    default Integer getPriority(){return PRIORITY;}

    default String getName(){return DEFAULT_NAME;}

    default String getDescription(){return DEFAULT_DESCRIPTION;}

    default CheckEventEnum check(TransferContext context){ return CheckEventEnum.PASS; }
}
