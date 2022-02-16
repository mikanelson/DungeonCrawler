package dev.skiff.dungeoncrawler.game.entities;

public abstract class Entity {
    int health;
    String name;
    int damage;

    public Entity(String name, int damage, int hp) {
        this.name = name;
        this.damage = damage;
        this.health = hp;
    }

    public abstract void takeDamage(int damage);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
