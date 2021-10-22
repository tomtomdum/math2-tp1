package com.company;
// on utilise openjdk 17

import com.company.graph.Route;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Route route = new Route();
        route.fileParser();
        route.runGraph("0110");
        route.printer();
    }
}
