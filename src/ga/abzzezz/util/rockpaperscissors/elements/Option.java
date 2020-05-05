/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.rockpaperscissors.elements;

import java.util.ArrayList;
import java.util.List;

public class Option {

    protected List<String> immune;
    private final String name;

    public Option(String string) {
        this.name = string;
        this.immune = new ArrayList<>();
    }


    public List<String> getImmune() {
        return immune;
    }

    public String getName() {
        return name;
    }
}
