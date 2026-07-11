package service;

import model.Menu;

public abstract class MenuManager {
    protected Menu menu;

    public MenuManager(Menu menu) {
        this.menu = menu;
    }

    public abstract void tampilInfo();
}