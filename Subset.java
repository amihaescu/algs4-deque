/**
 * Created by andrei on 7/19/14.
 */
public class Subset {
    public static void main(String[] args){
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            rq.enqueue(item);
        }
        int k = Integer.parseInt(args[0]);
        if (( k > 0) && (k < rq.size())) {
            while (k <= rq.size()){
                System.out.println(rq.sample());
            }
        }
    }
}
