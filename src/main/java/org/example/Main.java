package org.example;

import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {


        //todos tienen un daño base, diferente al anterior juego, pero algunos
        //tiene daño adicional por dado, como el usar la espada, la idea tampoco es que la pelea dure un año



        String[] opcionesuwu ={"iniciar","salir"};
        int opcionSelec= JOptionPane.showOptionDialog(null,"Bienvenido a peleas uwu","selecionar",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,opcionesuwu,opcionesuwu[0]);
        while (opcionSelec==0){
            ArrayList<Peleador> opciones=new ArrayList<>();
            opciones.add(new Arcomago("harit",70,100,100));
            opciones.add(new Guerrador("julian",100,80));
            opciones.add(new Arcomago("karina",60,120,100));
            opciones.add(new Guerrador("gatito",100,80));

            //mando el array al metodo seleccionpeleador para que devuelva un array con los elegidos
            int[] elegidos=seleccionPeleador(opciones);

            int p1=elegidos[0];
            int p2=elegidos[1];

            //mentiene la pelea hasta que la vida llegue a 0
            while (opciones.get(p1).getHp()>0&&opciones.get(p2).getHp()>0){
                realizarMov(p1,p2,opciones);

                realizarMov(p2,p1,opciones);
            }

            if (opciones.get(p1).getHp()>opciones.get(p2).getHp()){
                JOptionPane.showMessageDialog(null,opciones.get(p1).getNombre()+" gano la pelea");
            }else{
                JOptionPane.showMessageDialog(null,opciones.get(p2).getNombre()+" gano la pelea");
            }

            opcionSelec= JOptionPane.showOptionDialog(null,"Bienvenido a peleas uwu","selecionar",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,opcionesuwu,opcionesuwu[0]);

        }

            JOptionPane.showMessageDialog(null,"gracias por jugar");
            System.exit(0);


    }

    public static int[] seleccionPeleador(ArrayList<Peleador> lista){
        //en este metodo se decide quienes son seleccionados
        int[] elegidos= new int[2];

        String[] listaNombresPeleadores=new String[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            listaNombresPeleadores[i]=lista.get(i).getNombre();
        }
        double decicionuno=1;
        int elegida=0;
        while (decicionuno==1){
            //en caso de que se salga sin escoger, se devuelve -1, indice imporible entonces antes verificamos y se sale
            elegida =JOptionPane.showOptionDialog(null,"escoge tu primer peleador","selecionar",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,listaNombresPeleadores,listaNombresPeleadores[0]);

            //aca igual si se sale entraga null, toca hacer que se salga
            decicionuno=Integer.parseInt(JOptionPane.showInputDialog(null,"Informacion del peleador elgido:\n"
                    +"HP: "+lista.get(elegida).getHp()+"\n"
                    +"ATK: "+lista.get(elegida).getAtk()+"\n"
                    +"DEF: "+lista.get(elegida).getDef()+"\n"+"1.volver a pantalla de seleccion\n(ingrese cualquier otro numero para continuar)"
            ));
        }

        double deciciondos=1;

        int elegida2=0;
        while (deciciondos==1){
            //en caso de que se salga sin escoger, se devuelve -1, indice imporible entonces antes verificamos y se sale
            do {
                elegida2 =JOptionPane.showOptionDialog(null,"escoge tu segundo peleador","selecionar",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,listaNombresPeleadores,listaNombresPeleadores[0]);
            }while (elegida==elegida2);

            //aca igual si se sale entraga null, toca hacer que se salga
            deciciondos=Integer.parseInt(JOptionPane.showInputDialog(null,"Informacion del peleador elgido:\n"
                    +"HP: "+lista.get(elegida2).getHp()+"\n"
                    +"ATK: "+lista.get(elegida2).getAtk()+"\n"
                    +"DEF: "+lista.get(elegida2).getDef()+"\n"+"1.volver a pantalla de seleccion\n(ingrese cualquier otro numero para continuar)"
            ));
        }

        elegidos[0]=elegida;
        elegidos[1]=elegida2;

       return elegidos;
    }

    public static void realizarMov(int indP1,int indP2,ArrayList<Peleador> lista){
            Peleador peleadorMov =lista.get(indP1);
            Peleador enemigo =lista.get(indP2);

            //se hace un cast hacia abajo para poder usar los metodos, se pasa de peleador a algo mas especifico
            if (peleadorMov instanceof Guerrador){
                String[] habilidades={"aumentar fuerza","usar espada","atacar","bloquear"};
                Guerrador peleadorMovC= (Guerrador) peleadorMov;
                int hablilidadSelec=JOptionPane.showOptionDialog(null,"seleciona la habilidad que deseas que: "+peleadorMovC.getNombre()+" realize"+"\n"+peleadorMovC.mostrarInfo(),"selecion habilidad",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,habilidades,habilidades[0]);
                switch (hablilidadSelec){
                    case 0:
                        peleadorMovC.aumentarFuerza();
                        break;
                    case 1:
                        peleadorMovC.usarEspada(enemigo);
                        break;
                    case 2:
                        peleadorMovC.atacar(enemigo);
                        break;
                    case 3:
                        peleadorMovC.bloquear();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null,"error");
                        break;
                }

            } else if (peleadorMov instanceof Arcomago) {
                Arcomago peleadorMovC=(Arcomago) peleadorMov;
                String[] habilidades={"disparar flecha","cargar disparo","lanzar hechizo","recargar mana"};
                int hablilidadSelec=JOptionPane.showOptionDialog(null,"seleciona la habilidad que deseas que: "+peleadorMovC.getNombre()+" realize"+"\n" +
                        peleadorMovC.mostrarInfo(),"selecion habilidad",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,habilidades,habilidades[0]);
                switch (hablilidadSelec){
                    case 0:
                            peleadorMovC.dispararFlecha(enemigo);
                        break;
                    case 1:
                            peleadorMovC.cargarDisparo(enemigo);
                        break;
                    case 2:
                        peleadorMovC.lanzarHechizo(enemigo);
                        break;
                    case 3:
                        peleadorMovC.recargarMana(enemigo);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null,"error");
                        break;
                }
            }
/*
            JOptionPane.showMessageDialog(null,"golpea "+peleadorMov.getNombre()+" recibe "+enemigo.getNombre());
*/
    }
}