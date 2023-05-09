import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bol {
    public static void main(String[] args) {
        // Test cases
        String sentence = "Java öğrenmeyi seviyorum ama zor olmasa keşke";
        int n = 3;
        List<String> result = kelimeleriAyir(sentence, n);
        System.out.println(result);

        n = 6;
        result = kelimeleriAyir(sentence, n);
        System.out.println(result);
    }

    public static List<String> kelimeleriAyir(String sentence, int n) {
        List<String> words = Arrays.asList(sentence.split(" ")); // split the sentence into words
        List<String> result = new ArrayList<>();

        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            if (word.length() <= n) { // if the word is n or fewer characters, add it to the result
                result.add(word);
            } else { // otherwise, try to split the word into pieces with n or fewer characters
                List<String> pieces = new ArrayList<>();
                String piece = "";
                for (int j = 0; j < word.length(); j++) {
                    piece += word.charAt(j);
                    if (piece.length() == n) { // if the piece has n characters, add it to the list of pieces
                        pieces.add(piece);
                        piece = "";
                    }
                }
                if (!piece.equals("")) { // if there is any remaining piece, add it to the list of pieces
                    pieces.add(piece);
                }
                if (pieces.size() == 1) { // if the word cannot be split, add it to the result
                    result.add(word);
                } else { // otherwise, generate all possible combinations of pieces and add them to the result
                    for (int j = 0; j < pieces.size(); j++) {
                        String current = pieces.get(j);
                        if (current.length() <= n) { // add the piece if it is n or fewer characters
                            result.add(current);
                        }
                        for (int k = j + 1; k < pieces.size(); k++) {
                            current += " " + pieces.get(k);
                            if (current.length() <= n) { // add the combination if it is n or fewer characters
                                result.add(current);
                            } else { // if the combination is too long, break the loop
                                break;
                            }
                        }
                    }
                }
            }
        }

        return result;
    }
}
