package Part8;

import java.util.HashMap;

public class Nicknames {
    static void main() {
        HashMap<String, String> nicknames = new HashMap<>();

        nicknames.put("matthew", "matt");
        nicknames.put("michael", "mix");
        nicknames.put("arthur", "artie");

        System.out.println(nicknames.get("matthew"));

    }
}
