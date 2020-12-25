package com.david.tasks.productservice.util;

import com.david.tasks.productservice.modal.Price;
import com.david.tasks.productservice.modal.Product;
import com.david.tasks.productservice.service.ProductService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FilesUtil {

    private Logger logger = LoggerFactory.getLogger(ProductService.class);

    private List<Product> productList = new ArrayList<>();

    public List<Product> getProductList() {
        return productList;
    }

    private List<Product> readFromXML(Resource productsXmlFileResource) throws ParserConfigurationException, IOException, SAXException {
        logger.info("Reading products from XML file");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(productsXmlFileResource.getFile());

        List<Product> productList = new ArrayList<>();

        //root element for the DOM
        Element root = document.getDocumentElement();
        if (root != null && root.hasChildNodes()) {
            NodeList productNodes = root.getChildNodes();
            //Loop over the productNodes from XML file and fill the productList
            for (int i = 0; i < productNodes.getLength(); i++) {
                Node productNode = productNodes.item(i);
                if (productNode != null && productNode.getNodeType() == productNode.ELEMENT_NODE) {
                    Element productElement = (Element) productNode;
                    String productId = productElement.getAttribute("id");
                    String name = productElement.getElementsByTagName("Name").item(0).getTextContent();
                    String description = productElement.getElementsByTagName("Description").item(0).getTextContent();
                    String sku = productElement.getElementsByTagName("sku").item(0).getTextContent();
                    Product product = new Product(productId, name, description, sku);
                    productList.add(product);
                }
            }
        }
        return productList;
    }

    private void readFromJSON(List<Product> products, Resource priceListJsonFileResource) throws IOException {
        logger.info("Reading prices from JSON file");
        JSONTokener jsonTokener = new JSONTokener(priceListJsonFileResource.getInputStream());
        JSONArray unitPriceList = new JSONArray(jsonTokener);
        //Loop over the price list and check each price's SKU if equals one of our productList
        //Then add this price to that product
        unitPriceList.forEach(price -> {
            JSONObject unitPriceJsonObject = (JSONObject) price;
            String productId = (String) unitPriceJsonObject.get("id");
            String unit = (String) unitPriceJsonObject.get("unit");
            JSONObject priceJsonObject = (JSONObject) unitPriceJsonObject.get("price");
            Double value = (Double) priceJsonObject.get("value");
            String currency = (String) priceJsonObject.get("currency");
            for (Product product : products) {
                if (product.getSKU().equals(productId)) {
                    Price newPrice = new Price(value, currency, unit);
                    if (product.getPriceList() == null) {
                        ArrayList<Price> newPriceList = new ArrayList<>();
                        newPriceList.add(newPrice);
                        product.setPriceList(newPriceList);
                    } else {
                        product.getPriceList().add(newPrice);
                    }
                }
            }
        });

    }


    public void mergePricesToProductsList(Resource productsXmlFile, Resource priceListJsonFile) {
        try {
            //read products from XML file and fill productList
            productList = readFromXML(productsXmlFile);
            //update productList from the previous step to add prices from JSON file
            readFromJSON(productList, priceListJsonFile);
            logger.debug("Products list after merging prices: " + productList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
