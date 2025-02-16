package model.bo;

import model.dao.FeedBackDAO;
import model.vo.Feedback;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class FeedBackBO {

    public static void CadastrarFeedBack(Feedback feedback) throws SQLException, ClassNotFoundException {
        FeedBackDAO.CadastrarFeedBack(feedback);

    }

    public static Map<Integer, Feedback> BuscarFeedbacs() throws SQLException, ClassNotFoundException {
        Map<Integer, Feedback> feedbacks = new HashMap<Integer, Feedback>();
        feedbacks = FeedBackDAO.BuscarFeedBacks();
        return feedbacks;
    }
}
