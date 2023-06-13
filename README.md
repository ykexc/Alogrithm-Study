# 数据结构与算法

### 二分技巧

>`mid取法`

**mid = l + (r - l) / 2 或 l + r >> 1 配合 l 或 r = mid + 1使用**

**mid = r - (r - l) / 2 或 l + r + 1 >> 1 配合 l 或 r = mid - 1使用**

>`边界处理（取 l = 0, r = n - 1 的情况下）`

```java
若数组符合 [... Y targetL target targetR   X ...   ]//数组升序，target均相等,if (check()) 根据题意来写
1.此时如果要得到target的右边X的坐标则用 mid = l + (r - l) / 2 
2.与1相对的 求Y使用 mid = r - (r - l) / 2
3.求targetL使用mid = l + (r - l) / 2 
4.求targetR使用mid = r - (r - l) / 2
5.若target只有一个随便用
```

```java
int n, k;
    int[] arr;
    void solve() throws IOException {
        n = in.nextInt();
        k = in.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = in.nextInt();
        for (int i = 0; i < k; i++) {
            int t = in.nextInt();
            out.println(binarySearchL(t) + " " + binarySearchR(t));
        }
    }

    int binarySearchL(int k) {
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] < k) l = mid + 1;
            else r = mid;
        }
        return arr[l] == k ? l : -1;
    }

    int binarySearchR(int k) {
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = r - (r - l) / 2;
            if (arr[mid] <= k) l = mid;
            else r = mid - 1;
        }
        return arr[l] == k ? l : -1;
    }
```

#### 浮点数二分

```java
double x;
    void solve() throws IOException {
        x = in.nextDouble();
        double e = 1e-8;//低于精度2位
        double l = -100, r = 100;
        while (r - l > e) {
            double mid = (l + r) / 2;
            if (check(mid)) r = mid;//将等于当作大于
            else l = mid;
        }
        out.printf("%.6f", l);
    }
```



### 回溯

<img src="https://ykexc-1314584214.cos.ap-nanjing.myqcloud.com/image-20220721211914942.png" alt="image-20220721211914942" style="zoom:80%;" />

### 双向BFS

```java
d1、d2 为两个方向的队列
m1、m2 为两个方向的哈希表，记录每个节点距离起点的
    
// 只有两个队列都不空，才有必要继续往下搜索
// 如果其中一个队列空了，说明从某个方向搜到底都搜不到该方向的目标节点
while(!d1.isEmpty() && !d2.isEmpty()) {
    if (d1.size() < d2.size()) {
        update(d1, m1, m2);
    } else {
        update(d2, m2, m1);
    }
}

// update 为将当前队列 d 中包含的元素取出，进行「一次完整扩展」的逻辑（按层拓展）
void update(Deque d, Map cur, Map other) {}
```



### 并查集

- 一维

  ```java
  class UnionFind{
      int[]arr;
      int[]rank;
      public UnionFind(int n){
          arr = new int[n];
          rank = new int[n];
          for (int i = 0; i < n; i++) {
              arr[i] = i;
              rank[i] = 1;
          }
      }
      public int find(int n){
          if (n == arr[n]){
              return n;
          }
          return arr[n] = find(arr[n]);
      }
      public void union(int x , int y){
          int xx = find(arr[x]);
          int yy = find(arr[y]);
          if (xx != yy){
              if (rank[xx] < rank[yy]){
                  arr[xx] = yy;
              }else if (rank[xx] > rank[yy]){
                  arr[yy] = xx;
              }else {
                  arr[xx] = yy;
                  rank[yy] ++;
              }
          }
      }
  }
  
  ```

  

- 二维

  ```java
  class UnionFind {
      int m, n;
      int[][][] root;
      int[][] size;
  
      public UnionFind(int m, int n) {
          this.m = m;
          this.n = n;
          this.root = new int[m][n][2];
          this.size = new int[m][n];
          for (int i = 0; i < m; i++) {
              for (int j = 0; j < n; j++) {
                  root[i][j][0] = i;
                  root[i][j][1] = j;
                  size[i][j] = 1;
              }
          }
      }
  
      public int[] find(int i, int j) {
          int[] rootArr = root[i][j];
          int ri = rootArr[0], rj = rootArr[1];
          if (ri == i && rj == j) {
              return rootArr;
          }
          return find(ri, rj);
      }
  
      public void union(int i1, int j1, int i2, int j2) {
          int[] rootArr1 = find(i1, j1);
          int[] rootArr2 = find(i2, j2);
          int ri1 = rootArr1[0], rj1 = rootArr1[1];
          int ri2 = rootArr2[0], rj2 = rootArr2[1];
          if (ri1 != ri2 || rj1 != rj2) {
              if (size[ri1][rj1] >= size[ri2][rj2]) {
                  root[ri2][rj2][0] = ri1;
                  root[ri2][rj2][1] = rj1;
                  size[ri1][rj1] += size[ri2][rj2];
              } else {
                  root[ri1][rj1][0] = ri2;
                  root[ri1][rj1][1] = rj2;
                  size[ri2][rj2] += size[ri1][rj1];
              }
          }
      }
  }
  
  ```

### GCD

~~~java
public int gcd(int a , int b){
        return a == 0 ? b : gcd(b % a , a);
    }
~~~

### EXGCD

>可以解决线性同余问题

```java
int exGcd(int a, int b, int[] x, int[] y) {
        if (b == 0) {
            x[0] = 1;
            y[0] = 0;
            return a;
        } else {
            int d = exGcd(b, Math.floorMod(a, b), y, x);
            y[0] -= a / b * x[0];
            return d;
        }
    }
```



### 线性筛

>如果一个数的质因数的数量确定了,那么这个数也就确定了

