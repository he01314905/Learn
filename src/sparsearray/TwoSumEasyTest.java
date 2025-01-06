package sparsearray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class TwoSumEasyTest {

	public static void main(String[] args) throws IOException {
		Scanner g = new Scanner(System.in);
		String[] b = g.nextLine().split(" ");

		int[] nums = new int[b.length];
		for(int i=0;i<b.length;i++) {
			nums[i] = Integer.parseInt(b[i]);
		}
		System.out.println("輸入target:");
		int d = g.nextInt();
		boolean aa =false;
		for(int i=0;i<nums.length;i++) {
			for(int j =i+1;j<nums.length;j++) {
				int num = nums[i]+nums[j];
				if(num==d) {
					System.out.println("["+i+","+j+"]");
					aa=true;
				}
			}
			if(aa) {
				break;
			}
		}
		g.close();
	}
}
