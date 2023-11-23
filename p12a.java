import java.util.HashMap; 
import java.util.HashSet; 
import java.util.Iterator; 

class p12a
{ 
    static int pageFaults(int pages[], int n, int capacity) 
    { 
        HashSet<Integer> s = new HashSet<>(capacity); 
        HashMap<Integer, Integer> indexes = new HashMap<>(); 

        int page_faults = 0; 
        for (int i=0; i<n; i++) 
        { 
            if (s.size() < capacity) 
            { 
                if (!s.contains(pages[i])) 
                { 
                    s.add(pages[i]); 
                    page_faults++; 
                } 
                indexes.put(pages[i], i); 
            } 
    
            else
            { 
                if (!s.contains(pages[i])) 
                { 
                    int lru = Integer.MAX_VALUE, val=Integer.MIN_VALUE; 
                    
                    Iterator<Integer> itr = s.iterator(); 
                    
                    while (itr.hasNext()) { 
                        int temp = itr.next(); 
                        if (indexes.get(temp) < lru) 
                        { 
                            lru = indexes.get(temp); 
                            val = temp; 
                        } 
                    } 
                    s.remove(val); 
                indexes.remove(val); 
                    s.add(pages[i]); 
                    page_faults++; 
                } 
                indexes.put(pages[i], i); 
            } 
        } 
    
        return page_faults; 
    } 
    public static void main(String args[]) 
    { 
        int pages[] = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2}; 
        
        int capacity = 3; 
        
        System.out.println(pageFaults(pages, pages.length, capacity)); 
    } 
} 