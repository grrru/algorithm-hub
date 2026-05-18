using System;
class Program
{
    static int n;
    static int m;
    static string[] input;
    static Dictionary<string, int> map;
    static void Main(string[] args)
    {
        string[] inp = Console.ReadLine().Split();
        n = int.Parse(inp[0]);
        m = int.Parse(inp[1]);

        input = new string[n];
        map = new Dictionary<string, int>();

        for (int i = 0; i < n; i++)
        {
            input[i] = Console.ReadLine();
        }

        int k = int.Parse(Console.ReadLine());

        int ans = 0;

        for (int i = 0; i < n; i++)
        {
            if (map.ContainsKey(input[i]))
            {
                map[input[i]]++;
                ans = Math.Max(ans, map[input[i]]);
                continue;
            }

            int res = input[i].Count(c => c == '0');

            if (res > k || (k - res) % 2 == 1) continue;

            ans = Math.Max(ans, 1);
            map[input[i]] = 1;
        }

        Console.Write(ans);
    }
}