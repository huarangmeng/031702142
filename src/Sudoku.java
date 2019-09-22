import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Sudoku {
    private static String inputFileName;
    private static String outputFileName;
    private static int phraseWordNum;
    private static int sortedPrintNum;

    public static void main(String[] args) throws IOException {
        //Initialization
        loadargs(args);

        File file = new File(inputFileName);

        int x[][] = new int[9][9];
        int row = 0;
        int line = 0;
        String str = "";
        if(! file.exists()){
            System.out.println("对不起，不包含指定路径的文件");
        }else{
            try{
                FileReader fr = new FileReader(file);

                char[] data = new char[23];
                int length = 0;

                while((length = fr.read(data)) >0){
                    str = new String(data,0,length);
                    for(int j = 0; j < str.length(); j++){
                        if(str.charAt(j) >47 && str.charAt(j)<58){
                            char c = str.charAt(j);
                            x[row][line++] = Character.getNumericValue(c);
                            if(line == phraseWordNum){
                                //Line feed
                                row++;
                                line = 0;
                            }
                        }
                        if(row == phraseWordNum){
                            //A disk is well stored
                            Lib lib = new Lib(x,phraseWordNum,outputFileName);
                            lib.Initialization();
                            row = 0;
                            line = 0;
                        }
                    }
                }
                //Close the read stream
                fr.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    static void loadargs(String[] args){
        if(args.length > 0 && args != null){
            for(int i = 0; i < args.length; i++){
                switch (args[i]){
                    case "-i":
                        inputFileName = args[++i];
                        break;
                    case "-o":
                        outputFileName = args[++i];
                        break;
                    case "-m":
                        phraseWordNum = Integer.valueOf(args[++i]);
                        break;
                    case "-n":
                        sortedPrintNum = Integer.valueOf(args[++i]);
                        break;
                    default:
                        break;
                }
            }
        }else{
            System.out.println("No input parameters");
            System.exit(1);
        }
    }
}
