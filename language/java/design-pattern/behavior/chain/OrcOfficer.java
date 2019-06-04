package gu.java.pattern.behavior.chain;

/**
 * @author gudongxian
 * @create 2018-04-27 上午10:49
 **/
public class OrcOfficer extends RequestHandler {


    public OrcOfficer(RequestHandler next) {
        super(next);
    }

    @Override
    public void handleRequest(Request req) {
        if (req.getRequestType().equals(RequestType.TORTURE_PRISONER)) {
            printHandling(req);
            req.makeHandled();
        } else
            super.handleRequest(req);
    }

    @Override
    public String toString() {
        return "Orc Officer";
    }
}
