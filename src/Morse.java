import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Morse {
    public static void main(String[] args) {
        System.out.println(possibilities("..-"));
        System.out.println(possibilities("."));
        System.out.println(possibilities("?-"));
        System.out.println(possibilities(".??"));
    }
    public static List<String> possibilities(String signals) {
        List<String> possibleMatches = new ArrayList<>();
    /*
      Busca todas as combinações possíveis de substituição do sinal ? por . ou -
      Caso não haja nenhum ? no signals informado, será retorndo apenas o signals informado
    */
        List<String> permutations = getPermutations(signals);

    /*
      Seleciona do Map MORSE_DICTIONARY cada possível resultado para cada signals
    */
        for (String signal: permutations){
            if (MORSE_DICTIONARY.containsKey(signal)){
                possibleMatches.add(MORSE_DICTIONARY.get(signal));
            }
        }
        return possibleMatches;
    }

    /*
      Método para trocar todos os sinais ? por . E -
      Retorna uma lista com todas as possíveis trocas de sinais ? por . ou -
    */
    private static List<String> getPermutations(String signals){
        List<String> results = new ArrayList<>();

    /*
      Verifica se existe realmete um sinal ?
      Se não houver um ?, retorna o próprio signal recebido no método
    */
        int index = signals.indexOf('?');
        if (index == -1){
            results.add(signals);
            return results;
        }

        // Salva todo o prefixo até o sinal ?
        String prefix = signals.substring(0, index);

        // Salva todo o sufixo depois do sinal ?
        String suffix = signals.substring(index + 1);

        // A partir daqui existem dois caminhos: trocar o sinal ? por . ou por -

    /*
      Troca o ? pelo . , e chama o próprio getPermutations(signal),
      passando agora o . no lugar do ? como signal,
      utilizando os prefixo e sufixo obtidos anteriormente
    */
        results.addAll(getPermutations(prefix + "." + suffix));

    /*
      Troca o ? pelo - , e chama o próprio getPermutations(signal),
      passando agora o - no lugar do ? como signal,
      utilizando os prefixo e sufixo obtidos anteriormente
    */
        results.addAll(getPermutations(prefix + "-" + suffix));

    /*
      Caso haja outro sinal ?, ele repete o mesmo processo, até trocar todos os sinais ?
      Importante: caso haja, por exemplo, dois sinais ? em um signal de dois dígitos,
      para cada sinal ele irá realizar o processo 4 vezes, gerando 4 signals.
    */
        return results;
    }

    private static final Map<String, String> MORSE_DICTIONARY = Map.ofEntries(
            Map.entry(".", "E"),
            Map.entry("-", "T"),
            Map.entry("..", "I"),
            Map.entry(".-", "A"),
            Map.entry("-.", "N"),
            Map.entry("--", "M"),
            Map.entry("...", "S"),
            Map.entry("..-", "U"),
            Map.entry(".-.", "R"),
            Map.entry(".--", "W"),
            Map.entry("-..", "D"),
            Map.entry("-.-", "K"),
            Map.entry("--.", "G"),
            Map.entry("---", "O")
    );

}