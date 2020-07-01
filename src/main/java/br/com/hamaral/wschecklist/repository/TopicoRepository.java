package br.com.hamaral.wschecklist.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.hamaral.wschecklist.domain.Topico;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Integer> {

    @Transactional(readOnly = true)
    Optional<Topico> findByNome(String nome);

    @Transactional(readOnly = true)
    @Query(nativeQuery = true, value = "SELECT * FROM topico WHERE data_conclusao IS NOT NULL")
    List<Topico> findByFinalizado();

    @Transactional(readOnly = true)
    @Query(nativeQuery = true, value = "SELECT * FROM topico WHERE data_conclusao IS NULL")
    List<Topico> findByNotFinalizado();
}
