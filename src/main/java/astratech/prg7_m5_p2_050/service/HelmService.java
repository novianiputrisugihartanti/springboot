package astratech.prg7_m5_p2_050.service;

import astratech.prg7_m5_p2_050.model.Helm;
import astratech.prg7_m5_p2_050.repository.HelmRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HelmService {
    @Autowired
    HelmRepository helmRepository;

    public  boolean save(Helm helm){
        Helm result = helmRepository.save(helm);
        boolean isSuccess = true;
        if(result == null) {
            isSuccess = false;
        }
        return isSuccess;
    }

    public Helm getHelm(Integer idHelm){
        Helm helm = helmRepository.getHelmByIdHelm(idHelm);
        return helm;
    }

    @Transactional
    public void deleteHelm(Integer idHelm) {
        helmRepository.deleteHelmByIdHelm(idHelm);
    }


    public List<Helm> getHelms(){
        List<Helm> helm = helmRepository.getHelms();
        return helm;
    }

    @Transactional
    public void updateHelm(Integer idHelm, Integer harga, String jenis,String merk,Integer stok,String ukuran,String warna) {
        helmRepository.updateHelmById(idHelm, harga, jenis,merk,stok,ukuran,warna);
    }

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "F:";
        List<Helm> helms = (List<Helm>) helmRepository.findAll();
        File file = ResourceUtils.getFile("classpath:helmreport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(helms);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Noviani Putri Sugihartanti");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\helm.html");
        } else if (reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\helm.pdf");

        }
        return "Export Report to" + reportFormat;
    }
}

