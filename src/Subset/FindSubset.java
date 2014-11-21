package Subset;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
public class FindSubset {
	private BitSet start;
	private BitSet end;
	private Set<Set<Object>> subsets;
	public FindSubset() {
		this.start = new BitSet();
		this.end = new BitSet();
		this.subsets = new HashSet<Set<Object>>();
	}
	public void execute(int n, Set<Object> items) {
		int size = items.size();
		for (int i = 0; i < n; i++) {
			start.set(i, true);
		}
		for (int i = n; i < size; i++) {
			start.set(i, false);
		}
		for (int i = size - 1; i > size - n; i--) {
			end.set(i, true);
		}
		for (int i = 0; i < size - n; i++) {
			end.set(i, false);
		}
		find(items);
		while (start != end) {
			boolean endBit = start.get(size - 1);
			int last10 = -1;
			int i;
			for (i = size - 1; i > 0; i--) {
				boolean former = start.get(i - 1);
				boolean last = start.get(i);
				if (former == true && last == false) {
					last10 = i - 1;
					break;
				}
			}
			if (i == 0) {
				break;
			} else {
				if (endBit == false) {
					start.set(last10, false);
					start.set(last10 + 1, true);
					find(items);
				} else {
					start.set(last10, false);
					start.set(last10 + 1, true);
					last10 += 1;
					for (int j = last10 + 1; j < size; j++) {
						if (start.get(j)) {
							start.set(j, false);
							start.set(last10 + 1, true);
							last10 += 1;
							find(items);
						}
					}
				}
			}
		}
	}
	
	public void find(Set<Object> items) {
		Set<Object> temp = new HashSet<Object>();
		Object elements[] = items.toArray();
		for (int i = 0; i < elements.length; i++) {
			if (start.get(i)) {
				temp.add(elements[i]);
			}
		}
		subsets.add(temp);
	}
	
	public Set<Set<Object>> getSubsets() {
		return this.subsets;
	}
	
	public void clearSubsets(){
		this.subsets.clear();
	}
	public static void main(String[] args) {
		System.out.println("Hello world£¡");
//		Set<Object> items = new HashSet<Object>();
//		items.add("A");
//		items.add("2");
//		items.add("3"); 
//
//		
//		FindSubset fs = new FindSubset();
//		
//		for (int i = 0; i < items.size(); i++) {
//			System.out.println((i + 1) + "Ôª×Ó¼¯£º");
//			fs.execute(i + 1, items);
//			Iterator<Set<Object>> iterator = fs.getSubsets().iterator();
//			while (iterator.hasNext()) {
//				Object[] subset = iterator.next().toArray();
//				System.out.print("{");
//				for (int j = 0; j < subset.length - 1; j++) {
//					System.out.print(subset[j] + ",");
//				}
//				System.out.print(subset[subset.length - 1]);
//				System.out.println("}");
//			}
//			
//			fs.clearSubsets();
//		}
		
	}
}
