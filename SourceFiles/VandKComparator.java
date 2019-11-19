import java.util.*;
//Modified version of the code provided on https://beginnersbook.com/2014/07/how-to-sort-a-treemap-by-value-in-java/
class VandKComparator {
	//Method for sorting the TreeMap based on values
	public class ValueThenKeyComparator<K extends Comparable<? super K>, V extends Comparable<? super V>> implements Comparator<Map.Entry<K, V>> {

		public int compare(Map.Entry<K, V> a, Map.Entry<K, V> b) {
			int cmp1 = a.getValue().compareTo(b.getValue());
			if (cmp1 != 0) 
			{
				return cmp1;
			} 
			else 
			{
				return a.getKey().compareTo(b.getKey());
			}
		}
	}
}
