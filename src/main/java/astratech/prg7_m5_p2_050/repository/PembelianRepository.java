package astratech.prg7_m5_p2_050.repository;

import astratech.prg7_m5_p2_050.model.Helm;
import astratech.prg7_m5_p2_050.model.Pembelian;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import sun.security.util.Pem;

import java.util.List;

public interface PembelianRepository extends CrudRepository<Pembelian, Integer> {
    @Query("SELECT p FROM Pembelian p WHERE p.idPembelian = :idpembelian")
    public Pembelian getPembelianByIdPembelian(@Param("idpembelian") Integer idpembelian);

    @Query("SELECT p FROM Pembelian p")
    public List<Pembelian> listPembelian();


}