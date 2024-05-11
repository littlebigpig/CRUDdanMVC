/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.util.List;
import DAOdatamovie.datamovieDAO;
import DAOImplement.datamovieimplement;
import model.*;
import view.MainView;
/**
 *
 * @author USER
 */
public class datamoviecontroller {
    MainView frame;
    datamovieimplement impldatamovie;
    List<datamovie> dt;
    
    public datamoviecontroller(MainView frame) {
        this.frame = frame;
        impldatamovie = new datamovieDAO();
        dt = impldatamovie.getAll();
    }
    
    public void isitabel() {
        dt = impldatamovie.getAll();
        modeltabeldata mp = new modeltabeldata(dt);
        frame.getTabelData().setModel(mp);
    }
    
    public void insert(){
        try {
        datamovie dt = new datamovie();
        dt.setJudul(frame.getJTxtJudul().getText());
        double alur = Double.parseDouble(frame.getJTxtAlur().getText());
        double penokohan = Double.parseDouble(frame.getJTxtPenokohan().getText());
        double akting = Double.parseDouble(frame.getJTxtAkting().getText());
        double nilai = (alur + penokohan + akting) / 3.0;
        
        dt.setAlur(alur);
        dt.setPenokohan(penokohan);
        dt.setAkting(akting); 
        dt.setNilai(nilai);
        impldatamovie.insert(dt);
        popup.infoBox("Data Berhasil Ditambahkan", "Message");
        
        }catch(NumberFormatException e){
            popup.infoBox("Data Gagal Ditambahkan", "Message");
        }
    }
    
    public void update(){
        try {
        datamovie dt = new datamovie();
        dt.setJudul(frame.getJTxtJudul().getText());
        double alur = Double.parseDouble(frame.getJTxtAlur().getText());
        double penokohan = Double.parseDouble(frame.getJTxtPenokohan().getText());
        double akting = Double.parseDouble(frame.getJTxtAkting().getText());
        double nilai = (alur + penokohan + akting) / 3.0;
        
        dt.setAlur(alur);
        dt.setPenokohan(penokohan);
        dt.setAkting(akting); 
        dt.setNilai(nilai);
        impldatamovie.update(dt);
        popup.infoBox("Data Berhasil Diubah", "Message");
        
        }catch(NumberFormatException e){
            popup.infoBox("Data Gagal Diubah", "Message");
        }
    }
    
    public void delete(){
        String judul = frame.getJTxtJudul().getText();
        impldatamovie.delete(judul);
        popup.infoBox("Data Berhasil Dihapus", "Message");
    }
}



