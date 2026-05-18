using System;
using System.Collections.Generic;

class Program
{
    static int M, N, H;
    static int[,,] box;
    static Queue<(int z, int y, int x)> queue = new Queue<(int, int, int)>();
    static int[,] dirs = { {-1,0,0}, {1,0,0}, {0,-1,0}, {0,1,0}, {0,0,-1}, {0,0,1} };

    static void Main()
    {
        ReadInput();
        int result = BFS();
        Console.WriteLine(result);
    }

    static void ReadInput()
    {
        string[] input = Console.ReadLine().Split();
        M = int.Parse(input[0]);
        N = int.Parse(input[1]);
        H = int.Parse(input[2]);

        box = new int[H, N, M];

        for (int h = 0; h < H; h++)
        {
            for (int n = 0; n < N; n++)
            {
                input = Console.ReadLine().Split();
                for (int m = 0; m < M; m++)
                {
                    box[h, n, m] = int.Parse(input[m]);
                    if (box[h, n, m] == 1)
                        queue.Enqueue((h, n, m));
                }
            }
        }
    }

    static int BFS()
    {
        int days = 0;

        while (queue.Count > 0)
        {
            int size = queue.Count;
            for (int i = 0; i < size; i++)
            {
                var (z, y, x) = queue.Dequeue();

                for (int d = 0; d < 6; d++)
                {
                    int nz = z + dirs[d, 0];
                    int ny = y + dirs[d, 1];
                    int nx = x + dirs[d, 2];

                    if (nz >= 0 && nz < H && ny >= 0 && ny < N && nx >= 0 && nx < M && box[nz, ny, nx] == 0)
                    {
                        box[nz, ny, nx] = 1;
                        queue.Enqueue((nz, ny, nx));
                    }
                }
            }
            if (queue.Count > 0) days++;
        }

        return IsAllRipe() ? days : -1;
    }

    static bool IsAllRipe()
    {
        for (int h = 0; h < H; h++)
            for (int n = 0; n < N; n++)
                for (int m = 0; m < M; m++)
                    if (box[h, n, m] == 0)
                        return false;
        return true;
    }
}