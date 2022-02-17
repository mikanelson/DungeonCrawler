package dev.skiff.dungeoncrawler.model;

public class Weapon {
    private String name;
    private int damage;
    private String type;

    public Weapon(String name, int damage, String type) {
        this.name = name;
        this.damage = damage;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public String toString() {
        return "{Weapon: " + name + ", Type: " + type + ", Damage: " + damage + "}";
    }
}
