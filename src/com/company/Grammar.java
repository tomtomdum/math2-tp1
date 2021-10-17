package com.company;

import java.util.ArrayList;

public class Grammar {
    // grammaire
   ArrayList<String> vocabulaire;
    ArrayList<String> terminaux;
    ArrayList<String> regles;

    //
    private String leftPart = "";
    private String RightPartTerminal = "";
    private String rightPart = "";
    ArrayList<Rule> rules;


    public ArrayList<Rule> getRules() {
        return rules;
    }

    public void setRules(Rule rule) {
        rules.add(rule);
    }

}
