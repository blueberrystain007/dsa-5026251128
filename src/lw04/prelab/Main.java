package lw04.prelab;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(Main.class.getResourceAsStream("Branches.txt"));
        int N = input.nextInt();
        int[] SV = new int[N];
        for(int i = 0; i < N; i++){
            SV[i] = input.nextInt();
        }
        int M = input.nextInt();
        int[] target = new int[M];
        for(int i = 0; i < M; i++){
            target[i] = input.nextInt();
        }

        for(int i = 1; i <= 3; i++) {
			int key = SV[i];
			int current = i;
			while(current > 0 && SV[current - 1] < key) {
				SV[current] = SV[current - 1];
				current--;
			} 
			SV[current] = key;
		}

        for(int x : SV){
            System.out.print(x + " ");
        }
        System.out.println();

        for(int i = 0; i < target.length; i++){
            boolean found = false;
            for(int j = 0; j < SV.length; j++){
                if(SV[j] == target[i]){
                    System.out.println(target[i] + ": FOUND at index " + j);
                    found = true;
                    break;
                }
            }
            if(found == false){
                System.out.println(target[i] + ": NOT FOUND");
            }
        }
    }
}