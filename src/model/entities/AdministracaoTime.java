package model.entities;

import model.enums.JogadorStatus;
import model.exceptions.EscalacaoError;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class AdministracaoTime {
    public List<Jogador> jogadores;
    public List<Jogador> escalados;
    public List<Jogador> reservas;
    public List<String> numeros;

    public AdministracaoTime(List<Jogador> jogadores,List<Jogador> escalados,List<Jogador> reservas,
                             List<String> numeros)
    {
        this.jogadores = jogadores;
        this.escalados = escalados;
        this.reservas = reservas;
        this.numeros = numeros;
    }

    public void LerEscalacao()
    {
        Scanner input = null;
        String path = "C:\\Users\\napst\\IdeaProjects\\trabalho-escalacao-ivonei-em-java\\src\\application\\convocados.txt";
        File arquivo = new File(path);
        try
        {
            input = new Scanner(arquivo);
            while (input.hasNextLine())
            {
                String[] lista = input.nextLine().split(":");
                String numero = lista[0];
                String nome = lista[1];
                String posicao = lista[2];
                jogadores.add(new Jogador(numero,nome,posicao, JogadorStatus.NORMAL));
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (input != null)
            {
                input.close();
            }
        }
    }
    public void EscalarTime() {
        Scanner sc = new Scanner(System.in);
        while (escalados.size() < 11)
        {
            System.out.println("Digite o número do jogador escalado: ");
            String numeroEscalado = sc.nextLine();
            validarEscalacao(numeroEscalado);
            for (Jogador jogador : jogadores)
            {
                if (numeroEscalado.equals(jogador.getNumero()))
                {
                    jogador.setParticipouPartida(true);
                    escalados.add(jogador);
                    numeros.add(jogador.getNumero());
                }
            }
            jogadores.removeIf(jogador -> numeroEscalado.equals(jogador.getNumero()));
            reservas = jogadores;
        }
    }

    public void Substituir()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o número do jogador a ser substituído: ");
        String substituido = sc.nextLine();
        System.out.println("Digite o número do jogador que vai entrar na partida: ");
        String substituto = sc.nextLine();
        validarSubstituicao(substituto,substituido);
        for (Jogador escalado : escalados)
        {
            if (escalado.getNumero().equals(substituido))
            {
                reservas.add(escalado);
                escalados.remove(escalado);
                break;
            }
        }
        for (Jogador reserva : reservas)
        {
            if (reserva.getNumero().equals(substituto))
            {
                reserva.setParticipouPartida(true);
                escalados.add(reserva);
                reservas.remove(reserva);
                break;
            }
        }

    }

    public void Expulsar()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o número do jogador a ser expulso: ");
        String expulso = sc.nextLine();
        validarExpulsao(expulso);
        for (Jogador escalado : escalados)
        {
            if (expulso.equals(escalado.getNumero()))
            {
                reservas.add(new Jogador(escalado.getNumero(),escalado.getNomeJogador(),escalado.getPosicao(), JogadorStatus.EXPULSO));
            }

        }
        escalados.removeIf(jogador -> expulso.equals(jogador.getNumero()));
    }
    public void ImprimirEscalacao()
    {
        try
        {
            File arquivo = new File("C:\\Users\\napst\\IdeaProjects\\trabalho-escalacao-ivonei-em-java\\src\\application\\todosjogadores.txt");
            if (arquivo.createNewFile())
            {
                System.out.println("Arquivo criado: " + arquivo.getName());
            } else
            {
                System.out.println("Arquivo já existe.");
            }
        } catch (IOException io)
        {
            System.out.println("Um erro ocorreu.");
            io.printStackTrace();
        }
        try
        {
            FileWriter writter = new FileWriter("C:\\Users\\napst\\IdeaProjects\\trabalho-escalacao-ivonei-em-java\\src\\application\\todosjogadores.txt");
            for (Jogador escalado : escalados)
            {
                writter.write(escalado.getNumero()+":"+escalado.getNomeJogador()+":"+escalado.getPosicao()+"\n");
            }
            for (Jogador reserva : reservas)
            {
                if (reserva.getParticipouPartida())
                {
                    writter.write(reserva.getNumero()+":"+reserva.getNomeJogador()+":"+reserva.getPosicao()+"\n");
                }
            }
            writter.close();
        } catch (IOException io2)
        {
            System.out.println("Erro no writter.");
            io2.printStackTrace();
        }
        File filee = new File("C:\\Users\\napst\\IdeaProjects\\trabalho-escalacao-ivonei-em-java\\src\\application\\todosjogadores.txt");
        try (Scanner filereader = new Scanner(filee))
        {
            while (filereader.hasNextLine()) {
                System.out.println(filereader.nextLine());
            }
        } catch (IOException io4) {
            io4.printStackTrace();
        }
        try
        {
            File fileName = new File("C:\\Users\\napst\\IdeaProjects\\trabalho-escalacao-ivonei-em-java\\src\\application\\todosjogadores.txt");
            boolean result = Files.deleteIfExists(Paths.get(fileName.toURI()));
            if (result) {
                System.out.println("Arquivo deletado.");
            } else
            {
                System.out.println("Erro ao deletar arquivo.");
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void validarEscalacao(String input)
    {
        if (numeros.contains(input))
        {
            throw new EscalacaoError("Jogador já foi escalado.");
        }
        if (Integer.parseInt(input) > 26 || Integer.parseInt(input) < 1)
        {
            throw new EscalacaoError("O número do jogador não pode ser maior que 26 ou menor que 1.");
        }
    }

    public void validarSubstituicao(String substituto,String substituido)
    {
        List<String> reservasNumeros = new ArrayList<>();
        if (Integer.parseInt(substituido) > 26 || Integer.parseInt(substituido) < 1 ||
                Integer.parseInt(substituto) > 26 || Integer.parseInt(substituto) < 1)
        {
            throw new EscalacaoError("O número do jogador não pode ser maior que 26 ou menor que 1.");
        }
        if (!numeros.contains(substituido))
        {
            throw new EscalacaoError("Jogador não foi escalado.");
        }
        for (Jogador reserva : reservas)
        {
            reservasNumeros.add(reserva.getNumero());
        }
        if (!reservasNumeros.contains(substituto))
        {
            throw new EscalacaoError("Jogador não está no banco de reservas.");
        }
    }

    public void validarExpulsao(String jogadorExpulso)
    {
        if (Integer.parseInt(jogadorExpulso) > 26 || Integer.parseInt(jogadorExpulso) < 1)
        {
            throw new EscalacaoError("O número do jogador não pode ser maior que 26 ou menor que 1.");
        }
        if (!numeros.contains(jogadorExpulso))
        {
            throw new EscalacaoError("Jogador não foi escalado.");
        }
    }
}
