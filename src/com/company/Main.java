package com.company;
// on utilise openjdk 17

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            File myObj = new File("C:\\Users\\toys7\\Desktop\\math2-tp1\\src\\com\\company\\grammar.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();
                String lettreDuDepart = "";
                String goingto = "";
                String valeurDuChemin = "";
                String lettrePointee = "";

                if(!data.equals("e")) {
                    String[] parts = data.split("->");
                    lettreDuDepart = parts[0]; // va aller chercher premiere partie de l'expression
                    goingto = parts[1];
                    valeurDuChemin = goingto.replaceAll("[^0-9]+", "");
                    goingto = parts[1];
                    lettrePointee = goingto.replaceAll("\\d","");
                }
                else{} // gérer l'élément vide ici
                System.out.println(lettreDuDepart);

                System.out.println(goingto);

                System.out.println(lettrePointee);

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
