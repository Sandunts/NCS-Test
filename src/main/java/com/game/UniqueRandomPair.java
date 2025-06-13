package com.game;

import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class UniqueRandomPair {
    Set<Pair> pairs = new HashSet<>();
    static class Pair{
        int x, y;

        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Pair other = (Pair) obj;
            return x == other.x && y == other.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public Set<Pair> generatePair(int max, int noOfPairs){
        Random rand  = new Random();
        while(pairs.size() < noOfPairs){
            int x = rand.nextInt(max - 1);
            int y = rand.nextInt(max - 1);
            pairs.add(new Pair(x, y));
        }
        return pairs;
    }
}
