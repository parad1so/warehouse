package com.warehouse.service.servlets;

import com.warehouse.dao.domain.HibernateDaoImpl.service.ArrivalProductService;
import com.warehouse.dao.domain.HibernateDaoImpl.service.MovingProductService;
import com.warehouse.dao.domain.HibernateDaoImpl.service.SaleProductService;
import com.warehouse.dao.domain.documentsDaoJdbc.DocumentsArrivalDao;
import com.warehouse.dao.domain.documentsDaoJdbc.DocumentsMovingDao;
import com.warehouse.dao.domain.documentsDaoJdbc.DocumentsSaleDao;
import com.warehouse.model.ArrivalOfProduct;
import com.warehouse.model.MovingOfProduct;
import com.warehouse.model.SaleOfProduct;
import com.warehouse.model.interfaceModel.Model;
import com.warehouse.service.folder.Folder;
import com.warehouse.service.json.Converter;
import com.warehouse.service.zip.ZipArchive;
import com.warehouse.view.hibernateView.ArrivalProductHibernateViewImpl;
import com.warehouse.view.hibernateView.MovingProductHibernateViewImpl;
import com.warehouse.view.hibernateView.SaleProductHibernateViewImpl;
import com.warehouse.view.interfaceView.View;
import com.warehouse.view.jdbcView.GeneralListOfProductViewImpl;
import com.warehouse.view.jdbcView.StockBalancesViewImpl;

import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

/*@WebServlet(name = "MainWindowServlet", urlPatterns = { "/window/*" })
@MultipartConfig*/
public class WindowServlet extends HttpServlet {


    /** Поле список товаров */
    private List<Model> productList;
    /** Поле отчеты */
    private List<View> reports;

    /** Поле поступление документов */
    private ArrivalProductService serviceAr;
    /** Поле продажи документов */
    private SaleProductService serviceSale;
    /** Поле перемещение документов */
    private MovingProductService serviceMoving;

    /** Поле загрузки для создание директории */
    private static final String UPLOAD_DIR = "uploads";
    /** Поле скачивания для создание директории */
    private static final String DOWNLOAD_DIR = "downloads";
    /** Поле для создание директории */
    private static final String JSON = "json";

