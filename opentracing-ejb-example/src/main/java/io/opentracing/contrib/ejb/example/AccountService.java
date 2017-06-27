package io.opentracing.contrib.ejb.example;

import io.opentracing.contrib.ejb.OpenTracingInterceptor;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import java.util.logging.Logger;

/**
 * This is a simple synchronous EJB, without any knowledge about span context or other OpenTracing semantics. All it
 * does is specify an interceptor and it's shown as the child of a parent span.
 *
 * @author Juraci Paixão Kröhling
 */
@Stateless
@Interceptors(OpenTracingInterceptor.class)
public class AccountService {
    private static final Logger log = Logger.getLogger(AccountService.class.getName());

    public void sendNotification() {
        log.info("Notifying the account owner about a new order");
    }
}
