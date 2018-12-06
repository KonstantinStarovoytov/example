package com.epam.lab.ta.onliner.business_objects;

import java.util.ArrayList;
import java.util.List;

public class NewsBlock {

    private List<String> elements = new ArrayList<>();

    public void setElements(String[] elements) {
        for (int i = 0; i<= elements.length-1; i++){
            this.elements.add(elements[i]);
        }
    }

    public List<String> getElements() {
        return elements;
    }
}