    /** Поле поступление документов JDBC */
    private DocumentsArrivalDao documentsArrivalDao;
    /** Поле продажи документов JDBC */
    private DocumentsSaleDao documentsSaleDao;
    /** Поле перемещение документов JDBC */
    private DocumentsMovingDao documentsMovingDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/view/html/Window.html").forward(req, response);
    }

    @Override
    public void init() throws ServletException {
        serviceAr = new ArrivalProductService();
        serviceSale = new SaleProductService();
        serviceMoving = new MovingProductService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestPath = request.getRequestURI();
        switch (requestPath) {
            case "/window/":
                doGet(request, response);
                break;
            case "/window/arrival":
            case "/window/sale":
            case "/window/moving":
                modelMenuView(request, response, requestPath);
                break;
            case "/window/arrival/view_document":
            case "/window/sale/view_document":
            case "/window/moving/view_document":
                documentView(request, response, requestPath);
                break;
            case "/window/arrival/view_all_documents":
            case "/window/sale/view_all_documents":
            case "/window/moving/view_all_documents":
                viewAllDocuments(request, response, requestPath);
                break;
            case "/window/arrival/send":
            case "/window/sale/send":
            case "/window/moving/send":
                sendJsonToDataBase(request, response, requestPath);
                break;
            case "/window/arrival/send_document":
            case "/window/sale/send_document":
            case "/window/moving/send_document":
                sentDocumentView(request, response, requestPath);
            case "/window/arrival/send/report_general_list_of_product_error":
            case "/window/sale/send/report_general_list_of_product_error":
            case "/window/moving/send/report_general_list_of_product_error":
            case "/window/arrival/send/report_stock_balances_error":
            case "/window/sale/send/report_stock_balances_error":
            case "/window/moving/send/report_stock_balances_error":
                getReportsView(request, response, requestPath);
                break;
            case "/window/arrival/send/report":
            case "/window/sale/send/report":
            case "/window/moving/send/report":
                getReportsView(request, response, requestPath);
                break;
        }
    }


    /**
     * Интерфейс для каждого раздела: Поступление, Продажа
     * Перемещение.
     * @param request принимает на вход запрос.
     * @param response отправляет ответ пользователю.
     * @param requestPath содержит путь
     *        части URL от имени протокола до строки запроса.
     * @throws ServletException если целевой ресурс выдает это исключение.
     * @throws IOException если целевой ресурс выдает это исключение.
     */
    private void modelMenuView(HttpServletRequest request, HttpServletResponse response, String requestPath) throws  ServletException, IOException {
        switch (requestPath) {
            case "/window/arrival":
                request.getServletContext().getRequestDispatcher("/view/html/ArrivalProduct/ArrivalMenu.html").forward(request, response);
                break;
            case "/window/sale":
                request.getServletContext().getRequestDispatcher("/view/html/SaleProduct/SaleMenu.html").forward(request,response);
            case "/window/moving":
                request.getServletContext().getRequestDispatcher("/view/html/MovingProduct/MovingMenu.html").forward(request,response);
        }
    }

    /**
     *  Интерфейс для просмотра документов.
     *  Была добавлена для каждого раздела: Поступление, Продажа
     *  Перемещение.
     * @param request принимает на вход запрос.
     * @param response отправляет ответ пользователю.
     * @param requestPath содержит путь
     *        части URL от имени протокола до строки запроса.
     * @throws ServletException если целевой ресурс выдает это исключение.
     * @throws IOException если целевой ресурс выдает это исключение.
     */
    private void documentView(HttpServletRequest request, HttpServletResponse response, String requestPath) throws  ServletException, IOException {
        switch (requestPath) {
            case "/window/arrival/view_document":
                List<ArrivalOfProduct> docAr = new ArrivalProductHibernateViewImpl().findAllView();
                request.setAttribute("arrivalProduct", docAr);

                request.getServletContext().getRequestDispatcher("/view/jsp/ArrivalProduct/DbArrivalViewDocument.jsp").forward(request, response);
                break;
            case "/window/sale/view_document":
                List<SaleOfProduct> docSale = new SaleProductHibernateViewImpl().findAllView();
                request.setAttribute("saleProduct", docSale);
                request.getServletContext().getRequestDispatcher("/view/jsp/SaleProduct/DbSaleViewDocument.jsp").forward(request, response);
                break;
            case "/window/moving/view_document":
                List<MovingOfProduct> docMov = new MovingProductHibernateViewImpl().findAllView();
                request.setAttribute("movingProduct", docMov);
                request.getServletContext().getRequestDispatcher("/view/jsp/MovingProduct/DbMovingViewDocument.jsp").forward(request, response);
                break;
        }

    }

    /**
     * Два интерфейса. Первый интерфейс для показа ошибки, если наш файл который будет отправляться
     * в базу данных отсутствует или некорректен. Второй для выдачи отчетов.
     * Сначала у нас загружается файл или файлы, потом проверяет список товаров. Если он пустой или null ,
     * то перенаправляет на первый интерфейс, иначе на второй.
     * @param request принимает на вход запрос.
     * @param response отправляет ответ пользователю.
     * @param requestPath содержит путь
     *        части URL от имени протокола до строки запроса.
     * @throws ServletException если целевой ресурс выдает это исключение.
     * @throws IOException если целевой ресурс выдает это исключение.
     */
    private void viewAllDocuments(HttpServletRequest request, HttpServletResponse response, String requestPath) throws  ServletException, IOException {

        switch (requestPath) {
            case "/window/arrival/view_all_documents":
                addFilesOnServer(request);
                this.productList = Converter.toJavaObjectListWithRequestPath(requestPath);
                if (this.productList == null) {
                    request.getServletContext().getRequestDispatcher("/view/html/ArrivalProduct/ArrivalMenuError.html").forward(request, response);
                } else if (this.productList.isEmpty()) {
                    request.getServletContext().getRequestDispatcher("/view/html/ArrivalProduct/ArrivalMenuError.html").forward(request, response);
                } else {
                    request.setAttribute("sentArrivalProduct", this.productList);
                    request.getServletContext().getRequestDispatcher("/view/jsp/ArrivalProduct/DbArrivalSend.jsp").forward(request, response);
                }
                break;
            case "/window/sale/view_all_documents":
                addFilesOnServer(request);
                this.productList = Converter.toJavaObjectListWithRequestPath(requestPath);
                if (this.productList == null) {
                    request.getServletContext().getRequestDispatcher("/view/html/SaleProduct/SaleMenuError.html").forward(request, response);
                } else if ( this.productList.isEmpty()) {
                    request.getServletContext().getRequestDispatcher("/view/html/SaleProduct/SaleMenuError.html").forward(request, response);
                } else {
                    request.setAttribute("sentSaleProduct",  this.productList);
                    request.getServletContext().getRequestDispatcher("/view/jsp/SaleProduct/DbSaleSend.jsp").forward(request, response);
                }
                break;
            case "/window/moving/view_all_documents":
                addFilesOnServer(request);
                this.productList = Converter.toJavaObjectListWithRequestPath(requestPath);
                if (this.productList == null) {
                    request.getServletContext().getRequestDispatcher("/view/html/MovingProduct/MovingMenuError.html").forward(request, response);
                } else if ( this.productList.isEmpty()) {
                    request.getServletContext().getRequestDispatcher("/view/html/MovingProduct/MovingMenuError.html").forward(request, response);
                } else {
                    request.setAttribute("sentMovingProduct",  this.productList);
                    request.getServletContext().getRequestDispatcher("/view/jsp/MovingProduct/DbMovingSend.jsp").forward(request, response);
                }
                break;
        }

    }


    /**
     * Два интерфейса. Первый интерефейс для показа ошибки. Второй для выдачи отчетов.
     * Если запрос SQL-запрос успешно был выполнен, то сохраняет объект в базу данных и
     * перенаправляет на второй интерфейс, иначе на первый.
     * @param request принимает на вход запрос.
     * @param response отправляет ответ пользователю.
     * @param requestPath содержит путь
     *        части URL от имени протокола до строки запроса.
     * @throws ServletException если целевой ресурс выдает это исключение.
     * @throws IOException если целевой ресурс выдает это исключение.
     */
    private void sendJsonToDataBase(HttpServletRequest request, HttpServletResponse response, String requestPath) throws  ServletException, IOException {

        switch (requestPath) {
            case "/window/arrival/send":
                try {
                    for (Model product : productList) {
                        serviceAr.save((ArrivalOfProduct) product);
                    }
                    request.getServletContext().getRequestDispatcher("/view/html/ArrivalProduct/DbArrivalSentSuccess.html").forward(request, response);
                } catch (PersistenceException e) {
                    e.printStackTrace();
                    request.getServletContext().getRequestDispatcher("/view/html/ArrivalProduct/ArrivalErrorSendToDb.html").forward(request, response);
                }

                break;
            case "/window/sale/send":
                try {
                    for (Model product : productList) {
                        serviceSale.save((SaleOfProduct) product);
                    }
                    request.getServletContext().getRequestDispatcher("/view/html/SaleProduct/DbSaleSentSuccess.html").forward(request, response);


                } catch (PersistenceException e) {
                    e.printStackTrace();
                    request.getServletContext().getRequestDispatcher("/view/html/SaleProduct/SaleErrorSendToDb.html").forward(request, response);
                }
                break;


            case "/window/moving/send":
                try {
                    for (Model product : productList) {
                        serviceMoving.save((MovingOfProduct) product);
                    }
                    request.getServletContext().getRequestDispatcher("/view/html/MovingProduct/DbMovingSentSuccess.html").forward(request, response);
                } catch (PersistenceException e) {
                    request.getServletContext().getRequestDispatcher("/view/html/MovingProduct/MovingErrorSendToDb.html").forward(request, response);

                }
                break;
        }
    }

    /**
     * Интерфейс для отображение данных, перед отправкой в базу данных.
     * @param request принимает на вход запрос.
     * @param response отправляет ответ пользователю.
     * @param requestPath содержит путь
     *        части URL от имени протокола до строки запроса.
     * @throws ServletException если целевой ресурс выдает это исключение.
     * @throws IOException если целевой ресурс выдает это исключение.
     */
    private void sentDocumentView(HttpServletRequest request, HttpServletResponse response, String requestPath) throws  ServletException, IOException {
        switch (requestPath) {
            case "/window/arrival/send_document":
                request.getServletContext().getRequestDispatcher("/view/jsp/ArrivalProduct/DbArrivalSend.jsp").forward(request, response);
                break;
            case "/window/sale/send_document":
                request.getServletContext().getRequestDispatcher("/view/jsp/SaleProduct/DbSaleSend.jsp").forward(request, response);
                break;
            case "/window/moving/send_document":
                request.getServletContext().getRequestDispatcher("/view/jsp/MovingProduct/DbMovingSend.jsp").forward(request, response);
                break;
        }

    }

    /**
     * Отправляет с сервера на компьютер пользователя отчет/отчеты в zip архиве.
     * Если параметр пустой, то отправляет все данные из базы данных или конкретные данные.
     * Если параметр был введен не корректно, то перенаправит на интерфейс с ошибкой.
     * @param request принимает на вход запрос.
     * @param response отправляет ответ пользователю.
     * @param requestPath содержит путь
     *         части URL от имени протокола до строки запроса.
     * @throws ServletException если целевой ресурс выдает это исключение.
     * @throws IOException если целевой ресурс выдает это исключение.
     */
    private void getReportsView(HttpServletRequest request, HttpServletResponse response, String requestPath) throws  ServletException, IOException {

         switch (requestPath) {
            case "/window/arrival/send/report_general_list_of_product_error":
                request.setCharacterEncoding("UTF-8");
                String nameListArrival = request.getParameter("productName");
                if (nameListArrival.isEmpty()) {
                    reports = new GeneralListOfProductViewImpl().findAllView();

                    downloadFileFromServer(request, response, reports);
                } else {
                    reports = new GeneralListOfProductViewImpl().findByAllName(nameListArrival);
                    if (reports.size() == 0) {
                        request.getServletContext().getRequestDispatcher("/view/jsp/ArrivalProduct/ErrorList.jsp").forward(request, response);
                    } else {
                        downloadFileFromServer(request, response, reports);
                    }
                }
                break;
            case "/window/arrival/send/report_stock_balances_error":
                request.setCharacterEncoding("UTF-8");
                String nameStockArrival = request.getParameter("stockName");
                if (nameStockArrival.isEmpty()) {
                    reports = new StockBalancesViewImpl().findAllView();
                    downloadFileFromServer(request, response, reports);
                } else {
                    reports = new StockBalancesViewImpl().findByAllName(nameStockArrival);
                    if (reports.size() == 0) {
                        request.getServletContext().getRequestDispatcher("/view/jsp/ArrivalProduct/ErrorList.jsp").forward(request, response);
                    } else {
                        downloadFileFromServer(request, response, reports);
                    }
                }
                break;
            case "/window/arrival/send/report":
                request.getServletContext().getRequestDispatcher("/view/html/ArrivalProduct/DbArrivalSentSuccess.html").forward(request, response);
                break;


            case "/window/sale/send/report_general_list_of_product_error":
                request.setCharacterEncoding("UTF-8");
                String nameSale = request.getParameter("productName");
                if (nameSale.isEmpty()) {
                    reports = new GeneralListOfProductViewImpl().findAllView();
                    downloadFileFromServer(request, response, reports);
                } else {
                    reports = new GeneralListOfProductViewImpl().findByAllName(nameSale);
                    if (reports.size() == 0) {
                        request.getServletContext().getRequestDispatcher("/view/jsp/SaleProduct/ErrorList.jsp").forward(request, response);
                    } else {
                        downloadFileFromServer(request, response, reports);
                    }
                }
                break;
            case "/window/sale/send/report_stock_balances_error":
                request.setCharacterEncoding("UTF-8");
                String nameStockSale = request.getParameter("stockName");
                if (nameStockSale.isEmpty()) {
                    reports = new StockBalancesViewImpl().findAllView();
                    downloadFileFromServer(request, response, reports);
                } else {
                    reports = new StockBalancesViewImpl().findByAllName(nameStockSale);
                    if (reports.size() == 0) {
                        request.getServletContext().getRequestDispatcher("/view/jsp/SaleProduct/ErrorList.jsp").forward(request, response);
                    } else {
                        downloadFileFromServer(request, response, reports);
                    }
                }
                break;
            case "/window/sale/send/report":
                request.getServletContext().getRequestDispatcher("/view/html/SaleProduct/DbSaleSentSuccess.html").forward(request, response);
                break;


            case "/window/moving/send/report_general_list_of_product_error":
                request.setCharacterEncoding("UTF-8");
                String nameMoving = request.getParameter("productName");
                if (nameMoving.isEmpty()) {
                    reports = new GeneralListOfProductViewImpl().findAllView();
                    downloadFileFromServer(request, response, reports);
                } else {
                    reports = new GeneralListOfProductViewImpl().findByAllName(nameMoving);
                    if (reports.size() == 0) {
                        request.getServletContext().getRequestDispatcher("/view/jsp/MovingProduct/ErrorList.jsp").forward(request, response);
                    } else {
                        downloadFileFromServer(request, response, reports);
                    }
                }
                break;
            case "/window/moving/send/report_stock_balances_error":
                request.setCharacterEncoding("UTF-8");
                String nameStockMoving = request.getParameter("stockName");
                if (nameStockMoving.isEmpty()) {
                    reports = new StockBalancesViewImpl().findAllView();
                    downloadFileFromServer(request, response, reports);
                } else {
                    reports = new StockBalancesViewImpl().findByAllName(nameStockMoving);
                    if (reports.size() == 0) {
                        request.getServletContext().getRequestDispatcher("/view/jsp/MovingProduct/ErrorList.jsp").forward(request, response);
                    } else {
                        downloadFileFromServer(request, response, reports);
                    }
                }
                break;
            case "/window/moving/send/report":
                request.getServletContext().getRequestDispatcher("/view/html/MovingProduct/DbMovingSentSuccess.html").forward(request, response);
                break;
        }

    }

    /**
     * Загружает файлы типа JSON на сервер.
     * @param request принимает на вход запрос.
     * @throws IOException если целевой ресурс выдает это исключение.
     * @throws ServletException если целевой ресурс выдает это исключение.
     */
    private void addFilesOnServer(HttpServletRequest request) throws IOException, ServletException {
        String applicationPath = request.getServletContext().getRealPath("");
        Folder folder = new Folder();
        folder.createFolder(applicationPath, UPLOAD_DIR, JSON);
        String uploadFilePath = folder.getFolderString(applicationPath, UPLOAD_DIR, JSON);
        for (Part part : request.getParts()) {
            if (part != null && part.getSize() > 0) {
                String fileName = part.getSubmittedFileName();
                String contentType = part.getContentType();
                if (!contentType.equalsIgnoreCase("application/json")) {
                    continue;
                }
                part.write(uploadFilePath + File.separator + fileName);
            }
        }
    }

    /**
     * Скачивает файлы типа JSON с сервера и упаковывает в zip архив.
     * @param request принимает на вход запрос.
     * @param response отправляет ответ пользователю.
     * @param productList список товаров.
     * @throws IOException если целевой ресурс выдает это исключение.
     */
    private void downloadFileFromServer(HttpServletRequest request, HttpServletResponse response, List<View> productList) throws IOException {
        response.setContentType("application/json");
        String applicationPath = request.getServletContext().getRealPath("");
        Folder folder = new Folder();
        folder.createFolder(applicationPath, DOWNLOAD_DIR, JSON);
        folder.deleteListFolder(applicationPath, DOWNLOAD_DIR, JSON);
        Converter.toJsonListOfProduct(productList);
        String[] files = folder.getFilesInDir(applicationPath, DOWNLOAD_DIR, JSON);
        File dir = folder.getFolder(applicationPath, DOWNLOAD_DIR, JSON);
        if (files != null && files.length > 0) {

            byte[] zip = new ZipArchive().zipFiles(dir, files);
            ServletOutputStream sos = response.getOutputStream();
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=report.zip");
            sos.write(zip);
            sos.flush();

        }
    }
}

