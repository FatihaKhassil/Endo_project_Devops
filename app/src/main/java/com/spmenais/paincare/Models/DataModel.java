package com.spmenais.paincare.Models;

import com.spmenais.paincare.Adapters.NestedAdapter;

import java.util.ArrayList;
import java.util.List;

public class DataModel {
    private final List<String> optionsList;
    private final String title;
    private boolean isExpandable;
    private final List<String> nestedList;
    private List<Integer> selectedPositions;
    private NestedAdapter nestedAdapter;
    private List<String> selectedOptions = new ArrayList<>();

    public DataModel(List<String> optionsList, String title) {
        this.optionsList = optionsList;
        this.title = title;
        this.isExpandable = false;
        this.nestedList = new ArrayList<>();
        this.selectedPositions = new ArrayList<>(); // Initialize the selectedPositions list
    }

    public List<String> getOptionsList() {
        return optionsList;
    }

    public String getTitle() {
        return title;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }

    public List<String> getNestedList() {
        return nestedList;
    }

    public void setSelectedPositions(List<Integer> selectedPositions) {
        this.selectedPositions = selectedPositions;
    }

    public List<Integer> getSelectedPositions() {
        return selectedPositions;
    }

    public void setNestedAdapter(NestedAdapter nestedAdapter) {
        this.nestedAdapter = nestedAdapter;
    }

    public NestedAdapter getNestedAdapter() {
        return nestedAdapter;
    }

    public void setSelectedOptions(List<String> selectedOptions) {
        this.selectedOptions = selectedOptions;
    }

    public List<String> getSelectedOptions() {
        return selectedOptions;
    }

    // ========= CODE SMELLS POUR BUILD #8 =========

    // Smell 1 : catch vide
    public void methodeSmell1() {
        try {
            String s = getTitle();
        } catch (Exception e) {
            // vide intentionnellement
        }
    }

    // Smell 2 : variables inutilisées
    public void methodeSmell2() {
        String variableInutilisee = "jamais utilisee";
        int compteur = 0;
        List<String> listeVide = new ArrayList<>();
    }

    // Smell 3 : méthodes dupliquées
    public String getTitleCopy1() { return title; }
    public String getTitleCopy2() { return title; }
    public String getTitleCopy3() { return title; }

    // Smell 4 : complexité cyclomatique élevée
    public String analyseComplexe(int a, int b, int c, int d, int e) {
        if (a > 0) {
            if (b > 0) {
                if (c > 0) {
                    if (d > 0) {
                        if (e > 0) {
                            return "tous positifs";
                        } else { return "e negatif"; }
                    } else { return "d negatif"; }
                } else { return "c negatif"; }
            } else { return "b negatif"; }
        }
        return "a negatif";
    }

    // ========= FIN CODE SMELLS =========
}
