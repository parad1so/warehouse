package ru.seoweblab.service.json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import ru.seoweblab.model.ArrivalOfProduct;
import ru.seoweblab.model.MovingOfProduct;
import ru.seoweblab.model.SaleOfProduct;
import ru.seoweblab.model.interfaceModel.Model;
import ru.seoweblab.view.interfaceView.View;
import org.springframework.context.annotation.PropertySource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@PropertySource("classpath:converter.properties")
public class Converter {
    /** Поле для загрузки файла/файлов*/
    private final static String baseFile = "../webapps/root/uploads/json/";

    private final static String baseFileSpring = "D:\\IDEA\\apache-tomcat-8.5.50\\temp\\";
    /** Поле для скачивания файла/файлов*/
    private final static String baseDownloadFile = "../webapps/root/downloads/json" + File.separator;

    /**
     * Преобразует JSON в Java объект.
     * Затем удаляет файлы, которые находились по пути {@link Converter#baseFileSpring}.
     * @param requestPath содержит путь части URL от имени протокола до строки запроса.
     * @return java объект
     * @throws IOException если целевой ресурс выдает это исключение.
     * @since 1.0
     */
    @Deprecated
    public static List<Model> toJavaObjectListWithRequestPath(String requestPath) throws IOException {
        List<Model> jsonToObjectList = new ArrayList<>();
        File dir = new File(baseFile);
        try {
            for (File file : dir.listFiles()
            ) {
                ObjectMapper mapper = new ObjectMapper();
                switch (requestPath) {
                    case "/window/arrival/view_all_documents":
                        ArrivalOfProduct jsonToObjectArrival = mapper.readValue(file, ArrivalOfProduct.class);
                        jsonToObjectList.add(jsonToObjectArrival);
                        break;
                    case "/window/sale/view_all_documents":
                        SaleOfProduct jsonToObjectSale = mapper.readValue(file, SaleOfProduct.class);
                        jsonToObjectList.add(jsonToObjectSale);
                        break;
                    case "/window/moving/view_all_documents":
                        MovingOfProduct jsonToObjectMoving = mapper.readValue(file, MovingOfProduct.class);
                        jsonToObjectList.add(jsonToObjectMoving);
                        break;
                }
                if (file.delete()) {
                    System.out.println("Успешно");
                }
            }
            return jsonToObjectList;


        } catch (UnrecognizedPropertyException | JsonParseException e) {
            for (File file : dir.listFiles()
            ) {
                if (file.delete()) {
                    System.out.println("Успешно");
                }
            }
        }
        return null;
    }

    /**
     * Преобразует JSON в Java объект.
     * Затем удаляет файлы, которые находились по пути {@link Converter#baseFileSpring}.
     * @param model содержит сущность Базы данных.
     * @return java объект
     * @throws IOException если целевой ресурс выдает это исключение.
     * @since 2.0
     */

    public static List<Model> toJavaObjectList(Model model) throws IOException {
        List<Model> jsonToObjectList = new ArrayList<>();
        File dir = new File(baseFileSpring);
        try {
            for (File file : dir.listFiles()
            ) {
                ObjectMapper mapper = new ObjectMapper();
                Model jsonToObjectArrival = mapper.readValue(file, model.getClass());
                jsonToObjectList.add(jsonToObjectArrival);
                if (file.delete()) {
                    System.out.println("Успешно");
                }
            }
            return jsonToObjectList;

        } catch (UnrecognizedPropertyException | JsonParseException e) {
            for (File file : dir.listFiles()
            ) {
                if (file.delete()) {
                    System.out.println("Успешно");
                }
            }
        }
        return null;
    }





    /**
     * Преобразует объект/объекты java в JSON.
     * @param list список объектов для класса {@link View}
     */
    public static void toJsonListOfProduct(List<View> list) {
        try {
            String name = "report";
            int count = 0;
            String typeFile = ".json";

            for (View product : list
            ) {
                String countString = Integer.toString(count);
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.writeValue(new File(baseFileSpring , name + countString + typeFile), product);
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
