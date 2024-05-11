/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOdatamovie;
import java.sql.*;
import java.util.*;
import koneksi.connector;
import model.*;
import DAOImplement.datamovieimplement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author USER
 */
public class datamovieDAO implements datamovieimplement{
    Connection connection;
    
    final String select = "SELECT * FROM movie";
    final String insert = "INSERT INTO movie (judul, alur, penokohan, akting, nilai) VALUES (?, ?, ?, ?, ?);";
    final String update = "UPDATE movie SET judul=?, alur=?, penokohan=?, akting=?, nilai=? WHERE judul=?;";
    final String delete = "DELETE FROM movie WHERE judul=?;";
 
    public datamovieDAO(){
        connection = connector.connection();
    }

    @Override
    public void insert(datamovie m) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(insert, statement.RETURN_GENERATED_KEYS);
            statement.setString(1, m.getJudul());
            statement.setDouble(2, m.getAlur());
            statement.setDouble(3, m.getPenokohan());
            statement.setDouble(4, m.getAkting());
            statement.setDouble(5, m.getNilai());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while(rs.next()) {
                m.setJudul(rs.getString(1));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            try {
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void update(datamovie m) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(update);
            statement.setString(1, m.getJudul());
            statement.setDouble(2, m.getAlur());
            statement.setDouble(3, m.getPenokohan());
            statement.setDouble(4, m.getAkting());
            statement.setDouble(5, m.getNilai());
            statement.setString(6, m.getJudul());
            statement.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            try {
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String judul) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(delete);
            statement.setString(1, judul);
            statement.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            try {
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<datamovie> getAll() {
        List<datamovie> dt = null;
        try{
            dt = new ArrayList<datamovie>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                datamovie movie = new datamovie();
                movie.setJudul(rs.getString("judul"));
                movie.setAlur(rs.getDouble("alur"));
                movie.setPenokohan(rs.getDouble("penokohan"));
                movie.setAkting(rs.getDouble("akting"));
                movie.setNilai(rs.getDouble("nilai"));
                dt.add(movie);
            }
        }catch(SQLException ex){
            Logger.getLogger(datamovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dt;
    }
}
