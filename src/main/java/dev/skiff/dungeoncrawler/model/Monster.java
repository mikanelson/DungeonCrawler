package dev.skiff.dungeoncrawler.model;

import dev.skiff.dungeoncrawler.game.Controller;

public class Monster {
    private String species;
    private int damage;
    private int health;

    public Monster(String species, int damage, int health) {
        this.species = species;
        this.damage = damage;
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        System.out.println("You deal " + damage + " damage to the " + this.species +
                    ". Its health is " + this.health + ".");
        if (health <= 0) {
            Controller.getInstance().incrementScore();
        }
    }

    public String toString() {
        return "{Monster: " + species + ", Health: " + health + ", Damage: " + damage + "}";
    }
}
