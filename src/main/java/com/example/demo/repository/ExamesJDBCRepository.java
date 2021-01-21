package com.example.demo.repository;

import com.example.demo.model.Exames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpInc;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class ExamesJDBCRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    class ExamesRowMapper implements RowMapper<Exames>{
        @Override
         public Exames mapRow(ResultSet rs, int rowNum) throws SQLException{
            Exames exames = new Exames();
            exames.setId(rs.getLong("id"));
            exames.setNomePaciente(rs.getString("nome_paciente"));
            exames.setNomeExame(rs.getString("nome_exame"));
            exames.setResultadoExame(rs.getString("resultado_exame"));
            exames.setDataExame(rs.getLong("data_exame"));
            return exames;
        }
    }

    public List<Exames> findAll(){
        return jdbcTemplate.query("select * from exames", new ExamesRowMapper());
    }

    public Optional<Exames> findById(long id){
        return Optional.of(jdbcTemplate.queryForObject("select * from exames where id=?", new Object[]{
                id },
        new BeanPropertyRowMapper<Exames>(Exames.class)));
    }

    public List<Exames> findByNomePacienteCrescente(){
        return jdbcTemplate.query("SELECT * FROM exames.exames order by nome_paciente;", new ExamesRowMapper());
    }

    public int deleteById(long id){
        return jdbcTemplate.update("delete from exames where id=?", new Object[]{
            id
        });
    }


    public int insert(Exames exames){
        return jdbcTemplate.update("INSERT INTO `exames`.`exames` (`data_exame`, `nome_exame`, `nome_paciente`, `resultado_exame`) VALUES (NOW(), ?, ?, ?)",
        new Object[]{
                exames.getNomeExame(),
                exames.getNomePaciente(),
                exames.getResultadoExame()
        });
    }

    public int update(Exames exames){
        return jdbcTemplate.update("UPDATE exames " + " set nome_exame = ?, nome_paciente = ?, resultado_exame = ? " + " where id = ?",
                new Object[]{
                        exames.getNomeExame(),
                        exames.getNomePaciente(),
                        exames.getResultadoExame(),
                        exames.getId()
                });
    }




}
