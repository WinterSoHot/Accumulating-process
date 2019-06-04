package gu.java.pattern.behavior.chain;

import java.util.Objects;

/**
 * @author gudongxian
 * @create 2018-04-27 上午10:43
 **/
public class Request {

    private final RequestType requestType;
    private final String requestDecription;

    private boolean handled;

    public Request(RequestType requestType, String requestDecription) {
        this.requestType = Objects.requireNonNull(requestType);
        this.requestDecription = Objects.requireNonNull(requestDecription);
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public String getRequestDecription() {
        return requestDecription;
    }

    public boolean isHandled() {
        return handled;
    }

    public void makeHandled() {
        this.handled = true;
    }

    @Override
    public String toString() {
        return getRequestDecription();
    }
}
