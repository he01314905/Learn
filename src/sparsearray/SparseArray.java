package sparsearray;

import javax.crypto.NoSuchPaddingException;

public class SparseArray {
public static void main(String[] args) {
	//創建一個原始二維陣列 11 * 11
	//0:表示沒有棋子,1表示黑子,2表示白子
	int chessArr[][]=new int[11][11];
	chessArr[1][2]=1;
	chessArr[2][3]=2;
	//輸出原始二維陣列
	System.out.println("原始二維陣列:");
	for(int[] row : chessArr) {
		for(int data : row) {
			System.out.printf("%d\t",data);
		}
		System.out.println();
	}
	
	//將二維陣列 轉 稀疏矩陣
	//1. 先遍歷二維陣列 得到非0數據的個數
	int sum = 0;
	for(int i = 0;i< 11; i++) {
		for(int j = 0;j < 11;j++) {
			if(chessArr[i][j]!= 0) {
				sum++;
			}
		}
	}
	System.out.println("sum="+sum);
	
	//創建對應的稀疏矩陣    						//row(行)  col(列) vol(有效的數據個數)
	int sparseArr[][]=new int[sum+1][3];	// 11       11     2
	// 給稀疏矩陣賦值
	sparseArr[0][0]=11;
	sparseArr[0][1]=11;
	sparseArr[0][2]=sum;
	
	//遍歷二維陣列,將非0的值存到 sparseArr中
	int count = 0;
	for(int i = 0;i< 11; i++) {
		for(int j = 0;j < 11;j++) {
			if(chessArr[i][j]!= 0) {
				count++;
				sparseArr[count][0] = i;
				sparseArr[count][1] = j;
				sparseArr[count][2] = chessArr[i][j];
			}
		}
		
	}
	//輸出稀疏矩陣
	System.out.println();
	System.out.println("得到的稀疏矩陣為:");
	for(int i = 0; i < sparseArr.length; i++) {
		System.out.printf("\n%d\t%d\t%d\t",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
	}
	System.out.println();
}

}
