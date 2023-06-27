package astratech.prg7_m5_p2_050.controller;

import astratech.prg7_m5_p2_050.Result;
import astratech.prg7_m5_p2_050.model.Helm;
import astratech.prg7_m5_p2_050.model.Pembelian;
import astratech.prg7_m5_p2_050.service.HelmService;
import astratech.prg7_m5_p2_050.service.PembelianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
public class PembelianController {

    @Autowired
    PembelianService pembelianService;

    @PostMapping("/savePembelian")
    public Result save(HttpServletResponse response, @RequestBody Pembelian pembelian) {
        LocalDate now = LocalDate.now();
        Date date = java.sql.Date.valueOf(now);
        Pembelian pembelian1 = new Pembelian(pembelian.getIdPembelian(), pembelian.getNama(), date, 0);
        boolean isSuccess = pembelianService.save(pembelian1);

        if (isSuccess) {
            return new Result(500, "Success");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(200, "Fail");
        }
    }

    @GetMapping("/listPembelian")
    public List<Pembelian> listPembelian(HttpServletResponse response){
        List<Pembelian> pembelians = pembelianService.listPembelian();
        return pembelians;
    }


}
