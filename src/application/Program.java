package application;
import model.entities.Jogador;
import model.entities.AdministracaoTime;


import java.util.*;

public class Program
{
    public static void main (String[] args) {
        List<Jogador> jogadores = new ArrayList<>();
        List<Jogador> escalados = new ArrayList<>();
        List<Jogador> reservas = new ArrayList<>();
        List<String> numeros = new ArrayList<>();

        AdministracaoTime time = new AdministracaoTime(jogadores,escalados,reservas,numeros);
        Menu(time);
    }
    public static void Menu(AdministracaoTime time)
    {
        while (true)
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("==============================");
            System.out.println("            MENU");
            System.out.println("==============================");
            System.out.println("""
                    1- Ler escalação
                    2- Escalar time
                    3- Realizar substituição
                    4- Expulsão
                    5- Imprimir escalação
                    """);
            System.out.println("Escolha:");
            String escolha = sc.nextLine();
            if (escolha.equals("1"))
            {
                time.LerEscalacao();
            }
            if (escolha.equals("2"))
            {
                time.EscalarTime();
            }
            if (escolha.equals("3"))
            {
                time.Substituir();
            }
            if (escolha.equals("4"))
            {
                time.Expulsar();
            }
            if (escolha.equals("5"))
            {
                time.ImprimirEscalacao();
                sc.close();
                break;
            }

        }
    }
}
