package org.example;

import javax.swing.*;

public class Arcomago extends Peleador implements Mago,Arquero{

    private int mana;
    private boolean isDisparo;   //para la carga del disparo, lo pienso manejar por turno

    public Arcomago(String nombre, int def, int atk, int mana) {
        super(nombre, def, atk);
        this.mana=mana;
        this.isDisparo=true;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override //sera su ataque
    public void dispararFlecha(Peleador enemigo) {
        int danioReal=0;
        int dado=(int)(Math.random()*4)+1;
        if (dado==2 || dado==4){
            danioReal=getAtk()*(dado+2);
        }else if (dado==3){
            danioReal=getAtk()*(dado+2);
        }

        if (isDisparo){
            if (danioReal>enemigo.getDef()){
                if (dado==3){
                    enemigo.setHp(enemigo.getHp()-danioReal);
                    JOptionPane.showMessageDialog(null,"Puntos def enemigo:"+enemigo.getDef()+"\n"+
                            "Daño real: "+danioReal+"\n"+
                            getNombre()+" disparo la flecha, hizo un daño critico de "+danioReal);
                }else{
                    enemigo.setHp(enemigo.getHp()-(danioReal-enemigo.getDef()));
                    JOptionPane.showMessageDialog(null,"Puntos def enemigo:"+enemigo.getDef()+"\n"+
                            "Daño real: "+danioReal+"\n"+
                            getNombre()+" disparo la flecha, hizo un daño de "+(danioReal-enemigo.getDef()));
                }
            }else{
                JOptionPane.showMessageDialog(null,"Puntos def enemigo:"+enemigo.getDef()+"\n"+getNombre()+" disparo la flecha, pero la defensa enemiga es mayor, daño :"+0);
            }
            isDisparo=false;
        }else{
            JOptionPane.showMessageDialog(null,"no ha cargado disparo, recarga");
            cargarDisparo(enemigo);
        }
        JOptionPane.showMessageDialog(null,"la vida del enemigo: "+enemigo.getNombre()+" es de "+enemigo.getHp());


    }

    @Override
    public void cargarDisparo(Peleador enemigo) {
        if (!isDisparo){
            JOptionPane.showMessageDialog(null,"disparo cargado correctamente");
            this.isDisparo=true;
        }else{
            JOptionPane.showMessageDialog(null,"el disparo ya se encuentra cargado");
            dispararFlecha(enemigo);
        }

    }

    @Override
    public void lanzarHechizo(Peleador enemigo) {
        //por ahora en caso de tener mas interes pensaba manerjar un selector del hechizo
        //el hechizo por ahora sera un atq poderozo pero tendra un costo alto de mana

        String[] listaHech={"daño explosivo","florece"};
        int hechSlec=JOptionPane.showOptionDialog(null,"hechizos disponibles \n daño explosivo (60 mana): aplica daño = daño base x3. \n florece(25): cura 5 puntos de vida","seleccion de hechizo",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,listaHech,listaHech[0]);
        if (hechSlec==0){
            int costMana=60;
            if(getMana()<costMana){
                JOptionPane.showMessageDialog(null,"mana insuficiente, se le mandara a recargar :)");
                recargarMana(enemigo);
            }else{
                JOptionPane.showMessageDialog(null,"hechizo efectuado correctamente, daño realizado: "+getAtk()*3);
                setMana(getMana()-costMana);
                enemigo.setHp(enemigo.getHp()-((getAtk()*3)- enemigo.getDef()));
            }
        }else{
            int costMana=25;
            if(getMana()<costMana){
                JOptionPane.showMessageDialog(null,"mana insuficiente, se le mandara a recargar :)");
                recargarMana(enemigo);
            }else{
                if (getHp()+30>1000||getHp()<=0){
                    JOptionPane.showMessageDialog(null,"por abuso de confianza pierdes turno");
                }else {
                    setHp(getHp()+30);
                    JOptionPane.showMessageDialog(null,"hechizo efectuado correctamente, te curaste 30 hp tu vida ahora es: "+getHp());
                    setMana(getMana()-costMana);
                }

            }

        }

        JOptionPane.showMessageDialog(null,"la vida del enemigo: "+enemigo.getNombre()+" es de "+enemigo.getHp());

    }


    @Override
    public void recargarMana(Peleador enemigo) {
        int recMan=25;
        if (getMana()<100){
            if (getMana()+recMan>100){
                setMana(100);
                JOptionPane.showMessageDialog(null, "nivel de mana es "+this.mana);
            }else{
                setMana(getMana()+recMan);
                JOptionPane.showMessageDialog(null, "nivel de mana es "+this.mana);
            }
        }else{
            JOptionPane.showMessageDialog(null, "mana completo, no se puede recargar se le mandara a hechizos");
            lanzarHechizo(enemigo);
        }
    }

    public String mostrarInfo() {
        return "Nombre: " + getNombre() +
                "\nVida: " + getHp() +
                "\nAtaque: " + getAtk() +
                "\nDefensa: " + getDef() +
                "\nMana: " + getMana() +
                "\nDisparo cargado: " + (isDisparo ? "Sí" : "No");
    }
}
