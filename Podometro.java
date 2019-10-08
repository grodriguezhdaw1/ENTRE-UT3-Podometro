/**
 * La clase modela un sencillo pod�metro que registra informaci�n
 * acerca de los pasos, distancia, ..... que una persona (hombre o mujer)
 * ha dado en una semana. 
 * 
 * @author    Gorka 
 * 
 */
public class Podometro {

    //Constantes
    public final char HOMBRE = 'H';
    public final char MUJER = 'M';
    public final double ZANCADA_HOMBRE = 0.45;
    public final double ZANCADA_MUJER = 0.41;
    public final int SABADO = 6;
    public final int DOMINGO = 7;

    //Variables
    private String marca;
    private double altura;
    private char sexo;
    private double longitudZancada;
    private int totalPasosLaborables;
    private int totalPasosSabado;
    private int totalPasosDomingo;
    private int totalDistanciaSemana;
    private int totalDistanciaFinSemana;
    private int tiempo;
    private int caminatasNoche;
    

    /**
     * Inicializa el pod�metro con la marca indicada por el par�metro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    public Podometro(String queMarca) {
        marca = queMarca;
        altura = 0;
        sexo = MUJER;
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;
    }

    /**
     * accesor para la marca
     *  
     */
    public  String  getMarca() {

        return marca;

    }

    /**
     * Simula la configuraci�n del pod�metro.
     * Recibe como par�metros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna adem�s el valor adecuado al atributo longitudZancada
     * 
     * (leer enunciado)
     *  
     */
    public void configurar(double queAltura, char queSexo) {

        altura = queAltura;
        sexo = queSexo;

        if (sexo == 'M') 
        {

            longitudZancada = (Math.floor(altura * ZANCADA_MUJER)) / 100;

        }
        else
        {
            longitudZancada = (Math.ceil(altura * ZANCADA_HOMBRE)) / 100;
        }

    }

    /**
     *  Recibe cuatro par�metros que supondremos correctos:
     *    pasos - el n� de pasos caminados
     *    dia - n� de d�a de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - S�bado, 7 - Domingo)
     *    horaInicio � hora de inicio de la caminata
     *    horaFina � hora de fin de la caminata
     *    
     *    A partir de estos par�metros el m�todo debe realizar ciertos c�lculos
     *    y  actualizar� el pod�metro adecuadamente  
     *   
     *   (leer enunciado del ejercicio)
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio,
    int horaFin) {

        double totalKilometros = (pasos * longitudZancada);
        double minutosFinal;
        double minutosInicio;
        
        totalDistanciaSemana += totalKilometros;
        minutosFinal = ((horaFin / 100) * 60) +(horaFin % 100);
        minutosInicio = ((horaInicio / 100) * 60) +(horaInicio % 100);
        tiempo += minutosFinal - minutosInicio;

        switch (dia){

            case 1:
            case 2:
            case 3:
            case 4:
            case 5: totalPasosLaborables += totalKilometros;
            break;
            case 6: totalPasosSabado += totalKilometros; 
            totalDistanciaFinSemana += totalKilometros;
            break;
            default: totalPasosDomingo += totalKilometros;
            totalDistanciaFinSemana += totalKilometros;
        }

        if ((horaInicio / 100) >= 21 && (horaInicio / 100) < 9){
            caminatasNoche++;
        }

    }

    /**
     * Muestra en pantalla la configuraci�n del pod�metro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
        String sexoCompleto;

        if (sexo == 'M')
        {
            sexoCompleto = "Mujer";
        }
        else{sexoCompleto = "Hombre";
        }

        System.out.println("Configuraci�n del pod�metro" +
            "\n*********************************" +
            "\nAltura " + (altura / 100) + " mtos" + 
            "\nSexo " + sexoCompleto  + 
            "\nLongitud zancada: " + longitudZancada);

    }

    /**
     * Muestra en pantalla informaci�n acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstad�sticas() {
        String queDia;
        if (totalDistanciaFinSemana > (totalPasosLaborables / 100))
        {queDia = "FESTIVO";}

        else{
            queDia = "LABORABLE";}

        System.out.println("Estad�sticas" +
            "\n*********************************" +
            "\nDistancia recorrida toda la semana: " + (totalDistanciaSemana / 1000) + " Km" + 
            "\nDistancia recorrida fin de semana: " + (totalDistanciaFinSemana / 1000) + " Km" + 
            "\n" +
            "\n" +
            "\nN� pasos d�as laborables: " + totalPasosLaborables + 
            "\nN� pasos S�BADO: " + totalPasosSabado + 
            "\nN� pasos DOMINGO: " + totalPasosDomingo + 
            "\n" +
            "\n" +
            "\nN� caminatas realizadas a partir de las 21h.: " + caminatasNoche + 
            "\n" +
            "\n" +
            "\nTiempo total caminado en la semana: " + (tiempo / 100) + "h " + (tiempo % 100) + "m" +
            "\nD�a/s con m�s pasos caminados: " + queDia);

    }
    /**
     *  Calcula y devuelve un String que representa el nombre del d�a
     *  en el que se ha caminado m�s pasos - "S�BADO"   "DOMINGO" o  "LABORABLES"
     */
    public void diaMayorNumeroPasos() {
        
        
    }

    /**
     * Restablecer los valores iniciales del pod�metro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no var�a
     *  
     */    
    public void reset() {

        altura = 0;
        sexo = MUJER;
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;

    }
}
