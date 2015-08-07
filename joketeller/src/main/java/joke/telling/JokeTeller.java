package joke.telling;

import java.util.Random;

public class JokeTeller {

    public String getJoke(){
        String[] joke = {
            "Who does a pharaoh talk to when he's sad?\n- His Mummy.",
            "Why does Waldo always wear stripes?\n- Because he doesn't want to be spotted.",
            "What's great about living in switzerland?\n- Well, the flag is a big plus!",
            "How does NASA organize a party?\n- They planet."
        };

        int min = 0;
        int max = joke.length-1;
        Random random = new Random();

        return joke[random.nextInt(max - min + 1) + min];
    }
}
