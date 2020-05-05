/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.rockpaperscissors;

import ga.abzzezz.util.animations.MathUtil;
import ga.abzzezz.util.logging.ConsoleColors;
import ga.abzzezz.util.rockpaperscissors.elements.Option;
import ga.abzzezz.util.rockpaperscissors.elements.impl.Paper;
import ga.abzzezz.util.rockpaperscissors.elements.impl.Rock;
import ga.abzzezz.util.rockpaperscissors.elements.impl.Scissors;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainClass {


    private List<Option> options;
    private final int[] score = new int[2];

    public void setup() {
        this.options = new ArrayList<>();
        this.options.add(new Rock());
        this.options.add(new Paper());
        this.options.add(new Scissors());

        Scanner scanner = new Scanner(System.in);

        System.out.println(ConsoleColors.GREEN + "Enter rounds: ");
        int rounds = scanner.nextInt();

        for (int i = 0; i < rounds; i++) {
            String botIn = generate();
            System.out.println(ConsoleColors.GREEN + "Choose Rock/Paper/Scissors");
            System.out.println(ConsoleColors.YELLOW_BOLD + "Winner: " + winner(scanner.next(), botIn));
        }

        if (score[0] == score[1]) {
            System.out.println("Tie");
        }

        if (score[0] < score[1]) {
            System.out.println("Bot won :" + score[1]);
        } else {
            System.out.println("Player won :" + score[0]);
        }
    }

    private String winner(String playerIn, String botIn) {
        System.out.println(ConsoleColors.PURPLE + "Bot choose: " + botIn);

        Option playerChoice = null;
        for (Option option : options) {
            if (option.getName().equalsIgnoreCase(playerIn)) {
                playerChoice = options.get(options.indexOf(option));
            }
        }

        if (playerIn.equalsIgnoreCase(botIn)) return "Tie";

        for (String playerImmune : playerChoice.getImmune()) {
            if (playerImmune.equalsIgnoreCase(botIn)) {
                score[0] += 1;
                return "Player";
            } else {
                score[1] += 1;
                return "Bot";
            }
        }
        return "ERROR";
    }

    private String generate() {
        return options.get(MathUtil.getRandomInt(options.size())).getName();
    }
}
