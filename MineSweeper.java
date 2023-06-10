package mayıntarlasıoyunu;

import java.util.Random;
import java.util.Scanner;

public class MineSweeper {

    String zemin[][],bos[][];
    int tnt, satir, sutun;

    public MineSweeper(int satir, int sutun) {
        this.satir = satir;
        this.sutun = sutun;
        this.zemin = new String[satir][sutun];
        this.bos=new String[satir][sutun];
        this.tnt = satir * sutun / 4;
    }

    void yerlestir() {
        for (int i = 0; i < zemin.length; i++) {
            for (int j = 0; j < zemin[i].length; j++) {
                this.zemin[i][j] = "-";
                this.bos[i][j] = "-";
            }
        }
    }

    void tntyerlestir() {
        Random rndm = new Random();
        while (this.tnt > 0) {
            int rsatir = rndm.nextInt(satir);
            int rsutun = rndm.nextInt(sutun);
            if (!"*".equals(this.bos[rsatir][rsutun])) {
                this.bos[rsatir][rsutun] = "*";
            }
            this.tnt--;
        }
    }

    int check(int x, int y) {
        int counter = 0;
        if("*".equals(this.bos[x][y])){
            return -100;
        }
        else if(x>=0 && x<bos.length && y>=0 && y<bos[0].length){
            for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i >= 0 && i < bos.length && j >= 0 && j < bos[i].length) {
                    if (bos[i][j].equals("*")) {
                        counter++;
                    }
                }
            }
        }
       }
        return counter;
    }

   void print() {
    for (int i = 0; i < zemin.length; i++) {
        for (int j = 0; j < zemin[i].length; j++) {
            if (zemin[i][j].equals("-")) {
                System.out.print("-");
            } else {
                System.out.print("*");
            }
        }
        System.out.println();
    }
}


    void run() {
        Scanner input = new Scanner(System.in);
        boolean durum = true;
        this.yerlestir();
        this.tntyerlestir();

        this.print();
        while (durum) {
            System.out.println("Satır sayısı girin:");
            int row = input.nextInt();
            if (row < 0 || row >= satir) {
                System.out.println("Hatalı değer girişi...");
                continue;
            }
            System.out.println("Sütun sayısı: ");
            int col = input.nextInt();
            if (col < 0 || col >= sutun) {
                System.out.println("Hatalı değer girişi");
                continue;
            }
            row--;
            col--;
            int sayi = this.check(row, col);
            if (sayi == -100) {
                System.out.println("GAME OVER! Mayına basıldı.");
                break;
            } else {
                zemin[row][col] = String.valueOf(sayi);
                print();
            }
        }
    }

}

