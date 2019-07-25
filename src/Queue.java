package sample;

/**
 * Created by Ondřej Horyna on 2019-06-12.
 */
public class Queue {

    /**
     * Class variables
     */
    private int[] f;
    private int start, end;
    private final int n;

    /**
     * Class constructor
     * @param MAX
     */
    public Queue(int MAX) {

        this.n = MAX + 1;
        this.f = new int[n];
        this.start = 0;
        this.end = 0;

    }

    /**
     * This method check that the queue is empty
     * @return
     */
    public boolean is_empty() {

        return (this.start == this.end);

    }

    /**
     * This method insert a number into the queue
     * @param key
     */
    public void insert(int key) {

        this.f[this.end++] = key;
        this.end = this.end % this.n;

    }

    /**
     * This method return the number on the beginning from queue
     * @return int
     */
    public int select() {

        int v = this.f[this.start++];
        this.start = this.start % this.n;
        return v;

    }

}