```java
static int N = 1000010;
    static int cnt;
    static int[] pr = new int[N];
    static boolean[] st = new boolean[N];

    public static void find(int n) {
        for (int i = 2; i <= n; i++) {
            if (!st[i]) pr[cnt++] = i;//将没有被标记的点加入到pr数组中去，也就是质数
            for (int j = 0; pr[j] <= n / i; j++) {//从小到大遍历质数
                st[pr[j] * i] = true; //每一次让pr[j]*i标记
                if (i % pr[j] == 0) break;
                //只要pi % pr[j] == 0说明他是pr[j]*i的最小质因数了,然后结束循环，
                //如果不break循环的话就会进行pr[j+1]*i晒掉，因为pr[j+1]*i的最小质因数
                //不是pr[j+1],所以会导致重复删除，这也是线性筛的优点所在
                //例如：st[2*4=8]标记之后，如果你不在下面判断if(4%2==0)break掉
                //就会继续pr[(j+1)]*i== st[3*4=12],3不是12的最小质因数，所以这样也是
                //会导致重复删除，执行到i=6时候，st[2*6=12]会再次标记，这样也就在此导致重复了

                //i % pr[j] != 0 说明pr[j]永远是pr[j]*i的最小质因数
                //因为pr[j]的最小质因数是本身pr[j]，然后pr[j]*i是pr[j]的倍数
                //所以pr[j]*i的最小质因数也是pr[j]，永远都是，pr[j+1]*i的时候也是
            }
        }
    }
```

### 欧拉函数

>$\phi(N)=N(1-1/p_1)(1-1/p_2)...   $$p1,p2...\text为N的质因子$

```go
func phi(a int) int {
	res := a
	for i := 2; i <= a/i; i++ {
		if a%i == 0 {
			res = res / i * (i - 1)
			for a%i == 0 {
				a /= i
			}
		}
	}
	if a != 1 {
		res = res / a * (a - 1)
	}
	return res
}
```

```java
//线性筛求欧拉函数
long getEulers(int n) {
    euler[1] = 1;
    for (int i = 2; i <= n; i++) {
        if (!vis[i]) {
            primes[cnt++] = i;
            euler[i] = i - 1;
        }
        for (int j = 0; primes[j] <= n / i; j++) {
            int t = i * primes[j];
            vis[t] = true;
            if (i % primes[j] == 0) {
                euler[t] = euler[i] * primes[j];
                break;
            }
            euler[t] = euler[i] * (primes[j] - 1);
        }
    }
    long res = 0;
    for (int i = 1; i <= n; i++) {
        res += euler[i];
    }
    return res;
}
```



### 卡特兰数

>$C_{n+1} = 2C_n(2n + 1)\ /(n + 2)$
>
>$h_n = C_{2n}^n - C_{2n}^{n-1} = C_{2n}^n/(n+1)$

```java
public int catalan(int n) {
        //用 long 类型防止计算过程中的溢出
        long C = 1;
        for (int i = 0; i < n; ++i) {
            C = C * 2 * (2L * i + 1) / (i + 2);
        }
        return (int) C;
    }
```

### 快速幂

```java
long qmi(long a, long k, long m) {
        long ans = 1;
        while (k != 0) {
            if ((k & 1) == 1) {
                ans = ans * a % m;
            }
            k >>= 1;
            a = a * a % m;
        }
        return ans;
    }
```



### 快速乘

```java
long mul(long a, long k) {
        long ans = 0;
        while (k > 0) {
            if ((k & 1) == 1) ans += a;
            k >>= 1;
            a += a;
        }
        return ans;
    }
```



### 快速选择

```java
int qSelect(int[] nums, int l, int r, int k) {
        if (l == r) return nums[k];
        int x = nums[l + r >> 1], i = l - 1, j = r + 1;
        while (i < j) {
            do i++; while (nums[i] < x);
            do j--; while (nums[j] > x);
            if (i < j) {
                nums[i] ^= nums[j];
                nums[j] ^= nums[i];
                nums[i] ^= nums[j];
            }
        }
        if (k <= j) return qSelect(nums, l, j, k);
        else return qSelect(nums, j + 1, r, k);
    }
```





### 二进制枚举

```java
6173
class Solution {
    public int maximumRows(int[][] mat, int cols) {
        int n = mat.length, m = mat[0].length;
        int ans = 0;
        int[] rowState = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1) rowState[i] |= 1 << j;
            }
        }
        for (int i = 0; i < 1 << m; i++) {
            int cnt = 0;
            for (int j = 0; j < m; j++) {
                cnt += i >> j & 1;
            }
            if (cnt != cols) continue;
            int t = 0;
            for (int j = 0; j < n; j++) {
                if ((rowState[j] & i) == rowState[j]) t++;
            }
            ans = Math.max(ans, t);
        }
        return ans;
    }
}
```

### 多路归并

```java
//丑数
class Solution {
    public int getUglyNumber(int n) {
        int[] ans = new int[n + 1];
        ans[1] = 1;
        int i2 = 1, i3 = 1, i5 = 1;
        for (int i = 2; i <= n; i++) {
            int a = ans[i2] * 2, b = ans[i3] * 3, c = ans[i5] * 5;
            int min = Math.min(a, Math.min(b, c));
            if (min == a) i2++;
            if (min == b) i3++;
            if (min == c) i5++;
            ans[i] = min;
        }
        return ans[n];
    }
}

//LC373(如果多行的话,先两两合并)
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int n = nums1.length, m = nums2.length;
        List<List<Integer>> ans = new ArrayList<>();
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> (nums1[a[0]] + nums2[a[1]])));
        for (int i = 0; i < Math.min(n, k); i++) q.add(new int[]{i, 0});
        while (ans.size() < k && !q.isEmpty()) {
            int[] poll = q.poll();
            int a = poll[0], b = poll[1];
            ans.add(new ArrayList<>(){{
                add(nums1[a]);
                add(nums2[b]);
            }});
            if (b + 1 < m) q.add(new int[]{a, b + 1});
        }
        return ans;
    }
}
//LC1439，ACW146
```

### Trie

```java
class Trie {
    static class TrieNode {
        boolean isEnd;
        TrieNode[] trieNodes = new TrieNode[26];
    }

    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode temp = this.root;
        for (int i = 0; i < word.length(); i++) {
            if (temp.trieNodes[word.charAt(i) - 'a'] == null) {
                temp.trieNodes[word.charAt(i) - 'a'] = new TrieNode();
            }
            temp = temp.trieNodes[word.charAt(i) - 'a'];
        }
        temp.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode temp = this.root;
        for (int i = 0; i < word.length(); i++) {
            if (temp.trieNodes[word.charAt(i) - 'a'] == null) {
                return false;
            }
            temp = temp.trieNodes[word.charAt(i) - 'a'];
        }
        return temp.isEnd;
    }

    public boolean startsWith(String prefix) {
        TrieNode temp = this.root;
        for (int i = 0; i < prefix.length(); i++) {
            if (temp.trieNodes[prefix.charAt(i) - 'a'] == null) {
                return false;
            }
            temp = temp.trieNodes[prefix.charAt(i) - 'a'];
        }
        return true;
    }
}

```

