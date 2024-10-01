package br.olinda.factory.model.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.olinda.factory.model.entities.Problema;

public class ProblemaRepository implements Repository<Problema, Integer> {

    @Override
    public void create(Problema problema) throws SQLException {
        
        String query = "INSERT INTO problema (descricao) values (?)";

        PreparedStatement psmt = ConnectionManager.getCurrentConnection().prepareStatement(query);

        psmt.setString(1, problema.getDescricao());

        psmt.execute();

    }

    @Override
    public Problema read(Integer k) throws SQLException {
        
        String query = "SELECT * FROM problema WHERE id = ?";

        PreparedStatement psmt = ConnectionManager.getCurrentConnection().prepareStatement(query);

        psmt.setInt(1, k);
        ResultSet rs = psmt.executeQuery();

        if(rs.next()) {
            
            Problema problema = new Problema();
            
            problema.setId(rs.getInt("id"));
            problema.setDescricao(rs.getString("descricao"));

            return problema;
        }

        return null;
    }

    @Override
    public List<Problema> readAll() throws SQLException {
        
        String query = "SELECT * FROM problema";

        PreparedStatement psmt = ConnectionManager.getCurrentConnection().prepareStatement(query);

        ResultSet rs = psmt.executeQuery();

        List<Problema> problemas = new ArrayList<>();

        while(rs.next()) {

            Problema problema = new Problema();

            problema.setId(rs.getInt("id"));
            problema.setDescricao(rs.getString("descricao"));

            problemas.add(problema);

        }
        
        return problemas;
    }

}