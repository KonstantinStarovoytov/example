package com.epam.lab.ta.onliner.business_objects;

import java.util.ArrayList;
import java.util.List;

public class MainMenu {

    private List<String> buttons = new ArrayList<>();

    public void setButtons(String[] buttons) {
        for (int i = 0; i<=buttons.length-1; i++){
            this.buttons.add(buttons[i]);
        }
    }

    public List<String> getButtons() {
        return buttons;
    }
}