### 01背包

```java
import java.util.*;
public class Main{
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();
        int v = sc.nextInt();
        int []dp = new int[v + 1];
        int []m = new int[c];
        int []vv = new int[c];
        for(int i = 0;i < c;i++){
            m[i] = sc.nextInt();
            vv[i] = sc.nextInt();
        }
        for(int i = 0;i < c;i++){
            for(int j = v;j >= m[i];j--){
                dp[j] = Math.max(dp[j],dp[j - m[i]] + vv[i]);
            }
        }
        System.out.println(dp[v]);
    }
}
```

### 完全背包

```java
import java.util.*;
public class Main{
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();
        int v = sc.nextInt();
        int []dp = new int[v + 1];
        int []m = new int[c];
        int []vv = new int[c];
        for(int i = 0;i < c;i++){
            m[i] = sc.nextInt();
            vv[i] = sc.nextInt();
        }
        for(int i = 0;i < c;i++){
            for(int j = m[i];j <=v;j++){
                dp[j] = Math.max(dp[j],dp[j - m[i]] + vv[i]);
            }
        }
        System.out.println(dp[v]);
    }
}
```

### 多重背包（二进制优化）

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int vv = sc.nextInt();
        int[] v = new int[100000];
        int[] m = new int[100000];
        int num = 0;
        for (int i = 0; i < n; i++) {
            int v1 = sc.nextInt();
            int va = sc.nextInt();
            int cn = sc.nextInt();
            for (int j = 1;j < cn ; j<<=1) {
                v[num] = j*v1;
                m[num++] = j*va;
                cn -= j;
            }
            if ( cn!= 0){
                v[num] = cn*v1;
                m[num ++] = cn*va;
            }
        }
        int[] dp = new int[vv + 1];
        for (int i = 0; i < num; i++) {
            for (int k = vv; k >= v[i]; k--) {
                dp[k] = Math.max(dp[k],dp[k - v[i]] + m[i]);
            }
        }
        System.out.println(dp[vv]);
    }
}
```

### 混合背包

```java
import java.util.*;
public class Main{
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int v = sc.nextInt();
        int num = 0;
        int[]dp = new int[v + 1];
        int []vv = new int[1000000];
        int []va = new int[1000000];
        int []t = new int[1000000];
        for( int i = 0; i < n; i ++ ){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            if(c == -1){
                vv[num] = a;
                va[num] = b;
                t[num++] = 1;
            }else if(c == 0){
                vv[num] = a;
                va[num] = b;
                t[num++] = 0;
            }else {
                for(int j = 1; j <= c; j <<= 1){
                vv[num] = a*j;
                va[num] = b*j;
                t[num++] = 1;
                c -= j;
                }
                if(c != 0){
                vv[num] = c*a;
                va[num] = c*b;
                t[num++] = 1;
                }
            }
        }
        
        for(int i = 0; i < num; i ++ ){
            if(t[i] == 1){
                for(int j = v; j >= vv[i]; j --){
                    dp[j] = Math.max(dp[j],dp[j - vv[i]] + va[i]);
                }
            }else {
                for(int j = vv[i]; j <= v; j ++ ){
                    dp[j] = Math.max(dp[j],dp[j - vv[i]] + va[i]);
                }
            }
        }
        System.out.println(dp[v]);
    }
}
```

### 多维费用背包

```java
import java.util.*;
public class Main{
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int vv = sc.nextInt();
        int mm = sc.nextInt();
        int[]v = new int[n];
        int[]m = new int[n];
        int[]va = new int[n];
        for(int i = 0; i < n; i ++ ){
            v[i] = sc.nextInt();
            m[i] = sc.nextInt();
            va[i] = sc.nextInt();
        }
        int[][]dp = new int[vv + 1][mm + 1];
        for(int i = 0; i < n; i ++ ){
            for(int j = vv; j >= v[i]; j -- ){
                for(int k = mm; k >= m[i]; k -- ){
                    dp[j][k] = Math.max(dp[j][k],dp[j - v[i]][k - m[i]] + va[i]);
                }
            }
        }
        System.out.println(dp[vv][mm]);
    }
}
```

### 分组背包

```java
import java.util.*;
public class Main{
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int v = sc.nextInt();
        int[] vv = new int[102];
        int[] mm = new int[102];
        int []dp = new int[v + 1];
        for(int i = 0; i < n; i ++ ){
            int c = sc.nextInt();
            for(int j = 0; j < c; j ++){
                vv[j] = sc.nextInt();
                mm[j] = sc.nextInt();
            }
            for(int z = v; z >= 0; z -- ){//这里加强理解
                for(int x = 0; x < c; x ++ ){
                    if(z >= vv[x]) {
                        dp[z] = Math.max(dp[z],dp[z - vv[x]] + mm[x]);
                    }
                }
            }
        }
        System.out.println(dp[v]);
    }
}
```

### 求方案数背包

```java
import java.util.Scanner;
import java.util.Arrays;

public class Main{
    public static void main(String[] arg){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int C = sc.nextInt();
        int[] dp1 = new int[C + 1]; // dp1[i][j] 表示面对前i个物品，当前容量为j时的最大价值
        int[] dp2 = new int[C + 1]; // dp2[i][j] 表示面对前i个物品，当前容量为j最大价值的方案数
        Arrays.fill(dp2, 1);        // 就算一个也不拿，也是一种方案
        for(int i = 0; i < N; i++){
            int vi = sc.nextInt();  // 体积
            int wi = sc.nextInt();  // 价值
            for(int j = C; j >= vi;  j--){
                int get = dp1[j - vi] + wi;
                if(get == dp1[j]){
                    dp2[j] += dp2[j - vi];
                }else if(dp1[j] < get){
                    dp1[j] = get;
                    dp2[j] = dp2[j - vi];
                }
                dp2[j] %= 1000000007;
            }
        }
        System.out.println(dp2[C]);
    }
}
```

### 树状数组

```java
class BIT {
    int[] tree;

