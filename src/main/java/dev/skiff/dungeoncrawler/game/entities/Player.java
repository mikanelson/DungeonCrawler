package dev.skiff.dungeoncrawler.game.entities;

import dev.skiff.dungeoncrawler.game.Controller;

import java.util.Scanner;

public class Player extends Entity {

    public Player(String name, int damage, int hp) {
        super(name, damage, hp);
    }

    public Player() {
        super("Adventurer", 1, 10);
    }

    @Override
    public void takeDamage(int damage) {
        this.health -= damage;
        if (health <= 0) {
            Controller.getInstance().gameOver();
        } else {
            System.out.println("You take " + damage + " damage. Your health is " + this.health + ".");
        }
    }

    public int getHealth() {
        return this.health;
    }

    public void setName(Scanner s) {
        System.out.println("Greetings, traveller. What is your name?");
        String name;
        do {
            name = s.next();
            System.out.println(name);
            if (name.length() > 20) {
                System.out.println("That's quite the name, adventurer. Is there anything shorter that I may call you?");
            }
        } while (name.length() > 20);
        this.name = name;
    }
}
