package linkedlist;

public class Josephu {
public static void main(String[] args) {
	//test
	CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
	circleSingleLinkedList.addBoy(3);//加入五個小孩
	circleSingleLinkedList.show();
}
}

//創建一個環形單向鏈表
class CircleSingleLinkedList{
	//創建一個first節點,先不賦值,當前沒編號
	private Boy first = new Boy(-1); // 後面做修改
	//新增節點,構建成一個環形鏈表
	public void addBoy(int nums) { //要加幾個節點
		//nums 數據驗證
		if(nums <1) {
			System.out.println("至少一個節點");
			return;
		}
		Boy curBoy=null; //輔助變數,幫助建構環形
		//for 循環來創建環形鏈表
		for(int i =1 ;i<= nums;i++) {
			//根據編號,創建節點
			Boy boy =new Boy(i);
			//如果是第一個
			if(i==1) {
				first= boy;
				first.setNext(first);
				curBoy=first;//讓curBoy指向第一個
			}else {
				curBoy.setNext(boy);
				boy.setNext(first);
				curBoy=boy;
			}
		}
	}
	
	//遍歷當前的環形鏈表
	public void show() {
		//先判斷是否為空
		if(first==null) {
			System.out.println("鏈表為空");
			return;
		}
		//因為first不能動,需要一個輔助變數完成遍歷
		Boy curBoy =first;
		while(true) {
			System.out.printf("小孩的編號 %d \n",curBoy.getNo());
			if(curBoy.getNext()==first) {//說明遍歷完畢
				break;
			}
			curBoy=curBoy.getNext(); //curBoy後移
		}
	}
	
}

//創建一個小孩類,表示一個節點
class Boy{
	private int no;
	private Boy next;//指向下一個節點,默認null
	public Boy(int no) {
		this.no=no;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public Boy getNext() {
		return next;
	}
	public void setNext(Boy next) {
		this.next = next;
	}
	
}