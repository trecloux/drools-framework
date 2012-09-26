package org.chtijbug.drools.runtime;

public class Main {

    public static void main(String args[]) {
        RuleBasePackage ruleBasePackage = RuleBaseBuilder.createPackageBasePackage("fibonacci.drl") ;
        try {
            for (int i = 0; i < 1000; i++) {
                RuleBaseSession session1 = ruleBasePackage.createRuleBaseSession();
                Fibonacci fibonacci = new Fibonacci(5);
                session1.insertObject(fibonacci);
                session1.fireAllRules();
                session1.dispose();
                Thread.sleep(2000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
