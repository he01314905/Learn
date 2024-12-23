package linkedlist;

public class SingleLinkedListDemo {
public static void main(String[] args) {
	//test
	//先創建節點
	HeroNode hero1=new HeroNode(1, "宋江", "及時雨");
	HeroNode hero2=new HeroNode(2, "虎松", "打老虎");
	HeroNode hero3=new HeroNode(3, "吳用", "智多星");
	HeroNode hero4=new HeroNode(4, "林沖", "豹子頭");
	
	//創建一個鍊表
	SingleLinkedList singleLinkedList = new SingleLinkedList();
	//加入
//	singleLinkedList.add(hero1);
//	singleLinkedList.add(hero4);
//	singleLinkedList.add(hero2);
//	singleLinkedList.add(hero3);
//輸出為1423,加入的順序為順序
	
	//按照編號順序加入
	singleLinkedList.addByOrder(hero1);
	singleLinkedList.addByOrder(hero4);
	singleLinkedList.addByOrder(hero2);
	singleLinkedList.addByOrder(hero3);
	
	//輸出為1234
	
	
	//顯示
	singleLinkedList.list();
}
}

//定義SingleLinkedList 管理人物
class SingleLinkedList{
	//初始化頭節點,頭節點不要動
	private HeroNode head =new HeroNode(0,"","");
	
	//添加節點加入鍊表
	//當不考慮編號順序時
	//1.找到當前鍊表最後節點
	//2.將最後節點的next 指向新的節點
	public void add(HeroNode heroNode) {
		
		//因為head節點不能動,因此需要一個輔助變量 temp
		HeroNode temp = head;
		//遍歷找到最後
		while(true) {
			//找到鍊表的最後
			if(temp.next==null) {
				break;
			}
			//如果沒有找到最後,將temp後移
			temp=temp.next;
		}
		//當退出while ,temp指向鍊表最後
		//將最後節點的next 指向 新的節點
		temp.next=heroNode;
	}
	
	//第二種 在添加人物時 根據排名插入位置,如果有相同排名添加失敗,列印提示
	public void addByOrder(HeroNode heroNode) {
		//head不能動,創一個輔助變量temp來幫助找添加的位置
		//因為單鍊表,需要找到temp的 位於添加位置的前一個節點,否則+不進去
		HeroNode temp =head;
		boolean flag = false; //標示添加的編號是否存在,預設為false
		while(true) {
			if(temp.next==null) { //說明已經到最後的位置
				break; 
			}
			if(temp.next.no > heroNode.no) { //位置找到,就在temp的後面插入
				//temp的後一位排名>要添加的人物排名,所以要加到temp後一位的前一位
				break;
			}else if(temp.next.no==heroNode.no) {//說明添加的排名已存在
				
				flag=true; //說明排名存在
				break;
			}
			temp=temp.next; //後移,遍歷當前鍊表
		}
		//先判斷flag的值
		if(flag) {//如果為true 不能添加 排名存在
			System.out.printf("準備加入的人物,排名 %d 已經存在,不能加入\n",heroNode.no);
		}else {
			//加入到鍊表中,temp的後面
			heroNode.next=temp.next;
			temp.next=heroNode;
			
		}
	}
	
	
	
	
	
	//顯示鍊表
	public void list(){
		//先判斷是否為空
		if(head.next==null) {
			System.out.println("鍊表為空");
			return;
		}
		//因為head不能動,因此需要一個輔助變量來遍歷
		HeroNode temp = head.next;
		while(true) {
			//判斷是否到最後
			if(temp==null) {
				break;
			}
			//不為空,輸出節點
			System.out.println(temp);
			//將temp後移,不然會變死循環
			temp=temp.next;
			}
	}
}


//定義HeroNode , 每個HeroNode 對象就是一個節點
class HeroNode{
	
	public int no;
	public String name;
	public String nickname;
	public HeroNode next;
	
	//構造器  編號  名稱 暱稱
	public HeroNode(int no,String name, String nickName) {
		this.no=no;
		this.name=name;
		this.nickname=nickName;
	}
//為了顯示方便override
	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname +  "]";
	}

	
	
	
}