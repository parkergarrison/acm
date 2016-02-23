import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class palpaths {
	
	static Comparator comp0 = new Comparator<int[]>() {public int compare(int[] o1, int[] o2) {return o1[0] - o2[0];}};
	static Comparator compend = new Comparator<int[]>() {public int compare(int[] o1, int[] o2) {return o1[o1.length-1] - o2[o2.length-1];}};
	
	public static boolean debug = true;
	
	public static class Alist extends ArrayList<Integer> {}
	public static class Stree extends TreeSet<String> {}
	
	public static char[][] c;
	static int N;//, M;
	
	static HashSet<String> work = new HashSet<String>();
	static ArrayList<int[]> bots;
	
	/*public static String[][] tstrs;
	public static int[] tstri;
	public static String[][] bstrs;
	public static int[] bstri;
	
	public static void genTop(int x, int y, String s) {
		if (x+y == N-1) {
			//ptn(x+" "+y);
			//System.out.println(tstrs[x]);
			//ptn(""+tstri[x]);
			tstrs[x][tstri[x]++] = s+c[x][y];
		} else {
			genTop(x+1, y, s+c[x][y]);
			genTop(x, y+1, s+c[x][y]);
		}
	}
	
	public static void genBot(int x, int y, String s) {
		if (x+y == N-1) {
			bstrs[x][bstri[x]++] = s+c[x][y];
		} else {
			genBot(x-1, y, s+c[x][y]);
			genBot(x, y-1, s+c[x][y]);
		}
	}*/
	
	public static void go(String s, int a, int b) { // bfs/pruning
		
		if (a+b == N-1) { // got to the end
			work.add(s+c[a][b]);
			return;
		}
		
		ArrayList newbots = new ArrayList<int[]>();
		int i = 0;
		
		while (i < bots.size()) {
			if (c[a][b] != c[bots.get(i)[0]][bots.get(i)[1]]) {
				bots.remove(i); // prune off; don't do i++
			} else {
				newbots.add(new int[]{bots.get(i)[0]-1, bots.get(i)[1]});
				newbots.add(new int[]{bots.get(i)[0], bots.get(i)[1]-1});
				i++;
			}
		}
		
		if (bots.size() == 0) return; // dead end
		
		bots = newbots;
		//ptn(a+","+b);
		go(s+c[a][b], a+1, b);
		go(s+c[a][b], a, b+1);
	}
	public static int choose(int b, int a) {
		long prod = 1;
		
		//ptn("b a "+b+" "+a);
		for (int i = b-a+1 ; i <= b; i++) {
			prod *= i;
		}
		for (int i =1 ; i <= a; i++) {
			prod /= i;
		}
		
		return (int) prod;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new FileReader("palpath.in"));
		StringTokenizer st;
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palpath.out")));
		
		st = new StringTokenizer(in.readLine());
		
		N = it(st);
		bots = new ArrayList<int[]>();
		bots.add(new int[]{N-1, N-1});
		
		c = new char[N][N];
		
		for (int d = 0; d < N; d++) {
			st = new StringTokenizer(in.readLine());
			/*
			for (int j = 0; j < vals[d].length; j++) { // can replace length
				vals[d][j] = it(st);
			}*/
			String s = nt(st);
			int j = 0;
			
			//c[d]=new char[N];
			
			for (char ch: s.toCharArray()) {
				c[d][j++] = ch;
			}
			//vals[d] = it(st);
		}
		
		go("", 0, 0);
		
		//for (String s: work) ptn(s);
		
		int ans = work.size();
		
		
		pt(ans);
		out.println(ans);
		out.close();
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
	
	public static void ptd(String a) {
		if (debug) System.out.print(a);
	}
	
	public static void ptn(String a) {
		System.out.println(a);
	}
	
	public static void ptdn(String a) {
		if (debug) System.out.println(a);
	}
	
	public static void ptn() {
		System.out.println();
	}
	
	public static void pt(int a) {
		System.out.print(a);
	}
	
	public static void ptn(int a) {
		System.out.println(a);
	}
	
	public static void ptd(int a) {
		if (debug) System.out.print(a);
	}
	
	public static void ptdn(int a) {
		if (debug) System.out.println(a);
	}
	
	public static void pt(double a) {
		System.out.print(a);
	}
	
	public static void ptn(double a) {
		System.out.println(a);
	}
	
	public static void ptd(double a) {
		if (debug) System.out.print(a);
	}
	
	public static void ptdn(double a) {
		if (debug) System.out.println(a);
	}
	
	// Printing functions
	
	public static void ptd(int[] a) {
		if (debug) pt(a);
	}
	
	public static void pt(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]+" ");
		}
		System.out.println();
	}
	
	public static void ptd(int[][] a) {
		if (debug) pt(a);
	}
	
	public static void pt(int[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public static void ptd(long[] a) {
		if (debug) pt(a);
	}
	
	public static void pt(long[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]+" ");
		}
		System.out.println();
	}
		
	public static void ptd(long[][] a) {
		if (debug) pt(a);
	}
	
	public static void pt(long[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public static void ptd(double[] a) {
		if (debug) pt(a);
	}
	
	public static void pt(double[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]+" ");
		}
		System.out.println();
	}
	
	public static void ptd(double[][] a) {
		if (debug) pt(a);
	}
	
	public static void pt(double[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public static void pt(Object o) {
		System.out.println(o);
	}
	
	public static void ptd(Object o) {
		if (debug) pt(o);
	}
	
	public static void pt(Object[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]+" ");
		}
	}
	
	public static void ptd(Object[] a) {
		if (debug) pt(a);
	}
	
	public static int comp(int a, int b) {
		if (a > b) return 1;
		if (a < b) return -1;
		else return 0;
	}
	
  	public static int[] cpy(int[] x) {
  		int[] ans = new int[x.length];
  		for (int i = 0; i < x.length; i++) {
  			ans[i] = x[i];
  		}
  		
  		return ans;
  	}
  
  	public static boolean ie(char[][] a1, char[][] a2) {
  		boolean w = true;
  		
  		for (int i = 0; i < a1.length; i++) {
  			for (int j = 0; j < a1[0].length; j++) {
  				if (a1[i][j] != a2[i][j])
  					w = false;
  			}
  		}
  		
  		return w;
  	}
  	
  	public static int[] removeDups(int[] a) {
  		Set<Integer> tmp = new LinkedHashSet<Integer>();
  		
  		for (int each: a) // add to set
  			tmp.add(each);
  		
  		int[] ans = new int[tmp.size()];
  		
  		int i = 0;
  		for (int each: tmp)
  			ans[i++] = each; // extract from set
  		
  		return ans;
  	}
  	
}