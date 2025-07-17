package com.tugalsan.api.servlet.gwt.webapp.client;

import com.tugalsan.api.function.client.maythrowexceptions.unchecked.TGS_FuncMTU_In1;
import com.tugalsan.api.log.client.*;
import com.tugalsan.api.time.client.TGS_Time;
import com.tugalsan.api.function.client.maythrowexceptions.unchecked.TGS_FuncMTU;

public class TGC_SGWTCalller {
    
    private TGC_SGWTCalller(){
        
    }

    final private static TGC_Log d = TGC_Log.of(TGC_SGWTCalller.class);

    public static <T extends TGS_SGWTFuncBase> void async(T func, TGS_FuncMTU_In1<T> runnable) {
        async(func, runnable, null, null);
    }

    public static <T extends TGS_SGWTFuncBase> void async(boolean periodic, T func, TGS_FuncMTU_In1<T> runnable) {
        async(periodic, func, runnable, null, null);
    }

    public static <T extends TGS_SGWTFuncBase> void async(T func, TGS_FuncMTU_In1<T> runnable, TGS_FuncMTU closure) {
        async(func, runnable, null, closure);
    }

    public static <T extends TGS_SGWTFuncBase> void async(T func, TGS_FuncMTU_In1<T> runnable, TGS_FuncMTU_In1<Throwable> onFail) {
        async(func, runnable, onFail, null);
    }

    public static <T extends TGS_SGWTFuncBase> void async(T func, TGS_FuncMTU_In1<T> runnable, TGS_FuncMTU_In1<Throwable> onFail, TGS_FuncMTU closure) {
        async(false, func, runnable, onFail, closure);
    }

    public static <T extends TGS_SGWTFuncBase> void async(boolean periodic, T func, TGS_FuncMTU_In1<T> runnable, TGS_FuncMTU_In1<Throwable> onFail, TGS_FuncMTU closure) {
        if (!periodic) {
            lastTime.setToTodayAndNow();
        }
        d.ci("async", func.getSuperClassName(), func);
        TGC_SGWTService.singleton().call(func, new TGC_SGWTResponse(runnable, onFail, closure));
    }
    public static final TGS_Time lastTime = TGS_Time.of();

    /*
    @Deprecated //NOT SUPPORTED YET
    public static <T extends TGS_SGWTFuncBase> T syncPromise(T func) {
        Promise<String> promise = new Promise((resolve, reject) -> {
            Promise.resolve("done");
            Promise.reject(new RE("Whoops!"));
        });
//        promise.then((resolve)->{});
        return null;
    }

    @Deprecated //NOT SUPPORTED YET
    public static <T extends TGS_SGWTFuncBase> T syncCompletableFuture(T func) {
        tryy {
            var future = new CompletableFuture<TGS_SGWTFuncBase>();
            TGC_SGWTService.singleton().call(func, new AsyncCallback<TGS_SGWTFuncBase>() {
                @Override
                public void onFailure(Throwable t) {
                    future.completeExceptionally(t);
                }

                @Override
                public void onSuccess(TGS_SGWTFuncBase f) {
                    if (f == null) {
                        onFailure(new RE("onSuccess." + TGS_SGWTFuncBase.class.getSimpleName() + " == null"));
                    } else {
                        future.complete(f);
                    }
                }
            });
            return (T) future.get();
        } catch (Exception e) {
            TGS_FuncUtils.throwIfInterruptedException(e);
            d.ct("syncCompletableFuture", e);
            return null;
        }
    }
     */
}
