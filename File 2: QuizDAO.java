import java.sql.*;
import java.util.ArrayList;

public class QuizDAO {

    public int login(String user, String pass){
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "SELECT user_id FROM users WHERE username=? AND password=?"
            );
            ps.setString(1, user);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) return rs.getInt(1);
            else return -1;

        } catch (Exception e) {
            return -1;
        }
    }

    public ArrayList<Question> getQuestions(){
        ArrayList<Question> list = new ArrayList<>();

        try{
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM questions");

            while(rs.next()){
                list.add(new Question(
                    rs.getString("question"),
                    rs.getString("opt1"),
                    rs.getString("opt2"),
                    rs.getString("opt3"),
                    rs.getString("opt4"),
                    rs.getInt("correct_opt")
                ));
            }

        }catch(Exception e){}
        return list;
    }
}
