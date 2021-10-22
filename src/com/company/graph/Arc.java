package com.company.graph;

public class Arc {
    private Noeud source;
    private Noeud destination;
    private int valeurArc;

    public Arc(Noeud source, Noeud destination, int valeurArc) {
        this.source = source;
        this.destination = destination;
        this.valeurArc = valeurArc;
    }

//    public void printer(Arc arc){
//        System.out.println("source: " + arc.source + " dest: " + arc.destination + " valeur arc: " + arc.valeurArc + " dest finale : " + arc.destination.isFinal());
//    }

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
}