    BIT(int[] arr) {
        tree = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            this.add(i + 1, arr[i]);
        }
    }

    int lowBit(int n) {
        return n & -n;
    }

    int query(int x) {
        int sum = 0;
        while (x > 0) {
            sum += tree[x];
            x -= lowBit(x);
        }
        return sum;
    }

    void add(int x, int v) {
        while (x < tree.length) {
            tree[x] += v;
            x += lowBit(x);
        }
    }
}
```

```java
	int[][] bit = new int[250][250];

    int lowBit(int a) {
        return a & -a;
    }

    void add(int x1, int y1, int v) {
        for (int i = x1; i < bit.length; i += lowBit(i)) {
            for (int j = y1; j < bit.length; j += lowBit(j)) {
                bit[i][j] += v;
            }
        }
    }

    int query(int x1, int y1) {
        int ans = 0;
        for (int i = x1; i > 0; i -= lowBit(i)) {
            for (int j = y1; j > 0; j -= lowBit(j)) {
                ans += bit[i][j];
            }
        }
        return ans;
    }
```



### 线段树

```java
    void buildTree(int[] f, int k, int l, int r) {
        if (l == r) {
            f[k] = w[l];
            return;
        }
        int i = (l + r) >> 1;
        buildTree(f, k + k, l, i);
        buildTree(f, k + k + 1, i + 1, r);
        f[k] = f[k + k] + f[k + k + 1];
    }

    void add(int[] f, int k, int l, int r, int x, int y) {
        f[k] = y + f[k];
        if (l == r) return;
        int i = (l + r) >> 1;
        if (x <= i) add(f, k + k, l, i, x, y);
        else add(f, k + k + 1, i + 1, r, x, y);
    }

    int query(int[] f, int k, int l, int r, int s, int t) {
        if (l == s && r == t) return f[k];
        int i = (l + r) >> 1;
        if (t <= i) {
            return query(f, k + k, l, i, s, t);
        } else {
            if (s >= i + 1) {
                return query(f, k + k + 1, i + 1, r, s, t);
            }
            return query(f, k + k, l, i, s, i) + query(f, k + k + 1, i + 1, r, i + 1, t);
        }
    }
```

### 后缀数组

```java
import java.util.*;


public class SuffixArray {
    private final String[] suffixes;//后缀数组
    private final int N;//字符串和数组的长度

    public SuffixArray(String s) {
        N = s.length();
        suffixes = new String[N];
        for (int i = 0; i < N; i++) {
            suffixes[i] = s.substring(i);
        }
        Arrays.sort(suffixes);
    }

    /**
     * @return 数组长度|字符串长度
     **/
    public int length() {
        return N;
    }

    /**
     * @param i 后缀数组索引
     * @return 后缀字符串
     */

    public String select(int i) {
        return suffixes[i];
    }

    /***
     *
     * @param i
     * @return selcet(i)的索引
     */
    public int index(int i) {
        return N - suffixes[i].length();
    }

    /***
     *
     * @param key
     * @return小于键key的后缀数量
     */

    public int rank(String key) {
        //二分查找
        int left = 0, right = N - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            int cmp = key.compareTo(suffixes[mid]);
            if (cmp < 0) {
                right = mid - 1;
            } else if (cmp > 0) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }

    /***
     *
     * @param s
     * @param t
     * @return两个字符串的最长公共前缀
     */
    public int lcp(String s, String t) {
        int N = Math.min(s.length(), t.length());
        for (int i = 0; i < N; i++) {
            if (s.charAt(i) != t.charAt(i)) return i;
        }
        return N;
    }

    /***
     *
     * @param i
     * @return 后缀数组相邻元素suffixes[i]与suffixes[i-1]的最长公共前缀
     */

    public int lcp(int i) {
        return lcp(suffixes[i], suffixes[i - 1]);
    }


}
```



### 单调栈/单调队列

```java
//单调栈（leetcode907）
class Solution {
    int mod = (int) 1e9 + 7;

    public int sumSubarrayMins(int[] arr) {
        var r = new int[arr.length];
        var l = new int[arr.length];
        Arrays.fill(l, -1);
        Arrays.fill(r, arr.length);
        var stack = new ArrayDeque<Integer>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peekLast()] >= arr[i]) {
                r[stack.pollLast()] = i;
            }
            stack.addLast(i);
        }
        stack.clear();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peekLast()] > arr[i]) {
                l[stack.pollLast()] = i;
            }
            stack.addLast(i);
        }
        long ans = 0;
        for (int i = 0; i < arr.length; i++) {
            ans += (i - l[i]) *1L* (r[i] - i) * arr[i] % mod;
            ans %= mod;
        }
        return (int)ans;
    }
}

//单调栈(LC1124)另一种思路，求最长
func longestWPI(hours []int) (ans int) {
    n := len(hours)
    sum := make([]int, n+1)
    stk := []int{0}
    for i, v := range hours {
        if v > 8 {
            sum[i+1]++
        } else {
            sum[i+1]--
        }
        sum[i+1] += sum[i]
        if sum[i+1] < sum[stk[len(stk)-1]] {
            stk = append(stk, i+1)
        }
    }
    for i := n; i >= 1; i-- {
        for len(stk) != 0 && sum[i] > sum[stk[len(stk)-1]] {
            ans = max(ans, i-stk[len(stk)-1])
            stk = stk[0:len(stk)-1]
        }
    }
    return
}

func max(a, b int) int { if b > a { return b }; return a }
```

```java
//单调队列（leetcode239）
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        var deque = new ArrayDeque<Integer>();
        var ans = new int[nums.length - k + 1];
        for (var i = 0; i < nums.length; i++) {
            if (!deque.isEmpty() && deque.peekFirst() <= i - k) deque.pollFirst();
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) deque.pollLast();
            deque.addLast(i);
            if (i >= k - 1) ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }
}
```

```go
//二维单调队列 LC2373
func largestLocal(grid [][]int) [][]int {
	n, m := len(grid), len(grid[0])
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, m)
	}

	for i := 0; i < n; i++ {
		var q []int
		for j := 0; j < m; j++ {
			if len(q) != 0 && j-q[0] >= 3 {
				q = q[1:]
			}
			for len(q) != 0 && grid[i][q[len(q)-1]] < grid[i][j] {
				q = q[:len(q)-1]
			}
			q = append(q, j)
			if j >= 2 {
				f[i][j] = grid[i][q[0]]
			}
		}
	}
	for i := 0; i < m; i++ {
		var q []int
		for j := 0; j < n; j++ {
			if len(q) != 0 && j-q[0] >= 3 {
				q = q[1:]
			}
			for len(q) != 0 && f[q[len(q)-1]][i] < f[j][i] {
				q = q[:len(q)-1]
			}
			q = append(q, j)
			if j >= 2 {
				grid[j][i] = f[q[0]][i]
			}
		}
	}
	ans := make([][]int, n-2)
	for i := range ans {
		ans[i] = make([]int, m-2)
	}
	for i := 2; i < n; i++ {
		for j := 2; j < m; j++ {
			ans[i-2][j-2] = grid[i][j]
		}
	}
	return ans
}

