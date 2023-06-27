package astratech.prg7_m5_p2_050.controller;

import astratech.prg7_m5_p2_050.Result;
import astratech.prg7_m5_p2_050.model.Detail;
import astratech.prg7_m5_p2_050.model.Helm;
import astratech.prg7_m5_p2_050.model.Pembelian;
import astratech.prg7_m5_p2_050.service.DetailService;
import astratech.prg7_m5_p2_050.service.HelmService;
import astratech.prg7_m5_p2_050.service.PembelianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class DetailController {

    @Autowired
    DetailService detailService;
    @Autowired
    HelmService helmService;
    @Autowired
    PembelianService pembelianService;

    @PostMapping("/saveDetail")
    public Result save(HttpServletResponse response, @RequestBody Detail detail) {
        Helm helm = helmService.getHelm(detail.getIdHelm().getIdHelm());
        helm.setStok(helm.getStok() - detail.getJumlah());
        helmService.save(helm);

        Pembelian pembelian = pembelianService.getPembelian(detail.getIdPembelian().getIdPembelian());
        pembelian.setTotal(pembelian.getTotal() + detail.getJumlah() * helm.getHarga());
        pembelianService.save(pembelian);

        Detail detail1 = new Detail(detail.getIdDetail(), detail.getIdPembelian(), detail.getIdHelm(), detail.getJumlah(),
        detail.getJumlah() * helm.getHarga());

        boolean isSuccess = detailService.save(detail1);
        if (isSuccess) {
            return new Result(500, "Result");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(200, "Fail");

        }
    }

    @GetMapping("/detailPembelian")
    public List<Detail> detailPembelian(HttpServletResponse response, @RequestParam("idPembelian") Pembelian pembelian){
        List<Detail> details = detailService.detailPembelian(pembelian);
        return details;
    }


}
