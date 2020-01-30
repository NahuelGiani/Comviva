package ar.com.ejercicioTres.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.ejercicioTres.domain.ComvivaFile;

@Repository("jpaComvivaFile")
public interface JpaComvivaFile extends JpaRepository<ComvivaFile, Integer>{

}