```



### 数位DP

<img src="https://ykexc-1314584214.cos.ap-nanjing.myqcloud.com/image-20221018105851801.png" alt="image-20221018105851801" style="zoom:80%;" />

```java
数位DP经典模板
int f(int i, int cnt, boolean isLimit, boolean isNum, int k) {
        if (i == s.length) return cnt;
        if (!isLimit && dp[i][cnt] >= 0 && isNum) return dp[i][cnt];
        int res = 0;
        if (!isNum) res += f(i + 1, cnt, false, false, k);
        int up = isLimit ? s[i] - '0' : 9;
        for (int j = isNum ? 0 : 1; j <= up; j++) {
            res += f(i + 1, cnt + (j == k ? 1 : 0), isLimit && j == up, true, k);
        }
        if (!isLimit && isNum) dp[i][cnt] = res;
        return res;
    }


//leetcode902、233、600、1012
```

### 区间dp

```java
//戳气球
public int maxCoins(int[] nums) {
        var n = nums.length;
        var nums1 = new int[n + 2];
        System.arraycopy(nums, 0, nums1, 1, n);
        nums1[0] = nums1[n + 1] = 1;
        var dp = new int[n + 2][n + 2];
        for (var i = n; i >= 0; i--) {
            for (var j = i + 1; j <= n + 1; j++) {
                for (var k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + nums1[i] * nums1[j] * nums1[k]);
                }
            }
        }
        return dp[0][n + 1];
    }
```

### 状压dp

```java
//参加考试的最大学生数
class Solution {
    public int maxStudents(char[][] seats) {
        int[] valid = new int[seats.length];
        int m = seats.length;
        int n = seats[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                valid[i] <<= 1;
                valid[i] |= (seats[i][j] == '.' ? 1 : 0);
            }
        }
        int ans = 0;
        int[][] dp = new int[m][1 << n];
        for (int i = 0; i < m; i++) Arrays.fill(dp[i], -1);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 1 << n; j++) {
                if ((j & valid[i]) == j && (j & j << 1) == 0) {
                    if (i == 0) dp[i][j] = Integer.bitCount(j);
                    else {
                        for (int k = 0; k < 1 << n; k++) {
                            if (dp[i - 1][k] != -1 && (j & k >> 1) == 0 && (k & j >> 1) == 0) dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] + Integer.bitCount(j));
                        }
                    }
                }
                ans = Math.max(dp[i][j], ans);
            }
        }
        return ans;
    }
}
//lc526
public int countArrangement2(int n) {
        var mask = 1 << n;
        var dp = new int[n + 1][mask];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < mask; j++) {
                for (int k = 1; k <= n; k++) {
                    if ((j >> (k - 1) & 1) == 0) continue;
                    if (k % i != 0 && i % k != 0) continue;
                    dp[i][j] += dp[i - 1][j & ~(1 << k - 1)];
                }
            }
        }
        return dp[n][mask - 1];
    }
```

### 拓扑排序

```java
//lc 课程表
List<Integer>[] edges;
    int[] visited;
    boolean valid = true;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; ++i) edges[i] = new ArrayList<>();
        visited = new int[numCourses];
        for (int[] info : prerequisites) {
            edges[info[1]].add(info[0]);
        }
        for (int i = 0; i < numCourses && valid; ++i) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return valid;
    }

    public void dfs(int u) {
        visited[u] = 1;
        for (int v: edges[u]) {
            if (visited[v] == 0) dfs(v);
            else if (visited[v] == 1) {
                valid = false;
                return;
            }
        }
        visited[u] = 2;
    }
//课程表二
public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] map = new List[numCourses];
        var r = new int[numCourses];
        var ans = new ArrayList<Integer>();
        var queue = new ArrayDeque<Integer>();
        for (int i = 0; i < numCourses; i++) map[i] = new ArrayList<Integer>();
        for (int[] prerequisite : prerequisites) {
            int x = prerequisite[0], y = prerequisite[1];
            r[x]++;
            map[y].add(x);
        }
        for (int i = 0; i < numCourses; i++) if (r[i] == 0) queue.addLast(i);
        while (!queue.isEmpty()) {
            int t = queue.pollFirst();
            if (r[t] == 0) ans.add(t);
            for (int i : map[t]) {
                r[i]--;
                if (r[i] == 0)
                queue.addLast(i);
            }
        }
        int[] ints = ans.stream().mapToInt(a -> a).toArray();
        return ans.size() == numCourses ? ints : new int[]{};
    }

//深搜
List<Integer> ans = new ArrayList<>();
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] map = new List[numCourses];
        var r = new int[numCourses];
        for (int i = 0; i < numCourses; i++) map[i] = new ArrayList<Integer>();
        for (int[] prerequisite : prerequisites) {
            int x = prerequisite[0], y = prerequisite[1];
            r[x]++;
            map[y].add(x);
        }
        for (int i = 0; i < numCourses; i++) if (r[i] == 0) dfs(i, map, r);
        System.out.println(ans.size());
        int[] ints = ans.stream().mapToInt(a -> a).toArray();
        return ans.size() == numCourses ? ints : new int[]{};
    }

    void dfs(int i, List<Integer>[] map, int[] r) {
        ans.add(i);
        for (int x : map[i]) {
            if (--r[x] == 0) {
                dfs(x, map, r);
                r[x]++;
            }
        }
    }
