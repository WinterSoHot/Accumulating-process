package gu.java.pattern.behavior.chain;

/**
 * @author gudongxian
 * @create 2018-04-27 上午10:49
 **/
public class OrcSoldier extends RequestHandler {


    public OrcSoldier(RequestHandler next) {
        super(next);
    }

    @Override
    public void handleRequest(Request req) {
        if (req.getRequestType().equals(RequestType.COLLECT_TAX)) {
            printHandling(req);
            req.makeHandled();
        } else
            super.handleRequest(req);
    }

    @Override
    public String toString() {
        return "Orc OrcSoldier";
    }
}
