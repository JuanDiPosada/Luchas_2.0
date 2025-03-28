package org.example;

public class Peleador {
    private int hp;
    private String nombre;
    private int def;
    private int atk;

    public Peleador(String nombre, int def, int atk) {
        this.hp = 1000;
        this.nombre = nombre;
        this.def = def;
        this.atk = atk;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }
}
