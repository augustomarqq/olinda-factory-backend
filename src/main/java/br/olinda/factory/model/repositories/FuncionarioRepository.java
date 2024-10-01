package br.olinda.factory.model.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.olinda.factory.model.entities.Funcionario;

public class FuncionarioRepository implements Repository<Funcionario, Integer> {

    @Override
    public void create(Funcionario funcionario) throws SQLException {

        String query = "INSERT INTO funcionario (nome, setor_id, cargo) values (?, ?, ?)";

        PreparedStatement psmt = ConnectionManager.getCurrentConnection().prepareStatement(query);
        
        psmt.setString(1, funcionario.getNome());
        psmt.setInt(2, funcionario.getSetor().getId());
        psmt.setString(3, funcionario.getCargo());

        psmt.execute();

    }

    @Override
    public Funcionario read(Integer k) throws SQLException {
        
        String query = "SELECT * FROM funcionario WHERE id = ?";

        PreparedStatement psmt = ConnectionManager.getCurrentConnection().prepareStatement(query);

        psmt.setInt(1, k);
        ResultSet rs = psmt.executeQuery();

        if(rs.next()) {

            Funcionario funcionario = new Funcionario();

            funcionario.setId(rs.getInt("id"));
            funcionario.setNome(rs.getString("nome"));
            funcionario.setCargo(rs.getString("cargo"));

            return funcionario;

        }

        return null;
    }

    @Override
    public List<Funcionario> readAll() throws SQLException {
        
        String query = "SELECT * FROM funcionario";

        PreparedStatement psmt = ConnectionManager.getCurrentConnection().prepareStatement(query);

        ResultSet rs = psmt.executeQuery();

        List<Funcionario> funcionarios = new ArrayList<>();

        while(rs.next()) {

            Funcionario funcionario = new Funcionario();

            funcionario.setId(rs.getInt("id"));
            funcionario.setNome(rs.getString("nome"));
            funcionario.setCargo(rs.getString("cargo"));

            funcionarios.add(funcionario);

        }

        return funcionarios;
    }

    public List<Funcionario> searchBySetor(int setorId) throws SQLException {

        String query = "SELECT * FROM funcionario WHERE setor_id = ?";

        PreparedStatement psmt = ConnectionManager.getCurrentConnection().prepareStatement(query);

        psmt.setInt(1, setorId);

        ResultSet rs = psmt.executeQuery();

        List<Funcionario> funcionarios = new ArrayList<>();

        while(rs.next()) {

            Funcionario funcionario = new Funcionario();

            funcionario.setId(rs.getInt("id"));
            funcionario.setNome(rs.getString("nome"));
            funcionario.setCargo(rs.getString("cargo"));

            funcionarios.add(funcionario);

        }

        return funcionarios;
    }
    
}