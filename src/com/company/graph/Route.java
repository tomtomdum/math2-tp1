package com.company.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Route {

    private Noeud noeudPrecedant;
    private Noeud noeudCourant;
    private Noeud noeudSuivant;

    ArrayList<Noeud> listeNoeuds = new ArrayList<Noeud>();

    public void printer(){
        for(Noeud element : listeNoeuds) element.printer();
    }

//    public void ProchainNoeud(){
//        for( Noeud noeud :listeNoeuds)
//            if(noeud.getListeDesArcs().get)
//        listeNoeuds.get();
//    }

    public void fileParser(){
        try {
            File myObj = new File("C:\\Users\\toys7\\Desktop\\math2-tp1\\src\\com\\company\\grammar.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();
                String lettreDuDepart = "";
                String goingto = "";
                String valeurDuChemin = "";
                String lettrePointee = "";
                Boolean isNoeudSourceExistant = false;
                Boolean isNoeudDestinationExistant = false;
                Noeud noeudSource = null;
                Noeud noeudDestination = null;

                if(!data.equals("e")) {
                    String[] parts = data.split("->");
                    lettreDuDepart = parts[0]; // va aller chercher premiere partie de l'expression
                    goingto = parts[1];
                    valeurDuChemin = goingto.replaceAll("[^0-9]+", "");
                    goingto = parts[1];
                    lettrePointee = goingto.replaceAll("\\d","");
                }
                else{} // gérer l'élément vide ici

                // recherche en premier si le noeud existe dans la liste
                for(Noeud noeud: listeNoeuds){
                    if (noeud.getNom().equals(lettreDuDepart)) {
                        isNoeudSourceExistant = true;
                        noeudSource = noeud;
                        break;
                    }
                }

                // recherche si le noeud de destination existe dans la liste
                for(Noeud noeud: listeNoeuds){
                    if (noeud.getNom().equals(lettreDuDepart)) {
                        isNoeudDestinationExistant = true;
                        noeudDestination = noeud;
                        break;
                    }
                }

                if(isNoeudSourceExistant && isNoeudDestinationExistant){
                    noeudSource.addArc(noeudSource, noeudDestination, Integer.parseInt(valeurDuChemin));
                }
                else if( isNoeudSourceExistant && !isNoeudDestinationExistant){
                    noeudDestination = new Noeud(lettrePointee);
                    noeudSource.addArc(noeudSource, noeudDestination, Integer.parseInt(valeurDuChemin));
                    listeNoeuds.add(noeudDestination);
                }

                else if( isNoeudSourceExistant && !isNoeudDestinationExistant ){
                    noeudSource = new Noeud(lettreDuDepart);
                    noeudSource.addArc(noeudSource, noeudDestination, Integer.parseInt(valeurDuChemin));
                    listeNoeuds.add(noeudSource);
                }

                else{
                    noeudSource = new Noeud(lettreDuDepart);
                    noeudDestination = new Noeud(lettrePointee);

                    // il est possible qu'un noeud ne pointe vers rien, mais sois pointé, donc on crée seulement un arc de source à destinataire
                    noeudSource.addArc(noeudSource, noeudDestination, Integer.parseInt(valeurDuChemin));

                    listeNoeuds.add(noeudSource);
                    listeNoeuds.add(noeudDestination);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
