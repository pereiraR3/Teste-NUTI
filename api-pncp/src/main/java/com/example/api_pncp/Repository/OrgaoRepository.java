package com.example.api_pncp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api_pncp.Model.Orgao.Orgao;

import java.util.Optional;

public interface OrgaoRepository extends JpaRepository<Orgao, Long>{

    Optional<Orgao> findByCnpj(String cnpj);
} 
