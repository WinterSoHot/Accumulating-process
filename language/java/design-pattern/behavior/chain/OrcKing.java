package gu.java.pattern.behavior.chain;

/**
 * @author gudongxian
 * @create 2018-04-27 上午10:48
 **/
public class OrcKing {
    RequestHandler chain;

    public OrcKing() {
        buildChain();
    }

    private void buildChain() {
        chain = new OrcCommander(new OrcOfficer(new OrcSoldier(null)));
    }

    public void makeRequest(Request request) {
        chain.handleRequest(request);
    }
}
