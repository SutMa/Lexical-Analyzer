


import java.util.*;
import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.Mapl;


/**
 *
 * @author sutlama
 */
public class Lama_Lexer {
    
    public static Character readingToken;
    
    public static String lex = "";
    
    public static ArrayList<String> result = new ArrayList<>();
   
    
    

    public static final Map<String, String> customTokens = new HashMap<>() {
        {
        put("if", "IF");
        put("else", "ELSE");
        put("while", "WHILE");
        put("for", "FOR");
        put("procedure", "PROC");
        put("return", "RETURN");
        put("int", "INT");
        put("else", "ELSE");
        put("do", "DO");
        put("break", "BREAK");
        put("end", "END");
        put("\\=", "ASSIGN");
        put("\\+", "ADD_OP");
        put("\\-", "SUB_OP");
        put("\\*", "MUL_OP");
        put("/", "DIV_OP");
        put("%", "MOD_OP");
        put(">", "GT");
        put("<", "LT");
        put(">=", "GE");
        put("++", "INC");
        put("(", "LP");
        put(")", "RP");
        put("{", "LB");
        put("}", "RB");
        put("|", "OR");
        put("&", "AND");
        put("==", "EE");
        put("!", "NEG");
        put(",", "COMMA");
        put(";", "SEMI");
        }
    };

    public static void nextToken(BufferedReader holder) throws IOException {
        readingToken = (char) holder.read();
    }

    public static void Tokenize(String fileName) throws IOException {
        
        BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
        
        int hasNextChar = 0;
        
        while ((hasNextChar = reader.read())!= -1) {
            readingToken = (char) hasNextChar;
            while(Character.isLetter(readingToken) || Character.isDigit(readingToken)) {
                lex += readingToken;
                nextToken(reader);
            } if (customTokens.containsKey(lex)) {
                try {
                    
                    Integer.parseInt(lex);
                    result.add("INT_CONST");
                    lex = "";
                } catch (NumberFormatException e) {}
            }
            if (customTokens.containsKey(lex)) {
                
                result.add(customTokens.get(lex));
                lex = "";
            }
            if(!customTokens.containsKey(lex) && lex.length() >= 1 && Character.isLetter(lex.charAt(0))) {
                result.add("IDENT");
                lex = "";
            }
            if(customTokens.containsKey(readingToken.toString())) {
                
                if (readingToken == '<'){
                    nextToken(reader);
                    if (readingToken == '='){
                    result.add("LE");
                    }else {
                    result.add("LT");
                    }
                        
                }else if (readingToken == '+'){
                nextToken(reader);
                if(readingToken =='+'){
                result.add("INC");
                }else {
                result.add("ADD_OP");
                }
                
                }else if (readingToken =='>'){
                    nextToken(reader);
                    if (readingToken == '='){
                    result.add("GE");
                    }else{
                    result.add("GT");
                    }
                
                }else if (readingToken == '='){
                    nextToken(reader);
                    if (readingToken == '='){
                    result.add("EE");
                    } else {
                    result.add("ASSIGN");
                    }
                }
                
                
                else{
                    result.add(customTokens.get(readingToken.toString()));
                    
                }
                
                lex = "";
                
          
                
                
            }
                
                
            if(lex.equals("")) { continue; }
            else {
                result.add("ERROR: INVALID IDENTIFIER N");
                break;
            }
        }
        
        for (String s : result) {
           
            System.out.println(s);
        }
    }


  
    
}
