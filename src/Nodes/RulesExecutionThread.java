package Nodes;

public class RulesExecutionThread extends Thread {

    private BContractNode node;

    RulesExecutionThread(BContractNode node) {
        this.node = node;
    }

    @Override
    public void run () {
        try {
            this.node.executeRules();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
