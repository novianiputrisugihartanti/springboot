package astratech.prg7_m5_p2_050.service;

import astratech.prg7_m5_p2_050.model.Detail;
import astratech.prg7_m5_p2_050.model.Pembelian;
import astratech.prg7_m5_p2_050.repository.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailService {

    @Autowired
    DetailRepository detailRepository;
    public boolean save (Detail detail) { Detail result = detailRepository.save(detail);
        boolean isSuccess = true; if(result == null) { }
        isSuccess = false;
        return isSuccess;
    }

    public List<Detail> detailPembelian(Pembelian idPembelian){
        List<Detail> details = detailRepository.getDetailByIdPembelian(idPembelian);
        return details;
    }



}
