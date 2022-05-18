package com.javarush.task.task33.task3309;

import org.w3c.dom.Document;
import org.w3c.dom.Comment;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;


/* 
Комментарий внутри xml
*/

public class Solution {

    // Список специальных символов, при встрече с которыми мы будем оборачивать весь текст в блок CDATA
    private static final String[] escapeSymbols = {"<", ">", "'", "\"", "&"};

    // Единственный метод, который вызывается в main.
    // Передаём объект для сериализации в XML и последующего превращения в Document.
    // Также передаём tagName, перед которым нужно вставлять <!--комментарий-->, и сам текст комментария.
    public static String toXmlWithComment(Object obj, String tagName, String comment) {
        try {
            return addCommentToTag(convertObjectToXML(obj), tagName, comment);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Главный метод, который создаёт результат
    private static String addCommentToTag(String xml, String tagName, String comment) throws Exception {
        // Из текста получаем Документ
        Document document = getDocument(xml);
        // Приводим к форматированному виду (как выглядит форматированный вид, можно узнать из документации, если интересно)
        document.normalizeDocument();

        // Добавляем блоки CDATA, аргументами передаём сам документ и документ, представленный как элемент (для удобства доступа)
        addCdataBlocks(document, document.getDocumentElement());
        // Добавляем комментарии, в метод передаём название тега, перед которым нужно вставить коммент, и целевой документ
        addComments(tagName, comment, document);

        // Создаём трансформатор, который сделает нам красивый вывод в XML из источника (документа)
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        // Writer нужен как выходной поток для записи результата трансформации
        StringWriter writer = new StringWriter();
        // Трансформатору для работы нужен источник и результат. Создаём их. (Source и Result - это интерфейсы.)
        Source source = new DOMSource(document);    // DOMSource - источник, основанный на документе.
        Result result = new StreamResult(writer);   // StreamResult - это результат, основанный на выходном потоке.
        // Для красоты устанавливаем отступы.
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        // В шапку XML устанавливаем standalone="no", этот параметр говорит, зависит ли XML от других внешних ресурсов
        transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
        // Творим магию превращения из источника в результат
        transformer.transform(source, result);
        // Добываем результирующую строку из writer-а, который поселился параметром у StreamResult-а.
        return writer.toString();
    }

    // Метод, который заключает в блок CDATA текст, содержащий "волшебные" символы
    // При вызове из addCommentToTag() сюда передаётся сам документ и его представление в виде корня иерархии узлов
    private static void addCdataBlocks(Document document, Node rootElement) {
        // Если у корневого элемента есть дочерние элементы
        if (rootElement.hasChildNodes()) {
            // Получить их список
            NodeList childNodes = rootElement.getChildNodes();
            int length = childNodes.getLength();
            // Пройтись по каждому из них
            for (int i = 0; i < length; i++) {
                // Рекурсивно вызвать этот же метод, в котором корнем будет каждый ребёнок (и так далее пока не кончатся дети)
                addCdataBlocks(document, childNodes.item(i));
            }
            // А если дочерних элементов нет
        } else {
            // Помещаем во временную строку текстовое содержимое рассматриваемого элемента
            String textContent = rootElement.getTextContent();
            // Если в этот содержимом есть заранее оговоренные символы
            if (containsEscapeSymbols(textContent)) {
                // Убираем весь текст из рассматриваемого элемента
                rootElement.setTextContent("");
                // В родительский элемент добавляем новый дочерний элемент (создаём сестру),
                // являющийся блоком CDATA, в который помещается сохранённый выше текст
                rootElement.getParentNode().appendChild(document.createCDATASection(textContent));
            }
        }
    }

    // Метод, добавляющий комментарий перед искомым тегом. Передаём искомый тег, что написать в комменте, и документ
    private static void addComments(String tagName, String comment, Document document) {
        // Получаем список только тех узлов, которые соответствуют искомому тегу
        NodeList nodeList = document.getElementsByTagName(tagName);
        // Проходимся по нему
        for (int i = 0; i < nodeList.getLength(); i++) {
            // Создаём узел типа Comment, в параметре передаём желаемый текст.
            Comment documentComment = document.createComment(comment);
            // Приводим комментарий с форматированному виду (что за форматированный вид, можно прочитать в документации)
            documentComment.normalize();
            // Берём узел из списка узлов
            Node item = nodeList.item(i);
            // В родительский узел вставляем комментарий перед дочерним.
            // То есть, получается, мы отталкиваемся не от item, а говорим родителю всунуть комментарий перед его дочерним item
            item.getParentNode().insertBefore(documentComment, item);
        }
    }

    // Получение Документа из строки XML (шаг 2)
    private static Document getDocument(String xml) throws Exception {
        // Получаем фабрику билдеров.
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        // Сообщаем фабрике, что наш XML имеет пространство имён.
        builderFactory.setNamespaceAware(true);
        // Получаем билдера с фабрики.
        DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
        // Возвращаем документ, который билдер строит из потока байтов, поданного из файла XML.
        return documentBuilder.parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));
    }

    // Сериализация объекта в строку XML (шаг 1)
    private static String convertObjectToXML(Object o) throws Exception {
        // Writer, в который будет производиться сериализация
        StringWriter writer = new StringWriter();
        // Создаём маршаллер, который запишет объект в формате XML
        Marshaller marshaller = JAXBContext.newInstance(o.getClass()).createMarshaller();
        // Говорим маршаллеру, что нам нужен красивый вывод
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        // Превращаем объект в XML-запись, результат сливаем во writer
        marshaller.marshal(o, writer);
        // Ну, и возвращаем строку.
        return writer.toString();
    }

    // Простенький метод, который говорит, есть ли в строке какой-нибудь из специальных символов
    private static boolean containsEscapeSymbols(String s) {
        if (s != null && !s.isEmpty()) {
            for (String character : escapeSymbols) {
                if (s.contains(character))
                    return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

    }

}
