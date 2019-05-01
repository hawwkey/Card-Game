import javafx.scene.image.Image;

public class Card {
    //Rank and suit of the card
    private int rank = 0;
    private int suit = 0;
    Image img;

    public Card(int rank, int suit) {//constructor to create a Card object
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public int getSuit() {
        return suit;
    }

    public String toString() {//returns the name of the card's image file
        String cardSuit = "";
        String cardRank = "";
        String cardString = "";

        int cs = getSuit();
        int cr = getRank();

        switch (cr) {
            case 1:
                cardRank = "ace";
                break;
            case 2:
                cardRank = "2";
                break;
            case 3:
                cardRank = "3";
                break;
            case 4:
                cardRank = "4";
                break;
            case 5:
                cardRank = "5";
                break;
            case 6:
                cardRank = "6";
                break;
            case 7:
                cardRank = "7";
                break;
            case 8:
                cardRank = "8";
                break;
            case 9:
                cardRank = "9";
                break;
            case 10:
                cardRank = "10";
                break;
            case 11:
                cardRank = "jack";
                break;
            case 12:
                cardRank = "queen";
                break;
            case 13:
                cardRank = "king";
                break;
            default:
                cardRank = "n/a";
        }//switch rank

        switch (cs) {
            case 0:
                cardSuit = "clubs";
                break;
            case 1:
                cardSuit = "diamonds";
                break;
            case 2:
                cardSuit = "hearts";
                break;
            case 3:
                cardSuit = "spades";
                break;
            default:
                cardSuit = "n/a";
        }
        return cardRank+"_of_"+cardSuit;
    }

    public boolean rankIsLessThan(Card c) {//check if card's rank is less
        boolean rankIsLess = false;
        if (rank < c.getRank()) {
            rankIsLess = true;
        }
        return rankIsLess;
    }

    public boolean rankIsGreaterThan(Card c) {//check if card's rank is greater
        boolean rankIsGreater = false;
        if (rank < c.getRank()) {
            rankIsGreater = true;
        }
        return rankIsGreater;
    }

    public boolean rankIsEqualTo(Card c) {//check if card's rank is greater
        boolean rankIsEqualTo = false;
        if (rank == c.getRank()) {
            rankIsEqualTo = true;
        }
        return rankIsEqualTo;
    }
}
