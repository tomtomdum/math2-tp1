package com.company.graph;

import java.util.ArrayList;
import java.util.LinkedList;

public class Noeud {
    private String nom;
    private boolean isFinal = false;
    private ArrayList<Arc> listeDesArcs = new ArrayList<Arc>();

    public Noeud(String nom, boolean isFinal) {
        this.nom = nom;
        this.isFinal = isFinal;
    }

    public void printer(){
        for(Arc element : listeDesArcs) System.out.println(element.getSource().getNom()+ " -> " + element.getDestination().getNom() + " valeur: " + element.getValeurArc());
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<Arc> getListeDesArcs() {
        return listeDesArcs;
    }


    public void addArc(Noeud source, Noeud destination, int valeurArc){
        Arc arc = new Arc(source, destination, valeurArc);
        listeDesArcs.add(arc);
    }
}
