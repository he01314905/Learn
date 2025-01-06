package linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {

	public static void main(String[] args) {
		// test
		// 先創建節點
		HeroNode hero1 = new HeroNode(1, "宋江", "及時雨");
		HeroNode hero2 = new HeroNode(2, "虎松", "打老虎");
		HeroNode hero3 = new HeroNode(3, "吳用", "智多星");
		HeroNode hero4 = new HeroNode(4, "林沖", "豹子頭");

		// 創建一個鍊表
		SingleLinkedList singleLinkedList = new SingleLinkedList();
		System.out.println("原鏈表情況~~~~~~~");

		// 加入
//	singleLinkedList.add(hero1);
//	singleLinkedList.add(hero4);
//	singleLinkedList.add(hero2);
//	singleLinkedList.add(hero3);
//輸出為1423,加入的順序為順序

		// 按照編號順序加入
		singleLinkedList.addByOrder(hero1);
		singleLinkedList.addByOrder(hero4);
		singleLinkedList.addByOrder(hero2);
		singleLinkedList.addByOrder(hero3);
		// 顯示
		singleLinkedList.list();

//	System.out.println("反轉後的鏈表~~~");
//	reversetList(singleLinkedList.getHead());
//	singleLinkedList.list();

		System.out.println("反向打印~~~");
		reversePrint(singleLinkedList.getHead());
//	
//	System.out.println("修改後如下");
//	HeroNode newHeroNode = new HeroNode(2,"武松","扁大象");
//	singleLinkedList.update(newHeroNode);
//	//修改後顯示
//	singleLinkedList.list();
//	System.out.println("-------刪除後的順序------");
//	singleLinkedList.del(3);
//	singleLinkedList.list();
//	
//	System.out.println("有效節點個數:"+getLength(singleLinkedList.getHead()));
//	HeroNode res = findIndex(singleLinkedList.getHead(), 1);
//	System.out.println("res="+res);
	}

//方式2 可以利用stack,將各節點放進stack中,利用stack的先進後出,來實現反向打印
	public static void reversePrint(HeroNode head) {
		if (head.next == null) {
			return; // 空鏈表 不能打印
		}
		// 建立一個stack
		Stack<HeroNode> stack = new Stack<HeroNode>();
		HeroNode temp = head.next;
		// 將鍊表的所有節點,押入stack中
		while (temp != null) {
			stack.push(temp);
			temp = temp.next; // 讓temp後移
		}
		// 將stack中的節點打印
		while (stack.size() > 0) {
			System.out.println(stack.pop());
		}
	}

//單鏈表反轉
	public static void reversetList(HeroNode head) {
		// 如果當前鏈表為空,或只有一個節點,無須反轉,直接返回
		if (head.next == null || head.next.next == null) {
			return;
		}
		// 要有一個輔助指針(變數),幫我們遍歷元鏈表
		HeroNode temp = head.next;
		HeroNode next = null; // 指向當前節點[temp]的下個節點
		HeroNode reverseHead = new HeroNode(0, "", "");
		// 遍歷原鏈表,每遍歷一個節點將她取出,並放在reverseHead鏈表最前端
		while (temp != null) {
			next = temp.next;// 暫時保存當前節點的下個節點
			temp.next = reverseHead.next; // 將 temp的下一個節點指向新鍊表的頭節點
			reverseHead.next = temp; // 將 temp 連接到新鏈表上
			temp = next;
		}
		// 將head.next 指向 reverseHead.next ,實現鏈表反轉
		head.next = reverseHead.next;
	}

//查找鍊表的倒數第K個節點
//寫一個方法接收head節點,同時接收一個index
//index 表示倒數第index個節點
//先遍歷鍊表,獲得總長 getLength
//得到size後,從鍊表第一個開始便利(size-index)個,就可以得到要的位置,找不到就返回null
	public static HeroNode findIndex(HeroNode head, int index) {
		// 判斷是否為空
		if (head.next == null) {
			return null;
		}
		int size = getLength(head);
		if (index <= 0 || index > size) {
			return null;
		}
		HeroNode temp = head.next;
		for (int i = 0; i < size - index; i++) {
			temp = temp.next;
		}
		return temp;
	}

	public static int getLength(HeroNode head) {
		if (head.next == null) {
			return 0;
		}
		int length = 0;
		// 定義一個輔助變數
		HeroNode cur = head.next;
		while (cur != null) {
			length++;
			cur = cur.next;
		}
		return length;
	}

}

//定義SingleLinkedList 管理人物
class SingleLinkedList {
	// 初始化頭節點,頭節點不要動
	private HeroNode head = new HeroNode(0, "", "");

	public HeroNode getHead() {
		return head;
	}

	// 添加節點加入鍊表
	// 當不考慮編號順序時
	// 1.找到當前鍊表最後節點
	// 2.將最後節點的next 指向新的節點
	public void add(HeroNode heroNode) {

		// 因為head節點不能動,因此需要一個輔助變量 temp
		HeroNode temp = head;
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
		// 將最後節點的next 指向 新的節點
		temp.next = heroNode;
	}

	// 第二種 在添加人物時 根據排名插入位置,如果有相同排名添加失敗,列印提示
	public void addByOrder(HeroNode heroNode) {
		// head不能動,創一個輔助變量temp來幫助找添加的位置
		// 因為單鍊表,需要找到temp的 位於添加位置的前一個節點,否則+不進去
		HeroNode temp = head;
		boolean flag = false; // 標示添加的編號是否存在,預設為false
		while (true) {
			if (temp.next == null) { // 說明已經到最後的位置
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
			heroNode.next = temp.next;
			temp.next = heroNode;

		}
	}
	// 修改節點,根據no,no不可修改

	public void update(HeroNode newHeroNode) {
		if (head.next == null) {
			System.out.println("鍊表為空");
			return;
		}
		HeroNode temp = head.next;
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

	// 刪除節點
	// 1. head不能動,需要一個輔助變數temp,找到待刪除節點的前一個 temp.next=temp.next.next 即可
	public void del(int no) {
		HeroNode temp = head;
		boolean flag = false; // 是否找到待刪除節點
		while (true) {
			if (temp.next == null) { // 已到最後節點
				break;
			}
			if (temp.next.no == no) {
				// 找到待刪除節點
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if (flag) {
			temp.next = temp.next.next;
		} else {
			System.out.printf("沒有找到%d 節點\n", no);
		}
	}

	// 獲取單鍊表的的節點個數(不統計頭節點)

	// 顯示鍊表
	public void list() {
		// 先判斷是否為空
		if (head.next == null) {
			System.out.println("鍊表為空");
			return;
		}
		// 因為head不能動,因此需要一個輔助變量來遍歷
		HeroNode temp = head.next;
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

//定義HeroNode , 每個HeroNode 對象就是一個節點
class HeroNode {

	public int no;
	public String name;
	public String nickname;
	public HeroNode next;

	// 構造器 編號 名稱 暱稱
	public HeroNode(int no, String name, String nickName) {
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