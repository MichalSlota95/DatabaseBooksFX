package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class UserController implements Initializable {


    @FXML
    private TableView<BooksData> bookstable;
    @FXML
    private TableColumn<BooksData, String> idcolumn;
    @FXML
    private TableColumn<BooksData, String> tittlecolumn;
    @FXML
    private TableColumn<BooksData, String> authorcolumn;
    @FXML
    private TableColumn<BooksData, String> typecolumn;
    @FXML
    private TextField id;
    @FXML
    private TextField tittle;
    @FXML
    private TextField author;
    @FXML
    private TextField type;
    private ObservableList<BooksData> data;
    private SqliteConnection sqliteConnection;


    public void initialize(URL location, ResourceBundle resource) {

        this.sqliteConnection = new SqliteConnection();

    }

    @FXML
    public void clearFields(javafx.event.ActionEvent event) {

        this.id.setText("");
        this.tittle.setText("");
        this.author.setText("");
        this.type.setText("");


    }

    @FXML
    public void addBook(javafx.event.ActionEvent event) {

        String sql = "INSERT INTO `Books`(`id`, `tittle`, `author`, `type`) VALUES (? , ?, ?, ?)";
        try {
            Connection conn = SqliteConnection.Connector();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, this.id.getText());
            stmt.setString(2, this.tittle.getText());
            stmt.setString(3, this.author.getText());
            stmt.setString(4, this.type.getText());


            stmt.execute();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }


    }


    @FXML
    public void LoadBooks(javafx.event.ActionEvent event) {


        try {
            Connection conn = SqliteConnection.Connector();
            this.data = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Books");
            while (rs.next()) {
                this.data.add(new BooksData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException e) {
            System.err.println("Error " + e);
        }
        this.idcolumn.setCellValueFactory(new PropertyValueFactory("ID"));
        this.tittlecolumn.setCellValueFactory(new PropertyValueFactory<BooksData, String>("tittle"));
        this.authorcolumn.setCellValueFactory(new PropertyValueFactory<BooksData, String>("author"));
        this.typecolumn.setCellValueFactory(new PropertyValueFactory<BooksData, String>("type"));


        this.bookstable.setItems(null);
        this.bookstable.setItems(this.data);
    }


}





