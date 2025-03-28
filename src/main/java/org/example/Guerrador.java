package org.example;

import javax.swing.*;

public class Guerrador extends Peleador implements Luchador,Guerrero{
    boolean isBloqueo;


    public Guerrador(String nombre, int def, int atk) {
        super(nombre, def, atk);
        boolean isBloqueo=false;
    }

    @Override//en su ataque, tendra una probalididad de crítico que ignorara la defensa
    public void usarEspada(Peleador enemigo) {
        verifBloq();
        //la espada dara un daño adiconal por dado,
        // ya que el igual puede aumentar fuerza

        int aleNum= (int)(Math.random()*3)+1;
        int dado=(int)(Math.random()*200)+1; //este refiere al daño adicional

        System.out.println(aleNum);
        if (aleNum!=3){

            int danioReal=this.getAtk()+dado;
            if (danioReal > enemigo.getDef()) {
                enemigo.setHp(enemigo.getHp() - (danioReal - enemigo.getDef()));
                JOptionPane.showMessageDialog(null, "Puntos def enemigo: " + enemigo.getDef() + "\n" +
                        "Daño real: "+danioReal+"\n"+
                        getNombre() + " ataco con la espada, hizo un daño de " + (danioReal - enemigo.getDef()));

            } else {
                JOptionPane.showMessageDialog(null, "Puntos def enemigo: " + enemigo.getDef() + "\n" +
                        "Daño real: "+danioReal+"\n"+
                        getNombre() + " ataco con la espada, pero no supero la defensa, daño realizado " + 0);
            }

        }else{
            int danioReal=this.getAtk()+dado;
            JOptionPane.showMessageDialog(null,getNombre()+" realizo un critico, ignorando la defensa del enemigo "+danioReal);
            enemigo.setHp(enemigo.getHp()-danioReal);
        }
        JOptionPane.showMessageDialog(null,"la vida del enemigo: "+enemigo.getNombre()+" es de "+enemigo.getHp());
    }//😀

    @Override
    public void aumentarFuerza() {
        verifBloq();
        setAtk(getAtk()+10);
        JOptionPane.showMessageDialog(null,getNombre()+" aumento su ataque base mas un punto, ahora tiene: "+getAtk());
    }//😀

    @Override
    public void atacar(Peleador enemigo) {
        verifBloq();
        int dado = (int) (Math.random() * 2) + 1;
        int danioReal = (dado == 2) ? this.getAtk() * 4 : this.getAtk();

        if (danioReal>enemigo.getDef()){
            if (dado == 2) {
                enemigo.setHp(enemigo.getHp() - (danioReal-enemigo.getDef()));
                JOptionPane.showMessageDialog(null, "Puntos def enemigo: " + enemigo.getDef() + "\n" +
                        "Daño real: "+danioReal+"\n"+
                        getNombre() + " atacó y su daño se multiplicó x4, hizo un daño de " + (danioReal-enemigo.getDef()));
            } else {
                enemigo.setHp(enemigo.getHp() - (danioReal- enemigo.getDef()));
                JOptionPane.showMessageDialog(null, "Puntos def enemigo: " + enemigo.getDef() + "\n" +
                        "Daño real: "+danioReal+"\n"+
                        getNombre() + " atacó, hizo un daño de " + (danioReal-enemigo.getDef()));
            }
        }else{
            JOptionPane.showMessageDialog(null,"Puntos def enemigo:"+enemigo.getDef()+"\n"+getNombre()+" ataco pero su daño no supero la defensa, daño realizado "+0);
        }
        JOptionPane.showMessageDialog(null,"la vida del enemigo: "+enemigo.getNombre()+" es de "+enemigo.getHp());
    }//😀

    @Override
    public void bloquear() {
        int dado=(int)(Math.random()*2)+1;
        if (dado==2)isBloqueo=true;
        if (dado==1)isBloqueo=false;
        if (isBloqueo){
            if (getDef()<9000){
                setDef(getDef()+9000);
            }

            JOptionPane.showMessageDialog(null,"bloqueo ejecutado correctamente: el proximo daño recibido no tendra efectos, si no es critico :)");
        }else{
            verifBloq();
            JOptionPane.showMessageDialog(null,"fallo el bloqueo no te tocaba");
        }
    }//😀

    public void verifBloq(){
        if (getDef()>9000){
            setDef(getDef()-9000);
        };
    }//😀

    public String mostrarInfo() {

        return"Nombre: " + getNombre()+
                        "\nVida: " + getHp() +
                        "\nAtaque: " + getAtk() +
                        "\nDefensa: " + getDef() +
                        "\nbloqueo activo?" + (isBloqueo ? "Sí" : "No");
    }

}
