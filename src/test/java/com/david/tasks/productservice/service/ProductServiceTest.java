package com.david.tasks.productservice.service;

import com.david.tasks.productservice.modal.Price;
import com.david.tasks.productservice.modal.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductServiceTest {

    private ProductService productService;

    @Autowired
    public ProductServiceTest(ProductService productService) {
        this.productService = productService;
    }

    private Product testProduct;

    @BeforeEach
    void setUp() {

        testProduct = new Product(
                "43b105a0-b5da-401b-a91d-32237ae418e4"
                , "Banana"
                , "\n" +
                "            \n" +
                "            <p>The <b>banana</b> is an edible <a href=\"/wiki/Fruit\" title=\"Fruit\">fruit</a> – botanically a <a href=\"/wiki/Berry_(botany)\" title=\"Berry (botany)\">berry</a><sup id=\"cite_ref-purdue1_1-0\" class=\"reference\"><a href=\"#cite_note-purdue1-1\">[1]</a></sup><sup id=\"cite_ref-Armstrong_2-0\" class=\"reference\"><a href=\"#cite_note-Armstrong-2\">[2]</a></sup> – produced by several kinds of large <a href=\"/wiki/Herbaceous\" class=\"mw-redirect\" title=\"Herbaceous\">herbaceous</a> <a href=\"/wiki/Flowering_plant\" title=\"Flowering plant\">flowering plants</a> in the <a href=\"/wiki/Genus\" title=\"Genus\">genus</a> <i><a href=\"/wiki/Musa_(genus)\" title=\"Musa (genus)\">Musa</a></i>.<sup id=\"cite_ref-MW_3-0\" class=\"reference\"><a href=\"#cite_note-MW-3\">[3]</a></sup> In some countries, bananas used for cooking may be called <a href=\"/wiki/Cooking_banana\" title=\"Cooking banana\">plantains</a>, in contrast to <b>dessert bananas</b>. The fruit is variable in size, color and firmness, but is usually elongated and curved, with soft flesh rich in <a href=\"/wiki/Starch\" title=\"Starch\">starch</a> covered with a rind which may be green, yellow, red, purple, or brown when ripe. The fruits grow in clusters hanging from the top of the plant. Almost all modern edible <a href=\"/wiki/Parthenocarpy\" title=\"Parthenocarpy\">parthenocarpic</a> (seedless) bananas come from two wild species&nbsp;– <i><a href=\"/wiki/Musa_acuminata\" title=\"Musa acuminata\">Musa acuminata</a></i> and <i><a href=\"/wiki/Musa_balbisiana\" title=\"Musa balbisiana\">Musa balbisiana</a></i>. The <a href=\"/wiki/Binomial_nomenclature\" title=\"Binomial nomenclature\">scientific names</a> of most cultivated bananas are <i>Musa acuminata</i>, <i>Musa balbisiana</i>, and <i>Musa</i> × <i>paradisiaca</i> for the hybrid <i>Musa acuminata</i> × <i>M.&nbsp;balbisiana</i>, depending on their <a href=\"/wiki/Genome\" title=\"Genome\">genomic</a> constitution. The old scientific name <i>Musa sapientum</i> is no longer used.</p>\n" +
                "            \n" +
                "        "
                , "BA-01");
        Price piecePrice = new Price(2.45, "EUR", "piece");
        Price packagePrice = new Price(10.99, "EUR", "package");

        List<Price> priceList = Arrays.asList(piecePrice, packagePrice);
        testProduct.setPriceList(priceList);


    }

    @Test
    void getAllProducts() {
        assertThat(productService.getAllProducts()).isNotNull();
    }

    @Test
    void getSingleProduct() {

        Product product = productService.getSingleProduct("BA-01");
        assertThat(product).isNotNull();
        assertThat(product.getId()).isEqualTo(testProduct.getId());
        assertThat(product.getSKU()).isEqualTo(testProduct.getSKU());
        assertThat(product.getDescription()).isEqualTo(testProduct.getDescription());
        assertThat(product.getName()).isEqualTo(testProduct.getName());
        assertThat(product.getPriceList()).isEqualTo(testProduct.getPriceList());

    }
}