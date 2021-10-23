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

/*
RESTE À FAIRE PEUT EN MANQUER
* boucle
* état final fait
* chemin normal fait
* chemin impossible
*
* */
    public void runGraph(String serieBinaire){
        Arc arcPrecedant = null;
        Arc aLoop = null;
        boolean thereIsALoop = false;
        int positionRoute = 0;

        // recherche du point de départ
        for(Noeud noeud: listeNoeuds){
            if (noeud.getNom().equals("S")) {
                noeudCourant = noeud;
                break;
            }
        }

        for(int positionLangage = 0; positionLangage<serieBinaire.length(); positionLangage++){
            char ch = serieBinaire.charAt(positionLangage);
//            noeudCourant = route.get(positionRoute).getDestination();
//            noeudCourant.printer();

            int positionArray = 0; // utiliser pour éliminer les chemin deja essayé

            for(Arc arc: noeudCourant.getListeDesArcs()){
                if(arc.getSource().equals(arc.getDestination()) && Character.getNumericValue(ch) == arc.getValeurArc()) {
                    aLoop = arc;
                    thereIsALoop = true;
                    System.out.println(arc.getSource().getNom() + "->" + arc.getValeurArc() + arc.getDestination().getNom());
                }
            }

            if(thereIsALoop){
                while(Character.getNumericValue(ch) == aLoop.getValeurArc() && positionLangage < serieBinaire.length() -1){
                    positionLangage++; // pas vraiment besoin d'assigner le noeud courant, cela revient au meme, on se déplace dans le langage donné
                    route.add(aLoop);
                    positionRoute++;// on ajoute seulement une loop dans le chemin utilisé
                }
                thereIsALoop = false;
            }

            for(Arc arc: noeudCourant.getListeDesArcs()){
//!(positionLangage < serieBinaire.length() && arc.getDestination().isFinal() )
                if(Character.getNumericValue(ch) == arc.getValeurArc()){
                    // si la valeur de l'input est égal à la valeur de l'arc on se déplace
                    // on vérifie aussi que le dernier noeud qu'on se déplace est final sinon il ne sera pas possible de revenir en arriere en utilisant le prochain if
                    route.add(arc);
                    positionRoute++;
                    noeudCourant = arc.getDestination();
                    System.out.println(arc.getSource().getNom() + "->" + arc.getValeurArc() + arc.getDestination().getNom());
                    break;
                }
                //revenir en arriere
                if(positionArray == (noeudCourant.getListeDesArcs().size() -1)) {// le langage se rend nul part, on a atteint la fin de la liste d'arc
                    noeudCourant = route.get(positionRoute).getSource();// on retourne vers la source de l'arc, car on est déja à sa destination
                    // le noeud se rend nul part et n'est pas final, on enleve le chemin accessible
                    for(Noeud noeud: listeNoeuds){
                        if(noeud.getNom().equals(arc.getSource().getNom())) noeud.getListeDesArcs().remove(route.get(positionRoute)); positionRoute--;
                        break;
                    }
                    positionLangage--;
                }
                positionArray++;
            }

            if(noeudCourant.isFinal() && positionLangage == (serieBinaire.length() - 1)){
                System.out.println("Fin");
                for(Arc arc: route){
                    System.out.println(arc.getSource().getNom() + "->" + arc.getValeurArc() + arc.getDestination().getNom());
                }
                break;
            } else if(positionLangage == serieBinaire.length() -1 && !noeudCourant.isFinal()){
                positionRoute--;
                noeudCourant = route.get(positionRoute).getSource();// on retourne vers la source de l'arc, car on est déja à sa destination
                route.remove(positionRoute);
//                // le noeud se rend nul part et n'est pas final, on enleve le chemin accessible
                noeudCourant.removeArc(positionArray);
                positionLangage--;
                System.out.println("going back");
            }
        }
    }

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
