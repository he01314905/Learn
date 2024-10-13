package queue;

import java.util.Scanner;

public class ArrayQueueDemo {
public static void main(String[] args) {
	//創建陣列
	ArrayQueue Queue = new ArrayQueue(3);
	char key =' '; //接收使用者輸入
	Scanner sc = new Scanner(System.in);
	boolean loop = true ;
	//選項menu
	while(loop) {
		System.out.println("s(show): 顯示陣列");
		System.out.println("e(exit): 退出程序");
		System.out.println("a(add): 增加資料到陣列");
		System.out.println("g(get): 從陣列取出資料");
		System.out.println("h(head): 查看陣列頭部資料");
		key =sc.next().charAt(0);
		switch(key) {
		case 's':
			Queue.showQueue();
			break;
		case 'a': //添加資料
			System.out.println("輸入一個數字");
			int value=sc.nextInt();
			Queue.addQueue(value);
			break;
		case 'g': //取資料
			try {
				int res = Queue.getQueue();
				System.out.printf("取出的資料是%d\n",res);
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case 'h': //查看陣列頭部資料
			try {
				int res = Queue.headQueue();
				System.out.printf("陣列的頭部資料是%d\n",res);
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case 'e':
			sc.close();
			loop = false;
		default:
			break;
		}
	}
	System.out.println("程式退出...");
	}
}

//使用陣列模擬佇列-編寫一個ArrayQueue Class

class ArrayQueue{
	private int maxSize; //陣列最大容量
	private int front; //佇列排頭
	private int rear ; //排尾
	private int [] arr ;//模擬佇列,用於存放資料
	
	//創建陣列構造
	public ArrayQueue(int arrMaxSize) {
		maxSize = arrMaxSize;  
		arr = new int[maxSize];
		front = -1; //指向陣列頭 , 分析出 front是指向陣列頭前一位
		rear = -1;	// 指向陣列尾 , 指尾巴的實際位置(即陣列最後一位)
	}
	//判斷陣列是否滿
	public boolean isFull() {
		return rear == maxSize -1;
	}
	
	//判斷陣列是否為空
	public boolean isEmpty() {
		return rear == front;
	}
	
	//增加資料到陣列
	public void addQueue(int n) {
		//先判斷陣列是否為滿
		if(isFull()) {
			System.out.println("陣列滿了");
			return;
		}
		rear++; // 讓rear後移一位
		arr[rear] =n;
	}
	//得到陣列資料
	public int getQueue() {
		//判斷陣列是否為空
		if(isEmpty()) {
			//要例外處理
			throw new RuntimeException("陣列為空,沒有資料");
		}
		front++; //front後移
		return arr[front];
	}
	//顯示陣列的所有資料
	public void showQueue() {
		//遍歷
		if(isEmpty()) {
			System.out.println("陣列為空沒資料");
			return;
		}
		for(int i = 0 ;i<arr.length;i++) {
			System.out.printf("arr[%d]=%d\n",i,arr[i]);
		}
	}
	//顯示陣列頭資料,不是取出
	public int headQueue() {
		//判斷
		if(isEmpty()) {
			
			throw new RuntimeException("陣列空,沒資料");
		}
		return arr[front +1];
	}
	
}
