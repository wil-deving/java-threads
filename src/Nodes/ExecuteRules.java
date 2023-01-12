package Nodes;

import java.util.ArrayList;
import java.util.List;

public class ExecuteRules {

    public static void main(String args[]) {
        List<BContractNode> nodes = fillMockNodeList();
        List<BContractNode> mainNodes = getNodesByRiskLevelFlag(nodes, 1);
        List<BContractNode> riskNodes = getNodesByRiskLevelFlag(nodes, 0);
        /*
        System.out.println("main nodes");
        for (BContractNode element: mainNodes) {
            //System.out.println(element.getNodeNo());
            try {
                element.executeRules();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
         */

        System.out.println("risk nodes");


        RulesExecutionThread pivotThread = null;
        NodeThreadManager ntm = new NodeThreadManager();
        for (BContractNode element: riskNodes) {
            //System.out.println(element.getNodeNo());
            RulesExecutionThread rulesExecutionByNode = new RulesExecutionThread(element);
            ntm.addThread(rulesExecutionByNode);
            //rulesExecutionByNode.start();

            //System.out.println("status t1 " + rulesExecutionByNode.getState());
            //System.out.println("status t1 " + rulesExecutionByNode.getId());
            //System.out.println("status t1 " + rulesExecutionByNode.getThreadGroup());
            //System.out.println("status t1 " + rulesExecutionByNode.isAlive());

            //pivotThread = rulesExecutionByNode;

            //try {
              //  rulesExecutionByNode.join();
            //} catch (InterruptedException e) {
              //  throw new RuntimeException(e);
            //}

        }

        ntm.startThreads();





        /*
        try {
            pivotThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
         */




        /*
        BContractNode n1 = riskNodes.get(0);
        BContractNode n2 = riskNodes.get(1);
        BContractNode n3 = riskNodes.get(2);
        RulesExecutionThread rulesExecutionByNode1 = new RulesExecutionThread(n1);
        RulesExecutionThread rulesExecutionByNode2 = new RulesExecutionThread(n2);
        RulesExecutionThread rulesExecutionByNode3 = new RulesExecutionThread(n3);
        rulesExecutionByNode1.start();
        rulesExecutionByNode2.start();
        rulesExecutionByNode3.start();

        try {
            rulesExecutionByNode1.join();
            rulesExecutionByNode2.join();
            rulesExecutionByNode3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
         */

        System.out.println("Rules Were Validated successfully");
    }

    public static List<BContractNode> getNodesByRiskLevelFlag(List<BContractNode> inputList, int flag) {
        List<BContractNode> result = new ArrayList<>();
        for (BContractNode inputNode: inputList) {
            if (inputNode.getRiskLevelFlag() == flag)
                result.add(inputNode);
        }
        return result;
    }


    public static List<BContractNode> fillMockNodeList() {
        List<BContractNode> bContractNodes = new ArrayList<>();
        BContractNode bcn1 = new BContractNode("1", 1, false);
        BContractNode bcn2 = new BContractNode("1-1", 1, false);
        BContractNode bcn3 = new BContractNode("1-1-1", 0, false);
        BContractNode bcn4 = new BContractNode("1-1-2", 0, false);
        BContractNode bcn5 = new BContractNode("1-1-3", 0, false);
        bContractNodes.add(bcn1);
        bContractNodes.add(bcn2);
        bContractNodes.add(bcn3);
        bContractNodes.add(bcn4);
        bContractNodes.add(bcn5);
        return bContractNodes;
    }


}
