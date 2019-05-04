import java.util.Collections;
import java.util.Stack;

public class DeckOfCards {

    Stack<Card> deck = new Stack<>();//using stack to create a deck of cards

    public DeckOfCards() {
        for (int i = 1; i <= 13; i++) {
            for (int j = 0; j<=3; j++){
                deck.push(new Card(i,j));//fill the stack with cards
            }
        }
    }//constructor

    public Card dealTopCard() {
        return deck.pop();//dealing top card is basically popping the stack
    }//deal top card

    public boolean isEmpty() {
        return deck.isEmpty();//check if stack is empty
    }//isEmpty

    public void shuffle() {
        Collections.shuffle(deck);
    }//shuffle
}
