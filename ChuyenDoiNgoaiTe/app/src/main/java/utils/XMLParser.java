package utils;

import android.os.Environment;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import model.Currency;

/**
 * Created by hao on 3/15/2017.
 */

public class XMLParser {
    public static final String FILE_NAME = "currencies.xml";
    private static final String VALID_NAME_TAG = "Exrate";
    private static final String CURRENCY_CODE = "CurrencyCode";
    private static final String CURRENCY_NAME = "CurrencyName";
    private static final String BUY = "Buy";
    private static final String TRANSFER = "Transfer";
    private static final String SELL = "Sell";

    public List<Currency> getCurrencies(){
        List<Currency> currencies = new ArrayList<>();

        try {
            DocumentBuilder builder = setupBuilder();
            Document document = getDocument(builder);
            currencies.addAll(parse(document));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return currencies;
    }

    private DocumentBuilder setupBuilder() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        return factory.newDocumentBuilder();
    }

    private Document getDocument(DocumentBuilder builder) throws IOException, SAXException {
        String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
        String xmlfile = sdcard + "/" + FILE_NAME;
        return builder.parse(new FileInputStream(xmlfile));
    }

    private List<Currency> parse(Document document){
        List<Currency>  currencies = new ArrayList<>();

        Currency VNDcurrnecy = new Currency("VND", "Vietnam dong", 1.0, 1.0, 1.0);
        currencies.add(VNDcurrnecy);

        Element root = document.getDocumentElement();
        NodeList list = root.getChildNodes();

        for(int i = 0; i < list.getLength(); i++){
            Node node = list.item(i);

            if(node instanceof Element){
                Element element = (Element) node;

                if(element.getTagName().equals(VALID_NAME_TAG)){
                    Currency currency = new Currency();
                    currency.setCurrencyCode(element.getAttribute(CURRENCY_CODE));
                    currency.setCurrencyName(element.getAttribute(CURRENCY_NAME));
                    currency.setBuy(Double.parseDouble(element.getAttribute(BUY)));
                    currency.setTransfer(Double.parseDouble(element.getAttribute(TRANSFER)));
                    currency.setSell(Double.parseDouble(element.getAttribute(SELL)));

                    currencies.add(currency);

                }
            }
        }

        return currencies;
    }
}
