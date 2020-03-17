import es.datastructur.synthesizer.GuitarString;

import java.util.ArrayList;
import java.util.List;

public class GuitarHero {

    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        List<GuitarString> guitarString = new ArrayList<>();

        for (int i = 0; i < 37; i++) {
            double frequency = 440 * Math.pow(2, (i - 24) / 12.0);
            GuitarString StringI = new GuitarString(frequency);
            guitarString.add(StringI);

        }

        GuitarString currString = guitarString.get(0);

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (keyboard.indexOf(key) < 0) {
                    throw new RuntimeException("input another key");
                }
                int indexOfKey = keyboard.indexOf(key);
                currString = guitarString.get(indexOfKey);
                currString.pluck();

            }

            double sample = currString.sample();
            StdAudio.play(sample);
            currString.tic();

        }

    }


}


