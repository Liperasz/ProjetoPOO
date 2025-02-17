package model.bo;

import model.dao.FeedBackDAO;
import model.vo.Feedback;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class FeedBackBO {

    //Função que vai cadastrar o feedback
    public static void CadastrarFeedBack(Feedback feedback) throws SQLException, ClassNotFoundException {
        FeedBackDAO.CadastrarFeedBack(feedback);

    }

    //Função que vai listar os feedbacks
    public static Map<Integer, Feedback> BuscarFeedbacs() throws SQLException, ClassNotFoundException {
        Map<Integer, Feedback> feedbacks = new HashMap<Integer, Feedback>();
        feedbacks = FeedBackDAO.BuscarFeedBacks();
        return feedbacks;
    }
}
