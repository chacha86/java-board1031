package javaboard1031;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Test2 {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		ArrayList<Num> nlist = new ArrayList<Num>();

		System.out.println("========== 일반 정렬 =========");
		
		list.add(4);
		list.add(1);
		list.add(2);
		list.add(5);
		list.add(3);

		// 오름차순
		System.out.println("========== 일반 정렬 - 오름차순 =========");
		Collections.sort(list);
		
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		System.out.println("========== 일반 정렬 - 내림차순 =========");
		Collections.sort(list, Collections.reverseOrder());
		
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		
		System.out.println("========== 객체 정렬 =========");
		nlist.add(new Num(4, 5));
		nlist.add(new Num(1, 1));
		nlist.add(new Num(2, 4));
		nlist.add(new Num(5, 3));
		nlist.add(new Num(3, 2));

		System.out.println("========== 객체 정렬 n1 기준 - 오름차순 =========");
		MyComp comp = new MyComp();
		Collections.sort(nlist, comp); // 
		
		
		for (int i = 0; i < nlist.size(); i++) {
			System.out.println(nlist.get(i).n1 + ", " + nlist.get(i).n2);
		}
		
		System.out.println("========== 객체 정렬 n2 기준 - 오름차순 =========");
		for (int i = 0; i < nlist.size(); i++) {
			System.out.println(nlist.get(i).n1 + ", " + nlist.get(i).n2);
		}
		
		System.out.println("========== 객체 정렬 n1 기준 - 내림차순 =========");
		for (int i = 0; i < nlist.size(); i++) {
			System.out.println(nlist.get(i).n1 + ", " + nlist.get(i).n2);
		}
		
		System.out.println("========== 객체 정렬 n2 기준 - 내림차순 =========");
		for (int i = 0; i < nlist.size(); i++) {
			System.out.println(nlist.get(i).n1 + ", " + nlist.get(i).n2);
		}
	}

}

class Num {

	int n1;
	int n2;

	Num(int a, int b) {
		n1 = a;
		n2 = b;
	}

}

