using System;
using System.Linq;

class MainClass {
    static int n;
    static int[] height = new int[1000001];

    public static void Main(string[] args) {
        n = int.Parse(Console.ReadLine());

        int[] inputs = Console.ReadLine().Split().Select(int.Parse).ToArray();

        int ans = 0;

        foreach (int k in inputs) {
            if (height[k] == 0) {
                ans++;
                height[k - 1]++;
                continue;
            }

            height[k]--;
            height[k - 1]++;
        }

        Console.WriteLine(ans);
    }
}