package com.company.graph;

import java.util.ArrayList;
import java.util.LinkedList;

public class Noeud {
    private String nom;
    private ArrayList<Arc> listeDesArcs = new ArrayList<Arc>();

    public Noeud(String nom) {
        this.nom = nom;
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
