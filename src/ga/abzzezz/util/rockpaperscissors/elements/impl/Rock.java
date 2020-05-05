/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.rockpaperscissors.elements.impl;

import ga.abzzezz.util.rockpaperscissors.elements.Option;

public class Rock extends Option {

    public Rock() {
        super("Rock");
        immune.add("Scissors");
    }
}
