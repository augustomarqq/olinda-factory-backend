package br.olinda.factory.model.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.olinda.factory.model.entities.Setor;

public class SetorRepository implements Repository<Setor, Integer> {

    
    @Override
    public void create(Setor setor) throws SQLException {

        String query = "INSERT INTO  setor (nome) values (?)";

        PreparedStatement psmt = ConnectionManager.getCurrentConnection().prepareStatement(query);

        psmt.setString(1, setor.getNome());
        psmt.execute();

    }

    @Override
    public Setor read(Integer k) throws SQLException {
        
        String query = "SELECT * FROM setor WHERE id = ?";

        PreparedStatement psmt = ConnectionManager.getCurrentConnection().prepareStatement(query);
        
        psmt.setInt(1, k);
        ResultSet rs = psmt.executeQuery();

        if (rs.next()) {

            FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
            RelatoRepository relatoRepository = new RelatoRepository();

            Setor setor = new Setor();
            setor.setId(rs.getInt("id"));
            setor.setNome(rs.getString("nome"));
            setor.setFuncionarios(funcionarioRepository.searchBySetor(setor.getId()));
            setor.setRelatos(relatoRepository.searchBySetor(setor.getId()));

            return setor;

        }

        return null;
    }

    @Override
    public List<Setor> readAll() throws SQLException {
        
        String query = "SELECT * FROM setor";

        PreparedStatement psmt = ConnectionManager.getCurrentConnection().prepareStatement(query);

        ResultSet rs = psmt.executeQuery();

        FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
        RelatoRepository relatoRepository = new RelatoRepository();

        List<Setor> setores = new ArrayList<>();

        while(rs.next()) {
            
            Setor setor = new Setor();
            setor.setId(rs.getInt("id"));
            setor.setNome(rs.getString("nome"));
            setor.setFuncionarios(funcionarioRepository.searchBySetor(setor.getId()));
            setor.setRelatos(relatoRepository.searchBySetor(setor.getId()));

            setores.add(setor);

        }

        return setores;
    }

}