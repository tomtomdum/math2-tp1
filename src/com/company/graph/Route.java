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
    ArrayList<Arc> route = new ArrayList<Arc>();

    public void printer(){
        for(Noeud element : listeNoeuds) element.printer();
    }


    public void runGraph(String serieBinaire) throws Exception {
        serieBinaire +="F";
        Arc arcPrecedant = null;
        Arc aLoop = null;
        Noeud noeudS =null;
        boolean thereIsALoop = false;
        int positionRoute = 0;

        // recherche du point de départ
        for(Noeud noeud: listeNoeuds){
            if (noeud.getNom().equals("S")) {
                noeudCourant= noeudS = noeud;
                break;
            }
        }

        for(int positionLangage = 0; positionLangage<serieBinaire.length(); positionLangage++){
            char ch = serieBinaire.charAt(positionLangage);
//            noeudCourant = route.get(positionRoute).getDestination();
//            noeudCourant.printer();

            int positionArray = 0; // utiliser pour éliminer les chemin deja essayé

            // on détece si le noeud courant contient une boucle, car il faut la faire en premier
            for(Arc arc: noeudCourant.getListeDesArcs()){
                if(arc.getSource().equals(arc.getDestination()) && Character.getNumericValue(ch) == arc.getValeurArc()) {
                    aLoop = arc;
                    thereIsALoop = true;
                }
            }

            if(thereIsALoop){
                while(Character.getNumericValue(ch) == aLoop.getValeurArc() && !aLoop.isCheminDejaEmprunter()){
                    positionLangage++;
                    ch = serieBinaire.charAt(positionLangage);// pas vraiment besoin d'assigner le noeud courant, cela revient au meme, on se déplace dans le langage donné
                    route.add(aLoop);
                    positionRoute++;// on ajoute seulement une loop dans le chemin utilisé
                    System.out.println(aLoop.getSource().getNom() + "->" + aLoop.getValeurArc() + aLoop.getDestination().getNom());
                }
                thereIsALoop = false;
            }

            for(Arc arc: noeudCourant.getListeDesArcs()){
//!(positionLangage < serieBinaire.length() && arc.getDestination().isFinal() )
                if(Character.getNumericValue(ch) == arc.getValeurArc() && !arc.isCheminDejaEmprunter()){
                    // si la valeur de l'input est égal à la valeur de l'arc on se déplace
                    // on vérifie aussi que le dernier noeud qu'on se déplace est final sinon il ne sera pas possible de revenir en arriere en utilisant le prochain if
                    route.add(arc);
                    positionRoute++;
                    noeudCourant = arc.getDestination();
                    System.out.println(arc.getSource().getNom() + "->" + arc.getValeurArc() + arc.getDestination().getNom());
                    break;
                }
            }

            if(noeudCourant.isFinal() && ch=='F'){
                System.out.println("Fin");
                break;
            } else if(!possibilites(ch) && !noeudCourant.isFinal()){
                positionRoute--;
                noeudCourant = route.get(positionRoute).getSource();// on retourne vers la source de l'arc, car on est déja à sa destination
                route.get(positionRoute).setCheminDejaEmprunter(true);
//                noeudCourant.removeArcObject(route.get(positionRoute));
                route.remove(positionRoute);
//                // le noeud se rend nul part et n'est pas final, on enleve le chemin accessible
//                noeudCourant.removeArc(positionArray);
                positionLangage -= 2; // on sosutrait deux car la boucle for ajoute un automatiquement
                System.out.println("going back");
            } else if( noeudCourant == noeudS && !possibilites(ch)){// on revient au point de départ et aucune possibilité n'est possible, donc le langage n'est pas accepté
                throw new Exception("Le langage n'est pas possible");
            }
        }
    }

    public boolean possibilites(char ch){
        for(Arc arc : noeudCourant.getListeDesArcs()){
            if(Character.getNumericValue(ch) == arc.getValeurArc() && !arc.isCheminDejaEmprunter()){
                return true;
            }
        }
        return false;
    }

    public void fileParser(){
        try {
            File myObj = new File("src/com/company/grammar.txt");
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


                String[] parts = data.split("->");
                lettreDuDepart = parts[0]; // va aller chercher premiere partie de l'expression
                goingto = parts[1];
                valeurDuChemin = goingto.replaceAll("[^0-9]+", "");
                goingto = parts[1];
                lettrePointee = goingto.replaceAll("\\d", "");

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

                    if(lettrePointee.equals("e") && noeudSource.getNom().equals("S")){
                        noeudSource.setFinal(true);
                    }
                    else if(lettrePointee.equals("")){
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