//package com.company.graph;
//
//import java.util.ArrayList;
//import java.util.LinkedList;
//
//public class WeightedGraph {
//    static class Edge {
//        int source;
//        int destination;
//        int valeurArc;
//
//        public Edge(int source, int destination, int valeurArc) {
//            this.source = source;
//            this.destination = destination;
//            this.valeurArc = valeurArc;
//        }
//    }
//
//    static class Graph {
//        int vertices;
//        ArrayList<Edge> adjacencylist = new ArrayList<Edge>();
//
//        Graph() {
//            this.vertices = vertices;
////            for (int i = 0; i <vertices ; i++) {
////                adjacencylist[i] = new LinkedList<>();
////            }
//        }
//
//        public void addEgde(Noeud source, Noeud destination, int valeurArc) {
//            Edge edge = new Edge(source, destination, valeurArc);
//            adjacencylist.add(edge);
////        adjacencylist[source].addFirst(edge); //for directed graph
//        }
//
//        //    public void addArc(Noeud source, Noeud destination, int valeurArc){
////        Arc arc = new Arc(source, destination, valeurArc);
////        listeDesArcs.add(arc);
////    }
//
//        public void printGraph(){
//            for (int i = 0; i <vertices ; i++) {
//                ArrayList<Edge> list = adjacencylist.get(i);
//                for (int j = 0; j <list.size() ; j++) {
//                    System.out.println("vertex-" + i + " is connected to " +
//                            list.get(j).destination + " with weight " + list.get(j).weight);
//                }
//            }
//        }
//    }
//    public static void main(String[] args) {
//        int vertices = 6;
//        Graph graph = new Graph(vertices);
//        graph.addEgde(0, 1, 4);
//        graph.addEgde(0, 2, 3);
//        graph.addEgde(1, 3, 2);
//        graph.addEgde(1, 2, 5);
//        graph.addEgde(2, 3, 7);
//        graph.addEgde(3, 4, 2);
//        graph.addEgde(4, 0, 4);
//        graph.addEgde(4, 1, 4);
//        graph.addEgde(4, 5, 6);
//        graph.printGraph();
//    }
//}
