package astratech.prg7_m5_p2_050.controller;

import astratech.prg7_m5_p2_050.Result;
import astratech.prg7_m5_p2_050.model.Helm;
import astratech.prg7_m5_p2_050.service.HelmService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.List;

@RestController
public class HelmController {
    @Autowired
    HelmService helmService;

//    @PostMapping(value = "/saveHelm", produces = "application/json")
//    public Result save(HttpServletResponse response, @RequestBody Helm helmParam) {
//        Helm helm = new Helm(helmParam.getIdHelm(), helmParam.getMerk(), helmParam.getWarna(), helmParam.getUkuran(), helmParam.getStok(), helmParam.getJenis(), helmParam.getHarga());
//        boolean isSuccess = helmService.save(helm);
//        if (isSuccess) {
//            return new Result(500, "Success");
//        } else {
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            return new Result(200, "Fail");
//        }
//    }
//    @PutMapping("/updateHelm")
//    public void updateHelm(HttpServletResponse response, @RequestParam("id") Integer id, @RequestParam("harga") Integer harga, @RequestParam("jenis") String jenis, @RequestParam("merk") String merk, @RequestParam("stok") Integer stok, @RequestParam("ukuran") String ukuran, @RequestParam("warna") String warna) {
//        helmService.updateHelm(id, harga, jenis, merk, stok, ukuran, warna);
//
//    }

    @GetMapping("/saveHelm")
    public ResponseEntity<Void> save(HttpServletResponse response, @RequestParam(name="merk") String merk,
                                     @RequestParam(name="warna") String warna,
                                     @RequestParam(name="ukuran") String ukuran,
                                     @RequestParam(name="stok") String stok,
                                     @RequestParam(name="jenis") String jenis,
                                     @RequestParam(name="harga") String harga){
        Helm helm = new Helm(0,merk,warna,ukuran,Integer.parseInt(stok),jenis, Integer.parseInt(harga));
        helmService.save(helm);
        return ResponseEntity.status(HttpStatus.FOUND).
                location(URI.create("http://localhost:8080/listhelmjson.html")).build();
    }

    @GetMapping("/getHelm")
    public Helm getHelm(HttpServletResponse response, @RequestParam("idHelm") Integer idHelm) {
        Helm helm = helmService.getHelm(idHelm);
        return helm;
    }

    @GetMapping("/getHelms")
    public List<Helm> getHelms(HttpServletResponse response) {
        List<Helm> helm = helmService.getHelms();
        return helm;
    }

    @DeleteMapping("/deleteHelm")
    public void deleteHelm(HttpServletResponse response, @RequestParam("id") Integer id) {
        helmService.deleteHelm(id);

    }

    @GetMapping("/editHelm")
    public ResponseEntity<Void> editHelm(HttpServletResponse response, @RequestParam("idHelm") Integer idHelm) {
        // Mengatur Location header untuk mengarahkan ke form updatehelmjson.html
        response.setHeader("Location", "http://localhost:8080/updatehelmjson.html?idHelm=" + idHelm);
        return ResponseEntity.status(HttpStatus.SEE_OTHER).build();
    }

    @GetMapping("/updateHelm")
    public ResponseEntity<Void> updateHelm(HttpServletResponse response, @RequestParam(name="idHelm") String idHelm,@RequestParam(name="merk") String merk,
                                     @RequestParam(name="warna") String warna,
                                     @RequestParam(name="ukuran") String ukuran,
                                     @RequestParam(name="stok") String stok,
                                     @RequestParam(name="jenis") String jenis,
                                     @RequestParam(name="harga") String harga){

        helmService.updateHelm( Integer.parseInt(idHelm), Integer.parseInt(harga),  jenis, merk, Integer.parseInt(stok), ukuran, warna);
        return ResponseEntity.status(HttpStatus.FOUND).
                location(URI.create("http://localhost:8080/listhelmjson.html")).build();
    }

    @GetMapping("/helm/report/{format}")    public String generateReport (@PathVariable String format) throws JRException, FileNotFoundException {
        return helmService.exportReport(format);
    }

}
