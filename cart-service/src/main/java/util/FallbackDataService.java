package util;

import com.birlasoft.domain.product.Product;

import java.util.HashMap;
import java.util.Map;

public class FallbackDataService {

    public static final Map<Long, Product> products = new HashMap<>();

    static {
        //Product one
        Product productOne = new Product();
        productOne.setCategory("Cosmetic");
        productOne.setProductName("HeadAndShoulder");
        productOne.setProductPrice("36");
        productOne.setId(1L);

        //Product two
        Product productTwo = new Product();
        productTwo.setCategory("Hygiene");
        productTwo.setProductName("Harpic");
        productTwo.setProductPrice("99");
        productTwo.setId(2L);

        //Product three
        Product productThree = new Product();
        productThree.setCategory("Grocery");
        productThree.setProductName("Oatmeal");
        productThree.setProductPrice("45");
        productThree.setId(3L);

        products.put(1L, productOne);
        products.put(2L, productTwo);
        products.put(3L, productThree);
    }


    public static Product getProduct(Long productId) {
        return products.get(productId);
    }
}
