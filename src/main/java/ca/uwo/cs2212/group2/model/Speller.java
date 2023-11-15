// Incomplete speller object, currently only has a spellcheck function
//test

public class Speller {
    /*
     * textproccessor usertext; 
     * dictionary dict;
     * Metrics metric;
     */
    public Speller(String intext){
        //1. Create textproccessor object out of given text

        //2. Create dictionary either from "userdict.txt" or "dict.txt"

        //3. Iterate through all words of textproccessor, calling spellcheck on the word

            //3a. Check if word in dict, if not, call LevDam on word for each word in dict, return 4 lowest distance word
    }

    // private Spellcheck(textproccessor usertext, dictionary dict)

    // Levenstein Damerau with NO TRANSPOSITION t,s: O(s1 s2)
    private static int LevDam(String s1, String s2)
    {
        //Creating the matrix, this will hold all possible 
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        //Set up an i x j grid with first row labeled 1 - len(s1), second row labeled 1 - len(s2)
        for (int i = 0; i < s1.length(); i++){
            dp[i][0] = i;
        }
        for (int j = 0; j < s2.length(); j++){
            dp[0][j] = j;
        }

        // Analyze each possible combination of substrings
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j -1)){
                    //If the characters match, then we can reduce it to a smaller subproblem where the strings have the common letter removed
                    dp[i][j] = dp[i - 1][j - 1];
                    }
                else{
                    //Otherwise, we need to compute thenumber of moves required to convert string
                    // Minimum between delete, insert, replace of the subproblems on the grid (Can explain if you guys want)
                    dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                    }
                }
            }
        return dp[s1.length()][s2.length()];
        }
}