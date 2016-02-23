/* View the problem here: https://icpcarchive.ecs.baylor.edu/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=166

We can build up a set of bomb holes which touch the left edge, or those which
touch another bomb hole in this set, and do the same for the right edge.  Then,
we know that a vehicle must pass through the middle of these two regions.  We
then find the lowest diameter bomb needed to either block that hole, allowing it
to possibly contact one of the other sides instead of a bomb touching one of those
sides if that would result in an optimal solution.

This algorithm runs in n**3 time due to the loops which add vertices to the left
or right edge; both sets of the 3 nested loops can have n iterations for each loop.

As a rule of thumb, you can test on the order of 1,000,000 to 10,000,000 test cases
in the time provided (potentially less if each test case involves many operations).

By examining N * n**3, we find that this is sufficient for the bounds in this
problem, but it would not be difficult to adapt this method to N**2 time by using
an adjacency matrix to generate the sets.  I chose this solution because I
would be able to reliably code in the time period, rather than the solution with
the faster runtime, because both solutions would be accepted for the same credit.

*/


import java.io.*;
import java.util.*;
import static java.lang.Math.*;



public class BombingTheBridge {
		
	public static int[] vals;
	static int N;
	
	public static class Bomb {
		public int x, y, r;
		public Bomb(int a, int b, int c) {
			x = a;
			y = b;
			r = c;
		}
	}	

	public static void main(String[] args) throws IOException {
		
		Scanner in = new Scanner(System.in);
		
		N = in.nextInt();
		
		for (int tc = 1; tc <= N; tc++) {
			int w, l, n;
			w = in.nextInt();
			l = in.nextInt();
			n = in.nextInt(); // b in problem statement
			
			Bomb[] bombs = new Bomb[n];
			
			for (int d = 0; d < n; d++) {
				bombs[d] = new Bomb(in.nextInt(), in.nextInt(), in.nextInt());
			}
			
			// width is x value
			
			
			HashSet<Integer> leftbombinds = new HashSet<Integer>();
			
			while (true) {
				boolean found = false;
				for (int bind = 0; bind < n; bind++) {
					if (!leftbombinds.contains(bind)) {
						if (bombcollideany(bombs, leftbombinds, bind) || bombs[bind].x - bombs[bind].r <= 0) { // connected to left or off left
							leftbombinds.add(bind);
							found = true;
						}
					}
				}
				if (!found)
					break;
			}
			
			
			HashSet<Integer> rightbombinds = new HashSet<Integer>();
			
			while (true) {
				boolean found = false;
				for (int bind = 0; bind < n; bind++) {
					if (!rightbombinds.contains(bind)) {
						if (bombcollideany(bombs, rightbombinds, bind) || bombs[bind].x + bombs[bind].r >= w) {
							rightbombinds.add(bind);
							found = true;
						}
					}
				}
				if (!found)
					break;
			}
			
			
			// if there is overlap between the left bomb indeces and right bomb indeces this will be detected with a negative value for distance.
			
			
			int mindiam = w; // width of the bridge, using no groups (this happens in case 2)
			
			// check for not using any of the bombs in the left or right size, going straight to the edge from either group
						
			for (int lind: leftbombinds) {
				mindiam = Math.min(mindiam, w - (bombs[lind].r+bombs[lind].x));
			}
			
			for (int rind: rightbombinds) {
				mindiam = Math.min(mindiam, bombs[rind].x - bombs[rind].r);
			}
			
			
			for (int lind: leftbombinds) {
				for (int rind: rightbombinds) {
					int dist = (int) Math.ceil(Math.sqrt(Math.pow((bombs[lind].x - bombs[rind].x),2) + Math.pow((bombs[lind].y - bombs[rind].y),2))) - bombs[lind].r - bombs[rind].r; //EXACTMATH
					
					// is this a shorter distance to cover with the bomb?
							
					if (dist < mindiam) {
						mindiam = dist;
					}
				}
			}
			
			if (mindiam <= 0) {
				ptn("Bridge already split");
			} else {
				ptn(""+(int)Math.ceil(mindiam/2.0));
			}
		}
	}
	
	public static boolean bombcollideany(Bomb[] bombs, HashSet<Integer> indset, int ind) {
		// does bombs[ind] overlap with any of bombs[i] for i in indset?
		
		for (int eind: indset) {
			if (bomboverlap(bombs[eind], bombs[ind])) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean bomboverlap(Bomb bomb1, Bomb bomb2) {
		// allow to be touching by the point, >=
		return Math.pow((bomb1.r + bomb2.r), 2) >= Math.pow((bomb1.x - bomb2.x), 2) + Math.pow((bomb1.y - bomb2.y), 2);
	}
	
	// Printing functions
	
	public static int is(String s) {
		return Integer.parseInt(s);
	}
	
	public static int it(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}
	
	public static String nt(StringTokenizer st) {
		return st.nextToken();
	}
		
	public static void pt(String a) {
		System.out.print(a);
	}
	
	
	public static void ptn(String a) {
		System.out.println(a);
	}
	
}