package br.com.lacostech.pegasusbackend.util;

import java.util.List;
import java.util.Random;

public class Randomizer {

    public static <T> T getRandomValueFromList(List<T> list) {
        Random random = new Random();
        int itemIndex = random.nextInt(list.size());
        return list.get(itemIndex);
    }

}
