/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import config.Config;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import php.PHPTokenParser;
import utiles.Streams;

/**
 *
 * @author ado
 */
public class testerPHPTokenizer {
    
    public static void main(String[] args)
    {
        PHPTokenParser runner = new PHPTokenParser(Config.PHP_PATH, Config.PHPFORMATER_SCRIPT_PATH, Config.PHPFORMATER_DATA_OUTPUT_PATH);
        
        try {

//            String res = Streams.StreamToString(runner.run(), null);
//            System.out.println(res);
            
//            PHPTokensParser.parse(runner.run(), (t) -> {
//                System.out.print(t.getToken());
//            });





                String r = Streams.StreamToString(runner.run(), null);
                System.out.println(r);
        } catch (Exception e) {
            System.out.println("[Error] problema ejecutando proceso php: " + e.getMessage());
        }
    }
    
    public static void testMatcher(String input)
    {
        Pattern pattern = Pattern.compile("^\\w+\\s*<.*?>[\\n\\r]*(\\w+\\s*<.+?>[\\n\\r]*)+$", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(input);
        
        if (matcher.find())
        {
            System.out.println("yes");
            
        }
        else
            System.out.println("no");
    }
}
