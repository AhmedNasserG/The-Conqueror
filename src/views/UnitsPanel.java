package views;

import listeners.CardListener;
import units.Army;
import units.Unit;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UnitsPanel extends JPanel {

    private String armyOwner;
    private Army army;


    private Card selectedCard;

    private CardListener listener;

    private ArrayList<Card> cards;

    static final Dimension SCREENSIZE = Toolkit.getDefaultToolkit().getScreenSize();

    public UnitsPanel(String armyOwner, Army army, CardListener listener) {
        this.cards = new ArrayList<>();
        this.armyOwner = armyOwner;
        this.army = army;
        this.listener = listener;

        this.setPreferredSize(new Dimension(army.getUnits().size() * 150, SCREENSIZE.height / 4));

        this.setLayout(new GridLayout(1, 0));

        if (armyOwner.equals("player")) this.setBorder(BorderFactory.createTitledBorder("PLAYER UNITS"));
        else this.setBorder(BorderFactory.createTitledBorder("ENEMY UNITS"));

        ArrayList<Unit> units = army.getUnits();
        for (Unit unit : units) {
            if (unit.getCurrentSoldierCount() == 0) continue;
            Card card;
            if (armyOwner.equals("player")) {
                card = new Card(unit);
                card.setActionCommand("FRIENDLY_CARD_CLICKED_BV");
            } else {
                card = new Card(unit);
                card.setActionCommand("ENEMY_CARD_CLICKED_BV");
            }
            card.setListener(listener);
            this.add(card);
        }
    }


    public void updatePanel(Army army) {
        removeAll();

        ArrayList<Unit> units = army.getUnits();
        for (Unit u : units) {
            if (u.getCurrentSoldierCount() == 0) continue;
            Card c;
            if (armyOwner.equals("player")) {
                c = new Card(u);
                c.setActionCommand("FRIENDLY_CARD_CLICKED_BV");
            } else {
                c = new Card(u);
                c.setActionCommand("ENEMY_CARD_CLICKED_BV");
            }
            c.setListener(listener);
            this.add(c);
        }

        revalidate();
        repaint();
    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }

    public void setListener(CardListener listener) {
        this.listener = listener;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
