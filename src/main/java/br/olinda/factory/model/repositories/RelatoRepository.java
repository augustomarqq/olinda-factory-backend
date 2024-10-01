package br.olinda.factory.model.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.olinda.factory.model.entities.Relato;

public class RelatoRepository implements Repository<Relato, Integer> {

    @Override
    public void create(Relato relato) throws SQLException {
        
        String query = "INSERT INTO relato (setor_id, problema_id, data) values (?, ?, ?)";

        PreparedStatement psmt = ConnectionManager.getCurrentConnection().prepareStatement(query);

        psmt.setInt(1, relato.getSetor().getId());
        psmt.setInt(2, relato.getProblema().getId());
        psmt.setDate(3, java.sql.Date.valueOf(LocalDate.now()));

        psmt.execute();
    }

    @Override
    public Relato read(Integer k) throws SQLException {
        
        String query = "SELECT * FROM relato WHERE id = ?";

        PreparedStatement psmt = ConnectionManager.getCurrentConnection().prepareStatement(query);

        psmt.setInt(1, k);
        ResultSet rs = psmt.executeQuery();

        if(rs.next()) {

            SetorRepository setorRepository = new SetorRepository();
            ProblemaRepository problemaRepository = new ProblemaRepository();

            Relato relato = new Relato();
            
            relato.setId(rs.getInt("id"));
            relato.setSetor(setorRepository.read(rs.getInt("setor_id")));
            relato.setProblema(problemaRepository.read(rs.getInt("problema_id")));
            relato.setData(rs.getDate("data"));

            return relato;

        }

        return null;
    }

    @Override
    public List<Relato> readAll() throws SQLException {
        
        String query = "SELECT * FROM relato";

        PreparedStatement psmt = ConnectionManager.getCurrentConnection().prepareStatement(query);

        ResultSet rs = psmt.executeQuery();

        SetorRepository setorRepository = new SetorRepository();
        ProblemaRepository problemaRepository = new ProblemaRepository();

        List<Relato> relatos = new ArrayList<>();

        while(rs.next()) {

            Relato relato = new Relato();

            relato.setId(rs.getInt("id"));
            relato.setSetor(setorRepository.read(rs.getInt("setor_id")));
            relato.setProblema(problemaRepository.read(rs.getInt("problema_id")));
            relato.setData(rs.getDate("data"));

            relatos.add(relato);
        }

            return relatos;
    }

    public List<Relato> searchBySetor(int setorId) throws SQLException {

        String query = "SELECT * FROM relato WHERE setor_id = ?";

        PreparedStatement psmt = ConnectionManager.getCurrentConnection().prepareStatement(query);

        psmt.setInt(1, setorId);

        ResultSet rs = psmt.executeQuery();

        ProblemaRepository problemaRepository = new ProblemaRepository();

        List<Relato> relatos = new ArrayList<>();

        while(rs.next()) {

            Relato relato = new Relato();

            relato.setId(rs.getInt("id"));
            relato.setProblema(problemaRepository.read(rs.getInt("problema_id")));
            relato.setData(rs.getDate("data"));

            relatos.add(relato);

        }

        return relatos;
    }
}