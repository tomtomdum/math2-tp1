package com.company.graph;

public class Arc {
    private Noeud source;
    private Noeud destination;
    private int valeurArc;
    private boolean cheminDejaEmprunter;



    public Arc(Noeud source, Noeud destination, int valeurArc) {
        this.source = source;
        this.destination = destination;
        this.valeurArc = valeurArc;
    }

    public Noeud getSource() {
        return source;
    }

    public void setSource(Noeud source) {
        this.source = source;
    }

    public Noeud getDestination() {
        return destination;
    }

    public void setDestination(Noeud destination) {
        this.destination = destination;
    }

    public int getValeurArc() {
        return valeurArc;
    }

    public void setValeurArc(int valeurArc) {
        this.valeurArc = valeurArc;
    }

    public boolean isCheminDejaEmprunter() {
        return cheminDejaEmprunter;
    }

    public void setCheminDejaEmprunter(boolean cheminDejaEmprunter) {
        this.cheminDejaEmprunter = cheminDejaEmprunter;
    }
}
