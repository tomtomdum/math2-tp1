package com.company;
// on utilise openjdk 17

import com.company.graph.Noeud;
import com.company.graph.Route;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Route route = new Route();
        route.fileParser();
        menu(route);
    }
    //menu
    public static void menu(Route route) throws Exception {

        Scanner scan = new Scanner(System.in);
        int choix = 0;

        System.out.println("bienvenue");

        System.out.println("1 -Les règles de base de ma grammaire");
        System.out.println("2 -Ajouter une ou plusieurs règles");
        System.out.println("3 - Evaluer une expression");
        System.out.println("4 - Quitter");
        System.out.print(">>> ");
        choix= scan.nextInt();
        while ( choix <1 || choix >4) {
            System.out.println("Entrez un numéro de menu entre 1 et 4 ");
            choix= scan.nextInt();
        }

        switch(choix) {
            case 1 :
                fileReader(route);
                break;
            case 2 :
                ajoutRegles(route);
                break;
            case 3 :
                expressionTest(route);

                break;
            case 4 :
                //quitter
                System.exit(1);
                break;
        }
    }
    //Afficher mes règles
    public static void fileReader(Route route) throws Exception {
        try {
            File myObj = new File("src/com/company/grammar.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                System.out.println(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //retour au menu
        int rep=0;
        Scanner scan = new Scanner(System.in);
        System.out.println("retourner au menu ? : 1 oui, 2:Quitter" );
        rep =scan.nextInt();
        if (rep==1)
            menu(route);
        else if(rep==2)
            System.exit(1);
    }
    //ajouter une règle
    public static void ajoutRegles(Route route) throws Exception {
        Scanner scan = new Scanner(System.in);
        boolean nouvelleRegle=true;
        while (nouvelleRegle) {
            System.out.println("saisir la regle à ajouter");
            System.out.println("La règle doit respecter la forme A->1A");
            String text = scan.next();
            while (!text.substring(1, 3).equals("->") || text.length()>5 || (text.charAt(3)!='1'&& text.charAt(3)!='0')) {
                System.out.println("respectez la forme A->1A");
                text = scan.next();
            }
            try {
                FileWriter fw = new FileWriter("src/com/company/grammar.txt", true);
                // utiliser /n si vous n'etes pas sous WINDOWS
                fw.write("\r\n" );
                fw.write (text );
                fw.close();
            } catch (IOException ioe) {
                System.err.println(ioe.getMessage());
            }
            System.out.println("voulez vous ajouter une nouvelle règle");
            System.out.println("1: oui");
            System.out.println("2: non");
            if (scan.nextInt()==2)
                nouvelleRegle=false;
            int rep=0;
            System.out.println("retourner au menu ? : 1 oui, 2:Quitter" );
            rep =scan.nextInt();
            if (rep==1)
                menu(route);
            else if(rep==2)
                System.exit(1);
        }
    }
    //tester une expression
    public static void expressionTest(Route route) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("Saisissez l'expression à évaluer(exemple:1101):");
        String serieBinaire=scan.next();
        route.runGraph(serieBinaire);
        //route.printer();
        //retour au menu
        int rep=0;
        System.out.println("retourner au menu ? : 1 oui, 2:Quitter" );
        rep =scan.nextInt();
        if (rep==1)
            menu(route);
        else if(rep==2)
            System.exit(1);
    }
}
