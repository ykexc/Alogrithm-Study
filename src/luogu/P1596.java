package luogu;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class P1596 {
    static int N, M;
    static boolean[][] used = new boolean[105][105];
    static char[][] map = new char[105][105];
    static int[] dx = new int[]{0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static Deque<Point> deque = new ArrayDeque<>();
    static int ans = 0;

    public static void main(String args[]) throws IOException {
        Read sc = new Read();

        N = sc.nextInt();
        M = sc.nextInt();
        for (int i = 1; i <= N; i++) {
            String s = sc.next();
            char[] chars = s.toCharArray();
            System.arraycopy(chars, 0, map[i], 1, M);
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (map[i][j] == 'W' && !used[i][j]) {
                    used[i][j] = true;
                    deque.addLast(new Point(i, j));
                    ans++;
                    while (!deque.isEmpty()) {
                        Point temp = deque.pollFirst();
                        for (int k = 0; k < dx.length; k++) {
                            int tx = temp.x + dx[k];
                            int ty = temp.y + dy[k];
                            if (map[tx][ty] == 'W' && !used[tx][ty]) {
                                used[tx][ty] = true;
                                deque.addLast(new Point(tx, ty));
                            }
                        }
                    }
                }
            }
        }


        sc.print(ans);
        sc.bw.flush();
        sc.bw.close();
    }
}

class Read {
    BufferedReader bf;
    StringTokenizer st;
    BufferedWriter bw;

    public Read() {
        bf = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer("");
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    public String nextLine() throws IOException {
        return bf.readLine();
    }

    public String next() throws IOException {
        while (!st.hasMoreTokens()) {
            st = new StringTokenizer(bf.readLine());
        }
        return st.nextToken();
    }

    public char nextChar() throws IOException {
        return next().charAt(0);
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    public long nextLong() throws IOException {
        return Long.parseLong(next());
    }

    public double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }

    public float nextFloat() throws IOException {
        return Float.parseFloat(next());
    }

    public byte nextByte() throws IOException {
        return Byte.parseByte(next());
    }

    public short nextShort() throws IOException {
        return Short.parseShort(next());
    }

    public BigInteger nextBigInteger() throws IOException {
        return new BigInteger(next());
    }

    public void println(int a) throws IOException {
        bw.write(String.valueOf(a));
        bw.newLine();
        return;
    }

    public void print(int a) throws IOException {
        bw.write(String.valueOf(a));
        return;
    }

    public void println(String a) throws IOException {
        bw.write(a);
        bw.newLine();
        return;
    }

    public void print(String a) throws IOException {
        bw.write(a);
        return;
    }

    public void println(long a) throws IOException {
        bw.write(String.valueOf(a));
        bw.newLine();
        return;
    }

    public void print(long a) throws IOException {
        bw.write(String.valueOf(a));
        return;
    }

    public void println(double a) throws IOException {
        bw.write(String.valueOf(a));
        bw.newLine();
        return;
    }

    public void print(double a) throws IOException {
        bw.write(String.valueOf(a));
        return;
    }
}
