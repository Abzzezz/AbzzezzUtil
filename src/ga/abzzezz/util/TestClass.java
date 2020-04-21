package ga.abzzezz.util;


import ga.abzzezz.util.array.ArrayUtil;

import java.util.*;

public class TestClass {

    /*
    Mau mau;
    Farbe auf Farbe:
    Ass = nochmal
    7 = zwei Ziehen
    Bube = random Farbe
     */


    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        testClass.startGame();
    }


    private List<String> layed = new ArrayList<>();

    private void startGame() {
        System.out.println("Willkommen zu Mau-Mau");
        System.out.println("Spiel Started y/n?");
        String[] firstHand = generateHand();
        String[] playerHand = generateHand();

        Scanner scanner = new Scanner(System.in);
        if(scanner.next().equalsIgnoreCase("y")) {
            int begin = getRandom(2);

            if(begin == 1) {
                loopGame(firstHand, playerHand, scanner);
            } else {

            }
        }

        System.out.println("Gewonnen hat: ");
    }

    private void loopGame(String[] firstHand, String[] playerHand, Scanner scanner) {
        while(firstHand.length > 0 || playerHand.length > 0) {
            System.out.println("Deine Hand: " + Arrays.toString(playerHand));
            System.out.println("Welche Karte möchtest du legen? 0 - " + playerHand.length);
            int card = scanner.nextInt();
            try {
                layed.add(playerHand[card]);
                ArrayUtil.removeElement(playerHand,card);
                System.out.println(playerHand.length);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error. Please select a valid card!");
            }
        }
    }

    private String[] generateHand() {
        String[] hand = new String[7];
        for (int i = 0; i < 7; i++) {
            hand[i] = getNewCard();
        }
        return hand;
    }

    public String getNewCard() {
        String[] colors = {"Pik", "Kreuz", "Herz", "Karo"};
        String[] cards = {"Bube", "Ass", "Sieben", "König", "Dame", "Normal"};
        String card = colors[getRandom(colors.length)] + " : "+ cards[getRandom(cards.length)];
        return card;
    }

    public int getRandom(int bound)  {
        return new Random().nextInt(bound);
    }
}
