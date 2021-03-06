import java.util.*;
import java.sql.*;

class Complain{
	private int idx;
	private int tabNum;
	private String content;

	//For testing
	//print Complain
	public void printComplain(){
		System.out.println("Complain " + this.idx + " from table " + this.tabNum);
		System.out.println("\tContent: " + this.content);
	}

	/**
	 *
	 */	
	public Complain(int idx, int tabNum, String content){
		this.idx = idx;
		this.tabNum = tabNum;
		this.content = new String(content);
	}

	/**
	 * @return the idx
	 */
	public int getIdx() {
		return idx;
	}

	/**
	 * @param idx the idx to set
	 */
	public void setIdx(int idx) {
		this.idx = idx;
	}

	/**
	 * @return the tabNum
	 */
	public int getTabNum() {
		return tabNum;
	}

	/**
	 * @param tabNum the tabNum to set
	 */
	public void setTabNum(int tabNum) {
		this.tabNum = tabNum;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
}

class ComplainList{
	static int nextComplainIdx = 1;

	static List<Complain> complainList = new LinkedList<Complain>();

	public static boolean addComplain(int tabNum, String content){
		if(tabNum < 1 || 20 < tabNum){
			System.out.println("Wrong table number");

			return false;
		}

		if(content.isEmpty()){
			System.out.println("Empty content");

			return false;
		}
		
		complainList.add(new Complain(nextComplainIdx, tabNum, content));


		DatabaseControlObject dbCO = new DatabaseControlObject();
		dbCO.openConnection();

		String query = "insert into Complain values (?, ?, ?)";
		try{
			dbCO.setPreparedStatement(dbCO.getConnection().prepareStatement(query));
				dbCO.getPreparedStatement().setInt(1, nextComplainIdx);
				dbCO.getPreparedStatement().setInt(2, tabNum);
				dbCO.getPreparedStatement().setString(3, content);
			dbCO.getPreparedStatement().executeUpdate();
		}catch(SQLException sqle){
			System.out.println("SQL Exception while inserting trend menu"+ sqle.toString());
			return false;
		}

		dbCO.closeConnection();

		nextComplainIdx++;
		return true;
	}
	public static boolean removeComplain(int complainIdx){
		Iterator<Complain> tempComplainListIterator = complainList.iterator();
		while(tempComplainListIterator.hasNext()){
			if(tempComplainListIterator.next().getIdx() == complainIdx){
				tempComplainListIterator.remove();

				return true;
			}

		}

		System.out.println("Complain " + complainIdx + "does not exist");
		return false;
	}
	public static Complain getComplain(int complainIdx){
		Iterator<Complain> tempComplainListIterator = complainList.iterator();
		while(tempComplainListIterator.hasNext()){
			Complain tempComplain = tempComplainListIterator.next();
			if(tempComplain.getIdx() == complainIdx)
				return tempComplain;
		}
		return null;
	}

	//methods for testing
	public static void printComplainList(){
		Iterator<Complain> tempComplainListIterator = complainList.iterator();
	
		System.out.println("----------Complain list----------");
		while(tempComplainListIterator.hasNext()){
			tempComplainListIterator.next().printComplain();

			System.out.println();
		}
	}
}