```

>无向图top_sort

```java
//LC收集树中金币
class Solution {
    public int collectTheCoins(int[] coins, int[][] edges) {
        var n = coins.length;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        var deg = new int[n];
        for (var edge : edges) {
            int x = edge[0], y = edge[1];
            g[x].add(y);
            g[y].add(x);
            deg[x]++;
            deg[y]++;
        }
        //先topSort一遍去除没用的节点
        var queue = new ArrayDeque<Integer>();
        for (int i = 0; i < n; i++)
            if (deg[i] == 1 && coins[i] == 0)
                queue.addLast(i);  //因为是无向图所以从入度为1开始
        while (!queue.isEmpty()) {
            var poll = queue.pollFirst();
            for (var x : g[poll]) {
                if (--deg[x] == 1 && coins[x] == 0) {
                    queue.addLast(x);
                }
            }
        }
        //再次拓扑排序,剥掉外面的两层
        for (int i = 0; i < n; i++)
            if (deg[i] == 1 && coins[i] == 1) queue.addLast(i);
        if (queue.size() <= 1) return 0;
        var time = new int[n];  //根据时间戳判断位于第几层
        while (!queue.isEmpty()) {
            var poll = queue.pollFirst();
            for (var x : g[poll]) {
                if (--deg[x] == 1) {
                    queue.addLast(x);
                    time[x] = time[poll] + 1;
                }
            }
        }
        var ans = 0;
        for (var e : edges) ans += time[e[0]] >= 2 && time[e[1]] >= 2 ? 2 : 0;
        return ans;
    }
}
```



### 数组模拟静态链表

```java
	static int N = (int) 1e5 + 10;//数据规模
	static int head;//头结点下标
	static int idx;//表示存储当前结点已经使用结点的下一个结点
	static int[] e = new int[N];//结点的值
	static int[] ne = new int[N];//结点的下一个结点
	
	/**
	 * 初始化
	 */
	static void init () {
		head = -1;//没有头结点
		idx = 0;//没有存入数据
	}
	/**
	 * 向头部插入val
	 * @param val
	 */
	static void addToHead(int val) {
		e[idx] = val;
		ne[idx] = head;
		head = idx;
		idx++;
	}
	/**
	 * 向k的下标的后添加一个元素
	 * @param k
	 * @param val
	 */
	static void add(int k, int val) {
	e[idx] = val;   // 赋值
        ne[idx] = head; // 插入之前头结点的前面
        head = idx;     // 更新头结点信息
        idx++;          // idx向右移动
	}
	/**
	 * 删除下标为k的下一个元素
	 * @param k
	 */
	static void remove(int k) {
		ne[k] = ne[ne[k]];
	}
```

### 树形dp

>lc6294等等

```java
//树的直径
class Solution {

    int[] e, ne, h;
    int idx = 0;
    int ans = 0, index = 0;
    int N = (int) 1e4;
    public int treeDiameter(int[][] edges) {
        int n = edges.length;
        e = new int[2 * N + 10];
        ne = new int[2 * N + 10];
        h = new int[2 * N + 10];
        Arrays.fill(h, -1);
        for (var edge : edges) {
            add(edge[0], edge[1]);
            add(edge[1], edge[0]);
        }
        dfs(0, -1, 0);
        dfs(index, -1, 0);
        return ans;
    }

    void dfs(int u, int p, int sum) {
        for (int i = h[u]; i != -1; i = ne[i]) {
            int ver = e[i];
            if (ver == p) continue;
            dfs(ver, u, sum + 1);
        }
        if (sum > ans) {
            ans = sum;
            index = u;
        }
    }

    void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }
}
```

### dijkstra

```java
public int networkDelayTime(int[][] times, int n, int k) {
        var len = new int[n + 1][n + 1];
        for (var i = 1; i <= n; i++)
            for (var j = 1; j <= n; j++)
                len[i][j] = i == j ? 0 : 0x3f3f3f3f;
        for (var time : times) len[time[0]][time[1]] = time[2];
        var w = new int[n + 1];
        Arrays.fill(w, 0x3f3f3f3f);
        w[k] = 0;
        var via = new boolean[n + 1];
        for (var i = 1; i <= n; i++) {
            var t = -1;
            for (var j = 1; j <= n; j++) {
                if (!via[j] && (t == -1 || w[j] < w[t])) t = j;
            }
            via[t] = true;
            for (var j = 1; j <= n; j++) {
                w[j] = Math.min(w[j], w[t] + len[t][j]);
            }
        }
        var res = 0;
        for (var i = 1; i <= n; i++) {
            res = Math.max(res, w[i]);
        }
        return res >= 0x3f3f3f3f ? -1 : res;
    }
```

### dijktra优化

```java
class Main {
    static int n = 0, m = 0, N = 1000010;
    static PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> {
        return a[1] - b[1];
    });//堆
    static int[] dist = new int[N];//距离数组
    static boolean[] f = new boolean[N];//标记数组
    static int[] h = new int[N], ne = new int[N], e = new int[N], w = new int[N];//邻接表
    static int idx = 1;

    static int Dijkstra() {//类似广搜的过程
        Arrays.fill(dist, 0x3f3f3f3f);
        dist[1] = 0;//初始化第一个点到自身的距离
        q.offer(new int[]{1, 0});
        while (q.size() != 0) {
            int[] a = q.poll();
            int t = a[0], distance = a[1];
            if (f[t]) continue;
            f[t] = true;
            for (int i = h[t]; i != -1; i = ne[i]) {
                int j = e[i];
                if (dist[j] > distance + w[i]) {
                    dist[j] = distance + w[i];
                    q.offer(new int[]{j, dist[j]});
                }
            }
        }
        if (dist[n] != 0x3f3f3f3f) return dist[n];
        return -1;
    }

    static void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        String[] params = buf.readLine().split(" ");
        n = Integer.parseInt(params[0]);
        m = Integer.parseInt(params[1]);
        Arrays.fill(h, -1);
        for (int i = 1; i <= m; ++i) {
            String[] info = buf.readLine().split(" ");
            int a = Integer.parseInt(info[0]);
            int b = Integer.parseInt(info[1]);
            int c = Integer.parseInt(info[2]);
            add(a, b, c);
        }
        System.out.print(Dijkstra());

    }
}
/*
*使用list数组存储
*/
void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        List<int[]>[] map = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            map[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt(), b = in.nextInt(), v = in.nextInt();
            map[a].add(new int[] {b, v});
        }
        int[] va = new int[n + 1];
        boolean[] vis = new boolean[n + 1];
        Arrays.fill(va, 0x3f3f3f3f);
        va[1] = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        queue.add(new int[] {1, 0});
        while(!queue.isEmpty()) {
            int[] temp = queue.poll();
            int p = temp[0], v = temp[1];
            if (vis[p]) continue;
            for (int[] lis : map[p]) {
                int pp = lis[0], vv = lis[1];
                if (va[pp] > vv + v) {
                    va[pp] = vv + v;
                    queue.add(new int[] {pp, va[pp]});
                }
            }
            vis[p] = true;  //注意vis的的位置，因为有重边，就不能加一个vis一个
        }
        out.println(va[n] == 0x3f3f3f3f ? -1 : va[n]);
    }
