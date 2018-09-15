package io.github.guojiank.game.core;

import io.github.guojiank.game.core.Model.*;

import java.util.*;

import static io.github.guojiank.game.core.Model.Cell.*;
import static java.lang.Math.abs;

public class Algorithm {

    /**
     * 寻找最短路径
     *
     * @param src     开始地址
     * @param dst     目标地址
     * @param world
     * @param exclude 要排查的路径上的点
     * @return 返回src 和 dst 路径的列表，包含src和dst。如果没有找到路径返回None。
     * 返回的列表中的第一个元素是src,最后一个元素是dst
     */
    public static List<Coord> findShortestPath(Coord src, Coord dst, Cell[][] world, Set<Coord> exclude) {
        List<Coord> paths = new LinkedList<>(); // 返回的最短路径
        Queue<Coord> q = new LinkedList<>();
        Map<Coord, Coord> m = new HashMap(); // 保存上一步和下一步的先后顺序
        Set<Coord> s = new HashSet(); // 保存访问过的记录

        q.offer(src);
        m.put(src, null);
        s.add(src);
        s.addAll(exclude);

        paths.add(dst);

        while (q.size() > 0) {
            Coord n = q.poll();
            if ((abs(n.row - dst.row) == 1 && n.col == dst.col) ||
                    (abs(n.col - dst.col) == 1) && n.row == dst.row) {
                Coord k = n;
                while (k != null) {
                    paths.add(k);
                    k = m.get(k);
                }
                Collections.reverse(paths);
                return paths;
            } else {
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (abs(abs(i) - abs(j)) == 1) {
                            Coord o = new Coord(n.row + i, n.col + j);
                            Cell value = world[o.row][o.col];
                            if (value != WALL && value != SNAKE && !s.contains(o)) {
                                s.add(o);
                                q.offer(o);
                                m.put(o, n);
                            }
                        }
                    }
                }
            }

        }

        return null;
    }

}
