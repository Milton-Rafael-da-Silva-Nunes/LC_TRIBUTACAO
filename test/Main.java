
import java.util.Scanner;

/**
 *
 * @author Rafael Nunes
 */
public class Main {

    public static void testeLinear(int codigo) {

        System.out.println("Qual NCM deseja buscar? ");
        int codigoNcm = codigo;

        int[] lista = new int[1000000];
        for (int i = 0; i < lista.length; i++) {
            lista[i] = i;
        }
        
        int contador = 0;

        boolean achou = false;
        for (int str : lista) {
            if (str == codigoNcm) {
                achou = true;
                break;
            }
            contador++;
        }

        System.out.print("Quantidade de teste: " + contador);
        
        if (achou) {
            System.out.println("CONTEM CODIGO: " + codigoNcm);
        } else {
            System.out.println("NÃO CONTEM CODIGO: " + codigoNcm);
        }
    }

    public static void testeBinario1(int codigo) {

        int contador = 0;
        boolean achou = false;

        int[] vetor = new int[1000000];
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = i;
        }

        int inicio = 0;
        int meio;
        int fim = vetor.length - 1;

        while (inicio <= fim) {
            meio = (int) (inicio + fim) / 2;
            contador++;

            if (vetor[meio] == codigo) {
                achou = true;
                break;
            } else if (vetor[meio] < 0) {
                inicio = meio + 1;
            } else {
                fim = meio - 1;
            }
        }

        System.out.println("Quantidade de testes: " + contador);

        if (achou) {
            System.out.println("Achou!");
        } else {
            System.out.println("Não achou!");
        }
    }

    public static void testeBinario2(int codigo) {

        int[] vetor = new int[1000000];

        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = i * 2;
        }

        int contador = 0;
        boolean achou = false;
        int inicio = 0;
        int fim = vetor.length - 1;
        int meio;

        while (inicio <= fim) {
            meio = (int) ((inicio + fim) / 2);

            contador++; // Conta quantas vezes contou

            if (vetor[meio] == codigo) {
                achou = true;
                break;
            } else if (vetor[meio] < codigo) {
                inicio = meio + 1;
            } else {
                fim = meio - 1;
            }
        }
        System.out.println("Quantidade de teste: " + contador);

        if (achou == true) {
            System.out.println("Achou!");
        } else {
            System.out.println("Não achou!");
        }
    }

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.println("QUAL NUMERO DESEJA BUSCAR? ");
        int codigo = sc.nextInt();
        
        System.out.println("Teste Linear");
        testeLinear(codigo);
        System.out.println("\nTeste Binario 1");
        testeBinario1(codigo);
        System.out.println("\nTeste binario 2");
        testeBinario2(codigo);
        
        sc.close();

    }
}
