package com.warehouse.service.servlets.spring;

import com.warehouse.dao.spring.ArrivalOfProductRepository;
import com.warehouse.dao.spring.MovingOfProductRepository;
import com.warehouse.dao.spring.SaleOfProductRepository;
import com.warehouse.model.ArrivalOfProduct;
import com.warehouse.model.MovingOfProduct;
import com.warehouse.model.SaleOfProduct;
import com.warehouse.model.interfaceModel.Model;
import com.warehouse.service.folder.Folder;
import com.warehouse.service.json.Converter;
import com.warehouse.service.zip.ZipArchive;
import com.warehouse.view.interfaceView.View;
import com.warehouse.view.jdbcSpringView.GeneralProductViewSpringImpl;
import com.warehouse.view.jdbcSpringView.StockBalancesViewSpringImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WindowController {

    private static final String UPLOAD_DIR = "D:\\IDEA\\apache-tomcat-8.5.50\\temp";
    private static final String JSON = "json";
    @Resource(name = "arrRep")
    ArrivalOfProductRepository arrivalOfProductRepository;
    @Resource(name = "saleRep")
    SaleOfProductRepository saleOfProductRepository;
    @Resource(name = "movingRep")
    MovingOfProductRepository movingOfProductRepository;

    @Autowired
    private List<ArrivalOfProduct> productArList;
    @Autowired
    private List<SaleOfProduct> productSaList;
    @Autowired
    private List<MovingOfProduct> productMoList;
    @Autowired
    private List<View> views;
    @Autowired
    GeneralProductViewSpringImpl generalProductViewSpring;

    @Autowired
    StockBalancesViewSpringImpl stockBalancesViewSpring;

    private List json;

    private static final String DOWNLOAD_DIR = "downloads";

    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public String mainWindows() {
        return "redirect:/window";
    }

    @RequestMapping(value = "/window", method = {RequestMethod.POST, RequestMethod.GET})
    public String mainWindow() {
        return "window";
    }

    @PostMapping("/window/{product}")
    public String windowProduct(@PathVariable("product") String product) {
        switch (product) {
            case "arrival":
                return "arrivalMenu";
            case "sale":
                return "saleMenu";
            case "moving":
                return "movingMenu";
        }
        return "redirect:/window";
    }

    //Реализовать Template
    @PostMapping("/window/{product}/{template}")
    public String windowProduct(@PathVariable("product") String product, @PathVariable String template, ModelMap map,
                                @RequestParam("fileName1") MultipartFile[] files) {
        switch (product) {
            case "arrival":
                switch (template) {
                    case "view_document":

                        return returnPageViewDocument(productArList, arrivalOfProductRepository, "arrivalViewDocument", map);

                    case "view_all_documents":

                        return uploadFilesOnServerAndReturnPage(files, map, new ArrivalOfProduct(), "arrivalSendConfirm",
                                "arrivalSendConfirm", "sentArrivalProduct");
                    case "send":

                        return sendToDataBaseAndReturnPage(map, arrivalOfProductRepository, "arrivalSendSuccessAndGetFilesErrorOrSuccess",
                                "arrivalSendSuccessAndGetFilesErrorOrSuccess");

                    default:
                        return "redirect:/window";
                }

            case "sale":
                switch (template) {
                    case "view_document":
                        return returnPageViewDocument(productSaList, saleOfProductRepository, "saleViewDocument", map);

                    case "view_all_documents":

                        return uploadFilesOnServerAndReturnPage(files, map, new SaleOfProduct(), "saleSendConfirm",
                                "saleSendConfirm", "sentSaveProduct");
                    case "send":
                       return sendToDataBaseAndReturnPage(map,saleOfProductRepository, "saleSendSuccessAndGetFilesErrorOrSuccess",
                                "saleSendSuccessAndGetFilesErrorOrSuccess");
                    default:
                        return "redirect:/window";
                }

            case "moving":
                switch (template) {
                    case "view_document":

                        return returnPageViewDocument(productMoList, movingOfProductRepository, "movingViewDocument", map);

                    case "view_all_documents":

                        return uploadFilesOnServerAndReturnPage(files, map, new MovingOfProduct(), "movingSendConfirm",
                                "movingSendConfirm", "movingSaveProduct");
                    case "send":
                     return    sendToDataBaseAndReturnPage(map,movingOfProductRepository, "movingSendSuccessAndGetFilesErrorOrSuccess",
                                "movingSendSuccessAndGetFilesErrorOrSuccess" );

                    default:
                        return "redirect:/window";
                }
        }
        return "redirect:/window";
    }

    @GetMapping("/*/*/*/*")
    public String ErrorPage() {
        return "redirect:/window";
    }

    @RequestMapping( value = "/window/{product}/{template}/{getFiles}",produces = "text/plain;charset=UTF-8")
    public String windowGetProductFiles(@PathVariable("product") String product, @PathVariable("template") String template,
                                      @PathVariable("getFiles")  String getFiles, HttpServletResponse response, HttpServletRequest request, ModelMap map) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        switch (product) {
            case "arrival":
                if (downloadFileOrReturnPage(template, getFiles, response, request, map)) return "arrivalSendSuccessAndGetFiles";

            case "sale":
                if (downloadFileOrReturnPage(template, getFiles, response, request, map)) return "saleSendSuccessAndGetFiles";
            case "moving":
                if (downloadFileOrReturnPage(template, getFiles, response, request, map)) return "movingSendSuccessAndGetFiles";
        }

        return null;

    }

    private boolean downloadFileOrReturnPage(String template, String getFiles, HttpServletResponse response, HttpServletRequest request, ModelMap map) throws UnsupportedEncodingException {
        switch (template) {
            case "send":
                switch (getFiles) {
                    case "report_product":

                        String param = request.getParameter("productName");
                      //  String decodedToUTF8 = new String(param.getBytes("ISO-8859-1"), "UTF-8");


                        if (returnSuccessPageOrErrorPage(map, param,  generalProductViewSpring.findByAllName(param), generalProductViewSpring.findAllView()))
                            return true;
                        downloadFileFromServer(response, views);
                        break;
                    case "report_sklad":

                        String requestParameter = request.getParameter("stockName");
                       // String UTF8 = new String(requestParameter.getBytes("ISO-8859-1"), "UTF-8");

                        if (returnSuccessPageOrErrorPage(map, requestParameter, stockBalancesViewSpring.findByAllName(requestParameter), stockBalancesViewSpring.findAllView()))
                            return true;
                        downloadFileFromServer(response, views);
                }
        }
        return false;
    }

    private boolean returnSuccessPageOrErrorPage(ModelMap map, String requestParameter, List<View> byAllName, List<View> allView) {
        if (!requestParameter.isEmpty()) {
            views = byAllName;
            if (views.size() == 0) {
                map.addAttribute("marker", null);
                return true;
            } else map.addAttribute("marker", true);
        } else {
            views = allView;
        }
        return false;
    }


    private String returnPageViewDocument(List productList, JpaRepository a, String page, ModelMap map ){
        productList = a.findAll();
        map.addAttribute("productFromServer", productList);
        return page;
    }


    private String uploadFilesOnServerAndReturnPage(MultipartFile[] files, ModelMap map, Model clazz,
                                                    String errReturnPage, String successReturnPage, String ModelMapAttribute) {
        new Folder().deleteListFolder(UPLOAD_DIR);
        try {
            for (MultipartFile file : files) {
                if (file.isEmpty()) {
                    continue;
                }
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOAD_DIR + File.separator + file.getOriginalFilename());
                Files.write(path, bytes);
            }
            json = Converter.toJavaObjectList(clazz);
            map.addAttribute(ModelMapAttribute, json);
            return successReturnPage;
        } catch (Exception e) {
            return errReturnPage;
        }
    }


    private String sendToDataBaseAndReturnPage(ModelMap map, JpaRepository a, String pageSuccess, String pageError) {

        List<Object> root = new ArrayList<>();
        try {
            root.addAll(json);
            a.saveAll(root);
            map.addAttribute("success", true);
            json.clear();
            return pageSuccess;
        } catch (Exception e) {
            map.addAttribute("success", null);
            json.clear();
            return pageError;
        }
    }

    private void downloadFileFromServer( HttpServletResponse response, List<View> productList) {
       try {
        response.setContentType("application/json");
      
        Folder folder = new Folder();
        folder.createFolder(UPLOAD_DIR);
        folder.deleteListFolder(UPLOAD_DIR);

        Converter.toJsonListOfProduct(productList);
        String[] files = folder.getFilesInDir(UPLOAD_DIR);
        File dir = folder.getFolder(UPLOAD_DIR);

        if (files != null && files.length > 0) {

            byte[] zip = new ZipArchive().zipFiles(dir, files);

            ServletOutputStream sos = response.getOutputStream();
            response.setContentType("application/zip");
            response.addHeader("Content-Disposition", "attachment; filename=report.zip");
            sos.write(zip);
            sos.flush();
            response.getOutputStream().flush();

        }
    }catch (IOException e) {
           e.printStackTrace();
       }
    }


}



  /* Для одного файла

    byte[] bytes = file.getBytes();
    Path path = Paths.get(UPLOAD_DIR + File.separator + file.getOriginalFilename());
            Files.write(path, bytes);
    List<Model> json = Converter.toJavaObjectList(request.getRequestURI());
            modelMap.addAttribute("sentArrivalProduct", json);
            return "arrivalSendConfirm";
} catch (AccessDeniedException e) {
        e.printStackTrace();
        return "arrivalSendConfirm";*/



