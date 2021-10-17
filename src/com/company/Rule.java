package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Rule {
    String lettreDuDepart = "";
    String goingto = "";
    String valeurDuChemin = "";
    String lettrePointee = "";

    public void parser(){
            try {
                File myObj = new File("/home/man/IdeaProjects/inf1006-tp1/src/regles.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {

                    String data = myReader.nextLine();


                    if(!data.equals("e")) {
                        String[] parts = data.split("->");
                        lettreDuDepart = parts[0]; // va aller chercher premiere partie de l'expression
                        goingto = parts[1];
                        valeurDuChemin = goingto.replaceAll("[^0-9]+", "");
                        goingto = parts[1];
                        lettrePointee = goingto.replaceAll("\\d","");
                    }
                    else{} // gérer l'élément vide ici

                    System.out.println(lettrePointee);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
    }





}
