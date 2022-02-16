package dev.skiff.dungeoncrawler.game.entities;

import dev.skiff.dungeoncrawler.game.Controller;

import java.util.Scanner;

public class Player extends Entity {

    public Player(String name, int damage, int hp) {
        super(name, damage, hp);
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

    public void setName(Scanner s) {
        System.out.println("Greetings, traveller. What is your name?");
        this.name = s.nextLine();
    }
}
