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
    ArrayList<Arc> listeArcsCompatible = new ArrayList<Arc>();

    public void printer(){
        for(Noeud element : listeNoeuds) element.printer();
    }


    public void runGraph(String serieBinaire){
        Arc arcPrecedant;
        // recherche du point de départ
        for(Noeud noeud: listeNoeuds){
            if (noeud.getNom().equals("S")) {
                noeudCourant = noeud;
                break;
            }
        }

        for(int i = 0; i<serieBinaire.length(); i++){
            char ch = serieBinaire.charAt(i);
            int j =0; // utiliser pour éliminer les chemin deja essayé

            for(Arc arc: noeudCourant.getListeDesArcs()){

                if(Character.getNumericValue(ch) == arc.getValeurArc()){// si la valeur de l'input est égal à la valeur de l'arc on se déplace
                    noeudPrecedant = noeudCourant;
                    noeudCourant = arc.getDestination();
                    arcPrecedant = arc;
                }

                // revenir en arriere
//                if(noeudCourant.) {// le noeud se rend nul part
//                    noeudCourant = noeudPrecedant;
//                    // le noeud se rend nul part et n'est pas final, on enleve le chemin accessible
//                    if(!arc.getSource().equals(arc.getDestination())) {
//                        listeArcsCompatible.remove(arc);
//                    }
//                }

                if(noeudCourant.isFinal()) break;
            }
        }
    }

// TODO: traiter le charactere e
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
                Noeud noeudFinal = new Noeud("final", true); // noeud générique à insérer dans un arc à chaque fois que la grammaire produit un état final ex: S->0

                if (!data.equals("e")) {
                    String[] parts = data.split("->");
                    lettreDuDepart = parts[0]; // va aller chercher premiere partie de l'expression
                    goingto = parts[1];
                    valeurDuChemin = goingto.replaceAll("[^0-9]+", "");
                    goingto = parts[1];
                    lettrePointee = goingto.replaceAll("\\d", "");
                } else {// gérer l'élément vide ici
//                    for(Noeud noeud: listeNoeuds){
//                        if (noeud.getNom().equals("S")) {
//                            isNoeudSourceExistant = true;
//                            noeudSource = noeud;
//                            break;
//                        }

                }


                // recherche en premier si le noeud existe dans la liste
                for (Noeud noeud : listeNoeuds) {
                    if (noeud.getNom().equals(lettreDuDepart)) {
                        isNoeudSourceExistant = true;
                        noeudSource = noeud;
                        break;
                    }
                }

                // recherche si le noeud de destination existe dans la liste
                for (Noeud noeud : listeNoeuds) {
                    if (noeud.getNom().equals(lettrePointee)) {
                        isNoeudDestinationExistant = true;
                        noeudDestination = noeud;
                        break;
                    }
                }

                if (isNoeudSourceExistant && isNoeudDestinationExistant) {
                    noeudSource.addArc(noeudSource, noeudDestination, Integer.parseInt(valeurDuChemin));
                } else if (isNoeudSourceExistant && !isNoeudDestinationExistant) {

                    if(lettrePointee.equals("")){
                        noeudSource.addArc(noeudSource, noeudFinal, Integer.parseInt(valeurDuChemin));
                    }
                    else{
                        noeudDestination = new Noeud(lettrePointee, false);
                        noeudSource.addArc(noeudSource, noeudDestination, Integer.parseInt(valeurDuChemin));
                        listeNoeuds.add(noeudDestination);
                    }

                } else if (!isNoeudSourceExistant && isNoeudDestinationExistant) {
                    noeudSource = new Noeud(lettreDuDepart, false);
                    noeudSource.addArc(noeudSource, noeudDestination, Integer.parseInt(valeurDuChemin));
                    listeNoeuds.add(noeudSource);
                } else {
                    noeudSource = new Noeud(lettreDuDepart, false);
                    noeudDestination = new Noeud(lettrePointee, false);

                    // il est possible qu'un noeud ne pointe vers rien, mais sois pointé, donc on crée seulement un arc de source à destinataire
                    noeudSource.addArc(noeudSource, noeudDestination, Integer.parseInt(valeurDuChemin));

                    listeNoeuds.add(noeudSource);
                    listeNoeuds.add(noeudDestination);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
