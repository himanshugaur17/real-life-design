import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import board.game.models.BasicDiceImpl;
import board.game.models.Board;
import board.game.models.Game;
import board.game.models.OneDimensionalPos;
import board.game.models.Piece;
import board.game.models.Player;
import board.game.models.PlayerToken;
import board.game.models.SnakeAndLadderBoard;
import board.game.rules.RuleEngine;
import board.game.rules.SnakeAndLadderRuleEngine;

public class App {
    public static void main(String[] args) throws Exception {
        List<Player> players = List.of(new Player("1", "himanshu"), new Player("2", "vaus"));
        Map<Player, Piece> playerTokenMap = new HashMap<>();
        playerTokenMap.put(players.get(0), new PlayerToken(players.get(0), new OneDimensionalPos(0)));
        playerTokenMap.put(players.get(1), new PlayerToken(players.get(1), new OneDimensionalPos(0)));
        Board<OneDimensionalPos> board = new SnakeAndLadderBoard();

        RuleEngine<OneDimensionalPos> ruleEngine = new SnakeAndLadderRuleEngine(new HashMap<>(), new BasicDiceImpl());
        Game<OneDimensionalPos> game = new Game<>(ruleEngine, board, players);
        game.startGame();
        while (!game.hasGameEnded()) {
            Player player = game.getNextPlayer();
            game.makeMove(player, playerTokenMap.get(player), null);
        }
    }
}

class LFUCache {
    static class Node {
        Node next, prev;
        int val;
        int key;
        int currFreq;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    static class DLL {
        Node head, tail;

        public DLL() {
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            head.prev = null;
            tail.prev = head;
            tail.next = null;
        }

    }

    private int capacity;
    private Map<Integer, DLL> freqToDLLMap = new HashMap<>();
    private Map<Integer, Node> keyNodeMap = new HashMap<>();
    private int minFreq = 1;

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!keyNodeMap.containsKey(key))
            return -1;
        Node node = keyNodeMap.get(key);
        DLL currFreqDLL = freqToDLLMap.get(node.currFreq);
        removeNodeFromDLL(currFreqDLL, node);
        int newFreq = node.currFreq + 1;
        node.currFreq = newFreq;
        insertNodeAtBeginning(freqToDLLMap.get(newFreq), node);
        return node.val;
    }

    private void insertNodeAtBeginning(LFUCache.DLL dll, LFUCache.Node node) {
        int freq = node.currFreq;
        if (dll == null)
            freqToDLLMap.put(freq, new DLL());
        dll = freqToDLLMap.get(freq);
        Node firstNonDummyOrTailNode = dll.head.next;
        dll.head.next = node;
        node.prev = dll.head;
        node.next = firstNonDummyOrTailNode;
        firstNonDummyOrTailNode.prev = node;

    }

    private void removeNodeFromDLL(LFUCache.DLL currFreqDLL, LFUCache.Node node) {
        Node leftNode = node.prev;
        Node rightNode = node.next;
        leftNode.next = rightNode;
        rightNode.prev = leftNode;
        keyNodeMap.remove(node.key);
        if (currFreqDLL.head.next == currFreqDLL.tail) {
            if (minFreq == node.currFreq)
                minFreq++;
            freqToDLLMap.remove(node.currFreq);
        }

    }

    private void removeLRU(DLL dll) {
        Node tail = dll.tail;
        Node lastNode = tail.prev;
        removeNodeFromDLL(dll, lastNode);
        if (dll.head.next == tail)
            freqToDLLMap.remove(lastNode.currFreq);
    }

    public void put(int key, int value) {
        Node node = keyNodeMap.get(key);
        if (node != null) {
            int oldFreq = node.currFreq;
            int newFreq = oldFreq + 1;
            removeNodeFromDLL(freqToDLLMap.get(oldFreq), node);
            node.currFreq = newFreq;
            node.val = value;
            insertNodeAtBeginning(freqToDLLMap.get(newFreq), node);
        } else {
            node = new Node(key, value);
            node.currFreq = 1;
            node.val = value;
            if (capacity == keyNodeMap.size()) {
                removeLRU(freqToDLLMap.get(minFreq));
            }
            minFreq = 1;
            insertNodeAtBeginning(freqToDLLMap.get(1), node);
        }
        keyNodeMap.put(key, node);
    }
}

class Solution {
    private List<String> buildMeTheString(char[] wordArray, int index, Set<String> set) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < wordArray.length; j++)
                if (j == index)
                    sb.append((char) (i + 'a'));
                else
                    sb.append(wordArray[j]);
            if (!sb.toString().equals(new String(wordArray)) && set.contains(sb.toString()))
                res.add(sb.toString());
        }
        return res;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> adjacencyGraph = new HashMap<>();
        Set<String> dict = new HashSet<>(wordList);
        dict.add(beginWord);
        for (String word : dict) {
            char wordArray[] = word.toCharArray();
            for (int index = 0; index < word.length(); index++) {
                adjacencyGraph.computeIfAbsent(word, (key) -> new ArrayList<>());
                List<String> formedStrings = buildMeTheString(wordArray, index, dict);
                adjacencyGraph.get(word).addAll(formedStrings);
            }
        }
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        int cost = 0;
        while (!q.isEmpty()) {
            int currSize = q.size();
            for (int i = 0; i < currSize; i++) {
                String word = q.poll();
                if (word.equals(endWord))
                    return cost;
                List<String> connectedNodes = adjacencyGraph.get(word);
                for (String v : connectedNodes)
                    q.add(v);
                cost++;
            }
        }
        return 0;
    }
}