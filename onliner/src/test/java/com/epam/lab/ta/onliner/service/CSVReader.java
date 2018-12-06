package com.epam.lab.ta.onliner.service;

import com.epam.lab.ta.onliner.business_objects.NewsBlock;
import com.epam.lab.ta.onliner.business_objects.MainMenu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVReader {
    public static Iterator<Object> getCSVIterator(String path) {
        List<Object> inputData = new ArrayList<>();
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                inputData.add(initializeMainMenu(line.split(",")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputData.iterator();
    }

    public static Iterator<Object> getCSVIteratorBlocks(String path) {
        List<Object> inputData = new ArrayList<>();
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                inputData.add(initializeNewsBlockName(line.split(",")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputData.iterator();
    }

    private static MainMenu initializeMainMenu(String[] lineFromCsv){
        MainMenu menu = new MainMenu();
        menu.setButtons(lineFromCsv);
        return menu;
    }

    private static NewsBlock initializeNewsBlockName(String[] lineFromCsv){
        NewsBlock newsBlock = new NewsBlock();
        newsBlock.setElements(lineFromCsv);
        return newsBlock;
    }
}
