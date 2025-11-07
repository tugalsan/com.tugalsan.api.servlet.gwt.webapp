package com.tugalsan.api.servlet.gwt.webapp.server;

import module com.tugalsan.api.function;
import module com.tugalsan.api.os;
import module com.tugalsan.api.servlet.gwt.webapp;
import module com.tugalsan.api.thread;
import java.util.concurrent.*;

import module javax.servlet.api;

abstract public class TS_SGWTExecutor implements TGS_FuncMTU_In4<TS_ThreadSyncTrigger, HttpServletRequest, TGS_SGWTFuncBase, Object> {

    abstract public String name();

    public int timeout_seconds() {
        return 60;
    }

    public Semaphore semaphore = new Semaphore(TS_OsCpuUtils.getProcessorCount() - 1);

    abstract public TS_SGWTValidationResult validate(TS_ThreadSyncTrigger servletKillTrigger, HttpServletRequest request, TGS_SGWTFuncBase funcBase);

    public static void ifValidExecute(TS_ThreadSyncTrigger servletKillTrigger, TS_SGWTExecutor executor, HttpServletRequest rq, TGS_SGWTFuncBase funcBase) {
        var pack = executor.validate(servletKillTrigger, rq, funcBase);
        if (!pack.result()) {
            return;
        }
        executor.run(servletKillTrigger, rq, funcBase, pack.data());
    }
}
