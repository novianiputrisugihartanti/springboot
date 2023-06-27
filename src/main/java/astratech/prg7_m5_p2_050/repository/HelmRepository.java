package astratech.prg7_m5_p2_050.repository;

import astratech.prg7_m5_p2_050.model.Helm;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HelmRepository extends CrudRepository<Helm, Integer> {
    @Query("SELECT h FROM Helm h WHERE h.idHelm = :idhelm")
    public Helm getHelmByIdHelm(@Param("idhelm") Integer idhelm);

    @Query("SELECT h FROM Helm h")
    public List<Helm> getHelms();

    @Modifying
    @Query("DELETE from Helm h WHERE h.idHelm = :idhelm")
    public void deleteHelmByIdHelm(@Param("idhelm") Integer idhelm);

    @Modifying
    @Query("UPDATE Helm h SET h.harga = :harga, h.jenis = :jenis, h.merk = :merk, h.stok = :stok, h.ukuran = :ukuran, h.warna = :warna  WHERE h.idHelm = :idhelm")
    public void updateHelmById(@Param("idhelm") Integer idhelm, @Param("harga") Integer harga, @Param("jenis") String jenis, @Param("merk") String merk, @Param("stok") Integer stok, @Param("ukuran") String ukuran, @Param("warna") String warna);

}
