package Hashing;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;


public class MyHashSet2<E>
{
	private class Node<E> { // chaning�� ��Ű�� ���Ḯ��Ʈ
		public E element;
		public Node next;
		//��
		public Node(){};
		public Node(E element, Node next){
			this.element = element;
			this.next = next;
		}
		public E getElement() {
			return element;
		}
		public void setElement(E element) {
			this.element = element;
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
		
	}	

	private Node[] table;
	private int capacity; // ���̺� ũ��
//	private Node[] table;
//	LinkedList<E> array = new LinkedList<E>();
	private float loadFactor; // load factor(a = n/m)
	private int size=0; // �ؽ� �� ����� ��(ũ��)
	public int size(){
		E entry;
//		if(add(entry))
		return size;
	}
	public MyHashSet2(int initCap, float load) {	//�ʱ� ���̺�ũ��, loadFactor ��	
		//��
		this.capacity = initCap;
		this.table = new Node[capacity];
		this.loadFactor = load;
		/*if(size > initCap*load){
			//���� �����ٽ� ���ο� �ؽ����̺� ����
			this.capacity *= 2;
			Node[] table = new Node[capacity];
			for(int i=0;i<capacity;i++){
				table2 = table;
			}
		}*/
	}
	private int hash(E entry) {	//E ���� hash���� index�� ��ȯ
		return (entry.hashCode() & 0x7fffffff) % capacity;
	}
	public int getHash(E entry){
		return (entry.hashCode() & 0x7fffffff) % capacity;
	}
	public E getEntry(E entry){
		return entry;
	}
	boolean isEmpty(){
		int i=0;
		for(Node x : table){
			if(x != null)
				i++;
		}
		if(i==0)
			return true;
		return false;
	}
	boolean contains(E entry){
		for(int i=0;i<capacity;i++){
			while(table[i] !=null){
				if(table[i].element == entry)
					return true;
				/*else if(table[i].next.element == entry)
					return true;*/
				else
					table[i] = table[i].next;
			}
		}
		return false;
	}
	void rearray(){
		Node[] oldTable = table;
		this.capacity *=2;
		table = new Node[capacity];
		for(int i=0;i<oldTable.length;i++){
			if(oldTable[i] != null && table[i].element != oldTable[i].element)
				table[i] = oldTable[i];
		}
	}
	/*private void resize() {
        int tableSize = 2 * table.length;
        maxSize = (int) (tableSize * threshold);
        HashEntry[] oldTable = table;
        table = new HashEntry[tableSize];
        size = 0;
        for (int i = 0; i < oldTable.length; i++)
              if (oldTable[i] != null && oldTable[i] != DeletedEntry.getUniqueDeletedEntry())
                    put(oldTable[i].getKey(), oldTable[i].getValue());
  }*/
	boolean add(E entry){
		E key = getEntry(entry);	//key�� �Ҵ�
		int index = hash(entry);	//�ε��� �� �ҷ���
		
		Node nd = new Node();
		nd.element = entry;
		if(this.size > capacity*loadFactor){
			//�迭ũ�� 2��Ȯ�� �� �ٽ� hashing
			rearray();
		}if(table[index]==null){
			table[index] = nd;
			size++;
			return true;
		}
		//���� ���� ���� ��� Linkedlist�� ������ �ϴµ� ���� �ִ� ���� next�� ������ ����
		else if(contains(entry)){
			table[index].next = nd;
			size++;
			return true;
		}
		return false;
	}
	/* public void add(E entry) {
         int index = hash(entry);
         int initialHash = -1;
         int indexOfDeletedEntry = -1;
         while (index != initialHash && (table[index] != null && table[index].element != entry)) {
               if (initialHash == -1)
                     initialHash = index;
               if (table[index] == null)
                     indexOfDeletedEntry = index;
               index = (index + 1) % table.length;
         }
         if ((table[index] == null || index == initialHash) && indexOfDeletedEntry != -1) {
               table[indexOfDeletedEntry] = new Node(entry, table[index].next);
               size++;
         } else if (initialHash != index)
               if (table[index] != null && table[index].element == entry)
                     table[index].setNext(table[index].next);
               else {
                     table[index] = new Node(entry, table[index].next);
                     size++;
               }
         if (size >= loadFactor*capacity)
               rearray();
   }*/
	/*boolean remove(E entry){
		int index = hash(entry);
		
		Node pre = table[index];
		Node now = pre.next;
		while(table[index] !=null && table[index].element != entry){
			if(now==null){
				break;
			}else if(now.element == entry){
				now = pre.next;
				pre.next = now.next;
			}else {
				pre = now;
				now = now.next;
			}
		}
//		size--;
		return false;
	}*/
	void remove(E entry) {
        int index = hash(entry);
       /* int init = -1;
        while (index != init && (table[index] != null && table[index].element != entry)) {
        	if (init == -1)
                init = index;
             index = (index + 1) % table.length;
        }*/
        if(contains(entry)){
        	System.out.println("�����Ϸ��� ���� �����ϴ�.");
        }else{
	        if (index != -1 && table[index] != null) {
	              table[index] = null;
	              size--;
	           
	        }
        }
        
  }
	void clear(){
		for(Node i : table){
//			System.out.println("��� ������ �����մϴ�.");
			i.element = null;
		}
//		System.out.println("��� ������ �����մϴ�.");
	}
	
	public static void main(String[] args) {
		MyHashSet2 set = new MyHashSet2<>(100000, 0.8f);
		System.out.println("�ؽ��Լ�");
		
		while(true){
			System.out.print("$ ");
			Scanner sc = new Scanner(System.in);
			String command = sc.nextLine();
			if(command.split(" ")[0].equals("add")){
				set.add(Integer.parseInt(command.split(" ")[1]));
			}else if(command.split(" ")[0].equals("search")){
				if(set.contains(Integer.parseInt(command.split(" ")[1])))
					System.out.println(set.getHash(Integer.parseInt(command.split(" ")[1])));
				else
					System.out.println("���� �������� �ʽ��ϴ�.");
			}else if(command.split(" ")[0].equals("remove")){
				if(set.contains(Integer.parseInt(command.split(" ")[1])))
					set.remove(Integer.parseInt(command.split(" ")[1]));	
				/*else
					System.out.println("�����Ϸ��� ���� ã�� �� �����ϴ�.");
				set.remove(Integer.parseInt(command.split(" ")[1]));	*/
			}else if(command.split(" ")[0].equals("clear")){
				System.out.println("����� ������ �����˴ϴ�.");
				set.clear();
			}else if(command.equals("size")){
				System.out.println(set.size());
			}else if(command.equals("break")){
				System.out.print("���α׷��� �����մϴ�.");
				break;
			}else
				System.out.println("�ùٸ� ��ɾ �Է��ϼ���.");
		}
	}
}