import java.util.Random;
public class App {

    // --- len ---
    public static int tamanho (int[] array){
        int soma = 0;
        for(int i : array){
            soma += 1;
        }
        return soma;
    }

    /// --- vetorcrator ---
    public static int[] criarVetor(int tam){
        Random random = new Random();
        int[] vetor = new int[tam];
        for(int i = 0; i < tam; i++){
            vetor[i] = random.nextInt(100);
        }
        return vetor;
        
    }

    // --- printvetor ---
    public static void print(int[] array){
        for (int i : array) {
                System.out.print(i + " ");
               
                }
        System.out.print("\n");
    }

    // SHELL SORT --------------------------------------------------

    public class ShellSort {
        private static int iteracoes = 0;
        private static int trocas = 0;
        public static void shellSort(int[] array) {
            int n = tamanho(array);
            trocas = 0;
            iteracoes = 0;
        
            //for pra definição de gap e índice pro gap
            for (int gap = n / 2; gap > 0; gap /= 2) {
                // define key, o valor que o gap está apontando
                for (int i = gap; i < n; i++) {
                    int key = array[i];
                    int j = i;
                    iteracoes++;
                    // verifica se o atual é menor que o key (item do gap)
                    while (j >= gap && array[j - gap] > key) {
                        // troca padrão
                        array[j] = array[j - gap];
                        j -= gap;
                        trocas++;
                        iteracoes++; 
                    }
                    if (array[j] != key) { //verifica se precisa fazer troca
                        array[j] = key;
                        trocas++; 
                    }
                }
            }
        }
        
        public static void printmaluco(){
            System.out.println("Trocas: " + trocas);
            System.out.println("iteracoes: " + iteracoes);
        }
    }
    
    // MERGE SORT ------------------------------------------------

    public class MergeSort {
        private static int trocas = 0;
        private static int iteracoes = 0;
        public static void sort(int[] array) {
            if (tamanho(array) < 2) {
                return;
            }
            
            int metade_tam = tamanho(array) / 2;
            int[] esquerda = new int[metade_tam];
            int[] direita = new int[tamanho(array) - metade_tam];
            
            //transcriçao pro metade e resto
            //esquerda
            for(int i = 0; i < metade_tam; i++){
                esquerda[i] = array[i];
                iteracoes++;
            }
            //direita
            for(int i = metade_tam; i < tamanho(array); i++){
                direita[i - metade_tam] = array[i];
                iteracoes++;
            }
            //chama sort denovo fazendo a divisao de tudo
            sort(esquerda);
            sort(direita);
            merge(array, esquerda, direita);
        }
        // --- merge ---
        private static void merge(int[] array, int[] esquerda, int[] direita) {
            int i = 0, j = 0, k = 0;
            //loop pra esquerda e direira ao mesmo tempo
            while (i < tamanho(esquerda) && j < tamanho(direita)) {
                iteracoes++;
                //verifica qual é maior pra fazer o merge ordenado
                if (esquerda[i] <= direita[j]) {
                    array[k++] = esquerda[i++];
                    
                } else {
                    array[k++] = direita[j++];
                    
                }
                trocas++;
            }
            //verifica se restou algo(esquerda acabou e restou algo na direita vice e versa)
            while (i < tamanho(esquerda)) {
                array[k++] = esquerda[i++];
                trocas++;
                iteracoes++;
            }
            
            while (j < tamanho(direita)) {
                iteracoes++;
                array[k++] = direita[j++];
                trocas++;
            }
        }
        
        public static void printmaluco(){
            System.out.println("Trocas: " + trocas);
            System.out.println("Iteracoes: " + iteracoes);
        }

    }
    public class BubbleSort{
        private static int trocas = 0;
        private static int iteracoes = 0;
        public static void bubbleSort(int[] array) {

        int n = tamanho(array);
        int temp = 0;
        trocas = 0;   //reinicia tudo   
        iteracoes = 0;   
    
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                iteracoes++; 
                if (array[j - 1] > array[j]) {
                    //troca padrao
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                    trocas++; 
                }
            }
        }
        }
        //print dos requesitos
        public static void printmaluco(){
            System.out.println("Trocas: " + trocas);
            System.out.println("Iteracoes: " + iteracoes);
        }

    }
    
    public static void main(String[] args){
        //criaçao dos arrays com a quantidade desejada
        int[] arrayShell = criarVetor(5000);
        int[] arrayMerge = criarVetor(5000);
        int[] arrayBubble = criarVetor(5000);

        
        long inicio1 = System.nanoTime();
        ShellSort.shellSort(arrayShell); 
        long fim1 = System.nanoTime();
        long tempo_final1 = fim1 - inicio1;

        //print resultado
        
        System.out.println("SHELL --------------------------");
        
        System.out.println("Tempo em nano: " + tempo_final1);
        ShellSort.printmaluco();


        System.out.println("MERGE ---------------------------");
        long inicio = System.nanoTime();
        MergeSort.sort(arrayMerge);
        long fim = System.nanoTime();
        long tempo_final = fim - inicio;
        System.out.println("Tempo em nano: " + tempo_final);
        MergeSort.printmaluco();

        System.out.println("BUBBLE SORT ---------------------------");
        long inicio2 = System.nanoTime();  
        BubbleSort.bubbleSort(arrayBubble);
        long fim2 = System.nanoTime();
        long tempo_final2 = fim2 - inicio2;
        System.out.println("Tempo em nano: " + tempo_final2);
        BubbleSort.printmaluco();

        
    }
}