```

### bellmanford

```java
void bellmanFord() {//用edges存边权, last作拷贝，防止串联
        for (int i = 0; i < k; i++) {
            System.arraycopy(dist, 0, last, 0, n + 1);
            for (int j = 0; j < m; j++) {
                Edge e = edges[j];
                dist[e.b] = Math.min(dist[e.b], last[e.a] + e.v);
            }
        }
        if (dist[n] >= 0x3f3f3f3f / 2) out.println("impossible");
        else out.println(dist[n]);
    }
```

### spfa

```java
int spfa() {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        dist[1] = 0;
        vis[1] = true;
        queue.addLast(1);
        while (!queue.isEmpty()) {
            int poll = queue.pollFirst();
            vis[poll] = false;
            for (int[] e : lists[poll]) {
                int p = e[0], d = e[1];
                if (dist[p] > d + dist[poll]) {
                    dist[p] = d + dist[poll];
                    if (!vis[p]) {
                        queue.addLast(p);
                        vis[p] = true;
                    }
                }
            }
        }
        return dist[n];
	}
```



### floyd

```java
void floyd() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }
    }
```

### 01BFS

```java
//LC1263
class Solution {


    int n, m;
    char[][] grid;
    boolean[][] vis;
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int minPushBox(char[][] grid) {
        this.grid = grid;
        n = grid.length;
        m = grid[0].length;
        vis = new boolean[n * m][n * m];
        int sx = -1, sy = -1, bx = -1, by = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'S') {
                    sx = i;
                    sy = j;
                }
                if (grid[i][j] == 'B') {
                    bx = i;
                    by = j;
                }
            }
        }

        Deque<int[]> deque = new ArrayDeque<>();
        int sP = cal(sx, sy), sB = cal(bx, by);
        deque.addLast(new int[]{sP, sB, 0});
        vis[sP][sB] = true;
        while (!deque.isEmpty()) {
            int[] poll = deque.pollFirst();
            int[] person = deCal(poll[0]), box = deCal(poll[1]);
            int psx = person[0], psy = person[1], bsx = box[0], bsy = box[1], d = poll[2];
            if (grid[bsx][bsy] == 'T') return d;
            for (var dir : dirs) {
                int psxX = psx + dir[0], psyY = psy + dir[1];
                if (bsx == psxX && bsy == psyY) {
                    int bsxX = bsx + dir[0], bsyY = bsy + dir[1];
                    if (!checkValid(psxX, psyY, bsxX, bsyY)) continue;
                    int P = cal(psxX, psyY), B = cal(bsxX, bsyY);
                    vis[P][B] = true;
                    deque.addLast(new int[]{P, B, d + 1});
                } else {
                    if (!checkValid(psxX, psyY, bsx, bsy)) continue;
                    int P = cal(psxX, psyY), B = poll[1];
                    vis[P][B] = true;
                    deque.addFirst(new int[]{P, B, d});
                }
            }
        }
        return -1;
    }

    int cal(int i, int j) {
        return i * m + j;
    }

    int[] deCal(int v) {
        return new int[]{v / m, v % m};
    }

    boolean checkValid(int x, int y, int i, int j) {
        return x >= 0 && x < n && y >= 0 && y < m && i >= 0 && i < n && j >= 0 && j < m && grid[i][j] != '#' && !vis[cal(x, y)][cal(i, j)] && grid[x][y] != '#';
    }
}
```



### 最小生成树(Kruskal)

```java
package leetcode;

import java.util.PriorityQueue;

/**
 * 最小生成树
 */
public class LeetCode1584 {

    /**
     *Kruskal
     */
    public int minCostConnectPoints(int[][] points) {
        var n = points.length;
        var heap = new PriorityQueue<Edge>((e1, e2) -> e1.cost - e2.cost);
        UnionFind unionFind = new UnionFind(n);
        for (var i = 0; i < n; i++) {
            for (var j = i + 1; j < n; j++) {
                var e1 = points[i];
                var e2 = points[j];
                int cost = Math.abs(e1[0] - e2[0]) + Math.abs(e1[1] - e2[1]);
                Edge edge = new Edge(i, j, cost);
                heap.add(edge);
            }
        }
        var ans = 0;
        var k = 0;
        while (!heap.isEmpty()) {
            Edge poll = heap.poll();
            var point1 = poll.point1;
            var point2 = poll.point2;
            var cost = poll.cost;
            if (!unionFind.check(point2, point1)) {
                ans += cost;
                unionFind.union(point1, point2);
                k++;
            }
            if (k == n - 1) break;
        }
        return ans;
    }


    static class UnionFind {
        int[] union;
        int[] rank;
        int size;

        UnionFind(int size) {
            this.size = size;
            union = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                union[i] = i;
                rank[i] = i;
            }
        }

        int find(int i) {
            if (i == union[i]) return i;
            return union[i] = find(union[i]);
        }

        boolean check(int a, int b) {
            return find(a) == find(b);
        }

        void union(int a, int b) {
            int aa = find(a);
            int bb = find(b);
            if (aa != bb) {
                if (rank[aa] < rank[bb]) {
                    union[aa] = bb;
                } else if (rank[aa] > rank[bb]) {
                    union[bb] = aa;
                } else {
                    union[aa] = bb;
                    rank[bb]++;
                }
            }
        }
    }
    static class Edge {
        int point1;
        int point2;
        int cost;

        Edge(int point1, int point2, int cost) {
            this.point1 = point1;
            this.point2 = point2;
            this.cost = cost;
        }
    }
}

