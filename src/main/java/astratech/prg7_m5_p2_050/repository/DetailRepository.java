package astratech.prg7_m5_p2_050.repository;

import astratech.prg7_m5_p2_050.model.Detail;
import astratech.prg7_m5_p2_050.model.Helm;
import astratech.prg7_m5_p2_050.model.Pembelian;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetailRepository extends CrudRepository<Detail, Integer> {

    @Query("SELECT h FROM Detail h WHERE h.idPembelian =:idPembelian")
    public List<Detail> getDetailByIdPembelian(@Param("idPembelian") Pembelian idPembelian);
}