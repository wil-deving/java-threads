package Nodes;

public class BContractNode {

    public String nodeNo;
    public Integer riskLevelFlag;
    public boolean rulesExecuted;

    public BContractNode(String nodeNo, Integer riskLevelFlag, boolean rulesExecuted) {
        this.nodeNo = nodeNo;
        this.riskLevelFlag = riskLevelFlag;
        this.rulesExecuted = rulesExecuted;
    }

    public String getNodeNo() {
        return nodeNo;
    }

    public void setNodeNo(String nodeNo) {
        this.nodeNo = nodeNo;
    }

    public Integer getRiskLevelFlag() {
        return riskLevelFlag;
    }

    public void setRiskLevelFlag(Integer riskLevelFlag) {
        this.riskLevelFlag = riskLevelFlag;
    }

    public boolean isRulesExecuted() {
        return rulesExecuted;
    }

    public void setRulesExecuted(boolean rulesExecuted) {
        this.rulesExecuted = rulesExecuted;
    }

    public void executeRules() throws InterruptedException {
        //System.out.println("Node: " + this.getNodeNo());
        System.out.println("Node: " + this.getNodeNo() + " Initializing rules");
        if (this.getNodeNo().equals("1-1-1"))
            Thread.sleep(8000);
        else
            Thread.sleep(2000);

        System.out.println("Node: " + this.getNodeNo() + " Execute rules");
        if (this.getNodeNo().equals("1-1-2"))
            Thread.sleep(6000);
        else
            Thread.sleep(2000);

        System.out.println("Node: " + this.getNodeNo() + " Verify rules");
        if (this.getNodeNo().equals("1-1-3"))
            Thread.sleep(4000);
        else
            Thread.sleep(2000);

        System.out.println("Node: " + this.getNodeNo() + " Ending validation");
    }
}