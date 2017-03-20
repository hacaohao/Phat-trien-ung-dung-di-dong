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
    public static final String FILE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "currencies.xml";
    private static final String VALID_NAME_TAG = "Exrate";
    private static final String CURRENCY_CODE = "CurrencyCode";
    private static final String CURRENCY_NAME = "CurrencyName";
    private static final String BUY = "Buy";
    private static final String TRANSFER = "Transfer";
    private static final String SELL = "Sell";

    public List<Currency> getCurrencies(){
        List<Currency> currencies = new ArrayList<>();

        try {
            currencies = getCurrenciesFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return currencies;
    }

    private List<Currency> getCurrenciesFromFile() throws IOException, SAXException, ParserConfigurationException {
        Document document = setupDocument();
        List<Currency> currencies = new ArrayList<>();
        Currency VNDcurrency = new Currency("VND", "Vietnam dong", 1.0, 1.0, 1.0);

        currencies.add(VNDcurrency);
        currencies.addAll(getCurrenciesFromParsingXMLDocument(document));

        return currencies;
    }

    private Document setupDocument() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        return builder.parse(new FileInputStream(FILE_PATH));
    }
    
    private List<Currency> getCurrenciesFromParsingXMLDocument(Document document){
        List<Currency> currencies = new ArrayList<>();
        
        Element root = document.getDocumentElement();
        NodeList list = root.getChildNodes();

        for(int i = 0; i < list.getLength(); i++){
            Node node = list.item(i);

            if(isValid(node)) {
                Element element = (Element) node;
                currencies.add(getCurrencyByElement(element));
            }
        }

        return currencies;
    }

    private boolean isValid(Node node){
        if(!isElement(node)){
            return false;
        }else{
            Element element = (Element) node;
            return element.getTagName().equals(VALID_NAME_TAG);
        }
    }

    private boolean isElement(Node node){
        return node instanceof Element;
    }
    
    private Currency getCurrencyByElement(Element element){
        String currencyCode = element.getAttribute(CURRENCY_CODE);
        String currencyName = element.getAttribute(CURRENCY_NAME);
        double buy = Double.parseDouble(element.getAttribute(BUY));
        double transfer = Double.parseDouble(element.getAttribute(TRANSFER));
        double sell = Double.parseDouble(element.getAttribute(SELL));

        return new Currency(currencyCode, currencyName, buy, transfer, sell);
    }
}
