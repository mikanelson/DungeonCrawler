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

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        System.out.println("You deal " + damage + " damage to the " + this.species +
                    ". Its health is " + this.health + ".");
    }

    public String toString() {
        return "{Monster: " + species + ", Health: " + health + ", Damage: " + damage + "}";
    }
}
