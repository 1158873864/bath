package bath.util;


import bath.exception.SystemException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

public class XMLUtil {

    public static String parserXmlToGetPrepayId(String xmlStr) throws SystemException {
        try {
            xmlStr = xmlStr.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");
            Document document = DocumentHelper.parseText(xmlStr);
            Element root = document.getRootElement();
            return root.element("prepay_id").getStringValue();
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new SystemException();
        }
    }

    public static String parserXmlToGetNonceStr(String xmlStr) throws SystemException {
        try {
            xmlStr = xmlStr.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");
            Document document = DocumentHelper.parseText(xmlStr);
            Element root = document.getRootElement();
            return root.element("nonce_str").getStringValue();
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new SystemException();
        }
    }

    public static SortedMap<Object, Object> getSortedMapFromXML(String xmlStr) throws DocumentException {
        //注意：这里默认root的子节点只有一层
        Document document = DocumentHelper.parseText(xmlStr);
        SortedMap<Object, Object> map = new TreeMap<>();
        Element root = document.getRootElement();
        for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
            Element e = (Element) iterator.next();
            map.put(e.getName(), e.getText());
        }
        return map;
    }

}
