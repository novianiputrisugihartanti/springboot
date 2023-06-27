package astratech.prg7_m5_p2_050.service;

import astratech.prg7_m5_p2_050.model.Detail;
import astratech.prg7_m5_p2_050.model.Helm;
import astratech.prg7_m5_p2_050.model.Pembelian;
import astratech.prg7_m5_p2_050.repository.HelmRepository;
import astratech.prg7_m5_p2_050.repository.PembelianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PembelianService {

    @Autowired
    PembelianRepository pembelianRepository;

    public boolean save (Pembelian pembelian) {
        Pembelian result = pembelianRepository.save(pembelian);
        boolean isSuccess = true;
        if (result == null) {
            isSuccess = false;
        }
        return isSuccess;
    }

    public Pembelian getPembelian(Integer idPembelian){
        Pembelian pembelian = pembelianRepository.getPembelianByIdPembelian(idPembelian);
        return pembelian;
    }

    public List<Pembelian> listPembelian(){
        List<Pembelian> pembelians = pembelianRepository.listPembelian();
        return pembelians;
    }


}
