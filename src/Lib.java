import java.io.File;
import java.io.FileWriter;

import static java.lang.Math.sqrt;

public class Lib {
    private int [][] matrix;
    private int rank;
    private String outputFileName; //Target file address
    private boolean flag = false;

    public Lib(int[][] matrix,int rank,String outputFileName){
        this.matrix = matrix;
        this.rank = rank;
        this.outputFileName = outputFileName;
    }
    public Lib(){

    }

    public void Initialization(){
        backTrace(0,0);
        //Judging Solvability
        if(flag) {
            System.out.println("Success!!!!");
        } else{
           System.out.println("The test is insoluble!!!!!!");
           printf();
        }
    }

    public void backTrace(int i, int j){
        //End of Arrival Output
        if(i == (rank-1) && j == rank){
            flag = true;
            printf();
            return;
        }

        //Arrive at the end of the column, wrap
        if(j == rank){
            i++;
            j = 0;
        }

        //If the lattice is empty, the judgement is entered.
        if(matrix[i][j] == 0){
            for(int k = 1; k <= rank; k++){
                //Check whether the requirements are met
                if(check(i,j,k)){
                    //Give the value K to the lattice
                    matrix[i][j] = k;
                    backTrace(i,j+1);
                    //Initialize the lattice
                    matrix[i][j] = 0;
                }
            }
        }else {
            backTrace(i,j+1);
        }
    }

    private boolean check(int row, int line, int k){
        //Query whether rows or columns have duplicate numbers
        for(int i = 0 ;i < rank; i++){
            if(matrix[row][i] == k || matrix[i][line] == k)
                return  false;
        }

        //Query if there are duplicate numbers in the Nine-palace grid
        if(rank == 4 || rank == 9){
            int gong = (int)sqrt(rank);
            int _row = row / gong;
            int _line = line / gong;
            for(int i = 0; i < gong; i++){
                for(int j = 0; j < gong ;j++){
                    if(matrix[_row*gong + i][_line*gong + j] == k)
                        return false;
                }
            }
        }else if(rank == 6){
            int _row = row / 2;
            int _line = line / 3;
            for(int i = 0; i < 2; i++){
                for(int j = 0; j < 3 ;j++){
                    if(matrix[_row*2 + i][_line*3 + j] == k)
                        return false;
                }
            }
        }else if(rank == 8){
            int _row = row / 4;
            int _line = line / 2;
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 2 ;j++){
                    if(matrix[_row*4 + i][_line*2 + j] == k)
                        return false;
                }
            }
        }

        return true;
    }

    private void printf(){
        File file = new File(outputFileName);

        try{
            if(! file.exists()){
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file,true);

            if(flag){
                for(int i = 0 ;i < rank ;i ++){
                    for(int j = 0 ;j < rank; j++){
                        fw.write(matrix[i][j]+" ");
                    }
                    fw.write("\r\n");
                }
            }else{
                fw.write("The test is insoluble!!!!!!\r\n");
            }

            fw.write("\r\n");
            fw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
