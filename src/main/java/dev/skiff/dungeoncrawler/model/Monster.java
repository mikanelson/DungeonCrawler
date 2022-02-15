package dev.skiff.dungeoncrawler.model;

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

    public void setHealth(int health) {
        this.health = health;
    }

    public String toString() {
        return "{Monster: " + species + ", Health: " + health + ", Damage: " + damage + "}";
    }
}
