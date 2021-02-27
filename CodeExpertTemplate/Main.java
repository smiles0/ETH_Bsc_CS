
import java.util.ArrayList;
import java.util.List;

import io.In;
import io.Out;

class Main {
  public static void main(String[] args) {
    // Uncomment this line if you want to read from a file
    System.out.println("hi");
    In.open("public/test1.in");
    Out.compareTo("public/test1.out");

    int t = In.readInt();
    for (int i = 0; i < t; i++) { // i was < t
      testCase();
    }

    // Uncomment this line if you want to read from a file
    In.close();
  }

  public static void testCase() {
    // Input using In.java class
    int n = In.readInt();
    String dummy = In.readLine(); // Always a dummy row first...

    List<List<Character>> chain = new ArrayList<>();

    for (int i = 1; i <= n; i++) {
      String s = In.readLine();
      List<Character> l = new ArrayList<>();
      for (char cc : s.toCharArray())
        l.add(cc);
      chain.add(l);
    }

    for (int self = 0; self < chain.size(); self++) {
      List<List<Character>> skipped = new ArrayList<>();
      for (int other = 0; other < chain.size(); other++) {

        if (self != other) {
          List<Character> _self = chain.get(self);
          List<Character> _other = chain.get(other);

          for (int i = 1; i < _other.size() - 1; i++) {
            // TODO What if both matches?
            // Search first 2
            if (i != 0 && _self.get(0) == _other.get(i) && _self.get(1) == _other.get(i + 1)) {
              _self.add(0, _other.get(i - 1));
              break;
            }
            // Else Seach last 2
            if (i != 1 && _self.get(_self.size() - 2) == _other.get(i)
                && _self.get(_self.size() - 1) == _other.get(i + 1)) {
              if (i + 2 < _other.size())
                _self.add(_other.get(i + 2));
              break;
            }
            // If nothing happend, add to skipped
            skipped.add(_other);
          }
          // ** BEGIN SKIPPED
          if (skipped.size() != 0) {
            for (int q = 0; q < skipped.size(); q++) {
              // Do for all skipped fist
              List<Character> skpd = skipped.get(q);

              // SAME
              for (int w = 1; w < skpd.size() - 1; w++) {
                if (_self.get(0) == skpd.get(w) && _self.get(1) == skpd.get(w + 1)) {
                  _self.add(0, skpd.get(w - 1));
                  skipped.remove(skpd);
                  break;
                } else {
                  if (w != 1 && _self.get(_self.size() - 2) == skpd.get(w)
                      && _self.get(_self.size() - 1) == skpd.get(w + 1)) {
                    if (w + 2 < skpd.size()) {
                      _self.add(skpd.get(w + 2));
                      skipped.remove(skpd);
                      break;
                    }
                  }
                }

              }
              // END SAMME
            }
          }
          // ** END SKIPPED
        }
      }
    }

    int best = 0;
    for (List<Character> l : chain) {
      if (l.size() > best)
        best = l.size();
    }
    boolean res = false;
    if (best >= n + 2)
      res = true;
    String finalResult = res ? "yes" : "no";
    Out.println(finalResult);
    // Output using Out.java class
    // Out.println("yes");
  }

}
