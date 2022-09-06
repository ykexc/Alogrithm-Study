package leetcode;

import java.util.Arrays;

public class LeetCode1592 {


    public static void main(String[] args) {
        String[] s = {"huya","aaa","bbb","ccc"};
        System.out.println(String.join(" ", s));//repeat(int n)是将此字符串重复多少次;
    }
    public String reorderSpaces(String text) {
        char[] cs = text.toCharArray();
        int space = 0;
        for (char c : cs) {
            if (c == ' ')
                space++;
        }
        String[] words = text.trim().split("\\s+");// <-- 我学费了
        int size = words.length - 1;
        if (size == 0) {
            return words[0] + " ".repeat(space);
        }
        int d = space / size;
        int m = space % size;
        return String.join(" ".repeat(d), words) + " ".repeat(m);//学
    }
    /**
     * Returns a new String composed of copies of the
     * {@code CharSequence elements} joined together with a copy of
     * the specified {@code delimiter}.
     *
     * <blockquote>For example,
     * <pre>{@code
     *     String message = String.join("-", "Java", "is", "cool");
     *     // message returned is: "Java-is-cool"
     * }</pre></blockquote>
     *
     * Note that if an element is null, then {@code "null"} is added.
     *
     * @param  delimiter the delimiter that separates each element
     * @param  elements the elements to join together.
     *
     * @return a new {@code String} that is composed of the {@code elements}
     *         separated by the {@code delimiter}
     *
     * @throws NullPointerException If {@code delimiter} or {@code elements}
     *         is {@code null}
     *
     * @see java.util.StringJoiner
     * @since 1.8
     */
}
