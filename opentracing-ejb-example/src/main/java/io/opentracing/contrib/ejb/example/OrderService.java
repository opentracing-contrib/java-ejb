package io.opentracing.contrib.ejb.example;

import io.opentracing.SpanContext;
import io.opentracing.contrib.ejb.OpenTracingInterceptor;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import java.util.logging.Logger;

import static io.opentracing.contrib.ejb.OpenTracingInterceptor.SPAN_CONTEXT;

/**
 * This is a regular synchronous stateless EJB. It demonstrates how to get the span context for the span it's wrapped
 * on. This can be used to pass down the call chain, create child spans or add baggage items.
 *
 * @author Juraci Paixão Kröhling
 */
@Stateless
@Interceptors(OpenTracingInterceptor.class)
public class OrderService {
    private static final Logger log = Logger.getLogger(OrderService.class.getName());

    @Resource
    EJBContext ctx;

    @Inject
    InventoryService inventoryService;

    public void processOrderPlacement() {
        log.info("Placing order");
        Object ctxParentSpan = ctx.getContextData().get(SPAN_CONTEXT);
        if (ctxParentSpan instanceof SpanContext) {
            inventoryService.changeInventory((SpanContext) ctxParentSpan);
            return;
        }

        inventoryService.changeInventory(null);
    }
}
