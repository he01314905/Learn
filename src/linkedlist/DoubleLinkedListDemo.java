package linkedlist;

public class DoubleLinkedListDemo {

	public static void main(String[] args) {
		System.out.println("雙向鏈表測試");
		HeroNode2 hero1 = new HeroNode2(1, "宋江", "及時雨");
		HeroNode2 hero2 = new HeroNode2(2, "虎松", "打老虎");
		HeroNode2 hero3 = new HeroNode2(3, "吳用", "智多星");
		HeroNode2 hero4 = new HeroNode2(4, "林沖", "豹子頭");
		//創建鏈表對象
		DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//		doubleLinkedList.add(hero1);
//		doubleLinkedList.add(hero2);
//		doubleLinkedList.add(hero3);
//		doubleLinkedList.add(hero4);
		doubleLinkedList.addByOrder(hero2);
		doubleLinkedList.addByOrder(hero1);
		doubleLinkedList.addByOrder(hero3);
		doubleLinkedList.addByOrder(hero4);
		doubleLinkedList.list();
//		HeroNode2 newHeroNode = new HeroNode2(2,"武松","打大象");
//		doubleLinkedList.update(newHeroNode);
//		System.out.println("修改後的鏈表狀況");
//		doubleLinkedList.list();
//
//		doubleLinkedList.del(3);
//		System.out.println("刪除後的鏈表");
//		doubleLinkedList.list();
	}

}
class DoubleLinkedList {
	private HeroNode2 head = new HeroNode2(0, "", "");

	public HeroNode2 getHead() {
		return head;
	}
	
	//排序練習
	public void addByOrder(HeroNode2 heroNode) {
		// head不能動,創一個輔助變量temp來幫助找添加的位置
		
		HeroNode2 temp = head;
		boolean flag = false; // 標示添加的編號是否存在,預設為false
		while (true) {
			if (temp.next == null ) { // 說明已經到最後的位置
				break;
			}
			if (temp.next.no > heroNode.no) { // 位置找到,就在temp的後面插入
				// temp的後一位排名>要添加的人物排名,所以要加到temp後一位的前一位
				break;
			} else if (temp.next.no == heroNode.no) {// 說明添加的排名已存在

				flag = true; // 說明排名存在
				break;
			}
			temp = temp.next; // 後移,遍歷當前鍊表
		}
		// 先判斷flag的值
		if (flag) {// 如果為true 不能添加 排名存在
			System.out.printf("準備加入的人物,排名 %d 已經存在,不能加入\n", heroNode.no);
		} else {
			// 加入到鍊表中,temp的後面
			heroNode.next=temp.next;
			if(temp.next!=null) {	
				temp.next.pre=heroNode;
			}
			heroNode.pre=temp;
			temp.next=heroNode;
		}
	}
	//添加到雙向鏈表最後
	public void add(HeroNode2 heroNode) {
		// 因為head節點不能動,因此需要一個輔助變量 temp
		HeroNode2 temp = head;
		// 遍歷找到最後
		while (true) {
			// 找到鍊表的最後
			if (temp.next == null) {
				break;
			}
			// 如果沒有找到最後,將temp後移
			temp = temp.next;
		}
		// 當退出while ,temp指向鍊表最後
		//形成一個雙向鏈表
		temp.next=heroNode;
		heroNode.pre=temp;
	}
	//修改一個節點內容,與單向鏈表幾乎一樣
	public void update(HeroNode2 newHeroNode) {
		if (head.next == null) {
			System.out.println("鍊表為空");
			return;
		}
		HeroNode2 temp = head.next;
		boolean flag = false;
		while (true) {
			if (temp == null) {
				break; // 鍊表已經遍歷結束
			}
			if (temp.no == newHeroNode.no) {
				// 找到
				flag = true;
				break;
			}
			temp = temp.next;
		}
		// 根據flag 找到 一樣的節點
		if (flag) {
			temp.name = newHeroNode.name;
			temp.nickname = newHeroNode.nickname;
		} else {
			System.out.printf("沒有找到 %d 節點 \n", newHeroNode.no);
		}
	}
	
	//從雙向鏈表刪除一個節點
	//對於雙向鏈表,可以直接找到要刪除的節點
	//找到後自我刪除
	public void del(int no) {
		
		//判斷當前鏈表是否為空
		if(head.next==null) {
			System.out.println("鏈表為空");
			return;
		}
		HeroNode2 temp = head.next;
		boolean flag = false; // 是否找到待刪除節點
		while (true) {
			if (temp == null) { // 已到最後節點
				break;
			}
			if (temp.no == no) { 
				// 找到待刪除節點
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if (flag) {
			temp.pre.next=temp.next;
			//如果是最後一個節點就不用執行下面這句,不然會出現空值異常
			if(temp.next!=null) {
			temp.next.pre=temp.pre;
			}
		} else {
			System.out.printf("沒有找到%d 節點\n", no);
		}
	}
	
	public void list() {
		// 先判斷是否為空
		if (head.next == null) {
			System.out.println("鍊表為空");
			return;
		}
		// 因為head不能動,因此需要一個輔助變量來遍歷
		HeroNode2 temp = head.next;
		while (true) {
			// 判斷是否到最後
			if (temp == null) {
				break;
			}
			// 不為空,輸出節點
			System.out.println(temp);
			// 將temp後移,不然會變死循環
			temp = temp.next;
		}
	}
}
	class HeroNode2 {
		public int no;
		public String name;
		public String nickname;
		public HeroNode2 next; //指向下一個節點
		public HeroNode2 pre; //指向前一個節點

		// 構造器 編號 名稱 暱稱
		public HeroNode2(int no, String name, String nickName) {
			this.no = no;
			this.name = name;
			this.nickname = nickName;
		}

	//為了顯示方便override
		@Override
		public String toString() {
			return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
		}
	}
	
	