```

### 最小生成树(Prim)

```java
public int minCostConnectPoints2(int[][] points) {
        var n = points.length;
        var vis = new boolean[n];
        var heap = new PriorityQueue<Edge>(Comparator.comparingInt(a -> a.cost));
        var ans = 0;
        var size = n - 1;
        for (var i = 1; i < n; i++) {
            var p0 = points[0];
            var pi = points[i];
            var distance = Math.abs(p0[0] - pi[0]) + Math.abs(p0[1] - pi[1]);
            heap.add(new Edge(0, i, distance));
        }
        vis[0] = true;
        while (!heap.isEmpty()) {
            Edge edge = heap.poll();
            int point2 = edge.point2;
            int cost = edge.cost;
            if (!vis[point2]) {
                ans += cost;
                size--;
                vis[point2] = true;
                for (int i = 0; i < n; i++) {
                    if (!vis[i]) {
                        var distance = Math.abs(points[point2][0] - points[i][0]) + Math.abs(points[point2][1] - points[i][1]);
                        heap.add(new Edge(point2, i, distance));
                    }
                }
            }
            if (size == 0) break;
        }
        return ans;
    }
```



### LCA

```java
class TreeAncestor {
    private int[] depth;
    private int[][] pa;

    public TreeAncestor(int[][] edges) {
        int n = edges.length + 1;
        int m = 32 - Integer.numberOfLeadingZeros(n); // n 的二进制长度
        List<Integer> g[] = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (var e : edges) { // 节点编号从 0 开始
            int x = e[0], y = e[1];
            g[x].add(y);
            g[y].add(x);
        }

        depth = new int[n];
        pa = new int[n][m];
        dfs(g, 0, -1);

        for (int i = 0; i < m - 1; i++) {
            for (int x = 0; x < n; x++) {
                int p = pa[x][i];
                pa[x][i + 1] = p < 0 ? -1 : pa[p][i];
            }
        }
    }

    private void dfs(List<Integer>[] g, int x, int fa) {
        pa[x][0] = fa;
        for (int y : g[x]) {
            if (y != fa) {
                depth[y] = depth[x] + 1;
                dfs(g, y, x);
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        for (; k > 0; k &= k - 1)
            node = pa[node][Integer.numberOfTrailingZeros(k)];
        return node;
    }

    public int getLCA(int x, int y) {
        if (depth[x] > depth[y]) {
            int tmp = y;
            y = x;
            x = tmp;
        }
        // 使 y 和 x 在同一深度
        y = getKthAncestor(y, depth[y] - depth[x]);
        if (y == x)
            return x;
        for (int i = pa[x].length - 1; i >= 0; i--) {
            int px = pa[x][i], py = pa[y][i];
            if (px != py) {
                x = px;
                y = py;
            }
        }
        return pa[x][0];
    }
}

public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root.val == p.val || root.val == q.val ){
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if(left==null) return right;
        if(right == null) return left;
        return root;
    }
```





### 内向基环树找环(最值)

```java
//  LC2360 利用时间戳进行处理O(n), 通解还是拓扑排序
class Solution {
    public int longestCycle(int[] edges) {
        int n = edges.length, ans = -1;
        var time = new int[n];
        for (int i = 0, clock = 1; i < n; ++i) {
            if (time[i] > 0) continue;
            for (int x = i, startTime = clock; x >= 0; x = edges[x]) {
                if (time[x] > 0) { // 重复访问
                    if (time[x] >= startTime) // 找到了一个新的环
                        ans = Math.max(ans, clock - time[x]);
                    break;
                }
                time[x] = clock++;
            }
        }
        return ans;
    }
}
```

### 最短环

```java
class Solution {
    public int findShortestCycle(int n, int[][] edges) {  //删掉一条边,再判断是否连通
        @SuppressWarnings("unchecked")
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (var edge : edges) {
            int x = edge[0], y = edge[1];
            g[x].add(y);
            g[y].add(x);
        }
        int ans = 0x3f3f3f3f;
        int[] dist = new int[n];
        Deque<Integer> queue = new ArrayDeque<>();
        for (var edge : edges) {
            int a = edge[0], b = edge[1];
            Arrays.fill(dist, 0x3f3f3f3f);
            dist[a] = 0;
            queue.addLast(a);
            while (!queue.isEmpty()) {
                int poll = queue.pollFirst();
                for (var e : g[poll]) {
                    if (e == b && poll == a) continue;
                    if (dist[e] > dist[poll] + 1) {
                        dist[e] = dist[poll] + 1;
                        queue.addLast(e);
                    }
                }
            }
            ans = Math.min(dist[b] + 1, ans);
        }
        return ans == 0x3f3f3f3f ? -1 : ans;
    }
}
```



###  字符串哈希

```java
int N = (int) 1e5 + 10, P = 131313;
int[] h = new int[N], p = new int[N];
String s = "aaabbbaaa";
int n = s.length();
char[] chars = s.toCharArray();
p[0] = 1;
for (int i = 1; i <= n; i++) {
    h[i] = h[i - 1] * P + chars[i - 1];
    p[i] = p[i - 1] * P;
}
//前缀和思想 hash1 == hash2
int hash1 = h[3] - h[0] * p[3];
int hash2 = h[9] - h[6] * p[3];
```

### 随机算法-模拟退火

```java
class Solution {
    //模拟退火算法 -leetcode 1815. 得到新鲜甜甜圈的最多组数
    int ans;
    int m;
    Random random;

    public int cost(int[] w) {
        //计算当前排列的代价
        int n = w.length;
        int res = 0;
        for (int i = 0, s = 0; i < n; i++) {
            if (s == 0) res++;
            s = (s + w[i]) % m;
        }
        ans = Math.max(ans, res);
        return -res;//内能和收益是负相关
    }

    public void swap(int[] w, int i, int j) {
        int t = w[i];
        w[i] = w[j];
        w[j] = t;
    }

    public void simulate_anneal(int[] w) {
        double d = 0.97;//降温系数
        int n = w.length;
        for (double t = 1e6; t > 1e-6; t = t * d) {
            int a = random.nextInt(n);
            int b = random.nextInt(n);//交换两个位置
            if (a == b) b = (b + 1) % n;
            int x = cost(w);
            swap(w, a, b);
            int y = cost(w);
            int delta=y-x;
            if(delta<0) continue; //新的解的内能更小
            if((Math.exp(-delta/t)>Math.random())) continue;//接受
            //否则,不接受当前新的解，换回去
            swap(w,a,b);
        }
    }

    public int maxHappyGroups(int batchSize, int[] groups) {
        int step = 30;
        ans = 0;
        m = batchSize;
        random = new Random();
        for (int i = 0; i < step; i++) {
            simulate_anneal(groups);
        }
        return ans;
    }
}
```



