package dio.bootcamp.PublishedAPI.util;

import java.util.Random;

public class CartaoUtils {

    public static String gerarNumeroCartao() {
        Random random = new Random();
        StringBuilder numeroCartao = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            int bloco = random.nextInt(10000);
            numeroCartao.append(String.format("%04d", bloco));
            if (i < 3) {
                numeroCartao.append(" ");
            }
        }
        return numeroCartao.toString();
    }
}
