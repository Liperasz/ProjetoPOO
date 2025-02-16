package model.dao;

import model.vo.Feedback;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class FeedBackDAO {

    public static void CadastrarFeedBack (Feedback comentario) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();


        String sql = "insert into feedback (Feedback, Horario_Feedback)"
                + "   values (?, ?)";

        PreparedStatement stmt;

        stmt = conexao.prepareStatement(sql);

        stmt.setString(1, comentario.getFeedback());
        stmt.setTimestamp(2, Timestamp.valueOf(comentario.getFeedbackTime().withNano(0)));


        stmt.execute();
        stmt.close();
        conexao.close();
    }

    public static Map<Integer, Feedback> BuscarFeedBacks () throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();

        String sql = "select * from feedback";

        PreparedStatement stmt;
        stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        Map<Integer, Feedback> feedbacks = new HashMap<Integer, Feedback>();

        while (rs.next()) {
            Feedback feedback = new Feedback();
            feedback.setFeedback(rs.getString("Feedback"));
            feedback.setFeedbackTime(rs.getTimestamp("Horario_Feedback").toLocalDateTime());
            Integer id = rs.getInt("ID_Feedback");

            feedbacks.put(id, feedback);
        }

        rs.close();
        stmt.close();
        conexao.close();
        return feedbacks;
    }
}
