package queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
	// 優化Queue 讓他可以將取出後 能繼續使用
	public static void main(String[] args) {
		System.out.println("測試陣列模擬環形佇列");

		// 創建環形陣列
		CircleArray Queue = new CircleArray(4);//說明設置4,設置最大有效數據為3
		char key = ' '; // 接收使用者輸入
		Scanner sc = new Scanner(System.in);
		boolean loop = true;
		// 選項menu
		while (loop) {
			System.out.println("s(show): 顯示陣列");
			System.out.println("e(exit): 退出程序");
			System.out.println("a(add): 增加資料到陣列");
			System.out.println("g(get): 從陣列取出資料");
			System.out.println("h(head): 查看陣列頭部資料");
			key = sc.next().charAt(0);
			switch (key) {
			case 's':
				Queue.showQueue();
				break;
			case 'a': // 添加資料
				System.out.println("輸入一個數字");
				int value = sc.nextInt();
				Queue.addQueue(value);
				break;
			case 'g': // 取資料
				try {
					int res = Queue.getQueue();
					System.out.printf("取出的資料是%d\n", res);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 'h': // 查看陣列頭部資料
				try {
					int res = Queue.headQueue();
					System.out.printf("陣列的頭部資料是%d\n", res);
				} catch (Exception e) {
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

class CircleArray {
	private int maxSize; // 陣列最大容量

	// front 調整 指向陣列第一個位置
	// front初始為0
	private int front;
	// rear 調整 rear 指向陣列最後一個元素後一位 空出一個空間
	// rear 的初始值 = 0
	private int rear; // 陣列尾
	private int[] arr;// 模擬佇列,用於存放資料

	public CircleArray(int arrMaxSize) {
		maxSize = arrMaxSize;
		arr = new int[maxSize];

	}

	// Full 調整 是否為滿
	public boolean isFull() {
		return (rear + 1) % maxSize == front;
	}

	// 判斷陣列是否為空
	public boolean isEmpty() {
		return rear == front;
	}

	// 增加資料到陣列
	public void addQueue(int n) {
		// 先判斷陣列是否為滿
		if (isFull()) {
			System.out.println("陣列滿了");
			return;
		}
		// 直接將資料加入
		arr[rear] = n;
		// 將rear後移,必須考慮 %
		rear = (rear + 1) % maxSize;
	}

	// 得到陣列資料
	public int getQueue() {
		// 判斷陣列是否為空
		if (isEmpty()) {
			// 要例外處理
			throw new RuntimeException("陣列為空,沒有資料");
		}
		// 需要分析 front是指向陣列第一個元素
		// 1.先把front 對應的值 保存到一個變數中
		// 2.將front後移,取%
		// 3.將保存的變數返回
		int value = arr[front];
		front = (front + 1) % maxSize;
		return value;
	}

	// 顯示陣列的所有資料
	public void showQueue() {
		// 遍歷
		if (isEmpty()) {
			System.out.println("陣列為空沒資料");
			return;
		}
		// 從front開始遍歷,遍歷多少個元素
		for (int i = front; i < front + size(); i++) {
			System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
		}
	}

	// 求出當前陣列有效數據的個數
	public int size() {
		// rear = 1
		// front =0
		// maxSize =3
		return (rear + maxSize - front) % maxSize;
	}

	// 顯示陣列頭資料,不是取出
	public int headQueue() {
		// 判斷
		if (isEmpty()) {
			throw new RuntimeException("陣列空,沒資料");
		}
		return arr[front];
	}

}
